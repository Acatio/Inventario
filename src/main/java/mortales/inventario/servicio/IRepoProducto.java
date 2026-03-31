package mortales.inventario.servicio;

import mortales.inventario.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepoProducto extends JpaRepository<Producto,Integer>
{
}
