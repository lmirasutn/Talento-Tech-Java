package com.techlab.pedidos;

import com.techlab.excepciones.StockInsuficienteException;
import com.techlab.productos.Producto;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private List<LineaPedido> lineas = new ArrayList<>();

    public void agregarProducto(Producto producto, int cantidad) throws StockInsuficienteException {
        if (producto.getStock() < cantidad) {
            throw new StockInsuficienteException("Stock insuficiente para el producto: " + producto.getNombre());
        }
        producto.disminuirStock(cantidad);
        lineas.add(new LineaPedido(producto, cantidad));
    }

    public double calcularTotal() {
        return lineas.stream().mapToDouble(LineaPedido::getSubtotal).sum();
    }

    public void mostrarDetalle() {
        System.out.println("Detalle del pedido:");
        for (LineaPedido lp : lineas) {
            System.out.println(lp);
        }
        System.out.println("TOTAL: $" + calcularTotal());
    }
}
