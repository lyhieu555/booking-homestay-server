package com.booking.homestay.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class VerificationTokenAccount {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String token;

    @OneToOne(fetch = LAZY)
    @JoinColumn (unique = true, nullable=false)
    private User user;

    private Instant expiryDate ;

}
