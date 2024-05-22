package com.example.reader;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class Add_url extends AppCompatActivity {
    private TextInputEditText websiteNameText, websiteLinkText;
    private Button addUrlBtn, viewUrlsBtn;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_url);

        //initialising all variables
        websiteNameText = findViewById(R.id.website_name);
        websiteLinkText = findViewById(R.id.website_link);
        addUrlBtn = findViewById(R.id.button); // Referencing the button correctly
        viewUrlsBtn = findViewById(R.id.view_urls_button);
        dbHandler = new DBHandler(Add_url.this);

        addUrlBtn.setOnClickListener(v -> {
            String websiteName = Objects.requireNonNull(websiteNameText.getText()).toString();
            String websiteLink = Objects.requireNonNull(websiteLinkText.getText()).toString();

            if(websiteName.isEmpty() || websiteLink.isEmpty()){
                Toast.makeText(Add_url.this, "Please enter all the Data.", Toast.LENGTH_SHORT).show();
                return;
            }
            dbHandler.addUrl(websiteName, websiteLink);
            Toast.makeText(Add_url.this, "Database has been Updated.", Toast.LENGTH_SHORT).show();
            websiteNameText.setText("");
            websiteLinkText.setText("");
        });

        viewUrlsBtn.setOnClickListener(v -> {
            Intent intent = new Intent(Add_url.this, ViewUrlsActivity.class);
            startActivity(intent);
        });
    }
}
