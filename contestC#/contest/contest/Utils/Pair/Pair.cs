using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace contest.Utils.Pair
{
    /*
     * Contains two comparable elements
     */
    public class Pair<E1, E2> : IComparable<Pair<E1, E2>> where E1 : IComparable<E1>
                                                          where E2 : IComparable<E2>
    {
        private E1 first;
        private E2 second;

        public Pair(E1 e1, E2 e2)
        {
            this.First = e1;
            this.Second = e2;
        }

        public E1 First
        {
            get
            {
                return first;
            }

            set
            {
                first = value;
            }
        }

        public E2 Second
        {
            get
            {
                return second;
            }

            set
            {
                second = value;
            }
        }

        public override string ToString()
        {
            return first + " " + second;
        }

        public override bool Equals(object obj)
        {
            if (obj == null)
            {
                return false;
            }
            if (!(obj is Pair<E1, E2>))
            {
                return false;
            }
            Pair<E1, E2> p = (Pair<E1, E2>)obj;
            return p.First.Equals(this.First) && p.Second.Equals(this.Second);
        }

        public int CompareTo(Pair<E1, E2> other)
        {
            if (this.First.CompareTo(other.First) == 0)
            {
                return this.Second.CompareTo(other.Second);
            }
            return this.First.CompareTo(other.First);
        }
    }
}
