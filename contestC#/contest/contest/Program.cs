using contest.Controller;
using contest.Repository;
using contest.UI;
using contest.Validation;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace contest
{
   /* class Program
    {
        static void Main(string[] args)
        {
        }
    }*/

    static class Program
    {
        /// <summary>
        /// The main entry point for the application.
        /// </summary>
        [STAThread]
        static void Main()
        {
            CandidateValidator validatorCandidate = new CandidateValidator();
            CandidateFileRepository repositoryCandidate = new CandidateFileRepository(validatorCandidate, "../../Data/Candidates.txt");
            CandidateController controllerCandidate = new CandidateController(repositoryCandidate);

            DepartmentValidator validatorDepartment = new DepartmentValidator();
            DepartmentFileRepository repositoryDepartment = new DepartmentFileRepository(validatorDepartment, "../../Data/Departments.txt");
            DepartmentController controllerDepartment = new DepartmentController(repositoryDepartment);

            OptionValidator validatorOption = new OptionValidator();
            OptionFileRepository repositoryOption = new OptionFileRepository(validatorOption, "../../Data/Options.txt",
                                                                             repositoryDepartment, repositoryCandidate);
            OptionController controllerOption = new OptionController(repositoryOption);

            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);
            Application.Run(new HomePage(
                new CandidateView(controllerCandidate), 
                new DepartmentView(controllerDepartment),
                new OptionView(controllerOption, controllerCandidate, controllerDepartment)));
        }
    }
}
