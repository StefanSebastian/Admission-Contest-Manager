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
    public partial class DepartmentView : Form, Observer 
    {
        //controller
        private DepartmentController controller;
        //data model
        private BindingList<Department> dataModel;

        /*
         * Constructor
         */
        public DepartmentView(DepartmentController controller)
        {
            InitializeComponent();
            this.controller = controller;
            controller.addObserver(this);

            //intialize listbox
            updateDataModel(controller.getAll());
        }

        /*
         * When repository changes, update data model
         */
        public void update()
        {
            updateDataModel(controller.getAll());
        }

        /*
        * Updates the data model 
        */
        private void updateDataModel(List<Department> list)
        {
            dataModel = new BindingList<Department>(list);//binds the model
            listBoxDepartments.DataSource = dataModel;
            listBoxDepartments.DisplayMember = "IdName";
            listBoxDepartments.ValueMember = "Id";
        }

        /*
         * Clears all text fields 
         */
        private void buttonClearAll_Click(object sender, EventArgs e)
        {
            textBoxId.Text = "";
            textBoxName.Text = "";
            textBoxPlaces.Text = "";
        }

        /*
         * When the user selects an item
         */
        private void listBoxDepartments_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (listBoxDepartments.SelectedItem != null)
            {
                Department department = (Department)listBoxDepartments.SelectedItem;
                textBoxId.Text = department.Id.ToString();
                textBoxName.Text = department.Name;
                textBoxPlaces.Text = department.NumberOfPlaces.ToString();
            }

        }

        /*
         * Add a department
         */
        private void buttonAdd_Click(object sender, EventArgs e)
        {
            try
            {
                controller.add(textBoxId.Text, textBoxName.Text, textBoxPlaces.Text);
            }
            catch (ValidatorException exc)
            {
                System.Windows.Forms.MessageBox.Show(exc.Message);
            }
            catch (RepositoryException exc)
            {
                System.Windows.Forms.MessageBox.Show(exc.Message);
            }
        }

        /*
         * Deletes a department
         */
        private void buttonDelete_Click(object sender, EventArgs e)
        {
            if (listBoxDepartments.SelectedIndex != -1)
            {
                Department department = (Department)listBoxDepartments.SelectedItem;
                controller.delete(department.Id.ToString());
            }
            else
            {
                System.Windows.Forms.MessageBox.Show("You must select a candidate.");
            }
        }

        /*
         * Updates a department
         */
        private void buttonUpdate_Click(object sender, EventArgs e)
        {
            if (listBoxDepartments.SelectedIndex != -1)
            {
                try
                {
                    Department department = (Department)listBoxDepartments.SelectedItem;
                    controller.update(department.Id.ToString(),
                        textBoxId.Text, textBoxName.Text, textBoxPlaces.Text);
                }
                catch (ValidatorException exc)
                {
                    System.Windows.Forms.MessageBox.Show(exc.Message);
                }
                catch (RepositoryException exc)
                {
                    System.Windows.Forms.MessageBox.Show(exc.Message);
                }
            }
            else
            {
                System.Windows.Forms.MessageBox.Show("You must select a candidate.");
            }
        }

        /*
         * Filters by name
         */
        private void textBoxFilterName_TextChanged(object sender, EventArgs e)
        {
            string name = textBoxFilterName.Text;
            if (name.Equals(""))
            {
                updateDataModel(controller.getAll());
            } else
            {
                updateDataModel(controller.nameContains(name));
            }
        }

        /*
         * Filters by number of places 
         */
        private void textBoxFilterPlaces_TextChanged(object sender, EventArgs e)
        {
            string places = textBoxFilterPlaces.Text;
            if (places.Equals(""))
            {
                updateDataModel(controller.getAll());
            }
            else
            {
                try {
                    updateDataModel(controller.numberOfPlacesGreaterThan(int.Parse(places)));
                } catch(FormatException exc)
                {
                    
                }
                
            }
        }
    }
}
