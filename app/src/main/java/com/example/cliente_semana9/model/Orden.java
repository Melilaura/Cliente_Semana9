package com.example.cliente_semana9.model;

public class Orden {
    private String orden,tiempo;


    public Orden(String type, String tiempo) {
        super();
        this.orden = type;
        this.tiempo = tiempo;
    }

    public Orden() {

    }

    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }
}
