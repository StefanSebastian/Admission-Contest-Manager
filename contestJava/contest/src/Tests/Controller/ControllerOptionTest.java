package Tests.Controller;

import Controller.ControllerCandidate;
import Controller.ControllerDepartment;
import Controller.ControllerOption;
import Domain.Candidate;
import Domain.Department;
import Domain.Option;
import Repository.RepositoryCandidate;
import Repository.RepositoryDepartment;
import Repository.RepositoryOption;
import Repository.RepositoryOptionXML;
import Validator.ControllerException;
import Validator.ValidatorCandidate;
import Validator.ValidatorDepartment;
import Validator.ValidatorOption;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import static org.junit.Assert.*;

/**
 * Created by Sebi on 30-Nov-16.
 */
public class ControllerOptionTest {
    private RepositoryOption repositoryOption;
    private RepositoryDepartment repositoryDepartment;
    private RepositoryCandidate repositoryCandidate;
    private ControllerOption controllerOption;
    private ControllerDepartment controllerDepartment;
    private ControllerCandidate controllerCandidate;

    @Before
    public void setUp() throws Exception {
        repositoryDepartment = new RepositoryDepartment(new ValidatorDepartment());
        repositoryCandidate = new RepositoryCandidate(new ValidatorCandidate());
        repositoryOption = new RepositoryOption(new ValidatorOption(),
                repositoryCandidate, repositoryDepartment);
        controllerDepartment = new ControllerDepartment(repositoryDepartment);
        controllerCandidate = new ControllerCandidate(repositoryCandidate);
        controllerOption = new ControllerOption(repositoryOption, controllerCandidate, controllerDepartment);
    }

    @After
    public void tearDown() throws Exception {
        repositoryDepartment = null;
        repositoryOption = null;
        repositoryCandidate = null;
        controllerOption = null;
        controllerCandidate = null;
        controllerDepartment = null;
    }

    @Test
    public void formatElement() throws Exception {
        try {
            Option op = controllerOption.formatElement("1", "1", "1");
            assertTrue(true);
        } catch (ControllerException e){
            assertTrue(false);
        }
    }

    @Test
    public void formatElementInvalid() throws Exception {
        try {
            Option op = controllerOption.formatElement("1", "aza", "1");
            assertTrue(false);
        } catch (ControllerException e){
            assertTrue(true);
        }

        try {
            Option op = controllerOption.formatElement("aaa", "2", "1");
            assertTrue(false);
        } catch (ControllerException e){
            assertTrue(true);
        }

        try {
            Option op = controllerOption.formatElement("1", "1", "$sd2");
            assertTrue(false);
        } catch (ControllerException e){
            assertTrue(true);
        }

        try {
            Option op = controllerOption.formatElement("1", "1", "");
            assertTrue(false);
        } catch (ControllerException e){
            assertTrue(true);
        }
    }

    @Test
    public void formatId() throws Exception {
        try {
            Integer id = controllerOption.formatId("1");
            assertTrue(true);
        } catch (ControllerException e){
            assertTrue(false);
        }
    }

    @Test
    public void formatIdInvalid() throws Exception {
        try {
            Integer id = controllerOption.formatId("a1");
            assertTrue(false);
        } catch (ControllerException e){
            assertTrue(true);
        }
    }

}