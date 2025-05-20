package com.techlab;

import com.techlab.excepciones.StockInsuficienteException;
import com.techlab.pedidos.Pedido;
import com.techlab.pedidos.PedidoService;
import com.techlab.productos.Producto;
import com.techlab.productos.ProductoService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProductoService productoService = new ProductoService();
        PedidoService pedidoService = new PedidoService(productoService);
        boolean salir = false;

        while (!salir) {
            System.out.println("\n======= MENÚ =======");
            System.out.println("1. Agregar Producto");
            System.out.println("2. Listar Productos");
            System.out.println("3. Buscar/Actualizar Producto");
            System.out.println("4. Eliminar Producto");
            System.out.println("5. Crear Pedido");
            System.out.println("6. Listar Pedidos");
            System.out.println("7. Salir");
            System.out.print("Selecciona una opción: ");

            int opcion = sc.nextInt();
            sc.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1 -> productoService.agregarProductoInteractivo(sc);
                case 2 -> productoService.listarProductos().forEach(System.out::println);
                case 3 -> productoService.actualizarProducto(sc);
                case 4 -> productoService.eliminarProducto(sc);
                case 5 -> {
                    try {
                        pedidoService.crearPedidoInteractivo(sc);
                    } catch (StockInsuficienteException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 6 -> pedidoService.listarPedidos();
                case 7 -> {
                    salir = true;
                    System.out.println("Saliendo del sistema...");
                }
                default -> System.out.println("Opción inválida.");
            }
        }
    }
}
