package com.esprit.exam;

import com.esprit.exam.Entities.Bibliotheque;
import com.esprit.exam.Entities.Etat;
import com.esprit.exam.Entities.Livre;
import com.esprit.exam.Entities.Utilisateur;
import com.esprit.exam.Services.BibliothequeService;
import com.esprit.exam.Services.LivreService;
import com.esprit.exam.Services.UtilisateurService;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
public class RestControllerExam {

    private BibliothequeService bibliothequeService;
    private LivreService livreService;
    private UtilisateurService utilisateurService;

    public RestControllerExam(BibliothequeService bibliothequeService, LivreService livreService, UtilisateurService utilisateurService) {
        this.bibliothequeService = bibliothequeService;
        this.livreService = livreService;
        this.utilisateurService = utilisateurService;
    }

    @PostMapping("/ajouterLivreAuteurBibliotheque")
    public Bibliotheque ajouterLivreEtAuteurEtAffecterABiblio(@RequestBody Livre livre, @RequestParam String nomBibliotheque) {
        Bibliotheque bibliotheque = bibliothequeService.findByNom(nomBibliotheque);
        livre.setBibliotheque(bibliotheque);
        livreService.save(livre);
        bibliotheque.getLivres().add(livre);
        return bibliothequeService.save(bibliotheque);
    }
    @PostMapping("/ajouterBibliotheque")
    public Bibliotheque ajouterBibliotheque(@RequestBody Bibliotheque bibliotheque) {
        if(bibliotheque.getNom() == null || bibliotheque.getAdresse() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Données invalides");
        }

        bibliothequeService.save(bibliotheque);
        return bibliotheque;

    }


    @PutMapping("/affecterLivreALecteur")
    public String affecterLivreALecteur(@RequestParam long idLivre, @RequestParam long idLecteur) {
        Livre livre = livreService.findById(idLivre);
        Utilisateur lecteur = utilisateurService.findById(idLecteur);

        if (lecteur.getLivreReserve() != null) {
            return "Le livre " + lecteur.getLivreReserve().getNom() + " est déjà réservé !";
        }

        lecteur.setLivreReserve(livre);
        livre.setReserve(true);
        utilisateurService.save(lecteur);
        livreService.save(livre);

        return "L'affectation du livre " + livre.getNom() + " au lecteur " + lecteur.getNom() + " " + lecteur.getPrenom() + " est effectuée avec succès !";
    }

    @PostMapping("/ajouterUtilisateur")
    public Utilisateur ajouterUtilisateur(@RequestBody Utilisateur utilisateur) {

        if(utilisateur.getNom() == null || utilisateur.getPrenom() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Données invalides");
        }

        utilisateurService.save(utilisateur);
        return utilisateur;
    }

    @PutMapping("/modifierDateReservation")
    public void modifierDateReservation(@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date, @RequestParam long idLecteur) {
        Utilisateur lecteur = utilisateurService.findById(idLecteur);
        lecteur.getLivreReserve().setDateReservation(date);
        livreService.save(lecteur.getLivreReserve());
    }

    @PutMapping("/rendreLivre")
    public String rendreLivre(@RequestParam String nom, @RequestParam String prenom) {
        Utilisateur lecteur = utilisateurService.findByNomAndPrenom(nom, prenom);
        Livre livre = lecteur.getLivreReserve();


            lecteur.setEtat(Etat.BLOQUE);
            livre.setReserve(false);
            livre.setDateReservation(null);
            // mises à jour
            return "Le lecteur " + lecteur.getNom() + " " + lecteur.getPrenom() + " est bloqué ! La désaffectation est effectuée avec succès";

    }

    @GetMapping
    public List<Bibliotheque> getAllBibliotheques() {
        return bibliothequeService.findAll();
    }

    @GetMapping("/{id}")
    public Bibliotheque getBibliotheque(@PathVariable Long id) {
        return bibliothequeService.findById(id);
    }

    @GetMapping("/utilisateurs")
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurService.findAll();
    }

    @GetMapping("/utilisateurs/{id}")
    public Utilisateur getUtilisateur(@PathVariable Long id) {
        return utilisateurService.findById(id);
    }

    @GetMapping("/livres")
    public List<Livre> getAllLivres() {
        return livreService.findAll();
    }

    @GetMapping("/livres/{id}")
    public Livre getLivre(@PathVariable Long id) {
        return livreService.findById(id);
    }




}