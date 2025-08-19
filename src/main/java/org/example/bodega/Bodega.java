package org.example.bodega;

public class Bodega {
    private int indexProducto = 0;
    private Producto[] productos;
    private Movimiento[] movimientos;
    private int indexMovimiento = 0;

    public Bodega(int cantidadProductos, int cantidadMovimientos) {
        this.productos = new Producto[cantidadProductos];
        this.movimientos = new Movimiento[cantidadMovimientos];
    }

    public void registrarNuevoProducto (Producto producto) {
        try {
            int maxProductos = productos.length;
            if (this.productos[maxProductos - 1] != null) {
                throw new ValidationException("Los productos están llenos");
            }
            this.productos[indexProducto] = producto;
            indexProducto++;
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
        }
    }

    public int getStockProducto (int index) {
        try {
            int acumulado = 0;
            Producto producto = this.productos[index];
            if (producto == null) {
                throw new ValidationException("El producto no existe");
            }
            for (Movimiento m : this.movimientos) {
                if ((m != null) && (m.getProducto() == producto)) {
                    if (m.getTipo().equals(Movimiento.INGRESO)) {
                        acumulado += m.getCantidad();
                    } else {
                        acumulado -= m.getCantidad();
                    }
                }
            }
            return acumulado;
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    private void registrarMovimiento (int index, int cantidad, String tipo) {
        try {
            Producto producto = this.productos[index];
            if (producto == null) {
                throw new ValidationException("El producto que intentas ingresar no existe");
            }
            int stockProducto = this.getStockProducto(index);
            if (tipo.equals(Movimiento.DESPACHO) && stockProducto < cantidad) {
                throw new ValidationException("No hay stock suficiente del producto " + producto.getNombre());
            }
            int maxMovimientos = this.movimientos.length;
            if (this.movimientos[maxMovimientos - 1] != null) {
                throw new ValidationException("Los movimientos están llenos");
            }
            Movimiento nuevo = new Movimiento(producto, cantidad, tipo);
            this.movimientos[indexMovimiento] = nuevo;
            indexMovimiento++;
            System.out.println("Se registró movimiento para producto: " + producto.getNombre());
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
        }
    }

    public void ingresarProducto (int index, int cantidad) {
        registrarMovimiento(index, cantidad, Movimiento.INGRESO);
    }

    public void despacharProducto(int index, int cantidad) {
        registrarMovimiento(index, cantidad, Movimiento.DESPACHO);
    }

    public void printProductos () {
        for (int i = 0; i < this.productos.length; i++) {
            Producto p = this.productos[i];
            if (p != null) {
                System.out.println(p);
                System.out.println("Tiene un stock de " + getStockProducto(i));
                System.out.println("===============");
            }
        }
    }
}
