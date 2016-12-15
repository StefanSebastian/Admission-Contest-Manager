package Utils;

/**
 * Created by Sebi on 15-Nov-16.
 */
public interface Observer<E> {
    /*
    Called by observable when the state changes
     */
    void update();

    /*
    Called by observable when the state changes
     */
    void pushUpdate(E e);
}
