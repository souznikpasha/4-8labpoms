package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

public class DBActivity extends AppCompatActivity {
    //DB хранится между запусками
    //А история даже по переходу дохнет
    TextView dbContent;
    DatabaseManager databaseManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
        dbContent = findViewById(R.id.db_textView);
        databaseManager = new DatabaseManager(this);
        databaseManager.open();

        Button showDbButton = findViewById(R.id.show_db_button);
        showDbButton.setOnClickListener(v -> showDb());
        Button clearDbButton = findViewById(R.id.clear_db_button);
        clearDbButton.setOnClickListener(v -> clearDb());
        Button clearScreenButton = findViewById(R.id.clear_screen_button);
        clearScreenButton.setOnClickListener(v -> clearScreen());
    }
    private void showDb() {
        dbContent.setText(databaseManager.getAllAsText());
    }
    private void clearDb() {
        databaseManager.deleteAll();
    }
    private void clearScreen() {
        dbContent.setText("");
    }
}

