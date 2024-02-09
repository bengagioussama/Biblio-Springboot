package com.esprit.exam.Services;

import com.esprit.exam.Entities.Bibliotheque;
import com.esprit.exam.Repository.BibliothequeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class BibliothequeService {

    @Autowired
    private BibliothequeRepository bibliothequeRepository;

    public Bibliotheque save(Bibliotheque bibliotheque) {
        return bibliothequeRepository.save(bibliotheque);
    }

    public Bibliotheque findByNom(String nom) {
        return bibliothequeRepository.findByNom(nom);
    }
    public List<Bibliotheque>  findAll() {
        return bibliothequeRepository.findAll();
    }
    public Bibliotheque findById(long id){
        return bibliothequeRepository.findById(id);
    }
}
