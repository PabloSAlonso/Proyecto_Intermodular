package com.example.appmovil.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appmovil.Models.Usuario;
import com.example.appmovil.R;

import java.sql.Blob;
import java.sql.Date;

public class RegistroCuenta extends AppCompatActivity {

    private EditText ettNombre, ettNick, ettEmail, ettPass, ettFecha;
    private Button btnRegistrarse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_cuenta);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ettNombre = findViewById(R.id.ettNom);
        ettNick = findViewById(R.id.ettNick);
        ettEmail = findViewById(R.id.ettRMail);
        ettPass = findViewById(R.id.ettRPass);
        ettFecha = findViewById(R.id.editTextText);
        btnRegistrarse = findViewById(R.id.btnRegistrarse);

        btnRegistrarse.setOnClickListener(v -> registrarUsuario());
    }

    private void registrarUsuario() {
        String[] nombre_apellidos = ettNombre.getText().toString().trim().split(" ");
        String nombre = nombre_apellidos[0];
        String apellidos = nombre_apellidos[1] + " " + nombre_apellidos[2];
        String nick = ettNick.getText().toString().trim();
        String email = ettEmail.getText().toString().trim();
        String pass = ettPass.getText().toString().trim();
        String fechaStr = ettFecha.getText().toString().trim();

        if (nombre.isEmpty() || nick.isEmpty() || email.isEmpty()
                || pass.isEmpty() || fechaStr.isEmpty()) {

            Toast.makeText(this,
                    "Completa todos los campos",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        Date fechaNacimiento;
        try {
            fechaNacimiento = Date.valueOf(convertirFecha(fechaStr));
        } catch (Exception e) {
            Toast.makeText(this,
                    "Formato de fecha inválido (dd/mm/yyyy)",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        // Crear usuario (id 0 -> BD lo genera)
        Usuario nuevoUsuario = new Usuario(
                0,
                nombre,
                apellidos,                 // apellidos (si luego lo separas)
                nick,
                email,
                pass,
                null,               // foto perfil
                fechaNacimiento,
                new Date(System.currentTimeMillis())
        );

        Intent resultado = new Intent();
        resultado.putExtra("usuario_registrado", nuevoUsuario);
        setResult(RESULT_OK, resultado);
        finish();
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
        // dd/MM/yyyy -> yyyy-MM-dd
        String[] partes = fecha.split("/");
        return partes[2] + "-" + partes[1] + "-" + partes[0];
    }
}
