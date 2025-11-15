package Enums;

public enum Turno {
    MAÑANA("Mañana"),
    TARDE("Tarde"),
    NOCHE("Noche");

    public final String turno;

    Turno (String turno) {
        this.turno= turno;
    }

    public String getTurno() {
        return turno;
    }
}
