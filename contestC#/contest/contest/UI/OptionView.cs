using contest.Controller;
using contest.Domain;
using contest.Exceptions;
using contest.Utils.Observer;
using contest.Validation;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace contest.UI
{
    public partial class OptionView : Form, Observer 
    {
        //controllers 
        private OptionController optionController;
        private CandidateController candidateController;
        private DepartmentController departmentController;

        //data model
        private BindingList<Candidate> candidateDataModel;
        private BindingList<Department> departmentDataModel;
        private BindingList<Candidate> comboboxCandidateDataModel;
        private BindingList<Department> comboboxDepartmentDataModel;

        //selected candidate and department = selected option
        private Candidate selectedCandidate = null;
        private Department selectedDepartment = null;

        /*
         * Constructor
         */
        public OptionView(OptionController optionController, CandidateController candidateController, 
            DepartmentController departmentController)
        {
            InitializeComponent();

            this.optionController = optionController;
            this.departmentController = departmentController;
            this.candidateController = candidateController;

            comboBoxCandidate.DropDownStyle = ComboBoxStyle.DropDownList;
            comboBoxDepartment.DropDownStyle = ComboBoxStyle.DropDownList;

            optionController.addObserver(this);
            candidateController.addObserver(this);
            departmentController.addObserver(this);
            update();
        }

        /*
         * Populates the department combobox
         */
        public void populateDepartmentComboBox(List<Department> departments)
        {
            comboboxDepartmentDataModel = new BindingList<Department>(departments);
            comboBoxDepartment.DataSource = comboboxDepartmentDataModel;
            comboBoxDepartment.DisplayMember = "IdName";
            comboBoxDepartment.ValueMember = "Id";
        }

        /*
         * Populates the candidate combobox 
         */
        public void populateCandidateComboBox(List<Candidate> candidates)
        {
            comboboxCandidateDataModel = new BindingList<Candidate>(candidates);
            comboBoxCandidate.DataSource = comboboxCandidateDataModel;
            comboBoxCandidate.DisplayMember = "IdName";
            comboBoxCandidate.ValueMember = "Id";
        }

        /*
         * Populates the departments table
         */
        public void populateDepartmentTable(List<Department> departments)
        {
            departmentDataModel = new BindingList<Department>(departments);
            listBoxDepartments.DataSource = departmentDataModel;
            listBoxDepartments.DisplayMember = "IdName";
            listBoxDepartments.ValueMember = "Id";
        }

        /*
         * Populates the candidates table
         */
        public void populateCandidateTable(List<Candidate> candidates)
        {
            candidateDataModel = new BindingList<Candidate>(candidates);
            listBoxCandidates.DataSource = candidateDataModel;
            listBoxCandidates.DisplayMember = "IdName";
            listBoxCandidates.ValueMember = "Id";
        }

        /*
         * Gets data for candidate table 
         */
         public List<Candidate> getCandidateData()
        {
            List<Candidate> candidatesForDepartment = new List<Candidate>();
            if (listBoxDepartments.SelectedItem != null)
            {
                Department department = (Department)listBoxDepartments.SelectedItem;
                List<int> idCandidates = optionController.candidatesForDepartment(department.Id);
                foreach (int id in idCandidates)
                {
                    candidatesForDepartment.Add(candidateController.getById(id.ToString()));
                }
            }
            return candidatesForDepartment;
        }

        /*
         * Update comboboxes selection
         */
         public void updateComboBoxSelection(Candidate selectedCandidate, Department selectedDepartment)
        {
            comboBoxCandidate.SelectedIndex = comboboxCandidateDataModel.IndexOf(selectedCandidate);
            comboBoxDepartment.SelectedIndex = comboboxDepartmentDataModel.IndexOf(selectedDepartment);
        }

        /*
         * Refreshes selected candidate 
         */
        public void refreshSelectedCandidate()
        {
            if (listBoxCandidates.SelectedItem != null && listBoxDepartments.SelectedItem != null)
            {
                selectedCandidate = (Candidate)listBoxCandidates.SelectedItem;
            }
            else
            {
                selectedCandidate = null;
            }
        }

        /*
         * Refreshes selected department
         */
        public void refreshSelectedDepartment()
        {
            if (listBoxDepartments.SelectedItem != null)
            {
                selectedDepartment = (Department)listBoxDepartments.SelectedItem;
            }
            else
            {
                selectedDepartment = null;
            }
        }

        /*
         * Updates comboboxes when selected option changes
         */
        private void listBoxCandidates_SelectedIndexChanged(object sender, EventArgs e)
        {
            refreshSelectedCandidate();
            if (listBoxCandidates.SelectedItem != null && listBoxDepartments.SelectedItem != null)
            {
                Candidate selectedCandidate = (Candidate)listBoxCandidates.SelectedItem;
                Department selectedDepartment = (Department)listBoxDepartments.SelectedItem;
                updateComboBoxSelection(selectedCandidate, selectedDepartment);
            }
        }

        /*
         * Ignores push updates
         */
        public void pushUpdate(object e)
        {
        }

        /*
         * Repository data changed
         */
        public void update()
        {
            populateDepartmentTable(departmentController.getAll());
            populateCandidateComboBox(candidateController.getAll());
            populateDepartmentComboBox(departmentController.getAll());
            populateCandidateTable(getCandidateData());
        }

        /*
         * When selected department changes , load corresponding candidates
         */
        private void listBoxDepartments_SelectedIndexChanged(object sender, EventArgs e)
        {
            refreshSelectedDepartment();
            if (listBoxDepartments.SelectedItem != null)
            {
                populateCandidateTable(getCandidateData());
            }
        }

        /*
         * Sets the selection in department table
         */
         public void setSelectedDepartment(Department toSelect)
        {
            listBoxDepartments.SelectedIndex = departmentController.getAll().FindIndex(x => x.Equals(toSelect));
        }

        /*
         * Adds an option
         */
        private void buttonAdd_Click(object sender, EventArgs e)
        {
            try
            {
                if (comboBoxCandidate.SelectedItem != null && comboBoxDepartment.SelectedItem != null)
                {
                    Candidate selectedCandidateCombo = (Candidate)comboBoxCandidate.SelectedItem;
                    string idCandidate = selectedCandidateCombo.Id.ToString();
                    Department selectedDepartmentCombo = (Department)comboBoxDepartment.SelectedItem;
                    string idDepartment = selectedDepartmentCombo.Id.ToString();
                    optionController.add(idCandidate, idDepartment);

                    setSelectedDepartment(selectedDepartmentCombo);
                    refreshSelectedDepartment();
                    refreshSelectedCandidate();
                } else
                {
                    System.Windows.Forms.MessageBox.Show("You must select a candidate and a department.");
                }
            } catch(RepositoryException exc)
            {
                System.Windows.Forms.MessageBox.Show(exc.Message);
            } catch (ValidatorException exc)
            {
                System.Windows.Forms.MessageBox.Show(exc.Message);
            }
        }

        /*
         * Deletes selected option
         */
        private void buttonDelete_Click(object sender, EventArgs e)
        {
            try
            {
                if (selectedCandidate != null && selectedDepartment != null)
                {
                    Department prevSelectedDepartment = selectedDepartment;//keeps selection after delete

                    optionController.delete(selectedCandidate.Id.ToString() + " " +
                                            selectedDepartment.Id.ToString());

                    setSelectedDepartment(prevSelectedDepartment);
                    refreshSelectedDepartment();
                    refreshSelectedCandidate();
                } else
                {
                    System.Windows.Forms.MessageBox.Show("You must select a candidate and a department.");
                }
            } catch (RepositoryException exc)
            {
                System.Windows.Forms.MessageBox.Show(exc.Message);
            }
        }

        /*
         * Updates selected 
         */
        private void buttonUpdate_Click(object sender, EventArgs e)
        {
            Candidate selectedComboCandidate = (Candidate)comboBoxCandidate.SelectedItem;
            Department selectedComboDepartment = (Department)comboBoxDepartment.SelectedItem;
            try
            {
                if(selectedCandidate != null && selectedDepartment != null &&
                     selectedComboDepartment != null && selectedComboCandidate != null)
                {
                    Department prevSelectedDepartment = selectedDepartment;
                    Candidate prevSelectedCandidate = selectedCandidate;

                    optionController.add(selectedComboCandidate.Id.ToString(),
                                         selectedComboDepartment.Id.ToString());
                    optionController.delete(prevSelectedCandidate.Id.ToString() + " " +
                                            prevSelectedDepartment.Id.ToString());

                    setSelectedDepartment(prevSelectedDepartment);
                    refreshSelectedDepartment();
                    refreshSelectedCandidate();
                } else
                {
                    System.Windows.Forms.MessageBox.Show("You must select a candidate and a department.");
                }
            } catch (RepositoryException exc)
            {
                System.Windows.Forms.MessageBox.Show(exc.Message);

            } catch (ValidatorException exc)
            {
                System.Windows.Forms.MessageBox.Show(exc.Message);

            }
        }

    }
}
