import javax.swing.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Objects;

public class Pantalla_Cajero {
    JPanel panel_cajero;
    private JTabbedPane Panel1;
    private JTextField ingreso_cedula;
    private JTextField ingreso_nombre_apellido;
    private JTextField ingreso_valor_a_pagar;
    private JButton Boton1;
    private JButton boton_confirmacion;
    private JButton Boton3;
    private JButton boton_cerrar_sesion;
    private JTextField ingreso_codigo;
    private JTextField ingreso_vendedor;
    private JTextField ingreso_direccion;
    private JTextField ingreso_telefono;
    private JComboBox ingreso_producto;
    private JSpinner ingreso_cantidad;
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
    private Conexion conexion;

    //Constructor de la clase con parametros para el paso de valores de la clase LOGIN
    //y la clase Conexion
    public Pantalla_Cajero(Conexion info, String usuario) {
        this.conexion = info; //Guardar el valor de info en la variable conexion
        // Ejecutar una consulta sql para obtener el codigo unico y el nombre del vendedor
        String query = "select codigo_unico, usuario from usuarios WHERE usuario='%s'".formatted(usuario);
        ResultSet informacion=conexion.Consulta(query);
        // Llenar los campos de texto con la informacion obtenida de la consulta
        try {
            while (informacion.next()){
                ingreso_codigo.setText(informacion.getString("codigo_unico"));
                ingreso_vendedor.setText(informacion.getString("usuario"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        boton_confirmacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //ESTA PARTE DEL CODIGO TOMA LOS DATOS Y LOS ALMACENA EN VARIABLES
                //TAMBIEN ESTA VALIDADOS
                //EL TRY-CATCH MUESTRA UN MENSAJE CUANDO EL USARIO INGRESA UN VALOR DIFERENTE AL ESPERADO
                // ----> EJ. INGRESE PRECIO (EL USUARIO INGRESA UN PALABRA)
                try {
                    String codigo_vendedor=ingreso_codigo.getText();
                    String nombre_vendedor=ingreso_vendedor.getText();
                    String cedula=ingreso_cedula.getText();
                    String nombre_apellido=ingreso_nombre_apellido.getText();
                    String direccion=ingreso_direccion.getText();
                    String telefono=ingreso_telefono.getText();
                    int cantidad=Integer.parseInt(String.valueOf(ingreso_cantidad.getValue()));
                    String producto = String.valueOf(ingreso_producto.getSelectedItem());
                    double valor_a_pagar= Double.parseDouble(ingreso_valor_a_pagar.getText());

                    if (cedula.length()!=10 || nombre_apellido.isEmpty()||direccion.isEmpty()||telefono.length()!=10 || valor_a_pagar<=0){
                        JOptionPane.showMessageDialog(null,"IMPOSIBLE PROCESAR, Llene adecuadamente lo campos de texto");
                    } else if (producto.equals("Seleccione el producto...") ||cantidad<=0) {
                        JOptionPane.showMessageDialog(null,"No se ha selecionado ningun producto o cantidad de producto");
                    } else{
                        //aqui se ingresarian los datos a la base de datos
                        //y se mostraria un mensaje de exito
                        conexion.Insertar("INSERT INTO Clientes (cedula, nombres, direccion, telefono) VALUES ('"+cedula+"','"+nombre_apellido+"','"+direccion+"','"+telefono+"')");
                        ResultSet resultado = conexion.Consulta("SELECT precio FROM repuestos WHERE nombre_pieza='"+producto+"'");
                        double precio=0;
                        while (resultado.next()){
                            precio=resultado.getDouble("precio");
                        }
                        int columnas_afectadas = conexion.Insertar("INSERT INTO Ventas (cliente, producto, cantidad, precio_unitario, total, responsable) VALUES ('"+cedula+"','"+producto+"','"+cantidad+"','"+precio+"','"+valor_a_pagar+"','"+codigo_vendedor+"')");
                        if (columnas_afectadas>0){
                            JOptionPane.showMessageDialog(null,"Compra realizada con EXITO");
                        }
                        else {
                            JOptionPane.showMessageDialog(null,"Error al realizar la compra");
                        }
                    }
                }catch (Exception ex){
                    System.out.println(ex);
                    JOptionPane.showMessageDialog(null, "Error en los datos");
                }

            }
        });
        boton_cerrar_sesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Adios!!");
                LOGIN.frame_2.dispose();

            }
        });
        CANCELARCOMPRAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //ESTA PARTE DEL CODIGO CANCELA LA COMPRA
                //LIMPIA LOS CAMPOS DE TEXTO QUE FUERO LLENADOS PREVIAMENTE

                String valor_combobox="Seleccione el producto...";

                ingreso_cedula.setText("");
                ingreso_nombre_apellido.setText("");
                ingreso_direccion.setText("");
                ingreso_telefono.setText("");
                ingreso_cantidad.setValue(0);
                ingreso_producto.setSelectedItem(valor_combobox);
                ingreso_valor_a_pagar.setText("");
                JOptionPane.showMessageDialog(null, "Compra CANCELADA");

            }
        });
        Boton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String codigo_vendedor=ingreso_codigo.getText();
                String nombre_vendedor=ingreso_vendedor.getText();
                String cedula=ingreso_cedula.getText();
                String nombre_apellido=ingreso_nombre_apellido.getText();
                String direccion=ingreso_direccion.getText();
                String telefono=ingreso_telefono.getText();
                int cantidad=Integer.parseInt(String.valueOf(ingreso_cantidad.getValue()));
                String producto = String.valueOf(ingreso_producto.getSelectedItem());
                double valor_a_pagar= Double.parseDouble(ingreso_valor_a_pagar.getText());

                if (cedula.isEmpty()||nombre_apellido.isEmpty()||direccion.isEmpty()||telefono.isEmpty()
                        ||valor_a_pagar<=0||producto=="Seleccione el producto..."){


                }
            }
        });
    }
}
