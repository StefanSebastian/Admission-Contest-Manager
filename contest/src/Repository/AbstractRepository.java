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
    Validator pentru entitate
     */
    private IValidator<E> validator;

    public AbstractRepository(IValidator<E> validator){
        this.validator = validator;
    }

    /*
    Lista de elemente din repository
     */
    protected List<E> entities = new ArrayList<E>();

    @Override
    public int size(){
        return entities.size();
    }

    @Override
    public void save(E entity) throws RepositoryException, ValidatorException {
        for (E ent : entities){
            if (ent.getId().equals(entity.getId())){
                throw new RepositoryException("Acest ID apare deja!");
            }
        }
        validator.validate(entity);
        entities.add(entity);
        notifyObservers();
    }

    @Override
    public void update(ID id, E newEntity) throws RepositoryException, ValidatorException {
        if (entities.stream().filter(x -> x.getId().equals(id)).count() != 1){
            throw new RepositoryException("Id-ul initial nu e valid!");
        }
        long count = entities.stream()
                .filter(x -> x.getId().equals(newEntity.getId()) && !x.getId().equals(id))
                .count();
        if (count != 0){
            throw new RepositoryException("Id-ul nou apare deja!");
        }
        validator.validate(newEntity);
        this.delete(id);
        entities.add(newEntity);
        notifyObservers();
    }

    @Override
    public void delete(ID id) throws RepositoryException {
        long idCount = entities.stream().filter(x -> x.getId().equals(id)).count();
        if (idCount == 0) {
            throw new RepositoryException("Acest ID nu apare in repository!");
        }
        entities.removeIf(e -> e.getId().equals(id));
        notifyObservers();
    }

    @Override
    public E getById(ID id){
        for (E entity : entities){
            if (entity.getId().equals(id)){
                return entity;
            }
        }
        return null;
    }

    @Override
    public List<E> getAll(){
        return entities;
    }

    @Override
    public void clearAll(){
        entities.clear();
        notifyObservers();
    }

}
