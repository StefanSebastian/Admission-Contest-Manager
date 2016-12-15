package Validator;

/**
 * Created by Sebi on 30-Nov-16.
 */
public class InvalidSelectionException extends Exception {
/*
    Constructor - gets a message
    thrown by gui objects
     */
    public InvalidSelectionException(String message){
        super(message);
    }
}
