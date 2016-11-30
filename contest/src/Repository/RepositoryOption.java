package Repository;

import Domain.Candidate;
import Domain.Department;
import Domain.Option;
import Validator.RepositoryException;
import Validator.ValidatorException;
import Validator.ValidatorOption;

import java.util.List;

/**
 * Created by Sebi on 30-Nov-16.
 */
public class RepositoryOption extends AbstractRepository<Option, Integer> {
    //candidate repository
    private IRepository repositoryCandidate;

    //department repository
    private IRepository repositoryDepartment;

    /*
    Constructor
     */
    public RepositoryOption(ValidatorOption validatorOption,
                            IRepository repositoryCandidate,
                            IRepository repositoryDepartment){
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

        List<Candidate> candidates = repositoryCandidate.getAll();
        //valid candidate
        if (!candidates.stream().anyMatch(x -> x.getId().equals(option.getIdCandidate()))){
            throw new RepositoryException("The candidate id is not valid");
        }

        List<Department> departments = repositoryDepartment.getAll();
        //valid department
        if (!departments.stream().anyMatch(x -> x.getId().equals(option.getIdDepartment()))){
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

        List<Candidate> candidates = repositoryCandidate.getAll();
        //valid candidate
        if (!candidates.stream().anyMatch(x -> x.getId().equals(option.getIdCandidate()))){
            throw new RepositoryException("The candidate id is not valid");
        }

        List<Department> departments = repositoryDepartment.getAll();
        //valid department
        if (!departments.stream().anyMatch(x -> x.getId().equals(option.getIdDepartment()))){
            throw new RepositoryException("The department id is not valid");
        }
        super.update(id, option);
    }
}
