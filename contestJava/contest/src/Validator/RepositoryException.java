package Validator;

/**
 * Created by Sebi on 11/2/2016.
 */
public class RepositoryException extends Exception {
    /*
    Constructor - gets a message
    thrown by repository objects
     */
    public RepositoryException(String message){
        super(message);
    }
}
