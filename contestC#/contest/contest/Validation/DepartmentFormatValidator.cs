using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace contest.Validation
{
    public class DepartmentFormatValidator
    {
        public static void validateFormat(params string[] fields)
        {
            if (fields.Count() != 3)
            {
                throw new ValidatorException("Invalid number of parameters for candidate.");
            }
            string id = fields[0];
            string nrPlaces = fields[2];
            try
            {
                int idInt = int.Parse(id);
                int nrPlacesInt = int.Parse(nrPlaces);
            }
            catch (FormatException exc)
            {
                throw new ValidatorException("Invalid format.");
            }
            catch (ArgumentNullException exc)
            {
                throw new ValidatorException("Invalid format.");
            }
        }

        public static void validateId(string id)
        {
            try
            {
                int idInt = int.Parse(id);
            }
            catch (FormatException exc)
            {
                throw new ValidatorException("Invalid format.");
            }
            catch (ArgumentNullException exc)
            {
                throw new ValidatorException("Invalid format.");
            }
        }
    }
}
