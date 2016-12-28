using contest.Domain;
using contest.Validation;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace contest.Repository
{
    public abstract class AbstractFileRepository<E, ID> : AbstractRepository<E, ID>, IFileRepository<E, ID>
        where E : HasID<ID>
        where ID : IComparable<ID>
    {
        //name of the file
        protected string fileName;

        //from IFileRepository
        public abstract void readFromFile();
        public abstract void writeToFile();

        public AbstractFileRepository(Validator<E> validator, string fileName) : base(validator)
        {
            this.fileName = fileName;
            readFromFile(); //loads initial data
        }

        /*
         * Saves entity without updating file 
         */
        public void saveInMemory(E entity)
        {
            base.add(entity);
        }

        /*
         * Writes changes to file
         */
        public override void add(E entity)
        {
            base.add(entity);
            writeToFile();
        }

        public override void delete(ID id)
        {
            base.delete(id);
            writeToFile();
        }

        public override void update(ID id, E newEntity)
        {
            base.update(id, newEntity);
            writeToFile();
        }
    }
}
