package Validator;

import Domain.Candidat;
import Validator.IValidator;

/**
 * Created by Sebi on 10/18/2016.
 */
public class ValidatorCandidat implements IValidator<Candidat> {
    @Override
    public void validate(Candidat candidat) throws ValidatorException{
        String errors = "";

        if (candidat.getNume().isEmpty()){
             errors += "Numele nu e completat.\n";
        }
        if (candidat.getAdresa().isEmpty()){
            errors += "Adresa nu e completata.\n";
        }
        if (candidat.getTelefon().isEmpty()){
            errors += "Telefonul nu e completat.\n";
        }
        if (candidat.getTelefon().length() != 10){
            errors += "Numarul de telefon trebuia sa aiba 10 cifre.\n";
        }
        String nrTelefon = candidat.getTelefon();
        for (char c : nrTelefon.toCharArray()){
            if (!(c >= '0' && c <= '9')){
                errors += "Numarul de telefon trebuie sa contina doar cifre.\n"; break;
            }
        }

        if (errors != ""){
            throw new ValidatorException(errors);
        }
    }
}
