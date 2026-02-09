package com.example.appmovil.Views;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appmovil.API.ApiRest;
import com.example.appmovil.R;


public class RegistroCuenta extends AppCompatActivity {
    private EditText ettNom, ettNick;
    private Button btnRegistro;
    private ApiRest api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro_cuenta);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        api = new ApiRest();

        ettNick = findViewById(R.id.ettNick);
        ettNom = findViewById(R.id.ettNom);
        btnRegistro = findViewById(R.id.btnRegistrarse);

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = ettNom.getText().toString();

                String nick = ettNick.getText().toString();

            }
        });
    }
}