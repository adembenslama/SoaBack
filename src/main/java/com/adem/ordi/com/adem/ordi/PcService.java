package com.adem.ordi.com.adem.ordi;

import com.adem.ordi.entities.Pc;
import com.adem.ordi.repos.PcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PcService {

    @Autowired
    private PcRepository pcRepository;

    public List<Pc> getAllPcs() {
        return pcRepository.findAll();
    }

    public Pc getPcById(Long id) {
        return pcRepository.findById(id).orElseThrow(() -> new RuntimeException("PC not found"));
    }

    public Pc createPc(Pc pc) {
        return pcRepository.save(pc);
    }

    public Pc updatePc(Long id, Pc updatedPc) {
        Pc pc = getPcById(id);
        pc.setNomPc(updatedPc.getNomPc());
        pc.setPrixPc(updatedPc.getPrixPc());
        pc.setDescriptionPc(updatedPc.getDescriptionPc());
        pc.setDateCreation(updatedPc.getDateCreation());
        pc.setMarquePc(updatedPc.getMarquePc());
        return pcRepository.save(pc);
    }

    public void deletePc(Long id) {
        pcRepository.deleteById(id);
    }
}
