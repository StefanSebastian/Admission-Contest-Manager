using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace contest.Utils.Observer
{
    public class AbstractObservable : Observable
    {
        /*
         * Observer list 
         */
        List<Observer> observers = new List<Observer>();

        /*
         * Adds an observer
         */
        public void addObserver(Observer o)
        {
            observers.Add(o);
        }

        /*
         * Notifies all observers
         */
        public void notifyObservers()
        {
            foreach (Observer o in observers)
            {
                o.update();
            }
        }

        /*
         * Removes an observer
         */
        public void removeObserver(Observer o)
        {
            observers.Remove(o);
        }
    }
}
