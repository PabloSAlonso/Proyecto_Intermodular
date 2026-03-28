namespace Klyer
{
    partial class Inicio_de_sesión
    {
        private System.ComponentModel.IContainer components = null;

        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        private void InitializeComponent()
        {
            this.grupoComponentes = new System.Windows.Forms.GroupBox();
            this.lblBienvenida = new System.Windows.Forms.Label();
            this.lblCorreo = new System.Windows.Forms.Label();
            this.textBoxUsuario = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.textBoxPassword = new System.Windows.Forms.TextBox();
            this.btnInicio = new System.Windows.Forms.Button();
            this.lblMensajeRegistro = new System.Windows.Forms.Label();
            this.lblRegistro = new System.Windows.Forms.Label();
            this.lblNotificarCorreo = new System.Windows.Forms.Label();

            this.grupoComponentes.SuspendLayout();
            this.SuspendLayout();

            // ══════════════════════════════════════════════
            // grupoComponentes (panel central)
            // ══════════════════════════════════════════════
            this.grupoComponentes.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(24)))), ((int)(((byte)(24)))), ((int)(((byte)(24)))));
            this.grupoComponentes.Controls.Add(this.lblBienvenida);
            this.grupoComponentes.Controls.Add(this.lblCorreo);
            this.grupoComponentes.Controls.Add(this.textBoxUsuario);
            this.grupoComponentes.Controls.Add(this.label1);
            this.grupoComponentes.Controls.Add(this.textBoxPassword);
            this.grupoComponentes.Controls.Add(this.lblNotificarCorreo);
            this.grupoComponentes.Controls.Add(this.btnInicio);
            this.grupoComponentes.Controls.Add(this.lblMensajeRegistro);
            this.grupoComponentes.Controls.Add(this.lblRegistro);
            this.grupoComponentes.Dock = System.Windows.Forms.DockStyle.Fill;
            this.grupoComponentes.Location = new System.Drawing.Point(0, 0);
            this.grupoComponentes.Name = "grupoComponentes";
            this.grupoComponentes.Size = new System.Drawing.Size(1024, 720);
            this.grupoComponentes.TabIndex = 0;
            this.grupoComponentes.TabStop = false;

            // lblBienvenida
            this.lblBienvenida.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.lblBienvenida.AutoSize = true;
            this.lblBienvenida.Font = new System.Drawing.Font("Segoe UI", 28F, System.Drawing.FontStyle.Bold);
            this.lblBienvenida.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(14)))), ((int)(((byte)(165)))), ((int)(((byte)(233)))));
            this.lblBienvenida.Location = new System.Drawing.Point(340, 150);
            this.lblBienvenida.Name = "lblBienvenida";
            this.lblBienvenida.Size = new System.Drawing.Size(350, 51);
            this.lblBienvenida.TabIndex = 0;
            this.lblBienvenida.Text = "Bienvenido a Klyer";

            // lblCorreo
            this.lblCorreo.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.lblCorreo.AutoSize = true;
            this.lblCorreo.Font = new System.Drawing.Font("Segoe UI", 12F);
            this.lblCorreo.ForeColor = System.Drawing.Color.White;
            this.lblCorreo.Location = new System.Drawing.Point(280, 250);
            this.lblCorreo.Name = "lblCorreo";
            this.lblCorreo.Size = new System.Drawing.Size(160, 21);
            this.lblCorreo.TabIndex = 1;
            this.lblCorreo.Text = "Usuario (Nickname o Email)";

            // textBoxUsuario
            this.textBoxUsuario.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.textBoxUsuario.Font = new System.Drawing.Font("Segoe UI", 12F);
            this.textBoxUsuario.Location = new System.Drawing.Point(280, 278);
            this.textBoxUsuario.Name = "textBoxUsuario";
            this.textBoxUsuario.Size = new System.Drawing.Size(420, 29);
            this.textBoxUsuario.TabIndex = 2;

            // label1
            this.label1.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("Segoe UI", 12F);
            this.label1.ForeColor = System.Drawing.Color.White;
            this.label1.Location = new System.Drawing.Point(280, 325);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(89, 21);
            this.label1.TabIndex = 3;
            this.label1.Text = "Contraseña";

            // textBoxPassword
            this.textBoxPassword.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.textBoxPassword.Font = new System.Drawing.Font("Segoe UI", 12F);
            this.textBoxPassword.Location = new System.Drawing.Point(280, 353);
            this.textBoxPassword.Name = "textBoxPassword";
            this.textBoxPassword.PasswordChar = '●';
            this.textBoxPassword.Size = new System.Drawing.Size(420, 29);
            this.textBoxPassword.TabIndex = 4;

            // lblNotificarCorreo
            this.lblNotificarCorreo.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.lblNotificarCorreo.Font = new System.Drawing.Font("Segoe UI", 10F);
            this.lblNotificarCorreo.ForeColor = System.Drawing.Color.Red;
            this.lblNotificarCorreo.Location = new System.Drawing.Point(280, 400);
            this.lblNotificarCorreo.Name = "lblNotificarCorreo";
            this.lblNotificarCorreo.Size = new System.Drawing.Size(420, 25);
            this.lblNotificarCorreo.TabIndex = 5;
            this.lblNotificarCorreo.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;

            // btnInicio
            this.btnInicio.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.btnInicio.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(14)))), ((int)(((byte)(165)))), ((int)(((byte)(233)))));
            this.btnInicio.FlatAppearance.BorderSize = 0;
            this.btnInicio.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnInicio.Font = new System.Drawing.Font("Segoe UI", 16F, System.Drawing.FontStyle.Bold);
            this.btnInicio.ForeColor = System.Drawing.Color.White;
            this.btnInicio.Location = new System.Drawing.Point(280, 435);
            this.btnInicio.Name = "btnInicio";
            this.btnInicio.Size = new System.Drawing.Size(420, 50);
            this.btnInicio.TabIndex = 6;
            this.btnInicio.Text = "Iniciar Sesión";
            this.btnInicio.UseVisualStyleBackColor = false;
            this.btnInicio.Click += new System.EventHandler(this.btnInicio_Click);
            this.btnInicio.MouseEnter += new System.EventHandler(this.colorBotonInicio);
            this.btnInicio.MouseLeave += new System.EventHandler(this.colorBotonInicio);

            // lblMensajeRegistro
            this.lblMensajeRegistro.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.lblMensajeRegistro.AutoSize = true;
            this.lblMensajeRegistro.Font = new System.Drawing.Font("Segoe UI", 12F);
            this.lblMensajeRegistro.ForeColor = System.Drawing.Color.White;
            this.lblMensajeRegistro.Location = new System.Drawing.Point(410, 510);
            this.lblMensajeRegistro.Name = "lblMensajeRegistro";
            this.lblMensajeRegistro.Size = new System.Drawing.Size(200, 21);
            this.lblMensajeRegistro.TabIndex = 7;
            this.lblMensajeRegistro.Text = "¿Todavía no tienes cuenta?";

            // lblRegistro
            this.lblRegistro.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.lblRegistro.AutoSize = true;
            this.lblRegistro.Cursor = System.Windows.Forms.Cursors.Hand;
            this.lblRegistro.Font = new System.Drawing.Font("Segoe UI", 14F, ((System.Drawing.FontStyle)((System.Drawing.FontStyle.Bold | System.Drawing.FontStyle.Underline))));
            this.lblRegistro.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(14)))), ((int)(((byte)(165)))), ((int)(((byte)(233)))));
            this.lblRegistro.Location = new System.Drawing.Point(440, 540);
            this.lblRegistro.Name = "lblRegistro";
            this.lblRegistro.Size = new System.Drawing.Size(130, 25);
            this.lblRegistro.TabIndex = 8;
            this.lblRegistro.Text = "Regístrate";
            this.lblRegistro.Click += new System.EventHandler(this.lblRegistro_Click);
            this.lblRegistro.MouseEnter += new System.EventHandler(this.colorLabelRegistro);
            this.lblRegistro.MouseLeave += new System.EventHandler(this.colorLabelRegistro);

            // ══════════════════════════════════════════════
            // Inicio_de_sesión Form
            // ══════════════════════════════════════════════
            this.AcceptButton = this.btnInicio;
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(18)))), ((int)(((byte)(18)))), ((int)(((byte)(18)))));
            this.ClientSize = new System.Drawing.Size(1024, 720);
            this.Controls.Add(this.grupoComponentes);
            this.Name = "Inicio_de_sesión";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Klyer - Inicio de sesión";
            this.WindowState = System.Windows.Forms.FormWindowState.Maximized;

            this.grupoComponentes.ResumeLayout(false);
            this.grupoComponentes.PerformLayout();
            this.ResumeLayout(false);
        }

        #endregion

        private System.Windows.Forms.GroupBox grupoComponentes;
        private System.Windows.Forms.Label lblBienvenida;
        private System.Windows.Forms.Label lblCorreo;
        private System.Windows.Forms.TextBox textBoxUsuario;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.TextBox textBoxPassword;
        private System.Windows.Forms.Label lblNotificarCorreo;
        private System.Windows.Forms.Button btnInicio;
        private System.Windows.Forms.Label lblMensajeRegistro;
        private System.Windows.Forms.Label lblRegistro;
    }
}
