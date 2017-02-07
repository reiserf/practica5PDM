package com.example.alumno.myapplication.entity;


public class Asignatura {

    private String nombAsig,descripcion;
    private int codProf;
    private int codAsig;

    public Asignatura(String nomb, String desc, int codProf){
        this.nombAsig=nomb;
        this.descripcion=desc;
        this.codProf=codProf;

    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombAsig() {
        return nombAsig;
    }

    public void setNombAsig(String nombAsig) {
        this.nombAsig = nombAsig;
    }

    public int getCodProf() {
        return codProf;
    }

    public void setCodProf(int codProf) {
        this.codProf = codProf;
    }

    public int getCodAsig() {
        return codAsig;
    }

    public void setCodAsig(int codAsig) {
        this.codAsig = codAsig;
    }
}
