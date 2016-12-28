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
    public class CandidateController : AbstractController<Candidate, int>
    {
        /*
         * Constructor
         */
        public CandidateController(IRepository<Candidate, int> repository) : base(repository)
        {
        }

        /*
         * Formats an element
         * Throws ValidatorException if format is invalid
         */
        public override Candidate formatElement(params string[] fields)
        {
            CandidateFormatValidator.validateFormat(fields[0], fields[1], fields[2], fields[3]);
            return new Candidate(int.Parse(fields[0]), fields[1], fields[2], fields[3]);
        }

        /*
         * Formats the id 
         * Throws ValidatorException if id is not integer
         */
        public override int formatId(string id)
        {
            CandidateFormatValidator.validateId(id);
            return int.Parse(id);
        }
    }
}
