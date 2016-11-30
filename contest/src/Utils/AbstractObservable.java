package Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sebi on 25-Nov-16.
 */
public abstract class AbstractObservable<E> implements Observable<E> {
    /*
    Observer list
    */
    private List<Observer> observers = new ArrayList<>();

    @Override
    public void addObserver(Observer<E> o){
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer<E> o){
        observers.remove(o);
    }

    @Override
    public void notifyObservers(){
        for (Observer<E> o : observers){
            o.update();
        }
    }
}
