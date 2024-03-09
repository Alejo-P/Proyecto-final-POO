import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class main {
    private static Point initialClick;
    static JFrame frame = new JFrame();

    public static void main(String[] args) {
        frame.setContentPane(new LOGIN().Inicio_Cajero);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true); //Elimina la barra de título

        //Permite mover la ventana arrastrándola con el mouse----------------------
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                initialClick=e.getPoint();
                frame.getComponentAt(initialClick);
            }
        });
        frame.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int thisX=frame.getLocation().x;
                int thisY= frame.getLocation().y;

                int xMoved=thisX+e.getX()-initialClick.x;
                int yMoved=thisY+e.getY()-initialClick.y;

                frame.setLocation(xMoved, yMoved);
            }
        });
        //------------------------------------------------------
        frame.pack();
        //hace que la pantalla aparecía en el centro
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
