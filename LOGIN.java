import javax.swing.*;
import java.awt.*;
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
                // Conexion local
                //BDD = new Conexion("JAVA","123456", "jdbc:mysql://localhost:3306/base_poo");
                // Conexion en la nube
                BDD = new Conexion("uuaswpmfddjijnsj","P0iihEDAEoSCjcNLblwX", "jdbc:mysql://uuaswpmfddjijnsj:P0iihEDAEoSCjcNLblwX@bx96efbqmpafypdnsonx-mysql.services.clever-cloud.com:3306/bx96efbqmpafypdnsonx?autoReconnect=true");

                if (BDD.getCredenciales(textField1.getText(), new String (passwordField1.getPassword()), comboBox1.getSelectedItem().toString())){
                    JOptionPane.showMessageDialog(null, "Ingreso Exitoso");
                    if (BDD.getTipoUsuario().equals("Cajero")) {
                        frame_2.setContentPane(new Pantalla_Cajero(BDD, textField1.getText()).panel_cajero);
                        frame_2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        frame_2.pack();
                        frame_2.setVisible(true);
                        main.frame.dispose(); //Hace que la pantalla de inicio desaparezca una vez que se ingrese


                        //Centrar la ventana en la pantalla
                        Dimension screenSize= Toolkit.getDefaultToolkit().getScreenSize();//Obtiene el tamaño de la pantalla
                        int width=frame_2.getSize().width;//Obtiene el ancho  de la ventana actual
                        int height=frame_2.getSize().height;//Obtiene el alto  de la ventana actual
                        int x=(screenSize.width-width)/2; //Calcula las coordenadas x y y para centrar la ventana en la pantalla
                        int y=(screenSize.height-height)/2;
                        frame_2.setLocation(x,y); //Establece la ubicacion de la ventana en las coordenadas calculads


                    }
                    else{
                        Patalla_Admin pantallaAdmin = new Patalla_Admin(BDD);
                        frame_2.setContentPane(pantallaAdmin.pantalla); // Establece el contenido del JFrame frame_2 como el panel de la pantalla de administrador
                        frame_2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// Establece la operación de cierre del JFrame frame_2
                        frame_2.pack();// Hace que el JFrame frame_2 ajuste automáticamente su tamaño según su contenido
                        frame_2.setVisible(true);  // Hace visible el JFrame frame_2
                        main.frame.dispose(); // Cierra el JFrame main.frame (la pantalla de inicio)
                        centerFrameOnScreen(frame_2); // Centra el JFrame frame_2 en la pantalla llamando al método centerFrameOnScreen
                    }

                }
                else {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
                }
            }
        });
    }
    //Creación de la instancia de la clase Patalla_Admin
    private static void centerFrameOnScreen(JFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = frame.getSize().width;
        int height = frame.getSize().height;
        int x = (screenSize.width - width) / 2;
        int y = (screenSize.height - height) / 2;
        frame.setLocation(x, y);
    }

}
