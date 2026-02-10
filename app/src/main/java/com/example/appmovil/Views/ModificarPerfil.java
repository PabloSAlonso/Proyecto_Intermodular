package com.example.appmovil.Views;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appmovil.Models.Usuario;
import com.example.appmovil.R;

public class ModificarPerfil extends AppCompatActivity {
    private EditText ettNombre, ettNickname, ettPassword;
    private Toolbar toolbar;
    private ImageView imageViewPerfil;

    private Usuario usuarioOriginal;

    private Uri imagenSeleccionadaUri;

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
        toolbar = findViewById(R.id.toolbarModificarPerfil);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ettNombre = findViewById(R.id.ettNombreChange);
        ettNickname = findViewById(R.id.ettNicknamechange);
        ettPassword = findViewById(R.id.ettPassChange);
        imageViewPerfil = findViewById(R.id.imageViewPerfil);

        usuarioOriginal = (Usuario) getIntent().getSerializableExtra("usuario");

        if (usuarioOriginal != null) {
            ettNombre.setText(usuarioOriginal.getNombre());
            ettNickname.setText(usuarioOriginal.getNickname());
            ettPassword.setText(usuarioOriginal.getPassword());
        }
    }
    private void guardarCambios() {

        String nombreNuevo = ettNombre.getText().toString().trim();
        String nicknameNuevo = ettNickname.getText().toString().trim();
        String passNueva = ettPassword.getText().toString().trim();

        // Crear usuario modificado
        Usuario usuarioModificado = new Usuario(
                usuarioOriginal.getId(),
                nombreNuevo,
                usuarioOriginal.getApellidos(),
                nicknameNuevo,
                usuarioOriginal.getEmail(),
                passNueva,
                usuarioOriginal.getFoto_perfil(),
                usuarioOriginal.getFecha_nacimiento(),
                usuarioOriginal.getFecha_creacion_cuenta()
        );

        Intent resultado = new Intent();
        resultado.putExtra("usuario_modificado", usuarioModificado);
        setResult(RESULT_OK, resultado);
        finish();
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