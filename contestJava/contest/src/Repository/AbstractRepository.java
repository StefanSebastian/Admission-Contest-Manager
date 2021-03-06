package Repository;

import Domain.HasId;
import Utils.AbstractObservable;
import Utils.Observer;
import Validator.IValidator;
import Validator.RepositoryException;
import Validator.ValidatorException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sebi on 10/16/2016.
 */
public abstract class AbstractRepository<E extends HasId<ID>, ID extends Comparable<ID>> extends AbstractObservable<E> implements IRepository<E, ID> {
    /*
    Entity validator
     */
    private IValidator<E> validator;

    /*
    Constructor - gets a validator
     */
    public AbstractRepository(IValidator<E> validator){
        this.validator = validator;
    }

    /*
    List of entities from repository
     */
    protected List<E> entities = new ArrayList<E>();

    /*
    Returns the number of entities in repository
     */
    @Override
    public int size(){
        return entities.size();
    }

    /*
    Saves an entity in repository
     */
    @Override
    public void save(E entity) throws RepositoryException, ValidatorException {
        for (E ent : entities){
            if (ent.getId().equals(entity.getId())){
                throw new RepositoryException("This id is already in repository");
            }
        }
        validator.validate(entity);
        entities.add(entity);
        notifyObservers();
    }

    /*
    Updates an entity in repository
     */
    @Override
    public void update(ID id, E newEntity) throws RepositoryException, ValidatorException {
        if (entities.stream().filter(x -> x.getId().equals(id)).count() != 1){
            throw new RepositoryException("The initial id is not valid");
        }
        if (!id.equals(newEntity.getId())){
            throw new RepositoryException("You can't change the id");
        }
        validator.validate(newEntity);
        entities.removeIf(x -> x.getId().equals(id));
        entities.add(newEntity);
        notifyObservers();
    }

    /*
    Deletes an entity from repository
     */
    @Override
    public void delete(ID id) throws RepositoryException {
        long idCount = entities.stream().filter(x -> x.getId().equals(id)).count();
        if (idCount == 0) {
            throw new RepositoryException("This id is not valid");
        }
        entities.removeIf(e -> e.getId().equals(id));
        notifyObservers();
    }

    /*
    Returns the entity with the given id, if found in repository
     */
    @Override
    public E getById(ID id){
        for (E entity : entities){
            if (entity.getId().equals(id)){
                return entity;
            }
        }
        return null;
    }

    /*
    Returns all entities from repository
     */
    @Override
    public List<E> getAll(){
        return entities;
    }

    /*
    Removes all entities from repository
     */
    @Override
    public void clearAll(){
        entities.clear();
        notifyObservers();
    }

}
