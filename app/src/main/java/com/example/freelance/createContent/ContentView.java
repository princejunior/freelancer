package com.example.freelance.createContent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freelance.R;

import java.util.ArrayList;

public class ContentView extends RecyclerView.Adapter<ContentView.ContentViewItem> {
    Context context;
    ArrayList<DisplayAllContent> list;

//    public ContentView(FirstFragment firstFragment, ArrayList<DisplayAllContent> list) {
//    }

    public ContentView(Context context, ArrayList<DisplayAllContent> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ContentViewItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);

        return new ContentViewItem(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContentViewItem holder, int position) {
        DisplayAllContent displayAllContent = list.get(position);

        holder.firstName.setText(displayAllContent.firstName);
        holder.lastName.setText(displayAllContent.lastName);
        holder.age.setText(String.valueOf(displayAllContent.age));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ContentViewItem extends RecyclerView.ViewHolder {

        TextView title, description, imageUrl, price, userId, firstName, lastName, age;

        public ContentViewItem(@NonNull View itemView) {
            super(itemView);
            firstName = itemView.findViewById(R.id.tvfirstName);
            lastName = itemView.findViewById(R.id.tvlastName);
            age = itemView.findViewById(R.id.tvage);
        }
    }

}
