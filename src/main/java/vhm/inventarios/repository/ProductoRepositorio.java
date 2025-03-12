package vhm.inventarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vhm.inventarios.model.Producto;

public interface ProductoRepositorio extends JpaRepository<Producto, Integer> {

}
