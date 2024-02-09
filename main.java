import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Inicio Cajero");
        frame.setContentPane(new InicioCajero().Inicio_Cajero);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
