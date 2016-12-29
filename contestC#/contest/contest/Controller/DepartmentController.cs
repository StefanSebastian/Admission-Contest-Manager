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

        /*
         * Filters the departments whose names contain the given string
         */
         public List<Department> nameContains(string name)
        {
            Predicate<Department> filter = (x) => x.Name.Contains(name);
            return genericFilter(getAll(), filter);
        }

        /*
         * Filters the departments with a number of places greater than the given number
         */
         public List<Department> numberOfPlacesGreaterThan(int number)
        {
            Predicate<Department> filter = (x) => x.NumberOfPlaces >= number;
            return genericFilter(getAll(), filter);
        }
    }
}
