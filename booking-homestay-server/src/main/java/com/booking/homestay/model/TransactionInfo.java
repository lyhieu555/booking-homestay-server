package com.booking.homestay.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TransactionInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Instant dateRelease;

    private int totalPrice;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_creator", nullable=false)
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_booking", unique = true, nullable=false)
    private Booking booking;

}
