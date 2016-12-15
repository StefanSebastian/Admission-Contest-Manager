package Domain;

/**
 * Created by Sebi on 30-Nov-16.
 */
public class Option implements HasId<Integer> {
    private Integer id;
    private Integer idCandidate;
    private Integer idDepartment;

    public Option(Integer id, Integer idCandidate, Integer idDepartment){
        this.id = id;
        this.idCandidate = idCandidate;
        this.idDepartment = idDepartment;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    //getter for candidate id
    public Integer getIdCandidate() {
        return idCandidate;
    }

    //setter for candidate id
    public void setIdCandidate(Integer idCandidate) {
        this.idCandidate = idCandidate;
    }

    //getter for department id
    public Integer getIdDepartment() {
        return idDepartment;
    }

    //setter for department id
    public void setIdDepartment(Integer idDepartment) {
        this.idDepartment = idDepartment;
    }
}
