package Controller;

import Domain.Candidat;
import Repository.IRepository;
import Repository.RepositoryCandidat;
import Validator.ControllerException;

import java.util.List;
import java.util.function.Predicate;

/**
 * Created by Sebi on 25-Nov-16.
 */
public class ControllerCandidat extends AbstractController<Candidat, Integer> {

    public ControllerCandidat(IRepository repositoryCandidat){
        super(repositoryCandidat);
    }

    @Override
    public Candidat formatElement(String... args) throws ControllerException {
        if (args.length != 4){
            throw new ControllerException("Numar invalid de parametri");
        }

        Integer id;
        try {
            id = Integer.parseInt(args[0]);
        } catch (NumberFormatException exc){
            throw new ControllerException("Id trebuie sa fie numar");
        }
        return new Candidat(id, args[1], args[2], args[3]);
    }

    @Override
    public Integer formatId(String id) throws ControllerException {
        Integer idCandidat;
        try{
            idCandidat = Integer.parseInt(id);
        } catch (NumberFormatException exc){
            throw new ControllerException("Id-ul trebuie sa fie numar");
        }
        return idCandidat;
    }

    /*
  Returneaza toti candidatii al caror nume este egal cu cel dat
   */
    public List<Candidat> filterCandidatiByDenumire(String nume){
        Predicate<Candidat> numeEgalCuSir = x -> x.getNume().equals(nume);
        return filterGeneric(repository.getAll(), numeEgalCuSir);
    }

    /*
    Returneaza toti candidatii al caror numar de telefon incepe cu sirul dat
     */
    public List<Candidat> filterCandidatiByTelefon(String telefon){
        Predicate<Candidat> telefonIncepeCuSir = x -> x.getTelefon().startsWith(telefon);
        return filterGeneric(repository.getAll(), telefonIncepeCuSir);
    }

    /*
    Sorteaza candidatii dupa denumire
     */
    public List<Candidat> sortCandidatiByDenumire(){
        return sortGeneric(repository.getAll(), (x,y)->x.getNume().compareTo(y.getNume()));
    }
}
