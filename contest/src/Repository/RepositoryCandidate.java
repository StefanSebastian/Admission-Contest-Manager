package Repository;

import Domain.Candidate;
import Validator.ValidatorCandidate;

/**
 * Created by Sebi on 10/16/2016.
 */
public class RepositoryCandidate extends AbstractRepository<Candidate, Integer> {
    /*
    Constructor
     */
    public RepositoryCandidate(ValidatorCandidate validatorCandidate){
        super(validatorCandidate);
    }
}
