package Teste.Validator;

import Domain.Candidat;
import Validator.ValidatorCandidat;
import Validator.ValidatorException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Sebi on 10/18/2016.
 */
public class ValidatorCandidatTest {
    ValidatorCandidat validatorCandidat;

    @Before
    public void setUp() throws Exception {
        validatorCandidat = new ValidatorCandidat();
    }

    @After
    public void tearDown() throws Exception {
        validatorCandidat = null;
    }

    @Test
    public void validateCandidatValid() throws Exception {
        Candidat c = new Candidat(1, "George", "0742343022", "Vulturilor 67");
        try {
            validatorCandidat.validate(c);
        } catch (ValidatorException exception){
            assertTrue(false);
        }
    }

    @Test
    public void validateCandidatInvalidName() throws Exception {
        Candidat c = new Candidat(1, "", "0742343022", "Vulturilor 67");
        try {
            validatorCandidat.validate(c);
        } catch (ValidatorException exception){
            assertTrue(exception.getMessage().equals("Numele nu e completat.\n"));
        }
    }

    @Test
    public void validateCandidatInvalidAdress() throws Exception {
        Candidat c = new Candidat(1, "Mihai", "0742343022", "");
        try {
            validatorCandidat.validate(c);
        } catch (ValidatorException exception){
            assertTrue(exception.getMessage().equals("Adresa nu e completata.\n"));
        }
    }

    @Test
    public void validateCandidatInvalidAdressAndName() throws Exception {
        Candidat c = new Candidat(1, "", "0742343022", "");
        try {
            validatorCandidat.validate(c);
        } catch (ValidatorException exception){
            assertTrue(exception.getMessage().equals("Numele nu e completat.\nAdresa nu e completata.\n"));
        }
    }

    @Test
    public void validateCandidatNoTelephone() throws Exception {
        Candidat c = new Candidat(1, "George", "", "Vulturilor 67");
        try {
            validatorCandidat.validate(c);
        } catch (ValidatorException exception){
            assertTrue(exception.getMessage().equals("Telefonul nu e completat.\n" +
                    "Numarul de telefon trebuia sa aiba 10 cifre.\n"));
        }
    }

    @Test
    public void validateCandidatInvalidTelephone() throws Exception {
        Candidat c = new Candidat(1, "George", "abc", "Vulturilor 67");
        try {
            validatorCandidat.validate(c);
        } catch (ValidatorException exception) {
            assertTrue(exception.getMessage().equals("Numarul de telefon trebuia sa aiba 10 cifre.\n" +
                    "Numarul de telefon trebuie sa contina doar cifre.\n"));
        }
    }

    @Test
    public void validateCandidatInvalidTelephoneTenCharacters() throws Exception {
        Candidat c = new Candidat(1, "George", "abc123def2", "Vulturilor 67");
        try {
            validatorCandidat.validate(c);
        } catch (ValidatorException exception){
            assertTrue(exception.getMessage().equals("Numarul de telefon trebuie sa contina doar cifre.\n"));
        }
    }

}