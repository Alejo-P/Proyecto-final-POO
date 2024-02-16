import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LOGIN {
    JPanel Inicio_Cajero;
    private JTextField textField1;
    private JComboBox comboBox1;
    private JPasswordField passwordField1;
    private JButton ingresarButton;
    private JButton borrarButton;
    private JButton salirButton;

    public LOGIN() {
        JButton botonBorrar = new JButton("Borrar");
        borrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                borrarCampos();
            }
        });
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //boton que permite cerrar el programa
                //muestra en mensaje al momento de salir
                JOptionPane.showMessageDialog(null, "Gracias por usar este sistema");
                main.frame.dispose();
            }
        });
    }

    private void borrarCampos(){
        String usuarioTexto=textField1.getText();
        String contraseñaTexto=new String(passwordField1.getPassword());
        // Verifica si hay caracteres en el campo de usuario antes de intentar borrar
        if(!usuarioTexto.isEmpty()){
            // Borra la última letra del campo de usuario
            textField1.setText(usuarioTexto.substring(0, usuarioTexto.length()-1));
        }
        if(!contraseñaTexto.isEmpty()){
            passwordField1.setText(contraseñaTexto.substring(0, contraseñaTexto.length()-1));


        }

    }
}