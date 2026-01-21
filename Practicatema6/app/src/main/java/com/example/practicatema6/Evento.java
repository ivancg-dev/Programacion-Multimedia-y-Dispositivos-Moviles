package com.example.practicatema6;

public class Evento {

    private String nombre;
    private String fecha;
    private String hora;

    public Evento(String nombre, String fecha, String hora) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.hora = hora;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
