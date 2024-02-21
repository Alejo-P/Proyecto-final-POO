import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaAdmin {
    JComboBox<String> comboboxOpciones;
    private JButton salirButton;
    private JButton enterButton;
    public JPanel panel_admin;
    public PantallaAdmin(){

        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame=(JFrame) SwingUtilities.getWindowAncestor(PantallaAdmin.this.panel_admin);
                frame.dispose();
                JOptionPane.showMessageDialog(null,"Haz salido del sistema");

            }
        });

        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String opcionSeleccionada = (String) comboboxOpciones.getSelectedItem();

                switch (comboboxOpciones) {
                    //
                    case productosStock ignored:
                        JFrame stock = new JFrame("Productos en Stock");
                        stock.setContentPane(new productosStock().productosStock);
                        stock.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        stock.setVisible(true);
                        stock.pack();
                    case PantallaVendedores ignored:
                        JFrame cajeros = new JFrame("Elija el cajero a su eleccion");
                        cajeros.setContentPane(new PantallaVendedores().Pantalla_Num_Cajero);
                        cajeros.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        cajeros.setVisible(true);
                        cajeros.pack();
                    case PantallaAgregarC ignored:
                        JFrame agregarC = new JFrame("Cajero nuevo");
                        agregarC.setContentPane(new PantallaAgregarC().nuevo_cajero);
                        agregarC.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        agregarC.setVisible(true);
                        agregarC.pack();
                }

            }
        });
    }
}
