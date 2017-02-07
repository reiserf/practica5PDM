package com.example.alumno.myapplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.provider.BaseColumns;

/**
 * Created by jose on 02/02/2017.
 */

public class BaseDatosClase extends SQLiteOpenHelper {

    private static final String NOMBRE_BASE_DATOS = "basedatos.db";
    private static final int VERSION_ACTUAL = 1;
    private final Context contexto;

    interface Tablas {
        String PROFESOR = "cabecera_profesor";
        String ASIGNATURA = "cabecera_asignatura";
    }

    interface Referencias {

        String ID_CABECERA_PROFESOR = String.format("REFERENCES %s(%s)", Tablas.PROFESOR, MetaDatos.CabecerasProfesor.CODPROF);

    }

    /* Constructor */
    public BaseDatosClase(Context contexto) {
        super(contexto, NOMBRE_BASE_DATOS, null, VERSION_ACTUAL);
        this.contexto = contexto;
    }

    /* Lo de las referencias de las claves foráneas solo funciona en > Android Froyo 2.2 */
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                db.setForeignKeyConstraintsEnabled(true);
            } else {
                db.execSQL("PRAGMA foreign_keys=ON");
            }
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Creacion de la tabla Profesores
        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," + "%s TEXT NOT NULL,%s DATE NOT NULL, %s INTEGER NOT NULL)",
                Tablas.PROFESOR, MetaDatos.CabecerasProfesor.CODPROF, MetaDatos.CabecerasProfesor.NOMBREPROF, MetaDatos.CabecerasProfesor.FECHANAC, MetaDatos.CabecerasProfesor.IMAGEN));

        // Creacion de la tabla Asignaturas
        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," + "%s TEXT NOT NULL,%s TEXT NOT NULL, %s INTEGER NOT NULL %s)",
                Tablas.ASIGNATURA, MetaDatos.CabecerasAsignatura.CODASIG, MetaDatos.CabecerasAsignatura.NOMASIG, MetaDatos.CabecerasAsignatura.DESCRIPCION,
                MetaDatos.CabecerasAsignatura.CODPROF, Referencias.ID_CABECERA_PROFESOR));      // Referencia de Profesor a Asignatura
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS " + Tablas.PROFESOR);
        db.execSQL("DROP TABLE IF EXISTS " + Tablas.ASIGNATURA);

        onCreate(db);
    }
}
