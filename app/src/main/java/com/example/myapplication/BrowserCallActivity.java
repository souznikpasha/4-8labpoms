package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

public class BrowserCallActivity extends AppCompatActivity {

    EditText browserLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser_call);

        browserLink = findViewById(R.id.browser_link_text);

        Button browserCall = findViewById(R.id.open_browser_button);
        browserCall.setOnClickListener(v -> openBrowser());
    }


    public void openBrowser(){
        String link = browserLink.getText().toString();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(link));
        startActivity(intent);
    }
    //Ваша служба на каждое срабатывание расчитывало новое число фибаначи из предыд
}
