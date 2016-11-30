package Repository;

import Validator.ValidatorOption;

/**
 * Created by Sebi on 30-Nov-16.
 */
public class RepositoryOptionXML extends RepositoryOption {
    /*
    Name of the file
     */
    private String fileName;

    /*
    Constructor
     */
    public RepositoryOptionXML(String fileName, ValidatorOption validatorOption,
                               RepositoryCandidate repositoryCandidate, RepositoryDepartment repositoryDepartment){
        super(validatorOption, repositoryCandidate, repositoryDepartment);
        this.fileName = fileName;
        loadData();
    }

    /*
    Loads options from file
     */
    private void loadData(){

    }

    /*
    Saves options to file
     */
    private void saveData(){

    }
}
