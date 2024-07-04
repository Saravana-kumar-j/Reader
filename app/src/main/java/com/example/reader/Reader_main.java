package com.example.reader;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class Reader_main extends AppCompatActivity {

    private RecyclerView recyclerView;
    private UrlCardAdapter urlCardAdapter;
    private List<UrlData> urlList;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reader_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dbHandler = new DBHandler(this);
        urlList = dbHandler.getAllUrls();

        urlCardAdapter = new UrlCardAdapter(this, urlList);
        recyclerView.setAdapter(urlCardAdapter);
    }
}
