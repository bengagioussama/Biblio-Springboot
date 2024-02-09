package com.esprit.exam.Repository;

import com.esprit.exam.Entities.Bibliotheque;
import com.esprit.exam.Entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BibliothequeRepository extends JpaRepository<Bibliotheque, Long> {

    Bibliotheque findByNom(String nom);
    List<Bibliotheque> findAll();
    Bibliotheque findById(long id);

}
