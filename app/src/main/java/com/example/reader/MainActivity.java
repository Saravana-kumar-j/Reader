package com.example.reader;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import adapter.AdapterView;
import fragment.FragmentHome;
import fragment.FragmentNotification;
import fragment.FragmentProfile;

public class MainActivity extends AppCompatActivity {
    ViewPager2 pageMain;
    FirebaseAuth auth;
    Button button;
    TextView textView;
    FirebaseUser user;
    ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    BottomNavigationView bottomNav;

    // For back press handling
    private boolean doubleBackToExitPressedOnce = false;
    private final Handler handler = new Handler();
    private final Runnable resetBackPressFlag = () -> doubleBackToExitPressedOnce = false;

    @Override
    protected void onStart() {
        super.onStart();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        if (user == null) {
            startActivity(new Intent(this, Welcome_activity.class));
            finish();
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        button = findViewById(R.id.btn_logout);
        textView = findViewById(R.id.user_details);
        user = auth.getCurrentUser();

        // Pager Fragment
        pageMain = findViewById(R.id.pagerMain);
        fragmentArrayList.add(new FragmentHome());
        fragmentArrayList.add(new FragmentNotification());
        fragmentArrayList.add(new FragmentProfile());
        bottomNav = findViewById(R.id.bottomNav);

        AdapterView adapterView = new AdapterView(this, fragmentArrayList);

        pageMain.setAdapter(adapterView);
        pageMain.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        bottomNav.setSelectedItemId(R.id.itHome);
                        break;
                    case 1:
                        bottomNav.setSelectedItemId(R.id.itNotification);
                        break;
                    case 2:
                        bottomNav.setSelectedItemId(R.id.itProfile);
                        break;
                }
                super.onPageSelected(position);
            }
        });

        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.itHome) {
                    pageMain.setCurrentItem(0);
                } else if (id == R.id.itNotification) {
                    pageMain.setCurrentItem(1);
                } else if (id == R.id.itProfile) {
                    pageMain.setCurrentItem(2);
                }
                return true;
            }
        });

        // Uncomment and implement logout functionality if needed
        /*
        if (user != null) {
            textView.setText(user.getEmail());
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), Login_activity.class);
                startActivity(intent);
                finish();
            }
        });
        */
    }

   
    @Override
    public void onBackPressed() {
        if (pageMain.getCurrentItem() == 0) { // Check if we are on the Home Fragment
            if (doubleBackToExitPressedOnce) {
                exitApplication();
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();

            handler.postDelayed(resetBackPressFlag, 2000); // Reset the flag after 2 seconds
        } else {
            super.onBackPressed();
        }
    }

    private void exitApplication() {
        finishAffinity();
    }

}
