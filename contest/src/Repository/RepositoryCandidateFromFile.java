package Repository;

import Domain.Candidate;
import Validator.RepositoryException;
import Validator.ValidatorCandidate;
import Validator.ValidatorException;

import java.io.*;

/**
 * Created by Sebi on 10/28/2016.
 */
public class RepositoryCandidateFromFile extends RepositoryCandidate {
    /*
    Name of the file
     */
    private String fileName;

    /*
    Constructor
     */
    public RepositoryCandidateFromFile(String fileName, ValidatorCandidate validatorCandidate){
        super(validatorCandidate);
        this.fileName = fileName;
        loadData();
    }

    /*
    Loads all candidates from file
     */
    public void loadData(){
        String line;
        String fields[];
        entities.clear();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(this.fileName)));
            while ((line = reader.readLine()) != null){
                if (line.equals("")){//if the file is empty
                    break;
                }
                fields = line.split("\\|");
                if (fields.length != 4){
                    throw new RepositoryException("Corrupted file - Candidate must have 4 fields");
                }
                Candidate candidate = new Candidate(Integer.parseInt(fields[0]), fields[1], fields[2], fields[3]);
                this.save(candidate);
            }
            reader.close();
        } catch (IOException | RepositoryException | ValidatorException | NumberFormatException exception) { //corrupted file
            exception.printStackTrace();
        }
    }

    /*
    Saves all candidates to file
     */
    public void saveData(){
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.fileName)));
            for (Candidate candidate : this.entities){
                String line = candidate.getId() + "|" + candidate.getName() + "|" +
                        candidate.getTelephone() + "|" + candidate.getAddress() + "\n";
                writer.write(line);
            }
            writer.close();
        } catch (IOException exception){
            exception.printStackTrace();
        }
    }

    @Override
    public void save(Candidate c) throws ValidatorException, RepositoryException{
        super.save(c);
        saveData();
    }

    @Override
    public void delete(Integer Id) throws RepositoryException {
        super.delete(Id);
        saveData();
    }

    @Override
    public void clearAll() {
        super.clearAll();
        saveData();
    }

    @Override
    public void update(Integer id, Candidate newEntity) throws RepositoryException, ValidatorException {
        super.update(id, newEntity);
        saveData();
    }

}
