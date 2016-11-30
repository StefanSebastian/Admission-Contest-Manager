package Validator;

import Domain.Sectie;

import java.util.Objects;

/**
 * Created by Sebi on 10/18/2016.
 */
public class ValidatorSectie implements IValidator<Sectie> {
    /*
    Validates a department
     */
    @Override
    public void validate(Sectie sectie) throws ValidatorException{
        String errors = "";

        if (sectie.getNume().isEmpty()){
            errors += "Numele sectiei nu e completat.\n";
        }

        if (sectie.getNrLocuri() < 0){
            errors += "Numarul de locuri dintr-o sectie trebuie sa fie pozitiv.\n";
        }

        if (!Objects.equals(errors, "")){
            throw new ValidatorException(errors);
        }
    }
}
