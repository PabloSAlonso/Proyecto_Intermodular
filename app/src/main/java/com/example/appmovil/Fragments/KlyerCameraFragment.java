package com.example.appmovil.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appmovil.ApiRest.Api_Inserts;
import com.example.appmovil.KlyerFeed;
import com.example.appmovil.R;
import com.example.appmovil.UserSession;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class KlyerCameraFragment extends Fragment {

    private ImageView ivPreview;
    private EditText etDescription;
    private AutoCompleteTextView spinnerPostType;
    private Button btnCamera, btnGallery, btnPost;
    private Bitmap selectedBitmap;
    private Api_Inserts apiInserts;
    private FrameLayout loadingOverlay;
    private UserSession session;

    private final ActivityResultLauncher<Intent> cameraLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    Bundle extras = result.getData().getExtras();
                    if (extras != null) {
                        selectedBitmap = (Bitmap) extras.get("data");
                        ivPreview.setImageBitmap(selectedBitmap);
                    }
                }
            }
    );

    private final ActivityResultLauncher<String> galleryLauncher = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            uri -> {
                if (uri != null) {
                    try {
                        selectedBitmap = MediaStore.Images.Media.getBitmap(requireActivity().getContentResolver(), uri);
                        ivPreview.setImageBitmap(selectedBitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
    );

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_camera, container, false);

        apiInserts = new Api_Inserts();
        session = new UserSession(requireContext());
        
        ivPreview = view.findViewById(R.id.ivPreview);
        etDescription = view.findViewById(R.id.etDescription);
        btnCamera = view.findViewById(R.id.btnCamera);
        btnPost = view.findViewById(R.id.btnPost);
        loadingOverlay = view.findViewById(R.id.camera_loading_overlay);


        if (btnCamera != null) {
            btnCamera.setOnClickListener(v -> {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraLauncher.launch(intent);
            });
        }

        if (btnGallery != null) {
            btnGallery.setOnClickListener(v -> {
                galleryLauncher.launch("image/*");
            });
        }

        if (btnPost != null) {
            btnPost.setOnClickListener(v -> {
                postPost();
            });
        }

        return view;
    }

    private void postPost() {
        if (etDescription == null || spinnerPostType == null) {
            return;
        }
        
        String description = etDescription.getText().toString().trim();
        String selectedType = spinnerPostType.getText().toString();

        
        if (selectedBitmap == null || description.isEmpty()) {
            Toast.makeText(getContext(), "Añade una imagen y descripción", Toast.LENGTH_SHORT).show();
            return;
        }

        showLoading();

        int userId = session.getUserId();

        if (userId == -1) {
            hideLoading();
            Toast.makeText(getContext(), "Error: Sesión no válida", Toast.LENGTH_SHORT).show();
            return;
        }

        String imageBase64 = encodeImageToBase64(selectedBitmap);

        apiInserts.addPost(userId, description, imageBase64, "",success -> {
            if (isAdded() && getActivity() != null) {
                getActivity().runOnUiThread(() -> {
                    hideLoading();
                    if (success) {
                        Toast.makeText(getContext(), "¡Publicación compartida!", Toast.LENGTH_SHORT).show();
                        etDescription.setText("");
                        ivPreview.setImageResource(android.R.drawable.ic_menu_camera);
                        selectedBitmap = null;
                        
                        if (getActivity() instanceof KlyerFeed) {
                            ((KlyerFeed) getActivity()).navigateToHome();
                        }
                    } else {
                        Toast.makeText(getContext(), "Error al compartir publicación", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private String encodeImageToBase64(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 60, outputStream);
        byte[] byteArray = outputStream.toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
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
