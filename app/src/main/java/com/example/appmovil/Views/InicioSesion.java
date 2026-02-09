package com.example.appmovil.Views;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appmovil.API.ApiRest;
import com.example.appmovil.Models.Usuario;
import com.example.appmovil.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class InicioSesion extends AppCompatActivity {
    private EditText etMail,etPass;
    private TextView tvMail, tvPass, tvBienvenido, tvFoot, tvHeader;
    private Toolbar tb;
    private Button btnInicio;
    private ApiRest api;
    private ActivityResultLauncher<Intent> registroLauncher;

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
        api = new ApiRest();
        btnInicio = findViewById(R.id.btnInicio);
        tb = findViewById(R.id.toolbar);
        tvHeader = findViewById(R.id.tvHeader);
        etMail = findViewById(R.id.etEmail);
        etPass = findViewById(R.id.etPass);
        tvMail = findViewById(R.id.tvEmail);
        tvPass = findViewById(R.id.tvPass);
        tvBienvenido = findViewById(R.id.tvBienvenida);
        tvFoot = findViewById(R.id.tvDescripcion);
        btnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                api.obtenerDatosUsuario(
                        etMail.getText().toString(),
                        etPass.getText().toString(),
                        success -> {
                            if (success) {

                                Intent iniciarSesion = new Intent(InicioSesion.this, PaginaInicio.class);
                            } else {
                                new AlertDialog.Builder(InicioSesion.this)
                                        .setTitle("Error")
                                        .setMessage("Correo o contraseña inválidos")
                                        .setCancelable(false)
                                        .setPositiveButton("Aceptar", (dialog, which) -> dialog.dismiss())
                                        .show();
                            }
                        }
                );
            }
        });

        registroLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {

                        Usuario usuario = (Usuario)
                                result.getData().getSerializableExtra("usuario_registrado");

                        // Guardar en BD / API / sesión
                    }
                }
        );

        tvHeader.setOnClickListener(v -> {
            Intent intent = new Intent(InicioSesion.this, RegistroCuenta.class);
            registroLauncher.launch(intent);
        });
    }

}