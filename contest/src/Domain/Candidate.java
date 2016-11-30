package Domain;

/**
 * Created by Sebi on 10/7/2016.
 */

/**
 * Gestioneaza obiectele tip Candidate
 */
public class Candidate implements HasId<Integer> {
    private Integer id;
    private String name;
    private String telephone;
    private String address;

    /**
     * Constructor
     * @param id - Integer
     * @param name - String
     * @param telephone - String
     * @param address - String
     */
    public Candidate(Integer id, String name, String telephone, String address){
        this.id = id;
        this.name = name;
        this.telephone = telephone;
        this.address = address;
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

    public String getTelephone(){
        return telephone;
    }

    public String getAddress(){
        return address;
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

    public void setTelephone(String telephone){
        this.telephone = telephone;
    }

    public void setAddress(String address){
        this.address = address;
    }

    /*
    To string method
     */
    @Override
    public String toString(){
        return id + " " + name + " " + telephone + " " + address;
    }

}