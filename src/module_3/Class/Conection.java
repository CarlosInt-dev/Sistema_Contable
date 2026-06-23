package module_3.Class;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conection {
    //Aquí iran los metodos de conexion a la base
    private static final String URL = "jdbc:mariadb://localhost:3306/sistema_contable";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "";
    
    private static Connection conexion = null;

    public static Connection conect() {
        try {
            if (conexion == null || conexion.isClosed()) {
                // Cargar el driver de MariaDB o MySQL
                Class.forName("org.mariadb.jdbc.Driver");
                
                // Crear la conexión
                conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
                //JOptionPane.showMessageDialog(null,"? Conexión establecida con éxito.");
            }
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null,"? Error: No se encontró el driver de MariaDB.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"? Error al conectar a la base de datos: " + e.getMessage());
        }
        return conexion;
    }

    //Método para cerrar la conexión
    public static void disconect() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                JOptionPane.showMessageDialog(null,"? Conexión cerrada correctamente.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"? Error al cerrar la conexión: " + e.getMessage());
        }
    }
    
}

