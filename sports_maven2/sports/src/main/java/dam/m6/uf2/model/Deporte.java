package dam.m6.uf2.model;

public class Deporte {
    private int cod;
    private String nombre;

    public Deporte(int cod, String nombre) {
        this.cod = cod;
        this.nombre = nombre;
    }

    public Deporte(String nombre) {
        this.nombre = nombre;
    }

    public int getCod() {
        return cod;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return cod + " - " + nombre;
    }
}
