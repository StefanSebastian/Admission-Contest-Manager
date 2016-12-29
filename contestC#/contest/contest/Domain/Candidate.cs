using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace contest.Domain
{
    public class Candidate : HasID<int>
    {
        //attributes
        private int id;
        private string name;
        private string telephone;
        private string address;

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

        public string Telephone
        {
            get
            {
                return telephone;
            }
            set
            {
                telephone = value;
            }
        }

        public string Address
        {
            get
            {
                return address;
            }
            set
            {
                address = value;
            }
        }

        public string IdName => $"{Id} {Name}"; //read only property
        
        //Constructor 
        public Candidate(int id, string name, string telephone, string address)
        {
            this.id = id;
            this.name = name;
            this.telephone = telephone;
            this.address = address;
        }

        //to string method 
        public override string ToString()
        {
            return id + " " + name + " " + telephone + " " + address;
        }

        //equals method
        public override bool Equals(object obj)
        {
            if (obj == null)
            {
                return false;
            }
            if (!(obj is Candidate))
            {
                return false;
            }
            Candidate c = (Candidate)obj;
            return id == c.id;
        }
    }
}
