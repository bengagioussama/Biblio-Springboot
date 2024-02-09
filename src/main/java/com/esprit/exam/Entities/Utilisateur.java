package com.esprit.exam.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
@Table(name = "utilisateurs")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idUser;
    private String nom;
    private String prenom;
    private String nationalite;
    @Enumerated(EnumType.STRING)
    private Role role;
    private LocalDate dateDebutAbonnement;
    private LocalDate dateFinAbonnement;
    @Enumerated(EnumType.STRING)
    private Etat etat;


    @OneToOne(mappedBy = "lecteur")
    private Livre livreReserve;

    @OneToMany(mappedBy = "auteur")
    private List<Livre> livresEcrits;





}
