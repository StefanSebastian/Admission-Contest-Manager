package Validator;

import Domain.Department;

import java.util.Objects;

/**
 * Created by Sebi on 10/18/2016.
 */
public class ValidatorDepartment implements IValidator<Department> {
    /*
    Validates a department
     */
    @Override
    public void validate(Department department) throws ValidatorException{
        String errors = "";

        if (department.getName().isEmpty()){
            errors += "Name must be completed\n";
        }

        if (department.getNumberOfPlaces() < 0){
            errors += "Number of places must be positive\n";
        }

        if (!errors.equals("")){
            throw new ValidatorException(errors);
        }
    }
}
