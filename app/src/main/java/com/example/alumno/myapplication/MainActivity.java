package com.example.alumno.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.alumno.myapplication.database.OperacionesBaseDatos;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnAlta, btnSalir, btnItem, btnDatos;
    public static OperacionesBaseDatos datos;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datos = OperacionesBaseDatos.obtenerInstancia(getApplicationContext());

        // Creamos el Shared Preferences
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        editor = pref.edit();

        // Obtener el SharedPreferences
        String fecha = pref.getString("fecha", null);

        if(fecha != null)
            Toast.makeText(getApplicationContext(), "Ultimo inicio de sesion " + fecha, Toast.LENGTH_SHORT).show();

        inicializateViews();
        addListeners();
    }

    /* Inicializar vistas */
    public void inicializateViews() {

        btnAlta = (Button) findViewById(R.id.buttonAlta);
        btnItem = (Button) findViewById(R.id.buttonVer);
        btnSalir = (Button) findViewById(R.id.buttonSalir);
        btnDatos = (Button) findViewById(R.id.buttonBorrar);

    }

    /* Listeners de los botones*/
    public void addListeners() {

        btnAlta.setOnClickListener(this);
        btnItem.setOnClickListener(this);
        btnSalir.setOnClickListener(this);
        btnDatos.setOnClickListener(this);

    }

    @Override
    public void onResume() {
        super.onResume();

        Log.i("MainActivity", "Vuelta a la actividad ");

    }

    @Override
    public void onPause() {
        super.onPause();

        Log.i("MainActivity", "Actividad en pausa");

    }

    @Override
    public void onStart() {
        super.onStart();

        Log.i("MainActivity", "Actividad iniciada");

    }

    @Override
    public void onClick(View v) {

        Intent myIntent;

        switch (v.getId()) {

            case R.id.buttonAlta:
                myIntent = new Intent(getApplicationContext(), AltaProfActivity.class);
                startActivityForResult(myIntent, 10);
                break;

            case R.id.buttonVer:
                myIntent = new Intent(getApplicationContext(), VisualizarProfActivity.class);
                startActivityForResult(myIntent, 20);
                break;

            case R.id.buttonBorrar:
                myIntent = new Intent(getApplicationContext(), BorradoActivity.class);
                startActivityForResult(myIntent, 30);
                break;

            case R.id.buttonSalir:

                // Establecemos el valor de la Shared Preferences
                editor.putString("fecha", getHoraActual());
                editor.commit();

                finishAffinity();
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            switch(resultCode) {

            }
        }
    }


    public String getHoraActual() {

        String currentDateandTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return currentDateandTime;
    }
}
