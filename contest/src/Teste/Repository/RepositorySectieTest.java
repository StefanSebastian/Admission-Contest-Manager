package Teste.Repository;

import Domain.Sectie;
import Repository.RepositorySectie;
import Validator.RepositoryException;
import Validator.ValidatorSectie;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Sebi on 10/18/2016.
 */
public class RepositorySectieTest {
    RepositorySectie repository;
    ValidatorSectie validatorSectie;

    @Before
    public void setUp() throws Exception {
        validatorSectie = new ValidatorSectie();
        repository = new RepositorySectie(validatorSectie);
    }

    @After
    public void tearDown() throws Exception {
        repository = null;
    }

    @Test
    public void size() throws Exception {
        assertTrue(repository.size() == 0);
        Sectie s = new Sectie(1, "Informatica", 200);
        repository.save(s);
        assertTrue(repository.size() == 1);
    }

    @Test
    public void save() throws Exception {
        Sectie s = new Sectie(1, "Informatica", 200);
        repository.save(s);
        assertTrue(repository.size() == 1);
        Sectie s2 = repository.getById(1);
        assertTrue(s2.getId() == 1);
        assertTrue(s2.getNume().equals("Informatica"));
        assertTrue(s2.getNrLocuri() == 200);
    }

    @Test
    public void delete() throws Exception {
        Sectie s = new Sectie(1, "Informatica", 200);
        repository.save(s);
        assertTrue(repository.size() == 1);
        repository.delete(1);
        assertTrue(repository.size() == 0);
    }

    @Test
    public void getById() throws Exception {
        Sectie s = new Sectie(1, "Informatica", 200);
        Sectie s1 = new Sectie(2, "Istorie", 150);
        Sectie s2 = new Sectie(3, "Geografie", 126);
        repository.save(s); repository.save(s1); repository.save(s2);

        Sectie s4 = repository.getById(2);
        assertTrue(s4.getId() == 2);
        assertTrue(s4.getNume().equals("Istorie"));
    }

    @Test
    public void getAll() throws Exception {
        Sectie s = new Sectie(1, "Informatica", 200);
        Sectie s1 = new Sectie(2, "Istorie", 150);
        Sectie s2 = new Sectie(3, "Geografie", 126);
        repository.save(s); repository.save(s1); repository.save(s2);

        Iterable<Sectie> it = repository.getAll();
        Integer nrElems = 0;
        for (Sectie sectie : it){
            nrElems++;
        }
        assertTrue(nrElems == 3);
    }

    @Test
    public void addDuplicateId() throws Exception {
        Sectie s = new Sectie(1, "Informatica", 200);
        Sectie s1 = new Sectie(2, "Istorie", 150);
        Sectie s2 = new Sectie(1, "Geografie", 126);
        repository.save(s); repository.save(s1);

        try {
            repository.save(s2);
        } catch (RepositoryException exception){
            assertTrue(exception.getMessage().equals("Acest ID apare deja!"));
        }
    }

    @Test
    public void removeInvalidId() throws Exception {
        try {
            repository.delete(1);
            assertTrue(false);
        }
        catch (RepositoryException exception){
            assertTrue(exception.getMessage().equals("Acest ID nu apare in repository!"));
        }
    }

    @Test
    public void testValidUpdate() throws Exception {
        Sectie s = new Sectie(1, "Informatica", 200);
        Sectie s1 = new Sectie(2, "Istorie", 150);
        Sectie s2 = new Sectie(1, "Geografie", 126);
        repository.save(s); repository.save(s1);

        repository.update(1, s2);
        Sectie sectie = repository.getById(1);
        assertTrue(sectie.getNume().equals("Geografie"));

        Sectie s3 = new Sectie(4, "Fizica", 126);
        repository.update(1, s3);
        sectie = repository.getById(4);
        assertTrue(sectie.getNume().equals("Fizica"));

    }

    @Test
    public void testInvalidUpdate() throws Exception {
        Sectie s = new Sectie(1, "Informatica", 200);
        Sectie s1 = new Sectie(2, "Istorie", 150);
        Sectie s2 = new Sectie(1, "Geografie", 126);
        repository.save(s); repository.save(s1);

        try {
            repository.update(2, s2);
            assertTrue(false);
        } catch (RepositoryException exc){
            assertTrue(true);
        }
    }

}