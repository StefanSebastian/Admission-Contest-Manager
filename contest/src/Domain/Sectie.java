package Domain;

/**
 * Created by Sebi on 10/7/2016.
 */

import java.io.Serializable;

/**
 * Gestioneaza obiectele de tip Sectie
 */
public class Sectie implements HasId<Integer>, Serializable {
    private Integer id;
    private String nume;
    private Integer nrLocuri;

    /**
     * Constructor
     * @param id - Integer
     * @param nume - String
     * @param nrLocuri - Integer
     */
    public Sectie(Integer id, String nume, Integer nrLocuri){
        this.id = id;
        this.nume = nume;
        this.nrLocuri = nrLocuri;
    }

    /**
     * Getters
     */
    public Integer getId(){
        return id;
    }

    public String getNume(){
        return nume;
    }

    public Integer getNrLocuri(){
        return nrLocuri;
    }

    /**
     * Setters
     */
    public void setId(Integer id){
        this.id = id;
    }

    public void setNume(String nume){
        this.nume = nume;
    }

    public void setNrLocuri(Integer nrLocuri){
        this.nrLocuri = nrLocuri;
    }

    /*
    To string method
     */
    @Override
    public String toString(){
        return id + " " + nume + " " + nrLocuri;
    }
}
