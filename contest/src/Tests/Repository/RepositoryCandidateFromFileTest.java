package Tests.Repository;

import Domain.Candidate;
import Repository.RepositoryCandidateFromFile;
import Validator.ValidatorCandidate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import static org.junit.Assert.*;

/**
 * Created by Sebi on 10/28/2016.
 */
public class RepositoryCandidateFromFileTest {
    private RepositoryCandidateFromFile repository;
    private ValidatorCandidate validatorCandidate;

    @Before
    public void setUp() throws Exception {
        validatorCandidate = new ValidatorCandidate();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("src/Tests/Repository/CandidatiTest")));
        writer.write("1|Marian Marcel|0743212222|Rozelor 2\n");
        writer.write("2|Gheorghe Hamac|0742333444|Vulturilor 13\n");
        writer.write("3|Alin Minge|0756555676|Revolutiei 22\n");
        writer.write("4|Maria Pastarnac|0755444323|Muncii 11\n");
        writer.write("5|Ionela Codru|0758010202|Sarguintei 57\n");
        writer.close();
        repository = new RepositoryCandidateFromFile("src/Tests/Repository/CandidatiTest", validatorCandidate);
    }

    @After
    public void tearDown() throws Exception {
        repository = null;
        validatorCandidate = null;
    }

    @Test
    public void loadData() throws Exception {
        repository.loadData();
        assertTrue(repository.size() == 5);

        Candidate c1 = repository.getById(1);
        assertTrue(c1.getId() == 1);
        assertTrue(c1.getName().equals("Marian Marcel"));
        assertTrue(c1.getTelephone().equals("0743212222"));
        assertTrue(c1.getAddress().equals("Rozelor 2"));

        repository.loadData();
        assertTrue(repository.size() == 5);
    }

    @Test
    public void saveData() throws Exception {
        Candidate c1 = new Candidate(8, "Ghita", "0742333444", "Rozelor 2");
        Candidate c2 = new Candidate(9, "Mircea", "0742323414", "Magurei 2");
        Candidate c3 = new Candidate(10, "Florin", "0222333411", "Gh Doja 2");
        repository.save(c1);
        repository.save(c2);
        repository.save(c3);
        repository.saveData();

        repository.loadData();
        assertTrue(repository.size() == 8);

    }

    @Test
    public void saveAfterDeletion() throws Exception{
        repository.loadData();
        assertTrue(repository.size() == 5);

        repository.delete(1);
        assertTrue(repository.size() == 4);
    }

    @Test
    public void saveAfterAdd() throws Exception{
        repository.loadData();
        assertTrue(repository.size() == 5);

        Candidate c1 = new Candidate(11, "Ghita", "0742333444", "Rozelor 2");
        repository.save(c1);
        repository.saveData();
        repository.loadData();
        assertTrue(repository.size() == 6);

        Candidate c2 = repository.getById(11);
        assertTrue(c2.getName().equals("Ghita"));
    }

    @Test
    public void saveAfterModify() throws Exception{
        repository.loadData();

        Candidate candidate = repository.getById(1);
        assertTrue(candidate.getName().equals("Marian Marcel"));
        assertTrue(candidate.getId() == 1);
        repository.delete(1);

        Candidate c1 = new Candidate(1, "Ghita", "0742333444", "Rozelor 2");
        repository.save(c1);

        candidate = repository.getById(1);
        assertTrue(candidate.getName().equals("Ghita"));
        assertTrue(candidate.getId() == 1);
    }

    @Test
    public void saveEmptyRepository() throws Exception{
        repository.loadData();
        repository.delete(1);
        repository.delete(2);
        repository.delete(3);
        repository.delete(4);
        repository.delete(5);
        assertTrue(repository.size() == 0);
        repository.saveData();
        repository.loadData();
        assertTrue(repository.size() == 0);

    }

}