package Tests.Repository;

import Domain.Candidate;
import Domain.Department;
import Domain.Option;
import Repository.RepositoryCandidate;
import Repository.RepositoryDepartment;
import Repository.RepositoryOption;
import Validator.RepositoryException;
import Validator.ValidatorCandidate;
import Validator.ValidatorDepartment;
import Validator.ValidatorOption;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Sebi on 30-Nov-16.
 */
public class RepositoryOptionTest {
    private RepositoryOption repositoryOption;
    private ValidatorOption validatorOption;
    private RepositoryDepartment repositoryDepartment;
    private ValidatorDepartment validatorDepartment;
    private RepositoryCandidate repositoryCandidate;
    private ValidatorCandidate validatorCandidate;

    @Before
    public void setUp() throws Exception {
        validatorCandidate = new ValidatorCandidate();
        repositoryCandidate = new RepositoryCandidate(validatorCandidate);
        validatorDepartment = new ValidatorDepartment();
        repositoryDepartment = new RepositoryDepartment(validatorDepartment);
        validatorOption = new ValidatorOption();
        repositoryOption = new RepositoryOption(validatorOption, repositoryCandidate, repositoryDepartment);

        repositoryCandidate.save(new Candidate(1, "Cand1", "0742123123", "Adr1"));
        repositoryCandidate.save(new Candidate(2, "Cand2", "0742123123", "Adr2"));
        repositoryCandidate.save(new Candidate(3, "Cand3", "0742123123", "Adr3"));
        repositoryDepartment.save(new Department(1, "Opt1", 10));
        repositoryDepartment.save(new Department(2, "Opt2", 101));
        repositoryDepartment.save(new Department(3, "Opt3", 1023));
    }

    @After
    public void tearDown() throws Exception {
        validatorOption = null;
        repositoryOption = null;
        validatorCandidate = null;
        repositoryCandidate = null;
        validatorDepartment = null;
        repositoryDepartment = null;
    }

    @Test
    public void size() throws Exception {
        assertTrue(repositoryOption.size() == 0);
        repositoryOption.save(new Option(1, 1, 1));
        assertTrue(repositoryOption.size() == 1);
        repositoryOption.save(new Option(2, 2, 2));
        assertTrue(repositoryOption.size() == 2);

    }

    @Test
    public void save() throws Exception {
        repositoryOption.save(new Option(1,2,2));
        Option o = repositoryOption.getById(1);
        assertTrue(o.getId() == 1);
        assertTrue(o.getIdCandidate() == 2);
        assertTrue(o.getIdDepartment() == 2);
    }

    @Test
    public void update() throws Exception {
        repositoryOption.save(new Option(1,2,2));
        Option o = new Option(1, 3, 3);
        repositoryOption.update(1, o);
        Option o2 = repositoryOption.getById(1);
        assertTrue(o2.getId() == 1);
        assertTrue(o2.getIdDepartment() == 3);
    }

    @Test
    public void delete() throws Exception {
        assertTrue(repositoryOption.size() == 0);
        repositoryOption.save(new Option(1,2,2));
        assertTrue(repositoryOption.size() == 1);
        repositoryOption.save(new Option(2,2,3));
        assertTrue(repositoryOption.size() == 2);
        repositoryOption.delete(1);
        assertTrue(repositoryOption.size() == 1);
        repositoryOption.delete(2);
        assertTrue(repositoryOption.size() == 0);
    }

    @Test
    public void getById() throws Exception {
        repositoryOption.save(new Option(1,2,2));
        Option o = repositoryOption.getById(1);
        assertTrue(o.getId() == 1);
        assertTrue(o.getIdDepartment() == 2);
        assertTrue(o.getIdCandidate() == 2);
    }

    @Test
    public void getAll() throws Exception {
        repositoryOption.save(new Option(1,2,2));
        repositoryOption.save(new Option(2,3,2));
        repositoryOption.save(new Option(3,2,3));
        List<Option> list = repositoryOption.getAll();
        assertTrue(list.size() == 3);
    }

    @Test
    public void clearAll() throws Exception {
        repositoryOption.save(new Option(1,2,2));
        repositoryOption.save(new Option(2,3,2));
        repositoryOption.save(new Option(3,2,3));
        assertTrue(repositoryOption.size() == 3);
        repositoryOption.clearAll();
        assertTrue(repositoryOption.size() == 0);
    }

    @Test
    public void insertDuplicate() throws Exception {
        try{
            repositoryOption.save(new Option(1, 1, 1));
            repositoryOption.save(new Option(2, 1, 1));
            assertTrue(false);
        } catch (RepositoryException exc){
            assertTrue(true);
        }
    }

    @Test
    public void insertInvalidCandidateId() throws Exception {
        try {
            repositoryOption.save(new Option(1, 10, 1));
            assertTrue(false);
        } catch (RepositoryException exc){
            assertTrue(true);
        }
    }

    @Test
    public void insertInvalidDepartmentId() throws Exception {
        try {
            repositoryOption.save(new Option(1, 1, 10));
            assertTrue(false);
        } catch (RepositoryException exc){
            assertTrue(true);
        }
    }

    @Test
    public void updateDuplicate() throws  Exception {
        try {
            repositoryOption.save(new Option(1, 1, 1));
            repositoryOption.save(new Option(2, 1, 2));
            repositoryOption.update(1, new Option(1, 1, 2));
            assertTrue(false);
        } catch (RepositoryException exc){
            assertTrue(true);
        }
    }

    @Test
    public void updateSame() throws Exception {
        try {
            repositoryOption.save(new Option(1, 1, 1));
            repositoryOption.update(1, new Option(1, 1, 1));
            assertTrue(true);
        } catch (RepositoryException exc){
            assertTrue(false);
        }
    }

    @Test
    public void updateInvalidCandidate() throws Exception {
        try {
            repositoryOption.save(new Option(1, 1, 1));
            repositoryOption.update(1, new Option(1, 10, 1));
            assertTrue(false);
        } catch (RepositoryException exc){
            assertTrue(true);
        }
    }

    @Test
    public void updateInvalidDepartment() throws Exception {
        try {
            repositoryOption.save(new Option(1, 1, 1));
            repositoryOption.update(1, new Option(1, 1, 10));
            assertTrue(false);
        } catch (RepositoryException exc){
            assertTrue(true);
        }
    }
}