package Tests.Domain;

import Domain.Option;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Sebi on 30-Nov-16.
 */
public class OptionTest {
    Option option;

    @Before
    public void setUp() throws Exception {
        option = new Option(1, 1, 1);
    }

    @After
    public void tearDown() throws Exception {
        option = null;
    }

    @Test
    public void getId() throws Exception {
        Integer id = option.getId();
        assertTrue(id.equals(1));
    }

    @Test
    public void setId() throws Exception {
        option.setId(2);
        Integer id = option.getId();
        assertTrue(id.equals(2));
    }

    @Test
    public void getIdCandidate() throws Exception {
        Integer id = option.getIdCandidate();
        assertTrue(id.equals(1));
    }

    @Test
    public void setIdCandidate() throws Exception {
        option.setIdCandidate(3);
        Integer id = option.getIdCandidate();
        assertTrue(id.equals(3));
    }

    @Test
    public void getIdDepartment() throws Exception {
        Integer id = option.getIdDepartment();
        assertTrue(id.equals(1));
    }

    @Test
    public void setIdDepartment() throws Exception {
        option.setIdDepartment(4);
        Integer id = option.getIdDepartment();
        assertTrue(id.equals(4));
    }

}