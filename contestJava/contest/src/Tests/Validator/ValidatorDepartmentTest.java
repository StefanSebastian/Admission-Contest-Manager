package Tests.Validator;

import Domain.Department;
import Validator.ValidatorDepartment;
import Validator.ValidatorException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Sebi on 10/18/2016.
 */
public class ValidatorDepartmentTest {
    ValidatorDepartment validatorDepartment;

    @Before
    public void setUp() throws Exception {
        validatorDepartment = new ValidatorDepartment();
    }

    @After
    public void tearDown() throws Exception {
        validatorDepartment = null;
    }

    @Test
    public void validateSectieValida() throws Exception {
        Department s = new Department(1, "Informatica", 300);
        try {
            validatorDepartment.validate(s);
        } catch (ValidatorException exception){
            assertTrue(false);
        }
    }

    @Test
    public void validateSectieNumeInvalid() throws Exception {
        Department s = new Department(1, "", 300);
        try {
            validatorDepartment.validate(s);
        } catch (ValidatorException exception){
            assertTrue(exception.getMessage().equals("Name must be completed\n"));
        }
    }

    @Test
    public void validateSectieNumarLocuriNegativ() throws Exception {
        Department s = new Department(1, "Matematica", -12);
        try {
            validatorDepartment.validate(s);
        } catch (ValidatorException exception){
            assertTrue(exception.getMessage().equals("Number of places must be positive\n"));
        }
    }

    @Test
    public void validateSectieNumarLocuriSiNumeInvalide() throws Exception {
        Department s = new Department(1, "", -12);
        try {
            validatorDepartment.validate(s);
        } catch (ValidatorException exception){
            assertTrue(exception.getMessage().equals("Name must be completed\n" +
                           "Number of places must be positive\n"));
        }
    }

}