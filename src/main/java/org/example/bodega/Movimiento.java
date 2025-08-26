package org.example.bodega;

public class Movimiento {
    private String fecha;
    private Producto producto;
    private int cantidad;
    private TipoMovimiento tipo;

    public Movimiento(String fecha, Producto producto, int cantidad, TipoMovimiento tipo) {
        this.fecha = fecha;
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

    public TipoMovimiento getTipo() {
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
