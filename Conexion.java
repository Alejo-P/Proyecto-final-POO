import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class Conexion {
    private Connection conexion;
    private Statement sentencia;
    private ResultSet resultado;
    public Conexion(String usuario, String contraseña, String url) {
        try {
            conexion = DriverManager.getConnection(url, usuario, contraseña);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean getCredenciales(String user, String password, String tipoUsuario) {
        boolean acceso = false;
        try {
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery("SELECT * FROM Usuarios");
            while (resultado.next()) {
                String nombre = resultado.getString("Usuario");
                String contraseña = resultado.getString("Contrasena");
                String TipoUsuario = resultado.getString("Tipo usuario");
                if (nombre.equals(user) && contraseña.equals(contraseña) && TipoUsuario.equals(tipoUsuario)) {
                    acceso = true;
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return acceso;
    }
}