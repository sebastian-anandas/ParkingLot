package io.seb.controllers;

import io.seb.dtos.IssueTicketRequestDto;
import io.seb.dtos.IssueTicketResponseDto;
import io.seb.dtos.ResponseStatus;
import io.seb.exceptions.GateNotFoundException;
import io.seb.models.Ticket;
import io.seb.services.TicketService;

public class TicketController {

    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public IssueTicketResponseDto issueTicket(IssueTicketRequestDto requestDto)  {

        IssueTicketResponseDto responseDto = new IssueTicketResponseDto();

        try {
            Ticket ticket = ticketService.issueTicket(
                    requestDto.getGateId(),
                    requestDto.getVehicleNumber(),
                    requestDto.getVehicleOwnerName(),
                    requestDto.getVehicleType()
            );
            responseDto.setTicket(ticket);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);

        } catch (GateNotFoundException gateNotFoundException) {
            gateNotFoundException.getMessage();
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        
        return responseDto;



    }

}
