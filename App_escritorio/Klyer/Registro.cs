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
    public partial class Registro : Form
    {
        private ApiHelper _apiHelper;

        public Registro()
        {
            InitializeComponent();
            _apiHelper = new ApiHelper();
        }

        private async void btnRegistro_Click(object sender, EventArgs e)
        {
            string nombre = tbNom.Text.Trim();
            string nickname = tbNick.Text.Trim();
            string email = tbCorreo.Text.Trim();
            string password = tbPass.Text.Trim();
            string apellidos = tbApellidos.Text.Trim();

            if (string.IsNullOrEmpty(nombre) || string.IsNullOrEmpty(nickname) ||
                string.IsNullOrEmpty(email) || string.IsNullOrEmpty(password))
            {
                MessageBox.Show("Por favor complete todos los campos", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }

            try
            {
                var usuarioDto = new UsuarioDto
                {
                    nombre = nombre,
                    apellidos = apellidos,
                    nickname = nickname,
                    email = email,
                    password = password,
                    foto_perfil = new byte[0] // Foto vacía por defecto
                };

                bool success = await _apiHelper.PostAsync("/usuarios/insertar", usuarioDto);
                if (success)
                {
                    MessageBox.Show("Registro exitoso. Ahora puedes iniciar sesión.", "Éxito", MessageBoxButtons.OK, MessageBoxIcon.Information);
                    
                    // Limpiar formulario
                    tbNom.Clear();
                    tbNick.Clear();
                    tbCorreo.Clear();
                    tbPass.Clear();
                    tbApellidos.Clear();
                }
                else
                {
                    MessageBox.Show("Error al registrar. El usuario o email ya existe.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error de conexión: " + ex.Message, "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void lblInicia_Click(object sender, EventArgs e)
        {
            // Volver a la pantalla de inicio de sesión
            Inicio_de_sesión loginForm = new Inicio_de_sesión();
            loginForm.Show();
            this.Hide();
        }
    }
}