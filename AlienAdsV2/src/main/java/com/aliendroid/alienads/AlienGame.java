package com.aliendroid.alienads;

import android.app.Activity;

import com.jambox.monetisation.JamboxAdsHelper;
import com.jambox.monetisation.WebviewObject;

public class AlienGame {
    public static WebviewObject webview = null;
    public static void LoadGame(Activity activity, String interstitialID, String rewardID,String bannerID ){
        JamboxAdsHelper.InitializeAds(activity, interstitialID, rewardID, bannerID);
    }
    public static void ShowGame(Activity activity, String gameID){
        if (webview != null) return;
        webview = new WebviewObject(activity, gameID);
        webview.StartWebview();


   }
    public static void  CloseGame(){
        if (webview == null)
            return;

        webview.CloseWebview();
        webview = null;
    }
}
