package com.example.appmovil.Views;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appmovil.Adapters.AdaptadorInicio;
import com.example.appmovil.API.ApiRest;
import com.example.appmovil.Models.Publicacion;
import com.example.appmovil.Models.Usuario;
import com.example.appmovil.R;

import java.util.ArrayList;

public class Perfil extends Fragment {

    private ImageView imgFotoPerfil;
    private TextView txtNickname, txtNombre;
    private RecyclerView rvPublicaciones;
    private ProgressBar progressBar;
    private View statsLayout;
    
    private TextView tvPostsCount, tvSeguidoresCount, tvSeguidosCount;
    
    private Usuario usuario;
    private ArrayList<Publicacion> publicacionesUsuario;
    private Toolbar toolbarPerfil;
    private ActivityResultLauncher<Intent> editarPerfilLauncher;
    private ApiRest api;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_perfil, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        // Initialize API
        api = new ApiRest();

        toolbarPerfil = view.findViewById(R.id.toolbarPerfil);
        imgFotoPerfil = view.findViewById(R.id.imgFotoPerfil);
        txtNickname = view.findViewById(R.id.txtNickname);
        txtNombre = view.findViewById(R.id.txtNombre);
        rvPublicaciones = view.findViewById(R.id.rvPublicaciones);
        progressBar = view.findViewById(R.id.progressBar);
        
        // Find stats views if they exist
        statsLayout = view.findViewById(R.id.statsLayout);
        
        // Hide progress initially
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }

        // Set up toolbar with edit option
        if (toolbarPerfil != null) {
            toolbarPerfil.setTitle("Mi Perfil");
            toolbarPerfil.inflateMenu(R.menu.menu_perfil);
            toolbarPerfil.setOnMenuItemClickListener(item -> {
                if (item.getItemId() == R.id.opcion_editar) {
                    if (usuario != null) {
                        Intent intent = new Intent(getContext(), ModificarPerfil.class);
                        intent.putExtra("usuario_a_modificar", usuario);
                        editarPerfilLauncher.launch(intent);
                    }
                    return true;
                } else if (item.getItemId() == R.id.opcion_cerrar_sesion) {
                    // Logout
                    InicioSesion.logout(requireContext());
                    Intent intent = new Intent(getContext(), InicioSesion.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    return true;
                }
                return false;
            });
        }

        editarPerfilLauncher =
                registerForActivityResult(
                        new ActivityResultContracts.StartActivityForResult(),
                        result -> {
                            if (result.getResultCode() == AppCompatActivity.RESULT_OK
                                    && result.getData() != null) {

                                Usuario usuarioModificado =
                                        (Usuario) result.getData()
                                                .getSerializableExtra("usuario_modificado");

                                if (usuarioModificado != null) {
                                    usuario = usuarioModificado;
                                    actualizarUI();
                                }
                            }
                        }
                );

        // Get user from SharedPreferences
        usuario = obtenerUsuario();
        
        if (usuario != null) {
            actualizarUI();
            cargarPublicacionesUsuario();
        } else {
            Toast.makeText(getContext(), "Error al cargar usuario", Toast.LENGTH_SHORT).show();
        }
    }
    
    private void actualizarUI() {
        if (usuario == null) return;
        
        // Update UI with user data
        if (txtNickname != null) {
            String nickname = usuario.getNickname();
            txtNickname.setText("@" + (nickname != null && !nickname.isEmpty() ? nickname : "usuario"));
        }
        if (txtNombre != null) {
            String nombreCompleto = usuario.getNombre();
            String apellidos = usuario.getApellidos();
            if (apellidos != null && !apellidos.isEmpty()) {
                nombreCompleto += " " + apellidos;
            }
            txtNombre.setText(nombreCompleto != null ? nombreCompleto : "Usuario");
        }
    }
    
    private void cargarPublicacionesUsuario() {
        if (usuario == null || usuario.getId() <= 0) {
            // Show empty list for invalid user
            publicacionesUsuario = new ArrayList<>();
            AdaptadorInicio adaptador = new AdaptadorInicio(publicacionesUsuario, getContext());
            rvPublicaciones.setLayoutManager(new LinearLayoutManager(getContext()));
            rvPublicaciones.setAdapter(adaptador);
            return;
        }
        
        if (progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }
        
        // Get publications from API
        api.obtenerPublicacionesPorUsuario(usuario.getId(), new ApiRest.PublicationsCallback() {
            @Override
            public void onPublicationsResult(boolean success, ArrayList<Publicacion> publicaciones, String errorMessage) {
                if (getActivity() == null) return;
                
                getActivity().runOnUiThread(() -> {
                    if (progressBar != null) {
                        progressBar.setVisibility(View.GONE);
                    }
                    
                    if (success && publicaciones != null) {
                        publicacionesUsuario = publicaciones;
                    } else {
                        // Show empty list if API fails
                        publicacionesUsuario = new ArrayList<>();
                    }
                    
                    // Update posts count
                    if (statsLayout != null) {
                        // Try to find TextViews for stats
                        View postsView = statsLayout.findViewById(R.id.tvPostsCount);
                        if (postsView instanceof TextView) {
                            ((TextView) postsView).setText(String.valueOf(publicacionesUsuario.size()));
                        }
                    }
                    
                    // Set up RecyclerView
                    AdaptadorInicio adaptador = new AdaptadorInicio(publicacionesUsuario, getContext());
                    rvPublicaciones.setLayoutManager(new LinearLayoutManager(getContext()));
                    rvPublicaciones.setAdapter(adaptador);
                });
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_perfil, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.opcion_editar){
            if (usuario != null) {
                Intent modificar_perfil = new Intent(getContext(), ModificarPerfil.class);
                modificar_perfil.putExtra("usuario_a_modificar", usuario);
                editarPerfilLauncher.launch(modificar_perfil);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Get user from SharedPreferences (logged in user)
     */
    private Usuario obtenerUsuario() {
        int userId = InicioSesion.getUserId(requireContext());
        String nickname = InicioSesion.getUserNickname(requireContext());
        String nombre = ApiRest.getUserNombre(requireContext());
        String apellidos = ApiRest.getUserApellidos(requireContext());
        
        if (userId > 0) {
            Usuario u = new Usuario();
            u.setId(userId);
            u.setNickname(nickname != null ? nickname : "");
            u.setNombre(nombre != null ? nombre : "");
            u.setApellidos(apellidos != null ? apellidos : "");
            return u;
        }
        
        // Fallback to mock user if not logged in
        return new Usuario(
                1,
                "Nombre",
                "Apellidos",
                "nickname",
                "email@email.com",
                "1234",
                null,
                null,
                null
        );
    }
}
