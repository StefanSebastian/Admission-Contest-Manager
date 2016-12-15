package Validator;

/**
 * Created by Sebi on 10/28/2016.
 */
public class ValidatorException extends Exception {
    /*
    Constructor - gets a message
    thrown by validator objects
     */
    public ValidatorException(String message){
        super(message);
    }
}
