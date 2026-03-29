using System;
using System.Collections.Generic;
using System.Drawing;
using System.IO;
using System.Windows.Forms;

namespace Klyer
{
    /// <summary>
    /// Formulario para crear una nueva publicación con descripción e imagen opcional.
    /// Todos los controles están definidos en el Designer.
    /// </summary>
    public partial class NuevaPublicacionForm : Form
    {
        private ApiHelper _apiHelper;
        private UserSession _userSession;
        private string _imagenBase64;

        public NuevaPublicacionForm()
        {
            InitializeComponent();
            _apiHelper = new ApiHelper();
            _userSession = UserSession.Instance;
        }

        /// <summary>
        /// Publica la publicación enviándola a la API.
        /// </summary>
        private async void btnPublicar_Click(object sender, EventArgs e)
        {
            if (string.IsNullOrWhiteSpace(txtDescripcion.Text))
            {
                MessageBox.Show("Por favor ingrese una descripción.", "Aviso", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }

            btnPublicar.Enabled = false;
            btnPublicar.Text = "Publicando...";

            try
            {
                var datos = new Dictionary<string, object>
                {
                    ["id_usuario"] = _userSession.UserId,
                    ["fecha_publicacion"] = DateTime.Now.ToString("yyyy-MM-dd"),
                    ["descripcion"] = txtDescripcion.Text.Trim(),
                    ["imagen"] = _imagenBase64 ?? "",
                    ["likes"] = 0,
                    ["comentarios"] = 0
                };

                bool success = await _apiHelper.PostAsync("/publicaciones/insertar", datos);
                if (success)
                {
                    MessageBox.Show("Publicación creada exitosamente.", "Éxito", MessageBoxButtons.OK, MessageBoxIcon.Information);
                    this.DialogResult = DialogResult.OK;
                    this.Close();
                }
                else
                {
                    MessageBox.Show("Error al crear la publicación.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error: " + ex.Message, "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
            finally
            {
                btnPublicar.Enabled = true;
                btnPublicar.Text = "Publicar";
            }
        }

        /// <summary>
        /// Abre un diálogo para seleccionar una imagen, la convierte a base64 y muestra la vista previa.
        /// </summary>
        private void btnSeleccionarImagen_Click(object sender, EventArgs e)
        {
            using (var dialog = new OpenFileDialog())
            {
                dialog.Title = "Seleccionar imagen";
                dialog.Filter = "Imágenes|*.jpg;*.jpeg;*.png;*.gif;*.bmp";

                if (dialog.ShowDialog() == DialogResult.OK)
                {
                    try
                    {
                        byte[] bytes = File.ReadAllBytes(dialog.FileName);
                        _imagenBase64 = Convert.ToBase64String(bytes);

                        lblImagenSeleccionada.Text = Path.GetFileName(dialog.FileName);
                        lblImagenSeleccionada.ForeColor = Color.Green;

                        // Mostrar vista previa
                        using (var ms = new MemoryStream(bytes))
                        {
                            picPreview.Image = Image.FromStream(ms);
                        }
                        picPreview.Visible = true;
                    }
                    catch (Exception ex)
                    {
                        MessageBox.Show("Error al cargar la imagen: " + ex.Message, "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                    }
                }
            }
        }

        private void btnPublicar_Enter(object sender, EventArgs e)
        {
            btnPublicar.BackColor = Color.FromArgb(2, 132, 199);
        }

        private void btnPublicar_Leave(object sender, EventArgs e)
        {
            btnPublicar.BackColor = Color.FromArgb(14, 165, 233);
        }
    }
}
