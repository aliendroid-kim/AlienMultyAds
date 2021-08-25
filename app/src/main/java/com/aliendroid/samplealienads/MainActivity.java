package com.aliendroid.samplealienads;

import static com.aliendroid.samplealienads.SettingAds.ADMOB_BANNER;
import static com.aliendroid.samplealienads.SettingAds.ADMOB_INTER;
import static com.aliendroid.samplealienads.SettingAds.APPLOVIN_BANNER;
import static com.aliendroid.samplealienads.SettingAds.APPLOVIN_INTERS;
import static com.aliendroid.samplealienads.SettingAds.BACKUP_ADS;
import static com.aliendroid.samplealienads.SettingAds.BACKUP_ADS_BANNER;
import static com.aliendroid.samplealienads.SettingAds.BACKUP_ADS_INTERTITIAL;
import static com.aliendroid.samplealienads.SettingAds.BANNER_MOPUB;
import static com.aliendroid.samplealienads.SettingAds.COUNTER;
import static com.aliendroid.samplealienads.SettingAds.HIGH_PAYING_KEYWORD1;
import static com.aliendroid.samplealienads.SettingAds.HIGH_PAYING_KEYWORD2;
import static com.aliendroid.samplealienads.SettingAds.HIGH_PAYING_KEYWORD3;
import static com.aliendroid.samplealienads.SettingAds.HIGH_PAYING_KEYWORD4;
import static com.aliendroid.samplealienads.SettingAds.HIGH_PAYING_KEYWORD5;
import static com.aliendroid.samplealienads.SettingAds.INITIALIZE_SDK;
import static com.aliendroid.samplealienads.SettingAds.INITIALIZE_SDK_BACKUPADS;
import static com.aliendroid.samplealienads.SettingAds.INTERVAL;
import static com.aliendroid.samplealienads.SettingAds.INTER_MOPUB;
import static com.aliendroid.samplealienads.SettingAds.IRON_APPID;
import static com.aliendroid.samplealienads.SettingAds.IRON_BANNER;
import static com.aliendroid.samplealienads.SettingAds.IRON_INTERTITIAL;
import static com.aliendroid.samplealienads.SettingAds.MAIN_ADS_BANNER;
import static com.aliendroid.samplealienads.SettingAds.MAIN_ADS_INTERTITIAL;
import static com.aliendroid.samplealienads.SettingAds.MAIN_ADS_REWARDS;
import static com.aliendroid.samplealienads.SettingAds.MAX_BANNER;
import static com.aliendroid.samplealienads.SettingAds.MAX_INTER;
import static com.aliendroid.samplealienads.SettingAds.NATIVE_ADS_ADMOB;
import static com.aliendroid.samplealienads.SettingAds.SELECT_ADS;
import static com.aliendroid.samplealienads.SettingAds.STARTAPPID;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.aliendroid.alienads.AlienGDPR;
import com.aliendroid.alienads.AlienOpenAds;
import com.aliendroid.alienads.AliendroidBanner;
import com.aliendroid.alienads.AliendroidInitialize;
import com.aliendroid.alienads.AliendroidIntertitial;
import com.aliendroid.alienads.AliendroidNative;
import com.aliendroid.alienads.AliendroidReward;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RelativeLayout layAds = findViewById(R.id.layAds);
        FrameLayout nativeads = findViewById(R.id.laynative);
        RelativeLayout layAdsmall = findViewById(R.id.laysAdsmall);
        AlienGDPR.loadGdpr(MainActivity.this, SELECT_ADS, true);

        Spinner spinner = (Spinner) findViewById(R.id.spinner2);
        Button banner = findViewById(R.id.banner);
        Button initial = findViewById(R.id.initilize);
        Button interShow = findViewById(R.id.interstShow);
        TextView interval = findViewById(R.id.intervalText);
        Button loadInter = findViewById(R.id.loadInters);


        interval.setText(String.valueOf(COUNTER));


        initial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String SEL_ADS = spinner.getSelectedItem().toString();
                Toast.makeText(MainActivity.this, "Initialize " + spinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                switch (SEL_ADS) {
                    default:
                        AliendroidInitialize.SelectAds(MainActivity.this, SEL_ADS, "");
                        break;
                    case "STARTAPP":
                        AliendroidInitialize.SelectAds(MainActivity.this, SEL_ADS, STARTAPPID);
                        break;
                }

            }
        });

        loadInter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                COUNTER = 0;
                interval.setText(String.valueOf(COUNTER));

                Toast.makeText(MainActivity.this, "Inters " + spinner.getSelectedItem().toString() + " Will Load ", Toast.LENGTH_SHORT).show();
                String SEL_ADS = spinner.getSelectedItem().toString();


                switch (SEL_ADS) {
                    case "ADMOB":
                        AliendroidIntertitial.LoadIntertitial(MainActivity.this, SEL_ADS, ADMOB_INTER,HIGH_PAYING_KEYWORD1,
                                HIGH_PAYING_KEYWORD2, HIGH_PAYING_KEYWORD3, HIGH_PAYING_KEYWORD4, HIGH_PAYING_KEYWORD5);
                        break;
                    case "APPLOVIN-M":
                        AliendroidIntertitial.LoadIntertitial(MainActivity.this,SEL_ADS,MAX_INTER,HIGH_PAYING_KEYWORD1,
                                HIGH_PAYING_KEYWORD2, HIGH_PAYING_KEYWORD3, HIGH_PAYING_KEYWORD4, HIGH_PAYING_KEYWORD5);
                        break;
                    case "MOPUB":
                        AliendroidIntertitial.LoadIntertitial(MainActivity.this,SEL_ADS,INTER_MOPUB,HIGH_PAYING_KEYWORD1,
                                HIGH_PAYING_KEYWORD2, HIGH_PAYING_KEYWORD3, HIGH_PAYING_KEYWORD4, HIGH_PAYING_KEYWORD5);
                        break;
                    case "IRON":
                        AliendroidIntertitial.LoadIntertitial(MainActivity.this,SEL_ADS,IRON_APPID,HIGH_PAYING_KEYWORD1,
                                HIGH_PAYING_KEYWORD2, HIGH_PAYING_KEYWORD3, HIGH_PAYING_KEYWORD4, HIGH_PAYING_KEYWORD5);
                        break;
                    case "STARTAPP":
                        AliendroidIntertitial.LoadIntertitial(MainActivity.this,SEL_ADS,STARTAPPID,HIGH_PAYING_KEYWORD1,
                                HIGH_PAYING_KEYWORD2, HIGH_PAYING_KEYWORD3, HIGH_PAYING_KEYWORD4, HIGH_PAYING_KEYWORD5);
                        break;
                    case "APPLOVIN-D":
                        AliendroidIntertitial.LoadIntertitial(MainActivity.this,SEL_ADS,APPLOVIN_INTERS,HIGH_PAYING_KEYWORD1,
                                HIGH_PAYING_KEYWORD2, HIGH_PAYING_KEYWORD3, HIGH_PAYING_KEYWORD4, HIGH_PAYING_KEYWORD5);
                        break;
                }
            }
        });
        interShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Inters " + spinner.getSelectedItem().toString() + " Will Show ", Toast.LENGTH_SHORT).show();
                String SEL_ADS = spinner.getSelectedItem().toString();

                if (COUNTER <= INTERVAL) {
                    COUNTER++;
                } else {
                    COUNTER = 0;
                }
                interval.setText(String.valueOf(COUNTER));

                switch (SEL_ADS) {
                    case "ADMOB":
                        AliendroidIntertitial.ShowIntertitial(MainActivity.this, SEL_ADS, ADMOB_INTER, INTERVAL, HIGH_PAYING_KEYWORD1,
                                HIGH_PAYING_KEYWORD2, HIGH_PAYING_KEYWORD3, HIGH_PAYING_KEYWORD4, HIGH_PAYING_KEYWORD5);
                        break;
                    case "IRON":
                        AliendroidIntertitial.ShowIntertitial(MainActivity.this, SEL_ADS, IRON_INTERTITIAL, INTERVAL, HIGH_PAYING_KEYWORD1,
                                HIGH_PAYING_KEYWORD2, HIGH_PAYING_KEYWORD3, HIGH_PAYING_KEYWORD4, HIGH_PAYING_KEYWORD5);
                        break;
                    case "APPLOVIN-M":
                        AliendroidIntertitial.ShowIntertitial(MainActivity.this,SEL_ADS,MAX_INTER,INTERVAL,HIGH_PAYING_KEYWORD1,
                                HIGH_PAYING_KEYWORD2, HIGH_PAYING_KEYWORD3, HIGH_PAYING_KEYWORD4, HIGH_PAYING_KEYWORD5);
                        break;
                }
            }
        });
        banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Selected " + spinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();

                String SEL_ADS = spinner.getSelectedItem().toString();

                switch (SEL_ADS) {
                    case "ADMOB":
                        layAds.removeAllViews();
                        AliendroidBanner.SmallBanner(MainActivity.this, layAds, SEL_ADS, ADMOB_BANNER, HIGH_PAYING_KEYWORD1,
                                HIGH_PAYING_KEYWORD2, HIGH_PAYING_KEYWORD3, HIGH_PAYING_KEYWORD4, HIGH_PAYING_KEYWORD5);
                        break;
                    case "APPLOVIN-M":
                        layAds.removeAllViews();
                        AliendroidBanner.SmallBanner(MainActivity.this, layAds, SEL_ADS, MAX_BANNER, HIGH_PAYING_KEYWORD1,
                                HIGH_PAYING_KEYWORD2, HIGH_PAYING_KEYWORD3, HIGH_PAYING_KEYWORD4, HIGH_PAYING_KEYWORD5);
                        break;
                    case "MOPUB":
                        layAds.removeAllViews();
                        AliendroidBanner.SmallBanner(MainActivity.this, layAds, SEL_ADS, BANNER_MOPUB, HIGH_PAYING_KEYWORD1,
                                HIGH_PAYING_KEYWORD2, HIGH_PAYING_KEYWORD3, HIGH_PAYING_KEYWORD4, HIGH_PAYING_KEYWORD5);
                        break;
                    case "IRON":
                        layAds.removeAllViews();
                        AliendroidBanner.SmallBanner(MainActivity.this, layAds, SEL_ADS, IRON_BANNER, HIGH_PAYING_KEYWORD1,
                                HIGH_PAYING_KEYWORD2, HIGH_PAYING_KEYWORD3, HIGH_PAYING_KEYWORD4, HIGH_PAYING_KEYWORD5);
                        break;
                    case "STARTAPP":
                        layAds.removeAllViews();
                        AliendroidBanner.SmallBanner(MainActivity.this, layAds, SEL_ADS, STARTAPPID, HIGH_PAYING_KEYWORD1,
                                HIGH_PAYING_KEYWORD2, HIGH_PAYING_KEYWORD3, HIGH_PAYING_KEYWORD4, HIGH_PAYING_KEYWORD5);
                        break;
                    case "APPLOVIN-D":
                        layAds.removeAllViews();
                        AliendroidBanner.SmallBanner(MainActivity.this, layAds, SEL_ADS, "", HIGH_PAYING_KEYWORD1,
                                HIGH_PAYING_KEYWORD2, HIGH_PAYING_KEYWORD3, HIGH_PAYING_KEYWORD4, HIGH_PAYING_KEYWORD5);
                        break;
                }
            }
        });

        /*AliendroidBanner.MediumBanner(MainActivity.this, layAds,SettingAds.SELECT_ADS, SettingAds.MAIN_ADS_BANNER,HIGH_PAYING_KEYWORD1
        ,HIGH_PAYING_KEYWORD2,HIGH_PAYING_KEYWORD3,HIGH_PAYING_KEYWORD4,HIGH_PAYING_KEYWORD5);
        AliendroidBanner.SmallBanner(MainActivity.this, layAdsmall,SettingAds.SELECT_ADS, SettingAds.MAIN_ADS_BANNER,HIGH_PAYING_KEYWORD1
                ,HIGH_PAYING_KEYWORD2,HIGH_PAYING_KEYWORD3,HIGH_PAYING_KEYWORD4,HIGH_PAYING_KEYWORD5);
        AliendroidIntertitial.LoadIntertitial(MainActivity.this, SettingAds.SELECT_ADS, SettingAds.MAIN_ADS_INTERTITIAL,HIGH_PAYING_KEYWORD1
                ,HIGH_PAYING_KEYWORD2,HIGH_PAYING_KEYWORD3,HIGH_PAYING_KEYWORD4,HIGH_PAYING_KEYWORD5 );
        AliendroidNative.SmallNativeAdmob(MainActivity.this,SELECT_ADS, SettingAds.BACKUP_ADS, nativeads, NATIVE_ADS_ADMOB,MAIN_ADS_BANNER, HIGH_PAYING_KEYWORD1
                ,HIGH_PAYING_KEYWORD2,HIGH_PAYING_KEYWORD3,HIGH_PAYING_KEYWORD4,HIGH_PAYING_KEYWORD5);
        AliendroidReward.LoadReward(MainActivity.this, SELECT_ADS, MAIN_ADS_REWARDS );
        AlienOpenAds.ShowOpen(MainActivity.this);
         */

        switch (SELECT_ADS) {
            case "ADMOB":
                AliendroidBanner.SmallBannerAdmob(MainActivity.this, layAds, BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER, HIGH_PAYING_KEYWORD1,
                        HIGH_PAYING_KEYWORD2, HIGH_PAYING_KEYWORD3, HIGH_PAYING_KEYWORD4, HIGH_PAYING_KEYWORD5);
                AliendroidIntertitial.LoadIntertitialAdmob(MainActivity.this, BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL, HIGH_PAYING_KEYWORD1,
                        HIGH_PAYING_KEYWORD2, HIGH_PAYING_KEYWORD3, HIGH_PAYING_KEYWORD4, HIGH_PAYING_KEYWORD5);
                AlienOpenAds.ShowOpen(MainActivity.this);
                break;
            case "APPLOVIN-M":
                AliendroidBanner.SmallBannerApplovinMax(MainActivity.this, layAds, BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER);
                AliendroidIntertitial.LoadIntertitialApplovinMax(MainActivity.this, BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL);
                break;
            case "APPLOVIN-D":
                AliendroidBanner.SmallBannerApplovinDis(MainActivity.this, layAds, BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER);
                AliendroidIntertitial.LoadIntertitialApplovinDis(MainActivity.this, BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL);
                break;
            case "MOPUB":
                AliendroidBanner.SmallBannerMopub(MainActivity.this, layAds, BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER);
                AliendroidIntertitial.LoadIntertitialMopub(MainActivity.this, BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL);
                break;
            case "STARTAPP":
                AliendroidBanner.SmallBannerStartApp(MainActivity.this, layAds, BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER);
                AliendroidIntertitial.LoadIntertitialStartApp(MainActivity.this, BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL);
                break;
            case "IRON":
                AliendroidBanner.SmallBannerIron(MainActivity.this, layAds, BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER);
                AliendroidIntertitial.LoadIntertitialIron(MainActivity.this, BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL);
                break;


        }


    }

    public void munculiklan(View view) {


        switch (SELECT_ADS) {
            case "ADMOB":
                AliendroidIntertitial.ShowIntertitialAdmob(MainActivity.this, BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL, INTERVAL,
                        HIGH_PAYING_KEYWORD1, HIGH_PAYING_KEYWORD2, HIGH_PAYING_KEYWORD3, HIGH_PAYING_KEYWORD4, HIGH_PAYING_KEYWORD5);
                break;
            case "APPLOVIN-D":
                AliendroidIntertitial.ShowIntertitialApplovinDis(MainActivity.this, BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL, INTERVAL);
                break;
            case "APPLOVIN-M":
                AliendroidIntertitial.ShowIntertitialApplovinMax(MainActivity.this, BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL, INTERVAL);
                break;
            case "IRON":
                AliendroidIntertitial.ShowIntertitialIron(MainActivity.this, BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL, INTERVAL);
                break;
            case "MOPUB":
                AliendroidIntertitial.ShowIntertitialMopub(MainActivity.this, BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL, INTERVAL);
                break;
            case "STARTAPP":
                AliendroidIntertitial.ShowIntertitialSartApp(MainActivity.this, BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL, INTERVAL);
                break;
        }


    }

    public void munculreward(View view) {
        AliendroidReward.ShowReward(MainActivity.this, SELECT_ADS, MAIN_ADS_REWARDS);

    }

    public void onResume() {
        super.onResume();
        if (AliendroidReward.unlockreward) {
            Toast.makeText(getApplicationContext(), "OK Berhasil", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(getApplicationContext(), "gagal", Toast.LENGTH_LONG).show();
        }
    }


}