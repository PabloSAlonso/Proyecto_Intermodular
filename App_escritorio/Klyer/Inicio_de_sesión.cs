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
        public Inicio_de_sesión()
        {
            InitializeComponent();
        }

        private void lblRegistro_Click(object sender, EventArgs e)
        {

        }

        private void colorLabelRegistro(object sender, EventArgs e)
        {
            if (((Label)sender).ForeColor == Color.DodgerBlue)
            {
                ((Label)sender).ForeColor = Color.Blue;
            }
            else
            {
                ((Label)sender).ForeColor = Color.DodgerBlue;
            }
        }

        private void colorBotonInicio(object sender, EventArgs e) 
        {
            if (((Button)sender).BackColor == Color.DodgerBlue)
            {
                ((Button)sender).BackColor = Color.Blue;
            }
            else
            {
                ((Button)sender).BackColor = Color.DodgerBlue;
            }
        }
    }
}
