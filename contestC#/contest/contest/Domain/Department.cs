using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace contest.Domain
{
    public class Department : HasID<int>
    {
        //attributes
        private int id;
        private string name;
        private int numberOfPlaces;

        //getters and setters
        public int Id
        {
            get
            {
                return id;
            }

            set
            {
                id = value;
            }
        }

        public string Name
        {
            get
            {
                return name;
            }

            set
            {
                name = value;
            }
        }

        public int NumberOfPlaces
        {
            get
            {
                return numberOfPlaces;
            }

            set
            {
                numberOfPlaces = value;
            }
        }

        public string IdName => $"{Id} {Name}"; //read only property

        //Constructor
        public Department(int id, string name, int numberOfPlaces)
        {
            this.id = id;
            this.name = name;
            this.numberOfPlaces = numberOfPlaces;
        }

        //to string method
        public override string ToString()
        {
            return id + " " + name + " " + numberOfPlaces;
        }

        //equals method
        public override bool Equals(object obj)
        {
            if (obj == null)
            {
                return false;
            }
            if (!(obj is Department))
            {
                return false;
            }
            Department d = (Department)obj;
            return id == d.id;
        }

        public override int GetHashCode()
        {
            return base.GetHashCode();
        }
    }
}
