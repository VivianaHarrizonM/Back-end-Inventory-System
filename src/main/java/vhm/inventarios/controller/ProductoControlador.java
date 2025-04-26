package vhm.inventarios.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vhm.inventarios.exception.RecursoNoEncontradoExcepcion;
import vhm.inventarios.model.Producto;
import vhm.inventarios.service.ProductoServicio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("inventario-app") // http://localhost:8080/inventario-app
@CrossOrigin(origins = {"https://front-end-inventory-system.onrender.com",
        "https://bright-griffin-7fc033.netlify.app"})//"http://localhost:4200" // Puerto por defecto de Angular
public class ProductoControlador {
  private static final Logger logger = LoggerFactory.getLogger(ProductoControlador.class);

  @Autowired
  private ProductoServicio productoServicio;

  @GetMapping("/productos") // http://localhost:8080/inventario-app/productos
  public List<Producto> obtenerProductos() {
    List<Producto> productos = this.productoServicio.listarProductos();
    logger.info("Se obtuvieron {} productos.", productos.size());
    productos.forEach(producto -> logger.debug("Producto: {}", producto));
    return productos;
  }

  @PostMapping("/productos")
  public Producto agregarProducto(@RequestBody Producto producto) {
    logger.info("Producto a agregar: {}", producto);
    return this.productoServicio.guardarProducto(producto);
  }

  @GetMapping("/productos/{id}")
  public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable int id) {
    Producto producto = this.productoServicio.buscarProductoPorId(id);
    if (producto != null) {
      return ResponseEntity.ok(producto);
    } else {
      throw new RecursoNoEncontradoExcepcion("No se encontró el id " + id);
    }
  }

  @PutMapping("/productos/{id}")
  public ResponseEntity<Producto> actualizarProducto(@PathVariable int id, @RequestBody Producto productoRecibido) {
    Producto producto = this.productoServicio.buscarProductoPorId(id);
    if (producto == null) {
      throw new RecursoNoEncontradoExcepcion("No se encontró el id: " + id);
    }

    producto.setDescripcion(productoRecibido.getDescripcion());
    producto.setPrecio(productoRecibido.getPrecio());
    producto.setExistencia(productoRecibido.getExistencia());

    this.productoServicio.guardarProducto(producto);
    return ResponseEntity.ok(producto);
  }

  @DeleteMapping("/productos/{id}") // Corregido de "producto" a "productos"
  public ResponseEntity<Map<String, Boolean>> eliminarProducto(@PathVariable int id) {
    Producto producto = this.productoServicio.buscarProductoPorId(id);
    if (producto == null) {
      throw new RecursoNoEncontradoExcepcion("No se encontró el id: " + id);
    }
    this.productoServicio.eliminarProductoPorId(producto.getIdProducto());
    Map<String, Boolean> respuesta = new HashMap<>();
    respuesta.put("Eliminado", Boolean.TRUE);
    return ResponseEntity.ok(respuesta);
  }
}
