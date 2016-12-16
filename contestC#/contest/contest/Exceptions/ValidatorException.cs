using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace contest.Validation
{
    /*
     * Exception thrown by validator 
     */
    public class ValidatorException : Exception
    {
        /*
         * Gets a message in constructor
         */
        public ValidatorException(string message) : base(message)
        {
        }
    }
}
