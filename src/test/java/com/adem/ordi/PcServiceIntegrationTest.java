package com.adem.ordi;

import com.adem.ordi.com.adem.ordi.MarqueService;
import com.adem.ordi.com.adem.ordi.PcService;
import com.adem.ordi.entities.Marque;
import com.adem.ordi.entities.Pc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import jakarta.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")  // This tells Spring Boot to use application-test.properties
@Transactional  // Rollback the changes after each test to keep the database clean
public class PcServiceIntegrationTest {

    @Autowired
    private PcService pcService;

    @Autowired
    private MarqueService marqueService;

    private Marque marque;
    private Pc pc;

    @BeforeEach
    public void setUp() {
        marque = new Marque();
        marque.setNomMarque("Test Marque");
        marque.setDescriptionMarque("This is a test description");
        marque = marqueService.createMarque(marque);

        pc = new Pc();
        pc.setNomPc("Test PC");
        pc.setPrixPc(1000.0);
        pc.setDescriptionPc("Test PC description");
        pc.setMarquePc(marque);
        pc.setDateCreation(new java.util.Date());
    }

    @Test
    public void testCreatePc() {
        Pc createdPc = pcService.createPc(pc);

        assertNotNull(createdPc);
        assertEquals("Test PC", createdPc.getNomPc());
        assertEquals(marque.getIdMarque(), createdPc.getMarquePc().getIdMarque());
    }

    @Test
    public void testFindAllPcs() {
        pcService.createPc(pc);

        List<Pc> pcs = pcService.getAllPcs();

        assertFalse(pcs.isEmpty());
        assertEquals("Test PC", pcs.get(0).getNomPc());
    }

    @Test
    public void testUpdatePc() {
        Pc createdPc = pcService.createPc(pc);
        createdPc.setNomPc("Updated PC");

        Pc updatedPc = pcService.updatePc(createdPc.getIdPc(), createdPc);

        assertEquals("Updated PC", updatedPc.getNomPc());
    }


}
