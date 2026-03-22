using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Klyer
{
    public partial class NuevaPublicacionForm : Form
    {
        private ApiHelper _apiHelper;
        private UserSession _userSession;

        public NuevaPublicacionForm()
        {
            InitializeComponent();
            _apiHelper = new ApiHelper();
            _userSession = UserSession.Instance;
        }

        private async void btnPublicar_Click(object sender, EventArgs e)
        {
            if (string.IsNullOrWhiteSpace(txtDescripcion.Text))
            {
                MessageBox.Show("Por favor ingrese una descripción", "Error", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }

            try
            {
                var publicacion = new Publicacion
                {
                    IdUsuario = _userSession.UserId,
                    FechaPublicacion = DateTime.Now,
                    Descripcion = txtDescripcion.Text.Trim(),
                    Likes = 0,
                    Comentarios = 0
                };

                var success = await _apiHelper.PostAsync("/publicaciones/insertar", publicacion);
                if (success)
                {
                    MessageBox.Show("Publicación creada exitosamente", "Éxito", MessageBoxButtons.OK, MessageBoxIcon.Information);
                    this.DialogResult = DialogResult.OK;
                    this.Close();
                }
                else
                {
                    MessageBox.Show("Error al crear la publicación", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show($"Error: {ex.Message}", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void btnSeleccionarImagen_Click(object sender, EventArgs e)
        {
            // TODO: Implementar selección de imagen
            // Por ahora dejamos vacío ya que el manejo de imágenes requiere más trabajo
            MessageBox.Show("Funcionalidad de selección de imagen no implementada aún", "Información", MessageBoxButtons.OK, MessageBoxIcon.Information);
        }
    }
}