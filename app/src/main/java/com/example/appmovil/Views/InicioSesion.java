package com.example.appmovil.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appmovil.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class InicioSesion extends AppCompatActivity {
    EditText etMail,etPass;
    TextView tvMail, tvPass, tvBienvenido, tvFoot, tvHeader;
    Toolbar tb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_inicio_sesion);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tb = findViewById(R.id.toolbar);
        tvHeader = findViewById(R.id.tvHeader);
        etMail = findViewById(R.id.etEmail);
        etPass = findViewById(R.id.etPass);
        tvMail = findViewById(R.id.tvEmail);
        tvPass = findViewById(R.id.tvPass);
        tvBienvenido = findViewById(R.id.tvBienvenida);
        tvFoot = findViewById(R.id.tvDescripcion);
        tvHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Buscar a usuario en la API
            }
        });
    }
}