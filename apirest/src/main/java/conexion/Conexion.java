package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    // 🔹 Cambia estos datos según tu BD
    private static final String URL = "jdbc:mysql:sql.freedb.tech:3306/freedb_klyer";
    private static final String USER = "freedb_pablillo2k";
    private static final String PASSWORD = "bUf*2m9N!w2mmEU";

    private Connection conexion;

    public Conexion() {
        try {
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos");
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos");
            e.printStackTrace();
        }
    }

    public Connection getConexion() {
        return conexion;
    }

    public void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("Conexión cerrada");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
