package com.example.examen;

public class Cliente {

    private String nombre;
    private String sexo;

    private int peso;

    private int edad;

    private int talla;

    private String tipoActividad;

    private String Observaciones;

    public Cliente(String nombre, String sexo, int peso, int edad, int talla, String tipoActividad, String observaciones) {
        this.nombre = nombre;
        this.sexo = sexo;
        this.peso = peso;
        this.edad = edad;
        this.talla = talla;
        this.tipoActividad = tipoActividad;
        Observaciones = observaciones;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nombre='" + nombre + '\'' +
                ", sexo='" + sexo + '\'' +
                ", peso=" + peso +
                ", edad=" + edad +
                ", talla=" + talla +
                ", tipoActividad='" + tipoActividad + '\'' +
                ", Observaciones='" + Observaciones + '\'' +
                '}';
    }

    public Cliente() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getTalla() {
        return talla;
    }

    public void setTalla(int talla) {
        this.talla = talla;
    }

    public String getTipoActividad() {
        return tipoActividad;
    }

    public void setTipoActividad(String tipoActividad) {
        this.tipoActividad = tipoActividad;
    }

    public String getObservaciones() {
        return Observaciones;
    }

    public void setObservaciones(String observaciones) {
        Observaciones = observaciones;
    }
}

