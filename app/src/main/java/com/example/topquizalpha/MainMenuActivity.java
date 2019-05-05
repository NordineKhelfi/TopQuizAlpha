package com.example.topquizalpha;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import butterknife.BindViews;
import butterknife.ButterKnife;
import io.paperdb.Paper;

public class MainMenuActivity extends AppCompatActivity {

    public static final String TOPIC_EXTRA_KEY = "TOPIC";

    @BindViews({R.id.ibProphets, R.id.ibMuhammad, R.id.ibQuran, R.id.ibSalat})
        List<ImageButton> topicImageButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        ButterKnife.bind(this);
        Paper.init(this);
        RefreshMainMenu();
    }

    @Override
    protected void onResume() {
        super.onResume();
        RefreshMainMenu();
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
                    .setPositiveButton("Okay", (dialog, which) -> dialog.dismiss())
                    .create().show();
        }
    }

    private void RefreshMainMenu(){
        Paper.book().write(String.valueOf(R.id.ibProphets), true);

        for(ImageButton topic : topicImageButtons){
            topic.setImageResource(Paper.book().read(String.valueOf(topic.getId()), false) ?
                    R.drawable.ib_selector :
                    R.drawable.mosque_bw);
        }
    }
}
