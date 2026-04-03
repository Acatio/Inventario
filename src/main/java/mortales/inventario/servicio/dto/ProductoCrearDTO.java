package mortales.inventario.servicio.dto;

public record ProductoCrearDTO(
        String nombre,
        String descripcion,
        Double precio,
        Integer existencia)
{
}
