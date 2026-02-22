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
        
        String postType = post.getPostType();
        if (postType != null && !postType.isEmpty()) {
            String emoji = getPostEmoji(postType);
            holder.tvPostType.setText(emoji + " " + postType);
            holder.tvPostType.setVisibility(View.VISIBLE);
        } else {
            holder.tvPostType.setVisibility(View.GONE);
        }
        
        String timeAgo = getTimeAgo(post.getCreatedAt());
        holder.tvPostInfo.setText("• " + timeAgo);
        
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

    private String getPostEmoji(String postType) {
        switch (postType) {
            case "Ejercicio": return "💪";
            case "Lectura": return "📚";
            case "Meditación": return "🧘";
            case "Estudio": return "📖";
            case "Saludable": return "🥗";
            default: return "⭐";
        }
    }

    private String getTimeAgo(String dateString) {
        if (dateString == null || dateString.isEmpty()) return "ahora";
        
        try {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", java.util.Locale.getDefault());
            sdf.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));
            java.util.Date date = sdf.parse(dateString.substring(0, Math.min(dateString.length(), 19)));
            
            if (date == null) return "ahora";
            
            long diff = System.currentTimeMillis() - date.getTime();
            
            if (diff < 60000) return "ahora";
            if (diff < 3600000) return Math.floor(diff / 60000) + "m";
            if (diff < 86400000) return Math.floor(diff / 3600000) + "h";
            if (diff < 604800000) return Math.floor(diff / 86400000) + "d";
            return Math.floor(diff / 604800000) + "sem";
        } catch (Exception e) {
            return "recientemente";
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
            tvPostInfo = itemView.findViewById(R.id.tvPostInfo);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvPostType = itemView.findViewById(R.id.tvPostType);
            ivPostImage = itemView.findViewById(R.id.ivPostImage);
            ivProfilePost = itemView.findViewById(R.id.ivProfilePost);
            cvPostImage = itemView.findViewById(R.id.cvPostImage);
        }
    }
}
