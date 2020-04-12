package com.example.passive_clocknote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.os.Bundle;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<ExampleItem> mExampleList;
    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    String curr_date;
    String curr_time;
    private RecyclerView.LayoutManager mLayoutManager;
    TimePickerDialog timePickerDialogtotime, timePickerDialogfromtime;
    DatePickerDialog.OnDateSetListener onDateSetListener;
    EditText totime,fromtime,date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadData();
        buildRecyclerView();
        setdatetime();
        setInsertButton();
        //checkdateandtime();

        /*Button buttonSave = findViewById(R.id.button_save);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
                ArrayList<String> fromtimelist = new ArrayList<>();
                ArrayList<String> datelist= new ArrayList<>();
                ArrayList<String> totimetist= new ArrayList<>();
                //String user_date = String.valueOf(date.getText());
                //String from_time = String.valueOf(totime.getText());
                for(ExampleItem a:mExampleList){
                    fromtimelist.add(a.getLine2());
                    datelist.add(a.getLine4());
                    totimetist.add(a.getLine3());
                }
                Intent intent=new Intent(MainActivity.this,MyService.class);


                intent.putExtra("fromtime-list",fromtimelist);
                intent.putExtra("date-list",datelist);
                intent.putExtra("totime-list",totimetist);
                startService(intent);
                Toast.makeText(MainActivity.this, "Entry Saved", Toast.LENGTH_LONG).show();
            }
        });*/


    }


    /* public void checkdateandtime(){


        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat simpletime= new SimpleDateFormat("hh:mm a");
        curr_time = simpletime.format(calendar.getTime());
        System.out.println("Current time is:"+curr_time);
        curr_date = String.valueOf(simpleDateFormat.format(calendar.getTime()));
        ArrayList<String> datelist= new ArrayList<>();
        System.out.println("");
        String temp_date =curr_date;

        System.out.println("Current date"+curr_date);
        ArrayList<String> timelist = new ArrayList<>();

        for(ExampleItem a:mExampleList){
            timelist.add(a.getLine2());
            datelist.add(a.getLine4());
        }
        //System.out.println("date:"+datelist);

                    for (String temp : datelist) {
                        System.out.println("here");
                        if (curr_date.equals(temp)) {
                            for (String temp2 : timelist) {
                                if (curr_time.equals(temp2)) {
                                    System.out.println("true");
                                    final AudioManager audioManager = (AudioManager) getSystemService(getApplicationContext().AUDIO_SERVICE);
                                    audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                                }
                            }

                        }
                        ;
                    }


        }*/
        //System.out.println("time"+timelist);
       // if(timelist.contains(curr_time)&&datelist.contains(curr_date)){


       /* if (curr_date.equals(user_date)) {
            if(curr_time.equals(from_time))
            final AudioManager audioManager = (AudioManager) getSystemService(getApplicationContext().AUDIO_SERVICE);
            audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
        }*/



    public void setdatetime(){
        Calendar calendar=Calendar.getInstance();
        final int day=calendar.get(Calendar.DAY_OF_MONTH);
        final int month=calendar.get(Calendar.MONTH);
        final int year=calendar.get(Calendar.YEAR);
        totime = (EditText) findViewById(R.id.totime);
        fromtime =(EditText) findViewById(R.id.edittext_line_2);

        date=findViewById(R.id.date);
        totime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialogtotime= new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String ampm;

                        if(hourOfDay>=12){
                            hourOfDay=hourOfDay%12;
                            ampm="PM";

                        }

                        else {
                            ampm="AM";
                        }
                        if(hourOfDay==0){
                            hourOfDay+=12;
                        }

                        String formathour= ""+ hourOfDay;

                        String formatminute= ""+ minute;
                        if(hourOfDay < 10){
                            formathour="0"+ hourOfDay;
                        }
                        if(minute<10){
                            formatminute="0"+minute;
                        }
                        totime.setText(formathour + ":" + formatminute + " "+ ampm);

                    }
                },0,0,false);
                timePickerDialogtotime.show();
            }
        });
        fromtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialogfromtime =new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String ampm;
                        if(hourOfDay>=12){
                            ampm="PM";
                            hourOfDay=hourOfDay%12;
                        }
                        else {
                            ampm="AM";
                        }
                        if(hourOfDay==0)
                            hourOfDay+=12;

                        String formathour= ""+ hourOfDay;
                        String formatminute= ""+ minute;

                        if(hourOfDay < 10){
                            formathour="0"+ hourOfDay;
                        }
                        if(minute<10){
                            formatminute="0"+minute;
                        }

                        fromtime.setText(formathour+":"+formatminute+ " " + ampm);
                    }
                },0,0,false);
                timePickerDialogfromtime.show();
            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog= new DatePickerDialog(
                        MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month=month+1;
                        String formatmonth= "" + month;
                        String formatday= "" + day;
                        if(month<10){
                            formatmonth= "0"+ month;
                        }
                        if(day<10){
                           formatday ="0" + day;
                        }

                        String dt=formatmonth+ "/" +formatday+ "/" + year;
                        date.setText(dt);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mExampleList);
        editor.putString("task list", json);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<ExampleItem>>() {}.getType();
        mExampleList = gson.fromJson(json, type);

        if (mExampleList == null) {
            mExampleList = new ArrayList<>();

        }
    }

    private void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(mExampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
            @Override
            public void onGeneralClick(int position) {
                setGeneral(position);
            }

            @Override
            public void onDeleteClick(int position) {
                setDelete(position);
            }

            @Override
            public void onVibrateClick(int position) {
                setVibrate(position);
            }
        });

    }

    private void setInsertButton() {
        Button buttonInsert = findViewById(R.id.button_insert);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText evname = findViewById(R.id.edittext_line_1);
                //EditText fromtime = findViewById(R.id.edittext_line_2);
                //EditText totime = findViewById(R.id.totime);
                //EditText date = findViewById(R.id.date);
                String eventname=evname.getText().toString();
                String eventdate=date.getText().toString();
                String evtotime=totime.getText().toString();
                String evfromtime=fromtime.getText().toString();

                System.out.println("Date is"+date);


                if(!TextUtils.isEmpty(eventname)&&!TextUtils.isEmpty(eventdate)&&!TextUtils.isEmpty(evtotime)&&!TextUtils.isEmpty(evfromtime)){
                    insertItem(eventname, evfromtime, evtotime, eventdate);
                    evname.setText("");
                    fromtime.setText("");
                    totime.setText("");
                    date.setText("");

                    saveData();
                    ArrayList<String> fromtimelist = new ArrayList<>();
                    ArrayList<String> datelist= new ArrayList<>();
                    ArrayList<String> totimetist= new ArrayList<>();
                    //String user_date = String.valueOf(date.getText());
                    //String from_time = String.valueOf(totime.getText());
                    for(ExampleItem a:mExampleList){
                        fromtimelist.add(a.getLine2());
                        datelist.add(a.getLine4());
                        totimetist.add(a.getLine3());
                    }
                    Intent intent=new Intent(MainActivity.this,MyService.class);


                    intent.putExtra("fromtime-list",fromtimelist);
                    intent.putExtra("date-list",datelist);
                    intent.putExtra("totime-list",totimetist);
                    startService(intent);
                    Toast.makeText(MainActivity.this, "Entry Saved", Toast.LENGTH_LONG).show();
                    //Toast.makeText(MainActivity.this, "Please save your input now", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Enter valid values", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    private void insertItem(String evname, String from,String totime, String date) {
        mExampleList.add(new ExampleItem(evname, from,totime,date));

        mAdapter.notifyItemInserted(mExampleList.size());
        //System.out.println("List="+mExampleList.size());


    }

    public void setDelete(int position){
        mExampleList.remove(position);
        mAdapter.notifyItemChanged(position);
        mAdapter.notifyItemRemoved(position);
        saveData();
        ArrayList<String> fromtimelist = new ArrayList<>();
        ArrayList<String> datelist= new ArrayList<>();
        ArrayList<String> totimetist= new ArrayList<>();
        //String user_date = String.valueOf(date.getText());
        //String from_time = String.valueOf(totime.getText());
        for(ExampleItem a:mExampleList){
            fromtimelist.add(a.getLine2());
            datelist.add(a.getLine4());
            totimetist.add(a.getLine3());
        }
        Intent intent=new Intent(MainActivity.this,MyService.class);


        intent.putExtra("fromtime-list",fromtimelist);
        intent.putExtra("date-list",datelist);
        intent.putExtra("totime-list",totimetist);
        startService(intent);
        Toast.makeText(MainActivity.this, "Entry Saved", Toast.LENGTH_LONG).show();
    }
    public void setGeneral(int position){
        final AudioManager audioManager=(AudioManager) getSystemService(getApplicationContext().AUDIO_SERVICE);
        audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
    }
    public void setVibrate(int position){
        final AudioManager audioManager=(AudioManager) getSystemService(getApplicationContext().AUDIO_SERVICE);
        audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
    }

}
