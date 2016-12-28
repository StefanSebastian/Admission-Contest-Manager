using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace contest.Domain
{
    public class Option : HasID<Tuple<int, int>>
    {
        //attributes
        Tuple<int, int> id;
        private Candidate candidate;
        private Department department;

        //getters and setters
        public Candidate Candidate
        {
            get
            {
                return candidate;
            }

            set
            {
                candidate = value;
            }
        }

        public Department Department
        {
            get
            {
                return department;
            }

            set
            {
                department = value;
            }
        }

        public Tuple<int, int> Id
        {
            get
            {
                return id;
            }
        }

        //Constructor 
        public Option(Candidate candidate, Department department)
        {
            this.candidate = candidate;
            this.department = department;
            if (candidate != null && department != null)
            {
                this.id = new Tuple<int, int>(candidate.Id, department.Id);
            }
        }

        //To string method 
        public override string ToString()
        {
            return "Candidate id " + id.Item1 + " with option id " + id.Item2;
        }

        //Equals method 
        public override bool Equals(object obj)
        {
            if (obj == null)
            {
                return false;
            }
            if (!(obj is Option))
            {
                return false;
            }
            Option o = (Option)obj;
            return id.Equals(o.id);
        }
    }
}
