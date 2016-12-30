using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace contest.Validation
{
    public class OptionFormatValidator
    {
        public static void validateFormat(params string[] fields)
        {
            if (fields.Count() != 2)
            {
                throw new ValidatorException("Invalid number of parameters for option.");
            }

            string id1 = fields[0];
            string id2 = fields[0];

            try
            {
                int idInt1 = int.Parse(id1);
                int idInt2 = int.Parse(id2);
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
