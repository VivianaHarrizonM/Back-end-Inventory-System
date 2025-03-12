package vhm.inventarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import vhm.inventarios.model.Producto;

@SpringBootApplication
public class InventariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventariosApplication.class, args);

		//Prueba Lombok
		Producto producto = new Producto();
		producto.setDescripcion("Camisa M");
		producto.setPrecio(600.0);
		producto.setExistencia(30);

		//Imprimir el objeto se llama toString de Lombok
		System.out.println(producto);

	}

}
