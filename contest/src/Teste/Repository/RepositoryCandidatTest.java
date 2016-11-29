package Teste.Repository;

import Domain.Candidat;
import Repository.RepositoryCandidat;
import Validator.RepositoryException;
import Validator.ValidatorCandidat;
import Validator.ValidatorException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Sebi on 10/17/2016.
 */
public class RepositoryCandidatTest {
    RepositoryCandidat repository;
    ValidatorCandidat validatorCandidat;

    @Before
    public void setUp() throws Exception {
        validatorCandidat = new ValidatorCandidat();
        repository = new RepositoryCandidat(validatorCandidat);
    }

    @After
    public void tearDown() throws Exception {
        repository = null;
    }

    @Test
    public void size() throws Exception {
        assertTrue(repository.size() == 0);
        Candidat c = new Candidat(1, "Mircea", "0756456789", "Rozelor 20");
        repository.save(c);
        assertTrue(repository.size() == 1);
    }

    @Test
    public void save() throws Exception {
        Candidat c = new Candidat(1, "Mircea", "0756456789", "Rozelor 20");
        repository.save(c);
        assertTrue(repository.size() == 1);
        Candidat c2 = repository.getById(1);
        assertTrue(c2.getId() == 1);
        assertTrue(c2.getNume().equals("Mircea"));
        assertTrue(c2.getAdresa().equals("Rozelor 20"));
        assertTrue(c2.getTelefon().equals("0756456789"));
    }

    @Test
    public void delete() throws Exception {
        Candidat c = new Candidat(1, "Mircea", "0756456789", "Rozelor 20");
        repository.save(c);
        assertTrue(repository.size() == 1);
        repository.delete(1);
        assertTrue(repository.size() == 0);
    }

    @Test
    public void getById() throws Exception {
        Candidat c = new Candidat(1, "Mircea", "0756456789", "Rozelor 20");
        Candidat c1 = new Candidat(2, "Paul", "0756456789", "Frunzelor 3");
        Candidat c2 = new Candidat(3, "Gabriel", "0756456789", "Labradorilor 12");
        repository.save(c); repository.save(c1); repository.save(c2);

        Candidat c4 = repository.getById(2);
        assertTrue(c4.getId() == 2);
        assertTrue(c4.getNume().equals("Paul"));
    }

    @Test
    public void getAll() throws Exception {
        Candidat c = new Candidat(1, "Mircea", "0756456789", "Rozelor 20");
        Candidat c1 = new Candidat(2, "Paul", "0756456789", "Frunzelor 3");
        Candidat c2 = new Candidat(3, "Gabriel", "0756456789", "Labradorilor 12");
        repository.save(c); repository.save(c1); repository.save(c2);

        Iterable<Candidat> it = repository.getAll();
        Integer nrElems = 0;
        for (Candidat candidat : it){
            nrElems++;
        }
        assertTrue(nrElems == 3);
    }

    @Test
    public void addDuplicateId() throws Exception {
        Candidat c = new Candidat(1, "Mircea", "0756456789", "Rozelor 20");
        Candidat c1 = new Candidat(2, "Paul", "0756456789", "Frunzelor 3");
        Candidat c2 = new Candidat(1, "Gabriel", "0756456789", "Labradorilor 12");
        repository.save(c); repository.save(c1);

        try {
            repository.save(c2);
        } catch (RepositoryException exception){
            assertTrue(exception.getMessage().equals("Acest ID apare deja!"));
        }
    }

    @Test
    public void removeInvalidId() throws Exception {
        try {
            repository.delete(1);
        } catch (RepositoryException exception){
            assertTrue(exception.getMessage().equals("Acest ID nu apare in repository!"));
        }

    }

    @Test
    public void testValidUpdate() throws Exception {
        Candidat c = new Candidat(1, "Mircea", "0756456789", "Rozelor 20");
        Candidat c1 = new Candidat(2, "Paul", "0756456789", "Frunzelor 3");
        Candidat c2 = new Candidat(1, "Gabriel", "0756456789", "Labradorilor 12");
        repository.save(c); repository.save(c1);

        repository.update(1, c2);
        Candidat candidat = repository.getById(1);
        assertTrue(candidat.getNume().equals("Gabriel"));

        Candidat c3 = new Candidat(3, "Mihai", "0123345345", "Rozelor 14");
        repository.update(1, c3);
        candidat = repository.getById(3);
        assertTrue(candidat.getNume().equals("Mihai"));
    }

    @Test
    public void testInvalidInitialIdUpdate() throws Exception {
        Candidat c = new Candidat(1, "Mircea", "0756456789", "Rozelor 20");
        Candidat c1 = new Candidat(2, "Paul", "0756456789", "Frunzelor 3");
        Candidat c2 = new Candidat(1, "Gabriel", "0756456789", "Labradorilor 12");
        repository.save(c); repository.save(c1);

        try {
            repository.update(4, c2);
            assertTrue(false);
        } catch (RepositoryException exc){
            assertTrue(true);
        }
    }

    @Test
    public void testInvalidNewIdUpdate() throws Exception {
        Candidat c = new Candidat(1, "Mircea", "0756456789", "Rozelor 20");
        Candidat c1 = new Candidat(2, "Paul", "0756456789", "Frunzelor 3");
        Candidat c2 = new Candidat(2, "Gabriel", "0756456789", "Labradorilor 12");
        repository.save(c); repository.save(c1);

        try {
            repository.update(1, c2);
            assertTrue(false);
        } catch (RepositoryException exc){
            assertTrue(true);
        }
    }

    @Test
    public void testInvalidNewEntityUpdate() throws Exception {
        Candidat c = new Candidat(1, "Mircea", "0756456789", "Rozelor 20");
        Candidat c1 = new Candidat(2, "Paul", "0756456789", "Frunzelor 3");
        Candidat c2 = new Candidat(1, "", "075645678", "Labradorilor 12");
        repository.save(c); repository.save(c1);

        try {
            repository.update(1, c2);
            assertTrue(false);
        } catch (ValidatorException exc){
            assertTrue(true);
        }

        assertTrue(repository.size() == 2);
    }

}