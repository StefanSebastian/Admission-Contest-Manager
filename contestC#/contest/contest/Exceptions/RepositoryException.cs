using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace contest.Exceptions
{
    /*
     * Exception thrown by Repository
     */
    public class RepositoryException : Exception
    {
        /*
         * Gets a message in constructor
         */
         public RepositoryException(string message) : base(message)
        {

        }
    }
}
