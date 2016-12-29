using contest.Domain;
using contest.Repository;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace contest.Controller
{
    /*
     * Abstract Controller class 
     */
    public abstract class AbstractController<E, ID> where E : HasID<ID>
                                                    where ID : IComparable<ID>
    {
        //reference to repository
        protected IRepository<E, ID> repository;

        /*
         * Constructor
         */
        public AbstractController(IRepository<E, ID> repository)
        {
            this.repository = repository;
        }

        /*
         * Adds an entity
         */
         public void add(params string[] fields)
        {
            E entity = formatElement(fields);
            repository.add(entity);
        }

        /*
         * Removes an entity
         */
         public void delete(string id)
        {
            ID idEnt = formatId(id);
            repository.delete(idEnt);
        }

        /*
         * Updates an entity 
         */
         public void update(string id, params string[] fields)
        {
            ID idEnt = formatId(id);
            E entity = formatElement(fields);
            repository.update(idEnt, entity);
        }

        /*
         * Returns the entity with the given id 
         * or null if it is not found
         */
         public E getById(string id)
        {
            ID idEnt = formatId(id);
            return repository.getById(idEnt);
        }

        /*
         * Returns all entities 
         */
         public List<E> getAll()
        {
            return repository.getAll();
        }

        /*
         * Generic filter method
         */
         public List<E> genericFilter(List<E> entities, Predicate<E> filter)
        {
            return entities.FindAll(filter);
        }

        /*
         * formats the element 
         */
        public abstract E formatElement(params string[] fields);

        /*
         * formats the id 
         */ 
        public abstract ID formatId(string id);
    }
}
