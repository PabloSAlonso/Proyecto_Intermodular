package com.example.appmovil;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class KlyerCompleteProfile extends AppCompatActivity {
    private TextInputEditText etBio;
    private ImageView ivAvatar;
    private Bitmap avatarBitmap;
    private FrameLayout loadingOverlay;

    private final ActivityResultLauncher<Intent> cameraLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    Bundle extras = result.getData().getExtras();
                    avatarBitmap = (Bitmap) extras.get("data");
                    ivAvatar.setImageBitmap(avatarBitmap);
                }
            }
    );

    private final ActivityResultLauncher<String> galleryLauncher = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            uri -> {
                if (uri != null) {
                    try {
                        avatarBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                        ivAvatar.setImageBitmap(avatarBitmap);
                    } catch (IOException ignored) {
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_complete_profile);

        etBio = findViewById(R.id.etBio);
        ivAvatar = findViewById(R.id.ivAvatar);
        Button btnSelectImage = findViewById(R.id.btnSelectImage);
        Button btnFinish = findViewById(R.id.btnFinishProfile);
        loadingOverlay = findViewById(R.id.loading_overlay);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnSelectImage.setOnClickListener(v -> showImageSourceDialog());
        btnFinish.setOnClickListener(v -> finishProfile());
    }

    private void showImageSourceDialog() {
        String[] options = {"Cámara", "Galería"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Seleccionar foto de perfil");
        builder.setItems(options, (dialog, which) -> {
            if (which == 0) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraLauncher.launch(intent);
            } else {
                galleryLauncher.launch("image/*");
            }
        });
        builder.show();
    }

    private void finishProfile() {
        showLoading();
        UserSession session = new UserSession(this);

        if (etBio != null) {
            session.setUserBio(etBio.getText() == null ? "" : etBio.getText().toString().trim());
        }

        if (avatarBitmap != null) {
            session.setUserAvatar(encodeImageToBase64(avatarBitmap));
        }

        hideLoading();
        startActivity(new Intent(KlyerCompleteProfile.this, KlyerFeed.class));
        finish();
    }

    private String encodeImageToBase64(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, outputStream);
        return Base64.encodeToString(outputStream.toByteArray(), Base64.NO_WRAP);
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
