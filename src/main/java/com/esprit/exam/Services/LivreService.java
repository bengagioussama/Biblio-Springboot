package com.esprit.exam.Services;

import com.esprit.exam.Entities.Livre;
import com.esprit.exam.Repository.LivreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class LivreService {
    @Autowired
    private LivreRepository livreRepository;

    public Livre save(Livre livre) {
        return livreRepository.save(livre);
    }

    public Livre findById(Long id) {
        return livreRepository.findById(id).orElse(null);
    }
    public List<Livre> findAll() {
        return livreRepository.findAll();
    }
}
