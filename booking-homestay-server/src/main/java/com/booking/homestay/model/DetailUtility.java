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
public class DetailUtility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_utility",nullable=false)
    private Utility utility;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_house",nullable=false)
    private House house;

    private Integer quantity;

}
