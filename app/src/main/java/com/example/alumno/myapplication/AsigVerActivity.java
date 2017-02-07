package com.example.alumno.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.alumno.myapplication.adapter.AdaptadorLista;
import com.example.alumno.myapplication.entity.Asignatura;
import com.example.alumno.myapplication.entity.Profesor;

import java.util.ArrayList;

public class AsigVerActivity extends AppCompatActivity {


    private ListView myListView;
     ArrayList<Asignatura> lista = new ArrayList<>();
    int flag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asigver);

        inicializeViews();

        Bundle b = getIntent().getExtras();
        flag = b.getInt("borrado");
        boton();


    }

    public void inicializeViews() {

        myListView = (ListView) findViewById(R.id.myListView);

        Cursor cursor = MainActivity.datos.obtenerAsignaturasPorProfesor(getIntent().getIntExtra("codigo", 0));


        // Cargamos los elementos del cursor en un array
        while (cursor.moveToNext()) {

            Asignatura aux = new Asignatura(cursor.getString(1), cursor.getString(2), cursor.getInt(3));
            aux.setCodAsig(cursor.getInt(0));
            lista.add(aux);
        }

        cursor.close();
        this.myListView.setAdapter(new AdaptadorLista(this, lista));

    }

    public void boton(){

        if(flag==1) {
                    myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                            int codasig = lista.get(position).getCodAsig();

                            MainActivity.datos.eliminarAsignatura(codasig, true);
                            Intent myintent = new Intent(getApplicationContext(), AsigVerActivity.class);
                            myintent.putExtra("borrado",1);
                            myintent.putExtra("codigo",getIntent().getIntExtra("codigo", 0));
                            startActivityForResult(myintent, 31);
                            finish();

                        }
                    });


                }
    }

}
