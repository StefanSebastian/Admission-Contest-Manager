package Controller;

import Repository.IRepository;
import Validator.ControllerException;
import Validator.RepositoryException;
import Validator.ValidatorException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by Sebi on 25-Nov-16.
 */
public abstract class AbstractController<E, ID> {
    protected IRepository<E, ID> repository;

    public AbstractController(IRepository repository){
        this.repository = repository;
    }

    /*
    Saves an entity
     */
    public void save(String... args) throws ControllerException, RepositoryException, ValidatorException {
        E element = formatElement(args);
        repository.save(element);
    }

    /*
    Removes an entity
     */
    public void delete(String id) throws RepositoryException, ControllerException {
        ID convertedId = formatId(id);
        repository.delete(convertedId);
    }

    /*
    Updates an entity
     */
    public void update(String id, String... newElement) throws RepositoryException, ValidatorException, ControllerException{
        ID convertedId = formatId(id);
        E convertedElement = formatElement(newElement);
        repository.update(convertedId, convertedElement);
    }

    /*
    Returns the number of elements in repository
     */
    public int size(){
        return repository.size();
    }

    /*
    Returns the entity with the given id
     */
    public E getById(String id) throws ControllerException {
        ID convertedId = formatId(id);
        return repository.getById(convertedId);
    }

    /*
    Deletes all entities
     */
    public void clearAll(){
        repository.clearAll();
    }

    /*
    Returns all entities
     */
    public List<E> getAll(){
        return repository.getAll();
    }

    /*
    Generic sort, using a comparator
    */
    public <E> List<E> sortGeneric(List<E> list, Comparator<E> comparator){
        List<E> result = new ArrayList<E>();
        list.stream().sorted(comparator).forEach(result::add);
        return result;
    }

    /*
    Generic filter, using a predicate
     */
    public <E> List<E> filterGeneric(List<E> list, Predicate<E> predicate){
        List<E> result = new ArrayList<E>();
        list.forEach(x -> { if (predicate.test(x)) result.add(x);});
        return result;
    }

    /*
    Formats the element - will be implemented by each controller
     */
    public abstract E formatElement(String... args) throws ControllerException;

    /*
    Formats the id - will be implemented by each controller
     */
    public abstract ID formatId(String id) throws ControllerException;
}
