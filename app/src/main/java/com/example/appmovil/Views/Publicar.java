package com.example.appmovil.Views;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.appmovil.API.ApiRest;
import com.example.appmovil.R;

public class Publicar extends Fragment {

    private ImageView imgPreview;
    private Button btnSeleccionarImagen, btnPublicar;
    private EditText etDescripcionPublicacion;
    private ProgressBar progressBar;
    private ActivityResultLauncher<Intent> galeriaLauncher;
    private Bitmap selectedImageBitmap;
    private ApiRest api;

    @Nullable
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
        
        // Initialize API
        api = new ApiRest();

        imgPreview = view.findViewById(R.id.imgPreview);
        btnSeleccionarImagen = view.findViewById(R.id.btnSeleccionarImagen);
        btnPublicar = view.findViewById(R.id.btnPublicar);
        etDescripcionPublicacion = view.findViewById(R.id.etDescripcionPublicacion);
        progressBar = view.findViewById(R.id.progressBar);
        
        galeriaLauncher =
                registerForActivityResult(
                        new ActivityResultContracts.StartActivityForResult(),
                        result -> {
                            if (result.getResultCode() == AppCompatActivity.RESULT_OK
                                    && result.getData() != null) {

                                Uri imagenSeleccionada = result.getData().getData();

                                if (imagenSeleccionada != null) {
                                    imgPreview.setImageURI(imagenSeleccionada);
                                    // Store the bitmap for later upload
                                    selectedImageBitmap = null; // Will be converted on publish
                                }
                            }
                        }
                );
        
        // Select image button
        btnSeleccionarImagen.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            galeriaLauncher.launch(intent);
        });

        // Publish button
        btnPublicar.setOnClickListener(v -> {
            String descripcion = etDescripcionPublicacion.getText().toString().trim();
            
            if (descripcion.isEmpty()) {
                Toast.makeText(getContext(),
                        "Escribe una descripción",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            // Get logged in user ID
            int userId = InicioSesion.getUserId(requireContext());
            if (userId <= 0) {
                Toast.makeText(getContext(),
                        "Error: usuario no identificado",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            // Convert image to Base64 if selected
            String imagenBase64 = null;
            if (selectedImageBitmap != null) {
                imagenBase64 = ApiRest.bitmapToBase64(selectedImageBitmap);
            }

            // Show loading
            progressBar.setVisibility(View.VISIBLE);
            btnPublicar.setEnabled(false);

            // Call API to create publication
            api.crearPublicacion(userId, descripcion, imagenBase64, 
                new ApiRest.PublicationCallback() {
                    @Override
                    public void onPublicationResult(boolean success, String errorMessage) {
                        if (getActivity() == null) return;
                        
                        getActivity().runOnUiThread(() -> {
                            progressBar.setVisibility(View.GONE);
                            btnPublicar.setEnabled(true);

                            if (success) {
                                Toast.makeText(getContext(),
                                        "¡Publicación creada con éxito!",
                                        Toast.LENGTH_SHORT).show();
                                
                                // Clear form
                                etDescripcionPublicacion.setText("");
                                imgPreview.setImageResource(R.drawable.ic_launcher_foreground);
                                selectedImageBitmap = null;
                                
                                // Navigate to Inicio tab to see new post
                                // The parent activity will handle this
                            } else {
                                Toast.makeText(getContext(),
                                        errorMessage != null ? errorMessage : "Error al crear publicación",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
        });
    }
}

