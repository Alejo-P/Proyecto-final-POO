import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    public Patalla_Admin() {
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
    }
    }
