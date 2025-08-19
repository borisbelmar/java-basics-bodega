package org.example.bodega;

public class Movimiento {
    final public static String INGRESO = "Ingreso";
    final public static String DESPACHO = "Despacho";
    private Producto producto;
    private int cantidad;
    private String tipo;

    public Movimiento(Producto producto, int cantidad, String tipo) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.tipo = tipo;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return "Movimiento{" +
                "producto=" + producto +
                ", cantidad=" + cantidad +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
