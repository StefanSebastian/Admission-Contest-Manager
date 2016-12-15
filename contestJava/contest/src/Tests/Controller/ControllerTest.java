package Tests.Controller;

import Domain.Candidate;
import Domain.Department;
import Repository.RepositoryCandidate;
import Repository.RepositoryDepartment;
import Validator.ValidatorCandidate;
import Controller.ControllerCandidate;
import Controller.ControllerDepartment;
import Validator.ValidatorDepartment;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by Sebi on 15-Nov-16.
 */
public class ControllerTest {
    private ControllerCandidate controllerCandidate;
    private ControllerDepartment controllerDepartment;
    private RepositoryCandidate repositoryCandidate;
    private ValidatorCandidate validatorCandidate;
    private RepositoryDepartment repositoryDepartment;
    private ValidatorDepartment validatorDepartment;

    @Before
    public void setUp() throws Exception {
        validatorCandidate = new ValidatorCandidate();
        repositoryCandidate = new RepositoryCandidate(validatorCandidate);
        validatorDepartment = new ValidatorDepartment();
        repositoryDepartment = new RepositoryDepartment(validatorDepartment);
        controllerCandidate = new ControllerCandidate(repositoryCandidate);
        controllerDepartment = new ControllerDepartment(repositoryDepartment);
    }

    @After
    public void tearDown() throws Exception {
        repositoryCandidate = null;
        validatorCandidate = null;
        repositoryDepartment = null;
        validatorDepartment = null;
        controllerCandidate = null;
        controllerDepartment = null;
    }

    @Test
    public void candidatCount() throws Exception {
        assertTrue(controllerCandidate.size() == 0);
        controllerCandidate.save("1", "George", "0742356567", "Rozelor 12");
        assertTrue(controllerCandidate.size() == 1);
    }

    @Test
    public void addCandidat() throws Exception {
        controllerCandidate.save("1", "George", "0742356567", "Rozelor 12");
        assertTrue(controllerCandidate.size() == 1);
        Candidate c = controllerCandidate.getById("1");
        assertTrue(c.getId() == 1);
        assertTrue(c.getName().equals("George"));
        assertTrue(c.getTelephone().equals("0742356567"));
        assertTrue(c.getAddress().equals("Rozelor 12"));
    }

    @Test
    public void removeCandidat() throws Exception {
        controllerCandidate.save("1", "George", "0742356567", "Rozelor 12");
        controllerCandidate.save("2", "Mihai", "0742356111", "Vulturilor 1");
        assertTrue(controllerCandidate.size() == 2);
        controllerCandidate.delete("2");
        assertTrue(controllerCandidate.size() == 1);
    }

    @Test
    public void getById() throws Exception {
        controllerCandidate.save("1", "George", "0742356567", "Rozelor 12");
        controllerCandidate.save("2", "Mihai", "0742356111", "Vulturilor 1");
        controllerCandidate.save("3", "Maria", "0742356222", "Soimilor 56");
        Candidate c = controllerCandidate.getById("2");
        assertTrue(c.getId() == 2);
        assertTrue(c.getName().equals("Mihai"));
    }

    @Test
    public void getAllCandidati() throws Exception {
        controllerCandidate.save("1", "George", "0742356567", "Rozelor 12");
        controllerCandidate.save("2", "Mihai", "0742356111", "Vulturilor 1");
        controllerCandidate.save("3", "Maria", "0742356222", "Soimilor 56");
        Iterable<Candidate> candidats = controllerCandidate.getAll();
        Integer nrElems = 0;
        for (Candidate c : candidats){
            nrElems++;
        }
        assertTrue(nrElems == 3);
    }

    @Test
    public void filtrareNume() throws Exception {
        controllerCandidate.save("1", "George", "0742356567", "Rozelor 12");
        controllerCandidate.save("2", "Mihai", "0742356111", "Vulturilor 1");
        controllerCandidate.save("3", "Mara", "0742356222", "Soimilor 56");
        controllerCandidate.save("4", "Calin", "0742356567", "Rozelor 12");
        controllerCandidate.save("5", "Giovanni", "0742356111", "Vulturilor 1");
        controllerCandidate.save("6", "Mara", "0742356222", "Soimilor 56");

        List<Candidate> list;

        list = controllerCandidate.filterCandidatesByName("Mara");
        assertTrue(list.size() == 2);
       // list.forEach(x -> System.out.println(x.getId() + " " + x.getName()));

        list = controllerCandidate.filterCandidatesByName("Mihai");
        assertTrue(list.size() == 1);

        list = controllerCandidate.filterCandidatesByName("Verde");
        assertTrue(list.size() == 0);
    }

    @Test
    public void filtrareTelefonCod() throws  Exception {
        controllerCandidate.save("1", "George", "0742356567", "Rozelor 12");
        controllerCandidate.save("2", "Mihai", "0742356111", "Vulturilor 1");
        controllerCandidate.save("3", "Maria", "0742356222", "Soimilor 56");
        controllerCandidate.save("4", "Calin", "0752356567", "Rozelor 12");
        controllerCandidate.save("5", "Giovanni", "0742356111", "Vulturilor 1");
        controllerCandidate.save("6", "Mara", "0752356222", "Soimilor 56");
        controllerCandidate.save("7", "Sergiu", "0788356222", "Soimilor 56");

        List<Candidate> list;

        list = controllerCandidate.filterCandidatesByTelephone("0752");
        assertTrue(list.size() == 2);

        list = controllerCandidate.filterCandidatesByTelephone("074");
        assertTrue(list.size() == 4);

        list = controllerCandidate.filterCandidatesByTelephone("078");
        assertTrue(list.size() == 1);

        list = controllerCandidate.filterCandidatesByTelephone("789");
        assertTrue(list.size() == 0);
    }

    @Test
    public void sortareDenumireCandidati() throws Exception {
        List<Candidate> lvida;
        lvida = controllerCandidate.sortCandidatesByName();
        assertTrue(lvida.size() == 0);

        controllerCandidate.save("1", "George", "0742356567", "Rozelor 12");
        controllerCandidate.save("2", "Mihai", "0742356111", "Vulturilor 1");
        controllerCandidate.save("3", "Maria", "0742356222", "Soimilor 56");
        controllerCandidate.save("4", "Calin", "0752356567", "Rozelor 12");
        controllerCandidate.save("5", "Giovanni", "0742356111", "Vulturilor 1");
        controllerCandidate.save("6", "Mara", "0752356222", "Soimilor 56");
        controllerCandidate.save("7", "Sergiu", "0788356222", "Soimilor 56");

        List<Candidate> list;

        list = controllerCandidate.sortCandidatesByName();
        assertTrue(list.size() == 7);

        Candidate c1 = list.get(0);
        assertTrue(c1.getName().equals("Calin"));

        c1 = list.get(6);
        assertTrue(c1.getName().equals("Sergiu"));
    }

    @Test
    public void filtrariSortariCandidati() throws Exception {
        controllerCandidate.save("1", "George", "0742356567", "Rozelor 12");
        controllerCandidate.save("2", "Mihai", "0742356111", "Vulturilor 1");
        controllerCandidate.save("3", "Mara", "0742356222", "Soimilor 56");
        controllerCandidate.save("4", "Calin", "0752356567", "Rozelor 12");
        controllerCandidate.save("5", "George", "0742356111", "Vulturilor 1");
        controllerCandidate.save("6", "Mara", "0752356222", "Soimilor 56");
        controllerCandidate.save("7", "Sergiu", "0788356222", "Soimilor 56");

        //controllerCandidate.sortCandidatesByName().forEach(x -> System.out.println(x.getName()));

        //filtreaza dupa nume si sorteaza dupa ID
        List<Candidate> lista;
        lista = controllerCandidate.filterCandidatesByName("Mara");
        assertTrue(lista.size() == 2);
        lista = controllerCandidate.sortGeneric(lista, (x, y)->x.getId().compareTo(y.getId()));

        Candidate c1 = lista.get(0);
        assertTrue(c1.getId() == 3);
        c1 = lista.get(1);
        assertTrue(c1.getId() == 6);


        //filtreaza dupa telefon si sorteaza dupa nume
        lista = controllerCandidate.filterCandidatesByTelephone("074");
        assertTrue(lista.size() == 4);
        lista = controllerCandidate.sortGeneric(lista, (x, y)->x.getName().compareTo(y.getName()));
        c1 = lista.get(0);
        assertTrue(c1.getName().equals("George"));
        c1 = lista.get(3);
        assertTrue(c1.getName().equals("Mihai"));

        //filtreaza dupa telefon si sorteaza dupa nume descrescator si id crescator
        lista = controllerCandidate.filterCandidatesByTelephone("074");
        assertTrue(lista.size() == 4);
        lista = controllerCandidate.sortGeneric(lista, (x, y)->{
            if (x.getName().equals(y.getName())){
                return x.getId().compareTo(y.getId());
            }
            return y.getName().compareTo(x.getName());
        });
        c1 = lista.get(0);
        assertTrue(c1.getName().equals("Mihai"));
        c1 = lista.get(2);
        assertTrue(c1.getName().equals("George"));
        assertTrue(c1.getId() == 1);
        c1 = lista.get(3);
        assertTrue(c1.getName().equals("George"));
        assertTrue(c1.getId() == 5);

    }



    @Test
    public void sectieCount() throws Exception {
        assertTrue(controllerCandidate.size() == 0);
        controllerDepartment.save("1", "Informatica", "200");
        assertTrue(controllerDepartment.size() == 1);
    }

    @Test
    public void addSectie() throws Exception {
        controllerDepartment.save("1", "Informatica", "200");
        assertTrue(controllerDepartment.size() == 1);

        Department department = controllerDepartment.getById("1");
        assertTrue(department.getId() == 1);
        assertTrue(department.getName().equals("Informatica"));
        assertTrue(department.getNumberOfPlaces() == 200);
    }

    @Test
    public void removeSectie() throws Exception {
        controllerDepartment.save("1", "Informatica", "200");
        controllerDepartment.save("2", "Matematica", "20");
        controllerDepartment.save("3", "Istorie", "33");
        assertTrue(controllerDepartment.size() == 3);
        controllerDepartment.delete("2");
        assertTrue(controllerDepartment.size() == 2);
    }

    @Test
    public void getSectieById() throws Exception {
        controllerDepartment.save("1", "Informatica", "200");
        controllerDepartment.save("2", "Matematica", "20");
        controllerDepartment.save("3", "Istorie", "33");

        Department department = controllerDepartment.getById("3");
        assertTrue(department.getId() == 3);
        assertTrue(department.getName().equals("Istorie"));
    }

    @Test
    public void getAllSectii() throws Exception {
        controllerDepartment.save("1", "Informatica", "200");
        controllerDepartment.save("2", "Matematica", "20");
        controllerDepartment.save("3", "Istorie", "33");

        Iterable<Department> sectii = controllerDepartment.getAll();
        Integer nrElems = 0;
        for (Department ignored : sectii){
            nrElems++;
        }
        assertTrue(nrElems == 3);
    }

    @Test
    public void filterSectiiByDenumire() throws Exception {
        controllerDepartment.save("1", "Informatica", "200");
        controllerDepartment.save("2", "Matematica", "20");
        controllerDepartment.save("3", "Istorie", "33");
        controllerDepartment.save("4", "Istorie Antica", "33");
        controllerDepartment.save("5", "Istorie Moderna", "33");

        List<Department> list = controllerDepartment.filterDepartmentsByName("Istorie");
        assertTrue(list.size() == 3);

        list = controllerDepartment.filterDepartmentsByName("Informatica");
        assertTrue(list.size() == 1);

        list = controllerDepartment.filterDepartmentsByName("Geografie");
        assertTrue(list.size() == 0);
    }

    @Test
    public void filterSectiiByLocuri() throws Exception {
        controllerDepartment.save("1", "Informatica", "200");
        controllerDepartment.save("2", "Matematica", "20");
        controllerDepartment.save("3", "Istorie", "33");
        controllerDepartment.save("4", "Istorie Antica", "78");
        controllerDepartment.save("5", "Istorie Moderna", "150");

        List<Department> list = controllerDepartment.filterDepartmentsByNumberOfPlaces(70);
        assertTrue(list.size() == 3);

        list = controllerDepartment.filterDepartmentsByNumberOfPlaces(500);
        assertTrue(list.size() == 0);

        list = controllerDepartment.filterDepartmentsByNumberOfPlaces(0);
        assertTrue(list.size() == 5);

        list = controllerDepartment.filterDepartmentsByNumberOfPlaces(199);
        assertTrue(list.size() == 1);
    }

    @Test
    public void sortareDenumireSectii() throws Exception {
        List<Department> lvida;
        lvida = controllerDepartment.sortDepartmentsByName();
        assertTrue(lvida.size() == 0);

        controllerDepartment.save("1", "Informatica", "200");
        controllerDepartment.save("2", "Matematica", "20");
        controllerDepartment.save("3", "Istorie", "33");
        controllerDepartment.save("4", "Istorie Antica", "78");
        controllerDepartment.save("5", "Istorie Moderna", "150");

        List<Department> list = new ArrayList<>();
        list = controllerDepartment.sortDepartmentsByName();
        Department s1 = list.get(0);
        assertTrue(s1.getName().equals("Informatica"));
        s1 = list.get(4);
        assertTrue(s1.getName().equals("Matematica"));
    }

    @Test
    public void filtrariSortariSectii() throws Exception {
        controllerDepartment.save("1", "Informatica", "200");
        controllerDepartment.save("2", "Matematica", "20");
        controllerDepartment.save("3", "Istorie", "33");
        controllerDepartment.save("4", "Istorie Antica", "220");
        controllerDepartment.save("5", "Istorie Moderna", "150");
        controllerDepartment.save("6", "Matematica Aplicata", "150");

        //Filtreaza dupa denumire si sorteaza dupa numarul de locuri
        List<Department> list;
        list = controllerDepartment.filterDepartmentsByName("Istorie");
        assertTrue(list.size() == 3);
        list = controllerDepartment.sortGeneric(list, (x, y)->x.getNumberOfPlaces().compareTo(y.getNumberOfPlaces()));
        Department s1 = list.get(0);
        assertTrue(s1.getName().equals("Istorie"));
        s1 = list.get(2);
        assertTrue(s1.getName().equals("Istorie Antica"));

        //Filtreaza dupa numarul de locuri si sorteaza dupa nume
        list = controllerDepartment.filterDepartmentsByNumberOfPlaces(100);
        assertTrue(list.size() == 4);
        list = controllerDepartment.sortGeneric(list, (x, y)->x.getName().compareTo(y.getName()));
        s1 = list.get(0);
        assertTrue(s1.getName().equals("Informatica"));
        s1 = list.get(1);
        assertTrue(s1.getName().equals("Istorie Antica"));

        //Filtreaza dupa numarul de locuri si sorteaza dupa nr de locuri si dupa nume descrescator
        list = controllerDepartment.filterDepartmentsByNumberOfPlaces(100);
        assertTrue(list.size() == 4);
        list = controllerDepartment.sortGeneric(list, (x, y)->{
            if (x.getNumberOfPlaces().equals(y.getNumberOfPlaces())){
                return y.getName().compareTo(x.getName());
            }
            return x.getNumberOfPlaces().compareTo(y.getNumberOfPlaces());
        });
        s1 = list.get(0);
        assertTrue(s1.getName().equals("Matematica Aplicata"));
        s1 = list.get(1);
        assertTrue(s1.getName().equals("Istorie Moderna"));
        s1 = list.get(2);
        assertTrue(s1.getName().equals("Informatica"));
    }

    @Test
    public void clearAllCandidati() throws Exception {
        controllerCandidate.save("1", "George", "0742356567", "Rozelor 12");
        controllerCandidate.save("2", "Mihai", "0742356111", "Vulturilor 1");
        controllerCandidate.save("3", "Mara", "0742356222", "Soimilor 56");
        controllerCandidate.save("4", "Calin", "0752356567", "Rozelor 12");
        assertTrue(controllerCandidate.size() == 4);
        controllerCandidate.clearAll();
        assertTrue(controllerCandidate.size() == 0);
    }

    @Test
    public void clearAllSectii() throws Exception {
        controllerDepartment.save("1", "Informatica", "200");
        controllerDepartment.save("2", "Matematica", "20");
        controllerDepartment.save("3", "Istorie", "33");
        controllerDepartment.save("4", "Istorie Antica", "220");
        assertTrue(controllerDepartment.size() == 4);
        controllerDepartment.clearAll();
        assertTrue(controllerDepartment.size() == 0);
    }
}
