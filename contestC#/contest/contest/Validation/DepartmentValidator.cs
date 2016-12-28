using contest.Domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace contest.Validation
{
    /*
     * Validates a department entity
     */
    public class DepartmentValidator : Validator<Department>
    {
        public void validate(Department department)
        {
            string errors = "";
            if (department == null)
            {
                throw new ValidatorException("Department is null pointer");
            }
            if (department.Id <= 0)
            {
                errors += "Id must be a positive integer\n";
            }
            if (department.Name.Length == 0)
            {
                errors += "Name must not be empty.\n";
            }
            if (!department.Name.All(c => (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == ' '))
            {
                errors += "Name must contain only letters.\n";
            }
            if (department.NumberOfPlaces < 0)
            {
                errors += "Number of places must be a positive integer.\n";
            }
            if (errors.Length != 0)
            {
                throw new ValidatorException(errors);
            }
        }
    }
}
