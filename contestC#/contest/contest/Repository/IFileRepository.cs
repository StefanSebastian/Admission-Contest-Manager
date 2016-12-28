using contest.Domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace contest.Repository
{
    interface IFileRepository<E, ID> : IRepository<E, ID> where E : HasID<ID>
                                                          where ID : IComparable<ID>
    {
        //reads all entities from a file 
        void readFromFile();

        //saves all entities to a file
        void writeToFile();
    }
}
