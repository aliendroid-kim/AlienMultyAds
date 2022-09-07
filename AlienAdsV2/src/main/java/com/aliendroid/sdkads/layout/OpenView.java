package com.aliendroid.sdkads.layout;

import static android.content.Context.DOWNLOAD_SERVICE;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.DownloadManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.URLUtil;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;


import com.aliendroid.alienads.BuildConfig;
import com.aliendroid.alienads.R;
import com.aliendroid.sdkads.cennections.ConnectionPromote;
import com.aliendroid.sdkads.config.AppsConfig;
import com.aliendroid.sdkads.interfaces.OnClosed;
import com.aliendroid.sdkads.interfaces.OnOpenViewAdListener;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

public class OpenView extends Dialog {
    private final Activity activity;
    private RelativeLayout close;
    private RelativeLayout install;
    private RelativeLayout  iconProgress;
    private WebView mWebView;
    StringBuilder blocklist;
    String loddnormallist= "0";
    private TextView name;
    private OnOpenViewAdListener onOpenViewAdListener;
    private OnClosed onClosed;

    String APPID;

    public OpenView(@NonNull Activity activity, String appID) {
        super(activity, R.style.InterstitialStyle);
        this.activity = activity;
        setContentView(R.layout.openads);
        setCancelable(false);
        initializeData();
        initializeUI();
        this.APPID = appID;
    }

    private void initializeData() {
        onLoadAdListener();
    }

    private void onLoadAdListener() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (ConnectionPromote.adList != null && !ConnectionPromote.adList.isEmpty()) {

                    if (onOpenViewAdListener != null) {
                        onOpenViewAdListener.onInterstitialAdLoaded();
                    }
                } else {
                    if (onOpenViewAdListener != null) {
                        onOpenViewAdListener.onInterstitialAdFailedToLoad("Ad Loaded, but data base of ad wrong ! please check your file.");
                    }
                }
            }
        }, 500);
    }


    private void initializeUI() {
        close = findViewById(R.id.interstitial_close);
        install = findViewById(R.id.interstitial_install);
        iconProgress = (RelativeLayout) findViewById(R.id.inter_icon_progress);
        name = findViewById(R.id.interstitial_app_name);
        mWebView = findViewById(R.id.webloads);
        generateInstallButton();

    }

    private void startCountDown() {
           // close.setClickable(false);
    }

    private void applyStyle(int style) {
        switch (style) {
            case 1:
                setContentView(R.layout.openads);
                break;
            case 2:
                setContentView(R.layout.openads);
                break;
            default:
                setContentView(R.layout.openads);
                break;
        }
    }

    //check the Ad List is not empty
    public boolean isAdLoaded() {
        if (ConnectionPromote.adList != null) {
            return !ConnectionPromote.adList.isEmpty();
        }
        //the ad list is empty.
        if (onOpenViewAdListener != null) {
            onOpenViewAdListener.onInterstitialAdFailedToLoad("Failed to show : No Ad");
        }
        return false;

    }

    @Override
    public void show() {
        super.show();
        buildInterstitial();
    }

    private void dismissAd() {
        try {
            if (isShowing()) {
                if (onClosed != null) {
                    onClosed.onAdClosed();
                }
                dismiss();
                if (BuildConfig.DEBUG) {
                    AppsConfig.setLog("The AlienInterstitial was dismiss.");
                }
            }
        } catch (Exception e) {
            cancel();
            if (BuildConfig.DEBUG) {
                AppsConfig.setLog("AlienInterstitial Dismiss methode caused a crush : " + e.getMessage());
            }
        }
    }


    private void buildInterstitial() {

        try {

            if (ConnectionPromote.adList != null && !ConnectionPromote.adList.isEmpty()) {
                int size = ConnectionPromote.adList.size();
                Random random = new Random();
                int interstitialIndex = random.nextInt(size);
                updateInterstitial(interstitialIndex);
                startCountDown();
                if (BuildConfig.DEBUG) {
                    AppsConfig.setLog("Build AlienInterstitial...");
                }
            } else {
                //the ad list is empty.
                if (onOpenViewAdListener != null) {
                    onOpenViewAdListener.onInterstitialAdFailedToLoad("The Ad list is empty ! please check your json file.");
                }
                if (BuildConfig.DEBUG) {
                    AppsConfig.setLog("Failed to build AlienInterstitial cause : the List of ad is empty, please check your connection first, than check your file json.");
                }

                dismissAd();
            }

        } catch (Exception e) {
            if (BuildConfig.DEBUG) {
                AppsConfig.setLog("Failed to build AlienInterstitial cause : " + e.getMessage());
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private void updateInterstitial(int index) {
        load();

        String data_name = ConnectionPromote.adList.get(index).getName();
        String data_packageName = ConnectionPromote.adList.get(index).getPackageName();

        name.setText(data_name);
        install.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onOpenViewAdListener != null) {
                    onOpenViewAdListener.onInterstitialAdClicked();
                }
                dismiss();
                AppsConfig.openAdLink(activity.getApplicationContext(), "https://ad.clickmobile.id/v1/do?ad_id=" + data_packageName + "&placement_id=" + APPID);
                if (BuildConfig.DEBUG) {
                    AppsConfig.setLog("AlienInterstitial Clicked.");
                }
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onOpenViewAdListener != null) {
                    onOpenViewAdListener.onInterstitialAdClosed();
                }
                if (BuildConfig.DEBUG) {
                    AppsConfig.setLog("AlienInterstitial Closed.");
                }
                dismissAd();

            }
        });

        mWebView.setWebViewClient(new MyBrowser ());
        mWebView.setWebChromeClient(new ChromeClient());

        try {
            WebSettings settings = mWebView.getSettings();
            settings.setJavaScriptCanOpenWindowsAutomatically(true);
            settings.setSupportMultipleWindows(true);
            settings.setBuiltInZoomControls(true);
            settings.setJavaScriptEnabled(true);
            settings.setAppCacheEnabled(true);
            settings.setAppCacheMaxSize(10 * 1024 * 1024);
            settings.setAppCachePath("");
            settings.setDatabaseEnabled(true);
            settings.setDomStorageEnabled(true);
            settings.setGeolocationEnabled(true);
            settings.setSaveFormData(false);
            settings.setSavePassword(false);
            settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
            // Flash settings
            settings.setPluginState(WebSettings.PluginState.ON);
            settings.setJavaScriptEnabled(true);
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
            settings.setDomStorageEnabled(true);
            settings.setAllowContentAccess(true);
            settings.setAllowFileAccess(true);
            settings.setAllowFileAccessFromFileURLs(true);
            settings.setAllowUniversalAccessFromFileURLs(true);
            mWebView.getSettings().setBuiltInZoomControls(true);
            mWebView.getSettings().setDisplayZoomControls(false);
            settings.setAllowFileAccess(true);
            settings.setAppCacheEnabled(true);
            mWebView.requestDisallowInterceptTouchEvent(true);
            // Geo location settings
            settings.setGeolocationEnabled(true);
            settings.setGeolocationDatabasePath("/data/data/selendroid");


            mWebView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
            mWebView.getSettings().setBuiltInZoomControls(true);
            mWebView.getSettings().setSupportZoom(true);
            mWebView.getSettings().setDisplayZoomControls(false);
            mWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
            mWebView.setScrollbarFadingEnabled(true);
            mWebView.setLongClickable(true);
            mWebView.getSettings().setJavaScriptEnabled(true);
            mWebView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
            mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
            mWebView.getSettings().setDomStorageEnabled(true);
            mWebView.getSettings().setAppCacheEnabled(true);
            mWebView.getSettings().setSavePassword(true);
            mWebView.getSettings().setSaveFormData(true);
            mWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(false);
            mWebView.getSettings().setEnableSmoothTransition(true);
            mWebView.getSettings().setMediaPlaybackRequiresUserGesture(true);
            mWebView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        } catch (Exception e) {
            //SelendroidLogger.error("Error configuring web view", e);
        }

        mWebView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength){
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));

                request.setDescription("Download file...");
                request.setTitle(URLUtil.guessFileName(url, contentDisposition, mimetype));
                request.allowScanningByMediaScanner();
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED); //Notify client once download is completed!
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, URLUtil.guessFileName(url, contentDisposition, mimetype));
                DownloadManager dm = (DownloadManager) activity.getSystemService(DOWNLOAD_SERVICE);
                dm.enqueue(request);
                Toast.makeText(activity, "Downloading File", Toast.LENGTH_LONG).show();
            }
        });

        mWebView.loadUrl("https://ad.clickmobile.id/v1/do?ad_id=" + data_packageName + "&placement_id=" + APPID);
        mWebView.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK
                        && event.getAction() == MotionEvent.ACTION_UP
                        && mWebView.canGoBack()) {
                    mWebView.goBack();
                    return true;
                }
                return false;
            }
        });

    }

    private void generateInstallButton() {

        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        generateInstallClose();
    }

    private void generateInstallClose() {

        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        gradientDrawable.setColor(Color.parseColor("#E4E4E4"));
        close.setBackground(gradientDrawable);
    }

    public void setStyle(int style) {
        applyStyle(style);
        initializeUI();
    }

    public void setOnOpenViewAdListener(OnOpenViewAdListener onOpenViewAdListener) {
        this.onOpenViewAdListener = onOpenViewAdListener;
    }

    public void setOnAdClosed(OnClosed onClosed) {
        this.onClosed = onClosed;
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            iconProgress.setVisibility(View.GONE);
            view.loadUrl(url);
            return true;

        }

        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
            ByteArrayInputStream EMPTY3 = new ByteArrayInputStream("".getBytes());
            String kk53 = String.valueOf(blocklist);//Load blocklist
            if (kk53.contains(":::::" + request.getUrl().getHost())) {// If blocklist equals url = Block
                return new WebResourceResponse("text/plain", "utf-8", EMPTY3);//Block
            }
            //iconProgress.setVisibility(View.GONE);
            return super.shouldInterceptRequest(view, request);
        }

        public void onReceivedError(WebView view, int errorCode, String description, String
                failingUrl) {
            Toast.makeText(activity, "please activate internet !! ", Toast.LENGTH_SHORT).show();

            view.loadUrl("about:blank");
        }


        @Override
        public void onPageFinished(WebView view, String url) {
            String javaScript = "javascript:(function() { document.getElementById('google_image_div').remove();})()";

            mWebView.loadUrl(javaScript);
            iconProgress.setVisibility(View.GONE);
        }
    }



    private class ChromeClient extends WebChromeClient {
        private View mCustomView;
        private CustomViewCallback mCustomViewCallback;
        protected FrameLayout mFullscreenContainer;
        private int mOriginalOrientation;
        private int mOriginalSystemUiVisibility;

        ChromeClient() {}

        public Bitmap getDefaultVideoPoster()
        {
            if (mCustomView == null) {
                return null;
            }
            return BitmapFactory.decodeResource(activity.getResources(), 2130837573);
        }

        public void onHideCustomView()
        {
            ((FrameLayout)getWindow().getDecorView()).removeView(this.mCustomView);
            this.mCustomView = null;
            getWindow().getDecorView().setSystemUiVisibility(this.mOriginalSystemUiVisibility);
            activity.setRequestedOrientation(this.mOriginalOrientation);
            this.mCustomViewCallback.onCustomViewHidden();
            this.mCustomViewCallback = null;
        }

        public void onShowCustomView(View paramView, CustomViewCallback paramCustomViewCallback)
        {
            if (this.mCustomView != null)
            {
                onHideCustomView();
                return;
            }
            this.mCustomView = paramView;
            this.mOriginalSystemUiVisibility = getWindow().getDecorView().getSystemUiVisibility();
            this.mOriginalOrientation = activity.getRequestedOrientation();
            this.mCustomViewCallback = paramCustomViewCallback;
            ((FrameLayout)getWindow().getDecorView()).addView(this.mCustomView, new FrameLayout.LayoutParams(-1, -1));
            getWindow().getDecorView().setSystemUiVisibility(3846 | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
    }

    //Advertise filter with the lists
    public class MyWebViewClient extends WebViewClient {

        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
            ByteArrayInputStream EMPTY = new ByteArrayInputStream("".getBytes());
            String kk5 = String.valueOf(blocklist);

            if (kk5.contains(":::::" + request.getUrl().getHost())) {
                return new WebResourceResponse("text/plain", "utf-8", EMPTY);
            }
            return super.shouldInterceptRequest(view, request);
        }
    }
    private void load(){
        String strLine2="";
        blocklist = new StringBuilder();
        InputStream fis2 = activity.getResources().openRawResource(R.raw.ads);//Storage location
        BufferedReader br2 = new BufferedReader(new InputStreamReader(fis2));
        if(fis2 != null) {
            try {
                while ((strLine2 = br2.readLine()) != null) {
                    if(loddnormallist.equals("0")){
                        blocklist.append(strLine2);//if ":::::" exists in blocklist | Line for Line
                        blocklist.append("\n");
                    }
                    if(loddnormallist.equals("1")){
                        blocklist.append(":::::"+strLine2);//if ":::::" not exists in blocklist | Line for Line
                        blocklist.append("\n");
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
