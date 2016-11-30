package Repository;

import Domain.Department;
import Validator.RepositoryException;
import Validator.ValidatorException;
import Validator.ValidatorDepartment;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Sebi on 10/28/2016.
 */
public class RepositoryDepartmentSerializable extends RepositoryDepartment {
    /*
    Name of the file
     */
    private String fileName;

    /*
    Constructor
     */
    public RepositoryDepartmentSerializable(String fileName, ValidatorDepartment validatorDepartment){
        super(validatorDepartment);
        this.fileName = fileName;
        loadData();
    }

    /*
    Loads departments from file
     */
    public void loadData(){
        this.entities.clear();
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(this.fileName));
            entities = (ArrayList<Department>) objectInputStream.readObject();
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException exception){
            exception.printStackTrace();
        }
    }

    /*
    Saves departments to file
     */
    public void saveData(){
        try{
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(this.fileName));
            objectOutputStream.writeObject(entities);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Department s) throws RepositoryException, ValidatorException{
        super.save(s);
        saveData();
    }

    @Override
    public void delete(Integer Id) throws RepositoryException{
        super.delete(Id);
        saveData();
    }

    @Override
    public void clearAll() {
        super.clearAll();
        saveData();
    }

    @Override
    public void update(Integer id, Department newEntity) throws  RepositoryException, ValidatorException {
        super.update(id, newEntity);
        saveData();
    }
}
