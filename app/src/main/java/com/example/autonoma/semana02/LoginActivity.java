package com.example.autonoma.semana02;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    TextInputLayout tilUsuario;
    TextInputLayout tilClave;
    Button btnValidar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //
        setTitle("Pantalla de valiación");
        //
        tilUsuario = (TextInputLayout) findViewById(R.id.tilUsuario);
        tilClave = (TextInputLayout) findViewById(R.id.tilClave);
        btnValidar = (Button) findViewById(R.id.btnLogin);
        //Validar si esta vacio
        btnValidar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = tilUsuario.getEditText().getText().toString();
                String clave = tilClave.getEditText().getText().toString();
                //Toast.makeText(LoginActivity.this,usuario,Toast.LENGTH_LONG).show();
                //valir si es vacio
                if(usuario.isEmpty() || clave.isEmpty()){
                    Toast.makeText(LoginActivity.this,
                    "Debe de ingresar todos los datos",Toast.LENGTH_LONG).show();
                }else{
                    if(usuario.equals("elvis") && clave.equals("clave")){
                        Toast.makeText(LoginActivity.this,
                                "Bienvenido: " + usuario,Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(LoginActivity.this,ListActivity.class);
                        intent.putExtra("nombre","Elvis Rivera");
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this,
                                "Datos incorrectos, revisar su información",
                                Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
