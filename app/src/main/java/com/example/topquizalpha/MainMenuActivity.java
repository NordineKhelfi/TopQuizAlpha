package com.example.topquizalpha;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainMenuActivity extends AppCompatActivity {

    public static final String TOPIC_EXTRA_KEY = "TOPIC";
    public ImageButton ibProphets;
    public ImageButton ibMuhammad;
    public ImageButton ibQuran;
    public ImageButton ibSalat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        ibProphets = findViewById(R.id.ibProphets);
        ibMuhammad = findViewById(R.id.ibMuhammad);
        ibQuran = findViewById(R.id.ibQuran);
        ibSalat = findViewById(R.id.ibSalat);
    }

    public void onTopicClick(View view) {
        Intent intent = new Intent(MainMenuActivity.this, PlayerActivity.class);
        intent.putExtra(TOPIC_EXTRA_KEY, view.getId());
        startActivity(intent);
    }
}
