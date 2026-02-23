package com.example.appmovil;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.appmovil.Fragments.KlyerCameraFragment;
import com.example.appmovil.Fragments.KlyerFeedFragment;
import com.example.appmovil.Fragments.KlyerPostsFragment;
import com.example.appmovil.Fragments.KlyerProfileFragment;
import com.example.appmovil.Fragments.KlyerSocialFragment;

public class KlyerFeed extends AppCompatActivity {

    private FrameLayout btnHome, btnPosts, btnCamera, btnSocial, btnProfile;
    private ImageView ivHome, ivPosts, ivCamera, ivSocial, ivProfile;
    private ImageView ivNavAvatar;
    private TextView tvNavAvatarLetter;
    private FrameLayout loadingOverlay;
    private int colorActive = Color.parseColor("#0F766E");
    private int colorInactive = Color.parseColor("#7A8AA0");
    private UserSession session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_feed);

        session = new UserSession(this);
        loadingOverlay = findViewById(R.id.loading_overlay);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0);
            return insets;
        });

        initNav();
        loadUserAvatarInNav();
        
        if (savedInstanceState == null) {
            navigateToHome();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadUserAvatarInNav();
    }


    private void loadUserAvatarInNav() {
        if (ivNavAvatar == null || tvNavAvatarLetter == null) {
            return;
        }

        String name = session.getUserName();
        String avatar = session.getUserAvatar();

        if (name != null && !name.isEmpty()) {
            tvNavAvatarLetter.setText(name.substring(0, 1).toUpperCase());
        } else {
            tvNavAvatarLetter.setText("");
        }

        if (avatar != null && !avatar.isEmpty()) {
            try {
                byte[] decodedString = Base64.decode(avatar, Base64.DEFAULT);
                Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                if (decodedBitmap != null) {
                    ivNavAvatar.setImageBitmap(decodedBitmap);
                    ivNavAvatar.setVisibility(View.VISIBLE);
                    tvNavAvatarLetter.setVisibility(View.GONE);
                } else {
                    ivNavAvatar.setVisibility(View.GONE);
                    tvNavAvatarLetter.setVisibility(View.VISIBLE);
                }
            } catch (Exception e) {
                ivNavAvatar.setVisibility(View.GONE);
                tvNavAvatarLetter.setVisibility(View.VISIBLE);
            }
        } else {
            ivNavAvatar.setVisibility(View.GONE);
            tvNavAvatarLetter.setVisibility(View.VISIBLE);
        }
    }

    public void showLoading() {
        if (loadingOverlay != null) {
            loadingOverlay.setVisibility(View.VISIBLE);
        }
    }

    public void hideLoading() {
        if (loadingOverlay != null) {
            loadingOverlay.setVisibility(View.GONE);
        }
    }

    public void navigateToHome() {
        updateNavUI(R.id.btn_nav_home);
        loadFragment(new KlyerFeedFragment());
    }

    private void initNav() {
        btnHome = findViewById(R.id.btn_nav_home);
        btnPosts = findViewById(R.id.btn_nav_posts);
        btnCamera = findViewById(R.id.btn_nav_camera);
        btnProfile = findViewById(R.id.btn_nav_profile);
        btnSocial = findViewById(R.id.btn_nav_social);

        ivHome = findViewById(R.id.iv_home_icon);
        ivPosts = findViewById(R.id.iv_posts_icon);
        ivCamera = findViewById(R.id.iv_camera_icon);
        ivSocial = findViewById(R.id.iv_social_icon);

        if (btnHome != null) btnHome.setOnClickListener(v -> { updateNavUI(R.id.btn_nav_home); loadFragment(new KlyerFeedFragment()); });
        if (btnPosts != null) btnPosts.setOnClickListener(v -> { updateNavUI(R.id.btn_nav_posts); loadFragment(new KlyerPostsFragment()); });
        if (btnCamera != null) btnCamera.setOnClickListener(v -> { updateNavUI(R.id.btn_nav_camera); loadFragment(new KlyerCameraFragment()); });
        if (btnProfile != null) btnProfile.setOnClickListener(v -> { updateNavUI(R.id.btn_nav_profile); loadFragment(new KlyerProfileFragment()); });
        if (btnSocial != null) btnSocial.setOnClickListener(v -> { updateNavUI(R.id.btn_nav_social); loadFragment(new KlyerSocialFragment()); });
    }

    private void updateNavUI(int selectedId) {
        if (ivHome != null) ivHome.setColorFilter(colorInactive);
        if (ivPosts != null) ivPosts.setColorFilter(colorInactive);
        if (ivCamera != null) ivCamera.setColorFilter(colorInactive);
        if (ivSocial != null) ivSocial.setColorFilter(colorInactive);
        
        if (ivNavAvatar != null) {
            if (selectedId == R.id.btn_nav_profile) {
                ivNavAvatar.setColorFilter(colorActive);
            } else {
                ivNavAvatar.clearColorFilter();
            }
        }
        
        if (tvNavAvatarLetter != null) {
            if (selectedId == R.id.btn_nav_profile) {
                tvNavAvatarLetter.setTextColor(colorActive);
            } else {
                tvNavAvatarLetter.setTextColor(colorInactive);
            }
        }

        if (selectedId == R.id.btn_nav_home && ivHome != null) {
            ivHome.setColorFilter(colorActive);
        } else if (selectedId == R.id.btn_nav_posts && ivPosts != null) {
            ivPosts.setColorFilter(colorActive);
        } else if (selectedId == R.id.btn_nav_camera && ivCamera != null) {
            ivCamera.setColorFilter(colorActive);
        } else if (selectedId == R.id.btn_nav_social && ivSocial != null) {
            ivSocial.setColorFilter(colorActive);
        }
    }

    public void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }
}
