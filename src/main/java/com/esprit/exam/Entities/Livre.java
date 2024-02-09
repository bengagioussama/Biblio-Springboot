package com.esprit.exam.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
@Table(name="Livre")
public class Livre {
    @Id
    @Column(name = "idLivre")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long idLivre;
    private String nom;
    @Enumerated(EnumType.STRING)
    private TypeLivre type;
    private LocalDate dateEmission;
    private boolean reserve;
    private LocalDate dateReservation;

    @ManyToOne
    private Bibliotheque bibliotheque;

    @OneToOne
    private Utilisateur lecteur;

    @ManyToOne
    private Utilisateur auteur;



}
