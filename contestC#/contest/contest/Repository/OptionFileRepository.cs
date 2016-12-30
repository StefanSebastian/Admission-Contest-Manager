using contest.Domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using contest.Validation;
using contest.Utils.Pair;
using contest.Repository;
using System.IO;
using contest.Utils.Observer;
using contest.Exceptions;

namespace contest.Repository
{
    /*
     * Repository from file for Option objects
     */
    public class OptionFileRepository : AbstractFileRepository<Option, Pair<int, int>>, Observer
    {
        //department repository
        private IRepository<Department, int> departmentRepository;

        //candidate repository
        private IRepository<Candidate, int> candidateRepository;

        /*
         * Constructor
         */
        public OptionFileRepository(Validator<Option> validator, string fileName, 
            IRepository<Department, int> repositoryDepartment,
            IRepository<Candidate, int> repositoryCandidate) : base(validator, fileName)
        {
            this.candidateRepository = repositoryCandidate;
            this.departmentRepository = repositoryDepartment;

            this.candidateRepository.addObserver(this);
            this.departmentRepository.addObserver(this);
        }

        public override void add(Option entity)
        {
            //valid candidate
            if (candidateRepository.getById(entity.IdCandidate) == null)
            {
                throw new RepositoryException("Invalid candidate id.");
            }
            //valid department
            if (departmentRepository.getById(entity.IdDepartment) == null)
            {
                throw new RepositoryException("Invalid department id.");
            }

            base.add(entity);
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
                if (tokens.Length == 2)
                {
                    OptionFormatValidator.validateFormat(tokens[0], tokens[1]);
                    Option option = new Option(int.Parse(tokens[0]), int.Parse(tokens[1]));
                    base.saveInMemory(option);
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
            foreach(Option o in getAll())
            {
                writer.WriteLine(o.IdCandidate + "#" + o.IdDepartment);
            }
            writer.Close();
        }

        /*
         * Ignores updates
         */
        public void update()
        {
            throw new NotImplementedException();
        }

        public void pushUpdate(Object e)
        {
            if (e is Candidate)
            {
                Candidate deletedCandidate = (Candidate)e;

                //gets options for the deleted candidate 
                List<Option> toDelete = new List<Option>();
                toDelete = getAll().FindAll(x => x.IdCandidate.Equals(deletedCandidate.Id));

                //deletes them
                foreach (Option o in toDelete)
                {
                    delete(o.Id);
                }
            } else if (e is Department)
            {
                Department deletedDepartment = (Department)e;

                //gets the options for the deleted department
                List<Option> toDelete = new List<Option>();
                toDelete = getAll().FindAll(x => x.IdDepartment.Equals(deletedDepartment.Id));

                //deletes them
                foreach(Option o in toDelete)
                {
                    delete(o.Id);
                }
            }
        }
    }
}
