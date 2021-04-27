package com.example.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LottieAnimationView lottieAnimationView;
        TextView textView;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        lottieAnimationView =findViewById(R.id.lottie);
        textView =findViewById(R.id.textView);
        lottieAnimationView.animate().translationY(-1600).setDuration(1000).setStartDelay(4000);
        textView.animate().translationY(1400).setDuration(1000).setStartDelay(4000);
        getSupportActionBar().hide();
        Thread thread=new Thread(){
            public void run(){
                try{
                    sleep(1600);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                finally{
                    Intent intent=new Intent(Splash.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };thread.start();
    }
}