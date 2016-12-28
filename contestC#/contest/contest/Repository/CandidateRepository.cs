using contest.Domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using contest.Validation;

namespace contest.Repository
{
    /*
     * Repository in memory for Candidates
     */
    public class CandidateRepository : AbstractRepository<Candidate, int>
    {
        /*
         * Constructor
         */
        public CandidateRepository(CandidateValidator validator) : base(validator)
        {
        }
    }
}
