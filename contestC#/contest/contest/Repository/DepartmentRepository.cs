using contest.Domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using contest.Validation;

namespace contest.Repository
{
    /*
     * Repository in memory for Departments
     */
    public class DepartmentRepository : AbstractRepository<Department, int>
    {
        /*
         * Constructor
         */
        public DepartmentRepository(DepartmentValidator validator) : base(validator)
        {
        }
    }
}
