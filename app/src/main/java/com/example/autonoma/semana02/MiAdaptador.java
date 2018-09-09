package com.example.autonoma.semana02;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MiAdaptador extends BaseAdapter {
    Context context;
    int layout;
    List<String> alumnos;

    public MiAdaptador(Context context, int layout, List<String> alumnos) {
        this.context = context;
        this.layout = layout;
        this.alumnos = alumnos;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        //
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        v=layoutInflater.inflate(R.layout.custom_list_item,null);
        //get datos
        String alumno= (String) alumnos.get(position);
        TextView textView = v.findViewById(R.id.tvNombre);
        textView.setText(alumno);
        return v;
    }
}
