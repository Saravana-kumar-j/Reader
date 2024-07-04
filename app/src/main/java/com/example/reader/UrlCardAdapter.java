package com.example.reader;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class UrlCardAdapter extends RecyclerView.Adapter<UrlCardAdapter.UrlCardViewHolder> {

    private List<UrlData> urlList;
    private Context context;

    public UrlCardAdapter(Context context, List<UrlData> urlList) {
        this.context = context;
        this.urlList = urlList;
    }

    @Override
    public UrlCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.web_preview_item, parent, false);
        return new UrlCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UrlCardViewHolder holder, int position) {
        UrlData urlData = urlList.get(position);
        holder.websiteNameTextView.setText(urlData.getWebsiteName());
        holder.websiteLinkTextView.setText(urlData.getWebsiteLink());

        // Handle item click
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, WebViewActivity.class);
            intent.putExtra("url", urlData.getWebsiteLink());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return urlList.size();
    }

    public static class UrlCardViewHolder extends RecyclerView.ViewHolder {
        TextView websiteNameTextView;
        TextView websiteLinkTextView;

        public UrlCardViewHolder(View itemView) {
            super(itemView);
            websiteNameTextView = itemView.findViewById(R.id.website_name_text_view);
            websiteLinkTextView = itemView.findViewById(R.id.website_link_text_view);

            // Apply margin to the item view
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) itemView.getLayoutParams();
            layoutParams.setMargins(16, 8, 16, 8); // Left, Top, Right, Bottom
            itemView.setLayoutParams(layoutParams);
        }
    }
}
