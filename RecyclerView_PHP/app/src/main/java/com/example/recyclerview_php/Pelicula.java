package com.example.recyclerview_php;

public class Pelicula {

    public int ID;
    public String nombre;
    public String director;
    public String descripcion;

    public Pelicula(int ID, String nombre, String director, String descripcion) {
        this.ID = ID;
        this.nombre = nombre;
        this.director = director;
        this.descripcion = descripcion;

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
