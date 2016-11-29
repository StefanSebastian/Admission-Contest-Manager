package Controller;

import Domain.Sectie;
import Repository.IRepository;
import Repository.RepositorySectie;
import Utils.Observer;
import Validator.ControllerException;

import java.util.List;
import java.util.function.Predicate;

/**
 * Created by Sebi on 25-Nov-16.
 */
public class ControllerSectie extends AbstractController<Sectie, Integer> {

    public ControllerSectie(IRepository repositorySectie){
        super(repositorySectie);
    }

    @Override
    public Sectie formatElement(String... args) throws ControllerException {
        if (args.length != 3){
            throw new ControllerException("Numar invalid de parametri");
        }

        Integer id;
        Integer nrLocuri;
        try{
            id = Integer.parseInt(args[0]);
        } catch (NumberFormatException exc){
            throw new ControllerException("Id trebuie sa fie numar");
        }
        try{
            nrLocuri = Integer.parseInt(args[2]);
        } catch (NumberFormatException exc){
            throw  new ControllerException("Numarul de locuri trebuie sa fie numar");
        }
        return new Sectie(id, args[1], nrLocuri);
    }

    @Override
    public Integer formatId(String id) throws ControllerException {
        Integer idSectie;
        try{
            idSectie = Integer.parseInt(id);
        } catch (NumberFormatException exc){
            throw new ControllerException("Id-ul trebuie sa fie numar");
        }
        return idSectie;
    }

    /*
   Returneaza sectiile al caror nume contine sirul dat
    */
    public List<Sectie> filterSectiiByDenumire(String sir){
        Predicate<Sectie> denumireContine = x -> x.getNume().contains(sir);
        return filterGeneric(repository.getAll(), denumireContine);
    }

    /*
    Returneaza sectiile cu numar de locuri mai mare decat cel dat
     */
    public List<Sectie> filterSectiiByNumarLocuri(Integer nrLocuri){
        Predicate<Sectie> maiMulteLocuri = x -> x.getNrLocuri() > nrLocuri;
        return filterGeneric(repository.getAll(), maiMulteLocuri);
    }

    /*
    Sorteaza sectiile dupa denumire
     */
    public List<Sectie> sortSectiiByDenumire(){
        return sortGeneric(repository.getAll(), (x, y) -> x.getNume().compareTo(y.getNume()));
    }

    /*
    Adauga un observer repository sectii
     */
    public void addObserver(Observer E){
        repository.addObserver(E);
    }
}
