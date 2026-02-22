package com.example.appmovil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appmovil.ApiRest.Api_Gets;
import com.example.appmovil.ApiRest.Api_Inserts;

public class Register extends AppCompatActivity {
    private EditText etName;
    private EditText etUsername;
    private EditText etEmail;
    private EditText etPassword;
    private EditText etConfirmPassword;
    private Api_Inserts apiInserts;
    private Api_Gets apiGets;
    private FrameLayout loadingOverlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        apiInserts = new Api_Inserts();
        apiGets = new Api_Gets();
        loadingOverlay = findViewById(R.id.loading_overlay);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etName = findViewById(R.id.etName);
        etUsername = findViewById(R.id.etUsername);
        etEmail = findViewById(R.id.etUserName);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        Button btnRegister = findViewById(R.id.btnRegister);
        TextView tvLogin = findViewById(R.id.tvLogin);

        btnRegister.setOnClickListener(v -> registerUser());
        tvLogin.setOnClickListener(v -> {
            startActivity(new Intent(Register.this, KlyerLogin.class));
            finish();
        });
    }

    private void registerUser() {
        String name = etName.getText().toString().trim();
        String username = etUsername.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString();
        String confirmPassword = etConfirmPassword.getText().toString();

        if (name.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Correo inválido", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.length() < 6) {
            Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            return;
        }

        showLoading();
        apiInserts.insertUsuario(name, username, email, password, success -> {
            if (!success) {
                runOnUiThread(() -> {
                    hideLoading();
                    Toast.makeText(Register.this, "No se pudo crear la cuenta", Toast.LENGTH_SHORT).show();
                });
                return;
            }

            apiGets.getUser(email, password, (loginSuccess, userId) -> runOnUiThread(() -> {
                hideLoading();
                if (!loginSuccess || userId <= 0) {
                    Toast.makeText(Register.this, "Cuenta creada. Inicia sesión.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Register.this, KlyerLogin.class));
                    finish();
                    return;
                }

                UserSession session = new UserSession(Register.this);
                session.saveUserData(userId, username, name, email, "", "");
                Toast.makeText(Register.this, "Cuenta creada", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Register.this, KlyerCompleteProfile.class));
                finish();
            }));
        });
    }

    private void showLoading() {
        if (loadingOverlay != null) {
            loadingOverlay.setVisibility(View.VISIBLE);
        }
    }

    private void hideLoading() {
        if (loadingOverlay != null) {
            loadingOverlay.setVisibility(View.GONE);
        }
    }
}
