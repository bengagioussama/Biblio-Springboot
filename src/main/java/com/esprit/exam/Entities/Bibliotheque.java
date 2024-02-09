package com.esprit.exam.Entities;

import jakarta.persistence.*;
import lombok.*;

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
@Table(name = "bibliotheques")
public class Bibliotheque {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idBiblio;
    private String nom;
    private String adresse;
    private long telephone;

    @OneToMany(mappedBy = "bibliotheque")
    private List<Livre> livres;

}
