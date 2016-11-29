package Teste.Repository;

import Domain.Candidat;
import Repository.RepositoryCandidatFromFile;
import Validator.ValidatorCandidat;
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
public class RepositoryCandidatFromFileTest {
    private RepositoryCandidatFromFile repository;
    private ValidatorCandidat validatorCandidat;

    @Before
    public void setUp() throws Exception {
        validatorCandidat = new ValidatorCandidat();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("src/Teste/Repository/CandidatiTest")));
        writer.write("1|Marian Marcel|0743212222|Rozelor 2\n");
        writer.write("2|Gheorghe Hamac|0742333444|Vulturilor 13\n");
        writer.write("3|Alin Minge|0756555676|Revolutiei 22\n");
        writer.write("4|Maria Pastarnac|0755444323|Muncii 11\n");
        writer.write("5|Ionela Codru|0758010202|Sarguintei 57\n");
        writer.close();
        repository = new RepositoryCandidatFromFile("src/Teste/Repository/CandidatiTest", validatorCandidat);
    }

    @After
    public void tearDown() throws Exception {
        repository = null;
        validatorCandidat = null;
    }

    @Test
    public void loadData() throws Exception {
        repository.loadData();
        assertTrue(repository.size() == 5);

        Candidat c1 = repository.getById(1);
        assertTrue(c1.getId() == 1);
        assertTrue(c1.getNume().equals("Marian Marcel"));
        assertTrue(c1.getTelefon().equals("0743212222"));
        assertTrue(c1.getAdresa().equals("Rozelor 2"));

        repository.loadData();
        assertTrue(repository.size() == 5);
    }

    @Test
    public void saveData() throws Exception {
        Candidat c1 = new Candidat(8, "Ghita", "0742333444", "Rozelor 2");
        Candidat c2 = new Candidat(9, "Mircea", "0742323414", "Magurei 2");
        Candidat c3 = new Candidat(10, "Florin", "0222333411", "Gh Doja 2");
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

        Candidat c1 = new Candidat(11, "Ghita", "0742333444", "Rozelor 2");
        repository.save(c1);
        repository.saveData();
        repository.loadData();
        assertTrue(repository.size() == 6);

        Candidat c2 = repository.getById(11);
        assertTrue(c2.getNume().equals("Ghita"));
    }

    @Test
    public void saveAfterModify() throws Exception{
        repository.loadData();

        Candidat candidat = repository.getById(1);
        assertTrue(candidat.getNume().equals("Marian Marcel"));
        assertTrue(candidat.getId() == 1);
        repository.delete(1);

        Candidat c1 = new Candidat(1, "Ghita", "0742333444", "Rozelor 2");
        repository.save(c1);

        candidat = repository.getById(1);
        assertTrue(candidat.getNume().equals("Ghita"));
        assertTrue(candidat.getId() == 1);
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