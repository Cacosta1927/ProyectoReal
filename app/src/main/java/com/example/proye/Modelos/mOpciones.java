package com.example.proye.Modelos;

public class mOpciones {

    String id, idObjeto, nombre;

    public mOpciones() {
    }

    public mOpciones(String id, String idObjeto, String nombre) {
        this.id = id;
        this.idObjeto = idObjeto;
        this.nombre = nombre;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdObjeto() {
        return idObjeto;
    }

    public void setIdObjeto(String idObjeto) {
        this.idObjeto = idObjeto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



}