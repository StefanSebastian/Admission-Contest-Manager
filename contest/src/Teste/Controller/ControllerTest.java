package Teste.Controller;

import Domain.Candidat;
import Domain.Sectie;
import Repository.RepositoryCandidat;
import Repository.RepositorySectie;
import Validator.ValidatorCandidat;
import Controller.ControllerCandidat;
import Controller.ControllerSectie;
import Validator.ValidatorSectie;
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
    private ControllerCandidat controllerCandidat;
    private ControllerSectie controllerSectie;
    private RepositoryCandidat repositoryCandidat;
    private ValidatorCandidat validatorCandidat;
    private RepositorySectie repositorySectie;
    private ValidatorSectie validatorSectie;

    @Before
    public void setUp() throws Exception {
        validatorCandidat = new ValidatorCandidat();
        repositoryCandidat = new RepositoryCandidat(validatorCandidat);
        validatorSectie = new ValidatorSectie();
        repositorySectie = new RepositorySectie(validatorSectie);
        controllerCandidat = new ControllerCandidat(repositoryCandidat);
        controllerSectie = new ControllerSectie(repositorySectie);
    }

    @After
    public void tearDown() throws Exception {
        repositoryCandidat = null;
        validatorCandidat = null;
        repositorySectie = null;
        validatorSectie = null;
        controllerCandidat = null;
        controllerSectie = null;
    }

    @Test
    public void candidatCount() throws Exception {
        assertTrue(controllerCandidat.size() == 0);
        controllerCandidat.save("1", "George", "0742356567", "Rozelor 12");
        assertTrue(controllerCandidat.size() == 1);
    }

    @Test
    public void addCandidat() throws Exception {
        controllerCandidat.save("1", "George", "0742356567", "Rozelor 12");
        assertTrue(controllerCandidat.size() == 1);
        Candidat c = controllerCandidat.getById("1");
        assertTrue(c.getId() == 1);
        assertTrue(c.getNume().equals("George"));
        assertTrue(c.getTelefon().equals("0742356567"));
        assertTrue(c.getAdresa().equals("Rozelor 12"));
    }

    @Test
    public void removeCandidat() throws Exception {
        controllerCandidat.save("1", "George", "0742356567", "Rozelor 12");
        controllerCandidat.save("2", "Mihai", "0742356111", "Vulturilor 1");
        assertTrue(controllerCandidat.size() == 2);
        controllerCandidat.delete("2");
        assertTrue(controllerCandidat.size() == 1);
    }

    @Test
    public void getById() throws Exception {
        controllerCandidat.save("1", "George", "0742356567", "Rozelor 12");
        controllerCandidat.save("2", "Mihai", "0742356111", "Vulturilor 1");
        controllerCandidat.save("3", "Maria", "0742356222", "Soimilor 56");
        Candidat c = controllerCandidat.getById("2");
        assertTrue(c.getId() == 2);
        assertTrue(c.getNume().equals("Mihai"));
    }

    @Test
    public void getAllCandidati() throws Exception {
        controllerCandidat.save("1", "George", "0742356567", "Rozelor 12");
        controllerCandidat.save("2", "Mihai", "0742356111", "Vulturilor 1");
        controllerCandidat.save("3", "Maria", "0742356222", "Soimilor 56");
        Iterable<Candidat> candidats = controllerCandidat.getAll();
        Integer nrElems = 0;
        for (Candidat c : candidats){
            nrElems++;
        }
        assertTrue(nrElems == 3);
    }

    @Test
    public void filtrareNume() throws Exception {
        controllerCandidat.save("1", "George", "0742356567", "Rozelor 12");
        controllerCandidat.save("2", "Mihai", "0742356111", "Vulturilor 1");
        controllerCandidat.save("3", "Mara", "0742356222", "Soimilor 56");
        controllerCandidat.save("4", "Calin", "0742356567", "Rozelor 12");
        controllerCandidat.save("5", "Giovanni", "0742356111", "Vulturilor 1");
        controllerCandidat.save("6", "Mara", "0742356222", "Soimilor 56");

        List<Candidat> list;

        list = controllerCandidat.filterCandidatiByDenumire("Mara");
        assertTrue(list.size() == 2);
       // list.forEach(x -> System.out.println(x.getId() + " " + x.getNume()));

        list = controllerCandidat.filterCandidatiByDenumire("Mihai");
        assertTrue(list.size() == 1);

        list = controllerCandidat.filterCandidatiByDenumire("Verde");
        assertTrue(list.size() == 0);
    }

    @Test
    public void filtrareTelefonCod() throws  Exception {
        controllerCandidat.save("1", "George", "0742356567", "Rozelor 12");
        controllerCandidat.save("2", "Mihai", "0742356111", "Vulturilor 1");
        controllerCandidat.save("3", "Maria", "0742356222", "Soimilor 56");
        controllerCandidat.save("4", "Calin", "0752356567", "Rozelor 12");
        controllerCandidat.save("5", "Giovanni", "0742356111", "Vulturilor 1");
        controllerCandidat.save("6", "Mara", "0752356222", "Soimilor 56");
        controllerCandidat.save("7", "Sergiu", "0788356222", "Soimilor 56");

        List<Candidat> list;

        list = controllerCandidat.filterCandidatiByTelefon("0752");
        assertTrue(list.size() == 2);

        list = controllerCandidat.filterCandidatiByTelefon("074");
        assertTrue(list.size() == 4);

        list = controllerCandidat.filterCandidatiByTelefon("078");
        assertTrue(list.size() == 1);

        list = controllerCandidat.filterCandidatiByTelefon("789");
        assertTrue(list.size() == 0);
    }

    @Test
    public void sortareDenumireCandidati() throws Exception {
        List<Candidat> lvida;
        lvida = controllerCandidat.sortCandidatiByDenumire();
        assertTrue(lvida.size() == 0);

        controllerCandidat.save("1", "George", "0742356567", "Rozelor 12");
        controllerCandidat.save("2", "Mihai", "0742356111", "Vulturilor 1");
        controllerCandidat.save("3", "Maria", "0742356222", "Soimilor 56");
        controllerCandidat.save("4", "Calin", "0752356567", "Rozelor 12");
        controllerCandidat.save("5", "Giovanni", "0742356111", "Vulturilor 1");
        controllerCandidat.save("6", "Mara", "0752356222", "Soimilor 56");
        controllerCandidat.save("7", "Sergiu", "0788356222", "Soimilor 56");

        List<Candidat> list;

        list = controllerCandidat.sortCandidatiByDenumire();
        assertTrue(list.size() == 7);

        Candidat c1 = list.get(0);
        assertTrue(c1.getNume().equals("Calin"));

        c1 = list.get(6);
        assertTrue(c1.getNume().equals("Sergiu"));
    }

    @Test
    public void filtrariSortariCandidati() throws Exception {
        controllerCandidat.save("1", "George", "0742356567", "Rozelor 12");
        controllerCandidat.save("2", "Mihai", "0742356111", "Vulturilor 1");
        controllerCandidat.save("3", "Mara", "0742356222", "Soimilor 56");
        controllerCandidat.save("4", "Calin", "0752356567", "Rozelor 12");
        controllerCandidat.save("5", "George", "0742356111", "Vulturilor 1");
        controllerCandidat.save("6", "Mara", "0752356222", "Soimilor 56");
        controllerCandidat.save("7", "Sergiu", "0788356222", "Soimilor 56");

        //controllerCandidat.sortCandidatiByDenumire().forEach(x -> System.out.println(x.getNume()));

        //filtreaza dupa nume si sorteaza dupa ID
        List<Candidat> lista;
        lista = controllerCandidat.filterCandidatiByDenumire("Mara");
        assertTrue(lista.size() == 2);
        lista = controllerCandidat.sortGeneric(lista, (x, y)->x.getId().compareTo(y.getId()));

        Candidat c1 = lista.get(0);
        assertTrue(c1.getId() == 3);
        c1 = lista.get(1);
        assertTrue(c1.getId() == 6);


        //filtreaza dupa telefon si sorteaza dupa nume
        lista = controllerCandidat.filterCandidatiByTelefon("074");
        assertTrue(lista.size() == 4);
        lista = controllerCandidat.sortGeneric(lista, (x, y)->x.getNume().compareTo(y.getNume()));
        c1 = lista.get(0);
        assertTrue(c1.getNume().equals("George"));
        c1 = lista.get(3);
        assertTrue(c1.getNume().equals("Mihai"));

        //filtreaza dupa telefon si sorteaza dupa nume descrescator si id crescator
        lista = controllerCandidat.filterCandidatiByTelefon("074");
        assertTrue(lista.size() == 4);
        lista = controllerCandidat.sortGeneric(lista, (x, y)->{
            if (x.getNume().equals(y.getNume())){
                return x.getId().compareTo(y.getId());
            }
            return y.getNume().compareTo(x.getNume());
        });
        c1 = lista.get(0);
        assertTrue(c1.getNume().equals("Mihai"));
        c1 = lista.get(2);
        assertTrue(c1.getNume().equals("George"));
        assertTrue(c1.getId() == 1);
        c1 = lista.get(3);
        assertTrue(c1.getNume().equals("George"));
        assertTrue(c1.getId() == 5);

    }



    @Test
    public void sectieCount() throws Exception {
        assertTrue(controllerCandidat.size() == 0);
        controllerSectie.save("1", "Informatica", "200");
        assertTrue(controllerSectie.size() == 1);
    }

    @Test
    public void addSectie() throws Exception {
        controllerSectie.save("1", "Informatica", "200");
        assertTrue(controllerSectie.size() == 1);

        Sectie sectie = controllerSectie.getById("1");
        assertTrue(sectie.getId() == 1);
        assertTrue(sectie.getNume().equals("Informatica"));
        assertTrue(sectie.getNrLocuri() == 200);
    }

    @Test
    public void removeSectie() throws Exception {
        controllerSectie.save("1", "Informatica", "200");
        controllerSectie.save("2", "Matematica", "20");
        controllerSectie.save("3", "Istorie", "33");
        assertTrue(controllerSectie.size() == 3);
        controllerSectie.delete("2");
        assertTrue(controllerSectie.size() == 2);
    }

    @Test
    public void getSectieById() throws Exception {
        controllerSectie.save("1", "Informatica", "200");
        controllerSectie.save("2", "Matematica", "20");
        controllerSectie.save("3", "Istorie", "33");

        Sectie sectie = controllerSectie.getById("3");
        assertTrue(sectie.getId() == 3);
        assertTrue(sectie.getNume().equals("Istorie"));
    }

    @Test
    public void getAllSectii() throws Exception {
        controllerSectie.save("1", "Informatica", "200");
        controllerSectie.save("2", "Matematica", "20");
        controllerSectie.save("3", "Istorie", "33");

        Iterable<Sectie> sectii = controllerSectie.getAll();
        Integer nrElems = 0;
        for (Sectie ignored : sectii){
            nrElems++;
        }
        assertTrue(nrElems == 3);
    }

    @Test
    public void filterSectiiByDenumire() throws Exception {
        controllerSectie.save("1", "Informatica", "200");
        controllerSectie.save("2", "Matematica", "20");
        controllerSectie.save("3", "Istorie", "33");
        controllerSectie.save("4", "Istorie Antica", "33");
        controllerSectie.save("5", "Istorie Moderna", "33");

        List<Sectie> list = controllerSectie.filterSectiiByDenumire("Istorie");
        assertTrue(list.size() == 3);

        list = controllerSectie.filterSectiiByDenumire("Informatica");
        assertTrue(list.size() == 1);

        list = controllerSectie.filterSectiiByDenumire("Geografie");
        assertTrue(list.size() == 0);
    }

    @Test
    public void filterSectiiByLocuri() throws Exception {
        controllerSectie.save("1", "Informatica", "200");
        controllerSectie.save("2", "Matematica", "20");
        controllerSectie.save("3", "Istorie", "33");
        controllerSectie.save("4", "Istorie Antica", "78");
        controllerSectie.save("5", "Istorie Moderna", "150");

        List<Sectie> list = controllerSectie.filterSectiiByNumarLocuri(70);
        assertTrue(list.size() == 3);

        list = controllerSectie.filterSectiiByNumarLocuri(500);
        assertTrue(list.size() == 0);

        list = controllerSectie.filterSectiiByNumarLocuri(0);
        assertTrue(list.size() == 5);

        list = controllerSectie.filterSectiiByNumarLocuri(199);
        assertTrue(list.size() == 1);
    }

    @Test
    public void sortareDenumireSectii() throws Exception {
        List<Sectie> lvida;
        lvida = controllerSectie.sortSectiiByDenumire();
        assertTrue(lvida.size() == 0);

        controllerSectie.save("1", "Informatica", "200");
        controllerSectie.save("2", "Matematica", "20");
        controllerSectie.save("3", "Istorie", "33");
        controllerSectie.save("4", "Istorie Antica", "78");
        controllerSectie.save("5", "Istorie Moderna", "150");

        List<Sectie> list = new ArrayList<>();
        list = controllerSectie.sortSectiiByDenumire();
        Sectie s1 = list.get(0);
        assertTrue(s1.getNume().equals("Informatica"));
        s1 = list.get(4);
        assertTrue(s1.getNume().equals("Matematica"));
    }

    @Test
    public void filtrariSortariSectii() throws Exception {
        controllerSectie.save("1", "Informatica", "200");
        controllerSectie.save("2", "Matematica", "20");
        controllerSectie.save("3", "Istorie", "33");
        controllerSectie.save("4", "Istorie Antica", "220");
        controllerSectie.save("5", "Istorie Moderna", "150");
        controllerSectie.save("6", "Matematica Aplicata", "150");

        //Filtreaza dupa denumire si sorteaza dupa numarul de locuri
        List<Sectie> list;
        list = controllerSectie.filterSectiiByDenumire("Istorie");
        assertTrue(list.size() == 3);
        list = controllerSectie.sortGeneric(list, (x, y)->x.getNrLocuri().compareTo(y.getNrLocuri()));
        Sectie s1 = list.get(0);
        assertTrue(s1.getNume().equals("Istorie"));
        s1 = list.get(2);
        assertTrue(s1.getNume().equals("Istorie Antica"));

        //Filtreaza dupa numarul de locuri si sorteaza dupa nume
        list = controllerSectie.filterSectiiByNumarLocuri(100);
        assertTrue(list.size() == 4);
        list = controllerSectie.sortGeneric(list, (x, y)->x.getNume().compareTo(y.getNume()));
        s1 = list.get(0);
        assertTrue(s1.getNume().equals("Informatica"));
        s1 = list.get(1);
        assertTrue(s1.getNume().equals("Istorie Antica"));

        //Filtreaza dupa numarul de locuri si sorteaza dupa nr de locuri si dupa nume descrescator
        list = controllerSectie.filterSectiiByNumarLocuri(100);
        assertTrue(list.size() == 4);
        list = controllerSectie.sortGeneric(list, (x, y)->{
            if (x.getNrLocuri().equals(y.getNrLocuri())){
                return y.getNume().compareTo(x.getNume());
            }
            return x.getNrLocuri().compareTo(y.getNrLocuri());
        });
        s1 = list.get(0);
        assertTrue(s1.getNume().equals("Matematica Aplicata"));
        s1 = list.get(1);
        assertTrue(s1.getNume().equals("Istorie Moderna"));
        s1 = list.get(2);
        assertTrue(s1.getNume().equals("Informatica"));
    }

    @Test
    public void clearAllCandidati() throws Exception {
        controllerCandidat.save("1", "George", "0742356567", "Rozelor 12");
        controllerCandidat.save("2", "Mihai", "0742356111", "Vulturilor 1");
        controllerCandidat.save("3", "Mara", "0742356222", "Soimilor 56");
        controllerCandidat.save("4", "Calin", "0752356567", "Rozelor 12");
        assertTrue(controllerCandidat.size() == 4);
        controllerCandidat.clearAll();
        assertTrue(controllerCandidat.size() == 0);
    }

    @Test
    public void clearAllSectii() throws Exception {
        controllerSectie.save("1", "Informatica", "200");
        controllerSectie.save("2", "Matematica", "20");
        controllerSectie.save("3", "Istorie", "33");
        controllerSectie.save("4", "Istorie Antica", "220");
        assertTrue(controllerSectie.size() == 4);
        controllerSectie.clearAll();
        assertTrue(controllerSectie.size() == 0);
    }
}
