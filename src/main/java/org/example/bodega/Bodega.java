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
                    if (m.getTipo().equals(TipoMovimiento.INGRESO)) {
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

    private void registrarMovimiento (String fecha, int index, int cantidad, TipoMovimiento tipo) {
        try {
            boolean esFechaValida = ValidadorFecha.isValid(fecha);
            if (!esFechaValida) {
                throw new ValidationException("La fecha del movimiento no es válida");
            }
            Producto producto = this.productos[index];
            if (producto == null) {
                throw new ValidationException("El producto que intentas ingresar no existe");
            }
            int stockProducto = this.getStockProducto(index);
            if (tipo.equals(TipoMovimiento.DESPACHO) && stockProducto < cantidad) {
                throw new ValidationException("No hay stock suficiente del producto " + producto.getNombre());
            }
            int maxMovimientos = this.movimientos.length;
            if (this.movimientos[maxMovimientos - 1] != null) {
                throw new ValidationException("Los movimientos están llenos");
            }
            Movimiento nuevo = new Movimiento(fecha, producto, cantidad, tipo);
            this.movimientos[indexMovimiento] = nuevo;
            indexMovimiento++;
            System.out.println("Se registró movimiento para producto: " + producto.getNombre());
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
        }
    }

    public void ingresarProducto (String fecha, int index, int cantidad) {
        registrarMovimiento(fecha, index, cantidad, TipoMovimiento.INGRESO);
    }

    public void despacharProducto(String fecha,int index, int cantidad) {
        registrarMovimiento(fecha, index, cantidad, TipoMovimiento.DESPACHO);
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
