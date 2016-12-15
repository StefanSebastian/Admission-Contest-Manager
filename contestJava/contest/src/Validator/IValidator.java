package Validator;

/**
 * Created by Sebi on 10/18/2016.
 */
public interface IValidator<E> {
    /*
    Validates an entity
    Throws ValidatorException if entity is not valid
     */
    public void validate(E entity) throws ValidatorException;
}
