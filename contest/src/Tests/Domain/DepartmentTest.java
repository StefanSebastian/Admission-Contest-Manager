package Tests.Domain;

import Domain.Department;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Sebi on 10/17/2016.
 */
public class DepartmentTest {
    Department s;

    @Before
    public void setUp() throws Exception {
        s = new Department(1, "Informatica", 200);
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
        assertEquals("Informatica", s.getName());
    }

    @Test
    public void getNrLocuri() throws Exception {
        int nr = s.getNumberOfPlaces();
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
        s.setName("Matematica");
        assertEquals("Matematica", s.getName());
    }

    @Test
    public void setNrLocuri() throws Exception {
        s.setNumberOfPlaces(124);
        int nr = s.getNumberOfPlaces();
        assertEquals(124, nr);
    }

}