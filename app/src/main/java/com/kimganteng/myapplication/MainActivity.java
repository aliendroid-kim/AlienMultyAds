package com.kimganteng.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.aliendroid.alienads.AlienOpenAds;
import com.aliendroid.alienads.AliendroidBanner;
import com.aliendroid.alienads.AliendroidInitialize;
import com.aliendroid.alienads.AliendroidIntertitial;
import com.aliendroid.alienads.AliendroidNative;
import com.aliendroid.alienads.AliendroidReward;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AlienOpenAds.LoadOpenAds("");
        RelativeLayout layAds = findViewById(R.id.layAds);
        RelativeLayout layNative = findViewById(R.id.layNative);
        AliendroidInitialize.SelectAdsAdmob(this,"","3932965");
        AliendroidBanner.SmallBannerAdmob(this,layAds,"","ca-app-pub-3940256099942544/6300978111","","","","","","");
        AliendroidNative.SmallNativeMax(this,layNative,"","ca-app-pub-3940256099942544/2247696110","");

        AliendroidReward.LoadRewardAdmob(this,"","ca-app-pub-3940256099942544/5224354917","");
        AliendroidIntertitial.LoadIntertitialAdmob(this,"","ca-app-pub-3940256099942544/1033173712","","","","","","");

    }

    public void showreward(View view){
        //AliendroidIntertitial.ShowIntertitialAdmob(MainActivity.this,"","ca-app-pub-3940256099942544/1033173712","",0,"","","","","");
        AliendroidReward.ShowRewardAdmob(MainActivity.this,"","ca-app-pub-3940256099942544/5224354917","");
    }
}