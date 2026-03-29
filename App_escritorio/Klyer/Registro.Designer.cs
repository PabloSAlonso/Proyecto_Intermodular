namespace Klyer
{
    partial class Registro
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
            this.groupBox1 = new System.Windows.Forms.GroupBox();
            this.lblRegistro = new System.Windows.Forms.Label();
            this.lblNom = new System.Windows.Forms.Label();
            this.tbNom = new System.Windows.Forms.TextBox();
            this.lblApellidos = new System.Windows.Forms.Label();
            this.tbApellidos = new System.Windows.Forms.TextBox();
            this.lblNick = new System.Windows.Forms.Label();
            this.tbNick = new System.Windows.Forms.TextBox();
            this.lblCorreo = new System.Windows.Forms.Label();
            this.tbCorreo = new System.Windows.Forms.TextBox();
            this.lblPass = new System.Windows.Forms.Label();
            this.tbPass = new System.Windows.Forms.TextBox();
            this.btnRegistro = new System.Windows.Forms.Button();
            this.lblMensajeIniciar = new System.Windows.Forms.Label();
            this.lblInicia = new System.Windows.Forms.Label();

            this.groupBox1.SuspendLayout();
            this.SuspendLayout();

            // ══════════════════════════════════════════════
            // groupBox1 (panel central)
            // ══════════════════════════════════════════════
            this.groupBox1.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(24)))), ((int)(((byte)(24)))), ((int)(((byte)(24)))));
            this.groupBox1.Controls.Add(this.lblRegistro);
            this.groupBox1.Controls.Add(this.lblNom);
            this.groupBox1.Controls.Add(this.tbNom);
            this.groupBox1.Controls.Add(this.lblApellidos);
            this.groupBox1.Controls.Add(this.tbApellidos);
            this.groupBox1.Controls.Add(this.lblNick);
            this.groupBox1.Controls.Add(this.tbNick);
            this.groupBox1.Controls.Add(this.lblCorreo);
            this.groupBox1.Controls.Add(this.tbCorreo);
            this.groupBox1.Controls.Add(this.lblPass);
            this.groupBox1.Controls.Add(this.tbPass);
            this.groupBox1.Controls.Add(this.btnRegistro);
            this.groupBox1.Controls.Add(this.lblMensajeIniciar);
            this.groupBox1.Controls.Add(this.lblInicia);
            this.groupBox1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.groupBox1.Location = new System.Drawing.Point(0, 0);
            this.groupBox1.Name = "groupBox1";
            this.groupBox1.Size = new System.Drawing.Size(1024, 720);
            this.groupBox1.TabIndex = 0;
            this.groupBox1.TabStop = false;

            // lblRegistro
            this.lblRegistro.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.lblRegistro.AutoSize = true;
            this.lblRegistro.Font = new System.Drawing.Font("Segoe UI", 28F, System.Drawing.FontStyle.Bold);
            this.lblRegistro.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(14)))), ((int)(((byte)(165)))), ((int)(((byte)(233)))));
            this.lblRegistro.Location = new System.Drawing.Point(340, 80);
            this.lblRegistro.Name = "lblRegistro";
            this.lblRegistro.Size = new System.Drawing.Size(340, 51);
            this.lblRegistro.TabIndex = 0;
            this.lblRegistro.Text = "Registro en Klyer";

            // lblNom
            this.lblNom.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.lblNom.AutoSize = true;
            this.lblNom.Font = new System.Drawing.Font("Segoe UI", 11F);
            this.lblNom.ForeColor = System.Drawing.Color.White;
            this.lblNom.Location = new System.Drawing.Point(280, 175);
            this.lblNom.Name = "lblNom";
            this.lblNom.Size = new System.Drawing.Size(64, 20);
            this.lblNom.TabIndex = 1;
            this.lblNom.Text = "Nombre";

            // tbNom
            this.tbNom.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.tbNom.Font = new System.Drawing.Font("Segoe UI", 12F);
            this.tbNom.Location = new System.Drawing.Point(280, 200);
            this.tbNom.Name = "tbNom";
            this.tbNom.Size = new System.Drawing.Size(420, 29);
            this.tbNom.TabIndex = 2;

            // lblApellidos
            this.lblApellidos.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.lblApellidos.AutoSize = true;
            this.lblApellidos.Font = new System.Drawing.Font("Segoe UI", 11F);
            this.lblApellidos.ForeColor = System.Drawing.Color.White;
            this.lblApellidos.Location = new System.Drawing.Point(280, 245);
            this.lblApellidos.Name = "lblApellidos";
            this.lblApellidos.Size = new System.Drawing.Size(73, 20);
            this.lblApellidos.TabIndex = 3;
            this.lblApellidos.Text = "Apellidos";

            // tbApellidos
            this.tbApellidos.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.tbApellidos.Font = new System.Drawing.Font("Segoe UI", 12F);
            this.tbApellidos.Location = new System.Drawing.Point(280, 270);
            this.tbApellidos.Name = "tbApellidos";
            this.tbApellidos.Size = new System.Drawing.Size(420, 29);
            this.tbApellidos.TabIndex = 4;

            // lblNick
            this.lblNick.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.lblNick.AutoSize = true;
            this.lblNick.Font = new System.Drawing.Font("Segoe UI", 11F);
            this.lblNick.ForeColor = System.Drawing.Color.White;
            this.lblNick.Location = new System.Drawing.Point(280, 315);
            this.lblNick.Name = "lblNick";
            this.lblNick.Size = new System.Drawing.Size(76, 20);
            this.lblNick.TabIndex = 5;
            this.lblNick.Text = "Nickname";

            // tbNick
            this.tbNick.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.tbNick.Font = new System.Drawing.Font("Segoe UI", 12F);
            this.tbNick.Location = new System.Drawing.Point(280, 340);
            this.tbNick.Name = "tbNick";
            this.tbNick.Size = new System.Drawing.Size(420, 29);
            this.tbNick.TabIndex = 6;

            // lblCorreo
            this.lblCorreo.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.lblCorreo.AutoSize = true;
            this.lblCorreo.Font = new System.Drawing.Font("Segoe UI", 11F);
            this.lblCorreo.ForeColor = System.Drawing.Color.White;
            this.lblCorreo.Location = new System.Drawing.Point(280, 385);
            this.lblCorreo.Name = "lblCorreo";
            this.lblCorreo.Size = new System.Drawing.Size(137, 20);
            this.lblCorreo.TabIndex = 7;
            this.lblCorreo.Text = "Correo electrónico";

            // tbCorreo
            this.tbCorreo.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.tbCorreo.Font = new System.Drawing.Font("Segoe UI", 12F);
            this.tbCorreo.Location = new System.Drawing.Point(280, 410);
            this.tbCorreo.Name = "tbCorreo";
            this.tbCorreo.Size = new System.Drawing.Size(420, 29);
            this.tbCorreo.TabIndex = 8;

            // lblPass
            this.lblPass.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.lblPass.AutoSize = true;
            this.lblPass.Font = new System.Drawing.Font("Segoe UI", 11F);
            this.lblPass.ForeColor = System.Drawing.Color.White;
            this.lblPass.Location = new System.Drawing.Point(280, 455);
            this.lblPass.Name = "lblPass";
            this.lblPass.Size = new System.Drawing.Size(86, 20);
            this.lblPass.TabIndex = 9;
            this.lblPass.Text = "Contraseña";

            // tbPass
            this.tbPass.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.tbPass.Font = new System.Drawing.Font("Segoe UI", 12F);
            this.tbPass.Location = new System.Drawing.Point(280, 480);
            this.tbPass.Name = "tbPass";
            this.tbPass.PasswordChar = '●';
            this.tbPass.Size = new System.Drawing.Size(420, 29);
            this.tbPass.TabIndex = 10;

            // btnRegistro
            this.btnRegistro.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.btnRegistro.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(14)))), ((int)(((byte)(165)))), ((int)(((byte)(233)))));
            this.btnRegistro.FlatAppearance.BorderSize = 0;
            this.btnRegistro.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnRegistro.Font = new System.Drawing.Font("Segoe UI", 16F, System.Drawing.FontStyle.Bold);
            this.btnRegistro.ForeColor = System.Drawing.Color.White;
            this.btnRegistro.Location = new System.Drawing.Point(280, 530);
            this.btnRegistro.Name = "btnRegistro";
            this.btnRegistro.Size = new System.Drawing.Size(420, 50);
            this.btnRegistro.TabIndex = 11;
            this.btnRegistro.Text = "Registrarse";
            this.btnRegistro.UseVisualStyleBackColor = false;
            this.btnRegistro.Click += new System.EventHandler(this.btnRegistro_Click);
            this.btnRegistro.MouseEnter += new System.EventHandler(this.btnRegistro_Enter);
            this.btnRegistro.MouseLeave += new System.EventHandler(this.btnRegistro_Leave);

            // lblMensajeIniciar
            this.lblMensajeIniciar.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.lblMensajeIniciar.AutoSize = true;
            this.lblMensajeIniciar.Font = new System.Drawing.Font("Segoe UI", 12F);
            this.lblMensajeIniciar.ForeColor = System.Drawing.Color.White;
            this.lblMensajeIniciar.Location = new System.Drawing.Point(400, 605);
            this.lblMensajeIniciar.Name = "lblMensajeIniciar";
            this.lblMensajeIniciar.Size = new System.Drawing.Size(180, 21);
            this.lblMensajeIniciar.TabIndex = 12;
            this.lblMensajeIniciar.Text = "¿Ya tienes una cuenta?";

            // lblInicia
            this.lblInicia.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.lblInicia.AutoSize = true;
            this.lblInicia.Cursor = System.Windows.Forms.Cursors.Hand;
            this.lblInicia.Font = new System.Drawing.Font("Segoe UI", 14F, ((System.Drawing.FontStyle)((System.Drawing.FontStyle.Bold | System.Drawing.FontStyle.Underline))));
            this.lblInicia.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(14)))), ((int)(((byte)(165)))), ((int)(((byte)(233)))));
            this.lblInicia.Location = new System.Drawing.Point(430, 635);
            this.lblInicia.Name = "lblInicia";
            this.lblInicia.Size = new System.Drawing.Size(150, 25);
            this.lblInicia.TabIndex = 13;
            this.lblInicia.Text = "Iniciar Sesión";
            this.lblInicia.Click += new System.EventHandler(this.lblInicia_Click);

            // ══════════════════════════════════════════════
            // Registro Form
            // ══════════════════════════════════════════════
            this.AcceptButton = this.btnRegistro;
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(18)))), ((int)(((byte)(18)))), ((int)(((byte)(18)))));
            this.ClientSize = new System.Drawing.Size(1024, 720);
            this.Controls.Add(this.groupBox1);
            this.Name = "Registro";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Klyer - Registro";
            this.WindowState = System.Windows.Forms.FormWindowState.Maximized;

            this.groupBox1.ResumeLayout(false);
            this.groupBox1.PerformLayout();
            this.ResumeLayout(false);
        }

        #endregion

        private System.Windows.Forms.GroupBox groupBox1;
        private System.Windows.Forms.Label lblRegistro;
        private System.Windows.Forms.Label lblNom;
        private System.Windows.Forms.TextBox tbNom;
        private System.Windows.Forms.Label lblApellidos;
        private System.Windows.Forms.TextBox tbApellidos;
        private System.Windows.Forms.Label lblNick;
        private System.Windows.Forms.TextBox tbNick;
        private System.Windows.Forms.Label lblCorreo;
        private System.Windows.Forms.TextBox tbCorreo;
        private System.Windows.Forms.Label lblPass;
        private System.Windows.Forms.TextBox tbPass;
        private System.Windows.Forms.Button btnRegistro;
        private System.Windows.Forms.Label lblMensajeIniciar;
        private System.Windows.Forms.Label lblInicia;
    }
}
