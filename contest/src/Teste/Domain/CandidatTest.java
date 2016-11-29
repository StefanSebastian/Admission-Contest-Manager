package Teste.Domain;

import Domain.Candidat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Sebi on 10/17/2016.
 */
public class CandidatTest {
    private Candidat candidat;

    @Before
    public void setUp() throws Exception {
        candidat = new Candidat(1, "Mircea", "0756456789", "Rozelor 20");
    }

    @After
    public void tearDown() throws Exception {
        candidat = null;
    }

    @Test
    public void getId() throws Exception {
        int id = candidat.getId();
        assertEquals(1, id);
    }

    @Test
    public void getNume() throws Exception {
        assertEquals("Mircea", candidat.getNume());
    }

    @Test
    public void getTelefon() throws Exception {
        assertEquals("0756456789", candidat.getTelefon());
    }

    @Test
    public void getAdresa() throws Exception {
        assertEquals("Rozelor 20", candidat.getAdresa());
    }

    @Test
    public void setId() throws Exception {
        candidat.setId(12);
        int id = candidat.getId();
        assertEquals(12, id);
    }

    @Test
    public void setNume() throws Exception {
        candidat.setNume("Andrei");
        assertEquals("Andrei", candidat.getNume());
    }

    @Test
    public void setTelefon() throws Exception {
        candidat.setTelefon("0742111222");
        assertEquals("0742111222", candidat.getTelefon());
    }

    @Test
    public void setAdresa() throws Exception {
        candidat.setAdresa("Vulturilor 22");
        assertEquals("Vulturilor 22", candidat.getAdresa());
    }

}