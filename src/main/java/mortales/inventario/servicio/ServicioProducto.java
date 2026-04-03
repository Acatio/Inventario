package mortales.inventario.servicio;

import mortales.inventario.modelo.Producto;
import mortales.inventario.servicio.dto.ProductoCrearDTO;
import mortales.inventario.servicio.dto.ProductoMostrarDTO;
import mortales.inventario.servicio.exceptions.InvalidProductException;
import mortales.inventario.servicio.exceptions.ProductoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioProducto
{
    @Autowired
    IRepoProducto repoProducto;

    public void agregarProducto(ProductoCrearDTO productoCrearDto)
    {
        validarProducto(productoCrearDto);
        Producto nuevo = crearProducto(productoCrearDto);
        repoProducto.save(nuevo);
    }

    private void validarProducto(ProductoCrearDTO producto)
    {
        if (producto == null) throw new InvalidProductException("El producto no es valido");
        if (producto.nombre().isBlank()) throw new InvalidProductException("El nombre no puede estar vacio");
        if (producto.descripcion().isBlank()) throw new InvalidProductException("La descripcion no puede estar vacia");
        if (producto.existencia() < 0) throw new InvalidProductException("La existencia no puede ser menor a cero");
        if (producto.precio() < 0) throw new InvalidProductException("El precio no puede ser menor a cero");
    }

    private Producto crearProducto(ProductoCrearDTO productoCrearDto)
    {
        Producto nuevo = new Producto();
        nuevo.setNombre(productoCrearDto.nombre());
        nuevo.setDescripcion(productoCrearDto.descripcion());
        nuevo.setExistencia(productoCrearDto.existencia());
        nuevo.setPrecio(productoCrearDto.precio());
        return nuevo;
    }

    public void eliminarProducto(Integer id)
    {
        Producto productoAeliminar = new Producto();
        productoAeliminar.setIdProducto(id);
        repoProducto.delete(productoAeliminar);
    }

    public List<ProductoMostrarDTO> listarProductos()
    {
        List<Producto> productos = repoProducto.findAll();
        return productos.stream()
                .map(this::toDto)
                .toList();
    }

    private ProductoMostrarDTO toDto(Producto p)
    {
        return new ProductoMostrarDTO(
                p.getIdProducto(),
                p.getNombre(),
                p.getDescripcion(),
                p.getPrecio(),
                p.getExistencia()
        );

    }

    public ProductoMostrarDTO getProductoById(Integer id)
    {
        Producto producto = repoProducto.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException(id));

        return toDto(producto);
    }


}
