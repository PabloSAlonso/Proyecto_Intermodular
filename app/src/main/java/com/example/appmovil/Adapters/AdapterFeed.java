package com.example.appmovil.Adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmovil.ApiRest.Api_Inserts;
import com.example.appmovil.Publicaciones.Post;
import com.example.appmovil.R;

import java.util.ArrayList;

public class AdapterFeed extends RecyclerView.Adapter<AdapterFeed.MyViewHolder> {
    private ArrayList<Post> listaPosts;
    private int userId;
    private Api_Inserts apiInserts;
    private Runnable onLikeChanged;

    public AdapterFeed(ArrayList<Post> listaPosts, int userId, Api_Inserts apiInserts, Runnable onLikeChanged) {
        this.listaPosts = listaPosts;
        this.userId = userId;
        this.apiInserts = apiInserts;
        this.onLikeChanged = onLikeChanged;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Post post = listaPosts.get(position);
        
        holder.tvUserName.setText("@" + post.getNombreUsuario());
        
        if (post.getDescripcion() != null && !post.getDescripcion().isEmpty()) {
            holder.tvDescription.setText(post.getDescripcion());
            holder.tvDescription.setVisibility(View.VISIBLE);
        } else {
            holder.tvDescription.setVisibility(View.GONE);
        }
        
        if (post.getUserAvatar() != null && !post.getUserAvatar().isEmpty()) {
            Bitmap avatar = decodeBase64(post.getUserAvatar());
            if (avatar != null) {
                holder.ivProfilePost.setImageBitmap(avatar);
            } else {
                holder.ivProfilePost.setImageResource(R.drawable.logoklyer);
            }
        } else {
            holder.ivProfilePost.setImageResource(R.drawable.logoklyer);
        }

        if (post.getImageUrl() != null && !post.getImageUrl().isEmpty()) {
            Bitmap postImage = decodeBase64(post.getImageUrl());
            if (postImage != null) {
                holder.ivPostImage.setImageBitmap(postImage);
                holder.cvPostImage.setVisibility(View.VISIBLE);
            } else {
                holder.cvPostImage.setVisibility(View.GONE);
            }
        } else {
            holder.cvPostImage.setVisibility(View.GONE);
        }
    }




    private Bitmap decodeBase64(String input) {
        try {
            byte[] decodedString = Base64.decode(input, Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int getItemCount() {
        return listaPosts.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvUserName, tvPostInfo, tvDescription, tvPostType;
        ImageView ivPostImage, ivProfilePost;
        View cvPostImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUserName = itemView.findViewById(R.id.tvUserName);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            ivPostImage = itemView.findViewById(R.id.ivPostImage);
            ivProfilePost = itemView.findViewById(R.id.ivProfilePost);
            cvPostImage = itemView.findViewById(R.id.cvPostImage);
        }
    }
}
