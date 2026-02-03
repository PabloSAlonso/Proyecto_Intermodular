namespace Klyer
{
    partial class Registro
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Registro));
            this.lblRegistro = new System.Windows.Forms.Label();
            this.groupBox1 = new System.Windows.Forms.GroupBox();
            this.tbNom = new System.Windows.Forms.TextBox();
            this.tbApellidos = new System.Windows.Forms.TextBox();
            this.tbNick = new System.Windows.Forms.TextBox();
            this.tbCorreo = new System.Windows.Forms.TextBox();
            this.tbPass = new System.Windows.Forms.TextBox();
            this.lblNom = new System.Windows.Forms.Label();
            this.lblApellidos = new System.Windows.Forms.Label();
            this.lblNick = new System.Windows.Forms.Label();
            this.lblCorreo = new System.Windows.Forms.Label();
            this.lblPass = new System.Windows.Forms.Label();
            this.lblMensajeIniciar = new System.Windows.Forms.Label();
            this.lblInicia = new System.Windows.Forms.Label();
            this.btnRegistro = new System.Windows.Forms.Button();
            this.groupBox1.SuspendLayout();
            this.SuspendLayout();
            // 
            // lblRegistro
            // 
            this.lblRegistro.AutoSize = true;
            this.lblRegistro.Font = new System.Drawing.Font("Microsoft Sans Serif", 24F, System.Drawing.FontStyle.Bold);
            this.lblRegistro.ForeColor = System.Drawing.Color.DodgerBlue;
            this.lblRegistro.Location = new System.Drawing.Point(86, 16);
            this.lblRegistro.Name = "lblRegistro";
            this.lblRegistro.Size = new System.Drawing.Size(276, 37);
            this.lblRegistro.TabIndex = 0;
            this.lblRegistro.Text = "Registro en Klyer";
            // 
            // groupBox1
            // 
            this.groupBox1.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(42)))), ((int)(((byte)(41)))), ((int)(((byte)(41)))));
            this.groupBox1.Controls.Add(this.btnRegistro);
            this.groupBox1.Controls.Add(this.lblInicia);
            this.groupBox1.Controls.Add(this.lblMensajeIniciar);
            this.groupBox1.Controls.Add(this.lblPass);
            this.groupBox1.Controls.Add(this.lblCorreo);
            this.groupBox1.Controls.Add(this.lblNick);
            this.groupBox1.Controls.Add(this.lblApellidos);
            this.groupBox1.Controls.Add(this.lblNom);
            this.groupBox1.Controls.Add(this.tbPass);
            this.groupBox1.Controls.Add(this.tbCorreo);
            this.groupBox1.Controls.Add(this.tbNick);
            this.groupBox1.Controls.Add(this.tbApellidos);
            this.groupBox1.Controls.Add(this.tbNom);
            this.groupBox1.Controls.Add(this.lblRegistro);
            this.groupBox1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.groupBox1.Location = new System.Drawing.Point(0, 0);
            this.groupBox1.Name = "groupBox1";
            this.groupBox1.Size = new System.Drawing.Size(459, 444);
            this.groupBox1.TabIndex = 1;
            this.groupBox1.TabStop = false;
            // 
            // tbNom
            // 
            this.tbNom.Location = new System.Drawing.Point(199, 69);
            this.tbNom.Name = "tbNom";
            this.tbNom.Size = new System.Drawing.Size(137, 20);
            this.tbNom.TabIndex = 1;
            // 
            // tbApellidos
            // 
            this.tbApellidos.Location = new System.Drawing.Point(199, 127);
            this.tbApellidos.Name = "tbApellidos";
            this.tbApellidos.Size = new System.Drawing.Size(137, 20);
            this.tbApellidos.TabIndex = 2;
            // 
            // tbNick
            // 
            this.tbNick.Location = new System.Drawing.Point(199, 186);
            this.tbNick.Name = "tbNick";
            this.tbNick.Size = new System.Drawing.Size(137, 20);
            this.tbNick.TabIndex = 3;
            this.tbNick.TextChanged += new System.EventHandler(this.tbNick_TextChanged);
            // 
            // tbCorreo
            // 
            this.tbCorreo.Location = new System.Drawing.Point(199, 239);
            this.tbCorreo.Name = "tbCorreo";
            this.tbCorreo.Size = new System.Drawing.Size(137, 20);
            this.tbCorreo.TabIndex = 4;
            // 
            // tbPass
            // 
            this.tbPass.Location = new System.Drawing.Point(199, 294);
            this.tbPass.Name = "tbPass";
            this.tbPass.PasswordChar = '*';
            this.tbPass.Size = new System.Drawing.Size(137, 20);
            this.tbPass.TabIndex = 5;
            // 
            // lblNom
            // 
            this.lblNom.AutoSize = true;
            this.lblNom.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblNom.ForeColor = System.Drawing.Color.White;
            this.lblNom.Location = new System.Drawing.Point(137, 70);
            this.lblNom.Name = "lblNom";
            this.lblNom.Size = new System.Drawing.Size(56, 16);
            this.lblNom.TabIndex = 6;
            this.lblNom.Text = "Nombre";
            // 
            // lblApellidos
            // 
            this.lblApellidos.AutoSize = true;
            this.lblApellidos.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblApellidos.ForeColor = System.Drawing.Color.White;
            this.lblApellidos.Location = new System.Drawing.Point(129, 127);
            this.lblApellidos.Name = "lblApellidos";
            this.lblApellidos.Size = new System.Drawing.Size(64, 16);
            this.lblApellidos.TabIndex = 7;
            this.lblApellidos.Text = "Apellidos";
            // 
            // lblNick
            // 
            this.lblNick.AutoSize = true;
            this.lblNick.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblNick.ForeColor = System.Drawing.Color.White;
            this.lblNick.Location = new System.Drawing.Point(125, 186);
            this.lblNick.Name = "lblNick";
            this.lblNick.Size = new System.Drawing.Size(68, 16);
            this.lblNick.TabIndex = 8;
            this.lblNick.Text = "Nickname";
            // 
            // lblCorreo
            // 
            this.lblCorreo.AutoSize = true;
            this.lblCorreo.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblCorreo.ForeColor = System.Drawing.Color.White;
            this.lblCorreo.Location = new System.Drawing.Point(76, 239);
            this.lblCorreo.Name = "lblCorreo";
            this.lblCorreo.Size = new System.Drawing.Size(117, 16);
            this.lblCorreo.TabIndex = 9;
            this.lblCorreo.Text = "Correo electrónico";
            // 
            // lblPass
            // 
            this.lblPass.AutoSize = true;
            this.lblPass.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblPass.ForeColor = System.Drawing.Color.White;
            this.lblPass.Location = new System.Drawing.Point(117, 294);
            this.lblPass.Name = "lblPass";
            this.lblPass.Size = new System.Drawing.Size(76, 16);
            this.lblPass.TabIndex = 10;
            this.lblPass.Text = "Contraseña";
            // 
            // lblMensajeIniciar
            // 
            this.lblMensajeIniciar.AutoSize = true;
            this.lblMensajeIniciar.Font = new System.Drawing.Font("Microsoft Sans Serif", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblMensajeIniciar.ForeColor = System.Drawing.Color.White;
            this.lblMensajeIniciar.Location = new System.Drawing.Point(130, 387);
            this.lblMensajeIniciar.Name = "lblMensajeIniciar";
            this.lblMensajeIniciar.Size = new System.Drawing.Size(206, 24);
            this.lblMensajeIniciar.TabIndex = 11;
            this.lblMensajeIniciar.Text = "¿Ya tienes una cuenta?";
            // 
            // lblInicia
            // 
            this.lblInicia.AutoSize = true;
            this.lblInicia.Font = new System.Drawing.Font("Microsoft Sans Serif", 15.75F, ((System.Drawing.FontStyle)((System.Drawing.FontStyle.Bold | System.Drawing.FontStyle.Underline))), System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblInicia.ForeColor = System.Drawing.Color.DodgerBlue;
            this.lblInicia.Location = new System.Drawing.Point(155, 411);
            this.lblInicia.Name = "lblInicia";
            this.lblInicia.Size = new System.Drawing.Size(155, 25);
            this.lblInicia.TabIndex = 12;
            this.lblInicia.Text = "Iniciar Sesión";
            // 
            // btnRegistro
            // 
            this.btnRegistro.BackColor = System.Drawing.Color.DodgerBlue;
            this.btnRegistro.Font = new System.Drawing.Font("Microsoft Sans Serif", 24F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnRegistro.ForeColor = System.Drawing.Color.White;
            this.btnRegistro.Location = new System.Drawing.Point(82, 334);
            this.btnRegistro.Name = "btnRegistro";
            this.btnRegistro.Size = new System.Drawing.Size(293, 50);
            this.btnRegistro.TabIndex = 13;
            this.btnRegistro.Text = "Registrarse";
            this.btnRegistro.UseVisualStyleBackColor = false;
            // 
            // Registro
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(459, 444);
            this.Controls.Add(this.groupBox1);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Name = "Registro";
            this.Text = "Registro";
            this.groupBox1.ResumeLayout(false);
            this.groupBox1.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Label lblRegistro;
        private System.Windows.Forms.GroupBox groupBox1;
        private System.Windows.Forms.TextBox tbPass;
        private System.Windows.Forms.TextBox tbCorreo;
        private System.Windows.Forms.TextBox tbNick;
        private System.Windows.Forms.TextBox tbApellidos;
        private System.Windows.Forms.TextBox tbNom;
        private System.Windows.Forms.Label lblPass;
        private System.Windows.Forms.Label lblCorreo;
        private System.Windows.Forms.Label lblNick;
        private System.Windows.Forms.Label lblApellidos;
        private System.Windows.Forms.Label lblNom;
        private System.Windows.Forms.Label lblInicia;
        private System.Windows.Forms.Label lblMensajeIniciar;
        private System.Windows.Forms.Button btnRegistro;
    }
}