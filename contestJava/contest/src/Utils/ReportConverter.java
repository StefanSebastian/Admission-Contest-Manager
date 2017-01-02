package Utils;

import Domain.Candidate;
import Domain.Department;
import Domain.DepartmentCandidates;

import java.util.List;

/**
 * Created by Sebi on 02-Jan-17.
 */
public class ReportConverter {
    /*
    Converts a report to html format
     */
    public static String convertHTML(List<DepartmentCandidates> report){
        String reportSt = "<div>";
        for (DepartmentCandidates departmentCandidates : report) {
            reportSt += "<div>";

            Department department = departmentCandidates.getDepartment();
            reportSt = reportSt + "<p><b> " + department.getId() + " " + department.getName() + "</b></p>";

            if (departmentCandidates.getCandidateList().size() != 0){
                reportSt += "<table>";
                reportSt += "<tr><th>Id</th><th>Name</th><th>Telephone</th><th>Address</th></tr>";
                for (Candidate candidate : departmentCandidates.getCandidateList()) {
                    reportSt += "<tr>";
                    reportSt = reportSt + "<td>" + candidate.getId() + "</td>";
                    reportSt = reportSt + "<td>" + candidate.getName() + "</td>";
                    reportSt = reportSt + "<td>" + candidate.getTelephone() + "</td>";
                    reportSt = reportSt + "<td>" + candidate.getAddress() + "</td>";
                    reportSt += "</tr>";
                }
                reportSt += "</table>";
            } else {
                reportSt += "<p> No candidates for this department</p>";
            }

            reportSt += "</div>";
        }
        reportSt += "</div>";
        return reportSt;
    }

    /*
    Converts a report to text format
     */
    public static String convertText(List<DepartmentCandidates> report){
        String reportSt = "";

        for (DepartmentCandidates departmentCandidates : report) {
            reportSt += "-----------------------------------\n";
            Department department = departmentCandidates.getDepartment();
            reportSt = reportSt + department.getId() + " " + department.getName()
                    + " with " + department.getNumberOfPlaces() + " places\n";

            if (departmentCandidates.getCandidateList().size() != 0){
                for (Candidate candidate : departmentCandidates.getCandidateList()) {
                    reportSt = reportSt + candidate.getId() + " ";
                    reportSt = reportSt + candidate.getName() + " ";
                    reportSt = reportSt + candidate.getTelephone() + " ";
                    reportSt = reportSt + candidate.getAddress() + "\n";
                }
            } else {
                reportSt += "No candidates for this department\n";
            }
            reportSt += "-----------------------------------\n";
        }

        return reportSt;
    }
}
