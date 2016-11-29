package Teste.Domain;

import Domain.Sectie;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Sebi on 10/17/2016.
 */
public class SectieTest {
    Sectie s;

    @Before
    public void setUp() throws Exception {
        s = new Sectie(1, "Informatica", 200);
    }

    @After
    public void tearDown() throws Exception {
        s = null;
    }

    @Test
    public void getId() throws Exception {
        int id = s.getId();
        assertEquals(1, id);
    }

    @Test
    public void getNume() throws Exception {
        assertEquals("Informatica", s.getNume());
    }

    @Test
    public void getNrLocuri() throws Exception {
        int nr = s.getNrLocuri();
        assertEquals(200, nr);
    }

    @Test
    public void setId() throws Exception {
        s.setId(33);
        int id = s.getId();
        assertEquals(33, id);
    }

    @Test
    public void setNume() throws Exception {
        s.setNume("Matematica");
        assertEquals("Matematica", s.getNume());
    }

    @Test
    public void setNrLocuri() throws Exception {
        s.setNrLocuri(124);
        int nr = s.getNrLocuri();
        assertEquals(124, nr);
    }

}