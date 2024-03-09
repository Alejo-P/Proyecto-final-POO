import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class VisorProductos {
    private JTable table1;
    private JButton seleccionarButton;
    JPanel Visor;
    private JProgressBar progressBar1;
    private JButton actualizarButton;
    private JLabel Informacion;

    public VisorProductos(JTextField Eproducto, Conexion conn) {
        Informacion.setText("Sin actualizar!");
        progressBar1.setIndeterminate(false);
        table1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) { // Verificar si la selección ha cambiado
                    int selectedRow = table1.getSelectedRow();
                    int selectedColumn = table1.getSelectedColumn();
                    if (selectedRow != -1 && selectedColumn != -1) { // Verificar si se ha seleccionado una celda válida
                        Object selectedValue = table1.getValueAt(selectedRow, 0);
                        // Realiza la acción que deseas hacer con el valor seleccionado
                        Eproducto.setText(selectedValue.toString());
                    }
                }
            }
        });

        seleccionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cerrar la ventana
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(Visor);
                frame.dispose();
            }
        });
        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Simular una tarea que se ejecuta en segundo plano
                SwingWorker<Void, Integer> worker = new SwingWorker<Void, Integer>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        progressBar1.setIndeterminate(true);
                        Informacion.setText("Consultando...");
                        ResultSet respuesta = conn.Consulta("SELECT * FROM Repuestos");
                        ResultSetMetaData meta = respuesta.getMetaData();
                        int columnas = meta.getColumnCount();
                        DefaultTableModel modelo = new DefaultTableModel();
                        for (int i = 1; i <= columnas; i++) {
                            modelo.addColumn(meta.getColumnName(i));
                        }
                        Informacion.setText("Cargando...");
                        while (respuesta.next()) {
                            Object[] fila = new Object[columnas];
                            for (int i = 0; i < columnas; i++) {
                                fila[i] = respuesta.getObject(i + 1);
                            }
                            modelo.addRow(fila);
                        }
                        table1.setModel(modelo);
                        return null;
                    }

                    @Override
                    protected void done() {
                        // Tarea completada
                        Informacion.setText("Actualizado!");
                        actualizarButton.setEnabled(true); // Habilitar el botón de inicio nuevamente
                        progressBar1.setIndeterminate(false); // Restablecer el valor de la barra de progreso
                    }
                };
                worker.execute(); // Ejecutar la tarea en segundo plano
                actualizarButton.setEnabled(false); // Deshabilitar el botón de inicio mientras se ejecuta la tarea
            }
        });
    }
}
