package com.aliendroid.sdkads.config;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.aliendroid.alienads.AdjustHelper;

import java.util.List;

public class AppsConfig {
    public static String ALIENSDKKEY = "uw277yqlu1hc";
    public static void TRACKING (Context context){
        AdjustHelper.Initialize(context, ALIENSDKKEY, AdjustHelper.AdjustEnv.ENVIRONMENT_PRODUCTION);

    }
}
