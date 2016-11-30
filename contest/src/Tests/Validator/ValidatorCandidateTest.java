package Tests.Validator;

import Domain.Candidate;
import Validator.ValidatorCandidate;
import Validator.ValidatorException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Sebi on 10/18/2016.
 */
public class ValidatorCandidateTest {
    ValidatorCandidate validatorCandidate;

    @Before
    public void setUp() throws Exception {
        validatorCandidate = new ValidatorCandidate();
    }

    @After
    public void tearDown() throws Exception {
        validatorCandidate = null;
    }

    @Test
    public void validateCandidatValid() throws Exception {
        Candidate c = new Candidate(1, "George", "0742343022", "Vulturilor 67");
        try {
            validatorCandidate.validate(c);
        } catch (ValidatorException exception){
            assertTrue(false);
        }
    }

    @Test
    public void validateCandidatInvalidName() throws Exception {
        Candidate c = new Candidate(1, "", "0742343022", "Vulturilor 67");
        try {
            validatorCandidate.validate(c);
        } catch (ValidatorException exception){
            assertTrue(exception.getMessage().equals("Name must not be empty\n"));
        }
    }

    @Test
    public void validateCandidatInvalidAdress() throws Exception {
        Candidate c = new Candidate(1, "Mihai", "0742343022", "");
        try {
            validatorCandidate.validate(c);
        } catch (ValidatorException exception){
            assertTrue(exception.getMessage().equals("Address must not be empty\n"));
        }
    }

    @Test
    public void validateCandidatInvalidAdressAndName() throws Exception {
        Candidate c = new Candidate(1, "", "0742343022", "");
        try {
            validatorCandidate.validate(c);
        } catch (ValidatorException exception){
            assertTrue(exception.getMessage().equals("Name must not be empty\nAddress must not be empty\n"));
        }
    }

    @Test
    public void validateCandidatNoTelephone() throws Exception {
        Candidate c = new Candidate(1, "George", "", "Vulturilor 67");
        try {
            validatorCandidate.validate(c);
        } catch (ValidatorException exception){
            assertTrue(exception.getMessage().equals("Telephone must not be empty\n" +
                    "Telephone number must have 10 digits\n"));
        }
    }

    @Test
    public void validateCandidatInvalidTelephone() throws Exception {
        Candidate c = new Candidate(1, "George", "abc", "Vulturilor 67");
        try {
            validatorCandidate.validate(c);
        } catch (ValidatorException exception) {
            assertTrue(exception.getMessage().equals("Telephone number must have 10 digits\n" +
                    "Telephone number must contain only digits\n"));
        }
    }

    @Test
    public void validateCandidatInvalidTelephoneTenCharacters() throws Exception {
        Candidate c = new Candidate(1, "George", "abc123def2", "Vulturilor 67");
        try {
            validatorCandidate.validate(c);
        } catch (ValidatorException exception){
            assertTrue(exception.getMessage().equals("Telephone number must contain only digits\n"));
        }
    }

}