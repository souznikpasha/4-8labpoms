package com.example.myapplication.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.viewmodel.HistoryFacade;

public class DBActivity extends AppCompatActivity {
    //DB хранится между запусками
    //А история даже по переходу дохнет
    TextView dbContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
        dbContent = findViewById(R.id.db_textView);


        Button showDbButton = findViewById(R.id.show_db_button);
        showDbButton.setOnClickListener(v -> showDb());
        Button clearDbButton = findViewById(R.id.clear_db_button);
        clearDbButton.setOnClickListener(v -> clearDb());
        Button clearScreenButton = findViewById(R.id.clear_screen_button);
        clearScreenButton.setOnClickListener(v -> clearScreen());
    }
    private void showDb() {
        dbContent.setText(HistoryFacade.getAllAsString(getBaseContext()));
    }
    private void clearDb() {
        HistoryFacade.deleteAll(getBaseContext());
    }
    private void clearScreen() {
        dbContent.setText("");
    }
}
