package Repository;

import Utils.AbstractObservable;
import Utils.Observable;
import Validator.RepositoryException;
import Validator.ValidatorException;

import java.util.List;

/**
 * Created by Sebi on 10/16/2016.
 */
public interface IRepository<E, ID> extends Observable<E> {
    /*
    Returns the number of elements stored in Repository
     */
    int size();

    /*
    Saves the given entity in the Repository
    Throws RepositoryException if the ID is already found in repository
     */
    void save(E entity) throws RepositoryException, ValidatorException;

    /*
    Deletes the entity that has the given ID
    Throws RepositoryException if the ID is not found in repository
     */
    void delete(ID id) throws RepositoryException;

    /*
    Updates the entity with the given ID
     */
    void update(ID id, E newEntity) throws RepositoryException, ValidatorException;

    /*
    Returns the entity that has the given ID
    returns null if the ID is not found
     */
    E getById(ID id);

    /*
    Returns all entities
     */
    List<E> getAll();

    /*
    Clears all enttities
     */
    void clearAll();

}
