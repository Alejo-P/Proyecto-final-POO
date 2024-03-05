import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Patalla_Admin {
    private JTabbedPane tabbedPane1;
    private JComboBox comboBox1;
    private JButton comprobarButton;
    private JComboBox comboBox2;
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
    private Repuesto repuesto;
    private Conexion conexion;

    public Patalla_Admin(Conexion conn) {
        this.conexion = conn;
        this.repuesto = new Repuesto();
        comprobarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String opcionSeleccionada = (String) comboBox1.getSelectedItem();
                DefaultTableModel modelo_stock = new DefaultTableModel();
                modelo_stock.addColumn("Producto");
                modelo_stock.addColumn("Estado");

                switch (opcionSeleccionada) {
                    case "Disco de Frenos":
                    case "Pastillas de Frenos":
                    case "Bujias":
                    case "Filtro de Aceite":
                    case "Bomba de Gasolina":
                    case "Bateria":
                    case "Aceite de Motor":
                    case "Refrigerante":
                    case "Neumaticos":
                    case "Pedales":
                        Object[] rowData = {opcionSeleccionada, "Se encuentra en stock"};
                        modelo_stock.addRow(rowData);
                        break;
                    default:
                        Object[] defaultRowData = {opcionSeleccionada, "No has seleccionado ninguna opción"};
                        modelo_stock.addRow(defaultRowData);
                        break;
                }


                table1.setModel(modelo_stock);
            }
        });
        insertarPorductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{

                    String producto = ingreso_producto.getText();
                    int numero_stock = Integer.parseInt(ingreso_stock.getText());
                    double precio = Double.parseDouble(ingreso_precio.getText());

                    if (producto.isEmpty() || ingreso_stock.getText().isEmpty() || ingreso_precio.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(pantalla, "Por favor, ingrese todos los datos");
                    }
                    else {
                        try {
                            repuesto.setNombre(producto);
                            repuesto.setStock(numero_stock);
                            repuesto.setPrecio(precio);

                            if (repuesto.registroCompleto()) {
                                String ConsultaSQL = "INSERT INTO Repuestos (nombre_pieza, stock, precio, imagen) VALUES (?, ?, ?, ?)";
                                int realizado = conexion.InsercionExplicita(ConsultaSQL, repuesto.getDatos());

                                if (realizado > 0) {
                                    JOptionPane.showMessageDialog(pantalla, "Registro insertado correctamente", "Acción Exitosa", JOptionPane.INFORMATION_MESSAGE);

                                } else if (realizado == 0) {
                                    JOptionPane.showMessageDialog(pantalla, "No se inserto el registro", "Error en la inserción", JOptionPane.ERROR_MESSAGE);

                                }
                            }
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(pantalla, "Error en la entrada de datos");
                        }

                    }

                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Error en ingreso de algun dato..");
                    System.out.println(ex);
                }

            }
        });
        seleccionarUnaImagenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser archivo = new JFileChooser();
                int ventana = archivo.showOpenDialog(null);
                if (ventana == JFileChooser.APPROVE_OPTION) {
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
        salirButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pantalla.add(salirButton1);
                pantalla.setLayout(null);
                pantalla.setVisible(true);

            }
        });
        accederButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String opcionSeleccionada2 = (String) comboBox2.getSelectedItem();
                DefaultTableModel cajero_venta = new DefaultTableModel();
                cajero_venta.addColumn("Producto");
                cajero_venta.addColumn("Estado");

                switch (opcionSeleccionada2) {
                    case "Cajero 1":
                    case "Cajero 2":
                    case "Cajero 3":
                    case "Cajero 4":
                    case "Cajero 5":
                        Object[] rowData = {opcionSeleccionada2, "Sus ventas son"};
                        cajero_venta.addRow(rowData);
                        break;
                    default:
                        Object[] defaultRowData = {opcionSeleccionada2, "No has seleccionado ninguna opción"};
                        cajero_venta.addRow(defaultRowData);
                        break;
                }


                table2.setModel(cajero_venta);
            }
        });

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                       JFrame frame = new JFrame("Agregar Persona");
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.setSize(300, 200);

                        /*JLabel nombreLabel = new JLabel("Nombre:");
                        nombreLabel.setBounds(10, 10, 100, 30);
                        JTextField nombreTextField = new JTextField(20);
                        nombreTextField.setBounds(100, 10, 150, 30);

                        JLabel apellidoLabel = new JLabel("Apellido:");
                        apellidoLabel.setBounds(10, 50, 100, 30);
                        JTextField apellidoTextField = new JTextField(20);
                        apellidoTextField.setBounds(100, 50, 150, 30);

                        JLabel cedulaLabel = new JLabel("Cédula de Identidad:");
                        cedulaLabel.setBounds(10, 90, 150, 30);
                        JTextField cedulaTextField = new JTextField(20);
                        cedulaTextField.setBounds(100, 90, 150, 30);

                        JLabel correoLabel = new JLabel("Correo Electrónico:");
                        correoLabel.setBounds(10, 130, 150, 30);
                        JTextField correoTextField = new JTextField(20);
                        correoTextField.setBounds(100, 130, 150, 30);

                        JLabel usuarioLabel = new JLabel("Usuario:");
                        usuarioLabel.setBounds(10, 170, 150, 30);
                        JTextField usuarioTextField = new JTextField(20);
                        usuarioTextField.setBounds(100, 170, 150, 30);

                        JLabel contraseñaLabel = new JLabel("Contraseña:");
                        contraseñaLabel.setBounds(10, 210, 150, 30);
                        JPasswordField contraseñaTextField = new JPasswordField(20);
                        contraseñaTextField.setBounds(100, 210, 150, 30);

                        JButton agregarButton = new JButton("Agregar");
                        agregarButton.setBounds(100, 250, 100, 30);
                        agregarButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                String nombre = nombreTextField.getText();
                                String apellido = apellidoTextField.getText();
                                String cedula = cedulaTextField.getText();
                                String correo = correoTextField.getText();
                                String usuario = usuarioTextField.getText();
                                String contraseña = new String(contraseñaTextField.getPassword());

                                Persona persona = new Persona(nombre, apellido, cedula, correo, usuario, contraseña);
                                List<Persona> personas = new ArrayList<>();
                                personas.add(persona);

                                JOptionPane.showMessageDialog(null, "Persona agregada con éxito!");
                            }
                        });*/

                        frame.add(nombre);
                        frame.add(cedula);
                        frame.add(correo);
                        frame.add(usuario);
                        frame.add(passwordField1);
                        frame.setLayout(null);
                        frame.setVisible(true);
                    }

                   /* public static class Persona {
                        private String nombre;
                        private String apellido;
                        private String cedula;
                        private String correo;
                        private String usuario;
                        private String contraseña;

                        public Persona(String nombre, String apellido, String cedula, String correo, String usuario, String contraseña) {
                            this.nombre = nombre;
                            this.apellido = apellido;
                            this.cedula = cedula;
                            this.correo = correo;
                            this.usuario = usuario;
                            this.contraseña = contraseña;
                        }
                    }*/

        });
        salirButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LOGIN.frame_2.dispose();
                JOptionPane.showMessageDialog(null, "Adios!!");
            }
        });

    }
}
