package Teste.Validator;

import Domain.Sectie;
import Validator.ValidatorException;
import Validator.ValidatorSectie;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Sebi on 10/18/2016.
 */
public class ValidatorSectieTest {
    ValidatorSectie validatorSectie;

    @Before
    public void setUp() throws Exception {
        validatorSectie = new ValidatorSectie();
    }

    @After
    public void tearDown() throws Exception {
        validatorSectie = null;
    }

    @Test
    public void validateSectieValida() throws Exception {
        Sectie s = new Sectie(1, "Informatica", 300);
        try {
            validatorSectie.validate(s);
        } catch (ValidatorException exception){
            assertTrue(false);
        }
    }

    @Test
    public void validateSectieNumeInvalid() throws Exception {
        Sectie s = new Sectie(1, "", 300);
        try {
            validatorSectie.validate(s);
        } catch (ValidatorException exception){
            assertTrue(exception.getMessage().equals("Numele sectiei nu e completat.\n"));
        }
    }

    @Test
    public void validateSectieNumarLocuriNegativ() throws Exception {
        Sectie s = new Sectie(1, "Matematica", -12);
        try {
            validatorSectie.validate(s);
        } catch (ValidatorException exception){
            assertTrue(exception.getMessage().equals("Numarul de locuri dintr-o sectie trebuie sa fie pozitiv.\n"));
        }
    }

    @Test
    public void validateSectieNumarLocuriSiNumeInvalide() throws Exception {
        Sectie s = new Sectie(1, "", -12);
        try {
            validatorSectie.validate(s);
        } catch (ValidatorException exception){
            assertTrue(exception.getMessage().equals("Numele sectiei nu e completat.\n" +
                           "Numarul de locuri dintr-o sectie trebuie sa fie pozitiv.\n"));
        }
    }

}