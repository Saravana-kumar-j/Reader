package com.example.reader;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ViewUrlsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private UrlAdapter urlAdapter;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_urls);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dbHandler = new DBHandler(ViewUrlsActivity.this);

        loadData();
    }

    private void loadData() {
        List<UrlData> urlList = dbHandler.getAllUrls();
        urlAdapter = new UrlAdapter(this, urlList);
        recyclerView.setAdapter(urlAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }
}
