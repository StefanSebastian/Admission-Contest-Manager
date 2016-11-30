package Tests.Domain;

import Domain.Candidate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Sebi on 10/17/2016.
 */
public class CandidateTest {
    private Candidate candidate;

    @Before
    public void setUp() throws Exception {
        candidate = new Candidate(1, "Mircea", "0756456789", "Rozelor 20");
    }

    @After
    public void tearDown() throws Exception {
        candidate = null;
    }

    @Test
    public void getId() throws Exception {
        int id = candidate.getId();
        assertEquals(1, id);
    }

    @Test
    public void getNume() throws Exception {
        assertEquals("Mircea", candidate.getName());
    }

    @Test
    public void getTelefon() throws Exception {
        assertEquals("0756456789", candidate.getTelephone());
    }

    @Test
    public void getAdresa() throws Exception {
        assertEquals("Rozelor 20", candidate.getAddress());
    }

    @Test
    public void setId() throws Exception {
        candidate.setId(12);
        int id = candidate.getId();
        assertEquals(12, id);
    }

    @Test
    public void setNume() throws Exception {
        candidate.setName("Andrei");
        assertEquals("Andrei", candidate.getName());
    }

    @Test
    public void setTelefon() throws Exception {
        candidate.setTelephone("0742111222");
        assertEquals("0742111222", candidate.getTelephone());
    }

    @Test
    public void setAdresa() throws Exception {
        candidate.setAddress("Vulturilor 22");
        assertEquals("Vulturilor 22", candidate.getAddress());
    }

}