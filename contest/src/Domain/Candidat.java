package Domain;

/**
 * Created by Sebi on 10/7/2016.
 */

/**
 * Gestioneaza obiectele tip Candidat
 */
public class Candidat implements HasId<Integer> {
    private Integer id;
    private String nume;
    private String telefon;
    private String adresa;

    /**
     * Constructor
     * @param id - Integer
     * @param nume - String
     * @param telefon - String
     * @param adresa - String
     */
    public Candidat(Integer id, String nume, String telefon, String adresa){
        this.id = id;
        this.nume = nume;
        this.telefon = telefon;
        this.adresa = adresa;
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

    public String getTelefon(){
        return telefon;
    }

    public String getAdresa(){
        return adresa;
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

    public void setTelefon(String telefon){
        this.telefon = telefon;
    }

    public void setAdresa(String adresa){
        this.adresa = adresa;
    }

    /*
    To string method
     */
    @Override
    public String toString(){
        return id + " " + nume + " " + telefon + " " + adresa;
    }

}