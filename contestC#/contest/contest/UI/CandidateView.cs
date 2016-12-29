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
    public partial class CandidateView : Form, Observer
    {
        //controller
        private CandidateController controller;
        //model
        private BindingList<Candidate> dataModel;

        public CandidateView(CandidateController controller)
        {
            InitializeComponent();
            this.controller = controller;
            this.controller.addObserver(this);

            //initialize list box
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
        private void updateDataModel(List<Candidate> list)
        {
            dataModel = new BindingList<Candidate>(list);//binds the model
            listBoxCandidate.DataSource = dataModel;
            listBoxCandidate.DisplayMember = "IdName";
            listBoxCandidate.ValueMember = "Id";
        }

        /*
         * When the selected index changes, loads the data into textboxes
         */
        private void listBoxCandidate_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (listBoxCandidate.SelectedItem != null)
            {
                Candidate candidate = (Candidate)listBoxCandidate.SelectedItem;
                textBoxId.Text = candidate.Id.ToString();
                textBoxName.Text = candidate.Name;
                textBoxTelephone.Text = candidate.Telephone;
                textBoxAddress.Text = candidate.Address;
            }
        }

        /*
         * Clears all text fields
         */
        private void buttonClearAll_Click(object sender, EventArgs e)
        {
            textBoxId.Text = "";
            textBoxName.Text = "";
            textBoxAddress.Text = "";
            textBoxTelephone.Text = "";
        }

        /*
         * Adds a candidate
         */
        private void buttonAdd_Click(object sender, EventArgs e)
        {
            try
            {
                controller.add(textBoxId.Text, textBoxName.Text, textBoxTelephone.Text, textBoxAddress.Text);
            } catch (ValidatorException exc)
            {
                System.Windows.Forms.MessageBox.Show(exc.Message);
            } catch (RepositoryException exc)
            {
                System.Windows.Forms.MessageBox.Show(exc.Message);
            }
        }

        /*
         * Deletes a candidate  
         */
        private void buttonDelete_Click(object sender, EventArgs e)
        {
            if (listBoxCandidate.SelectedIndex != -1)
            {
                Candidate candidate = (Candidate)listBoxCandidate.SelectedItem;
                controller.delete(candidate.Id.ToString());
            } else
            {
                System.Windows.Forms.MessageBox.Show("You must select a candidate.");
            }
        }

        /*
         * Updates a candidate
         */
        private void buttonUpdate_Click(object sender, EventArgs e)
        {
            if (listBoxCandidate.SelectedIndex != -1)
            {
                try
                {
                    Candidate candidate = (Candidate)listBoxCandidate.SelectedItem;
                    controller.update(candidate.Id.ToString(),
                        textBoxId.Text, textBoxName.Text, textBoxTelephone.Text, textBoxAddress.Text);
                }
                catch (ValidatorException exc)
                {
                    System.Windows.Forms.MessageBox.Show(exc.Message);
                }
                catch (RepositoryException exc)
                {
                    System.Windows.Forms.MessageBox.Show(exc.Message);
                }
            } else
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
                updateDataModel(controller.nameStartsWith(name));
            }

        }

        /*
         * Filters by address
         */ 
        private void textBoxFilterAddress_TextChanged(object sender, EventArgs e)
        {
            string name = textBoxFilterAddress.Text;
            if (name.Equals(""))
            {
                updateDataModel(controller.getAll());
            }
            else
            {
                updateDataModel(controller.addressStartsWith(name));
            }
        }
    }
}
