package com.example.reader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class UrlAdapter extends RecyclerView.Adapter<UrlAdapter.UrlViewHolder> {

    private List<UrlData> urlList;
    private DBHandler dbHandler;

    public UrlAdapter(Context context, List<UrlData> urlList) {
        this.urlList = urlList;
        this.dbHandler = new DBHandler(context);
    }

    @NonNull
    @Override
    public UrlViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.url_item, parent, false);
        return new UrlViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UrlViewHolder holder, int position) {
        UrlData urlData = urlList.get(position);
        holder.websiteNameTextView.setText(urlData.getWebsiteName());
        holder.websiteLinkTextView.setText(urlData.getWebsiteLink());

        holder.deleteButton.setOnClickListener(v -> {
            dbHandler.deleteUrl(urlData.getId());
            urlList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, urlList.size());
        });
    }

    @Override
    public int getItemCount() {
        return urlList.size();
    }

    public static class UrlViewHolder extends RecyclerView.ViewHolder {
        TextView websiteNameTextView;
        TextView websiteLinkTextView;
        Button deleteButton;

        public UrlViewHolder(@NonNull View itemView) {
            super(itemView);
            websiteNameTextView = itemView.findViewById(R.id.websiteNameTextView);
            websiteLinkTextView = itemView.findViewById(R.id.websiteLinkTextView);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}
