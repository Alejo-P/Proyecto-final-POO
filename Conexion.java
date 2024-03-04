import java.sql.*;
import java.util.ArrayList;

public class Conexion {
    private Connection conexion;
    private Statement sentencia;
    private PreparedStatement superSentencia;
    private ResultSet resultado;
    private String Tipo_Usuario;

    /**
     * COnexion a la base de datos MySQL
     * @param usuario Usuario para establecer la conexion
     * @param contraseña Contraseña de acceso al servidor
     * @param url URL de la conexion al servidor*/
    public Conexion(String usuario, String contraseña, String url) {
        try {
            conexion = DriverManager.getConnection(url, usuario, contraseña);
            sentencia = conexion.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Consulta a la base de datos
     * @param consulta Consulta SQL para la base de datos
     * @return Resultado de la consulta
     * */
    public ResultSet Consulta(String consulta) {
        try {
            return sentencia.executeQuery(consulta);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Insertar datos en la base de datos
     * @param consulta Consulta SQL para la base de datos
     * @return 1 si la consulta se ejecutó correctamente, -1 si se viola la restricción de integridad, 0 si no se ejecutó correctamente
     * */
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

    /**
     * Verificar las credenciales de acceso de un usuario
     * @param user Nombre de usuario
     * @param password Contraseña de usuario
     * @param tipoUsuario Tipo de usuario
     * @return True si las credenciales son correctas, False caso contrario
     * */
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

    /**
     * Obtener el tipo de usuario
     * @return Tipo de usuario
     * */
    public String getTipoUsuario() {
        return Tipo_Usuario;
    }

    /**
     * Insercion explicita de datos en la base de datos
     * @param consulta Consulta SQL para la base de datos
     * @param Elementos Elementos a insertar en la base de datos
     * @return 1 si la consulta se ejecutó correctamente, -1 si se viola la restricción de integridad, 0 si no se ejecutó correctamente
     * */
    public int InsercionExplicita(String consulta, ArrayList<Object> Elementos) {
        try {
            PreparedStatement ps = conexion.prepareStatement(consulta);
            for (int i = 0; i < Elementos.size(); i++) {
                if (Elementos.get(i) instanceof String) {
                    ps.setString(i + 1, (String) Elementos.get(i));
                } else if (Elementos.get(i) instanceof Integer) {
                    ps.setInt(i + 1, (int) Elementos.get(i));
                } else if (Elementos.get(i) instanceof Double) {
                    ps.setDouble(i + 1, (double) Elementos.get(i));
                } else if (Elementos.get(i) instanceof byte[]) {
                    ps.setBytes(i + 1, (byte[]) Elementos.get(i));
                }
            }
            return ps.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException ie){
            return -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}