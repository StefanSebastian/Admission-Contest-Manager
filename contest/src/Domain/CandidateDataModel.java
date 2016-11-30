package Domain;

import Repository.IRepository;
import Utils.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by Sebi on 15-Nov-16.
 */
public class CandidateDataModel implements Observer<Candidate> {
    /*
    Observable list, contains all candidates in repository
     */
    private ObservableList<Candidate> model;

    /*
    Candidate repository
     */
    private IRepository<Candidate, Integer> repository;

    /*
    Constructor
    registers as observer to repository
     */
    public CandidateDataModel(IRepository<Candidate, Integer> repository){
        this.repository = repository;
        repository.addObserver(this);
        model = FXCollections.observableArrayList(repository.getAll());
    }

    /*
    Returns the model
     */
    public ObservableList<Candidate> getModel(){
        return model;
    }


    /*
    Updates the model when data changes
     */
    @Override
    public void update() {
        model.setAll(repository.getAll());
    }
}
