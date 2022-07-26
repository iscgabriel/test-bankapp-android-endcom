package com.gabrielvalles.test_bankapp_android_endcom.models;

public class Cuenta {

    public String   nombre;
    public String   ultimaSesion;
    public long     noCuenta;
    public int      id;

    public int getId() {
        return id;
    }

    public String getNombreCompleto() {
        return nombre;
    }

    public long getNoCuenta() {
        return noCuenta;
    }

    public String getUltimaSesion() {
        return ultimaSesion;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNoCuenta(long noCuenta) {
        this.noCuenta = noCuenta;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombre = nombreCompleto;
    }

    public void setUltimaSesion(String ultimaSesion) {
        this.ultimaSesion = ultimaSesion;
    }
}
