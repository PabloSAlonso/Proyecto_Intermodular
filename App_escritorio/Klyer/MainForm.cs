using System;
using System.Collections.Generic;
using System.Drawing;
using System.IO;
using System.Windows.Forms;

namespace Klyer
{
    /// <summary>
    /// Formulario principal de la aplicación Klyer.
    /// Contiene tres pestañas: Feed, Mi Perfil y Subir Publicación.
    /// Todos los controles están definidos en el Designer.
    /// </summary>
    public partial class MainForm : Form
    {
        private ApiHelper _apiHelper;
        private UserSession _userSession;

        // ──────────────────────────────────────────────
        // CONSTRUCTOR
        // ──────────────────────────────────────────────

        public MainForm()
        {
            InitializeComponent();
            _apiHelper = new ApiHelper();
            _userSession = UserSession.Instance;

            if (_userSession.IsLoggedIn)
            {
                lblUser.Text = "Hola, " + _userSession.Nombre;
            }
        }

        protected override void OnShown(EventArgs e)
        {
            base.OnShown(e);
            CargarFeed();
        }

        // ──────────────────────────────────────────────
        // FEED
        // ──────────────────────────────────────────────

        /// <summary>
        /// Carga todas las publicaciones desde la API y las renderiza en el feed.
        /// </summary>
        private async void CargarFeed()
        {
            postsPanel.Controls.Clear();

            try
            {
                var publicaciones = await _apiHelper.GetAsync<List<Publicacion>>("/publicaciones/todas");
                if (publicaciones != null)
                {
                    foreach (var pub in publicaciones)
                    {
                        postsPanel.Controls.Add(CrearTarjetaPublicacion(pub, false));
                    }

                    if (publicaciones.Count == 0)
                    {
                        postsPanel.Controls.Add(CrearLabelVacio("No hay publicaciones todavía."));
                    }
                }
            }
            catch (Exception ex)
            {
                postsPanel.Controls.Add(CrearLabelVacio("Error al cargar publicaciones: " + ex.Message));
            }
        }

        // ──────────────────────────────────────────────
        // PERFIL
        // ──────────────────────────────────────────────

        /// <summary>
        /// Carga los datos del perfil y las publicaciones del usuario actual.
        /// </summary>
        private async void CargarPerfil()
        {
            if (!_userSession.IsLoggedIn) return;

            // Cargar datos del usuario
            try
            {
                var usuario = await _apiHelper.GetAsync<UsuarioDto>($"/usuarios/obtener/{_userSession.UserId}");
                if (usuario != null)
                {
                    _userSession.Nombre = usuario.nombre;
                    _userSession.Apellidos = usuario.apellidos;
                    _userSession.Nickname = usuario.nickname;
                    _userSession.Email = usuario.email;
                    _userSession.FotoPerfil = usuario.foto_perfil;

                    lblUser.Text = "Hola, " + usuario.nombre;
                    lblPerfilNombre.Text = (usuario.nombre ?? "") + " " + (usuario.apellidos ?? "");
                    lblPerfilNick.Text = "@" + (usuario.nickname ?? "");
                    lblPerfilEmail.Text = usuario.email ?? "";
                    picPerfilAvatar.Image = ObtenerAvatar(usuario.foto_perfil != null ? Convert.ToBase64String(usuario.foto_perfil) : null);
                }
            }
            catch (Exception ex)
            {
                lblPerfilNombre.Text = "Error al cargar perfil";
                lblPerfilNick.Text = ex.Message;
            }

            // Cargar publicaciones del usuario
            await CargarPublicacionesUsuario();
        }

        /// <summary>
        /// Carga y muestra las publicaciones del usuario en la pestaña de perfil.
        /// </summary>
        private async System.Threading.Tasks.Task CargarPublicacionesUsuario()
        {
            flpMisPublicaciones.Controls.Clear();

            try
            {
                var publicaciones = await _apiHelper.GetAsync<List<Publicacion>>($"/publicaciones/usuario/{_userSession.UserId}");
                if (publicaciones != null && publicaciones.Count > 0)
                {
                    int ancho = flpMisPublicaciones.ClientSize.Width - 60;
                    if (ancho < 400) ancho = 600;
                    foreach (var pub in publicaciones)
                    {
                        flpMisPublicaciones.Controls.Add(CrearTarjetaPublicacionInterna(pub, true, ancho));
                    }
                }
                else
                {
                    flpMisPublicaciones.Controls.Add(CrearLabelVacio("No tienes publicaciones todavía."));
                }
            }
            catch (Exception ex)
            {
                flpMisPublicaciones.Controls.Add(CrearLabelVacio("Error: " + ex.Message));
            }
        }

        // ──────────────────────────────────────────────
        // TARJETA DE PUBLICACIÓN
        // ──────────────────────────────────────────────

        /// <summary>
        /// Crea un panel visual para una publicación individual.
        /// </summary>
        /// <param name="pub">Publicación a mostrar.</param>
        /// <param name="conEliminar">Si es true, muestra el botón de eliminar.</param>
        private Panel CrearTarjetaPublicacion(Publicacion pub, bool conEliminar)
        {
            int anchoContenedor = postsPanel.ClientSize.Width - 60;
            if (anchoContenedor < 400) anchoContenedor = 600;

            return CrearTarjetaPublicacionInterna(pub, conEliminar, anchoContenedor);
        }

        /// <summary>
        /// Crea un panel visual para una publicación con un ancho específico.
        /// </summary>
        private Panel CrearTarjetaPublicacionInterna(Publicacion pub, bool conEliminar, int anchoContenedor)
        {  
            Panel tarjeta = new Panel();
            tarjeta.BorderStyle = BorderStyle.FixedSingle;
            tarjeta.Margin = new Padding(0, 0, 0, 15);
            tarjeta.Padding = new Padding(15);
            tarjeta.Width = anchoContenedor;
            tarjeta.AutoSize = false;

            int contenidoY = 10;

            // ── Cabecera: avatar + nombre + fecha ──
            PictureBox picAvatar = new PictureBox();
            picAvatar.Size = new Size(40, 40);
            picAvatar.Location = new Point(15, contenidoY);
            picAvatar.SizeMode = PictureBoxSizeMode.Zoom;
            picAvatar.Image = ObtenerAvatar(pub.foto_usuario);
            tarjeta.Controls.Add(picAvatar);

            Label lblNick = new Label();
            lblNick.Text = "@" + (pub.nickname_usuario ?? "Usuario");
            lblNick.Font = new Font("Segoe UI", 11, FontStyle.Bold);
            lblNick.AutoSize = true;
            lblNick.Location = new Point(65, contenidoY + 2);
            tarjeta.Controls.Add(lblNick);

            Label lblFecha = new Label();
            lblFecha.Text = pub.fecha_publicacion ?? "";
            lblFecha.Font = new Font("Segoe UI", 8.5f);
            lblFecha.ForeColor = Color.Gray;
            lblFecha.AutoSize = true;
            lblFecha.Location = new Point(65, contenidoY + 22);
            tarjeta.Controls.Add(lblFecha);

            // Botón eliminar
            if (conEliminar)
            {
                Button btnDel = new Button();
                btnDel.Text = "✕ Eliminar";
                btnDel.Size = new Size(85, 28);
                btnDel.Anchor = AnchorStyles.Top | AnchorStyles.Right;
                btnDel.Location = new Point(anchoContenedor - 110, contenidoY + 5);
                btnDel.FlatStyle = FlatStyle.Flat;
                btnDel.Font = new Font("Segoe UI", 8);
                btnDel.ForeColor = Color.Red;
                btnDel.Cursor = Cursors.Hand;
                int idPub = pub.id_publicacion;
                btnDel.Click += (s, e) => EliminarPublicacion(idPub);
                tarjeta.Controls.Add(btnDel);
            }

            contenidoY += 55;

            // ── Imagen ──
            if (!string.IsNullOrEmpty(pub.imagen))
            {
                try
                {
                    byte[] imgBytes = Convert.FromBase64String(pub.imagen);
                    using (var ms = new MemoryStream(imgBytes))
                    {
                        Image img = Image.FromStream(ms);

                        PictureBox picPost = new PictureBox();
                        picPost.Image = img;
                        picPost.SizeMode = PictureBoxSizeMode.Zoom;
                        picPost.Location = new Point(15, contenidoY);
                        picPost.Width = anchoContenedor - 35;
                        picPost.Height = Math.Min(350, (int)((float)img.Height / img.Width * picPost.Width));
                        tarjeta.Controls.Add(picPost);

                        contenidoY += picPost.Height + 10;
                    }
                }
                catch { }
            }

            // ── Descripción ──
            if (!string.IsNullOrEmpty(pub.descripcion))
            {
                Label lblDesc = new Label();
                lblDesc.Text = pub.descripcion;
                lblDesc.Font = new Font("Segoe UI", 10.5f);
                lblDesc.ForeColor = Color.FromArgb(33, 37, 41);
                lblDesc.Location = new Point(15, contenidoY);
                lblDesc.MaximumSize = new Size(anchoContenedor - 35, 0);
                lblDesc.AutoSize = true;
                tarjeta.Controls.Add(lblDesc);
                tarjeta.PerformLayout();

                contenidoY += lblDesc.Height + 5;
            }

            // ── Likes y comentarios ──
            Label lblStats = new Label();
            lblStats.Text = string.Format("♥ {0}    💬 {1}", pub.likes, pub.comentarios);
            lblStats.Font = new Font("Segoe UI", 9.5f);
            lblStats.ForeColor = Color.Gray;
            lblStats.Location = new Point(15, contenidoY + 8);
            lblStats.AutoSize = true;
            tarjeta.Controls.Add(lblStats);

            // Ajustar altura total
            tarjeta.Height = contenidoY + 45;

            return tarjeta;
        }

        // ──────────────────────────────────────────────
        // UTILIDADES
        // ──────────────────────────────────────────────

        /// <summary>
        /// Crea un label centrado para estados vacíos.
        /// </summary>
        private Label CrearLabelVacio(string texto)
        {
            Label lbl = new Label();
            lbl.Text = texto;
            lbl.Font = new Font("Segoe UI", 12);
            lbl.ForeColor = Color.Gray;
            lbl.AutoSize = true;
            lbl.Padding = new Padding(30);
            return lbl;
        }

        /// <summary>
        /// Obtiene un avatar a partir de base64, o genera uno por defecto.
        /// </summary>
        private Image ObtenerAvatar(string fotoBase64)
        {
            if (!string.IsNullOrEmpty(fotoBase64))
            {
                try
                {
                    byte[] bytes = Convert.FromBase64String(fotoBase64);
                    using (var ms = new MemoryStream(bytes))
                    {
                        return Image.FromStream(ms);
                    }
                }
                catch { }
            }

            Bitmap bmp = new Bitmap(40, 40);
            using (Graphics g = Graphics.FromImage(bmp))
            {
                g.Clear(Color.FromArgb(15, 118, 110));
                g.DrawString("U", new Font("Segoe UI", 15, FontStyle.Bold), Brushes.White, 6, 3);
            }
            return bmp;
        }

        // ──────────────────────────────────────────────
        // ELIMINAR PUBLICACIÓN
        // ──────────────────────────────────────────────

        private async void EliminarPublicacion(int idPublicacion)
        {
            if (MessageBox.Show("¿Eliminar esta publicación?", "Confirmar", MessageBoxButtons.YesNo, MessageBoxIcon.Question) != DialogResult.Yes)
                return;

            try
            {
                bool ok = await _apiHelper.DeleteAsync($"/publicaciones/eliminar/{idPublicacion}");
                if (ok)
                {
                    CargarFeed();
                    await CargarPublicacionesUsuario();
                }
                else
                {
                    MessageBox.Show("Error al eliminar la publicación.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error: " + ex.Message, "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        // ──────────────────────────────────────────────
        // EDITAR PERFIL
        // ──────────────────────────────────────────────

        private async void btnEditarPerfil_Click(object sender, EventArgs e)
        {
            Form dlg = new Form();
            dlg.Text = "Editar Perfil";
            dlg.Size = new Size(480, 420);
            dlg.StartPosition = FormStartPosition.CenterParent;
            dlg.FormBorderStyle = FormBorderStyle.FixedDialog;
            dlg.MaximizeBox = false;
            dlg.MinimizeBox = false;

            int y = 20;

            Label l1 = new Label() { Text = "Nombre:", Location = new Point(20, y + 3), AutoSize = true, Font = new Font("Segoe UI", 10) };
            TextBox t1 = new TextBox() { Text = _userSession.Nombre, Location = new Point(140, y), Width = 270, Font = new Font("Segoe UI", 10) };
            y += 35;
            Label l2 = new Label() { Text = "Apellidos:", Location = new Point(20, y + 3), AutoSize = true, Font = new Font("Segoe UI", 10) };
            TextBox t2 = new TextBox() { Text = _userSession.Apellidos, Location = new Point(140, y), Width = 270, Font = new Font("Segoe UI", 10) };
            y += 35;
            Label l3 = new Label() { Text = "Nickname:", Location = new Point(20, y + 3), AutoSize = true, Font = new Font("Segoe UI", 10) };
            TextBox t3 = new TextBox() { Text = _userSession.Nickname, Location = new Point(140, y), Width = 270, Font = new Font("Segoe UI", 10) };
            y += 35;
            Label l4 = new Label() { Text = "Email:", Location = new Point(20, y + 3), AutoSize = true, Font = new Font("Segoe UI", 10) };
            TextBox t4 = new TextBox() { Text = _userSession.Email, Location = new Point(140, y), Width = 270, Font = new Font("Segoe UI", 10) };
            y += 35;
            Label l5 = new Label() { Text = "Contraseña:", Location = new Point(20, y + 3), AutoSize = true, Font = new Font("Segoe UI", 10) };
            TextBox t5 = new TextBox() { Location = new Point(140, y), Width = 270, PasswordChar = '*', Font = new Font("Segoe UI", 10) };
            y += 45;

            Button btnOk = new Button() { Text = "Guardar", Location = new Point(140, y), Width = 120, Height = 35, BackColor = Color.FromArgb(14, 165, 233), ForeColor = Color.White, FlatStyle = FlatStyle.Flat, Font = new Font("Segoe UI", 10) };
            Button btnCancel = new Button() { Text = "Cancelar", Location = new Point(270, y), Width = 120, Height = 35, Font = new Font("Segoe UI", 10) };

            btnCancel.Click += (s, ev) => dlg.Close();
            btnOk.Click += async (s, ev) =>
            {
                var datos = new Dictionary<string, object>
                {
                    ["id"] = _userSession.UserId,
                    ["nombre"] = t1.Text.Trim(),
                    ["apellidos"] = t2.Text.Trim(),
                    ["nickname"] = t3.Text.Trim(),
                    ["email"] = t4.Text.Trim()
                };
                if (!string.IsNullOrWhiteSpace(t5.Text))
                    datos["password"] = t5.Text;

                try
                {
                    bool ok = await _apiHelper.PutAsync($"/usuarios/update/{_userSession.UserId}", datos);
                    if (ok)
                    {
                        _userSession.Nombre = t1.Text.Trim();
                        _userSession.Apellidos = t2.Text.Trim();
                        _userSession.Nickname = t3.Text.Trim();
                        _userSession.Email = t4.Text.Trim();
                        MessageBox.Show("Perfil actualizado correctamente.", "Éxito", MessageBoxButtons.OK, MessageBoxIcon.Information);
                        dlg.Close();
                        CargarPerfil();
                    }
                    else
                    {
                        MessageBox.Show("Error al actualizar el perfil.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                    }
                }
                catch (Exception ex)
                {
                    MessageBox.Show("Error: " + ex.Message, "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
            };

            dlg.Controls.AddRange(new Control[] { l1, t1, l2, t2, l3, t3, l4, t4, l5, btnOk, btnCancel });
            dlg.ShowDialog();
        }

        // ──────────────────────────────────────────────
        // ELIMINAR CUENTA
        // ──────────────────────────────────────────────

        private async void btnEliminarCuenta_Click(object sender, EventArgs e)
        {
            if (MessageBox.Show("¿Eliminar tu cuenta? Esta acción no se puede deshacer.", "Eliminar cuenta", MessageBoxButtons.YesNo, MessageBoxIcon.Warning) != DialogResult.Yes)
                return;

            try
            {
                bool ok = await _apiHelper.DeleteAsync($"/usuarios/eliminar/{_userSession.UserId}");
                if (ok)
                {
                    MessageBox.Show("Cuenta eliminada.", "Éxito", MessageBoxButtons.OK, MessageBoxIcon.Information);
                    CerrarSesion();
                }
                else
                {
                    MessageBox.Show("Error al eliminar la cuenta.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error: " + ex.Message, "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        // ──────────────────────────────────────────────
        // CERRAR SESIÓN
        // ──────────────────────────────────────────────

        private void btnCerrarSesion_Click(object sender, EventArgs e)
        {
            CerrarSesion();
        }

        private void CerrarSesion()
        {
            _userSession.Clear();
            var login = new Inicio_de_sesión();
            login.Show();
            this.Close();
        }

        // ──────────────────────────────────────────────
        // NAVEGACIÓN POR PESTAÑAS
        // ──────────────────────────────────────────────

        private void tabControl1_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (tabControl1.SelectedTab == tabFeed)
            {
                CargarFeed();
            }
            else if (tabControl1.SelectedTab == tabPerfil)
            {
                CargarPerfil();
            }
        }

        // ──────────────────────────────────────────────
        // CREAR PUBLICACIÓN
        // ──────────────────────────────────────────────

        private void CrearPublicacion()
        {
            var formPub = new NuevaPublicacionForm();
            if (formPub.ShowDialog() == DialogResult.OK)
            {
                CargarFeed();
            }
            tabControl1.SelectedTab = tabFeed;
        }

        private void btnNewPost_Click(object sender, EventArgs e)
        {
            CrearPublicacion();
        }
    }
}
