package Repository;

import Domain.Candidat;
import Validator.RepositoryException;
import Validator.ValidatorCandidat;
import Validator.ValidatorException;

import java.io.*;

/**
 * Created by Sebi on 10/28/2016.
 */
public class RepositoryCandidatFromFile extends RepositoryCandidat {
    /*
    Name of the file
     */
    private String fileName;

    /*
    Constructor
     */
    public RepositoryCandidatFromFile(String fileName, ValidatorCandidat validatorCandidat){
        super(validatorCandidat);
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
                Candidat candidat = new Candidat(Integer.parseInt(fields[0]), fields[1], fields[2], fields[3]);
                this.save(candidat);
            }
            reader.close();
        } catch (IOException | RepositoryException | ValidatorException exception) { //corrupted file
            exception.printStackTrace();
        }
    }

    /*
    Saves all candidates to file
     */
    public void saveData(){
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.fileName)));
            for (Candidat candidat : this.entities){
                String line = candidat.getId() + "|" + candidat.getNume() + "|" +
                        candidat.getTelefon() + "|" + candidat.getAdresa() + "\n";
                writer.write(line);
            }
            writer.close();
        } catch (IOException exception){
            exception.printStackTrace();
        }
    }

    @Override
    public void save(Candidat c) throws ValidatorException, RepositoryException{
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
    public void update(Integer id, Candidat newEntity) throws RepositoryException, ValidatorException {
        super.update(id, newEntity);
        saveData();
    }

}
