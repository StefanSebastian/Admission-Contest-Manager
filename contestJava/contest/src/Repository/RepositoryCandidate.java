package Repository;

import Domain.Candidate;
import Validator.RepositoryException;
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

    @Override
    public void delete(Integer id) throws RepositoryException{
        Candidate candidate = getById(id);
        if (candidate == null){
            throw new RepositoryException("This id is not valid");
        }
        notifyPushObservers(candidate);//if the candidate is valid
        super.delete(id);
    }
}
