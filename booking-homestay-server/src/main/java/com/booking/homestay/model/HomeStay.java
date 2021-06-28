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
public class HomeStay {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String homeStayName;

    @Lob
    private String description;

    private String phone;

    @Lob
    private String image;

    private boolean status;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "id_village", nullable=false)
    private Village village;


}
