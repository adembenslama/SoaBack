package com.adem.ordi;

import com.adem.ordi.com.adem.ordi.PcService;
import com.adem.ordi.entities.Pc;
import com.adem.ordi.repos.PcRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

public class PcServiceTest {

    @Mock
    private PcRepository pcRepository;

    @InjectMocks
    private PcService pcService;

    private Pc pc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        pc = new Pc();
        pc.setIdPc(1L);
        pc.setNomPc("Test PC");
        pc.setPrixPc(1000.0);
        pc.setDescriptionPc("Test Description");
    }

    @Test
    public void testGetPcById_Success() {
        when(pcRepository.findById(1L)).thenReturn(Optional.of(pc));

        Pc foundPc = pcService.getPcById(1L);

        assertNotNull(foundPc);
        assertEquals(1L, foundPc.getIdPc());
        assertEquals("Test PC", foundPc.getNomPc());
        verify(pcRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreatePc_Success() {
        when(pcRepository.save(any(Pc.class))).thenReturn(pc);

        Pc createdPc = pcService.createPc(pc);

        assertNotNull(createdPc);
        assertEquals("Test PC", createdPc.getNomPc());
        verify(pcRepository, times(1)).save(pc);
    }

    @Test
    public void testUpdatePc_Success() {
        Pc updatedPc = new Pc();
        updatedPc.setNomPc("Updated PC");
        updatedPc.setPrixPc(1500.0);

        when(pcRepository.findById(1L)).thenReturn(Optional.of(pc));
        when(pcRepository.save(any(Pc.class))).thenReturn(updatedPc);

        Pc result = pcService.updatePc(1L, updatedPc);

        assertEquals("Updated PC", result.getNomPc());
        assertEquals(1500.0, result.getPrixPc());
        verify(pcRepository, times(1)).findById(1L);
        verify(pcRepository, times(1)).save(pc);
    }

    @Test
    public void testDeletePc_Success() {
        doNothing().when(pcRepository).deleteById(1L);

        pcService.deletePc(1L);

        verify(pcRepository, times(1)).deleteById(1L);
    }
}
