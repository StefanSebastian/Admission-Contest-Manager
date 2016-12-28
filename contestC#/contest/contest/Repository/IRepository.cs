using contest.Domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace contest.Repository
{
    /*
     * Interface for repository classes 
     */
    public interface IRepository<E, ID> where E : HasID<ID>
                                 where ID : IComparable<ID>
    {
        void add(E entity);
        void delete(ID id);
        void update(ID id, E newEntity);
        E getById(ID id);
        List<E> getAll();
    }
}
