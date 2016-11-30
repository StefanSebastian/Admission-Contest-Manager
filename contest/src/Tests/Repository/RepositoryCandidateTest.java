package Tests.Repository;

import Domain.Candidate;
import Repository.RepositoryCandidate;
import Validator.RepositoryException;
import Validator.ValidatorCandidate;
import Validator.ValidatorException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Sebi on 10/17/2016.
 */
public class RepositoryCandidateTest {
    RepositoryCandidate repository;
    ValidatorCandidate validatorCandidate;

    @Before
    public void setUp() throws Exception {
        validatorCandidate = new ValidatorCandidate();
        repository = new RepositoryCandidate(validatorCandidate);
    }

    @After
    public void tearDown() throws Exception {
        repository = null;
    }

    @Test
    public void size() throws Exception {
        assertTrue(repository.size() == 0);
        Candidate c = new Candidate(1, "Mircea", "0756456789", "Rozelor 20");
        repository.save(c);
        assertTrue(repository.size() == 1);
    }

    @Test
    public void save() throws Exception {
        Candidate c = new Candidate(1, "Mircea", "0756456789", "Rozelor 20");
        repository.save(c);
        assertTrue(repository.size() == 1);
        Candidate c2 = repository.getById(1);
        assertTrue(c2.getId() == 1);
        assertTrue(c2.getName().equals("Mircea"));
        assertTrue(c2.getAddress().equals("Rozelor 20"));
        assertTrue(c2.getTelephone().equals("0756456789"));
    }

    @Test
    public void delete() throws Exception {
        Candidate c = new Candidate(1, "Mircea", "0756456789", "Rozelor 20");
        repository.save(c);
        assertTrue(repository.size() == 1);
        repository.delete(1);
        assertTrue(repository.size() == 0);
    }

    @Test
    public void getById() throws Exception {
        Candidate c = new Candidate(1, "Mircea", "0756456789", "Rozelor 20");
        Candidate c1 = new Candidate(2, "Paul", "0756456789", "Frunzelor 3");
        Candidate c2 = new Candidate(3, "Gabriel", "0756456789", "Labradorilor 12");
        repository.save(c); repository.save(c1); repository.save(c2);

        Candidate c4 = repository.getById(2);
        assertTrue(c4.getId() == 2);
        assertTrue(c4.getName().equals("Paul"));
    }

    @Test
    public void getAll() throws Exception {
        Candidate c = new Candidate(1, "Mircea", "0756456789", "Rozelor 20");
        Candidate c1 = new Candidate(2, "Paul", "0756456789", "Frunzelor 3");
        Candidate c2 = new Candidate(3, "Gabriel", "0756456789", "Labradorilor 12");
        repository.save(c); repository.save(c1); repository.save(c2);

        Iterable<Candidate> it = repository.getAll();
        Integer nrElems = 0;
        for (Candidate candidate : it){
            nrElems++;
        }
        assertTrue(nrElems == 3);
    }

    @Test
    public void addDuplicateId() throws Exception {
        Candidate c = new Candidate(1, "Mircea", "0756456789", "Rozelor 20");
        Candidate c1 = new Candidate(2, "Paul", "0756456789", "Frunzelor 3");
        Candidate c2 = new Candidate(1, "Gabriel", "0756456789", "Labradorilor 12");
        repository.save(c); repository.save(c1);

        try {
            repository.save(c2);
        } catch (RepositoryException exception){
            assertTrue(exception.getMessage().equals("This id is already in repository"));
        }
    }

    @Test
    public void removeInvalidId() throws Exception {
        try {
            repository.delete(1);
        } catch (RepositoryException exception){
            assertTrue(exception.getMessage().equals("This id is not valid"));
        }

    }

    @Test
    public void testValidUpdate() throws Exception {
        Candidate c = new Candidate(1, "Mircea", "0756456789", "Rozelor 20");
        Candidate c1 = new Candidate(2, "Paul", "0756456789", "Frunzelor 3");
        Candidate c2 = new Candidate(1, "Gabriel", "0756456789", "Labradorilor 12");
        repository.save(c); repository.save(c1);

        repository.update(1, c2);
        Candidate candidate = repository.getById(1);
        assertTrue(candidate.getName().equals("Gabriel"));

        Candidate c3 = new Candidate(3, "Mihai", "0123345345", "Rozelor 14");
        repository.update(1, c3);
        candidate = repository.getById(3);
        assertTrue(candidate.getName().equals("Mihai"));
    }

    @Test
    public void testInvalidInitialIdUpdate() throws Exception {
        Candidate c = new Candidate(1, "Mircea", "0756456789", "Rozelor 20");
        Candidate c1 = new Candidate(2, "Paul", "0756456789", "Frunzelor 3");
        Candidate c2 = new Candidate(1, "Gabriel", "0756456789", "Labradorilor 12");
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
        Candidate c = new Candidate(1, "Mircea", "0756456789", "Rozelor 20");
        Candidate c1 = new Candidate(2, "Paul", "0756456789", "Frunzelor 3");
        Candidate c2 = new Candidate(2, "Gabriel", "0756456789", "Labradorilor 12");
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
        Candidate c = new Candidate(1, "Mircea", "0756456789", "Rozelor 20");
        Candidate c1 = new Candidate(2, "Paul", "0756456789", "Frunzelor 3");
        Candidate c2 = new Candidate(1, "", "075645678", "Labradorilor 12");
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