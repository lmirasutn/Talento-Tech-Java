package com.techlab.pedidos;

import com.techlab.excepciones.StockInsuficienteException;
import com.techlab.productos.Producto;
import com.techlab.productos.ProductoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PedidoService {

    private final ProductoService productoService;
    private final List<Pedido> pedidos = new ArrayList<>();

    public PedidoService(ProductoService productoService) {
        this.productoService = productoService;
    }

    public void crearPedidoInteractivo(Scanner sc) throws StockInsuficienteException {
        Pedido pedido = new Pedido();

        System.out.print("¿Cuántos productos vas a agregar al pedido?: ");
        int cantidadProductos = sc.nextInt();
        sc.nextLine(); // Limpiar buffer

        for (int i = 0; i < cantidadProductos; i++) {
            System.out.print("ID del producto: ");
            int idProducto = sc.nextInt();

            Producto producto = productoService.buscarProductoPorId(idProducto);
            if (producto == null) {
                System.out.println("Producto no encontrado.");
                i--; // repetir este paso
                continue;
            }

            System.out.print("Cantidad: ");
            int cantidad = sc.nextInt();

            pedido.agregarProducto(producto, cantidad);
            System.out.println("Producto agregado al pedido.");
        }

        pedidos.add(pedido);
        System.out.println("\nPedido creado con éxito:");
        pedido.mostrarDetalle();
    }

    public void listarPedidos() {
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos realizados.");
        } else {
            for (int i = 0; i < pedidos.size(); i++) {
                System.out.println("\nPedido #" + (i + 1));
                pedidos.get(i).mostrarDetalle();
            }
        }
    }
}
