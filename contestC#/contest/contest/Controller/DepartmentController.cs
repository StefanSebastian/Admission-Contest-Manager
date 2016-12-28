using contest.Domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using contest.Repository;
using contest.Validation;

namespace contest.Controller
{
    public class DepartmentController : AbstractController<Department, int>
    {
        /*
         * Constructor
         */
        public DepartmentController(IRepository<Department, int> repository) : base(repository)
        {
        }

        /*
         * Formats an element
         * Throws ValidatorException if format is invalid
         */
        public override Department formatElement(params string[] fields)
        {
            DepartmentFormatValidator.validateFormat(fields);
            return new Department(int.Parse(fields[0]), fields[1], int.Parse(fields[2]));
        }

        /*
         * Formats the id 
         * Throws ValidatorException if id is not integer
         */
        public override int formatId(string id)
        {
            DepartmentFormatValidator.validateId(id);
            return int.Parse(id);
        }
    }
}
