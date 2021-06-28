package com.booking.homestay.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String houseName;

    @Column(nullable=false)
    private Integer amountRoom;

    @Column(nullable=false)
    private Integer price;

    @Column(nullable=false)
    private Integer size;

    @Column(nullable=false)
    private Integer capacity;

    @Lob
    private  String image;

    @Lob
    private String description;

    private boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_homestay", nullable=false)
    private HomeStay homeStay;


}
