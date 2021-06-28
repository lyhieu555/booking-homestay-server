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
public class Post {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String title;

    @Lob
    private String description;

    private Instant createDate;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "id_typePost", nullable=false)
    private TypePost typePost;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "id_user", nullable=false)
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "id_homestay")
    private HomeStay homeStay;

}
