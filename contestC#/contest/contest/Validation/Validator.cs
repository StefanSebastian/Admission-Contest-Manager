using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace contest.Validation
{
    /*
     * Validator interface - validates an entity 
     * Throws Validator exception 
     */
    interface Validator<E>
    {
        void validate(E entity);
    }
}
