package mortales.inventario.servicio.dto;

public record ProductoMostrarDTO(
        Integer id,
        String nombre,
        String descripcion,
        Double precio,
        Integer existencia
)
{
}
