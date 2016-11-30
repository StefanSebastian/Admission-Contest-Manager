package Repository;

import Domain.Department;
import Validator.ValidatorDepartment;

/**
 * Created by Sebi on 10/16/2016.
 */
public class RepositoryDepartment extends AbstractRepository<Department, Integer> {
    /*
    Constructor
     */
    public RepositoryDepartment(ValidatorDepartment validatorDepartment){
        super(validatorDepartment);
    }
}
