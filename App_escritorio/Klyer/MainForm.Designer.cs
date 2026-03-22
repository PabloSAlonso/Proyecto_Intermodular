namespace Klyer
{
    partial class MainForm
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; false otherwise.</param>
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
            this.tabControl1 = new System.Windows.Forms.TabControl();
            this.tabFeed = new System.Windows.Forms.TabPage();
            this.tabPerfil = new System.Windows.Forms.TabPage();
            this.tabSubir = new System.Windows.Forms.TabPage();
            this.headerPanel = new System.Windows.Forms.Panel();
            this.titleLabel = new System.Windows.Forms.Label();
            this.btnNewPost = new System.Windows.Forms.Button();
            this.lblUser = new System.Windows.Forms.Label();
            this.postsPanel = new System.Windows.Forms.FlowLayoutPanel();
            this.tabControl1.SuspendLayout();
            this.tabFeed.SuspendLayout();
            this.headerPanel.SuspendLayout();
            this.SuspendLayout();
            // 
            // tabControl1
            // 
            this.tabControl1.Controls.Add(this.tabFeed);
            this.tabControl1.Controls.Add(this.tabPerfil);
            this.tabControl1.Controls.Add(this.tabSubir);
            this.tabControl1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tabControl1.Location = new System.Drawing.Point(0, 0);
            this.tabControl1.Name = "tabControl1";
            this.tabControl1.SelectedIndex = 0;
            this.tabControl1.Size = new System.Drawing.Size(800, 600);
            this.tabControl1.TabIndex = 0;
            this.tabControl1.SelectedIndexChanged += new System.EventHandler(this.tabControl1_SelectedIndexChanged);
            // 
            // tabFeed
            // 
            this.tabFeed.Controls.Add(this.headerPanel);
            this.tabFeed.Controls.Add(this.postsPanel);
            this.tabFeed.Location = new System.Drawing.Point(4, 22);
            this.tabFeed.Name = "tabFeed";
            this.tabFeed.Padding = new System.Windows.Forms.Padding(3);
            this.tabFeed.Size = new System.Drawing.Size(792, 574);
            this.tabFeed.TabIndex = 0;
            this.tabFeed.Text = "Feed";
            this.tabFeed.UseVisualStyleBackColor = true;
            // 
            // tabPerfil
            // 
            this.tabPerfil.Location = new System.Drawing.Point(4, 22);
            this.tabPerfil.Name = "tabPerfil";
            this.tabPerfil.Padding = new System.Windows.Forms.Padding(3);
            this.tabPerfil.Size = new System.Drawing.Size(792, 574);
            this.tabPerfil.TabIndex = 1;
            this.tabPerfil.Text = "Perfil";
            this.tabPerfil.UseVisualStyleBackColor = true;
            // 
            // tabSubir
            // 
            this.tabSubir.Location = new System.Drawing.Point(4, 22);
            this.tabSubir.Name = "tabSubir";
            this.tabSubir.Padding = new System.Windows.Forms.Padding(3);
            this.tabSubir.Size = new System.Drawing.Size(792, 574);
            this.tabSubir.TabIndex = 2;
            this.tabSubir.Text = "Subir Publicación";
            this.tabSubir.UseVisualStyleBackColor = true;
            // 
            // headerPanel
            // 
            this.headerPanel.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(15)))), ((int)(((byte)(118)))), ((int)(((byte)(110)))));
            this.headerPanel.Controls.Add(this.lblUser);
            this.headerPanel.Controls.Add(this.btnNewPost);
            this.headerPanel.Controls.Add(this.titleLabel);
            this.headerPanel.Dock = System.Windows.Forms.DockStyle.Top;
            this.headerPanel.Height = 60;
            this.headerPanel.Location = new System.Drawing.Point(3, 3);
            this.headerPanel.Name = "headerPanel";
            this.headerPanel.Size = new System.Drawing.Size(786, 60);
            this.headerPanel.TabIndex = 0;
            // 
            // titleLabel
            // 
            this.titleLabel.AutoSize = true;
            this.titleLabel.Font = new System.Drawing.Font("Arial", 16F, System.Drawing.FontStyle.Bold);
            this.titleLabel.ForeColor = System.Drawing.Color.White;
            this.titleLabel.Location = new System.Drawing.Point(20, 15);
            this.titleLabel.Name = "titleLabel";
            this.titleLabel.Size = new System.Drawing.Size(51, 25);
            this.titleLabel.TabIndex = 0;
            this.titleLabel.Text = "Klyer";
            // 
            // btnNewPost
            // 
            this.btnNewPost.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(15)))), ((int)(((byte)(118)))), ((int)(((byte)(110)))));
            this.btnNewPost.FlatAppearance.BorderSize = 0;
            this.btnNewPost.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnNewPost.ForeColor = System.Drawing.Color.White;
            this.btnNewPost.Location = new System.Drawing.Point(696, 15);
            this.btnNewPost.Name = "btnNewPost";
            this.btnNewPost.Size = new System.Drawing.Size(120, 30);
            this.btnNewPost.TabIndex = 1;
            this.btnNewPost.Text = "Nueva Publicación";
            this.btnNewPost.UseVisualStyleBackColor = false;
            this.btnNewPost.Click += new System.EventHandler(this.btnNewPost_Click);
            // 
            // lblUser
            // 
            this.lblUser.AutoSize = true;
            this.lblUser.ForeColor = System.Drawing.Color.White;
            this.lblUser.Location = new System.Drawing.Point(556, 20);
            this.lblUser.Name = "lblUser";
            this.lblUser.Size = new System.Drawing.Size(35, 13);
            this.lblUser.TabIndex = 2;
            this.lblUser.Text = "Hola, ";
            // 
            // postsPanel
            // 
            this.postsPanel.AutoScroll = true;
            this.postsPanel.Dock = System.Windows.Forms.DockStyle.Fill;
            this.postsPanel.Location = new System.Drawing.Point(3, 63);
            this.postsPanel.Name = "postsPanel";
            this.postsPanel.Padding = new System.Windows.Forms.Padding(10);
            this.postsPanel.Size = new System.Drawing.Size(786, 508);
            this.postsPanel.TabIndex = 1;
            // 
            // MainForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 600);
            this.Controls.Add(this.tabControl1);
            this.Name = "MainForm";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Klyer - Feed";
            this.tabControl1.ResumeLayout(false);
            this.tabFeed.ResumeLayout(false);
            this.headerPanel.ResumeLayout(false);
            this.headerPanel.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.TabControl tabControl1;
        private System.Windows.Forms.TabPage tabFeed;
        private System.Windows.Forms.TabPage tabPerfil;
        private System.Windows.Forms.TabPage tabSubir;
        private System.Windows.Forms.Panel headerPanel;
        private System.Windows.Forms.Label titleLabel;
        private System.Windows.Forms.Button btnNewPost;
        private System.Windows.Forms.Label lblUser;
        private System.Windows.Forms.FlowLayoutPanel postsPanel;
    }
}