using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Klyer
{
    public class UserSession
    {
        private static UserSession _instance;
        private static readonly object _lock = new object();

        public int UserId { get; set; }
        public string Nombre { get; set; }
        public string Apellidos { get; set; }
        public string Nickname { get; set; }
        public string Email { get; set; }
        public byte[] FotoPerfil { get; set; }

        private UserSession() { }

        public static UserSession Instance
        {
            get
            {
                if (_instance == null)
                {
                    lock (_lock)
                    {
                        if (_instance == null)
                        {
                            _instance = new UserSession();
                        }
                    }
                }
                return _instance;
            }
        }

        public void Clear()
        {
            UserId = 0;
            Nombre = null;
            Apellidos = null;
            Nickname = null;
            Email = null;
            FotoPerfil = null;
        }

        public bool IsLoggedIn => UserId > 0;
    }
}