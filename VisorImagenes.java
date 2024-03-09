import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;

public class VisorImagenes {
    private JButton usarImagenButton;
    private JButton cancelarButton;
    private JLabel Imagen;
    private Repuesto repuesto;
    JPanel Visor;

    public VisorImagenes(File ruta, Repuesto info) {
        repuesto = info;
        ImageIcon imagen = new ImageIcon(String.valueOf(ruta));
        // Escalar la imagen al tamaño deseado
        Image imagenEscalada = imagen.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon imagenEscaladaIcon = new ImageIcon(imagenEscalada);

        Imagen.setIcon(imagenEscaladaIcon);
        usarImagenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Leer la imagen desde el archivo seleccionado
                FileInputStream Imagen = null;
                try {
                    Imagen = new FileInputStream(ruta);
                    byte[] imagenBytes= Imagen.readAllBytes();
                    Imagen.close();
                    repuesto.setImagen(imagenBytes);
                    JOptionPane.showMessageDialog(Visor, "Imagen cargada y lista para ser insertada en la base de datos", "Acción Exitosa", JOptionPane.INFORMATION_MESSAGE);
                    JFrame frame=(JFrame) SwingUtilities.getWindowAncestor(Visor); // Cerrar la ventana actual
                    frame.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(Visor, "Error al cargar la imagen", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame=(JFrame) SwingUtilities.getWindowAncestor(Visor); //Cerrar la ventana actual
                frame.dispose();
            }
        });
    }
}
