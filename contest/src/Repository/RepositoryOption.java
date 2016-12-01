package Repository;

import Domain.Candidate;
import Domain.Department;
import Domain.HasId;
import Domain.Option;
import Utils.Observer;
import Validator.RepositoryException;
import Validator.ValidatorException;
import Validator.ValidatorOption;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sebi on 30-Nov-16.
 */
public class RepositoryOption extends AbstractRepository<Option, Integer> implements Observer<HasId<Integer>> {
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
        repositoryCandidate.addObserver(this);
        repositoryDepartment.addObserver(this);
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

    @Override
    public void update() {
        //ignores normal updates
    }

    @Override
    public void pushUpdate(HasId<Integer> obj) {
        if (obj instanceof Candidate){
            Candidate deletedCandidate = (Candidate) obj;

            //gets all options for the deleted candidate
            List<Integer> toDelete = new ArrayList<>();
            getAll().stream()
                    .filter(x -> x.getIdCandidate().equals(deletedCandidate.getId()))
                    .map(Option::getId)
                    .forEach(toDelete::add);

            //deletes them

            toDelete.forEach((id) -> {
                try {
                    delete(id);
                } catch (RepositoryException e) {
                    e.printStackTrace();
                }
            });
        }
        if (obj instanceof Department){
            Department deletedDepartment = (Department) obj;

            //gets all options for the deleted department
            List<Integer> toDelete = new ArrayList<>();
            getAll().stream()
                    .filter(x -> x.getIdDepartment().equals(deletedDepartment.getId()))
                    .map(Option::getId)
                    .forEach(toDelete::add);

            //deletes them

            toDelete.forEach((id) -> {
                try {
                    delete(id);
                } catch (RepositoryException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
