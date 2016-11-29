package Domain;

import Repository.IRepository;
import Utils.Observable;
import Utils.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * Created by Sebi on 15-Nov-16.
 */
public class CandidatDataModel implements Observer<Candidat> {
    /*
    Observable list, contains all candidates in repository
     */
    private ObservableList<Candidat> model;

    /*
    Candidate repository
     */
    private IRepository<Candidat, Integer> repository;

    /*
    Constructor
    registers as observer to repository
     */
    public CandidatDataModel(IRepository<Candidat, Integer> repository){
        this.repository = repository;
        repository.addObserver(this);
        model = FXCollections.observableArrayList(repository.getAll());
    }

    /*
    Returns the model
     */
    public ObservableList<Candidat> getModel(){
        return model;
    }

    @Override
    public void update(Observable<Candidat> e) {
        model.setAll(repository.getAll());
    }
}
