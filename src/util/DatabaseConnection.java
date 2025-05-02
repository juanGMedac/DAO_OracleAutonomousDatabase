package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Gestiona la conexión con la base de datos Oracle Autonomous Database.
 * ASUME que los JARs necesarios (ojdbc, oraclepki, osdt_cert, osdt_core)
 * han sido añadidos manualmente al classpath del proyecto en IntelliJ.
 */
public class DatabaseConnection {

    // --- !!! IMPORTANTE: MODIFICA ESTOS VALORES !!! ---
    private static final String DB_USER = "ADMIN"; // O tu usuario de BD
    private static final String DB_PASSWORD = "PASS"; // Reemplaza con tu contraseña
    // Ruta absoluta a la carpeta del Wallet descomprimido
    // Ejemplo Windows: "C:/path/to/your/wallet_folder"
    // Ejemplo Linux/Mac: "/path/to/your/wallet_folder"
    private static final String WALLET_PATH = "lib/Wallet_EjConexion"; // Reemplaza con tu ruta
    // Nombre del servicio TNS del archivo tnsnames.ora (ej: yourdbname_high)
    private static final String TNS_NAME = "ejconexion_medium"; // Reemplaza con tu TNS Name
    // --- Fin de la sección de modificación ---

    private static final String DB_URL = "jdbc:oracle:thin:@" + TNS_NAME + "?TNS_ADMIN=" + WALLET_PATH;

    private static Connection connection = null;

    /**
     * Obtiene una conexión a la base de datos.
     * @return Objeto Connection.
     * @throws SQLException Si ocurre un error al conectar.
     */
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                // No es necesario Class.forName con JDBC 4.0+ si el JAR está en el classpath
                // Class.forName("oracle.jdbc.driver.OracleDriver");

                Properties props = new Properties();
                props.setProperty("user", DB_USER);
                props.setProperty("password", DB_PASSWORD);
                // La propiedad TNS_ADMIN en la URL se encarga de localizar el Wallet

                System.out.println("Intentando conectar a: " + DB_URL);
                System.out.println("Usando Wallet en (configurado vía TNS_ADMIN): " + WALLET_PATH);

                // Registrar el driver explícitamente (puede ayudar en algunos entornos)
                DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

                connection = DriverManager.getConnection(DB_URL, props);
                System.out.println("¡Conexión establecida exitosamente!");

            } catch (SQLException e) {
                System.err.println("Error al conectar a la base de datos: " + e.getMessage());
                System.err.println("Verifica la URL, usuario, contraseña, ruta del Wallet y que los JARs estén en el classpath.");
                e.printStackTrace();
                throw e;
            }
        }
        return connection;
    }

    /**
     * Cierra la conexión a la base de datos si está abierta.
     */
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println("Conexión cerrada.");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}