namespace Klyer
{
    partial class Inicio_de_sesión
    {
        /// <summary>
        /// Variable del diseñador necesaria.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Limpiar los recursos que se estén usando.
        /// </summary>
        /// <param name="disposing">true si los recursos administrados se deben desechar; false en caso contrario.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Código generado por el Diseñador de Windows Forms

        /// <summary>
        /// Método necesario para admitir el Diseñador. No se puede modificar
        /// el contenido de este método con el editor de código.
        /// </summary>
        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Inicio_de_sesión));
            this.lblCorreo = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.textBox1 = new System.Windows.Forms.TextBox();
            this.textBox2 = new System.Windows.Forms.TextBox();
            this.lblNotificarCorreo = new System.Windows.Forms.Label();
            this.lblMensajeRegistro = new System.Windows.Forms.Label();
            this.lblRegistro = new System.Windows.Forms.Label();
            this.lblBienvenida = new System.Windows.Forms.Label();
            this.btnInicio = new System.Windows.Forms.Button();
            this.grupoComponentes = new System.Windows.Forms.GroupBox();
            this.grupoComponentes.SuspendLayout();
            this.SuspendLayout();
            // 
            // lblCorreo
            // 
            this.lblCorreo.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.lblCorreo.AutoSize = true;
            this.lblCorreo.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblCorreo.Location = new System.Drawing.Point(18, 110);
            this.lblCorreo.Name = "lblCorreo";
            this.lblCorreo.Size = new System.Drawing.Size(140, 20);
            this.lblCorreo.TabIndex = 1;
            this.lblCorreo.Text = "Correo Electrónico";
            // 
            // label1
            // 
            this.label1.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label1.Location = new System.Drawing.Point(31, 147);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(92, 20);
            this.label1.TabIndex = 3;
            this.label1.Text = "Contraseña";
            // 
            // textBox1
            // 
            this.textBox1.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.textBox1.Location = new System.Drawing.Point(166, 112);
            this.textBox1.Name = "textBox1";
            this.textBox1.Size = new System.Drawing.Size(262, 20);
            this.textBox1.TabIndex = 2;
            // 
            // textBox2
            // 
            this.textBox2.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.textBox2.Location = new System.Drawing.Point(166, 150);
            this.textBox2.Name = "textBox2";
            this.textBox2.PasswordChar = '*';
            this.textBox2.Size = new System.Drawing.Size(262, 20);
            this.textBox2.TabIndex = 4;
            // 
            // lblNotificarCorreo
            // 
            this.lblNotificarCorreo.AutoSize = true;
            this.lblNotificarCorreo.ForeColor = System.Drawing.Color.Red;
            this.lblNotificarCorreo.Location = new System.Drawing.Point(385, 64);
            this.lblNotificarCorreo.Name = "lblNotificarCorreo";
            this.lblNotificarCorreo.Size = new System.Drawing.Size(7, 13);
            this.lblNotificarCorreo.TabIndex = 4;
            this.lblNotificarCorreo.Text = "\r\n";
            // 
            // lblMensajeRegistro
            // 
            this.lblMensajeRegistro.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.lblMensajeRegistro.AutoSize = true;
            this.lblMensajeRegistro.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblMensajeRegistro.Location = new System.Drawing.Point(135, 311);
            this.lblMensajeRegistro.Name = "lblMensajeRegistro";
            this.lblMensajeRegistro.Size = new System.Drawing.Size(204, 20);
            this.lblMensajeRegistro.TabIndex = 6;
            this.lblMensajeRegistro.Text = "¿Todavia no tienes cuenta?";
            // 
            // lblRegistro
            // 
            this.lblRegistro.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.lblRegistro.AutoSize = true;
            this.lblRegistro.Font = new System.Drawing.Font("Microsoft Sans Serif", 14.25F, ((System.Drawing.FontStyle)((System.Drawing.FontStyle.Bold | System.Drawing.FontStyle.Underline))), System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblRegistro.ForeColor = System.Drawing.Color.DodgerBlue;
            this.lblRegistro.Location = new System.Drawing.Point(180, 331);
            this.lblRegistro.Name = "lblRegistro";
            this.lblRegistro.Size = new System.Drawing.Size(103, 24);
            this.lblRegistro.TabIndex = 7;
            this.lblRegistro.Text = "Registrate";
            this.lblRegistro.Click += new System.EventHandler(this.lblRegistro_Click);
            this.lblRegistro.MouseEnter += new System.EventHandler(this.colorLabelRegistro);
            this.lblRegistro.MouseLeave += new System.EventHandler(this.colorLabelRegistro);
            // 
            // lblBienvenida
            // 
            this.lblBienvenida.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.lblBienvenida.AutoSize = true;
            this.lblBienvenida.Font = new System.Drawing.Font("Microsoft Sans Serif", 24F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblBienvenida.ForeColor = System.Drawing.SystemColors.Highlight;
            this.lblBienvenida.Location = new System.Drawing.Point(84, 47);
            this.lblBienvenida.Name = "lblBienvenida";
            this.lblBienvenida.Size = new System.Drawing.Size(298, 37);
            this.lblBienvenida.TabIndex = 0;
            this.lblBienvenida.Text = "Bienvenido a Klyer";
            // 
            // btnInicio
            // 
            this.btnInicio.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.btnInicio.BackColor = System.Drawing.Color.DodgerBlue;
            this.btnInicio.Font = new System.Drawing.Font("Microsoft Sans Serif", 24F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnInicio.ForeColor = System.Drawing.Color.White;
            this.btnInicio.Location = new System.Drawing.Point(91, 202);
            this.btnInicio.Name = "btnInicio";
            this.btnInicio.Size = new System.Drawing.Size(291, 51);
            this.btnInicio.TabIndex = 5;
            this.btnInicio.Text = "Iniciar Sesión";
            this.btnInicio.UseVisualStyleBackColor = false;
            this.btnInicio.MouseEnter += new System.EventHandler(this.colorBotonInicio);
            this.btnInicio.MouseLeave += new System.EventHandler(this.colorBotonInicio);
            // 
            // grupoComponentes
            // 
            this.grupoComponentes.Controls.Add(this.lblBienvenida);
            this.grupoComponentes.Controls.Add(this.btnInicio);
            this.grupoComponentes.Controls.Add(this.lblCorreo);
            this.grupoComponentes.Controls.Add(this.label1);
            this.grupoComponentes.Controls.Add(this.lblRegistro);
            this.grupoComponentes.Controls.Add(this.textBox1);
            this.grupoComponentes.Controls.Add(this.lblMensajeRegistro);
            this.grupoComponentes.Controls.Add(this.textBox2);
            this.grupoComponentes.Dock = System.Windows.Forms.DockStyle.Fill;
            this.grupoComponentes.Location = new System.Drawing.Point(0, 0);
            this.grupoComponentes.Name = "grupoComponentes";
            this.grupoComponentes.Size = new System.Drawing.Size(475, 411);
            this.grupoComponentes.TabIndex = 8;
            this.grupoComponentes.TabStop = false;
            // 
            // Inicio_de_sesión
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.White;
            this.ClientSize = new System.Drawing.Size(475, 411);
            this.Controls.Add(this.grupoComponentes);
            this.Controls.Add(this.lblNotificarCorreo);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Name = "Inicio_de_sesión";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Inicio de sesión Klyer";
            this.grupoComponentes.ResumeLayout(false);
            this.grupoComponentes.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label lblCorreo;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.TextBox textBox1;
        private System.Windows.Forms.TextBox textBox2;
        private System.Windows.Forms.Label lblNotificarCorreo;
        private System.Windows.Forms.Label lblMensajeRegistro;
        private System.Windows.Forms.Label lblRegistro;
        private System.Windows.Forms.Label lblBienvenida;
        private System.Windows.Forms.Button btnInicio;
        private System.Windows.Forms.GroupBox grupoComponentes;
    }
}

