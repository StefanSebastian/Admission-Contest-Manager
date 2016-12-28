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
    public class DepartmentFileRepository : AbstractFileRepository<Department, int>
    {
        /*
         * Constructor
         */
        public DepartmentFileRepository(Validator<Department> validator, string fileName) : base(validator, fileName)
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
                if (tokens.Length == 3)
                {
                    DepartmentFormatValidator.validateFormat(tokens[0], tokens[1], tokens[2]);
                    Department department = new Department(int.Parse(tokens[0]), tokens[1], int.Parse(tokens[2]));
                    base.saveInMemory(department);
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
            foreach (Department d in getAll())
            {
                writer.WriteLine(d.Id + "#" + d.Name + "#" + d.NumberOfPlaces);
            }
            writer.Close();
        }
    }
}
