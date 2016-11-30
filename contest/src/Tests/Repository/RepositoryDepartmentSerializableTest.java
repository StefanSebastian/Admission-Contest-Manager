package Tests.Repository;

import Domain.Department;
import Repository.RepositoryDepartmentSerializable;
import Validator.ValidatorDepartment;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Sebi on 10/28/2016.
 */
public class RepositoryDepartmentSerializableTest {
    private RepositoryDepartmentSerializable repository;
    private ValidatorDepartment validatorDepartment;

    @Before
    public void setUp() throws Exception {
        //init file
        Department s1 = new Department(1, "Informatica", 300);
        Department s2 = new Department(2, "Matematica", 150);
        Department s3 = new Department(3, "Fizica", 120);
        List<Department> list = new ArrayList<Department>();
        list.add(s1); list.add(s2); list.add(s3);

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new FileOutputStream("src/Tests/Repository/SectiiTest"));
        objectOutputStream.writeObject(list);
        objectOutputStream.close();

        validatorDepartment = new ValidatorDepartment();
        repository = new RepositoryDepartmentSerializable("src/Tests/Repository/SectiiTest", validatorDepartment);
    }

    @After
    public void tearDown() throws Exception {
        repository = null;

        //clear file
        List<Department> list = new ArrayList<>();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new FileOutputStream("src/Tests/Repository/SectiiTest"));
        objectOutputStream.writeObject(list);
        objectOutputStream.close();
    }

    @Test
    public void loadData() throws Exception {
        Department s1 = new Department(1, "Informatica", 300);
        Department s2 = new Department(2, "Matematica", 150);
        Department s3 = new Department(3, "Fizica", 120);
        List<Department> list = new ArrayList<Department>();
        list.add(s1); list.add(s2); list.add(s3);

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new FileOutputStream("src/Tests/Repository/SectiiTest"));
        objectOutputStream.writeObject(list);
        objectOutputStream.close();

        repository.loadData();
        assertTrue(repository.size() == 3);

        list.removeIf(e -> e.getName().equals("Matematica"));
        ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(
                new FileOutputStream("src/Tests/Repository/SectiiTest"));
        objectOutputStream2.writeObject(list);
        objectOutputStream2.close();

        repository.loadData();
        assertTrue(repository.size() == 2);
    }

    @Test
    public void saveData() throws Exception {
        repository.loadData();
        Department s1 = new Department(4, "Informatica", 300);
        Department s2 = new Department(5, "Matematica", 150);
        Department s3 = new Department(6, "Fizica", 120);
        repository.save(s1);
        repository.save(s2);
        repository.save(s3);
        repository.saveData();

        repository.loadData();
        assertTrue(repository.size() == 6);
        repository.loadData();
        assertTrue(repository.size() == 6);
    }

    @Test
    public void saveAfterDeletion() throws Exception{
        repository.loadData();
        repository.delete(1);
        repository.saveData();

        repository.loadData();
        assertTrue(repository.size() == 2);
    }

    @Test
    public void saveAfterAdd() throws Exception{
        repository.loadData();
        Department s1 = new Department(4, "Informatica", 300);
        Department s2 = new Department(5, "Matematica", 150);
        Department s3 = new Department(6, "Fizica", 120);
        repository.save(s1);
        repository.save(s2);
        repository.saveData();

        repository.loadData();
        assertTrue(repository.size() == 5);

        repository.save(s3);
        repository.saveData();

        repository.loadData();
        assertTrue(repository.size() == 6);
    }

    @Test
    public void saveAfterModify() throws Exception{
        repository.loadData();
        repository.delete(2);
        Department s4 = new Department(2, "Biologie", 56);
        repository.save(s4);

        repository.loadData();
        Department s5 = repository.getById(2);
        assertTrue(s5.getName().equals("Biologie"));
        assertTrue(s5.getId() == 2);
    }

    @Test
    public void saveEmptyRepository() throws Exception{
        repository.loadData();
        assertTrue(repository.size() == 3);
        repository.delete(1);
        repository.delete(2);
        repository.delete(3);
        repository.saveData();

        repository.loadData();
        assertTrue(repository.size() == 0);
    }

}