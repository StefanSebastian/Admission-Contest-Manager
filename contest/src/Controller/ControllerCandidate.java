package Controller;

import Domain.Candidate;
import Repository.IRepository;
import Validator.ControllerException;

import java.util.List;
import java.util.function.Predicate;

/**
 * Created by Sebi on 25-Nov-16.
 */
public class ControllerCandidate extends AbstractController<Candidate, Integer> {
    /*
    Constructor
     */
    public ControllerCandidate(IRepository repositoryCandidate){
        super(repositoryCandidate);
    }

    @Override
    public Candidate formatElement(String... args) throws ControllerException {
        if (args.length != 4){
            throw new ControllerException("Invalid number of parameters");
        }

        Integer id;
        try {
            id = Integer.parseInt(args[0]);
        } catch (NumberFormatException exc){
            throw new ControllerException("Id must be a number");
        }
        return new Candidate(id, args[1], args[2], args[3]);
    }

    @Override
    public Integer formatId(String id) throws ControllerException {
        Integer idCandidate;
        try{
            idCandidate = Integer.parseInt(id);
        } catch (NumberFormatException exc){
            throw new ControllerException("Id must be a number");
        }
        return idCandidate;
    }

    /*
    Returns all candidates whose name is equal with the given string
   */
    public List<Candidate> filterCandidatesByName(String name){
        Predicate<Candidate> numeEqualsString = x -> x.getName().equals(name);
        return filterGeneric(repository.getAll(), numeEqualsString);
    }

    /*
    Returns all candidates whose telephone number starts with the given string
     */
    public List<Candidate> filterCandidatesByTelephone(String telephone){
        Predicate<Candidate> telephoneStartsWith = x -> x.getTelephone().startsWith(telephone);
        return filterGeneric(repository.getAll(), telephoneStartsWith);
    }

    /*
    Sorts candidates by name
     */
    public List<Candidate> sortCandidatesByName(){
        return sortGeneric(repository.getAll(), (x,y)->x.getName().compareTo(y.getName()));
    }
}
