package com.booking.homestay.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

import static javax.persistence.FetchType.LAZY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Booking {

    @Id
    private Long id;

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
    private Integer price;

    private Integer costsIncurred;

    private Integer discount;

    @Column(nullable = false)
    private Integer depositPrice;

    @Lob
    private String identityCard;

    @Column(nullable = false)
    private String status;

    private boolean deposit;

    @Lob
    private String description;

    @Column(nullable = false)
    private Instant createDate;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "id_creator")
    private User creator;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "id_house", nullable = false)
    private House house;


}
