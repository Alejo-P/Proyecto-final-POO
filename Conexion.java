import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class Conexion {
    private Connection conexion;
    private Statement sentencia;
    private ResultSet resultado;
    private String Tipo_Usuario;
    public Conexion(String usuario, String contraseña, String url) {
        try {
            conexion = DriverManager.getConnection(url, usuario, contraseña);
            sentencia = conexion.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ResultSet Consulta(String consulta) {
        try {
            resultado = sentencia.executeQuery(consulta);
            return resultado;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public boolean getCredenciales(String user, String password, String tipoUsuario) {
        boolean acceso = false;
        try {
            resultado = sentencia.executeQuery("SELECT * FROM Usuarios");
            while (resultado.next()) {
                String nombre = resultado.getString("Usuario");
                String contrasenia = resultado.getString("Contrasena");
                String TipoUsuario = resultado.getString("Tipo usuario");
                if (nombre.equals(user) && contrasenia.equals(password) && TipoUsuario.equals(tipoUsuario)) {
                    Tipo_Usuario = tipoUsuario;
                    acceso = true;
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return acceso;
    }
    public String getTipoUsuario() {
        return Tipo_Usuario;
    }
}