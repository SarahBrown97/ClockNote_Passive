package com.example.passive_clocknote;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {
    Context context;
    ArrayList<String> fromtimelist = new ArrayList<>();
    ArrayList<String> datelist = new ArrayList<>();
    ArrayList<String> totimelist = new ArrayList<>();
    //Intent int_final = new Intent();
    public MyService(Context applicationContext) {
        super();
        context = applicationContext;

        Log.i("HERE", "here service created!");
    }

    public MyService(){

    }
    String curr_time,curr_date;
    public int counter = 0;


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        super.onStartCommand(intent, flags, startId);
         fromtimelist= intent.getStringArrayListExtra("fromtime-list");
         datelist=intent.getStringArrayListExtra("date-list");
         totimelist=intent.getStringArrayListExtra("totime-list");
        //System.out.println("Without service" + wtotimelist);
        //startTimer(fromtimelist,datelist,totimelist);
        startTimer();

        return START_STICKY;
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
        Intent broadcastIntent = new Intent("ac.in.ActivityRecognition.RestartSensor");
        sendBroadcast(broadcastIntent);
        stoptimertask();
    }
    private Timer timer;
    private TimerTask timerTask;

    //public void startTimer(ArrayList<String> fromtimelist, ArrayList<String> datelist, ArrayList<String> totimelist) {
    public void startTimer() {
        //set a new Timer

        timer = new Timer();

        //initialize the TimerTask's job
        //initializeTimerTask(fromtimelist,datelist,totimelist);
        initializeTimerTask();
        //schedule the timer, to wake up every 1 second
        timer.schedule(timerTask, 1000, 60000); //
    }

    //public void initializeTimerTask( ArrayList<String> fromtimelist,  ArrayList<String> datelist, ArrayList<String> totimelist) {
    public void initializeTimerTask() {
        timerTask = new TimerTask() {
            public void run() {
                Log.i("in timer", "in timer ++++  " + (counter++));

                //System.out.println("With service" + totimelist);
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
                SimpleDateFormat simpletime= new SimpleDateFormat("hh:mm a");
                curr_time = simpletime.format(calendar.getTime());
                System.out.println("Current time is:"+curr_time);

                curr_date = String.valueOf(simpleDateFormat.format(calendar.getTime()));
                System.out.println("Current date is"+curr_date);
                //ArrayList<String> fromtimelist= intent.getStringArrayListExtra("time-list");
                //ArrayList<String> datelist=intent.getStringArrayListExtra("date-list");
                //ArrayList<String> totimelist=intent.getStringArrayListExtra("from-list");
                System.out.println("time="+totimelist);
                System.out.println("date="+datelist);
                for(String temp:datelist){
                    if (curr_date.equals(temp)){
                        System.out.println("here in dl");
                        for(String temp2:fromtimelist){
                            System.out.println("here in tl");
                            if (curr_time.equals(temp2)){
                                AudioManager audioManager = (AudioManager) getSystemService(getApplicationContext().AUDIO_SERVICE);
                                audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                                System.out.println("here in silent mode");
                            }
                        }
                    }
                }
                for(String temp3:datelist){
                    if(curr_date.equals(temp3)){
                        for(String temp4:totimelist){
                            if(curr_time.equals(temp4)){
                                AudioManager audioManager = (AudioManager) getSystemService(getApplicationContext().AUDIO_SERVICE);
                                audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                                System.out.println("here in general mode");
                            }
                        }
                    }

                }
            }
        };
    }

    public void stoptimertask() {
        //stop the timer, if it's not already null
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
