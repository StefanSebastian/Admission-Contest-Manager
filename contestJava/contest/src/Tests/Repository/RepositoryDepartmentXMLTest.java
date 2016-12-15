package Tests.Repository;

import Domain.Department;
import Repository.RepositoryDepartmentXML;
import Validator.ValidatorDepartment;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import static org.junit.Assert.*;

/**
 * Created by Sebi on 02-Dec-16.
 */
public class RepositoryDepartmentXMLTest {
    private RepositoryDepartmentXML repositoryDepartmentXML;

    @Before
    public void setUp() throws Exception {
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream("src/Tests/Repository/DepartmentsXML")));
        writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                "<departments>\n" +
                "    <department id = '1'>\n" +
                "        <name>Informatica</name>\n" +
                "        <numberOfPlaces>120</numberOfPlaces>\n" +
                "    </department>\n" +
                "    <department id = '2'>\n" +
                "        <name>Matematica</name>\n" +
                "        <numberOfPlaces>2</numberOfPlaces>\n" +
                "    </department>\n" +
                "</departments>");
        writer.close();


        repositoryDepartmentXML = new RepositoryDepartmentXML(
                "src/Tests/Repository/DepartmentsXML", new ValidatorDepartment());
    }

    @After
    public void tearDown() throws Exception {
        repositoryDepartmentXML = null;
    }

    @Test
    public void loadDataTest() throws Exception {
        repositoryDepartmentXML.loadData();
        assertTrue(repositoryDepartmentXML.size() == 2);
    }

    @Test
    public void saveDataTest() throws Exception {
        repositoryDepartmentXML.save(new Department(3, "Geografie", 23));
        repositoryDepartmentXML.saveData();
        assertTrue(repositoryDepartmentXML.size() == 3);
    }

    @Test
    public void save() throws Exception {
        repositoryDepartmentXML.save(new Department(3, "Geografie", 23));
        assertTrue(repositoryDepartmentXML.size() == 3);
    }

    @Test
    public void delete() throws Exception {
        repositoryDepartmentXML.delete(1);
        assertTrue(repositoryDepartmentXML.size() == 1);
    }

    @Test
    public void update() throws Exception {
        Department department = new Department(1, "Fizica", 56);
        repositoryDepartmentXML.update(1, department);
        assertTrue(repositoryDepartmentXML.getById(1).getName().equals("Fizica"));
    }

    @Test
    public void clearAll() throws Exception {
        assertTrue(repositoryDepartmentXML.size() == 2);
        repositoryDepartmentXML.clearAll();
        assertTrue(repositoryDepartmentXML.size() == 0);
    }

    @Test
    public void size() throws Exception {
        assertTrue(repositoryDepartmentXML.size() == 2);
        repositoryDepartmentXML.delete(1);
        assertTrue(repositoryDepartmentXML.size() == 1);
        repositoryDepartmentXML.clearAll();
        assertTrue(repositoryDepartmentXML.size() == 0);
        repositoryDepartmentXML.save(new Department(5, "Fizica", 12));
        assertTrue(repositoryDepartmentXML.size() == 1);
    }

}