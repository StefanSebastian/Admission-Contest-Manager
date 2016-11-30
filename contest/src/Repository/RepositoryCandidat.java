package Repository;

import Domain.Candidat;
import Validator.ValidatorCandidat;

/**
 * Created by Sebi on 10/16/2016.
 */
public class RepositoryCandidat extends AbstractRepository<Candidat, Integer> {
    /*
    Constructor
     */
    public RepositoryCandidat(ValidatorCandidat validatorCandidat){
        super(validatorCandidat);
    }
}
