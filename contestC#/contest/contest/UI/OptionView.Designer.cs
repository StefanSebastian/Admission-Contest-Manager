namespace contest.UI
{
    partial class OptionView
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.listBoxDepartments = new System.Windows.Forms.ListBox();
            this.listBoxCandidates = new System.Windows.Forms.ListBox();
            this.labelDepartments = new System.Windows.Forms.Label();
            this.labelCandidates = new System.Windows.Forms.Label();
            this.comboBoxDepartment = new System.Windows.Forms.ComboBox();
            this.comboBoxCandidate = new System.Windows.Forms.ComboBox();
            this.buttonAdd = new System.Windows.Forms.Button();
            this.buttonDelete = new System.Windows.Forms.Button();
            this.buttonUpdate = new System.Windows.Forms.Button();
            this.labelDepartmentsCombo = new System.Windows.Forms.Label();
            this.labelCandidate = new System.Windows.Forms.Label();
            this.SuspendLayout();
            // 
            // listBoxDepartments
            // 
            this.listBoxDepartments.FormattingEnabled = true;
            this.listBoxDepartments.Location = new System.Drawing.Point(3, 22);
            this.listBoxDepartments.Name = "listBoxDepartments";
            this.listBoxDepartments.Size = new System.Drawing.Size(188, 108);
            this.listBoxDepartments.TabIndex = 0;
            this.listBoxDepartments.SelectedIndexChanged += new System.EventHandler(this.listBoxDepartments_SelectedIndexChanged);
            // 
            // listBoxCandidates
            // 
            this.listBoxCandidates.FormattingEnabled = true;
            this.listBoxCandidates.Location = new System.Drawing.Point(3, 173);
            this.listBoxCandidates.Name = "listBoxCandidates";
            this.listBoxCandidates.Size = new System.Drawing.Size(188, 121);
            this.listBoxCandidates.TabIndex = 1;
            this.listBoxCandidates.SelectedIndexChanged += new System.EventHandler(this.listBoxCandidates_SelectedIndexChanged);
            // 
            // labelDepartments
            // 
            this.labelDepartments.AutoSize = true;
            this.labelDepartments.Location = new System.Drawing.Point(12, 6);
            this.labelDepartments.Name = "labelDepartments";
            this.labelDepartments.Size = new System.Drawing.Size(67, 13);
            this.labelDepartments.TabIndex = 2;
            this.labelDepartments.Text = "Departments";
            // 
            // labelCandidates
            // 
            this.labelCandidates.AutoSize = true;
            this.labelCandidates.Location = new System.Drawing.Point(13, 154);
            this.labelCandidates.Name = "labelCandidates";
            this.labelCandidates.Size = new System.Drawing.Size(131, 13);
            this.labelCandidates.TabIndex = 3;
            this.labelCandidates.Text = "Candidates for department";
            // 
            // comboBoxDepartment
            // 
            this.comboBoxDepartment.FormattingEnabled = true;
            this.comboBoxDepartment.Location = new System.Drawing.Point(285, 56);
            this.comboBoxDepartment.Name = "comboBoxDepartment";
            this.comboBoxDepartment.Size = new System.Drawing.Size(121, 21);
            this.comboBoxDepartment.TabIndex = 4;
            // 
            // comboBoxCandidate
            // 
            this.comboBoxCandidate.FormattingEnabled = true;
            this.comboBoxCandidate.Location = new System.Drawing.Point(285, 108);
            this.comboBoxCandidate.Name = "comboBoxCandidate";
            this.comboBoxCandidate.Size = new System.Drawing.Size(121, 21);
            this.comboBoxCandidate.TabIndex = 5;
            // 
            // buttonAdd
            // 
            this.buttonAdd.Location = new System.Drawing.Point(285, 144);
            this.buttonAdd.Name = "buttonAdd";
            this.buttonAdd.Size = new System.Drawing.Size(121, 23);
            this.buttonAdd.TabIndex = 6;
            this.buttonAdd.Text = "Add";
            this.buttonAdd.UseVisualStyleBackColor = true;
            this.buttonAdd.Click += new System.EventHandler(this.buttonAdd_Click);
            // 
            // buttonDelete
            // 
            this.buttonDelete.Location = new System.Drawing.Point(285, 173);
            this.buttonDelete.Name = "buttonDelete";
            this.buttonDelete.Size = new System.Drawing.Size(121, 23);
            this.buttonDelete.TabIndex = 7;
            this.buttonDelete.Text = "Delete";
            this.buttonDelete.UseVisualStyleBackColor = true;
            this.buttonDelete.Click += new System.EventHandler(this.buttonDelete_Click);
            // 
            // buttonUpdate
            // 
            this.buttonUpdate.Location = new System.Drawing.Point(285, 202);
            this.buttonUpdate.Name = "buttonUpdate";
            this.buttonUpdate.Size = new System.Drawing.Size(121, 23);
            this.buttonUpdate.TabIndex = 8;
            this.buttonUpdate.Text = "Update";
            this.buttonUpdate.UseVisualStyleBackColor = true;
            this.buttonUpdate.Click += new System.EventHandler(this.buttonUpdate_Click);
            // 
            // labelDepartmentsCombo
            // 
            this.labelDepartmentsCombo.AutoSize = true;
            this.labelDepartmentsCombo.Location = new System.Drawing.Point(285, 37);
            this.labelDepartmentsCombo.Name = "labelDepartmentsCombo";
            this.labelDepartmentsCombo.Size = new System.Drawing.Size(62, 13);
            this.labelDepartmentsCombo.TabIndex = 9;
            this.labelDepartmentsCombo.Text = "Department";
            // 
            // labelCandidate
            // 
            this.labelCandidate.AutoSize = true;
            this.labelCandidate.Location = new System.Drawing.Point(282, 92);
            this.labelCandidate.Name = "labelCandidate";
            this.labelCandidate.Size = new System.Drawing.Size(55, 13);
            this.labelCandidate.TabIndex = 10;
            this.labelCandidate.Text = "Candidate";
            // 
            // OptionView
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(468, 295);
            this.ControlBox = false;
            this.Controls.Add(this.labelCandidate);
            this.Controls.Add(this.labelDepartmentsCombo);
            this.Controls.Add(this.buttonUpdate);
            this.Controls.Add(this.buttonDelete);
            this.Controls.Add(this.buttonAdd);
            this.Controls.Add(this.comboBoxCandidate);
            this.Controls.Add(this.comboBoxDepartment);
            this.Controls.Add(this.labelCandidates);
            this.Controls.Add(this.labelDepartments);
            this.Controls.Add(this.listBoxCandidates);
            this.Controls.Add(this.listBoxDepartments);
            this.Name = "OptionView";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.ListBox listBoxDepartments;
        private System.Windows.Forms.ListBox listBoxCandidates;
        private System.Windows.Forms.Label labelDepartments;
        private System.Windows.Forms.Label labelCandidates;
        private System.Windows.Forms.ComboBox comboBoxDepartment;
        private System.Windows.Forms.ComboBox comboBoxCandidate;
        private System.Windows.Forms.Button buttonAdd;
        private System.Windows.Forms.Button buttonDelete;
        private System.Windows.Forms.Button buttonUpdate;
        private System.Windows.Forms.Label labelDepartmentsCombo;
        private System.Windows.Forms.Label labelCandidate;
    }
}