package com.gabrielvalles.test_bankapp_android_endcom.models;

public class Saldos {
    public long     cuenta;
    public float      saldoGeneral;
    public float      ingresos;
    public float      gastos;
    public float      id;

    public long getCuenta() {
        return cuenta;
    }

    public void setCuenta(long cuenta) {
        this.cuenta = cuenta;
    }

    public float getSaldoGeneral() {
        return saldoGeneral;
    }

    public void setSaldoGeneral(int saldoGeneral) {
        this.saldoGeneral = saldoGeneral;
    }

    public float getIngresos() {
        return ingresos;
    }

    public void setIngresos(int ingresos) {
        this.ingresos = ingresos;
    }

    public float getGastos() {
        return gastos;
    }

    public void setGastos(int gastos) {
        this.gastos = gastos;
    }

    public float getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
