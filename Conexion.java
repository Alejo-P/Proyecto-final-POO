import java.sql.*;

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
    public int Insertar(String consulta) {
        try {
            return sentencia.executeUpdate(consulta);
        } catch (SQLIntegrityConstraintViolationException e) {
            return -1;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
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