import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaCajero {
    JPanel panel_cajero;
    private JComboBox comboBox1;
    private JButton salirButton;
    private JButton enterButton;

    public PantallaCajero() {
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame=(JFrame) SwingUtilities.getWindowAncestor(PantallaCajero.this.panel_cajero);
                frame.dispose();
                JOptionPane.showMessageDialog(null,"Haz salido del sistema");


            }
        });
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame transaccion=new JFrame("Transacciones");
                transaccion.setContentPane(new PantallaTransaccion().PantallaTransac);
                transaccion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                transaccion.setVisible(true);
                transaccion.pack();
            }
        });
    }
}
