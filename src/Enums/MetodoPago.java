package Enums;

public enum MetodoPago {

    DEBITO ("DEBITO"),
    CREDITO ("CREDITO"),
    EFECTIVO("EFECTIVO");

    public final String metodo;

    MetodoPago(String metodo) { this.metodo = metodo; }

    public String getMetodo() { return metodo; }
}
