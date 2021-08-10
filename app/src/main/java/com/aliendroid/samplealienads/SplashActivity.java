package com.aliendroid.samplealienads;



import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import androidx.appcompat.app.AppCompatActivity;

import com.aliendroid.alienads.AlienOpenAds;
import com.aliendroid.alienads.AliendroidInitialize;


public class SplashActivity extends AppCompatActivity {
    private static final int REQUEST = 112;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        AliendroidInitialize.SelectAds(SplashActivity.this,"ADMOB",
                "");
        AlienOpenAds.LoadOpenAds("ca-app-pub-3940256099942544/3419835294");



        new CountDownTimer(10000, 1000) {
                @Override
                public void onFinish() {

                        Intent intent = new Intent(getBaseContext(),MainActivity.class);
                        startActivity(intent);
                        finish();


                }
                @Override
                public void onTick(long millisUntilFinished) {

                }
            }.start();

    }


}
