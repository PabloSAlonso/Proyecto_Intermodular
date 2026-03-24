using System;

namespace Klyer
{
    /// <summary>
    /// Modelo de datos para una publicación del muro.
    /// Las propiedades usan nombres en minúsculas para coincidir con el JSON de la API.
    /// </summary>
    public class Publicacion
    {
        public int id_publicacion { get; set; }
        public int id_usuario { get; set; }
        public string fecha_publicacion { get; set; }
        public string imagen { get; set; }
        public string descripcion { get; set; }
        public int likes { get; set; }
        public int comentarios { get; set; }
        public string nickname_usuario { get; set; }
        public string foto_usuario { get; set; }
    }
}
