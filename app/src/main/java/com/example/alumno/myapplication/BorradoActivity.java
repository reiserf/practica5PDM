package com.example.alumno.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.alumno.myapplication.adapter.AdaptadorGridView;
import com.example.alumno.myapplication.entity.Profesor;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BorradoActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrado);

        GridView gv = (GridView) findViewById(R.id.gridview_borrado);


        Cursor cursor = MainActivity.datos.obtenerProfesores();
        final ArrayList<Profesor> lista=new ArrayList<>();

        while(cursor.moveToNext()){

            Profesor aux=new Profesor(cursor.getString(1), stringToDate(cursor.getString(2)), cursor.getInt(3));
            aux.setCodProf(cursor.getInt(0));       // Guardo el codigo del profesor tambien
            lista.add(aux);
        }

        cursor.close();


        // Adapter GridView
        gv.setAdapter(new AdaptadorGridView(this,lista));

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Profesor nuevo=lista.get(position);

                Intent myintent = new Intent(getApplicationContext(), ItemBorradoActivity.class);

                String fecha=dateToString(nuevo.getFecha_nac());
                myintent.putExtra("imagen",nuevo.getImagen());
                myintent.putExtra("nombre",nuevo.getNomProf());
                myintent.putExtra("codprof", nuevo.getCodProf());
                myintent.putExtra("fecha",fecha);

                startActivityForResult(myintent, 30);
                finish();
            }
        });

    }


    /**
     * Metodos auxiliares de conversion de fechas
     */
    public String dateToString(Date d) {

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String reportDate = df.format(d);

        return reportDate;
    }

    public Date stringToDate(String fecha) {

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;

        try {

            date = formatter.parse(fecha);
            System.out.println(date);
            System.out.println(formatter.format(date));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;

    }
}
