package com.aliendroid.alienads;

import android.content.Context;

import com.adjust.sdk.Adjust;
import com.adjust.sdk.AdjustConfig;
import com.adjust.sdk.AdjustEvent;

public class AdjustHelper
{
    public static void Initialize(Context context, String appToken, AdjustEnv env)
    {
        String environment = AdjustConfig.ENVIRONMENT_PRODUCTION;
        if (env == AdjustEnv.ENVIRONMENT_SANDBOX)
            environment = AdjustConfig.ENVIRONMENT_SANDBOX;
        AdjustConfig config = new AdjustConfig(context, appToken, environment);
        Adjust.onCreate(config);
    }

    public static void TrackEvent(String eventId)
    {
        AdjustEvent event = new AdjustEvent(eventId);
        Adjust.trackEvent(event);
    }

    public static void onResume()
    {
        Adjust.onResume();
    }

    public static void onPause()
    {
        Adjust.onPause();
    }

    public enum AdjustEnv
    {
        ENVIRONMENT_SANDBOX,
        ENVIRONMENT_PRODUCTION
    }
}
