package com.example.alumno.myapplication;

import android.database.DatabaseUtils;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.example.alumno.myapplication.entity.Profesor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.alumno.myapplication.MainActivity.datos;

public class AltaProfActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnAceptar, btnCancelar, sig, ant;
    private EditText etNombre;
    private DatePicker datePicker;
    private ImageSwitcher imageSwitcher;
    public static int fotos[] = {R.drawable.hombre1, R.drawable.mujer1, R.drawable.hombre2, R.drawable.mujer2};
    private int contador = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta);

        inicializateViews();
        addListeners();
    }

    public void inicializateViews() {

        btnAceptar = (Button) findViewById(R.id.btn_aceptar);
        btnCancelar = (Button) findViewById(R.id.btn_cancelar);
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        imageSwitcher = (ImageSwitcher) findViewById(R.id.imgswitch);

        sig = (Button) findViewById(R.id.sig);
        ant = (Button) findViewById(R.id.ant);

        // Edit Texts
        etNombre = (EditText) findViewById(R.id.etNombre);

        // Image Switcher para elegir las fotos
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {

            public View makeView() {
                ImageView myView = new ImageView(getApplicationContext());
                myView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                myView.setLayoutParams(new
                        ImageSwitcher.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT,
                        ActionBar.LayoutParams.WRAP_CONTENT));
                return myView;
            }
        });

        imageSwitcher.setImageResource(fotos[contador]);
    }


    public void addListeners() {

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!etNombre.getText().toString().equals("")) {

                    // Insercion profesor
                    try {
                        datos.getDb().beginTransaction();       // Empieza la transaccion

                        // Pillar la fecha del datePicker
                        int day = datePicker.getDayOfMonth();
                        int month = datePicker.getMonth() + 1;
                        int year = datePicker.getYear();
                        String fecha = String.valueOf(day) + "/" + String.valueOf(month) + "/" + String.valueOf(year);

                        // Llamar al metodo InsertarProfesor y poner la transaccion como succesfully
                        datos.insertarProfesor(new Profesor(etNombre.getText().toString(), dateToString(fecha), contador));
                        datos.getDb().setTransactionSuccessful();   // Se indica que se ha hecho correctamente

                    } finally {
                        datos.getDb().endTransaction();     // Final de la transaccion
                    }

                    // Mostrar profesores
                    Log.d("Profesores", "Profesores");
                    DatabaseUtils.dumpCursor(datos.obtenerProfesores());

                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.avisonuevo), Toast.LENGTH_SHORT).show();
                    finish();

                } else {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.avisoNoDatos), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancelar.setOnClickListener(this);   // interfaz OnClickListener


        // Botones anteriores y siguiente

        sig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (contador < 3)
                    contador++;

                imageSwitcher.setImageResource(fotos[contador]);

            }
        });

        ant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (contador > 0)
                    contador--;
                imageSwitcher.setImageResource(fotos[contador]);
            }
        });


    }

    public Date dateToString(String fecha) {

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

    @Override
    public void onClick(View v) {
        finish();
    }
}

