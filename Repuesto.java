import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Arrays;

public class Repuesto {
    // Atributos
    private String nombre;
    private int Stock;
    private double precio;
    private byte[] imagen;
    // Constructor
    public Repuesto() {}
    public Repuesto(String nombre, int stock, double precio, byte[] imagen) {
        this.nombre = nombre;
        this.Stock = stock;
        this.precio = precio;
        this.imagen = imagen;
    }
    // Metodos
    public boolean registroCompleto(){
        return !this.nombre.isEmpty() && this.Stock != 0 && this.precio != 0 && this.imagen != null;
    }

    public ArrayList<Object> getDatos(){
        if (!registroCompleto()){
            return null;
        }
        return new ArrayList<>(Arrays.asList(this.nombre, this.Stock, this.precio, this.imagen));
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int stock) {
        Stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
}
