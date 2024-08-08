package io.seb;

import io.seb.controllers.TicketController;
import io.seb.dtos.IssueTicketRequestDto;
import io.seb.dtos.IssueTicketResponseDto;
import io.seb.models.Ticket;
import io.seb.models.VehicleType;
import io.seb.repositories.GateRepository;
import io.seb.repositories.ParkingLotRepository;
import io.seb.repositories.TicketRepository;
import io.seb.repositories.VehicleRepository;
import io.seb.services.TicketService;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
//        System.out.printf("Hello and welcome!");

        //Create a Ticket.
        IssueTicketRequestDto requestDto = new IssueTicketRequestDto();
        requestDto.setGateId(123L);
        requestDto.setVehicleType(VehicleType.SUV);
        requestDto.setVehicleNumber("KA12X6789");
        requestDto.setVehicleOwnerName("Akash");

        GateRepository gateRepository = new GateRepository();
        TicketRepository ticketRepository = new TicketRepository();
        VehicleRepository vehicleRepository = new VehicleRepository();
        ParkingLotRepository parkingLotRepository = new ParkingLotRepository();

        TicketService ticketService = new TicketService(
                gateRepository,
                vehicleRepository,
                parkingLotRepository,
                ticketRepository
        );

        TicketController ticketController = new TicketController(ticketService);

        IssueTicketResponseDto responseDto = ticketController.issueTicket(requestDto);
        Ticket ticket = responseDto.getTicket();
    }
}