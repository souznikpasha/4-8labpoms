package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class HistoryListActivity extends AppCompatActivity {

    HistoryListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_list);

        Intent intent = getIntent();

        adapter = new HistoryListAdapter();
        if(intent!=null && intent.hasExtra(MainActivity.HISTORY_KEY)){
            adapter.initialize(intent.getParcelableArrayListExtra(MainActivity.HISTORY_KEY));
        }

        RecyclerView recyclerView = findViewById(R.id.historyList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
