import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class LOGIN {
    JPanel Inicio_Cajero;
    private JTextField textField1;
    private JComboBox comboBox1;
    private JPasswordField passwordField1;
    private JButton ingresarButton;
    private JButton borrarButton;
    private JButton salirButton;
    private Conexion BDD;
    // Variable para guardar el nombre del campo con el foco
    private String CampoConFoco = "";
    static JFrame frame_2=new JFrame();

    public LOGIN() {
        JButton botonBorrar = new JButton("Borrar");
        // Asignar nombre a los campos de texto y contraseña
        textField1.setName("usuario");
        passwordField1.setName("contraseña");

        // Obtener el campo de texto que tiene el foco
        textField1.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // Acción cuando el campo de texto gana el foco (Capturar el nombre del campo con foco)
                CampoConFoco=textField1.getName();
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Acción cuando el campo de texto pierde el foco
            }
        });
        // Obtener el campo de contraseña que tiene el foco
        passwordField1.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // Acción cuando el campo de texto gana el foco (Capturar el combre del campo con foco)
                CampoConFoco=passwordField1.getName();
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Acción cuando el campo de texto pierde el foco
            }
        });
        borrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuarioTexto=textField1.getText();
                String contraseñaTexto=new String(passwordField1.getPassword());

                // Verifica si hay caracteres en el campo de usuario antes de intentar borrar
                // Y verificar si el ultimo campo con foco es el del usuario
                if (CampoConFoco.equals("usuario")){
                    // Comprobar si no esta vacio el campo de usuario
                    if(!usuarioTexto.isEmpty()){
                        // Borra la última letra del campo de usuario
                        textField1.setText(usuarioTexto.substring(0, usuarioTexto.length()-1));
                    }
                }
                // Verifica si hay caracteres en el campo de contraseña antes de intentar borrar
                // Y verificar si el ultimo campo con foco es el de la contraseña
                if (CampoConFoco.equals("contraseña")){
                    // Comprobar si no esta vacio el campo de contraseña
                    if(!contraseñaTexto.isEmpty()){
                        // Borra la última letra del campo de usuario
                        passwordField1.setText(contraseñaTexto.substring(0, contraseñaTexto.length()-1));
                    }
                }
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
        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BDD = new Conexion("JAVA","123456", "jdbc:mysql://localhost:3306/base_poo");

                if (BDD.getCredenciales(textField1.getText(), new String (passwordField1.getPassword()), comboBox1.getSelectedItem().toString())){
                    JOptionPane.showMessageDialog(null, "Ingreso Exitoso");
                    if (BDD.getTipoUsuario().equals("Cajero"))
                    {
                        frame_2.setContentPane(new Pantalla_Cajero().panel_cajero);
                        frame_2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        frame_2.pack();
                        frame_2.setVisible(true);
                        main.frame.dispose();
                    }
                    else{
                        //JFrame pantallaAdministrador=new JFrame("Administrador");
                        frame_2.setContentPane(new Patalla_Admin().pantalla);
                        frame_2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        frame_2.pack();
                        frame_2.setVisible(true);
                        main.frame.dispose();
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
                }
            }
        });
    }
}
