package Repository;

import Domain.Sectie;
import Validator.ValidatorSectie;

/**
 * Created by Sebi on 10/16/2016.
 */
public class RepositorySectie extends AbstractRepository<Sectie, Integer> {
    /*
    Constructor
     */
    public RepositorySectie(ValidatorSectie validatorSectie){
        super(validatorSectie);
    }
}
