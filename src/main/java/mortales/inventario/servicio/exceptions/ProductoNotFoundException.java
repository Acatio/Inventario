package mortales.inventario.servicio.exceptions;

public class ProductoNotFoundException extends RuntimeException
{
    public ProductoNotFoundException(Integer id)
    {
        super("No se encontró el producto con id " + id);
    }
}
