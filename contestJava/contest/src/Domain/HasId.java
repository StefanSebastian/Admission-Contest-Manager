package Domain;

import java.util.Comparator;

/**
 * Created by Sebi on 10/16/2016.
 */
public interface HasId<ID extends Comparable<ID>> {
    /*
    Returns the id
     */
    ID getId();

    /*
    Sets the id
     */
    void setId(ID id);
}
