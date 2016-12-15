package Repository;

import Domain.Department;
import Validator.RepositoryException;
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

    @Override
    public void delete(Integer id) throws RepositoryException {
        Department department = getById(id);
        if (department == null){
            throw  new RepositoryException("This id is not valid");
        }
        notifyPushObservers(department);
        super.delete(id);
    }
}
