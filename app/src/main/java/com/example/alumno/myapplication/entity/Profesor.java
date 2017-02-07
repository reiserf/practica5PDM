package com.example.alumno.myapplication.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by alumno on 16/01/17.
 */

public class Profesor {

    private String nomProf;
    private Date fecha_nac;
    private int imagen;
    private int codProf;

    public Profesor(String nombre, Date fecha, int imagen) {
        this.nomProf = nombre;
        this.fecha_nac = fecha;
        this.imagen = imagen;
    }


    public String getNomProf() {
        return nomProf;
    }

    public void setNomProf(String nomProf) {
        this.nomProf = nomProf;
    }

    public Date getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(Date fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public int getCodProf() {
        return codProf;
    }

    public void setCodProf(int codProf) {
        this.codProf = codProf;
    }
}
