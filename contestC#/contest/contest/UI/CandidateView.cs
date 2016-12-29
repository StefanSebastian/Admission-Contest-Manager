using contest.Controller;
using contest.Domain;
using contest.Utils.Observer;
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
            updateDataModel();
        }

        /*
         * When repository changes, update data model
         */
        public void update()
        {
            updateDataModel();
        }

        /*
         * Updates the data model 
         */
        private void updateDataModel()
        {
            dataModel = new BindingList<Candidate>(controller.getAll());//binds the model
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
            controller.add(textBoxId.Text, textBoxName.Text, textBoxTelephone.Text, textBoxAddress.Text);
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
            }
        }

        /*
         * Updates a candidate
         */
        private void buttonUpdate_Click(object sender, EventArgs e)
        {
            if (listBoxCandidate.SelectedIndex != -1)
            {
                Candidate candidate = (Candidate)listBoxCandidate.SelectedItem;
                controller.update(candidate.Id.ToString(),
                    textBoxId.Text, textBoxName.Text, textBoxTelephone.Text, textBoxAddress.Text);
            }

        }

        
    }
}
