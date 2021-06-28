package com.booking.homestay.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BookingHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String fullName;

    private String address;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private Date dateIn;

    @Column(nullable = false)
    private Date dateOut;

    @Column(nullable = false)
    private int price;

    private Integer costsIncurred;

    private Integer discount;

    @Lob
    private String description;

    @Column(nullable = false)
    private Instant createDate;

    @ManyToOne
    @JoinColumn(name = "id_creator",nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_house", nullable = false)
    private House house;

    @ManyToOne
    @JoinColumn(name = "id_booking", nullable = false)
    private Booking booking;
}
