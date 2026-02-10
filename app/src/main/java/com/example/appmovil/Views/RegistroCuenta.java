package com.example.appmovil.Views;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.appmovil.API.ApiRest;
import com.example.appmovil.Models.Usuario;
import com.example.appmovil.R;

import java.sql.Date;

public class RegistroCuenta extends AppCompatActivity {

    private EditText ettNombre, ettNick, ettEmail, ettPass, ettFecha;
    private Button btnRegistrarse;
    private Toolbar toolbar;
    private Usuario nuevo_usuario;
    ApiRest api = new ApiRest();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_cuenta);

        toolbar = findViewById(R.id.toolbarRegistro);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ettNombre = findViewById(R.id.ettNom);
        ettNick = findViewById(R.id.ettNick);
        ettEmail = findViewById(R.id.ettRMail);
        ettPass = findViewById(R.id.ettRPass);
        ettFecha = findViewById(R.id.editTextText);
        btnRegistrarse = findViewById(R.id.btnRegistrarse);

        btnRegistrarse.setOnClickListener(v -> {
            String[] nombre_apellidos = ettNombre.getText().toString().trim().split(" ");
            String nombre = nombre_apellidos[0];
            String apellidos = nombre_apellidos[1] + " " + nombre_apellidos[2];
            String nickname = ettNick.getText().toString().trim();
            String email = ettEmail.getText().toString().trim();
            String password = ettPass.getText().toString().trim();
            String fechaTexto = ettFecha.getText().toString().trim();

            Date fechaNacimiento = Date.valueOf(convertirFecha(fechaTexto));
            Date fechaCreacion = new Date(System.currentTimeMillis());

            api.insertarUsuario(
                    nombre,
                    apellidos,
                    nickname,
                    email,
                    password,
                    null,
                    fechaNacimiento,
                    fechaCreacion
            );
            finish(); // cerrar registro
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

    private String convertirFecha(String fecha) {
        String[] partes = fecha.split("/");
        return partes[2] + "-" + partes[1] + "-" + partes[0];
    }
}
