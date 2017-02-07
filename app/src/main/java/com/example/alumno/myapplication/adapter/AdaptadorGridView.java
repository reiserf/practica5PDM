package com.example.alumno.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alumno.myapplication.AltaProfActivity;
import com.example.alumno.myapplication.R;
import com.example.alumno.myapplication.entity.Asignatura;
import com.example.alumno.myapplication.entity.Profesor;

import java.util.List;

/**
 * Created by jose on 07/02/2017.
 */

public class AdaptadorGridView extends BaseAdapter {

    private Context context;
    private List<Profesor> items;

    public AdaptadorGridView(Context context, List<Profesor> items) {
        this.context = context;
        this.items = items;
    }


    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View gridView = convertView;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            gridView = inflater.inflate(R.layout.row_borradohijo, parent, false);
        }

       Profesor p = this.items.get(position);

        // set value into textview
        TextView textView = (TextView) gridView.findViewById(R.id.textViewProfBorrado);
        textView.setText(p.getNomProf());

        // set image based on selected text
        ImageView imageView = (ImageView) gridView.findViewById(R.id.imageViewProfBorrado);
        imageView.setImageResource(AltaProfActivity.fotos[p.getImagen()]);

        return gridView;

    }
}
