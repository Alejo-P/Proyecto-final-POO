import java.sql.*;
import java.util.ArrayList;

public class Conexion {
    private Connection conexion;
    private Statement sentencia;
    private ResultSet resultado;
    private String Tipo_Usuario;

    /**
     * COnexion a la base de datos MySQL
     * @param usuario Usuario para establecer la conexion
     * @param contraseña Contraseña de acceso al servidor
     * @param url URL de la conexion al servidor*/
    public Conexion(String usuario, String contraseña, String url) {
        try {
            this.conexion = DriverManager.getConnection(url, usuario, contraseña);
            this.sentencia = this.conexion.createStatement();
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
            this.resultado = this.sentencia.executeQuery(consulta);
            return this.resultado;
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
            return this.sentencia.executeUpdate(consulta);
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
            this.resultado = this.sentencia.executeQuery("SELECT * FROM Usuarios");
            while (this.resultado.next()) {
                String nombre = this.resultado.getString("Usuario");
                String contrasenia = this.resultado.getString("Contrasena");
                String TipoUsuario = this.resultado.getString("Tipo usuario");
                if (nombre.equals(user) && contrasenia.equals(password) && TipoUsuario.equals(tipoUsuario)) {
                    this.Tipo_Usuario = tipoUsuario;
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
        return this.Tipo_Usuario;
    }

    /**
     * Insercion explicita de datos en la base de datos
     * @param consulta Consulta SQL para la base de datos
     * @param Elementos Elementos a insertar en la base de datos
     * @return 1 si la consulta se ejecutó correctamente, -1 si se viola la restricción de integridad, 0 si no se ejecutó correctamente
     * */
    public int InsercionExplicita(String consulta, ArrayList<Object> Elementos) {
        try {
            PreparedStatement ps = this.conexion.prepareStatement(consulta);
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
    public ArrayList<Object> getProductos(){
        this.resultado = Consulta("SELECT nombre_pieza FROM Repuestos");
        ArrayList<Object> productos = new ArrayList<>();
        try {
            while (this.resultado.next()){
                productos.add(this.resultado.getString("nombre_pieza"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }

    public ArrayList<Object> getCajeros(){
        this.resultado = Consulta("SELECT Usuario FROM Usuarios WHERE `Tipo usuario` = 'Cajero'");
        ArrayList<Object> cajeros = new ArrayList<>();
        try {
            while (this.resultado.next()){
                cajeros.add(this.resultado.getString("Usuario"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cajeros;
    }
}