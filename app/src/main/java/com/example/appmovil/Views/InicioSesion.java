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
    RatingBar rb;
    Toolbar tb;
    BottomNavigationView bnv;
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
        tvHeader = findViewById(R.id.tvHeader);


        tvHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent recoger_nuevo_user = new Intent(InicioSesion.this, RegistroCuenta.class);
                startActivity(recoger_nuevo_user);
            }
        });
    }
}