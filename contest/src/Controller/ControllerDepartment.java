package Controller;

import Domain.Department;
import Repository.IRepository;
import Utils.Observer;
import Validator.ControllerException;

import java.util.List;
import java.util.function.Predicate;

/**
 * Created by Sebi on 25-Nov-16.
 */
public class ControllerDepartment extends AbstractController<Department, Integer> {
    /*
    Constructor
     */
    public ControllerDepartment(IRepository repositoryDepartment){
        super(repositoryDepartment);
    }

    @Override
    public Department formatElement(String... args) throws ControllerException {
        if (args.length != 3){
            throw new ControllerException("Invalid number of parameters");
        }

        Integer id;
        Integer nrPlaces;
        try{
            id = Integer.parseInt(args[0]);
        } catch (NumberFormatException exc){
            throw new ControllerException("Id must be a number");
        }
        try{
            nrPlaces = Integer.parseInt(args[2]);
        } catch (NumberFormatException exc){
            throw  new ControllerException("Number of places must be a number");
        }
        return new Department(id, args[1], nrPlaces);
    }

    @Override
    public Integer formatId(String id) throws ControllerException {
        Integer idDepartment;
        try{
            idDepartment = Integer.parseInt(id);
        } catch (NumberFormatException exc){
            throw new ControllerException("Id must be a number");
        }
        return idDepartment;
    }

    /*
   Returns departments whose name contain the given string
    */
    public List<Department> filterDepartmentsByName(String sir){
        Predicate<Department> nameContains = x -> x.getName().contains(sir);
        return filterGeneric(repository.getAll(), nameContains);
    }

    /*
    Returns departments with number of places greater than the given one
     */
    public List<Department> filterDepartmentsByNumberOfPlaces(Integer nrPlaces){
        Predicate<Department> morePlaces = x -> x.getNumberOfPlaces() > nrPlaces;
        return filterGeneric(repository.getAll(), morePlaces);
    }

    /*
    Sorts departments by name
     */
    public List<Department> sortDepartmentsByName(){
        return sortGeneric(repository.getAll(), (x, y) -> x.getName().compareTo(y.getName()));
    }

    /*
    Adds an observer for departments
     */
    public void addObserver(Observer E){
        repository.addObserver(E);
    }
}
