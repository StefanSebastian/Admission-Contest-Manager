package Controller;

import Domain.Candidate;
import Domain.Option;
import Repository.IRepository;
import Utils.Observer;
import Validator.ControllerException;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Sebi on 30-Nov-16.
 */
public class ControllerOption extends AbstractController<Option, Integer> {
    /*
    Constructor
     */
    public ControllerOption(IRepository repositoryOption){
        super(repositoryOption);
    }

    @Override
    public Option formatElement(String... args) throws ControllerException {
        if (args.length != 3){
            throw new ControllerException("Invalid number of parameters");
        }

        Integer id, idCandidate, idDepartment;
        try{
            id = Integer.parseInt(args[0]);
            idCandidate = Integer.parseInt(args[1]);
            idDepartment = Integer.parseInt(args[2]);
        } catch (NumberFormatException e){
            throw new ControllerException("Id must be a number");
        }
        return new Option(id, idCandidate, idDepartment);
    }

    @Override
    public Integer formatId(String id) throws ControllerException {
        Integer idOption;
        try {
            idOption = Integer.parseInt(id);
        } catch (NumberFormatException e){
            throw new ControllerException("Id must be a number");
        }
        return idOption;
    }

    /*
    Returns the distinct id's of candidates that have the given department as an option
     */
    public List<Integer> candidateIdsForDepartment(Integer departmentId){
        return getAll()
                .stream()
                .filter(x -> x.getIdDepartment().equals(departmentId))
                .map(Option::getIdCandidate)
                .distinct()
                .collect(Collectors.toList());
    }

    /*
    Adds an observer to repo list
     */
    public void addObserver(Observer E){
        repository.addObserver(E);
    }

    /*
    Gets the id of the option that has given idCandidate, idDepartment
     */
    public Integer getIdForOption(Integer idCandidate, Integer idDepartment){
        OptionalInt id =
                this.getAll()
                .stream()
                .filter(x -> x.getIdDepartment().equals(idDepartment) && x.getIdCandidate().equals(idCandidate))
                .mapToInt(Option::getId)
                .findFirst();
        if (id.isPresent()){
            return id.getAsInt();
        } else {
            return -1;
        }
    }
}
