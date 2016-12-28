using contest.Domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace contest.Validation
{
    /*
     * Validates a candidate entity
     */
    public class CandidateValidator : Validator<Candidate>
    {
        public void validate(Candidate candidate)
        {
            string errors = "";
            if (candidate == null)
            {
                throw new ValidatorException("Candidate is null pointer");
            }
            if (candidate.Id <= 0)
            {
                errors += "Id must be a positive integer\n";
            }
            if (candidate.Name.Length == 0)
            {
                errors += "Name must not be empty.\n";
            }
            if (candidate.Telephone.Length == 0)
            {
                errors += "Telephone must not be empty.\n";
            }
            if (candidate.Address.Length == 0)
            {
                errors += "Address must not be empty.\n";
            }
            if (candidate.Telephone.Length != 10)
            {
                errors += "Telephone must have 10 digits.\n";
            }
            if (!candidate.Telephone.All(c => c >= '0' && c <= '9'))
            {
                errors += "Telephone must contain only digits.\n";
            }
            if (!candidate.Name.All(c => (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c == ' ')))
            {
                errors += "Name must contain only letters.\n";
            }
            if (errors.Length != 0)
            {
                throw new ValidatorException(errors);
            }
        }
    }
}
