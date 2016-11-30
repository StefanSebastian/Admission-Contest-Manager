package Tests.Repository;

import Domain.Candidate;
import Domain.Department;
import Domain.Option;
import Repository.RepositoryCandidate;
import Repository.RepositoryDepartment;
import Repository.RepositoryOption;
import Repository.RepositoryOptionXML;
import Validator.ValidatorCandidate;
import Validator.ValidatorDepartment;
import Validator.ValidatorOption;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Sebi on 30-Nov-16.
 */
public class RepositoryOptionXMLTest {
    RepositoryOptionXML repositoryOption;
    RepositoryDepartment repositoryDepartment;
    RepositoryCandidate repositoryCandidate;

    @Before
    public void setUp() throws Exception {
        repositoryDepartment = new RepositoryDepartment(new ValidatorDepartment());
        repositoryCandidate = new RepositoryCandidate(new ValidatorCandidate());
        repositoryCandidate.save(new Candidate(1, "Cand1", "0742123123", "Adr1"));
        repositoryCandidate.save(new Candidate(2, "Cand2", "0742123123", "Adr2"));
        repositoryCandidate.save(new Candidate(3, "Cand3", "0742123123", "Adr3"));
        repositoryDepartment.save(new Department(1, "Opt1", 10));
        repositoryDepartment.save(new Department(2, "Opt2", 101));
        repositoryDepartment.save(new Department(3, "Opt3", 1023));

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("src/Tests/Repository/OptionsTest")));
        writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                "<options>\n" +
                "    <option id = '1'>\n" +
                "        <idCandidate>1</idCandidate>\n" +
                "        <idDepartment>1</idDepartment>\n" +
                "    </option>\n" +
                "    <option id = '2'>\n" +
                "        <idCandidate>1</idCandidate>\n" +
                "        <idDepartment>2</idDepartment>\n" +
                "    </option>\n" +
                "</options>");
        writer.close();

        repositoryOption = new RepositoryOptionXML("src/Tests/Repository/OptionsTest", new ValidatorOption(),
                repositoryCandidate, repositoryDepartment);
    }

    @After
    public void tearDown() throws Exception {
        repositoryDepartment = null;
        repositoryOption = null;
        repositoryCandidate = null;
    }

    @Test
    public void loadFromFile() throws Exception {
        repositoryOption.loadData();
        assertTrue(repositoryOption.size() == 2);
    }

    @Test
    public void save() throws Exception {
        assertTrue(repositoryOption.size() == 2);
        repositoryOption.save(new Option(3, 2, 2));
        assertTrue(repositoryOption.size() == 3);
    }

    @Test
    public void update() throws Exception {
        assertTrue(repositoryOption.size() == 2);
        repositoryOption.save(new Option(3, 2, 2));
        repositoryOption.update(3, new Option(3, 2, 3));
        assertTrue(repositoryOption.getById(3).getIdCandidate() == 2);
        assertTrue(repositoryOption.getById(3).getIdDepartment() == 3);
    }

    @Test
    public void size() throws Exception {
        assertTrue(repositoryOption.size() == 2);
        repositoryOption.delete(1);
        assertTrue(repositoryOption.size() == 1);
    }

    @Test
    public void delete() throws Exception {
        assertTrue(repositoryOption.size() == 2);
        repositoryOption.delete(1);
        repositoryOption.delete(2);
        assertTrue(repositoryOption.size() == 0);
    }

    @Test
    public void getById() throws Exception {
        Option opt = repositoryOption.getById(1);
        assertTrue(opt.getIdDepartment() == 1);
        assertTrue(opt.getIdCandidate() == 1);
    }

    @Test
    public void getAll() throws Exception {
        List<Option> options = repositoryOption.getAll();
        assertTrue(options.size() == 2);
    }

    @Test
    public void clearAll() throws Exception {
        assertTrue(repositoryOption.size() == 2);
        repositoryOption.clearAll();
        assertTrue(repositoryOption.size() == 0);
    }

}