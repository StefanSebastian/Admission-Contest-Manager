package Domain;

import java.util.List;

/**
 * Created by Sebi on 02-Jan-17.
 */

/*
Contains a department and a list of candidates
 */
public class DepartmentCandidates {
    private Department department;
    private List<Candidate> candidateList;

    /*
    Constructor
     */
    public DepartmentCandidates(Department department, List<Candidate> candidates){
        this.department = department;
        this.candidateList = candidates;
    }

    /*
    Setters and getters
     */
    public void setDepartment(Department department){
        this.department = department;
    }

    public Department getDepartment(){
        return this.department;
    }

    public void setCandidateList(List<Candidate> candidateList){
        this.candidateList = candidateList;
    }

    public List<Candidate> getCandidateList(){
        return this.candidateList;
    }
}
