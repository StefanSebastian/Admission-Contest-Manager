package Validator;

/**
 * Created by Sebi on 10/18/2016.
 */
public interface IValidator<E> {
    /*
    Valideaza o entitate
    Arunca ValidatorException daca entitatea nu e valida
     */
    public void validate(E entity) throws ValidatorException;
}
