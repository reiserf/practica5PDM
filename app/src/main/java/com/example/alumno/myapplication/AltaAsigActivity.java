package com.example.alumno.myapplication;

import android.content.Intent;

import android.database.DatabaseUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.alumno.myapplication.entity.Asignatura;
import com.example.alumno.myapplication.entity.Profesor;

import static com.example.alumno.myapplication.MainActivity.datos;


public class AltaAsigActivity extends AppCompatActivity implements View.OnClickListener {


    Button btnAceptar, btnCancelar;
    EditText etNombre, etDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_asig);

        btnAceptar = (Button) findViewById(R.id.btn_aceptar);
        btnCancelar = (Button) findViewById(R.id.btn_cancelar);
        etNombre = (EditText) findViewById(R.id.etNombre);
        etDescripcion = (EditText) findViewById(R.id.etDescripcion);

        btnAceptar.setOnClickListener(this);
        btnCancelar.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        Intent myIntent;
        switch (v.getId()) {

            case R.id.btn_aceptar:


                if (!etNombre.getText().toString().equals("") && !etDescripcion.getText().toString().equals("")) {

                    // Insercion asignatura
                    try {
                        datos.getDb().beginTransaction();       // Empieza la transaccion
                        datos.insertarAsignatura(new Asignatura(etNombre.getText().toString(),etDescripcion.getText().toString(), getIntent().getIntExtra("codprofesor", 0)));
                        datos.getDb().setTransactionSuccessful();   // Se indica que se ha hecho correctamente

                    } finally {
                        datos.getDb().endTransaction();     // Final de la transaccion
                    }

                    // Mostrar Asignaturas
                    Log.d("Asignaturas", "Asignaturas");
                    DatabaseUtils.dumpCursor(datos.obtenerAsignaturas());
                }


                myIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivityForResult(myIntent, 30);
                finish();
                break;


            case R.id.btn_cancelar:
                myIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivityForResult(myIntent, 30);
                finish();
                break;
        }
    }
}
