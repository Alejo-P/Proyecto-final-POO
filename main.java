import javax.swing.*;

public class main {
    static JFrame frame = new JFrame("Inicio Cajero");
    public static void main(String[] args) {
        frame.setContentPane(new LOGIN().Inicio_Cajero);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //hace que la pantalla aparesca en el centro
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }
}
