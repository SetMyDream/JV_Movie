package com.dev.cinema.dto;

import com.dev.cinema.models.Ticket;
import lombok.Data;

import java.util.List;

@Data
public class OrderServiceRequestDto {
    private Long userId;
    private List<Ticket> tickets;
}

