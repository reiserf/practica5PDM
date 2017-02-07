package com.example.alumno.myapplication.database;


public class MetaDatos {

    interface ColumnasProfesor {
        String CODPROF = "codprof";
        String NOMBREPROF = "nombreprof";
        String FECHANAC = "fechanac";
        String IMAGEN = "imagenprof";
    }

    public static class CabecerasProfesor implements ColumnasProfesor {

    }

    interface ColumnasAsignatura{
        String CODASIG = "codasig";
        String NOMASIG = "nomasig";
        String DESCRIPCION = "descripcion";
        String CODPROF = "codprof";
    }


    public static class CabecerasAsignatura implements ColumnasAsignatura {

    }

    private MetaDatos() {

    }
}
