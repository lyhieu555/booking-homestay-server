package com.booking.homestay.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Village {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String villageName;

    private String type;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "id_district", nullable=false)
    private District district;
}
