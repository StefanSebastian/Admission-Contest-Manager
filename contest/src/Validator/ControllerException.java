package Validator;

/**
 * Created by Sebi on 25-Nov-16.
 */
public class ControllerException extends Exception {
    /*
    Constructor - gets a message
    thrown by controller object
     */
    public ControllerException(String message){
        super(message);
    }
}
