package com.adem.ordi.Controllers;

import java.text.ParseException;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.adem.ordi.entities.Marque;
import com.adem.ordi.entities.Pc;
import com.adem.ordi.services.PcService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import com.adem.ordi.entities.Pc;
import com.adem.ordi.services.PcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/pcs")
public class PcController {

    @Autowired
    private PcService pcService;

    @GetMapping
    public ResponseEntity<List<Pc>> getAllPcs(@RequestParam (name="page", defaultValue = "0") int page,
                                              @RequestParam (name="size", defaultValue = "3") int size) {
        List<Pc> pcs = pcService.getAllPcParPage(page, size).getContent();
        return new ResponseEntity<>(pcs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pc> getPcById(@PathVariable Long id) {
        Pc pc = pcService.getPc(id);
        if (pc == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 if not found
        }
        return new ResponseEntity<>(pc, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createPc(@Valid @RequestBody Pc pc, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // Print or log validation errors for debugging
            return ResponseEntity.badRequest().body(bindingResult.getFieldErrors());
        }
        Pc createdPc = pcService.savePc(pc);
        return new ResponseEntity<>(createdPc, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Pc> updatePc(@PathVariable Long id, @Valid @RequestBody Pc updatedPc, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Return 400 if there are validation errors
        }
        updatedPc.setIdPc(id); // Ensure the ID is set for the update
        Pc existingPc = pcService.updatePc(updatedPc);
        if (existingPc == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 if not found
        }
        return new ResponseEntity<>(existingPc, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePc(@PathVariable Long id) {
        try {
            pcService.deletePcById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Return 204 for successful deletion
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 if not found
        }
    }
}