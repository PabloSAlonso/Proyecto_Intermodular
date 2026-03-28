namespace Klyer
{
    partial class NuevaPublicacionForm
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
            this.mainPanel = new System.Windows.Forms.Panel();
            this.lblTitle = new System.Windows.Forms.Label();
            this.lblDescripcion = new System.Windows.Forms.Label();
            this.txtDescripcion = new System.Windows.Forms.TextBox();
            this.lblImagen = new System.Windows.Forms.Label();
            this.btnSeleccionarImagen = new System.Windows.Forms.Button();
            this.lblImagenSeleccionada = new System.Windows.Forms.Label();
            this.picPreview = new System.Windows.Forms.PictureBox();
            this.btnPanel = new System.Windows.Forms.Panel();
            this.btnCancelar = new System.Windows.Forms.Button();
            this.btnPublicar = new System.Windows.Forms.Button();

            this.mainPanel.SuspendLayout();
            this.btnPanel.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.picPreview)).BeginInit();
            this.SuspendLayout();

            // ══════════════════════════════════════════════
            // btnPanel (Bottom - se define primero para que el Dock funcione)
            // ══════════════════════════════════════════════
            this.btnPanel.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(240)))), ((int)(((byte)(240)))), ((int)(((byte)(240)))));
            this.btnPanel.Controls.Add(this.btnCancelar);
            this.btnPanel.Controls.Add(this.btnPublicar);
            this.btnPanel.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.btnPanel.Location = new System.Drawing.Point(0, 660);
            this.btnPanel.Name = "btnPanel";
            this.btnPanel.Padding = new System.Windows.Forms.Padding(20);
            this.btnPanel.Size = new System.Drawing.Size(1024, 60);
            this.btnPanel.TabIndex = 7;

            // btnCancelar
            this.btnCancelar.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.btnCancelar.Font = new System.Drawing.Font("Segoe UI", 11F);
            this.btnCancelar.Location = new System.Drawing.Point(780, 12);
            this.btnCancelar.Name = "btnCancelar";
            this.btnCancelar.Size = new System.Drawing.Size(100, 36);
            this.btnCancelar.TabIndex = 8;
            this.btnCancelar.Text = "Cancelar";
            this.btnCancelar.UseVisualStyleBackColor = true;

            // btnPublicar
            this.btnPublicar.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.btnPublicar.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(14)))), ((int)(((byte)(165)))), ((int)(((byte)(233)))));
            this.btnPublicar.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnPublicar.Font = new System.Drawing.Font("Segoe UI", 11F, System.Drawing.FontStyle.Bold);
            this.btnPublicar.ForeColor = System.Drawing.Color.White;
            this.btnPublicar.Location = new System.Drawing.Point(890, 12);
            this.btnPublicar.Name = "btnPublicar";
            this.btnPublicar.Size = new System.Drawing.Size(100, 36);
            this.btnPublicar.TabIndex = 9;
            this.btnPublicar.Text = "Publicar";
            this.btnPublicar.UseVisualStyleBackColor = false;
            this.btnPublicar.Click += new System.EventHandler(this.btnPublicar_Click);

            // ══════════════════════════════════════════════
            // mainPanel (Fill - contiene todos los controles de contenido)
            // ══════════════════════════════════════════════
            this.mainPanel.AutoScroll = true;
            this.mainPanel.Controls.Add(this.lblTitle);
            this.mainPanel.Controls.Add(this.lblDescripcion);
            this.mainPanel.Controls.Add(this.txtDescripcion);
            this.mainPanel.Controls.Add(this.lblImagen);
            this.mainPanel.Controls.Add(this.btnSeleccionarImagen);
            this.mainPanel.Controls.Add(this.lblImagenSeleccionada);
            this.mainPanel.Controls.Add(this.picPreview);
            this.mainPanel.Dock = System.Windows.Forms.DockStyle.Fill;
            this.mainPanel.Location = new System.Drawing.Point(0, 0);
            this.mainPanel.Name = "mainPanel";
            this.mainPanel.Padding = new System.Windows.Forms.Padding(30);
            this.mainPanel.Size = new System.Drawing.Size(1024, 660);
            this.mainPanel.TabIndex = 0;

            // lblTitle
            this.lblTitle.AutoSize = true;
            this.lblTitle.Font = new System.Drawing.Font("Segoe UI", 20F, System.Drawing.FontStyle.Bold);
            this.lblTitle.Location = new System.Drawing.Point(30, 30);
            this.lblTitle.Name = "lblTitle";
            this.lblTitle.Size = new System.Drawing.Size(310, 37);
            this.lblTitle.TabIndex = 0;
            this.lblTitle.Text = "Crear Nueva Publicación";

            // lblDescripcion
            this.lblDescripcion.AutoSize = true;
            this.lblDescripcion.Font = new System.Drawing.Font("Segoe UI", 11F);
            this.lblDescripcion.Location = new System.Drawing.Point(32, 90);
            this.lblDescripcion.Name = "lblDescripcion";
            this.lblDescripcion.Size = new System.Drawing.Size(91, 20);
            this.lblDescripcion.TabIndex = 1;
            this.lblDescripcion.Text = "Descripción:";

            // txtDescripcion
            this.txtDescripcion.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left) | System.Windows.Forms.AnchorStyles.Right)));
            this.txtDescripcion.Font = new System.Drawing.Font("Segoe UI", 12F);
            this.txtDescripcion.Location = new System.Drawing.Point(30, 115);
            this.txtDescripcion.Multiline = true;
            this.txtDescripcion.Name = "txtDescripcion";
            this.txtDescripcion.Size = new System.Drawing.Size(930, 120);
            this.txtDescripcion.TabIndex = 2;

            // lblImagen
            this.lblImagen.AutoSize = true;
            this.lblImagen.Font = new System.Drawing.Font("Segoe UI", 11F);
            this.lblImagen.Location = new System.Drawing.Point(32, 255);
            this.lblImagen.Name = "lblImagen";
            this.lblImagen.Size = new System.Drawing.Size(125, 20);
            this.lblImagen.TabIndex = 3;
            this.lblImagen.Text = "Imagen (opcional):";

            // btnSeleccionarImagen
            this.btnSeleccionarImagen.Font = new System.Drawing.Font("Segoe UI", 10F);
            this.btnSeleccionarImagen.Location = new System.Drawing.Point(30, 280);
            this.btnSeleccionarImagen.Name = "btnSeleccionarImagen";
            this.btnSeleccionarImagen.Size = new System.Drawing.Size(160, 35);
            this.btnSeleccionarImagen.TabIndex = 4;
            this.btnSeleccionarImagen.Text = "Seleccionar Imagen";
            this.btnSeleccionarImagen.UseVisualStyleBackColor = true;
            this.btnSeleccionarImagen.Click += new System.EventHandler(this.btnSeleccionarImagen_Click);

            // lblImagenSeleccionada
            this.lblImagenSeleccionada.AutoSize = true;
            this.lblImagenSeleccionada.Font = new System.Drawing.Font("Segoe UI", 10F);
            this.lblImagenSeleccionada.ForeColor = System.Drawing.Color.Gray;
            this.lblImagenSeleccionada.Location = new System.Drawing.Point(200, 288);
            this.lblImagenSeleccionada.Name = "lblImagenSeleccionada";
            this.lblImagenSeleccionada.Size = new System.Drawing.Size(190, 19);
            this.lblImagenSeleccionada.TabIndex = 5;
            this.lblImagenSeleccionada.Text = "Ninguna imagen seleccionada";

            // picPreview
            this.picPreview.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.picPreview.Location = new System.Drawing.Point(30, 325);
            this.picPreview.Name = "picPreview";
            this.picPreview.Size = new System.Drawing.Size(930, 280);
            this.picPreview.SizeMode = System.Windows.Forms.PictureBoxSizeMode.Zoom;
            this.picPreview.TabIndex = 6;
            this.picPreview.TabStop = false;
            this.picPreview.Visible = false;

            // ══════════════════════════════════════════════
            // NuevaPublicacionForm
            // ══════════════════════════════════════════════
            this.AcceptButton = this.btnPublicar;
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.CancelButton = this.btnCancelar;
            this.ClientSize = new System.Drawing.Size(1024, 720);
            this.Controls.Add(this.mainPanel);
            this.Controls.Add(this.btnPanel);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedDialog;
            this.MaximizeBox = false;
            this.Name = "NuevaPublicacionForm";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterParent;
            this.Text = "Nueva Publicación";

            this.mainPanel.ResumeLayout(false);
            this.mainPanel.PerformLayout();
            this.btnPanel.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.picPreview)).EndInit();
            this.ResumeLayout(false);
        }

        #endregion

        private System.Windows.Forms.Panel mainPanel;
        private System.Windows.Forms.Label lblTitle;
        private System.Windows.Forms.Label lblDescripcion;
        private System.Windows.Forms.TextBox txtDescripcion;
        private System.Windows.Forms.Label lblImagen;
        private System.Windows.Forms.Button btnSeleccionarImagen;
        private System.Windows.Forms.Label lblImagenSeleccionada;
        private System.Windows.Forms.PictureBox picPreview;
        private System.Windows.Forms.Panel btnPanel;
        private System.Windows.Forms.Button btnCancelar;
        private System.Windows.Forms.Button btnPublicar;
    }
}
