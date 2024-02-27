import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.*;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Objects;

public class Pantalla_Cajero {
    JPanel panel_cajero;
    private JTabbedPane Panel1;
    private JTextField ingreso_cedula;
    private JTextField ingreso_nombre_apellido;
    private JTextField ingreso_valor_a_pagar;
    private JButton Boton1;
    private JButton boton_confirmacion;
    private JButton boton_factura;
    private JButton boton_cerrar_sesion;
    private JTextField ingreso_codigo;
    private JTextField ingreso_vendedor;
    private JTextField ingreso_direccion;
    private JTextField ingreso_telefono;
    private JComboBox ingreso_producto;
    private JSpinner ingreso_cantidad;
    private JButton buscarButton;
    private JTabbedPane tabbedPane1;
    private JButton volverButton;
    private JButton imprimirButton;
    private JButton seleccionarButton;
    private JTable Tabla_info_ventas;
    private JTextField ID_producto_buscar;
    private JButton buscarStockButton;
    private JTable table3;
    private JButton actualizarButton;
    private JButton eliminarButton;
    private JButton boton_cancelar;
    private JFormattedTextField Formato_fecha;
    private JLabel Imagen_producto;
    private Conexion conexion;

    //Constructor de la clase con parametros para el paso de valores de la clase LOGIN
    //y la clase Conexion
    public Pantalla_Cajero(Conexion info, String usuario) {
        final double[] precio_pieza = new double[1];
        DefaultTableModel modelo = new DefaultTableModel(); // Crearun modelo para la tabla
        DefaultTableModel Stock_modelo = new DefaultTableModel(); // Crearun modelo para la tabla
        this.conexion = info; //Guardar el valor de info en la variable conexion
        // Ejecutar una consulta sql para obtener el codigo unico y el nombre del vendedor
        String query = "select codigo_unico, usuario from Usuarios WHERE usuario='%s'".formatted(usuario);
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
                boolean procesar_compra=false;
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
                    //Extraer el valor del campo, transformarlo y convertirlo a decimal
                    double valor_a_pagar= Double.parseDouble(ingreso_valor_a_pagar.getText().replace(',', '.'));

                    if (cedula.length()!=10 || nombre_apellido.isEmpty()||direccion.isEmpty()||telefono.length()!=10 || valor_a_pagar<=0){
                        JOptionPane.showMessageDialog(null,"IMPOSIBLE PROCESAR, Llene adecuadamente lo campos de texto");
                    } else if (producto.equals("Seleccione el producto...") ||cantidad<=0) {
                        JOptionPane.showMessageDialog(null,"No se ha selecionado ningun producto o cantidad de producto");
                    } else{
                        //aqui se ingresarian los datos a la base de datos
                        //y se mostraria un mensaje de exito
                        int insercion = conexion.Insertar("INSERT INTO Clientes (cedula, nombres, direccion, telefono) VALUES ('"+cedula+"','"+nombre_apellido+"','"+direccion+"','"+telefono+"')");
                        if (insercion<0){
                            int opcion = JOptionPane.showConfirmDialog(panel_cajero,"Desea actualizar el registro", "Cliente ya registrado", JOptionPane.YES_NO_OPTION);
                            if (opcion==JOptionPane.YES_OPTION){
                                int columnas_afectadas = conexion.Insertar("UPDATE Clientes SET nombres='"+nombre_apellido+"', direccion='"+direccion+"', telefono='"+telefono+"' WHERE cedula='"+cedula+"'");
                                if (columnas_afectadas>0){
                                    procesar_compra=true;
                                    JOptionPane.showMessageDialog(null,"Registro actualizado con EXITO");
                                }
                                else {
                                    JOptionPane.showMessageDialog(null,"Error al actualizar el registro");
                                }
                            }
                        }
                        else if (insercion>0){
                            procesar_compra=true;
                        }
                        else {
                            JOptionPane.showMessageDialog(null,"Error al realizar el registro");
                        }
                        if (procesar_compra){
                            ResultSet resultado = conexion.Consulta("SELECT id FROM Repuestos WHERE nombre_pieza='"+producto+"'");
                            int id=0;
                            while (resultado.next()){
                                id=resultado.getInt("id");
                            }
                            int columnas_afectadas = conexion.Insertar("INSERT INTO Ventas (cliente, producto, cantidad, precio_unitario, total, responsable) VALUES ('"+cedula+"','"+producto+"','"+cantidad+"','"+precio_pieza[0]+"','"+valor_a_pagar+"','"+codigo_vendedor+"')");
                            if (columnas_afectadas>0){
                                //actualizar el stock del producto
                                conexion.Insertar("UPDATE Repuestos SET stock=stock-"+cantidad+" WHERE id="+id);
                                JOptionPane.showMessageDialog(null,"Compra realizada con EXITO");
                            }
                            else {
                                JOptionPane.showMessageDialog(null,"Error al realizar la compra");
                            }
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
        boton_cancelar.addActionListener(new ActionListener() {
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
        boton_factura.addActionListener(new ActionListener() {
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

                //en esta parte del codigo remplazamos la como por un punto para poder convertir el valor a pagar en double.
                String valor = ingreso_valor_a_pagar.getText();
                valor=valor.replace(",", ".");

                if (cedula.isEmpty()||nombre_apellido.isEmpty()||direccion.isEmpty()||telefono.isEmpty()
                        || valor.isEmpty() || producto.equals("Seleccione el producto...") ||cantidad==0){
                    JOptionPane.showMessageDialog(null, "No se puede generar la Factura, campos vacios");
                }
                else{
                    // Si la cadena no es nula convierte su valor a double
                    double valor_a_pagar= Double.parseDouble(valor);
                    FACTURA factura_generada = new FACTURA(codigo_vendedor,nombre_vendedor,cedula,nombre_apellido,direccion,telefono,cantidad,producto,valor_a_pagar);
                    factura_generada.crear_factura();
                    JOptionPane.showMessageDialog(null, "Factura generada con exito");
                }
            }
        });
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResultSet resultado;

                if (!VerificarNumeros(Formato_fecha.getText())){
                    resultado = conexion.Consulta("SELECT * FROM Ventas");
                }
                else {
                    resultado = conexion.Consulta("SELECT * FROM Ventas WHERE fecha_venta='"+Formato_fecha.getText()+"'");
                }
                // Obtener metadatos de la consulta para configurar las columnas de la tabla
                ResultSetMetaData metaDatos = null;

                try {
                    modelo.setRowCount(0); // Limpiar la tabla
                    modelo.setColumnCount(0); // Limpiar la tabla
                    Tabla_info_ventas.setModel(modelo); // Asignar el modelo a la tabla
                    metaDatos = resultado.getMetaData();
                    int columnas = metaDatos.getColumnCount();
                    for (int i = 1; i <= columnas; i++) {
                        modelo.addColumn(metaDatos.getColumnName(i));
                    }
                    // Agregar filas a la tabla con los datos de la consulta
                    while (resultado.next()) {
                        Object[] filas = new Object[columnas];
                        for (int i = 1; i <= columnas; i++) {
                            filas[i - 1] = resultado.getObject(i);
                        }
                        modelo.addRow(filas);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        ingreso_producto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //ESTA PARTE DEL CODIGO TOMA EL PRODUCTO SELECCIONADO Y BUSCA SU PRECIO EN LA BASE DE DATOS
                //Y LO MUESTRA EN EL CAMPO DE TEXTO
                String producto = String.valueOf(ingreso_producto.getSelectedItem());
                if (producto.equals("Seleccione el producto...")){
                    Imagen_producto.setIcon(null);
                    ingreso_valor_a_pagar.setText("");
                    ingreso_cantidad.setValue(0);
                    ingreso_cantidad.setEnabled(false);
                }
                else {
                    try {
                        ResultSet resultado = conexion.Consulta("SELECT * FROM Repuestos WHERE nombre_pieza='"+producto+"'");
                        int cantidad=0;
                        Blob imagen_blob=null;
                        while (resultado.next()){
                            cantidad=resultado.getInt("stock");
                            precio_pieza[0]=resultado.getDouble("precio");
                            // Extraer la imagen de la base de datos
                            imagen_blob=resultado.getBlob("imagen");
                            byte[] imagen_bytes = imagen_blob.getBytes(1, (int) imagen_blob.length());
                            ImageIcon imagen = new ImageIcon(imagen_bytes);
                            // Ubicar la imagen en el JLabel correspondiente y redimensionarla
                            Image imagen_redimensionada = imagen.getImage().getScaledInstance(Imagen_producto.getWidth(), Imagen_producto.getHeight(), Image.SCALE_SMOOTH);
                            Imagen_producto.setIcon(new ImageIcon(imagen_redimensionada));
                        }
                        actualizarRango(0, cantidad, 0, 1);
                        ingreso_cantidad.setEnabled(true);



                        ingreso_valor_a_pagar.setText(String.valueOf(precio_pieza[0] * Integer.parseInt(String.valueOf(ingreso_cantidad.getValue()))));

                    }catch (Exception ex){
                        System.out.println(ex);
                    }
                }
            }
        });
        ingreso_cantidad.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                //ESTA PARTE DEL CODIGO TOMA EL PRODUCTO SELECCIONADO Y BUSCA SU PRECIO EN LA BASE DE DATOS
                //Y LO MUESTRA EN EL CAMPO DE TEXTO
                String producto = String.valueOf(ingreso_producto.getSelectedItem());
                if (producto.equals("Seleccione el producto...")){
                    ingreso_valor_a_pagar.setText("");
                }
                else {
                    try {
                        ResultSet resultado = conexion.Consulta("SELECT precio FROM Repuestos WHERE nombre_pieza='"+producto+"'");
                        double precio=0;
                        while (resultado.next()){
                            precio=resultado.getDouble("precio");
                        }
                        int cantidad=Integer.parseInt(String.valueOf(ingreso_cantidad.getValue()));
                        String valor = "%.2f".formatted(precio*cantidad);
                        ingreso_valor_a_pagar.setText(valor);
                    }catch (Exception ex){
                        System.out.println(ex);
                    }
                }
            }
        });
        buscarStockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResultSet resultado;
                //ESTA PARTE DEL CODIGO BUSCA EL PRODUCTO POR SU ID
                //Y MUESTRA SU INFORMACION EN LA TABLA
                String id_producto = ID_producto_buscar.getText();
                if (id_producto.isEmpty()){
                    resultado = conexion.Consulta("SELECT * FROM Repuestos");
                }
                else {
                    resultado = conexion.Consulta("SELECT * FROM Repuestos WHERE id='" + id_producto + "'");
                }
                try {
                    // Obtener metadatos de la consulta para configurar las columnas de la tabla
                    ResultSetMetaData metaDatos = null;
                    Stock_modelo.setRowCount(0); // Limpiar la tabla
                    Stock_modelo.setColumnCount(0); // Limpiar la tabla
                    table3.setModel(Stock_modelo); // Asignar el modelo a la tabla
                    metaDatos = resultado.getMetaData();
                    int columnas = metaDatos.getColumnCount();
                    for (int i = 1; i <= columnas; i++) {
                        Stock_modelo.addColumn(metaDatos.getColumnName(i));
                    }
                    // Agregar filas a la tabla con los datos de la consulta
                    while (resultado.next()) {
                        Object[] filas = new Object[columnas];
                        for (int i = 1; i <= columnas; i++) {
                            filas[i - 1] = resultado.getObject(i);
                        }
                        Stock_modelo.addRow(filas);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private static boolean VerificarNumeros(String cadena){
        for (char letra : cadena.toCharArray()) {
            if (letra == ' ') {
                return false; // Si es un espacio en blanco, retornar falso
            }
            if (letra == '-') {
                continue; // Si es un guión medio "-", saltar a la siguiente iteración del bucle
            }
            if (!Character.isDigit(letra)) {
                return false; // Si no es un dígito y no es un guión medio "-", retornar falso
            }
        }
        return true;
    }
    private void actualizarRango(int minimo, int maximo, int valor_inicial, int pasos){
        //ESTA PARTE DEL CODIGO LIMITA EL RANGO DE VALORES QUE SE PUEDEN INGRESAR EN EL SPINNER
        SpinnerNumberModel rango = new SpinnerNumberModel(valor_inicial, minimo, maximo, pasos);
        ingreso_cantidad.setModel(rango);
    }
    private void createUIComponents() throws ParseException {
        // TODO: place custom component creation code here
        //ESTA PARTE DEL CODIGO CREA UN FORMATO PARA EL CAMPO DE TEXTO
        MaskFormatter formato = new MaskFormatter("####-##-##");
        Formato_fecha = new JFormattedTextField(formato);
        ingreso_cantidad = new JSpinner();
        actualizarRango(0, 0, 0, 1);
        ingreso_cantidad.setEnabled(false);
    }
}
