package com.esprit.exam.Repository;

import com.esprit.exam.Entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    Utilisateur findByNomAndPrenom(String nom, String prenom);

    List<Utilisateur> findByDateFinAbonnementBetween(LocalDate debut, LocalDate fin);

}
