package com.example.autonoma.semana02;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TabHost;
import android.widget.Toast;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    public static final int REQUEST_PHONE_CALL=1;
    ListView lvListado;
    ArrayList alumnos;
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        //creado contenedor
        Bundle bundle = getIntent().getExtras();
        //muestro nombre
        setTitle("Listado Usuarios - " + bundle.get("nombre"));
        //
        lvListado = (ListView) findViewById(R.id.lvListado);
        //Datos
        alumnos = new ArrayList<String>();
        alumnos.add("Elvis");
        alumnos.add("Jose Luis");
        alumnos.add("Sixto");
        alumnos.add("Pretell");

        final ArrayList telefonos = new ArrayList<String>();
        telefonos.add("999888777");
        telefonos.add("987321654");
        telefonos.add("963951987");
        telefonos.add("953957862");

        //adaptador Contexto , item Layout, Datos
        adapter = new ArrayAdapter(
                this,
                R.layout.support_simple_spinner_dropdown_item,
                alumnos);
        //asignamos adaptador al listado
        lvListado.setAdapter(adapter);
        registerForContextMenu(lvListado);
        //Click en Listado
        lvListado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view,
                                    int position, long id) {

            Toast.makeText(ListActivity.this,
                        alumnos.get(position).toString(),
                        Toast.LENGTH_LONG).show();
                //public static final int REQUEST_PHONE_CALL=1;
                String numero = telefonos.get(position).toString();
                Intent intentCall = new Intent(Intent.ACTION_CALL,
                        Uri.parse("tel:" + numero));

                if (ActivityCompat.checkSelfPermission(
                        ListActivity.this,
                        Manifest.permission.CALL_PHONE) !=
                        PackageManager.PERMISSION_GRANTED) {

                    //pedir permiso
                    ActivityCompat.requestPermissions(
                            ListActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE},
                            REQUEST_PHONE_CALL);
                }else{
                    //Tienes permiso - ejecuta llamada
                    startActivity(intentCall);
                }
            }
        });//fin Click en Listado

    }//fin create

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu,menu);
        return true;
    }
    //
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.add_item:
                //
                Toast.makeText(
                        ListActivity.this,
                        "Agregando nuevo item",
                        Toast.LENGTH_SHORT).show();
                alumnos.add("Nombre Nuevo");
                adapter.notifyDataSetChanged();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    //
    @Override
    public void onCreateContextMenu(
            //crea el menu dentro del activity
            ContextMenu menu,View v,
            ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,v,menuInfo);
        MenuInflater inflater = getMenuInflater();
        
        AdapterView.AdapterContextMenuInfo info
                = (AdapterView.AdapterContextMenuInfo) menuInfo;

        menu.setHeaderTitle((String) this.alumnos.get(info.position));
        inflater.inflate(R.menu.context_menu,menu);
    }
    //Evento click en el contecto de listado
    @Override
    public boolean onContextItemSelected(MenuItem item){
        //detecta el click en algun item dentro del menu contextual
        AdapterView.AdapterContextMenuInfo info
                = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        //
        switch(item.getItemId()){
            case R.id.delete_item:
                Toast.makeText(
                        ListActivity.this,
                        "Eliminando item",
                        Toast.LENGTH_SHORT).show();
                alumnos.remove(info.position);
                adapter.notifyDataSetChanged();

                return  true;
            default:
                return super.onContextItemSelected(item);
        }

    }
}

