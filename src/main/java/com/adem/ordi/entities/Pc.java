package com.adem.ordi.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
public class Pc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPc;
    @NotNull
    @Size(min = 4,max = 15)
    private String nomPc;
    @Min(value = 10)
    @Max(value = 10000)
    private Double prixPc;
    private String descriptionPc;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent
    private Date dateCreation;

    @ManyToOne
    @JoinColumn(name = "marque_id")
    private Marque marquePc;

    // Getters and Setters
    public Long getIdPc() {
        return idPc;
    }

    public void setIdPc(Long idPc) {
        this.idPc = idPc;
    }

    public String getNomPc() {
        return nomPc;
    }

    public void setNomPc(String nomPc) {
        this.nomPc = nomPc;
    }

    public Double getPrixPc() {
        return prixPc;
    }

    public void setPrixPc(Double prixPc) {
        this.prixPc = prixPc;
    }

    public String getDescriptionPc() {
        return descriptionPc;
    }

    public void setDescriptionPc(String descriptionPc) {
        this.descriptionPc = descriptionPc;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Marque getMarquePc() {
        return marquePc;
    }

    public void setMarquePc(Marque marquePc) {
        this.marquePc = marquePc;
    }
}

