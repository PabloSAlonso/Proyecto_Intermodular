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
    public partial class Inicio_de_sesión : Form
    {
        private ApiHelper _apiHelper;
        private UserSession _userSession;

        public Inicio_de_sesión()
        {
            InitializeComponent();
            _apiHelper = new ApiHelper();
            _userSession = UserSession.Instance;
        }

        private void lblRegistro_Click(object sender, EventArgs e)
        {
            // Abrir formulario de registro
            Registro registroForm = new Registro();
            registroForm.ShowDialog();
        }

        private void colorLabelRegistro(object sender, EventArgs e)
        {
            var azul = Color.FromArgb(14, 165, 233);
            if (((Label)sender).ForeColor == azul)
            {
                ((Label)sender).ForeColor = Color.FromArgb(2, 132, 199);
            }
            else
            {
                ((Label)sender).ForeColor = azul;
            }
        }

        private void colorBotonInicio(object sender, EventArgs e) 
        {
            var azul = Color.FromArgb(14, 165, 233);
            if (((Button)sender).BackColor == azul)
            {
                ((Button)sender).BackColor = Color.FromArgb(2, 132, 199);
            }
            else
            {
                ((Button)sender).BackColor = azul;
            }
        }

        private async void btnInicio_Click(object sender, EventArgs e)
        {
            string usuarioOEmail = textBoxUsuario.Text.Trim();
            string password = textBoxPassword.Text.Trim();

            if (string.IsNullOrEmpty(usuarioOEmail) || string.IsNullOrEmpty(password))
            {
                lblNotificarCorreo.Text = "Por favor complete todos los campos";
                lblNotificarCorreo.ForeColor = Color.Red;
                return;
            }

            try
            {
                // Intentar login con la API - primero como nickname, luego como email si falla
                var usuario = await _apiHelper.GetAsync<UsuarioDto>($"/usuarios/login/{usuarioOEmail}/{password}");
                
                if (usuario != null && usuario.id > 0)
                {
                    // Guardar sesión
                    _userSession.UserId = usuario.id;
                    _userSession.Nombre = usuario.nombre;
                    _userSession.Apellidos = usuario.apellidos;
                    _userSession.Nickname = usuario.nickname;
                    _userSession.Email = usuario.email;
                    _userSession.FotoPerfil = usuario.foto_perfil;

                    // Abrir aplicación principal
                    MainForm mainForm = new MainForm();
                    mainForm.Show();
                    this.Hide();
                }
                else
                {
                    lblNotificarCorreo.Text = "Usuario o contraseña incorrectos";
                    lblNotificarCorreo.ForeColor = Color.Red;
                }
            }
            catch (Exception ex)
            {
                lblNotificarCorreo.Text = "Error de conexión: " + ex.Message;
                lblNotificarCorreo.ForeColor = Color.Red;
            }
        }
    }
}