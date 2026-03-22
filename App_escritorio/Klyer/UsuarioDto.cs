using System;

namespace Klyer
{
    public class UsuarioDto
    {
        public int id { get; set; }
        public string nombre { get; set; }
        public string apellidos { get; set; }
        public string nickname { get; set; }
        public string email { get; set; }
        public string password { get; set; }
        public byte[] foto_perfil { get; set; }
    }
}