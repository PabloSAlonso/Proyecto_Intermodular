package com.example.appmovil.Views;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appmovil.API.ApiRest;
import com.example.appmovil.Models.Usuario;
import com.example.appmovil.R;

public class ModificarPerfil extends AppCompatActivity {
    private EditText ettNombre, ettApellidos, ettNickname, ettEmail;
    private Toolbar toolbar;
    private ImageView imageViewPerfil;
    private ProgressBar progressBar;

    private Usuario usuarioOriginal;
    private Uri imagenSeleccionadaUri;
    private ApiRest api;

    private ActivityResultLauncher<Intent> galeriaLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                            imagenSeleccionadaUri = result.getData().getData();
                            imageViewPerfil.setImageURI(imagenSeleccionadaUri);
                        }
                    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_modificar_perfil);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        
        api = new ApiRest();
        
        toolbar = findViewById(R.id.toolbarModificarPerfil);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Editar Perfil");

        ettNombre = findViewById(R.id.ettNombreChange);
        ettApellidos = findViewById(R.id.ettApellidosChange);
        ettNickname = findViewById(R.id.ettNicknamechange);
        ettEmail = findViewById(R.id.ettEmailChange);
        imageViewPerfil = findViewById(R.id.imageViewPerfil);
        progressBar = findViewById(R.id.progressBar);

        // Get user from intent - support both key names
        usuarioOriginal = (Usuario) getIntent().getSerializableExtra("usuario_a_modificar");
        if (usuarioOriginal == null) {
            usuarioOriginal = (Usuario) getIntent().getSerializableExtra("usuario");
        }
        
        // If still null, try to get from session
        if (usuarioOriginal == null) {
            usuarioOriginal = ApiRest.getLoggedUser(this);
        }

        if (usuarioOriginal != null) {
            ettNombre.setText(usuarioOriginal.getNombre());
            ettApellidos.setText(usuarioOriginal.getApellidos());
            ettNickname.setText(usuarioOriginal.getNickname());
            ettEmail.setText(usuarioOriginal.getEmail());
            ettEmail.setEnabled(false); // Email cannot be changed
        } else {
            Toast.makeText(this, "Error al cargar usuario", Toast.LENGTH_SHORT).show();
            finish();
        }
        
        // Set up image selection
        imageViewPerfil.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            galeriaLauncher.launch(intent);
        });
    }

    private void guardarCambios() {
        if (usuarioOriginal == null) {
            Toast.makeText(this, "Error: usuario no encontrado", Toast.LENGTH_SHORT).show();
            return;
        }

        String nombreNuevo = ettNombre.getText().toString().trim();
        String apellidosNuevo = ettApellidos.getText().toString().trim();
        String nicknameNuevo = ettNickname.getText().toString().trim();

        // Validate inputs
        if (nombreNuevo.isEmpty() || nicknameNuevo.isEmpty()) {
            Toast.makeText(this, "Por favor completa los campos obligatorios", Toast.LENGTH_SHORT).show();
            return;
        }

        // Show loading
        progressBar.setVisibility(View.VISIBLE);

        // Create modified user object
        Usuario usuarioModificado = new Usuario(
                usuarioOriginal.getId(),
                nombreNuevo,
                apellidosNuevo,
                nicknameNuevo,
                usuarioOriginal.getEmail(),
                usuarioOriginal.getPassword(),
                usuarioOriginal.getFoto_perfil(),
                usuarioOriginal.getFecha_nacimiento(),
                usuarioOriginal.getFecha_creacion_cuenta()
        );

        // Update session with new data
        ApiRest.saveUserSession(this, usuarioModificado);
        
        // Return result
        Intent resultado = new Intent();
        resultado.putExtra("usuario_modificado", usuarioModificado);
        setResult(RESULT_OK, resultado);
        
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this, "Perfil actualizado correctamente", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu_guardar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.opcion_guardar) {
            guardarCambios();
            return true;
        } else if (item.getItemId() == android.R.id.home){
            setResult(RESULT_CANCELED);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

