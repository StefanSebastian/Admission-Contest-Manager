package Domain;

/**
 * Created by Sebi on 10/7/2016.
 */

import java.io.Serializable;

/**
 * Gestioneaza obiectele de tip Department
 */
public class Department implements HasId<Integer>, Serializable {
    private Integer id;
    private String name;
    private Integer numberOfPlaces;

    /**
     * Constructor
     * @param id - Integer
     * @param name - String
     * @param numberOfPlaces - Integer
     */
    public Department(Integer id, String name, Integer numberOfPlaces){
        this.id = id;
        this.name = name;
        this.numberOfPlaces = numberOfPlaces;
    }

    /**
     * Getters
     */
    public Integer getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public Integer getNumberOfPlaces(){
        return numberOfPlaces;
    }

    /**
     * Setters
     */
    public void setId(Integer id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setNumberOfPlaces(Integer numberOfPlaces){
        this.numberOfPlaces = numberOfPlaces;
    }

    /*
    To string method
     */
    @Override
    public String toString(){
        return id + " " + name + " " + numberOfPlaces;
    }
}
