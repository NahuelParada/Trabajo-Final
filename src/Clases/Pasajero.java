package Clases;

public class Pasajero {
    /// Atributos
    private String dni;
    private String nombre;
    private String apellido;
    private String gmail;
    private String telefono;
    private String origen;
    private String domicilio;

    /// Constructor
    public Pasajero(String dni, String nombre, String apellido, String gmail, String telefono, String origen, String domicilio) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.gmail = gmail;
        this.telefono = telefono;
        this.origen = origen;
        this.domicilio = domicilio;
    }


    ///  ToString

    @Override
    public String toString() {
        return "Información de pasajero: \n" +
                "  -dni: '" + dni + "'\n" +
                "  -nombre: '" + nombre + "'\n" +
                "  -apellido: '" + apellido + "'\n" +
                "  -gmail: '" + gmail + "'\n" +
                "  -telefono: '" + telefono + "'\n" +
                "  -origen: '" + origen + "'\n" +
                "  -domicilio: '" + domicilio + "'" +
                " ";
    }
}
