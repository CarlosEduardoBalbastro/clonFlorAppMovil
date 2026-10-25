package com.ispc.florappmovil;

public class Especie {

    private int id;
    private String nombre_comun;
    private String nombre_cientifico;
    private String descripcion;
    private String imagen;

    public Especie() {}

    // Constructor con parámetros por si querés instanciar rápido en local
    public Especie(int id, String nombre_comun, String nombre_cientifico, String descripcion, String imagen) {
        this.id = id;
        this.nombre_comun = nombre_comun;
        this.nombre_cientifico = nombre_cientifico;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNombreComun() {
        return nombre_comun;
    }

    public String getNombreCientifico() {
        return nombre_cientifico;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNombreComun(String nombre_comun) {
        this.nombre_comun = nombre_comun;
    }

    public void setNombreCientifico(String nombre_cientifico) {
        this.nombre_cientifico = nombre_cientifico;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}