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
    public partial class MainForm : Form
    {
        private ApiHelper _apiHelper;
        private UserSession _userSession;
        private List<Publicacion> _publicaciones;

        public MainForm()
        {
            InitializeComponent();
            _apiHelper = new ApiHelper();
            _userSession = UserSession.Instance;
            
            // Set the user name label
            if (_userSession.IsLoggedIn)
            {
                lblUser.Text = $"Hola, {_userSession.Nombre}";
            }
            else
            {
                lblUser.Text = "Hola, Invitado";
            }
            
            LoadPosts();
        }

        private async void LoadPosts()
        {
            try
            {
                var publicaciones = await _apiHelper.GetAsync<List<Publicacion>>("/publicaciones/todas");
                if (publicaciones != null)
                {
                    _publicaciones = publicaciones;
                    DisplayPosts();
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show($"Error al cargar publicaciones: {ex.Message}", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void DisplayPosts()
        {
            postsPanel.Controls.Clear();

            foreach (var pub in _publicaciones)
            {
                Panel postPanel = CreatePostPanel(pub);
                postsPanel.Controls.Add(postPanel);
            }
        }

        private Panel CreatePostPanel(Publicacion pub)
        {
            Panel postPanel = new Panel();
            postPanel.BorderStyle = BorderStyle.FixedSingle;
            postPanel.Margin = new Padding(5);
            postPanel.Padding = new Padding(10);
            postPanel.Width = postsPanel.Width - 40;

            // Header
            Panel headerPanel = new Panel();
            headerPanel.Height = 40;
            headerPanel.Dock = DockStyle.Top;

            // User info would go here (avatar, name, time)
            Label lblUser = new Label();
            lblUser.Text = pub.NicknameUsuario ?? "Usuario";
            lblUser.AutoSize = true;
            lblUser.Location = new Point(50, 10);
            headerPanel.Controls.Add(lblUser);

            Label lblTime = new Label();
            lblTime.Text = pub.FechaPublicacion?.ToString() ?? "";
            lblTime.AutoSize = true;
            lblTime.ForeColor = Color.Gray;
            lblTime.Location = new Point(headerPanel.Width - 100, 10);
            headerPanel.Controls.Add(lblTime);

            // Image (if exists)
            if (pub.Imagen != null && pub.Imagen.Length > 0)
            {
                PictureBox picImage = new PictureBox();
                picImage.Image = ByteArrayToImage(pub.Imagen);
                picImage.SizeMode = PictureBoxSizeMode.Zoom;
                picImage.Height = 200;
                picImage.Dock = DockStyle.Top;
                picImage.Margin = new Padding(0, 10, 0, 10);
                postPanel.Controls.Add(picImage);
            }

            // Description
            Label lblDescription = new Label();
            lblDescription.Text = pub.Descripcion ?? "";
            lblDescription.AutoSize = false;
            lblDescription.MaximumSize = new Size(postPanel.Width - 20, 0);
            lblDescription.AutoSize = true;
            lblDescription.Dock = DockStyle.Top;
            postPanel.Controls.Add(lblDescription);

            // Actions Panel
            Panel actionsPanel = new Panel();
            actionsPanel.Height = 30;
            actionsPanel.Dock = DockStyle.Bottom;

            Button btnLike = new Button();
            btnLike.Text = $"❤️ {pub.Likes}";
            btnLike.AutoSize = true;
            btnLike.Location = new Point(10, 5);
            btnLike.FlatStyle = FlatStyle.Flat;
            btnLike.FlatAppearance.BorderSize = 0;
            actionsPanel.Controls.Add(btnLike);

            Button btnComment = new Button();
            btnComment.Text = $"💬 {pub.Comentarios}";
            btnComment.AutoSize = true;
            btnComment.Location = new Point(100, 5);
            btnComment.FlatStyle = FlatStyle.Flat;
            btnComment.FlatAppearance.BorderSize = 0;
            actionsPanel.Controls.Add(btnComment);

            postPanel.Controls.Add(actionsPanel);
            postPanel.Controls.Add(headerPanel);

            return postPanel;
        }

        private System.Drawing.Image ByteArrayToImage(byte[] byteArrayIn)
        {
            using (System.IO.MemoryStream ms = new System.IO.MemoryStream(byteArrayIn))
            {
                return System.Drawing.Image.FromStream(ms);
            }
        }

        private void btnNewPost_Click(object sender, EventArgs e)
        {
            // Switch to the upload tab when clicking "Nueva Publicación"
            tabControl1.SelectedTab = tabSubir;
        }

        private void tabControl1_SelectedIndexChanged(object sender, EventArgs e)
        {
            // Handle tab changes if needed
            if (tabControl1.SelectedTab == tabFeed && _publicaciones == null)
            {
                LoadPosts();
            }
            else if (tabControl1.SelectedTab == tabPerfil)
            {
                // Load profile data when switching to profile tab
                LoadProfileData();
            }
        }

        private async void LoadProfileData()
        {
            if (_userSession.IsLoggedIn)
            {
                try
                {
                    var usuario = await _apiHelper.GetAsync<UsuarioDto>($"/usuarios/obtener/{_userSession.UserId}");
                    if (usuario != null)
                    {
                        // Update profile tab with user data
                        // This would typically update labels/pictures in the perfil tab
                        // For now, we'll just verify we got the data
                    }
                }
                catch (Exception ex)
                {
                    // Handle error silently for now
                }
            }
        }
    }
}