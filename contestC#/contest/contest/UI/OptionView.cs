using contest.Controller;
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
    public partial class OptionView : Form, Observer 
    {
        //controllers 
        private OptionController optionController;
        private CandidateController candidateController;
        private DepartmentController departmentController;

        //data model


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

            optionController.addObserver(this);
        }

        /*
         * Ignores push updates
         */
        public void pushUpdate(object e)
        {
        
        }

        public void update()
        {
            
        }
    }
}
