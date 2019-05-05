package com.example.topquizalpha;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.paperdb.Paper;

public class PlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        final EditText etPlayerName = findViewById(R.id.etName);
        final Button bPlay = findViewById(R.id.bPlay);

        int buttonId = getIntent().getIntExtra(MainMenuActivity.TOPIC_EXTRA_KEY, 0);

        switch (buttonId) {
            case R.id.ibProphets:
                Toast.makeText(this, "Prophets", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ibMuhammad:
                Toast.makeText(this, "SAWS", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ibQuran:
                Toast.makeText(this, "Quran", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ibSalat:
                Toast.makeText(this, "Salat", Toast.LENGTH_SHORT).show();
                break;
        }

        etPlayerName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                bPlay.setEnabled(count > 0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        bPlay.setOnClickListener(v -> {
            switch(etPlayerName.getText().toString()){
                case "SAWS":
                    Paper.book().write(String.valueOf(R.id.ibMuhammad), true);
                    Toast.makeText(PlayerActivity.this, "Congrats !", Toast.LENGTH_SHORT).show();
                    break;
                case "Salât":
                    Paper.book().write(String.valueOf(R.id.ibSalat), true);
                    Toast.makeText(PlayerActivity.this, "Congrats !", Toast.LENGTH_SHORT).show();
                    break;
                case "Quran":
                    Paper.book().write(String.valueOf(R.id.ibQuran), true);
                    Toast.makeText(PlayerActivity.this, "Congrats !", Toast.LENGTH_SHORT).show();
                    break;
                case "reset":
                    Paper.book().destroy();
                    Toast.makeText(PlayerActivity.this, "Reset.", Toast.LENGTH_SHORT).show();
                    break;
            }
        });
    }
}
