package com.example.alumno.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.alumno.myapplication.R;
import com.example.alumno.myapplication.entity.Asignatura;

import java.util.List;

/**
 * Created by alumno on 16/01/17.
 */
public class AdaptadorLista extends BaseAdapter {

    private Context context;
    private List<Asignatura> items;

    public AdaptadorLista(Context context, List<Asignatura> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return this.items.size();
    }

    @Override
    public Object getItem(int position) {
        return this.items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = convertView;

        if (convertView == null) {
            // Create a new view into the list.
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.rowhijov_layout, parent, false);
        }


        TextView tvnombreasig = (TextView) rowView.findViewById(R.id.nombAsig);
        TextView tvdescasig = (TextView) rowView.findViewById(R.id.descAsig);


        Asignatura item = this.items.get(position);
        tvnombreasig.setText(item.getNombAsig());
        tvdescasig.setText(String.valueOf(item.getDescripcion()));

        return rowView;
    }

}
