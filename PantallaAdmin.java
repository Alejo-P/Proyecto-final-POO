import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaAdmin {
    private JComboBox comboBox1;
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
                JFrame stock=new JFrame("Productos en Stock");
                stock.setContentPane(new productosStock().productosStock);
                stock.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                stock.setVisible(true);
                stock.pack();
                stock.dispose();
            }
        });
    }
}
