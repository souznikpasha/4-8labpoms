package com.example.myapplication;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import static java.lang.Thread.sleep;

public class CounterService extends Service {

    MyTask task;

    public void onCreate() {
        super.onCreate();
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        PendingIntent pendingIntent = intent.getParcelableExtra(ServiceActivity.PENDING_INTENT_KEY);
        task = new MyTask(pendingIntent);
        startWork();
        return super.onStartCommand(intent, flags, startId);
    }

    public void onDestroy() {
        super.onDestroy();
        task.stop();
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    void startWork(){
        Thread thread = new Thread(task,"T1");
        thread.start();
    }


    private class MyTask implements Runnable {

        private boolean exit;
        private PendingIntent pendingIntent;

        MyTask(PendingIntent pendingIntent) {
            this.pendingIntent = pendingIntent;
        }

        @Override
        public void run() {
            talkToCreator(new Intent(), ServiceActivity.COUNTER_START);

            exit = false;

            final int VERY_MUCH = 100000;
            for(int i=0;i<VERY_MUCH && !exit;i++){

                talkToCreator(new Intent().putExtra(ServiceActivity.COUNTER_ANSWER_KEY, i),
                        ServiceActivity.COUNTER_ANSWER);

                try{
                    sleep(2000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                    break;
                }
            }

            talkToCreator(new Intent(), ServiceActivity.COUNTER_FINISH);
        }
        void stop(){
            exit = true;
        }

        void talkToCreator(Intent intent, int code){
            try {
                pendingIntent.send(CounterService.this, code, intent);
            }
            catch (PendingIntent.CanceledException e){
                e.printStackTrace();
            }
        }
    }
}
