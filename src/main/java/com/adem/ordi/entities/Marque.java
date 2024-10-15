package com.adem.ordi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Marque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMarque;

    private String nomMarque;
    private String descriptionMarque;
    @JsonIgnore
    @OneToMany(mappedBy = "marquePc", cascade = CascadeType.ALL)
    private List<Pc> pcs;

    // Getters and Setters
    public Long getIdMarque() {
        return idMarque;
    }

    public void setIdMarque(Long idMarque) {
        this.idMarque = idMarque;
    }

    public String getNomMarque() {
        return nomMarque;
    }

    public void setNomMarque(String nomMarque) {
        this.nomMarque = nomMarque;
    }

    public String getDescriptionMarque() {
        return descriptionMarque;
    }

    public void setDescriptionMarque(String descriptionMarque) {
        this.descriptionMarque = descriptionMarque;
    }

    public List<Pc> getPcs() {
        return pcs;
    }

    public void setPcs(List<Pc> pcs) {
        this.pcs = pcs;
    }
}
