import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaAdmin {
    JComboBox<String> comboboxOpciones;
    private JButton salirButton;
    private JButton enterButton;
    private JPanel panel_admini;
    public PantallaAdmin(){

        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame=(JFrame) SwingUtilities.getWindowAncestor(PantallaAdmin.this.panel_admini);
                frame.dispose();
                JOptionPane.showMessageDialog(null,"Haz salido del sistema");

            }
        });

        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String opcionSeleccionada = (String) comboboxOpciones.getSelectedItem();
                System.out.println(opcionSeleccionada);
                switch (opcionSeleccionada) {

                    case "Productos en stock":
                        JFrame stock = new JFrame("Productos en Stock");
                        stock.setContentPane(new productosStock().productosStock);
                        stock.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        stock.setVisible(true);
                        stock.pack();
                        break;
                    case "Vendores":
                        JFrame cajeros = new JFrame("Elija el cajero a su eleccion");
                        cajeros.setContentPane(new PantallaVendedores().Pantalla_Num_Cajero);
                        cajeros.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        cajeros.setVisible(true);
                        cajeros.pack();
                        break;
                    case "Agregar Cajero Nuevo":
                        JFrame agregarC = new JFrame("Cajero nuevo");
                        agregarC.setContentPane(new PantallaAgregarC().nuevo_cajero);
                        agregarC.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        agregarC.setVisible(true);
                        agregarC.pack();
                        break;
                    case null:
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opcion no valida");
                }

            }
        });
    }
}
