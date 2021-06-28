package com.booking.homestay.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DetailPlace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_homestay", nullable=false)
    private HomeStay homeStay;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_place", nullable=false)
    private Place place;

}
