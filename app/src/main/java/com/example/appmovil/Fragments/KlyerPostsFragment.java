package com.example.appmovil.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmovil.Adapters.AdapterFeed;
import com.example.appmovil.ApiRest.Api_Gets;
import com.example.appmovil.ApiRest.Api_Inserts;
import com.example.appmovil.KlyerFeed;
import com.example.appmovil.Publicaciones.Post;
import com.example.appmovil.R;

import java.util.ArrayList;

public class KlyerPostsFragment extends Fragment {

    private RecyclerView rvMyPosts;
    private AdapterFeed adapter;
    private final ArrayList<Post> listaPosts = new ArrayList<>();
    private Api_Gets apiGets;
    private Api_Inserts apiInserts;
    private int myUserId;
    private FrameLayout loadingOverlay;
    private LinearLayout emptyState;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_posts, container, false);

        apiGets = new Api_Gets();
        apiInserts = new Api_Inserts();
        
        rvMyPosts = view.findViewById(R.id.rvMyPosts);
        rvMyPosts.setLayoutManager(new LinearLayoutManager(getContext()));
        loadingOverlay = view.findViewById(R.id.loading_overlay);
        emptyState = view.findViewById(R.id.emptyState);

        SharedPreferences prefs = requireActivity().getSharedPreferences("BettrPrefs", Context.MODE_PRIVATE);
        myUserId = prefs.getInt("userId", -1);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadMyPosts();
    }

    private void loadMyPosts() {
        if (myUserId == -1) {
            showEmptyState();
            return;
        }

        showLoading();

        apiGets.getPostsByUserId(myUserId, posts -> {
            if (isAdded() && getActivity() != null) {
                getActivity().runOnUiThread(() -> {
                    hideLoading();
                    if (posts != null && !posts.isEmpty()) {
                        listaPosts.clear();
                        listaPosts.addAll(posts);
                        adapter = new AdapterFeed(listaPosts, myUserId, apiInserts, () -> loadMyPosts());
                        rvMyPosts.setAdapter(adapter);
                        rvMyPosts.setVisibility(View.VISIBLE);
                        emptyState.setVisibility(View.GONE);
                    } else {
                        showEmptyState();
                    }
                });
            }
        });
    }

    private void showEmptyState() {
        rvMyPosts.setVisibility(View.GONE);
        emptyState.setVisibility(View.VISIBLE);
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

