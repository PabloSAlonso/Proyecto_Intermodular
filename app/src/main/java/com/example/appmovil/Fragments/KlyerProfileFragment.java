package com.example.appmovil.Fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.appmovil.ApiRest.Api_Gets;
import com.example.appmovil.ApiRest.Api_Inserts;
import com.example.appmovil.KlyerFeed;
import com.example.appmovil.KlyerIntro;
import com.example.appmovil.R;
import com.example.appmovil.UserSession;
import com.google.android.material.button.MaterialButton;

public class KlyerProfileFragment extends Fragment {

    private TextView tvName, tvUsername, tvEmail, tvBio, tvFollowers, tvFollowing, tvHabitsCount, tvAvatarLetter;
    private ImageView ivAvatar;
    private View layoutFollowers, layoutFollowing;
    private FrameLayout loadingOverlay;
    private MaterialButton btnLogout, btnDeleteAccount, btnEditProfile;
    private Api_Gets apiGets;
    private Api_Inserts apiInserts;
    private UserSession session;
    private int myUserId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        apiGets = new Api_Gets();
        apiInserts = new Api_Inserts();
        session = new UserSession(requireContext());

        initViews(view);

        myUserId = session.getUserId();

        if (myUserId != -1) {
            loadUserDataFromAPI();
            loadStatsFromAPI();
        }

        if (layoutFollowers != null) {
            layoutFollowers.setOnClickListener(v -> loadFragment(KlyerFollowersFragment.newInstance(myUserId, "followers")));
        }
        if (layoutFollowing != null) {
            layoutFollowing.setOnClickListener(v -> loadFragment(KlyerFollowersFragment.newInstance(myUserId, "following")));
        }
        if (btnLogout != null) {
            btnLogout.setOnClickListener(v -> logout());
        }
        if (btnDeleteAccount != null) {
            btnDeleteAccount.setOnClickListener(v -> confirmDeleteAccount());
        }
        if (btnEditProfile != null) {
            btnEditProfile.setOnClickListener(v -> editProfile());
        }

        return view;
    }

    private void initViews(View view) {
        tvName = view.findViewById(R.id.tvName);
        tvUsername = view.findViewById(R.id.tvUsername);
        tvEmail = view.findViewById(R.id.tvEmail);
        tvBio = view.findViewById(R.id.tvBio);
        tvFollowers = view.findViewById(R.id.tvFollowers);
        tvFollowing = view.findViewById(R.id.tvFollowing);
        tvHabitsCount = view.findViewById(R.id.tvHabitsCount);
        tvAvatarLetter = view.findViewById(R.id.tvAvatarLetter);
        ivAvatar = view.findViewById(R.id.ivAvatar);
        loadingOverlay = view.findViewById(R.id.loading_overlay);
        btnLogout = view.findViewById(R.id.btnLogout);
        btnDeleteAccount = view.findViewById(R.id.btnDeleteAccount);
        btnEditProfile = view.findViewById(R.id.btnEditProfile);
        layoutFollowers = view.findViewById(R.id.layoutFollowers);
        layoutFollowing = view.findViewById(R.id.layoutFollowing);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (myUserId != -1) {
            loadUserDataFromAPI();
            loadStatsFromAPI();
        }
    }

    private void loadUserDataFromAPI() {
        showLoading();

        apiGets.getUserById(myUserId, user -> {
            if (isAdded() && getActivity() != null) {
                getActivity().runOnUiThread(() -> {
                    hideLoading();
                    if (user == null) return;

                    session.saveUserData(
                        myUserId,
                        user.getUsername() != null ? user.getUsername() : "",
                        user.getName() != null ? user.getName() : "",
                        user.getEmail() != null ? user.getEmail() : "",
                        user.getAvatarUrl() != null ? user.getAvatarUrl() : "",
                        user.getDescription() != null ? user.getDescription() : ""
                    );

                    updateProfileUI(user.getName(), user.getUsername(), user.getEmail(),
                            user.getDescription(), user.getAvatarUrl());
                });
            }
        });
    }

    private void updateProfileUI(String name, String username, String email, String bio, String avatar) {
        // Name
        if (tvName != null) {
            if (name != null && !name.isEmpty()) {
                tvName.setText(name);
                tvName.setVisibility(View.VISIBLE);
            } else {
                tvName.setText("Usuario");
                tvName.setVisibility(View.VISIBLE);
            }
        }

        // Avatar letter
        if (tvAvatarLetter != null) {
            if (name != null && !name.isEmpty()) {
                tvAvatarLetter.setText(name.substring(0, 1).toUpperCase());
            } else {
                tvAvatarLetter.setText("U");
            }
        }

        // Username
        if (tvUsername != null) {
            if (username != null && !username.isEmpty()) {
                tvUsername.setText("@" + username);
            } else {
                tvUsername.setText("@usuario");
            }
        }

        // Email
        if (tvEmail != null) {
            if (email != null && !email.isEmpty()) {
                tvEmail.setText(email);
                tvEmail.setVisibility(View.VISIBLE);
            } else {
                tvEmail.setVisibility(View.GONE);
            }
        }

        // Bio
        if (tvBio != null) {
            if (bio != null && !bio.isEmpty()) {
                tvBio.setText(bio);
                tvBio.setVisibility(View.VISIBLE);
            } else {
                tvBio.setVisibility(View.GONE);
            }
        }

        // Avatar image
        if (ivAvatar != null && tvAvatarLetter != null) {
            if (avatar != null && !avatar.isEmpty()) {
                try {
                    byte[] decodedString = Base64.decode(avatar, Base64.DEFAULT);
                    Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                    if (decodedBitmap != null) {
                        ivAvatar.setImageBitmap(decodedBitmap);
                        ivAvatar.setVisibility(View.VISIBLE);
                        tvAvatarLetter.setVisibility(View.GONE);
                    } else {
                        ivAvatar.setVisibility(View.GONE);
                        tvAvatarLetter.setVisibility(View.VISIBLE);
                    }
                } catch (Exception e) {
                    ivAvatar.setVisibility(View.GONE);
                    tvAvatarLetter.setVisibility(View.VISIBLE);
                }
            } else {
                ivAvatar.setVisibility(View.GONE);
                tvAvatarLetter.setVisibility(View.VISIBLE);
            }
        }
    }

    private void loadStatsFromAPI() {
        apiGets.getUserStats(myUserId, (followers, following, habits) -> {
            if (isAdded() && getActivity() != null) {
                getActivity().runOnUiThread(() -> updateStatsUI(followers, following, habits));
            }
        });
    }

    private void updateStatsUI(int followers, int following, int posts) {
        if (tvFollowers != null) tvFollowers.setText(String.valueOf(followers));
        if (tvFollowing != null) tvFollowing.setText(String.valueOf(following));
        if (tvHabitsCount != null) tvHabitsCount.setText(String.valueOf(posts));
    }

    private void showLoading() {
        if (loadingOverlay != null) loadingOverlay.setVisibility(View.VISIBLE);
    }

    private void hideLoading() {
        if (loadingOverlay != null) loadingOverlay.setVisibility(View.GONE);
    }

    private void editProfile() {
        if (getActivity() == null) return;

        LinearLayout layout = new LinearLayout(requireContext());
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(50, 30, 50, 10);

        EditText etNombre = new EditText(requireContext());
        etNombre.setHint("Nombre");
        etNombre.setText(session.getUserName());
        layout.addView(etNombre);

        EditText etNickname = new EditText(requireContext());
        etNickname.setHint("Nickname");
        etNickname.setText(session.getUsername());
        layout.addView(etNickname);

        EditText etPassword = new EditText(requireContext());
        etPassword.setHint("Nueva contraseña (opcional)");
        etPassword.setInputType(android.text.InputType.TYPE_CLASS_TEXT | android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD);
        layout.addView(etPassword);

        new AlertDialog.Builder(requireContext())
            .setTitle("Editar perfil")
            .setView(layout)
            .setPositiveButton("Guardar", (dialog, which) -> {
                String nombre = etNombre.getText().toString().trim();
                String nickname = etNickname.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if (nombre.isEmpty() || nickname.isEmpty()) return;

                showLoading();
                apiInserts.updateUserProfile(myUserId, nombre, nickname, password, success -> {
                    if (isAdded() && getActivity() != null) {
                        getActivity().runOnUiThread(() -> {
                            hideLoading();
                            if (success) {
                                session.setUserName(nombre);
                                session.setUsername(nickname);
                                loadUserDataFromAPI();
                            }
                        });
                    }
                });
            })
            .setNegativeButton("Cancelar", null)
            .show();
    }

    private void confirmDeleteAccount() {
        if (getActivity() == null) return;
        new AlertDialog.Builder(requireContext())
            .setTitle("Eliminar cuenta")
            .setMessage("¿Estás seguro de que quieres eliminar tu cuenta? Esta acción no se puede deshacer.")
            .setPositiveButton("Eliminar", (dialog, which) -> {
                apiInserts.deleteUser(myUserId, success -> {
                    if (isAdded() && getActivity() != null) {
                        getActivity().runOnUiThread(() -> {
                            if (success) {
                                session.logout();
                                Intent intent = new Intent(getActivity(), KlyerIntro.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                if (getActivity() != null) getActivity().finish();
                            }
                        });
                    }
                });
            })
            .setNegativeButton("Cancelar", null)
            .show();
    }

    private void logout() {
        if (session != null) session.logout();
        Intent intent = new Intent(getActivity(), KlyerIntro.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        if (getActivity() != null) getActivity().finish();
    }

    private void loadFragment(Fragment fragment) {
        if (getActivity() instanceof KlyerFeed) {
            ((KlyerFeed) getActivity()).loadFragment(fragment);
        }
    }
}
