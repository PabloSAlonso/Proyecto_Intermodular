package com.example.appmovil.Views;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

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

public class InicioSesion extends AppCompatActivity {
    private EditText etMail, etPass;
    private TextView tvBienvenido, tvDescripcion, tvHeader;
    private Toolbar tb;
    private Button btnInicio;
    private ProgressBar progressBar;
    private ApiRest api;

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
        
        // Check if user is already logged in
        if (ApiRest.isLoggedIn(this)) {
            goToMainPage();
            return;
        }
        
        btnInicio = findViewById(R.id.btnInicio);
        tb = findViewById(R.id.toolbar);
        tvHeader = findViewById(R.id.tvHeader);
        etMail = findViewById(R.id.etEmail);
        etPass = findViewById(R.id.etPass);
        tvBienvenido = findViewById(R.id.tvBienvenida);
        tvDescripcion = findViewById(R.id.tvDescripcion);
        progressBar = findViewById(R.id.progressBar);
        
        btnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etMail.getText().toString().trim();
                String password = etPass.getText().toString().trim();
                
                // Validate inputs
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(InicioSesion.this, 
                            "Por favor completa todos los campos", 
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                
                // Show loading
                progressBar.setVisibility(View.VISIBLE);
                btnInicio.setEnabled(false);
                
                api.loginUsuario(email, password, new ApiRest.LoginCallback() {
                    @Override
                    public void onLoginResult(boolean success, Usuario usuario, String errorMessage) {
                        runOnUiThread(() -> {
                            progressBar.setVisibility(View.GONE);
                            btnInicio.setEnabled(true);
                            
                            if (success && usuario != null) {
                                // Save user session
                                ApiRest.saveUserSession(InicioSesion.this, usuario);
                                Toast.makeText(InicioSesion.this, 
                                        "Bienvenido, " + usuario.getNombre() + "!", 
                                        Toast.LENGTH_SHORT).show();
                                goToMainPage();
                            } else {
                                new AlertDialog.Builder(InicioSesion.this)
                                        .setTitle("Error")
                                        .setMessage(errorMessage != null ? errorMessage : "Correo o contraseña inválidos")
                                        .setCancelable(false)
                                        .setPositiveButton("Aceptar", (dialog, which) -> dialog.dismiss())
                                        .show();
                            }
                        });
                    }
                });
            }
        });

        ActivityResultLauncher<Intent> registroLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Usuario usuario = (Usuario) result.getData().getSerializableExtra("usuario_registrado");
                        if (usuario != null) {
                            // Auto-login after registration or redirect to login
                            Toast.makeText(InicioSesion.this, 
                                    "Registro exitoso. Inicia sesión.", 
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        tvHeader.setOnClickListener(v -> {
            Intent intent = new Intent(InicioSesion.this, RegistroCuenta.class);
            registroLauncher.launch(intent);
        });
    }
    
    private void goToMainPage() {
        Intent iniciarSesion = new Intent(InicioSesion.this, PaginaInicio.class);
        iniciarSesion.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(iniciarSesion);
        finish();
    }
    
    // Static methods for session management
    public static int getUserId(Context context) {
        return ApiRest.getUserId(context);
    }
    
    public static String getUserNickname(Context context) {
        return ApiRest.getUserNickname(context);
    }
    
    public static void logout(Context context) {
        ApiRest.logout(context);
    }
}

