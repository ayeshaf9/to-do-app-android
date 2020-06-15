package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Intent secondActivity = new Intent(this,SecondActivity.class );

        //Display the Start up screen for 2.5 seconds and then call the Second Activity with the TO DO list
        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(2500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                        startActivity(secondActivity);
                        finish();
                    }
                }
            };
         timer.start();
        }
    }
