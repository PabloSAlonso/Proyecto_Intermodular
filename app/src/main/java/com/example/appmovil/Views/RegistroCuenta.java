package com.example.appmovil.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.appmovil.API.ApiRest;
import com.example.appmovil.R;

import java.sql.Date;

public class RegistroCuenta extends AppCompatActivity {

    private EditText ettNombre, ettApellidos, ettNick, ettEmail, ettPass;
    private Button btnRegistrarse;
    private TextView tvLoginLink;
    private Toolbar toolbar;
    private ProgressBar progressBar;
    private ApiRest api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_cuenta);

        toolbar = findViewById(R.id.toolbarRegistro);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        ettNombre = findViewById(R.id.ettNom);
        ettApellidos = findViewById(R.id.ettApellidos);
        ettNick = findViewById(R.id.ettNick);
        ettEmail = findViewById(R.id.ettRMail);
        ettPass = findViewById(R.id.ettRPass);
        btnRegistrarse = findViewById(R.id.btnRegistrarse);
        tvLoginLink = findViewById(R.id.tvLoginLink);
        progressBar = findViewById(R.id.progressBar);
        
        api = new ApiRest();

        // Login link click
        tvLoginLink.setOnClickListener(v -> {
            finish();
        });

        btnRegistrarse.setOnClickListener(v -> {
            registrarUsuario();
        });
    }

    private void registrarUsuario() {
        // Get input values
        String nombre = ettNombre.getText().toString().trim();
        String apellidos = ettApellidos.getText().toString().trim();
        String nickname = ettNick.getText().toString().trim();
        String email = ettEmail.getText().toString().trim();
        String password = ettPass.getText().toString().trim();

        // Validate inputs
        if (nombre.isEmpty() || apellidos.isEmpty() || nickname.isEmpty() || 
            email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validate email format
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Por favor ingresa un correo electrónico válido", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validate nickname length
        if (nickname.length() < 3) {
            Toast.makeText(this, "El nombre de usuario debe tener al menos 3 caracteres", Toast.LENGTH_SHORT).show();
            return;
        }

        // Show loading
        progressBar.setVisibility(View.VISIBLE);
        btnRegistrarse.setEnabled(false);

        // Call API to register user
        api.registrarUsuario(nombre, apellidos, nickname, email, password, 
            new ApiRest.RegisterCallback() {
                @Override
                public void onRegisterResult(boolean success, String errorMessage) {
                    runOnUiThread(() -> {
                        progressBar.setVisibility(View.GONE);
                        btnRegistrarse.setEnabled(true);

                        if (success) {
                            Toast.makeText(RegistroCuenta.this, 
                                    "¡Registro exitoso! Ahora puedes iniciar sesión.", 
                                    Toast.LENGTH_SHORT).show();
                            
                            // Return to login screen
                            setResult(RESULT_OK);
                            finish();
                        } else {
                            Toast.makeText(RegistroCuenta.this, 
                                    errorMessage != null ? errorMessage : "Error al registrar usuario. El correo o nickname ya pueden estar en uso.", 
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                }
            });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            setResult(RESULT_CANCELED);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

