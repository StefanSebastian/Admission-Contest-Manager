package Validator;

import Domain.Candidate;

/**
 * Created by Sebi on 10/18/2016.
 */
public class ValidatorCandidate implements IValidator<Candidate> {

    /*
    Validates a candidate
     */
    @Override
    public void validate(Candidate candidate) throws ValidatorException{
        String errors = "";

        if (candidate.getName().isEmpty()){
             errors += "Name must not be empty\n";
        }
        if (candidate.getAddress().isEmpty()){
            errors += "Address must not be empty\n";
        }
        if (candidate.getTelephone().isEmpty()){
            errors += "Telephone must not be empty\n";
        }
        if (candidate.getTelephone().length() != 10){
            errors += "Telephone number must have 10 digits\n";
        }
        String nrTelefon = candidate.getTelephone();
        for (char c : nrTelefon.toCharArray()){
            if (!(c >= '0' && c <= '9')){
                errors += "Telephone number must contain only digits\n"; break;
            }
        }

        if (!errors.equals("")){
            throw new ValidatorException(errors);
        }
    }
}
