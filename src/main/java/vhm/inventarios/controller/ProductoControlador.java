package vhm.inventarios.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vhm.inventarios.model.Producto;
import vhm.inventarios.service.ProductoServicio;

import java.util.List;

@RestController
@RequestMapping("inventario-app") //http://localhost:8080/inventario-app
@CrossOrigin(value = "http://localhost:4200") //Puerto por default de Angular
public class ProductoControlador {
  private static final Logger logger = LoggerFactory.getLogger(ProductoControlador.class);

  @Autowired
  private ProductoServicio productoServicio;

  @GetMapping("/productos") //http://localhost:8080/inventario-app/productos
  public List<Producto> obtenerProductos(){
    List<Producto> productos = this.productoServicio.listarProductos();
    logger.info("Productos obtenidos: ");
    productos.forEach(producto -> logger.info(producto.toString()));
    return productos;
  }
  @PostMapping("/productos")
  public Producto agregarProducto(@RequestBody Producto producto){
    logger.info("Producto a agregar: " + producto);
    return this.productoServicio.guardarProducto(producto);
  }
}
