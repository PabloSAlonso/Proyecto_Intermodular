package com.example.appmovil.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appmovil.R;

public class InicioApp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        Button btnIrAInicioSesion = findViewById(R.id.btnIrAInicioSesion);
        Button btnIrARegistro = findViewById(R.id.btnIrARegistro);

        btnIrAInicioSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InicioApp.this, InicioSesion.class);
                startActivity(intent);
            }
        });

        btnIrARegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InicioApp.this, RegistroCuenta.class);
                startActivity(intent);
            }
        });
    }
}
