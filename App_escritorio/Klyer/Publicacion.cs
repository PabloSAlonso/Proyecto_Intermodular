using System;

namespace Klyer
{
    public class Publicacion
    {
        public int IdPublicacion { get; set; }
        public int IdUsuario { get; set; }
        public DateTime? FechaPublicacion { get; set; }
        public byte[] Imagen { get; set; }
        public string Descripcion { get; set; }
        public int Likes { get; set; }
        public int Comentarios { get; set; }
        
        // User info for display
        public string NicknameUsuario { get; set; }
        public string NombreUsuario { get; set; }
        public byte[] FotoPerfilUsuario { get; set; }
    }
}