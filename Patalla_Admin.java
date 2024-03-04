import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Patalla_Admin {
    private JTabbedPane tabbedPane1;
    private JComboBox comboBox1;
    private JButton comprobarButton;
    private JComboBox comboBox2;
    private JButton accederButton;
    private JButton salirButton;
    private JButton salirButton1;
    private JPasswordField passwordField1;
    private JTextField textField1;
    private JPasswordField passwordField2;
    private JButton agregarButton;
    JPanel pantalla;
    private JTable table1;
    private JTable table2;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField5;
    private JButton salirButton2;
    private JTextField ingreso_producto;
    private JTextField ingreso_stock;
    private JTextField ingreso_precio;
    private JButton seleccionarUnaImagenButton;
    private JButton insertarPorductoButton;
    private Repuesto repuesto;
    private Conexion conexion;


    /* public Patalla_Admin() {
             comprobarButton.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     String opcionSeleccionada = (String) comboBox1.getSelectedItem();
                     DefaultTableModel modelo_stock = new DefaultTableModel();
                     modelo_stock.addColumn();
                     switch (opcionSeleccionada) {
                         case "Disco de Frenos":

                             JOptionPane.showMessageDialog(null, "Se encuentra en stock");
                             break;
                         case "Pastillas de Frenos":
                             JOptionPane.showMessageDialog(null, "Se encuentra en stock");
                             break;
                         case "Bujias":
                             JOptionPane.showMessageDialog(null, "Se encuentra en stock");
                             break;
                         case "Filtro de Aceite":
                             JOptionPane.showMessageDialog(null, "Se encuentra en stock");
                             break;
                         case "Bomba de Gasolina":
                             JOptionPane.showMessageDialog(null, "Se encuentra en stock");
                             break;
                         case "Bateria":
                             JOptionPane.showMessageDialog(null, "Se encuentra en stock");
                             break;
                         case "Aceite de Motor":
                             JOptionPane.showMessageDialog(null, "Se encuentra en stock");
                             break;
                         case "Refrigerante":
                             JOptionPane.showMessageDialog(null, "Se encuentra en stock");
                             break;
                         case "Neumaticos":
                             JOptionPane.showMessageDialog(null, "Se encuentra en stock");
                             break;
                         case "Pedales":
                             JOptionPane.showMessageDialog(null, "Se encuentra en stock");
                             break;
                         default:
                             JOptionPane.showMessageDialog(null, "No has seleccionado ninguna opción");
                             break;
                     }
                 }
             });
         }*/
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

                // Aquí debes tener un JTable previamente creado en tu interfaz
                table1.setModel(modelo_stock);
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
    }
    }
