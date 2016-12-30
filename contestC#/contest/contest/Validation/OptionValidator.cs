using contest.Domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace contest.Validation
{
    /*
     * Validates an option entity
     */
    public class OptionValidator : Validator<Option>
    {
        public void validate(Option option)
        {
            string errors = "";
            if (option == null)
            {
                throw new ValidatorException("Option is null pointer");
            }
            if (errors.Length != 0)
            {
                throw new ValidatorException(errors);
            }
        }
    }
}
