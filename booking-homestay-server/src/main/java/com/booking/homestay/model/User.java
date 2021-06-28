package com.booking.homestay.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import java.time.Instant;
import java.util.Date;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String userName;

    @Column(nullable=false)
    private String password;

    @Column(nullable=false)
    private String email;

    private String firstName;

    private String lastName;

    @Column(nullable=false)
    private String phone;

    private String address;

    private String image;

    private String role;

    private Instant createdDate;

    private Date dateOfBirth;

    private String sex;

    @Column(nullable=false)
    private boolean enabled;

    @Column(nullable=false)
    private boolean status;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "id_creator")
    private User creator;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "id_homestay")
    private HomeStay homeStay;
}
