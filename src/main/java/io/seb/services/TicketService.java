package io.seb.services;

import io.seb.exceptions.GateNotFoundException;
import io.seb.factory.SpotAssignmnetStrategyFactory;
import io.seb.models.*;
import io.seb.repositories.GateRepository;
import io.seb.repositories.ParkingLotRepository;
import io.seb.repositories.TicketRepository;
import io.seb.repositories.VehicleRepository;
import io.seb.strategies.SpotAssignmentStrategy;

import java.util.Date;
import java.util.Optional;

public class TicketService {

    private GateRepository gateRepository;
    private VehicleRepository vehicleRepository;
    private ParkingLotRepository parkingLotRepository;
    private TicketRepository ticketRepository;

    public TicketService(GateRepository gateRepository, VehicleRepository vehicleRepository,
                         ParkingLotRepository parkingLotRepository, TicketRepository ticketRepository) {
        this.gateRepository = gateRepository;
        this.vehicleRepository = vehicleRepository;
        this.parkingLotRepository = parkingLotRepository;
        this.ticketRepository = ticketRepository;
    }

    public Ticket issueTicket(
            Long gateId,
            String vehicleNumber,
            String vehicleOwnerName,
            VehicleType vehicleType
    ) throws GateNotFoundException {

        Ticket ticket = new Ticket();
        ticket.setEntryTime(new Date());

        // get the gate object from the gateId
        Optional<Gate> optionalGate = gateRepository.findByGateId(gateId);

        if(optionalGate.isEmpty()) {
            throw new GateNotFoundException("Invalid gateId: " + gateId);
        }
        Gate gate = optionalGate.get();
        ticket.setGeneratedAt(gate);
        ticket.setGeneratedBy(gate.getOperator());

        // get the vehicle object with the vehicle number, if it is not present then save it to the DB
        Optional<Vehicle> optionalVehicle = vehicleRepository.findVehicleByVehicleNumber(vehicleNumber);
        Vehicle savedVehicle = null;

        if(optionalVehicle.isEmpty()) {
            Vehicle vehicle = new Vehicle();
            vehicle.setVehicleNumber(vehicleNumber);
            vehicle.setOwnerName(vehicleOwnerName);
            vehicle.setVehicleType(vehicleType);

            savedVehicle = vehicleRepository.save(vehicle);
        } else {
            savedVehicle = optionalVehicle.get();
        }
        ticket.setVehicle(savedVehicle);

        // assign the spot
        ParkingLot parkingLot = parkingLotRepository.getParkingLotByGateId();
        SpotAssignmentStrategyType spotAssignmentStrategyType = parkingLot.getSpotAssignmentStrategyType();

        SpotAssignmentStrategy spotAssignmentStrategy = SpotAssignmnetStrategyFactory.getSpotAssignmentStrategy(
                spotAssignmentStrategyType
        );

        ParkingSpot parkingSpot = spotAssignmentStrategy.assignSpot(vehicleType, gate);
        ticket.setParkingSpot(parkingSpot);

        ticket.setNumber("TICKET_" + gateId + "_" + ticket.getEntryTime());

        return ticketRepository.save(ticket);

    }

}
