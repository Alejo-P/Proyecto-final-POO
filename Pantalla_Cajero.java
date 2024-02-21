import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Pantalla_Cajero {
    JPanel PCajero;
    private JTabbedPane Panel1;
    private JTextField TXF1;
    private JTextField TXF2;
    private JTextField textField4;
    private JButton Boton1;
    private JButton Boton2;
    private JButton Boton3;
    private JButton Boton4;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField1;
    private JTextField textField5;
    private JComboBox comboBox1;
    private JSpinner spinner1;
    private JButton buscarButton;
    private JTextField textField7;
    private JTabbedPane tabbedPane1;
    private JButton volverButton;
    private JButton imprimirButton;
    private JButton seleccionarButton;
    private JTable table2;
    private JTable table1;
    private JTextField textField8;
    private JButton buscarButton1;
    private JTable table3;
    private JButton actualizarButton;
    private JButton eliminarButton;
    private JButton CANCELARCOMPRAButton;
    private Component panel_cajero;

    public Pantalla_Cajero() {
        Boton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame=(JFrame) SwingUtilities.getWindowAncestor(Pantalla_Cajero.this.panel_cajero);
                frame.dispose();
                JOptionPane.showMessageDialog(null,"Haz salido del sistema");
            }
        });

    }
}
