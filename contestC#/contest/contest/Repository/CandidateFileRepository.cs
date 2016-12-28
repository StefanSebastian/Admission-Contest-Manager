using contest.Domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using contest.Validation;
using System.IO;

namespace contest.Repository
{
    public class CandidateFileRepository : AbstractFileRepository<Candidate, int>
    {
        /*
         * Constructor
         */
        public CandidateFileRepository(CandidateValidator validator, string fileName) : base(validator, fileName)
        {
        }

        /*
         * Reads from file
         */
        public override void readFromFile()
        {
            StreamReader reader = new StreamReader(fileName);
            while (!reader.EndOfStream)
            {
                String line = reader.ReadLine();
                String[] tokens = line.Split('#');
                if (tokens.Length == 4)
                {
                    CandidateFormatValidator.validateFormat(tokens[0], tokens[1], tokens[2], tokens[3]);
                    Candidate candidate = new Candidate(int.Parse(tokens[0]), tokens[1], tokens[2], tokens[3]);
                    base.saveInMemory(candidate);
                }
            }
            reader.Close();
        }

        /*
         * Writes to file
         */
        public override void writeToFile()
        {
            StreamWriter writer = new StreamWriter(fileName);
            foreach (Candidate c in getAll())
            {
                writer.WriteLine(c.Id + "#" + c.Name + "#" + c.Telephone + "#" + c.Address);
            }
            writer.Close();
        }
    }
}
