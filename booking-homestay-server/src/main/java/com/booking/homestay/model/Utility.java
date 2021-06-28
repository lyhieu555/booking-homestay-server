package com.booking.homestay.model;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.Set;

import static javax.persistence.FetchType.LAZY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Utility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String utilityName;

    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_TypeUtility", nullable=false)
    private TypeUtility typeUtility;

}
