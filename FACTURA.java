import com.lowagie.text.*;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
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
    String codigo_vendedor;
    String nombre_vendedor;


    Document documento;
    FileOutputStream archivo;
    Paragraph titulo;
    Paragraph linea;


    public FACTURA(String codigo_vendedor, String nombre_vendedor,String cedula, String nombre, String direccion, String telefono, int cantidad,
                   String producto, double valor_a_pagar) {
        this.codigo_vendedor = codigo_vendedor;
        this.nombre_vendedor = nombre_vendedor;
        this.cedula = cedula;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.cantidad = cantidad;
        this.producto = producto;
        this.valor_a_pagar = valor_a_pagar;

        //Para que la pagina sea de tamaño carta
        documento = new Document(PageSize.LETTER);
        titulo = new Paragraph("FACTURA GENERADA");
        linea = new Paragraph("-----------------------------------------------------------------------------");

    }

    public void crear_factura(){

        try {

            //el archivo se guadara con el numero de cedula correspodiente de cada cliente
            archivo = new FileOutputStream("PDF_generado\\"+cedula+".pdf");
            PdfWriter.getInstance(documento, archivo);

            //abrimos el documento para que pueda ser editado
            documento.open();

            //Definimos la fuente del documento
            Font font = new Font(Font.TIMES_ROMAN, 12, Font.BOLD);

            //Coloca el texto en el centro
            //linea.setAlignment(1);

            //para agregar una imagen al documento, la imagen sera importada de una carpeta ya existente
            Image image = Image.getInstance("Imagenes//descarga.jpeg");

            //para poner la imagen en la parte superior derecha
            float x = documento.getPageSize().getWidth() - image.getScaledWidth();
            float y = documento.getPageSize().getHeight() - image.getScaledHeight();
            image.setAbsolutePosition(x, y);

            //tamaño de la iamgen
            image.scaleToFit(200, 200);

            //los siguientes elementos sera agregados en el documento
            //Genera una linea en blanco
            documento.add(Chunk.NEWLINE);
            documento.add(titulo);
            documento.add(image);
            documento.add(Chunk.NEWLINE);
            documento.add(linea);
            documento.add(new Paragraph("Vendedor: "+nombre_vendedor));
            documento.add(new Paragraph("Codigo de vendedor: "+codigo_vendedor));
            documento.add(linea);
            documento.add(Chunk.NEWLINE);
            documento.add(linea);
            documento.add(new Paragraph("Cliente: "+nombre));
            documento.add(new Paragraph("Cedula de identidad: "+cedula));
            documento.add(new Paragraph("Direccion: "+direccion));
            documento.add(new Paragraph("Telefono: "+telefono));
            documento.add(new Paragraph("Producto adquirido: "+producto));
            documento.add(new Paragraph("Cantidad: "+cantidad));
            documento.add(new Paragraph("Precio final: $"+valor_a_pagar));
            documento.add(linea);

            //cerramos el documento
            documento.close();
            System.out.println("archivo creado con exito...");

        }catch (FileNotFoundException e){
            System.err.println(e.getMessage());
        }
        catch (DocumentException e){
            System.err.println(e.getMessage());
        }
        catch (IOException e ){
            System.err.println(e.getMessage());
        }
    }
}
