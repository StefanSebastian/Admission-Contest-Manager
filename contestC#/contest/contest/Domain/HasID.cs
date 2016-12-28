using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace contest.Domain
{
    /*
     * Interface for entities which have an id 
     */
    public interface HasID<ID>
    {
        ID Id { get; }
    }
}
