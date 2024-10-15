package com.adem.ordi.Controllers;

import com.adem.ordi.com.adem.ordi.MarqueService;
import com.adem.ordi.entities.Marque;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/marques")
public class MarqueController {

    @Autowired
    private MarqueService marqueService;

    @GetMapping
    public List<Marque> getAllMarques() {
        return marqueService.getAllMarques();
    }

    @GetMapping("/{id}")
    public Marque getMarqueById(@PathVariable Long id) {
        return marqueService.getMarqueById(id);
    }

    @PostMapping
    public Marque createMarque(@RequestBody Marque marque) {
        return marqueService.createMarque(marque);
    }

    @PutMapping("/{id}")
    public Marque updateMarque(@PathVariable Long id, @RequestBody Marque updatedMarque) {
        return marqueService.updateMarque(id, updatedMarque);
    }

    @DeleteMapping("/{id}")
    public void deleteMarque(@PathVariable Long id) {
        marqueService.deleteMarque(id);
    }
}
