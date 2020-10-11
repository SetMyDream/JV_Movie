package com.dev.cinema.models;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "shopping_cart")
@NoArgsConstructor
@Data
public class ShoppingCart {
    @Id
    private Long id;
    @OneToOne
    @MapsId
    @JoinColumn(name = "shopping_cart_id")
    private User user;
    @OneToMany
    @JoinTable(name = "shopping_cart_tickets",
            joinColumns =
            @JoinColumn(name = "shopping_cart_id"),
            inverseJoinColumns =
            @JoinColumn(name = "ticket_id"))
    private List<Ticket> tickets;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
