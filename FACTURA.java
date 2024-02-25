import com.lowagie.text.*;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;

import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class FACTURA {

    //Parametros que se imprimiran en el PDF
    String cedula;
    String nombre;
    String direccion;
    String telefono;
    int cantidad;
    String producto;
    double valor_a_pagar;


    Document documento;
    FileOutputStream archivo;
    Paragraph titulo;


    public FACTURA(String cedula, String nombre, String direccion, String telefono, int cantidad,
                   String producto, double valor_a_pagar) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.cantidad = cantidad;
        this.producto = producto;
        this.valor_a_pagar = valor_a_pagar;

        documento = new Document();
        titulo = new Paragraph("FACTURA GENERADA");

    }

    public void crear_factura(){

        try {

            //el archivo se guadara con el numero de cedula correspodiente de cada cliente
            archivo = new FileOutputStream("PDF_generado\\"+cedula+".pdf");
            PdfWriter.getInstance(documento, archivo);

            //abrimos el documento para que pueda ser editado
            documento.open();
            //El numero 1 significa que el titulo sera colocado en el centro
            //los siguientes elementos sera agregados en el documento

            //Genera una linea en blanco


            documento.add(Chunk.NEWLINE);
            titulo.setAlignment(1);
            documento.add(titulo);
            documento.add(Chunk.NEWLINE);
            documento.add(new Paragraph("Cliente: "+nombre));
            documento.add(new Paragraph("Cedula de identidad: "+cedula));
            documento.add(new Paragraph("Direccion: "+direccion));
            documento.add(new Paragraph("Telefono: "+telefono));
            documento.add(new Paragraph("Producto adquirido: "+producto));
            documento.add(new Paragraph("Cantidad: "+cantidad));
            documento.add(new Paragraph("Precio final: $"+valor_a_pagar));

            //cerramos el documento
            documento.close();
            System.out.println("archivo creado con exito...");

        }catch (FileNotFoundException e){
            System.err.println(e.getMessage());
        }
        catch (DocumentException e){
            System.err.println(e.getMessage());
        }
    }
}
