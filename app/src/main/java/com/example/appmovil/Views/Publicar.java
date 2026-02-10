package com.example.appmovil.Views;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.appmovil.R;

public class Publicar extends Fragment {

    private ImageView imgPreview;
    private Button btnSeleccionarImagen, btnPublicar;
    private EditText etDescripcionPublicacion;
    private ActivityResultLauncher<Intent> galeriaLauncher;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_publicar, container, false);
    }

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        imgPreview = view.findViewById(R.id.imgPreview);
        btnSeleccionarImagen = view.findViewById(R.id.btnSeleccionarImagen);
        btnPublicar = view.findViewById(R.id.btnPublicar);
        etDescripcionPublicacion = view.findViewById(R.id.etDescripcionPublicacion);
        galeriaLauncher =
                registerForActivityResult(
                        new ActivityResultContracts.StartActivityForResult(),
                        result -> {
                            if (result.getResultCode() == AppCompatActivity.RESULT_OK
                                    && result.getData() != null) {

                                Uri imagenSeleccionada = result.getData().getData();

                                if (imagenSeleccionada != null) {
                                    imgPreview.setImageURI(imagenSeleccionada);
                                    // aquí luego conviertes a Base64 / Blob
                                }
                            }
                        }
                );

        btnPublicar.setOnClickListener(v -> {
            String descripcion = etDescripcionPublicacion.getText().toString().trim();
            if (descripcion.isEmpty()) {
                Toast.makeText(getContext(),
                        "Escribe una descripción",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(getContext(),
                    "Publicación preparada",
                    Toast.LENGTH_SHORT).show();
        });
    }
}
