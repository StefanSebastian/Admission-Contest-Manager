namespace contest.UI
{
    partial class HomePage
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
            this.tabControlMain = new System.Windows.Forms.TabControl();
            this.tabPageCandidates = new System.Windows.Forms.TabPage();
            this.tabPageDepartments = new System.Windows.Forms.TabPage();
            this.tabControlMain.SuspendLayout();
            this.SuspendLayout();
            // 
            // tabControlMain
            // 
            this.tabControlMain.Controls.Add(this.tabPageCandidates);
            this.tabControlMain.Controls.Add(this.tabPageDepartments);
            this.tabControlMain.Location = new System.Drawing.Point(3, 12);
            this.tabControlMain.Name = "tabControlMain";
            this.tabControlMain.SelectedIndex = 0;
            this.tabControlMain.Size = new System.Drawing.Size(505, 368);
            this.tabControlMain.TabIndex = 0;
            // 
            // tabPageCandidates
            // 
            this.tabPageCandidates.Location = new System.Drawing.Point(4, 22);
            this.tabPageCandidates.Name = "tabPageCandidates";
            this.tabPageCandidates.Padding = new System.Windows.Forms.Padding(3);
            this.tabPageCandidates.Size = new System.Drawing.Size(497, 342);
            this.tabPageCandidates.TabIndex = 0;
            this.tabPageCandidates.Text = "Candidates";
            this.tabPageCandidates.UseVisualStyleBackColor = true;
            // 
            // tabPageDepartments
            // 
            this.tabPageDepartments.Location = new System.Drawing.Point(4, 22);
            this.tabPageDepartments.Name = "tabPageDepartments";
            this.tabPageDepartments.Padding = new System.Windows.Forms.Padding(3);
            this.tabPageDepartments.Size = new System.Drawing.Size(497, 248);
            this.tabPageDepartments.TabIndex = 1;
            this.tabPageDepartments.Text = "Departments";
            this.tabPageDepartments.UseVisualStyleBackColor = true;
            // 
            // HomePage
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(517, 384);
            this.Controls.Add(this.tabControlMain);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.Fixed3D;
            this.Name = "HomePage";
            this.Text = "HomePage";
            this.tabControlMain.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.TabControl tabControlMain;
        private System.Windows.Forms.TabPage tabPageCandidates;
        private System.Windows.Forms.TabPage tabPageDepartments;
    }
}