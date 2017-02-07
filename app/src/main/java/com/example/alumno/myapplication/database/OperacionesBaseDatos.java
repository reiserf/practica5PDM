package com.example.alumno.myapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.alumno.myapplication.entity.Asignatura;
import com.example.alumno.myapplication.entity.Profesor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class OperacionesBaseDatos {

    private static BaseDatosClase baseDatos;

    private static OperacionesBaseDatos instancia = new OperacionesBaseDatos();

    private OperacionesBaseDatos() {
    }

    public static OperacionesBaseDatos obtenerInstancia(Context contexto) {
        if (baseDatos == null) {
            baseDatos = new BaseDatosClase(contexto);
        }
        return instancia;
    }

    /**
     {OPERACIONES PROFESOR}
     **/

    // Obtener profesor
    public Cursor obtenerProfesores() {
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s", BaseDatosClase.Tablas.PROFESOR);
        return db.rawQuery(sql, null);
    }

    // Insertar profesor
    public String insertarProfesor(Profesor p) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(MetaDatos.CabecerasProfesor.NOMBREPROF, p.getNomProf());
        valores.put(MetaDatos.CabecerasProfesor.FECHANAC, dateToString(p.getFecha_nac()));
        valores.put(MetaDatos.CabecerasProfesor.IMAGEN, p.getImagen());

        return db.insertOrThrow(BaseDatosClase.Tablas.PROFESOR, null, valores) > 0 ? null : null;
    }

    // Borrar profesor
    public boolean eliminarProfesor(int idProfesor) {

        SQLiteDatabase db = baseDatos.getWritableDatabase();

        eliminarAsignatura(idProfesor);

        String whereClause = String.format("%s=?", MetaDatos.CabecerasProfesor.CODPROF);
        final String[] whereArgs = {String.valueOf(idProfesor)};

        int resultado = db.delete(BaseDatosClase.Tablas.PROFESOR, whereClause, whereArgs);

        return resultado > 0;
    }

    /**
        OPERACIONES ASIGNATURAS INICIO
    **/

    // Obtener Asignaturas
    public Cursor obtenerAsignaturas() {
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s", BaseDatosClase.Tablas.ASIGNATURA);
        return db.rawQuery(sql, null);
    }

    public Cursor obtenerAsignaturasPorProfesor(int codProf) {
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s WHERE %s = %s", BaseDatosClase.Tablas.ASIGNATURA, MetaDatos.CabecerasAsignatura.CODPROF, codProf);
        return db.rawQuery(sql, null);
    }


    // Insertar asignaturas
    public String insertarAsignatura(Asignatura a) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(MetaDatos.CabecerasAsignatura.NOMASIG, a.getNombAsig());
        valores.put(MetaDatos.CabecerasAsignatura.DESCRIPCION, a.getDescripcion());
        valores.put(MetaDatos.CabecerasAsignatura.CODPROF, a.getCodProf());

        return db.insertOrThrow(BaseDatosClase.Tablas.ASIGNATURA, null, valores) > 0 ? null : null;
    }

    // Eliminar asignatura
    public boolean eliminarAsignatura(int idProfesor) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        String whereClause = String.format("%s=?", MetaDatos.CabecerasAsignatura.CODPROF);
        final String[] whereArgs = {String.valueOf(idProfesor)};

        int resultado = db.delete(BaseDatosClase.Tablas.ASIGNATURA, whereClause, whereArgs);

        return resultado > 0;
    }

    public boolean eliminarAsignatura(int idAsignatura,boolean flag) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        String whereClause = String.format("%s=?", MetaDatos.CabecerasAsignatura.CODASIG);
        final String[] whereArgs = {String.valueOf(idAsignatura)};

        int resultado = db.delete(BaseDatosClase.Tablas.ASIGNATURA, whereClause, whereArgs);

        return resultado > 0;
    }



    public SQLiteDatabase getDb() {
        return baseDatos.getWritableDatabase();
    }

    // Pasar de fecha a string
    public String dateToString(Date d) {

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String reportDate = df.format(d);

        return reportDate;
    }


}