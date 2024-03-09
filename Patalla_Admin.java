import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

public class Patalla_Admin {
    private JTabbedPane tabbedPane1;
    private JComboBox <String> comboBox1;
    private JButton comprobarButton;
    private JComboBox <String> comboBox2;
    private JButton accederButton;
    private JPasswordField passwordField1;
    private JTextField usuario;
    private JPasswordField passwordField2;
    private JButton agregarButton;
    JPanel pantalla;
    private JTable table1;
    private JTable table2;
    private JTextField nombre;
    private JTextField cedula;
    private JTextField correo;
    private JTextField ingreso_producto;
    private JTextField ingreso_stock;
    private JTextField ingreso_precio;
    private JButton seleccionarUnaImagenButton;
    private JButton insertarPorductoButton;
    private JButton salirButton1;
    private JButton verProductosButton;
    private Repuesto repuesto;
    private Conexion conexion;
    private ArrayList<Object> productos;

    public Patalla_Admin(Conexion conn) {
        this.conexion = conn;
        this.repuesto = new Repuesto();
        comprobarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String opcionSeleccionada = (String) comboBox1.getSelectedItem();
                ResultSet respuesta = null;
                assert opcionSeleccionada != null;
                if (opcionSeleccionada.equals("Presione para actualizar...")){
                    JOptionPane.showMessageDialog(pantalla, "Por favor, seleccione un producto");
                    return;
                } else if (opcionSeleccionada.equals("Todo")){
                    respuesta = conexion.Consulta("SELECT nombre_pieza, stock FROM Repuestos");
                } else {
                    respuesta = conexion.Consulta("SELECT nombre_pieza, stock FROM Repuestos WHERE nombre_pieza = '" + opcionSeleccionada + "'");
                }
                ResultSetMetaData meta = null;
                try {
                    meta = respuesta.getMetaData();
                    int columnas = meta.getColumnCount();
                    DefaultTableModel modelo = new DefaultTableModel();
                    for (int i = 1; i <= columnas; i++) {
                        modelo.addColumn(meta.getColumnName(i));
                    }
                    while (respuesta.next()) {
                        Object[] fila = new Object[columnas];
                        for (int i = 0; i < columnas; i++) {
                            fila[i] = respuesta.getObject(i + 1);
                        }
                        modelo.addRow(fila);
                    }
                    table1.setModel(modelo);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        insertarPorductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ingreso_producto.getText().isEmpty() || ingreso_stock.getText().isEmpty() || ingreso_precio.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(pantalla, "Por favor, ingrese todos los datos");
                    return;
                }
                try{
                    repuesto.setNombre(ingreso_producto.getText());
                    repuesto.setStock(Integer.parseInt(ingreso_stock.getText()));
                    repuesto.setPrecio(Double.parseDouble(ingreso_precio.getText()));
                    if (repuesto.registroCompleto()){
                        String ConsultaSQL = "INSERT INTO Repuestos (nombre_pieza, stock, precio, imagen) VALUES (?, ?, ?, ?)";
                        int realizado = conexion.InsercionExplicita(ConsultaSQL, repuesto.getDatos());
                        if (realizado > 0){
                            JOptionPane.showMessageDialog(pantalla, "Registro insertado correctamente", "Acción Exitosa", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else if (realizado == 0){
                            JOptionPane.showMessageDialog(pantalla, "No se inserto el registro", "Error en la inserción", JOptionPane.ERROR_MESSAGE);
                        }
                        else {
                            int opcion = JOptionPane.showConfirmDialog(pantalla, "Desea actualizar el producto", "Producto ya registrado", JOptionPane.YES_NO_OPTION);
                            if (opcion == JOptionPane.YES_OPTION){
                                ConsultaSQL = "UPDATE Repuestos SET stock = ?, precio = ?, imagen = ? WHERE nombre_pieza = ?";
                                ArrayList<Object> elementos = new ArrayList<>();
                                elementos.add(repuesto.getStock());
                                elementos.add(repuesto.getPrecio());
                                elementos.add(repuesto.getImagen());
                                elementos.add(repuesto.getNombre());
                                realizado = conexion.InsercionExplicita(ConsultaSQL, elementos);
                                if (realizado > 0){
                                    JOptionPane.showMessageDialog(pantalla, "Registro actualizado correctamente", "Acción Exitosa", JOptionPane.INFORMATION_MESSAGE);
                                }
                                else if (realizado == 0){
                                    JOptionPane.showMessageDialog(pantalla, "No se actualizo el registro", "Error en la actualización", JOptionPane.ERROR_MESSAGE);
                                }
                                else {
                                    JOptionPane.showMessageDialog(pantalla, "Error en la actualización del registro", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            } else {
                                JOptionPane.showMessageDialog(pantalla, "No se inserto el registro", "Error en la inserción", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(pantalla, "Error en la entrada de datos", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(pantalla, "Error en la entrada de datos");
                }

            }
        });
        seleccionarUnaImagenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser archivo = new JFileChooser();
                int ventana = archivo.showOpenDialog(null);
                if (ventana == JFileChooser.APPROVE_OPTION){
                    File archivoImagen = archivo.getSelectedFile();
                    JFrame imgs = new JFrame("Visor de imagenes");
                    imgs.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    imgs.setSize(400, 400);
                    imgs.setContentPane(new VisorImagenes(archivoImagen, repuesto).Visor);
                    imgs.pack();
                    imgs.setLocationRelativeTo(null); // Centrar la ventana
                    imgs.setResizable(false);
                    imgs.setVisible(true);
                }

            }
        });
        accederButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String opcionSeleccionada2 = (String) comboBox2.getSelectedItem();
                ResultSet datos = null;
                assert opcionSeleccionada2 != null;
                if (opcionSeleccionada2.equals("Presione para actualizar..."))
                {
                    JOptionPane.showMessageDialog(pantalla, "Por favor, seleccione un usuario");
                    return;
                } else if (opcionSeleccionada2.equals("Todo"))
                {
                    datos = conexion.Consulta("SELECT\t" +
                            "c.nombres," +
                            "v.producto," +
                            "v.cantidad," +
                            "v.precio_unitario," +
                            "v.total," +
                            "u.Usuario," +
                            "v.fecha_venta\n" +
                            "FROM Ventas v\n" +
                            "JOIN Clientes c ON v.cliente = c.cedula\n" +
                            "JOIN Usuarios u ON v.Responsable = u.codigo_unico");
                } else
                {
                    datos = conexion.Consulta("SELECT\t" +
                            "c.nombres," +
                            "v.producto," +
                            "v.cantidad," +
                            "v.precio_unitario," +
                            "v.total," +
                            "u.Usuario," +
                            "v.fecha_venta\n" +
                            "FROM Ventas v\n" +
                            "JOIN Clientes c ON v.cliente = c.cedula\n" +
                            "JOIN Usuarios u ON v.Responsable = u.codigo_unico\n" +
                            "WHERE u.Usuario ='" + opcionSeleccionada2 + "'");
                }
                ResultSetMetaData meta = null;
                try {
                    meta = datos.getMetaData();
                    int columnas = meta.getColumnCount();
                    DefaultTableModel modelo = new DefaultTableModel();
                    for (int i = 1; i <= columnas; i++) {
                        modelo.addColumn(meta.getColumnName(i));
                    }
                    while (datos.next()) {
                        Object[] fila = new Object[columnas];
                        for (int i = 0; i < columnas; i++) {
                            fila[i] = datos.getObject(i + 1);
                        }
                        modelo.addRow(fila);
                    }
                    table2.setModel(modelo);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nombre.getText().isEmpty() || cedula.getText().length()!=10 || correo.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(pantalla, "Por favor, ingrese todos los datos");
                    return;
                }
                if (!new String(passwordField1.getPassword()).equals(new String(passwordField2.getPassword()))){
                    JOptionPane.showMessageDialog(pantalla, "Las contraseñas no coinciden", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    String nombreUsuario = nombre.getText();
                    String cedulaUsuario = cedula.getText();
                    String correoUsuario = correo.getText();
                    String contrasenia = new String(passwordField2.getPassword());
                    String tipoUsuario = "Cajero";
                    String ConsultaSQL = "INSERT INTO Usuarios (Usuario, Contrasena, `Tipo usuario`, codigo_unico, Correo) VALUES (?, ?, ?, ?, ?)";
                    ArrayList<Object> elementos = new ArrayList<>();
                    elementos.add(nombreUsuario);
                    elementos.add(contrasenia);
                    elementos.add(tipoUsuario);
                    elementos.add(Integer.parseInt(cedulaUsuario));
                    elementos.add(correoUsuario);
                    int realizado = conexion.InsercionExplicita(ConsultaSQL, elementos);
                    if (realizado > 0) {
                        JOptionPane.showMessageDialog(pantalla, "Registro insertado correctamente", "Acción Exitosa", JOptionPane.INFORMATION_MESSAGE);
                    } else if (realizado == 0) {
                        JOptionPane.showMessageDialog(pantalla, "No se inserto el registro", "Error en la inserción", JOptionPane.ERROR_MESSAGE);
                    } else {
                        int opcion = JOptionPane.showConfirmDialog(pantalla, "Desea actualizar el usuario", "Usuario ya registrado", JOptionPane.YES_NO_OPTION);
                        if (opcion == JOptionPane.YES_OPTION) {
                            ConsultaSQL = "UPDATE Usuarios SET Contrasena = ?, `Tipo usuario` = ?, Usuario = ?, Correo = ? WHERE codigo_unico = ?";
                            elementos.add(nombreUsuario);
                            realizado = conexion.InsercionExplicita(ConsultaSQL, elementos);
                            if (realizado > 0) {
                                JOptionPane.showMessageDialog(pantalla, "Registro actualizado correctamente", "Acción Exitosa", JOptionPane.INFORMATION_MESSAGE);
                            } else if (realizado == 0) {
                                JOptionPane.showMessageDialog(pantalla, "No se actualizo el registro", "Error en la actualización", JOptionPane.ERROR_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(pantalla, "Error en la actualización del registro", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(pantalla, "Error en la entrada de datos");
                }
            }
        });
        salirButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LOGIN.frame_2.dispose();
                JOptionPane.showMessageDialog(null, "Adios!!");
            }
        });
        verProductosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Productos");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setContentPane(new VisorProductos(ingreso_producto, conexion).Visor);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
        comboBox2.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                ArrayList<Object> cajeros = conexion.getCajeros();
                comboBox2.removeAllItems();
                comboBox2.addItem("Todo");
                for (Object cajero: cajeros) {
                    comboBox2.addItem((String) cajero);
                }
            }
        });
        comboBox1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                productos = conexion.getProductos();
                comboBox1.removeAllItems();
                comboBox1.addItem("Todo");
                for (Object producto: productos) {
                    comboBox1.addItem((String) producto);
                }
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        comboBox1 = new JComboBox<String>();
        comboBox2 = new JComboBox<String>();
    }
}