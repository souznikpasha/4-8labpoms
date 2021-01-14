package com.example.myapplication.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.viewmodel.FileProcessor;

public class FileActivity extends AppCompatActivity {
    TextView fileContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);

        Button fileButton = findViewById(R.id.show_file_button);
        fileButton.setOnClickListener(v -> loadFileContents());
        fileContent = findViewById(R.id.file_textView);
    }

    private void loadFileContents() {
        fileContent.setText(FileProcessor.loadFileContent(getString(R.string.log_file_name)));
    }
}
