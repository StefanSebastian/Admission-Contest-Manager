using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace contest.Validation
{
    public class DepartmentFormatValidator
    {
        public static void validateFormat(string id, string name, string nrPlaces)
        {
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
    }
}
