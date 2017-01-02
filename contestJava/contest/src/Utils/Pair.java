package Utils;

/**
 * Created by Sebi on 02-Jan-17.
 */
public class Pair<E, T> {//holds a pair of elements
    private E first;
    private T second;

    public Pair(E first, T second){
        this.first = first;
        this.second = second;
    }

    public void setFirst(E first){
        this.first = first;
    }

    public E getFirst(){
        return first;
    }

    public T getSecond() {
        return second;
    }

    public void setSecond(T second) {
        this.second = second;
    }
}
