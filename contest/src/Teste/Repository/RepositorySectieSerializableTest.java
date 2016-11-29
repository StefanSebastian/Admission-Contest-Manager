package Teste.Repository;

import Domain.Sectie;
import Repository.RepositorySectieSerializable;
import Validator.ValidatorSectie;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Sebi on 10/28/2016.
 */
public class RepositorySectieSerializableTest {
    private RepositorySectieSerializable repository;
    private ValidatorSectie validatorSectie;

    @Before
    public void setUp() throws Exception {
        //init file
        Sectie s1 = new Sectie(1, "Informatica", 300);
        Sectie s2 = new Sectie(2, "Matematica", 150);
        Sectie s3 = new Sectie(3, "Fizica", 120);
        List<Sectie> list = new ArrayList<Sectie>();
        list.add(s1); list.add(s2); list.add(s3);

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new FileOutputStream("src/Teste/Repository/SectiiTest"));
        objectOutputStream.writeObject(list);
        objectOutputStream.close();

        validatorSectie = new ValidatorSectie();
        repository = new RepositorySectieSerializable("src/Teste/Repository/SectiiTest", validatorSectie);
    }

    @After
    public void tearDown() throws Exception {
        repository = null;

        //clear file
        List<Sectie> list = new ArrayList<>();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new FileOutputStream("src/Teste/Repository/SectiiTest"));
        objectOutputStream.writeObject(list);
        objectOutputStream.close();
    }

    @Test
    public void loadData() throws Exception {
        Sectie s1 = new Sectie(1, "Informatica", 300);
        Sectie s2 = new Sectie(2, "Matematica", 150);
        Sectie s3 = new Sectie(3, "Fizica", 120);
        List<Sectie> list = new ArrayList<Sectie>();
        list.add(s1); list.add(s2); list.add(s3);

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new FileOutputStream("src/Teste/Repository/SectiiTest"));
        objectOutputStream.writeObject(list);
        objectOutputStream.close();

        repository.loadData();
        assertTrue(repository.size() == 3);

        list.removeIf(e -> e.getNume().equals("Matematica"));
        ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(
                new FileOutputStream("src/Teste/Repository/SectiiTest"));
        objectOutputStream2.writeObject(list);
        objectOutputStream2.close();

        repository.loadData();
        assertTrue(repository.size() == 2);
    }

    @Test
    public void saveData() throws Exception {
        repository.loadData();
        Sectie s1 = new Sectie(4, "Informatica", 300);
        Sectie s2 = new Sectie(5, "Matematica", 150);
        Sectie s3 = new Sectie(6, "Fizica", 120);
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
        Sectie s1 = new Sectie(4, "Informatica", 300);
        Sectie s2 = new Sectie(5, "Matematica", 150);
        Sectie s3 = new Sectie(6, "Fizica", 120);
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
        Sectie s4 = new Sectie(2, "Biologie", 56);
        repository.save(s4);

        repository.loadData();
        Sectie s5 = repository.getById(2);
        assertTrue(s5.getNume().equals("Biologie"));
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