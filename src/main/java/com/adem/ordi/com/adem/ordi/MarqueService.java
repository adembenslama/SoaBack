package com.adem.ordi.com.adem.ordi;

import com.adem.ordi.entities.Marque;
import com.adem.ordi.repos.MarqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MarqueService {

    @Autowired
    private MarqueRepository marqueRepository;

    public List<Marque> getAllMarques() {
        return marqueRepository.findAll();
    }

    public Marque getMarqueById(Long id) {
        return marqueRepository.findById(id).orElseThrow(() -> new RuntimeException("Marque not found"));
    }

    public Marque createMarque(Marque marque) {
        return marqueRepository.save(marque);
    }

    public Marque updateMarque(Long id, Marque updatedMarque) {
        Marque marque = getMarqueById(id);
        marque.setNomMarque(updatedMarque.getNomMarque());
        marque.setDescriptionMarque(updatedMarque.getDescriptionMarque());
        return marqueRepository.save(marque);
    }

    public void deleteMarque(Long id) {
        marqueRepository.deleteById(id);
    }
}
