package Utils;

/**
 * Created by Sebi on 15-Nov-16.
 */
public interface Observable<E> {
    /*
    Adds an observer
     */
    void addObserver(Observer<E> o);

    /*
    Removes an observer
     */
    void removeObserver(Observer<E> o);

    /*
    Notifies all observers
     */
    void notifyObservers();

    /*
    Notifies push observers
     */
    void notifyPushObservers(E e);
}
