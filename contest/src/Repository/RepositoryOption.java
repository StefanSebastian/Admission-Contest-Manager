package Repository;

import Domain.Option;
import Validator.RepositoryException;
import Validator.ValidatorException;
import Validator.ValidatorOption;

/**
 * Created by Sebi on 30-Nov-16.
 */
public class RepositoryOption extends AbstractRepository<Option, Integer> {
    //candidate repository
    private RepositoryCandidate repositoryCandidate;

    //department repository
    private RepositoryDepartment repositoryDepartment;

    /*
    Constructor
     */
    public RepositoryOption(ValidatorOption validatorOption,
                            RepositoryCandidate repositoryCandidate,
                            RepositoryDepartment repositoryDepartment){
        super(validatorOption);
        this.repositoryDepartment = repositoryDepartment;
        this.repositoryCandidate = repositoryCandidate;
    }

    /*
    Checks for duplicates
    Checks if candidate and departments ids are valid
     */
    @Override
    public void save(Option option) throws RepositoryException, ValidatorException{
        //duplicates
        if (entities.stream().anyMatch(x -> x.getIdDepartment().equals(option.getIdDepartment()) &&
                                            x.getIdCandidate().equals(option.getIdCandidate()))){
            throw new RepositoryException("This option is already registered");
        }

        //valid candidate
        if (!repositoryCandidate.getAll().stream().anyMatch(x -> x.getId().equals(option.getIdCandidate()))){
            throw new RepositoryException("The candidate id is not valid");
        }

        //valid department
        if (!repositoryDepartment.getAll().stream().anyMatch(x -> x.getId().equals(option.getIdDepartment()))){
            throw new RepositoryException("The department id is not valid");
        }

        super.save(option);
    }

    /*
    Checks for duplicates
    Checks if candidate and departments ids are valid
     */
    @Override
    public void update(Integer id, Option option) throws RepositoryException, ValidatorException {
        //duplicates
        if (entities.stream().anyMatch(x -> x.getIdDepartment().equals(option.getIdDepartment()) &&
                x.getIdCandidate().equals(option.getIdCandidate()) && !x.getId().equals(option.getId()))){
            throw new RepositoryException("This option is already registered");
        }

        //valid candidate
        if (!repositoryCandidate.getAll().stream().anyMatch(x -> x.getId().equals(option.getIdCandidate()))){
            throw new RepositoryException("The candidate id is not valid");
        }

        //valid department
        if (!repositoryDepartment.getAll().stream().anyMatch(x -> x.getId().equals(option.getIdDepartment()))){
            throw new RepositoryException("The department id is not valid");
        }
        super.update(id, option);
    }
}
