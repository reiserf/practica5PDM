package com.example.alumno.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import static com.example.alumno.myapplication.MainActivity.datos;

public class VisualizarProfActivity extends AppCompatActivity implements View.OnClickListener {

    private Button Ant, Sig, btnalta, btnlistar;
    private TextView nombre, code, fecha;
    private ImageView idfoto;
    Cursor cursor = datos.obtenerProfesores();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visualizarprof);

        initViews();

        cursor.moveToFirst();   // Movemos el cursor a la primera posicion

        addListeners();
        listarProfesores();

    }

    public void initViews() {

        Ant = (Button) findViewById(R.id.Ant);
        Sig = (Button) findViewById(R.id.Sig);
        btnalta = (Button) findViewById(R.id.btnlistarH);
        btnlistar = (Button) findViewById(R.id.btninsertar);
        code = (TextView) findViewById(R.id.listcodigo);
        nombre = (TextView) findViewById(R.id.listnombre);
        fecha = (TextView) findViewById(R.id.listfecha);
        idfoto = (ImageView) findViewById(R.id.id_foto);

    }

    public void listarProfesores() {

        // Obtenemos los datos del cursor actual y los pintamos en los TextView
        try {
            int codeProf = cursor.getInt(0);
            String nomProf = cursor.getString(1);
            String dateProf = cursor.getString(2);

            code.setText(String.valueOf(codeProf));
            nombre.setText(nomProf);
            fecha.setText(dateProf);
            idfoto.setImageResource(AltaProfActivity.fotos[cursor.getInt(3)]);

        } catch (CursorIndexOutOfBoundsException e) {
            Toast.makeText(getApplicationContext(), "Out of data", Toast.LENGTH_SHORT).show();
        }

    }


    public void addListeners() {
        Sig.setOnClickListener(this);
        Ant.setOnClickListener(this);
        btnalta.setOnClickListener(this);
        btnlistar.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        Intent itent;

        switch (v.getId()) {
            // Button Anterior
            case R.id.Ant:

                if (cursor.getPosition() > 0) {
                    cursor.moveToPrevious();
                    listarProfesores();
                }

                break;

            // Button Siguiente
            case R.id.Sig:

                if (cursor.getPosition() < cursor.getCount() - 1) {
                    cursor.moveToNext();
                    listarProfesores();
                }

                break;

            // Button insertar asignatura
            case R.id.btninsertar:

                try {

                    itent = new Intent(VisualizarProfActivity.this, AltaAsigActivity.class);
                    int codigoprof = cursor.getInt(0);
                    itent.putExtra("codprofesor", codigoprof);
                    startActivityForResult(itent, 12);

                } catch (CursorIndexOutOfBoundsException e) {

                }

                break;


            // Boton listar asignaturas
            case R.id.btnlistarH:

                try {
                    itent = new Intent(VisualizarProfActivity.this, AsigVerActivity.class);
                    int codigoprof = cursor.getInt(0);
                    itent.putExtra("codigo", codigoprof);
                    itent.putExtra("borrado",0);
                    startActivityForResult(itent, 14);
                }catch (CursorIndexOutOfBoundsException e) {

                }

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            listarProfesores();
        }
    }
}
