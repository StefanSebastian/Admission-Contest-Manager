using contest.Domain;
using contest.Exceptions;
using contest.Utils.Observer;
using contest.Validation;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace contest.Repository
{
    /*
     * Abstract repository class 
     */
    public class AbstractRepository<E, ID> : AbstractObservable, IRepository<E, ID> where E : HasID<ID>
                                                                                       where ID : IComparable<ID>
    {
        /*
         * List of save entities 
         */
        private List<E> entities;

        /*
         * Entity validator
         */ 
        private Validator<E> validator;

        /*
         * Constructor
         */
         public AbstractRepository(Validator<E> validator)
        {
            this.validator = validator;
            entities = new List<E>();
        }

        /*
         * Saves an entity to repository
         * Throws ValidatorException if entity is not valid
         * Throws RepositoryException if it contains a duplicate id 
         */
        public virtual void add(E entity)
        {
            if (getById(entity.Id) != null)
            {
                throw new RepositoryException("This id is already in Repository.");
            }
            validator.validate(entity);
            entities.Add(entity);
            notifyObservers();
        }

        /*
         * Deletes an entity from repository
         * Does not do anything if id is not in repository
         */
        public virtual void delete(ID id)
        {
            E entity = getById(id);
            if (entity != null)
            {
                entities.Remove(entity);
                notifyObservers();
                notifyPushObservers(entity);
            }
        }

        /*
         * Returns all entities
         */ 
        public List<E> getAll()
        {
            return entities;
        }

        /*
         * Returns the entity with the given id 
         * Returns null if the entity is not found
         */
        public E getById(ID id)
        {
            int index = entities.FindIndex(x => x.Id.Equals(id));
            if (index != -1)
            {
                return entities[index];
            } else
            {
                return default(E);
            }
        }

        /*
         * Updates an entity in repository
         * Throws ValidatorException if newEntity is not valid 
         * Throws RepositoryException if original id is not found 
         * Throws RepositoryException if original id is different from new id 
         */
        public virtual void update(ID id, E newEntity)
        {
            validator.validate(newEntity);

            if (!(id.Equals(newEntity.Id)))
            {
                throw new RepositoryException("Id can not be modified.");
            }

            int index = entities.FindIndex(x => x.Id.Equals(id));
            if (index == -1)
            {
                throw new RepositoryException("The original id is not valid.");
            }

            entities[index] = newEntity;
            notifyObservers();
        }

    }
}
