package com.aliendroid.alienads;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxAppOpenAd;
import com.applovin.sdk.AppLovinSdk;

public class ApplovinOpenAds
        implements LifecycleObserver, MaxAdListener, Application.ActivityLifecycleCallbacks
{
    private  MaxAppOpenAd appOpenAd;
    private  Context context;
    public static MyApplication myApplication;
    public ApplovinOpenAds(MyApplication myApplication) {
        ApplovinOpenAds.myApplication = myApplication;
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
        AppOpenManager(context);
    }

    public void AppOpenManager(Context context)
    {
        this.context = context;
        appOpenAd = new MaxAppOpenAd( "344b15a154e06223", context);
        appOpenAd.setListener( this );
        appOpenAd.loadAd();
    }

    private void showAdIfReady()
    {
        if ( appOpenAd == null || !AppLovinSdk.getInstance( context ).isInitialized() ) return;

        if ( appOpenAd.isReady() )
        {
            appOpenAd.showAd( "344b15a154e06223" );
        }
        else
        {
            appOpenAd.loadAd();
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart()
    {
        showAdIfReady();
    }

    @Override
    public void onAdLoaded(final MaxAd ad) {}
    @Override
    public void onAdLoadFailed(final String adUnitId, final MaxError error) {}
    @Override
    public void onAdDisplayed(final MaxAd ad) {}
    @Override
    public void onAdClicked(final MaxAd ad) {}

    @Override
    public void onAdHidden(final MaxAd ad)
    {
        appOpenAd.loadAd();
    }

    @Override
    public void onAdDisplayFailed(final MaxAd ad, final MaxError error)
    {
        appOpenAd.loadAd();
    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {

    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {

    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {

    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {

    }
}

