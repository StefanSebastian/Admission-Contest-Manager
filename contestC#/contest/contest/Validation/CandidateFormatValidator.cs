using contest.Domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace contest.Validation
{
    public class CandidateFormatValidator
    {
        public static void validateFormat(string id, string name, string telephone, string address)
        {
            try
            {
                int idInt = int.Parse(id);
            } catch (FormatException exc)
            {
                throw new ValidatorException("Invalid format.");
            } catch (ArgumentNullException exc)
            {
                throw new ValidatorException("Invalid format.");
            }
        }
    }
}
