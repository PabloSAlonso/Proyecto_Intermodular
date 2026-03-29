namespace Klyer
{
    partial class MainForm
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
            this.tabControl1 = new System.Windows.Forms.TabControl();
            this.tabFeed = new System.Windows.Forms.TabPage();
            this.tabPerfil = new System.Windows.Forms.TabPage();

            // ── Controles del Header (compartido entre pestañas) ──
            this.headerPanel = new System.Windows.Forms.Panel();
            this.titleLabel = new System.Windows.Forms.Label();
            this.btnNewPost = new System.Windows.Forms.Button();
            this.lblUser = new System.Windows.Forms.Label();
            this.btnCerrarSesionHeader = new System.Windows.Forms.Button();

            // ── Controles del Feed ──
            this.postsPanel = new System.Windows.Forms.FlowLayoutPanel();

            // ── Controles del Perfil ──
            this.pnlPerfilHeader = new System.Windows.Forms.Panel();
            this.picPerfilAvatar = new System.Windows.Forms.PictureBox();
            this.lblPerfilNombre = new System.Windows.Forms.Label();
            this.lblPerfilNick = new System.Windows.Forms.Label();
            this.lblPerfilEmail = new System.Windows.Forms.Label();
            this.btnEditarPerfil = new System.Windows.Forms.Button();
            this.btnEliminarCuenta = new System.Windows.Forms.Button();
            this.lblTituloMisPosts = new System.Windows.Forms.Label();
            this.flpMisPublicaciones = new System.Windows.Forms.FlowLayoutPanel();

            // ── Controles de Subir Publicación ──
            this.btnCrearPublicacion = new System.Windows.Forms.Button();

            this.tabControl1.SuspendLayout();
            this.tabFeed.SuspendLayout();
            this.tabPerfil.SuspendLayout();
            this.headerPanel.SuspendLayout();
            this.pnlPerfilHeader.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.picPerfilAvatar)).BeginInit();
            this.SuspendLayout();

            // ══════════════════════════════════════════════
            // tabControl1
            // ══════════════════════════════════════════════
            this.tabControl1.Controls.Add(this.tabFeed);
            this.tabControl1.Controls.Add(this.tabPerfil);
            this.tabControl1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tabControl1.Location = new System.Drawing.Point(0, 0);
            this.tabControl1.Name = "tabControl1";
            this.tabControl1.SelectedIndex = 0;
            this.tabControl1.Size = new System.Drawing.Size(1024, 720);
            this.tabControl1.TabIndex = 0;
            this.tabControl1.SelectedIndexChanged += new System.EventHandler(this.tabControl1_SelectedIndexChanged);

            // ══════════════════════════════════════════════
            // HEADER PANEL (común a todas las pestañas)
            // ══════════════════════════════════════════════
            this.headerPanel.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(14)))), ((int)(((byte)(165)))), ((int)(((byte)(233)))));
            this.headerPanel.Controls.Add(this.btnCerrarSesionHeader);
            this.headerPanel.Controls.Add(this.lblUser);
            this.headerPanel.Controls.Add(this.btnNewPost);
            this.headerPanel.Controls.Add(this.titleLabel);
            this.headerPanel.Dock = System.Windows.Forms.DockStyle.Top;
            this.headerPanel.Location = new System.Drawing.Point(0, 0);
            this.headerPanel.Name = "headerPanel";
            this.headerPanel.Size = new System.Drawing.Size(1024, 55);
            this.headerPanel.TabIndex = 0;

            // titleLabel
            this.titleLabel.AutoSize = true;
            this.titleLabel.Font = new System.Drawing.Font("Arial", 18F, System.Drawing.FontStyle.Bold);
            this.titleLabel.ForeColor = System.Drawing.Color.White;
            this.titleLabel.Location = new System.Drawing.Point(15, 13);
            this.titleLabel.Name = "titleLabel";
            this.titleLabel.Size = new System.Drawing.Size(65, 29);
            this.titleLabel.TabIndex = 0;
            this.titleLabel.Text = "Klyer";

            // btnNewPost
            this.btnNewPost.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.btnNewPost.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(14)))), ((int)(((byte)(165)))), ((int)(((byte)(233)))));
            this.btnNewPost.FlatAppearance.BorderSize = 0;
            this.btnNewPost.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnNewPost.Font = new System.Drawing.Font("Segoe UI", 10F);
            this.btnNewPost.ForeColor = System.Drawing.Color.White;
            this.btnNewPost.Location = new System.Drawing.Point(780, 10);
            this.btnNewPost.Name = "btnNewPost";
            this.btnNewPost.Size = new System.Drawing.Size(140, 35);
            this.btnNewPost.TabIndex = 1;
            this.btnNewPost.Text = "+ Nueva Publicación";
            this.btnNewPost.UseVisualStyleBackColor = false;
            this.btnNewPost.Click += new System.EventHandler(this.btnNewPost_Click);
            this.btnNewPost.MouseEnter += new System.EventHandler(this.btnHover_Enter);
            this.btnNewPost.MouseLeave += new System.EventHandler(this.btnPrimary_Leave);

            // lblUser
            this.lblUser.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.lblUser.Font = new System.Drawing.Font("Segoe UI", 10F);
            this.lblUser.ForeColor = System.Drawing.Color.White;
            this.lblUser.Location = new System.Drawing.Point(500, 17);
            this.lblUser.Name = "lblUser";
            this.lblUser.Size = new System.Drawing.Size(270, 20);
            this.lblUser.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            this.lblUser.TabIndex = 2;
            this.lblUser.Text = "Hola, ";

            // btnCerrarSesionHeader
            this.btnCerrarSesionHeader.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.btnCerrarSesionHeader.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(220)))), ((int)(((byte)(53)))), ((int)(((byte)(69)))));
            this.btnCerrarSesionHeader.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnCerrarSesionHeader.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.btnCerrarSesionHeader.ForeColor = System.Drawing.Color.White;
            this.btnCerrarSesionHeader.Location = new System.Drawing.Point(930, 10);
            this.btnCerrarSesionHeader.Name = "btnCerrarSesionHeader";
            this.btnCerrarSesionHeader.Size = new System.Drawing.Size(80, 35);
            this.btnCerrarSesionHeader.TabIndex = 3;
            this.btnCerrarSesionHeader.Text = "Salir";
            this.btnCerrarSesionHeader.UseVisualStyleBackColor = false;
            this.btnCerrarSesionHeader.Click += new System.EventHandler(this.btnCerrarSesion_Click);
            this.btnCerrarSesionHeader.MouseEnter += new System.EventHandler(this.btnHover_Enter);
            this.btnCerrarSesionHeader.MouseLeave += new System.EventHandler(this.btnDanger_Leave);

            // ══════════════════════════════════════════════
            // TAB: FEED
            // ══════════════════════════════════════════════
            this.tabFeed.Controls.Add(this.postsPanel);
            this.tabFeed.Controls.Add(this.headerPanel);
            this.tabFeed.Location = new System.Drawing.Point(4, 22);
            this.tabFeed.Name = "tabFeed";
            this.tabFeed.Padding = new System.Windows.Forms.Padding(0);
            this.tabFeed.Size = new System.Drawing.Size(1016, 694);
            this.tabFeed.TabIndex = 0;
            this.tabFeed.Text = "  Feed  ";
            this.tabFeed.UseVisualStyleBackColor = true;

            // postsPanel (scrollable)
            this.postsPanel.AutoScroll = true;
            this.postsPanel.Dock = System.Windows.Forms.DockStyle.Fill;
            this.postsPanel.Location = new System.Drawing.Point(0, 55);
            this.postsPanel.Name = "postsPanel";
            this.postsPanel.Padding = new System.Windows.Forms.Padding(20, 10, 20, 10);
            this.postsPanel.Size = new System.Drawing.Size(1016, 639);
            this.postsPanel.TabIndex = 1;
            this.postsPanel.WrapContents = false;
            this.postsPanel.FlowDirection = System.Windows.Forms.FlowDirection.TopDown;

            // ══════════════════════════════════════════════
            // TAB: PERFIL
            // ══════════════════════════════════════════════
            this.tabPerfil.Controls.Add(this.flpMisPublicaciones);
            this.tabPerfil.Controls.Add(this.pnlPerfilHeader);
            this.tabPerfil.Location = new System.Drawing.Point(4, 22);
            this.tabPerfil.Name = "tabPerfil";
            this.tabPerfil.Padding = new System.Windows.Forms.Padding(0);
            this.tabPerfil.Size = new System.Drawing.Size(1016, 694);
            this.tabPerfil.TabIndex = 1;
            this.tabPerfil.Text = "  Mi Perfil  ";
            this.tabPerfil.UseVisualStyleBackColor = true;

            // ── pnlPerfilHeader ──
            this.pnlPerfilHeader.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(30)))), ((int)(((byte)(30)))), ((int)(((byte)(30)))));
            this.pnlPerfilHeader.Controls.Add(this.btnCrearPublicacion);
            this.pnlPerfilHeader.Controls.Add(this.lblTituloMisPosts);
            this.pnlPerfilHeader.Controls.Add(this.btnEliminarCuenta);
            this.pnlPerfilHeader.Controls.Add(this.btnEditarPerfil);
            this.pnlPerfilHeader.Controls.Add(this.lblPerfilEmail);
            this.pnlPerfilHeader.Controls.Add(this.lblPerfilNick);
            this.pnlPerfilHeader.Controls.Add(this.lblPerfilNombre);
            this.pnlPerfilHeader.Controls.Add(this.picPerfilAvatar);
            this.pnlPerfilHeader.Dock = System.Windows.Forms.DockStyle.Top;
            this.pnlPerfilHeader.Location = new System.Drawing.Point(0, 0);
            this.pnlPerfilHeader.Name = "pnlPerfilHeader";
            this.pnlPerfilHeader.Padding = new System.Windows.Forms.Padding(40, 25, 40, 15);
            this.pnlPerfilHeader.Size = new System.Drawing.Size(1016, 170);
            this.pnlPerfilHeader.TabIndex = 0;

            // picPerfilAvatar
            this.picPerfilAvatar.Location = new System.Drawing.Point(40, 25);
            this.picPerfilAvatar.Name = "picPerfilAvatar";
            this.picPerfilAvatar.Size = new System.Drawing.Size(100, 100);
            this.picPerfilAvatar.SizeMode = System.Windows.Forms.PictureBoxSizeMode.Zoom;
            this.picPerfilAvatar.TabIndex = 0;
            this.picPerfilAvatar.TabStop = false;

            // lblPerfilNombre
            this.lblPerfilNombre.AutoSize = true;
            this.lblPerfilNombre.Font = new System.Drawing.Font("Segoe UI", 20F, System.Drawing.FontStyle.Bold);
            this.lblPerfilNombre.ForeColor = System.Drawing.Color.White;
            this.lblPerfilNombre.Location = new System.Drawing.Point(160, 25);
            this.lblPerfilNombre.Name = "lblPerfilNombre";
            this.lblPerfilNombre.Size = new System.Drawing.Size(220, 37);
            this.lblPerfilNombre.TabIndex = 1;
            this.lblPerfilNombre.Text = "Nombre Apellido";

            // lblPerfilNick
            this.lblPerfilNick.AutoSize = true;
            this.lblPerfilNick.Font = new System.Drawing.Font("Segoe UI", 12F);
            this.lblPerfilNick.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(148)))), ((int)(((byte)(163)))), ((int)(((byte)(184)))));
            this.lblPerfilNick.Location = new System.Drawing.Point(162, 68);
            this.lblPerfilNick.Name = "lblPerfilNick";
            this.lblPerfilNick.Size = new System.Drawing.Size(100, 21);
            this.lblPerfilNick.TabIndex = 2;
            this.lblPerfilNick.Text = "@nickname";

            // lblPerfilEmail
            this.lblPerfilEmail.AutoSize = true;
            this.lblPerfilEmail.Font = new System.Drawing.Font("Segoe UI", 10F);
            this.lblPerfilEmail.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(100)))), ((int)(((byte)(116)))), ((int)(((byte)(139)))));
            this.lblPerfilEmail.Location = new System.Drawing.Point(162, 92);
            this.lblPerfilEmail.Name = "lblPerfilEmail";
            this.lblPerfilEmail.Size = new System.Drawing.Size(150, 19);
            this.lblPerfilEmail.TabIndex = 3;
            this.lblPerfilEmail.Text = "email@ejemplo.com";

            // btnEditarPerfil
            this.btnEditarPerfil.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.btnEditarPerfil.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(14)))), ((int)(((byte)(165)))), ((int)(((byte)(233)))));
            this.btnEditarPerfil.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnEditarPerfil.Font = new System.Drawing.Font("Segoe UI", 10F);
            this.btnEditarPerfil.ForeColor = System.Drawing.Color.White;
            this.btnEditarPerfil.Location = new System.Drawing.Point(680, 35);
            this.btnEditarPerfil.Name = "btnEditarPerfil";
            this.btnEditarPerfil.Size = new System.Drawing.Size(130, 35);
            this.btnEditarPerfil.TabIndex = 4;
            this.btnEditarPerfil.Text = "Editar perfil";
            this.btnEditarPerfil.UseVisualStyleBackColor = false;
            this.btnEditarPerfil.Click += new System.EventHandler(this.btnEditarPerfil_Click);
            this.btnEditarPerfil.MouseEnter += new System.EventHandler(this.btnHover_Enter);
            this.btnEditarPerfil.MouseLeave += new System.EventHandler(this.btnPrimary_Leave);

            // btnEliminarCuenta
            this.btnEliminarCuenta.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.btnEliminarCuenta.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(239)))), ((int)(((byte)(68)))), ((int)(((byte)(68)))));
            this.btnEliminarCuenta.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnEliminarCuenta.Font = new System.Drawing.Font("Segoe UI", 10F);
            this.btnEliminarCuenta.ForeColor = System.Drawing.Color.White;
            this.btnEliminarCuenta.Location = new System.Drawing.Point(820, 35);
            this.btnEliminarCuenta.Name = "btnEliminarCuenta";
            this.btnEliminarCuenta.Size = new System.Drawing.Size(145, 35);
            this.btnEliminarCuenta.TabIndex = 5;
            this.btnEliminarCuenta.Text = "Eliminar cuenta";
            this.btnEliminarCuenta.UseVisualStyleBackColor = false;
            this.btnEliminarCuenta.Click += new System.EventHandler(this.btnEliminarCuenta_Click);
            this.btnEliminarCuenta.MouseEnter += new System.EventHandler(this.btnHover_Enter);
            this.btnEliminarCuenta.MouseLeave += new System.EventHandler(this.btnDanger_Leave);

            // btnCrearPublicacion (debajo de editar/eliminar)
            this.btnCrearPublicacion.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.btnCrearPublicacion.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(14)))), ((int)(((byte)(165)))), ((int)(((byte)(233)))));
            this.btnCrearPublicacion.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnCrearPublicacion.Font = new System.Drawing.Font("Segoe UI", 10F, System.Drawing.FontStyle.Bold);
            this.btnCrearPublicacion.ForeColor = System.Drawing.Color.White;
            this.btnCrearPublicacion.Location = new System.Drawing.Point(680, 80);
            this.btnCrearPublicacion.Name = "btnCrearPublicacion";
            this.btnCrearPublicacion.Size = new System.Drawing.Size(285, 35);
            this.btnCrearPublicacion.TabIndex = 7;
            this.btnCrearPublicacion.Text = "+ Nueva Publicación";
            this.btnCrearPublicacion.UseVisualStyleBackColor = false;
            this.btnCrearPublicacion.Click += new System.EventHandler(this.btnNewPost_Click);
            this.btnCrearPublicacion.MouseEnter += new System.EventHandler(this.btnHover_Enter);
            this.btnCrearPublicacion.MouseLeave += new System.EventHandler(this.btnPrimary_Leave);

            // lblTituloMisPosts
            this.lblTituloMisPosts.AutoSize = true;
            this.lblTituloMisPosts.Font = new System.Drawing.Font("Segoe UI", 14F, System.Drawing.FontStyle.Bold);
            this.lblTituloMisPosts.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(14)))), ((int)(((byte)(165)))), ((int)(((byte)(233)))));
            this.lblTituloMisPosts.Location = new System.Drawing.Point(40, 140);
            this.lblTituloMisPosts.Name = "lblTituloMisPosts";
            this.lblTituloMisPosts.Size = new System.Drawing.Size(170, 25);
            this.lblTituloMisPosts.TabIndex = 6;
            this.lblTituloMisPosts.Text = "Mis Publicaciones";

            // flpMisPublicaciones (scrollable)
            this.flpMisPublicaciones.AutoScroll = true;
            this.flpMisPublicaciones.Dock = System.Windows.Forms.DockStyle.Fill;
            this.flpMisPublicaciones.Location = new System.Drawing.Point(0, 170);
            this.flpMisPublicaciones.Name = "flpMisPublicaciones";
            this.flpMisPublicaciones.Padding = new System.Windows.Forms.Padding(20, 10, 20, 10);
            this.flpMisPublicaciones.Size = new System.Drawing.Size(1016, 524);
            this.flpMisPublicaciones.TabIndex = 1;
            this.flpMisPublicaciones.WrapContents = false;
            this.flpMisPublicaciones.FlowDirection = System.Windows.Forms.FlowDirection.TopDown;

            // ══════════════════════════════════════════════
            // MainForm
            // ══════════════════════════════════════════════
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1024, 720);
            this.Controls.Add(this.tabControl1);
            this.Name = "MainForm";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Klyer";
            this.WindowState = System.Windows.Forms.FormWindowState.Maximized;

            this.tabControl1.ResumeLayout(false);
            this.tabFeed.ResumeLayout(false);
            this.tabPerfil.ResumeLayout(false);
            this.headerPanel.ResumeLayout(false);
            this.headerPanel.PerformLayout();
            this.pnlPerfilHeader.ResumeLayout(false);
            this.pnlPerfilHeader.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.picPerfilAvatar)).EndInit();
            this.ResumeLayout(false);
        }

        #endregion

        // ── Controles principales ──
        private System.Windows.Forms.TabControl tabControl1;
        private System.Windows.Forms.TabPage tabFeed;
        private System.Windows.Forms.TabPage tabPerfil;

        // ── Header ──
        private System.Windows.Forms.Panel headerPanel;
        private System.Windows.Forms.Label titleLabel;
        private System.Windows.Forms.Button btnNewPost;
        private System.Windows.Forms.Label lblUser;
        private System.Windows.Forms.Button btnCerrarSesionHeader;

        // ── Feed ──
        private System.Windows.Forms.FlowLayoutPanel postsPanel;

        // ── Perfil ──
        private System.Windows.Forms.Panel pnlPerfilHeader;
        private System.Windows.Forms.PictureBox picPerfilAvatar;
        private System.Windows.Forms.Label lblPerfilNombre;
        private System.Windows.Forms.Label lblPerfilNick;
        private System.Windows.Forms.Label lblPerfilEmail;
        private System.Windows.Forms.Button btnEditarPerfil;
        private System.Windows.Forms.Button btnEliminarCuenta;
        private System.Windows.Forms.Label lblTituloMisPosts;
        private System.Windows.Forms.FlowLayoutPanel flpMisPublicaciones;

        // ── Subir Publicación ──
        private System.Windows.Forms.Button btnCrearPublicacion;
    }
}
