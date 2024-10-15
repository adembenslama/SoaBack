package com.adem.ordi.repos;

import com.adem.ordi.entities.Marque;
import com.adem.ordi.entities.Pc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
public interface PcRepository extends JpaRepository<Pc, Long> {
    List<Pc> findByNomPc(String nom);
    List<Pc> findByNomPcContains(String nom);

    @Query("select c from Pc c where c.nomPc like %:nom and c.prixPc > :prix")
    List<Pc> findByNomPrix(@Param("nom") String nom, @Param("prix") Double prix);

    @Query("select c from Pc c where c.marquePc = ?1")
    List<Pc> findByMarquePc(Marque marque);

    // Updated method
    @Query("select c from Pc c where c.marquePc.idMarque = ?1") // Use idMarque here
    List<Pc> findByMarqueIdMar(Long id);


    List<Pc> findByOrderByNomPcAsc();

    @Query("select c from Pc c order by c.nomPc ASC, c.prixPc DESC")
    List<Pc> trierPcsNomsPrix();
    List<Pc> findByMarquePcIdMarque(Long idMarque);

}
