package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private Connection conexion;

    private static String readEnv(String key) {
        String value = System.getenv(key);
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalStateException("Missing environment variable: " + key);
        }
        return value;
    }

    public Conexion() {
        try {
            String url = readEnv("DB_URL");
            String user = readEnv("DB_USER");
            String password = readEnv("DB_PASSWORD");
            conexion = DriverManager.getConnection(url, user, password);
            System.out.println("Conexion exitosa a la base de datos");
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
                System.out.println("Conexion cerrada");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
