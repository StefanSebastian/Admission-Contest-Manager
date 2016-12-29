using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace contest.Utils.Observer
{
    /*
     * Observable interface 
     */
    public interface Observable
    {
        void notifyObservers();
        void addObserver(Observer o);
        void removeObserver(Observer o);
    }
}
