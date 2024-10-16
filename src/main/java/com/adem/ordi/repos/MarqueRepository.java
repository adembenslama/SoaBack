package com.adem.ordi.repos;

import com.adem.ordi.entities.Marque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
@CrossOrigin("http://localhost:4200/")
public interface MarqueRepository extends JpaRepository<Marque, Long> {

}