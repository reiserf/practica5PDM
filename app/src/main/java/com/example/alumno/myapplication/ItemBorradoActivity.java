package com.example.alumno.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alumno.myapplication.database.OperacionesBaseDatos;

import org.w3c.dom.Text;

import java.util.Date;

public class ItemBorradoActivity extends AppCompatActivity implements View.OnClickListener {


    Button btnverh, btnborrar;
    TextView Tnombre, Tcodigo, Tfecha;
    ImageView Ifoto;
    int codporf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_borrado);

        initViews();

        Bundle b = getIntent().getExtras();
        String nomb = b.getString("nombre");
        int foto = b.getInt("imagen");
        codporf = b.getInt("codprof");
        String fecha = b.getString("fecha");

        Tnombre.setText(nomb);
        Tcodigo.setText(String.valueOf(codporf));
        Tfecha.setText(fecha);
        Ifoto.setImageResource(AltaProfActivity.fotos[foto]);


        btnverh.setOnClickListener(this);
        btnborrar.setOnClickListener(this);
    }

    public void initViews() {
        Tnombre = (TextView) findViewById(R.id.listnombre);
        Tcodigo = (TextView) findViewById(R.id.listcodigo);
        Tfecha = (TextView) findViewById(R.id.listfecha);
        Ifoto = (ImageView) findViewById(R.id.id_foto);
        btnverh = (Button) findViewById(R.id.btnlistarH);
        btnborrar = (Button) findViewById(R.id.btnborrarh);
    }

    @Override
    public void onClick(View v) {

        Intent myIntent;

        switch (v.getId()) {

            case R.id.btnlistarH:

                myIntent = new Intent(getApplicationContext(), AsigVerActivity.class);
                myIntent.putExtra("codigo", codporf);
                myIntent.putExtra("borrado",1);
                startActivityForResult(myIntent, 30);
                break;

            case R.id.btnborrarh:
                MainActivity.datos.eliminarProfesor(codporf);
                myIntent = new Intent(getApplicationContext(), BorradoActivity.class);
                startActivityForResult(myIntent, 31);
                finish();
                break;
        }

    }
}
