import javax.swing.*;
import java.awt.event.*;

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


    public Pantalla_Cajero() {
        /*boton_cerrar_sesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame=(JFrame) SwingUtilities.getWindowAncestor(Pantalla_Cajero.this.panel_cajero);
                frame.dispose();
                JOptionPane.showMessageDialog(null,"Haz salido del sistema");
            }
        });*/


        boton_confirmacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


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

                    if (cedula.isEmpty()||nombre_apellido.isEmpty()||direccion.isEmpty()||telefono.isEmpty()
                            ||valor_a_pagar<=0){
                        JOptionPane.showMessageDialog(null,"IMPOSIBLE PROCESAR, campo de texto vacio");


                    } else if (producto=="Seleccione el producto..."||cantidad<=0) {
                        JOptionPane.showMessageDialog(null,"No se ha selecionado ningun producto o " +
                                "cantidad de producto");

                    } else if (codigo_vendedor.isEmpty()||nombre_vendedor.isEmpty()){
                        JOptionPane.showMessageDialog(null, "No se ingresaron datos del vendedor");

                    }
                    else{
                        //aqui se ingresarian los datos a la base de datos
                        JOptionPane.showMessageDialog(null,"Compra realizada con EXITO");
                        System.out.println(codigo_vendedor);
                        System.out.println(nombre_vendedor);
                        System.out.println(cedula);
                        System.out.println(nombre_apellido);
                        System.out.println(direccion);
                        System.out.println(telefono);
                        System.out.println(cantidad);
                        System.out.println(producto);
                        System.out.println(valor_a_pagar);
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

    }
}
