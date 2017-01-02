package Controller;

import Domain.Candidate;
import Domain.Department;
import Domain.Option;
import Repository.IRepository;
import Utils.Observer;
import Utils.Pair;
import Validator.ControllerException;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Sebi on 30-Nov-16.
 */
public class ControllerOption extends AbstractController<Option, Integer> {
    //Controllers
    private ControllerCandidate controllerCandidate;
    private ControllerDepartment controllerDepartment;

    /*
    Constructor
     */
    public ControllerOption(IRepository repositoryOption, ControllerCandidate controllerCandidate,
                            ControllerDepartment controllerDepartment){
        super(repositoryOption);
        this.controllerDepartment = controllerDepartment;
        this.controllerCandidate = controllerCandidate;
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

    /*
    Returns the list of candidates that have the given department as an option
     */
    public List<Candidate> getCandidatesForDepartment(Integer departmentId){
        List<Integer> candidateIds = candidateIdsForDepartment(departmentId);
        List<Candidate> candidates = new ArrayList<>();
        for (Integer id : candidateIds){
            try {
                candidates.add(controllerCandidate.getById(id.toString()));
            } catch (ControllerException e) {
                e.printStackTrace();
            }
        }
        return candidates;
    }

    /*
    Returns top x departments
     */
    public List<Department> topXDepartments(Integer x){
        List<Department> topDepartments = new ArrayList<>();

        if (x < 0 || x > controllerDepartment.size()){ //if the number is negative or greater than total departments
            x = controllerDepartment.getAll().size();
        }

        List<Pair<Integer, Integer>> departmentCandidates = new ArrayList<>();
        //inserts id's for all departments and the initial count of 0
        for (Department d : controllerDepartment.getAll()){
            departmentCandidates.add(new Pair<>(d.getId(), 0));
        }
        //gets the number of candidates for each department
        for (Option o : getAll()){
            Pair<Integer, Integer> pair =
                    departmentCandidates.stream()
                                        .filter(p -> p.getFirst().equals(o.getIdDepartment()))
                                        .findFirst()
                                        .get();
            pair.setSecond(pair.getSecond() + 1);
        }
        //sorts the pairs
        departmentCandidates.sort((x1, y1) -> y1.getSecond().compareTo(x1.getSecond()));

        //gets only the first x
        List<Pair<Integer, Integer>> topX = departmentCandidates.subList(0, x);
        for (Pair<Integer, Integer> pair : topX){
            try {
                topDepartments.add(controllerDepartment.getById(pair.getFirst().toString()));
            } catch (ControllerException e) {
                e.printStackTrace();
            }
        }

        return topDepartments;
    }
}
