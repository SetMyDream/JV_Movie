package com.dev.cinema.dto;

import com.dev.cinema.models.Ticket;
import java.util.List;
import lombok.Data;

@Data
public class OrderServiceRequestDto {
    private Long userId;
    private List<Ticket> tickets;
}

