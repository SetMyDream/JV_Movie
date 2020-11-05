package com.dev.cinema.dto;

import com.dev.cinema.models.Ticket;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class OrderResponseDto {
    private Long id;
    private String username;
    private List<Ticket> tickets;
    private LocalDateTime orderDate;
}
