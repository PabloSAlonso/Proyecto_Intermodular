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
import com.example.appmovil.Dao.User;

public class KlyerLogin extends AppCompatActivity {

    private EditText etUsername;
    private EditText etPassword;
    private Api_Gets apiGets;
    private FrameLayout loadingOverlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        apiGets = new Api_Gets();
        loadingOverlay = findViewById(R.id.loading_overlay);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        Button btnLogin = findViewById(R.id.btnLogin);
        TextView tvRegister = findViewById(R.id.tvRegister);

        btnLogin.setOnClickListener(v -> loginUser());
        tvRegister.setOnClickListener(v -> {
            startActivity(new Intent(KlyerLogin.this, Register.class));
            finish();
        });
    }

    private void loginUser() {
        String emailOrNick = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString();

        if (emailOrNick.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Completa usuario/correo y contraseña", Toast.LENGTH_SHORT).show();
            return;
        }

        showLoading();
        apiGets.getUser(emailOrNick, password, user -> {
            runOnUiThread(() -> {
                hideLoading();
                if (user == null) {
                    Toast.makeText(KlyerLogin.this, "Credenciales inválidas", Toast.LENGTH_SHORT).show();
                    return;
                }

                UserSession session = new UserSession(KlyerLogin.this);
                session.saveUserData(
                        user.getId(),
                        user.getUsername() != null ? user.getUsername() : "",
                        user.getName() != null ? user.getName() : "",
                        user.getEmail() != null ? user.getEmail() : "",
                        user.getAvatarUrl() != null ? user.getAvatarUrl() : "",
                        user.getDescription() != null ? user.getDescription() : ""
                );

                startActivity(new Intent(KlyerLogin.this, KlyerFeed.class));
                finish();
            });
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
