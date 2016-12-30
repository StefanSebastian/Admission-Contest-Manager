using contest.Domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using contest.Repository;
using contest.Utils.Pair;
using contest.Validation;

namespace contest.Controller
{
    public class OptionController : AbstractController<Option, Pair<int, int>>
    {
        /*
         * Constructor
         */
        public OptionController(IRepository<Option, Pair<int, int>> repository) : base(repository)
        {
        }

        /*
         * Formats an element
         * Throws ValidatorException if format is invalid
         */
        public override Option formatElement(params string[] fields)
        {
            OptionFormatValidator.validateFormat(fields);
            return new Option(int.Parse(fields[0]), int.Parse(fields[1]));
        }

        /*
         * Formats the id 
         * Throws ValidatorException
         */
        public override Pair<int, int> formatId(string id)
        {
            OptionFormatValidator.validateId(id);
            string[] tokens = id.Split(' ');
            return new Pair<int, int>(int.Parse(tokens[0]), int.Parse(tokens[1]));
        }
    }
}
