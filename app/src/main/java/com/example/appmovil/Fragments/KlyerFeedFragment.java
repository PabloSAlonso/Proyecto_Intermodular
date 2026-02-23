package com.example.appmovil.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.appmovil.Adapters.AdapterFeed;
import com.example.appmovil.ApiRest.Api_Gets;
import com.example.appmovil.ApiRest.Api_Inserts;
import com.example.appmovil.KlyerFeed;
import com.example.appmovil.Publicaciones.Post;
import com.example.appmovil.R;
import com.example.appmovil.UserSession;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class KlyerFeedFragment extends Fragment {

    private RecyclerView rvFeed;
    private SwipeRefreshLayout swipeRefresh;
    private AdapterFeed adapter;
    private final ArrayList<Post> listaPosts = new ArrayList<>();
    private Api_Gets apiGets;
    private Api_Inserts apiInserts;
    private int myUserId;
    private View emptyState;
    private ShapeableImageView ivProfileTop;
    private UserSession userSession;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed, container, false);

        apiGets = new Api_Gets();
        apiInserts = new Api_Inserts();
        rvFeed = view.findViewById(R.id.rvFeed);
        rvFeed.setLayoutManager(new LinearLayoutManager(getContext()));
        
        swipeRefresh = view.findViewById(R.id.swipeRefresh);
        emptyState = view.findViewById(R.id.emptyState);
        ivProfileTop = view.findViewById(R.id.ivProfileTop);
        
        userSession = new UserSession(requireContext());
        SharedPreferences prefs = requireActivity().getSharedPreferences("BettrPrefs", Context.MODE_PRIVATE);
        myUserId = prefs.getInt("userId", -1);

        if (swipeRefresh != null) {
            swipeRefresh.setColorSchemeResources(R.color.blue_primary);
            swipeRefresh.setOnRefreshListener(this::loadPosts);
        }

        loadUserAvatar();
        loadPosts();

        return view;
    }
    
    private void loadUserAvatar() {
        if (myUserId == -1) return;
        
        apiGets.getUserById(myUserId, user -> {
            if (isAdded() && getActivity() != null) {
                getActivity().runOnUiThread(() -> {
                    if (user != null) {
                        String avatarBase64 = user.getAvatarUrl();
                        if (avatarBase64 != null && !avatarBase64.isEmpty()) {
                            Bitmap avatar = decodeBase64(avatarBase64);
                            if (avatar != null) {
                                ivProfileTop.setImageBitmap(avatar);
                            } else {
                                ivProfileTop.setImageBitmap(generateInitialAvatar(user.getUsername()));
                            }
                        } else {
                            ivProfileTop.setImageBitmap(generateInitialAvatar(user.getUsername()));
                        }
                    }
                });
            }
        });
    }
    
    private Bitmap generateInitialAvatar(String username) {
        if (username == null || username.isEmpty()) return null;
        String initial = username.substring(0, 1).toUpperCase();
        int size = 200;
        Bitmap bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        android.graphics.Canvas canvas = new android.graphics.Canvas(bitmap);
        android.graphics.Paint bgPaint = new android.graphics.Paint();
        bgPaint.setColor(android.graphics.Color.parseColor("#0F766E"));
        canvas.drawCircle(size / 2f, size / 2f, size / 2f, bgPaint);
        android.graphics.Paint textPaint = new android.graphics.Paint();
        textPaint.setColor(android.graphics.Color.WHITE);
        textPaint.setTextSize(100);
        textPaint.setTextAlign(android.graphics.Paint.Align.CENTER);
        textPaint.setAntiAlias(true);
        canvas.drawText(initial, size / 2f, size / 2f + 35, textPaint);
        return bitmap;
    }
    
    private Bitmap decodeBase64(String input) {
        try {
            byte[] decodedString = Base64.decode(input, Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        } catch (Exception e) {
            return null;
        }
    }

    private void loadPosts() {
        if (myUserId == -1) {
            if (swipeRefresh != null) swipeRefresh.setRefreshing(false);
            showEmptyState();
            return;
        }

        if (swipeRefresh != null && !swipeRefresh.isRefreshing()) {
            showLoading();
        }

        apiGets.getSocialFeed(myUserId, posts -> {
            if (isAdded() && getActivity() != null) {
                getActivity().runOnUiThread(() -> {
                    hideLoading();
                    if (swipeRefresh != null) swipeRefresh.setRefreshing(false);
                    
                    if (posts != null && !posts.isEmpty()) {
                        listaPosts.clear();
                        listaPosts.addAll(posts);
                        if (adapter == null) {
                            adapter = new AdapterFeed(listaPosts, myUserId, apiInserts, this::loadPosts);
                            rvFeed.setAdapter(adapter);
                        } else {
                            adapter.notifyDataSetChanged();
                        }
                        hideEmptyState();
                    } else {
                        showEmptyState();
                    }
                });
            }
        });
    }

    private void showLoading() {
        if (getActivity() instanceof KlyerFeed) {
            ((KlyerFeed) getActivity()).showLoading();
        }
    }

    private void hideLoading() {
        if (getActivity() instanceof KlyerFeed) {
            ((KlyerFeed) getActivity()).hideLoading();
        }
    }

    private void showEmptyState() {
        if (rvFeed != null) rvFeed.setVisibility(View.GONE);
        if (emptyState != null) emptyState.setVisibility(View.VISIBLE);
    }

    private void hideEmptyState() {
        if (rvFeed != null) rvFeed.setVisibility(View.VISIBLE);
        if (emptyState != null) emptyState.setVisibility(View.GONE);
    }
}
