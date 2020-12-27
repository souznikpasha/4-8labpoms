package com.example.myapplication;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

public class ServiceActivity extends AppCompatActivity {


    public static final String PENDING_INTENT_KEY = "pending_intent";
    public static final String COUNTER_ANSWER_KEY = "pending_intent";

    private static final int COUNTER_SERVICE = 1;

    public static final int COUNTER_START = 1;
    public static final int COUNTER_ANSWER = 2;
    public static final int COUNTER_FINISH = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        Button start = findViewById(R.id.start_service_button);
        Button stop = findViewById(R.id.stop_service_button);
        start.setOnClickListener(v -> startService());
        stop.setOnClickListener(v -> stopService());
    }

    public void startService(){
        PendingIntent pendingIntent = createPendingResult(COUNTER_SERVICE, new Intent(), 0);

        Intent intent = new Intent(this, CounterService.class);
        intent.putExtra(PENDING_INTENT_KEY, pendingIntent);

        startService(intent);
    }

    public void stopService(){
        Intent intent = new Intent(this, CounterService.class);
        stopService(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == COUNTER_SERVICE) {
            switch (resultCode) {
                case COUNTER_START:
                    Toast.makeText(this, getResources().getString(R.string.service_started), Toast.LENGTH_SHORT).show();
                    break;
                case COUNTER_ANSWER:
                    int counter = data.getIntExtra(COUNTER_ANSWER_KEY, 0);
                    Toast.makeText(this, getResources().getString(R.string.counted_to, counter), Toast.LENGTH_SHORT).show();
                    break;
                case COUNTER_FINISH:
                    Toast.makeText(this, getResources().getString(R.string.service_stopped), Toast.LENGTH_SHORT).show();
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
