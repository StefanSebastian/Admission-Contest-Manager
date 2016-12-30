using contest.Utils.Pair;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace contest.Domain
{
    public class Option : HasID<Pair<int, int>>
    {
        //attributes
        Pair<int, int> id;
        private int idCandidate;
        private int idDepartment;

        //getters and setters
        public Pair<int, int> Id
        {
            get
            {
                return id;
            }
        }

        public int IdCandidate
        {
            get
            {
                return idCandidate;
            }

            set
            {
                idCandidate = value;
            }
        }

        public int IdDepartment
        {
            get
            {
                return idDepartment;
            }

            set
            {
                idDepartment = value;
            }
        }

        //Constructor 
        public Option(int idCandidate, int idDepartment)
        {
            this.idCandidate = idCandidate;
            this.idDepartment = idDepartment;
            this.id = new Pair<int, int>(idCandidate, idDepartment);
        }

        //To string method 
        public override string ToString()
        {
            return "Candidate id " + id.First + " with option id " + id.Second;
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
