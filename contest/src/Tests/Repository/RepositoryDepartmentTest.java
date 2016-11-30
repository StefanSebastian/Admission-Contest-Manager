package Tests.Repository;

import Domain.Department;
import Repository.RepositoryDepartment;
import Validator.RepositoryException;
import Validator.ValidatorDepartment;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Sebi on 10/18/2016.
 */
public class RepositoryDepartmentTest {
    RepositoryDepartment repository;
    ValidatorDepartment validatorDepartment;

    @Before
    public void setUp() throws Exception {
        validatorDepartment = new ValidatorDepartment();
        repository = new RepositoryDepartment(validatorDepartment);
    }

    @After
    public void tearDown() throws Exception {
        repository = null;
    }

    @Test
    public void size() throws Exception {
        assertTrue(repository.size() == 0);
        Department s = new Department(1, "Informatica", 200);
        repository.save(s);
        assertTrue(repository.size() == 1);
    }

    @Test
    public void save() throws Exception {
        Department s = new Department(1, "Informatica", 200);
        repository.save(s);
        assertTrue(repository.size() == 1);
        Department s2 = repository.getById(1);
        assertTrue(s2.getId() == 1);
        assertTrue(s2.getName().equals("Informatica"));
        assertTrue(s2.getNumberOfPlaces() == 200);
    }

    @Test
    public void delete() throws Exception {
        Department s = new Department(1, "Informatica", 200);
        repository.save(s);
        assertTrue(repository.size() == 1);
        repository.delete(1);
        assertTrue(repository.size() == 0);
    }

    @Test
    public void getById() throws Exception {
        Department s = new Department(1, "Informatica", 200);
        Department s1 = new Department(2, "Istorie", 150);
        Department s2 = new Department(3, "Geografie", 126);
        repository.save(s); repository.save(s1); repository.save(s2);

        Department s4 = repository.getById(2);
        assertTrue(s4.getId() == 2);
        assertTrue(s4.getName().equals("Istorie"));
    }

    @Test
    public void getAll() throws Exception {
        Department s = new Department(1, "Informatica", 200);
        Department s1 = new Department(2, "Istorie", 150);
        Department s2 = new Department(3, "Geografie", 126);
        repository.save(s); repository.save(s1); repository.save(s2);

        Iterable<Department> it = repository.getAll();
        Integer nrElems = 0;
        for (Department department : it){
            nrElems++;
        }
        assertTrue(nrElems == 3);
    }

    @Test
    public void addDuplicateId() throws Exception {
        Department s = new Department(1, "Informatica", 200);
        Department s1 = new Department(2, "Istorie", 150);
        Department s2 = new Department(1, "Geografie", 126);
        repository.save(s); repository.save(s1);

        try {
            repository.save(s2);
        } catch (RepositoryException exception){
            assertTrue(exception.getMessage().equals("This id is already in repository"));
        }
    }

    @Test
    public void removeInvalidId() throws Exception {
        try {
            repository.delete(1);
            assertTrue(false);
        }
        catch (RepositoryException exception){
            assertTrue(exception.getMessage().equals("This id is not valid"));
        }
    }

    @Test
    public void testValidUpdate() throws Exception {
        Department s = new Department(1, "Informatica", 200);
        Department s1 = new Department(2, "Istorie", 150);
        Department s2 = new Department(1, "Geografie", 126);
        repository.save(s); repository.save(s1);

        repository.update(1, s2);
        Department department = repository.getById(1);
        assertTrue(department.getName().equals("Geografie"));

        Department s3 = new Department(1, "Fizica", 126);
        repository.update(1, s3);
        department = repository.getById(1);
        assertTrue(department.getName().equals("Fizica"));

    }

    @Test
    public void testInvalidUpdate() throws Exception {
        Department s = new Department(1, "Informatica", 200);
        Department s1 = new Department(2, "Istorie", 150);
        Department s2 = new Department(1, "Geografie", 126);
        repository.save(s); repository.save(s1);

        try {
            repository.update(2, s2);
            assertTrue(false);
        } catch (RepositoryException exc){
            assertTrue(true);
        }
    }

}