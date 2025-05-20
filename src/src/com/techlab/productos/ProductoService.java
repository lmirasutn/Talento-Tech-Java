package com.techlab.productos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class ProductoService {
    private List<Producto> productos = new ArrayList<>();

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }


    public void agregarProductoInteractivo(Scanner sc) {
        System.out.print("Ingrese el nombre del producto: ");
        String nombre = sc.nextLine();

        System.out.print("Ingrese el precio del producto: ");
        double precio = sc.nextDouble();

        System.out.print("Ingrese el stock del producto: ");
        int stock = sc.nextInt();

        Producto nuevo = new Producto(nombre, precio, stock);
        productos.add(nuevo);
        System.out.println("Producto agregado con éxito.");
    }


    public void actualizarProducto(Scanner sc) {
        System.out.print("Ingrese el ID del producto a actualizar: ");
        int id = sc.nextInt();

        Producto p = buscarProductoPorId(id);
        if (p == null) {
            System.out.println("Producto no encontrado.");
            return;
        }

        System.out.print("Nuevo nombre (actual: " + p.getNombre() + "): ");
        sc.nextLine(); // limpiar buffer
        String nombre = sc.nextLine();
        if (!nombre.isBlank()) p.setNombre(nombre);

        System.out.print("Nuevo precio (actual: $" + p.getPrecio() + "): ");
        double precio = sc.nextDouble();
        p.setPrecio(precio);

        System.out.print("Nuevo stock (actual: " + p.getStock() + "): ");
        int stock = sc.nextInt();
        p.setStock(stock);

        System.out.println("Producto actualizado.");
    }


    public void eliminarProducto(Scanner sc) {
        System.out.print("Ingrese el ID del producto a eliminar: ");
        int id = sc.nextInt();

        Iterator<Producto> it = productos.iterator();
        while (it.hasNext()) {
            Producto p = it.next();
            if (p.getId() == id) {
                it.remove();
                System.out.println("Producto eliminado.");
                return;
            }
        }
        System.out.println("Producto no encontrado.");
    }

    public Producto buscarProductoPorId(int id) {
        for (Producto p : productos) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    public List<Producto> listarProductos() {
        return productos;
    }
}
