package com.esprit.exam.Services;

import com.esprit.exam.Entities.Utilisateur;
import com.esprit.exam.Repository.UtilisateurRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public Utilisateur save(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    public List<Utilisateur> saveAll(List<Utilisateur> utilisateurs) {
        return utilisateurRepository.saveAll(utilisateurs);
    }

    public Utilisateur findById(Long id) {
        return utilisateurRepository.findById(id).orElse(null);
    }

    public Utilisateur findByNomAndPrenom(String nom, String prenom) {
        return utilisateurRepository.findByNomAndPrenom(nom, prenom);
    }

    public List<Utilisateur> findByDateFinAbonnementBetween(LocalDate debut, LocalDate fin) {
        return utilisateurRepository.findByDateFinAbonnementBetween(debut, fin);
    }
    public List<Utilisateur> findAll() {
        return utilisateurRepository.findAll();
    }

}
