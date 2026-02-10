package com.example.appmovil.Fragments;

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

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appmovil.Adapters.AdaptadorInicio;
import com.example.appmovil.Models.Publicacion;
import com.example.appmovil.Models.Usuario;
import com.example.appmovil.R;
import com.example.appmovil.Views.ModificarPerfil;

import java.util.ArrayList;

public class Perfil extends Fragment {

    private ImageView imgFotoPerfil;
    private TextView txtNickname, txtDescripcion;
    private RecyclerView rvPublicaciones;

    private Usuario usuario;
    private ArrayList<Publicacion> publicacionesUsuario;
    private Toolbar toolbarPerfil;
    private ActivityResultLauncher<Intent> editarPerfilLauncher;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_perfil, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        toolbarPerfil = view.findViewById(R.id.toolbarPerfil);

        toolbarPerfil.setTitle("Perfil");
        toolbarPerfil.inflateMenu(R.menu.menu_perfil);

        toolbarPerfil.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.opcion_editar) {
                Intent intent = new Intent(getContext(), ModificarPerfil.class);
                intent.putExtra("usuario_a_modificar", usuario);
                editarPerfilLauncher.launch(intent);
                return true;
            }
            return false;
        });

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
//                                    actualizarVistaUsuario();
                                }
                            }
                        }
                );


        imgFotoPerfil = view.findViewById(R.id.imgFotoPerfil);
        txtNickname = view.findViewById(R.id.txtNickname);
        txtDescripcion = view.findViewById(R.id.txtDescripcion);
        rvPublicaciones = view.findViewById(R.id.rvPublicaciones);

        toolbarPerfil = view.findViewById(R.id.toolbarPerfil);



        usuario = obtenerUsuario();
        publicacionesUsuario = obtenerPublicacionesUsuario(usuario.getId());

        txtNickname.setText(usuario.getNickname());
        txtDescripcion.setText("Descripción del usuario");

        imgFotoPerfil.setImageResource(R.drawable.user_icon);

        AdaptadorInicio adaptador =
                new AdaptadorInicio(publicacionesUsuario, getContext());

        rvPublicaciones.setLayoutManager(new LinearLayoutManager(getContext()));
        rvPublicaciones.setAdapter(adaptador);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_perfil, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.opcion_editar){
            Intent modificar_perfil = new Intent(getContext(), ModificarPerfil.class);
            modificar_perfil.putExtra("usuario_a_modificar", usuario);
            editarPerfilLauncher.launch(modificar_perfil);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private Usuario obtenerUsuario() {
        // Esto luego vendrá de API.
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

    private ArrayList<Publicacion> obtenerPublicacionesUsuario(int idUsuario) {

        ArrayList<Publicacion> lista = new ArrayList<>();

        // Aquí luego filtrar por id_usuario
        // lista.add(...)

        return lista;
    }
}
