package com.example.proye.Modelos;

public class mArticulos {

    String id, categoria, nombre, imagen;

    public mArticulos() {
    }

    public mArticulos(String id, String categoria, String nombre, String imagen) {
        this.id = id;
        this.categoria = categoria;
        this.nombre = nombre;
        this.imagen = imagen;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

}