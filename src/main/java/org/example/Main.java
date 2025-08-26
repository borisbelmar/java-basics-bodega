package org.example;

import org.example.bodega.Producto;
import org.example.bodega.Bodega;

public class Main {
    public static void main(String[] args) {
        Bodega nuevaTienda = new Bodega(5, 20);

        nuevaTienda.registrarNuevoProducto(new Producto("Cafe", 20));

        nuevaTienda.printProductos();

        nuevaTienda.despacharProducto("XX-08-2025", 0, 2);
        nuevaTienda.ingresarProducto("24-08-2025", 0, 10);
        nuevaTienda.ingresarProducto("23-08-2025", 0, 20);
        nuevaTienda.despacharProducto("22-08-2025", 1, 5);

        nuevaTienda.printProductos();
    }
}