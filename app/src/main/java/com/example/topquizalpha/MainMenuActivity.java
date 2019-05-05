package com.example.topquizalpha;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import java.util.Arrays;
import java.util.List;

import io.paperdb.Paper;

public class MainMenuActivity extends AppCompatActivity {

    public static final String TOPIC_EXTRA_KEY = "TOPIC";
    public ImageButton ibProphets;
    public ImageButton ibMuhammad;
    public ImageButton ibQuran;
    public ImageButton ibSalat;
    public List<ImageButton> topicImageButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Paper.init(this);
        Paper.book().write(String.valueOf(R.id.ibProphets), true);

        ibProphets = findViewById(R.id.ibProphets);
        ibMuhammad = findViewById(R.id.ibMuhammad);
        ibQuran = findViewById(R.id.ibQuran);
        ibSalat = findViewById(R.id.ibSalat);

        topicImageButtons = Arrays.asList(
                ibProphets,
                ibMuhammad,
                ibQuran,
                ibSalat
        );

        for(ImageButton topic : topicImageButtons){
            topic.setImageResource(Paper.book().read(String.valueOf(topic.getId()), false) ?
                    R.drawable.ib_selector :
                    R.drawable.mosque_bw);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Paper.book().write(String.valueOf(R.id.ibProphets), true);

        for(ImageButton topic : topicImageButtons){
            topic.setImageResource(Paper.book().read(String.valueOf(topic.getId()), false) ?
                    R.drawable.ib_selector :
                    R.drawable.mosque_bw);
        }
    }

    public void onTopicClick(View view) {
        if (Paper.book().read(String.valueOf(view.getId()), false)) {
            Intent intent = new Intent(MainMenuActivity.this, PlayerActivity.class);
            intent.putExtra(TOPIC_EXTRA_KEY, view.getId());
            startActivity(intent);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("This topic is locked")
                    .setIcon(R.drawable.ic_mecca)
                    .setMessage("You must reach 100% of good answers at the previous topic first.")
                    .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .create().show();
        }
    }
}
