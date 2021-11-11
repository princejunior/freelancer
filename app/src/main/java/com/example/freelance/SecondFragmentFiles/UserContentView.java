package com.example.freelance.SecondFragmentFiles;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freelance.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class UserContentView extends RecyclerView.Adapter<UserContentView.UserContentViewItem> {

    Context context;
    ArrayList<UserContent> list;

    public UserContentView(Context context, ArrayList<UserContent> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public UserContentView.UserContentViewItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.contentlist, parent, false);

        return new UserContentView.UserContentViewItem(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull UserContentView.UserContentViewItem holder, int position) {

        UserContent userContent = list.get(position);

        holder.price.setText(userContent.price);
        holder.title.setText(userContent.title);
        Picasso.with(context).load(userContent.imageUrl).into(holder.imageView);

    }

    public static class UserContentViewItem extends RecyclerView.ViewHolder {

        TextView title, price;
        ImageView imageView;

        public UserContentViewItem(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvTitle_contentList);
            price = itemView.findViewById(R.id.tvPrice_contentList);
            imageView = itemView.findViewById(R.id.ivContentImage_contentList);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
