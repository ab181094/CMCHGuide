package com.example.amrit.cmchguide.Map;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.amrit.cmchguide.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void gotoMap(View view) {
        Intent intent = new Intent(MainActivity.this, MapActivity.class);
        startActivity(intent);
    }

    public void gotoMapTile(View view) {
        Intent intent = new Intent(MainActivity.this, MapTileActivity.class);
        startActivity(intent);
    }
}
