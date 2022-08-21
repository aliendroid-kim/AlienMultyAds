package com.kimganteng.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.aliendroid.alienads.AlienGDPR;
import com.aliendroid.alienads.AlienOpenAds;
import com.aliendroid.alienads.AliendroidBanner;
import com.aliendroid.alienads.AliendroidInitialize;
import com.aliendroid.alienads.AliendroidIntertitial;
import com.aliendroid.alienads.AliendroidMediumBanner;
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
        AlienGDPR.loadGdpr(this,"APPLOVIN-M",false);
        AliendroidInitialize.SelectAdsApplovinMax(this,"","85460dcd");
        //AliendroidNative.SmallNativeAdmob(this,layAds,"","ca-app-pub-3940256099942544/2247696110","",
                //"","","","","");
        AliendroidNative.MediumNativeMax(this,layNative,"","DefaultBanner","");
        AliendroidBanner.SmallBannerApplovinMax(this,layAds,"","Home_Screen","");

        AliendroidReward.LoadRewardApplovinMax(this,"APPLOVIN-D","ca-app-pub-3940256099942544/5224354917","");
       // AliendroidIntertitial.LoadIntertitialApplovinMax(this,"","DefaultInterstitial","");

    }

    public void showreward(View view){

        //AliendroidIntertitial.ShowIntertitialApplovinMax(MainActivity.this,"","DefaultInterstitial","",0);
        AliendroidReward.ShowRewardApplovinMax(MainActivity.this,"APPLOVIN-D","ca-app-pub-3940256099942544/5224354917","");
    }
}