package dam.m6.uf2.model;

public class Deportista {
    private int cod;
    private String nombre;
    private Integer codDeporte;
    private String deporteNombre;

    public Deportista(int cod, String nombre, Integer codDeporte) {
        this.cod = cod;
        this.nombre = nombre;
        this.codDeporte = codDeporte;
    }

    public Deportista(int cod, String nombre, Integer codDeporte, String deporteNombre) {
        this.cod = cod;
        this.nombre = nombre;
        this.codDeporte = codDeporte;
        this.deporteNombre = deporteNombre;
    }

    public Deportista(String nombre, Integer codDeporte) {
        this.nombre = nombre;
        this.codDeporte = codDeporte;
    }

    public int getCod() {
        return cod;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getCodDeporte() {
        return codDeporte;
    }

    public String getDeporteNombre() {
        return deporteNombre;
    }
}