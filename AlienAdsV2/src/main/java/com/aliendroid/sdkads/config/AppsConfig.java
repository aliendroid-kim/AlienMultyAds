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

import java.util.List;

public class AppsConfig {

    public static String appPromote = "ads_slot_02";
    public static String appName = "title";
    public static String appPackage = "ad_id";
    public static String appPreview = "preview";
    public static String ANALYSKEY = AESHelper.decrypt("T+jldPZgLshPb1sJGIme1tnT0tiG1I85x0V64nM5GGM=");
    public static String APPKEY = AESHelper.decrypt("2f8dUHXOScKGuDEEdG6mxA==");

    public static void openAdLink(Context context, String link) {
        if (!TextUtils.isEmpty(link) && link != null) {
            if (link.startsWith("http")) {
                openOnInternet(context, link);
            } else {
                openOnGooglePlayStore(context, link);
            }
        } else {
            setToast(context, "Failed to get Ad link.");
        }
    }

    public static void openOnGooglePlayStore(Context context, String packageName) {
        try {
            String GGMarket = "market://details?id=";
            Intent intentMarket = new Intent(Intent.ACTION_VIEW, Uri.parse(GGMarket + packageName));
            intentMarket.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intentMarket.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            try {
                intentMarket.setPackage("com.android.vending");
            } catch (Exception v) {
                v.printStackTrace();
            }
            context.startActivity(intentMarket);
        } catch (ActivityNotFoundException localActivityNotFoundException) {
            String GG_APPS = "https://play.google.com/store/apps/details?id=";
            Intent intentStore = new Intent(Intent.ACTION_VIEW, Uri.parse(GG_APPS + packageName));
            intentStore.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intentStore.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            try {
                intentStore.setPackage("com.android.vending");
            } catch (Exception v) {
                v.printStackTrace();
            }
            if (isIntentAvailable(context, intentStore)) {
                context.startActivity(intentStore);
            } else {
                setToast(context, "Failed to open Ad.");
            }

        }
    }

    public static void openOnInternet(Context context, String link) {
        try {
            Intent intentMarket = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
            intentMarket.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intentMarket.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(intentMarket);
        } catch (ActivityNotFoundException localActivityNotFoundException) {
            setToast(context, "Failed to open Ad.");

        }
    }


    public static boolean isIntentAvailable(Context context, Intent intent) {
        final PackageManager mgr = context.getPackageManager();
        @SuppressLint("QueryPermissionsNeeded")
        List<ResolveInfo> list = mgr.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }



    public static void setLog(String log) {
        Log.d("adPromote", log);
    }

    public static void setToast(Context context, String toast) {
        Toast.makeText(context, toast, Toast.LENGTH_SHORT).show();
    }


}
