package com.techlab.pedidos;

import com.techlab.productos.Producto;

public class LineaPedido {
    private Producto producto;
    private int cantidad;

    public LineaPedido(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return producto.getPrecio() * cantidad;
    }

    @Override
    public String toString() {
        return cantidad + " x " + producto.getNombre() + " = $" + getSubtotal();
    }
}
