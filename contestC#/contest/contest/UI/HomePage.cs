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
    public partial class HomePage : Form
    {
        public HomePage(CandidateView candidateView, DepartmentView departmentView)
        {
            InitializeComponent();

            tabControlMain.Dock = DockStyle.Fill;

            this.IsMdiContainer = true;//contains child forms
            candidateView.TopLevel = false;//is child form
            departmentView.TopLevel = false;//is child form

            candidateView.Parent = tabPageCandidates;
            departmentView.Parent = tabPageDepartments;

            candidateView.Dock = DockStyle.Fill;
            departmentView.Dock = DockStyle.Fill;

            candidateView.Visible = true;
            departmentView.Visible = true;   
        }

    }
}
