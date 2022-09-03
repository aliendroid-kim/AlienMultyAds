package com.aliendroid.sdkads.layout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import com.aliendroid.alienads.R;
import com.aliendroid.sdkads.cennections.ConnectionPromote;
import com.aliendroid.sdkads.config.AppsConfig;
import com.aliendroid.sdkads.interfaces.OnLoadBannerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.Random;

public class BannerView extends FrameLayout {
    private final Context context;
    private int attrBodyColor = Color.WHITE; //default color
    private RelativeLayout bannerBody;
    private ImageView icon;
    private OnLoadBannerView onLoadBannerView;
    private final Handler handler = new Handler();
    String APPID;

    public BannerView(Context context, String appID) {
        super(context);
        initView(null);
        this.context = context;
        initializeData();
        this.APPID = appID;
    }

    public BannerView(Context context, @Nullable AttributeSet attrs, String appID) {
        super(context, attrs);
        initView(attrs);
        this.context = context;
        initializeData();
        this.APPID = appID;
    }

    public BannerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, String appID) {
        super(context, attrs, defStyleAttr);
        initView(attrs);
        this.context = context;
        initializeData();
        this.APPID = appID;
    }


    public BannerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes, String appID) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(attrs);
        this.context = context;
        initializeData();
        this.APPID = appID;

    }


    private void initView(AttributeSet attrs) {
        final TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.BannerView, 0, 0);
        attrBodyColor = typedArray.getColor(R.styleable.BannerView_banner_bodyColor, attrBodyColor);
        inflateBanner();
        typedArray.recycle();

    }

    private void inflateBanner() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.small_banner, this);
    }

    private void initializeData() {

    }


    private void applyUI() {
        if (bannerBody != null) {
            bannerBody.setBackgroundColor(attrBodyColor);
        }
    }


    @Override
    public void onFinishInflate() {
        super.onFinishInflate();

    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        showPromoteBanner(APPID);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        handler.removeCallbacksAndMessages(null);
    }

    @SuppressLint("SetTextI18n")
    private void initializeInflateUI() {
        bannerBody = (RelativeLayout) findViewById(R.id.banner_body);
        icon = (ImageView) findViewById(R.id.icons);
        applyUI();
    }


    public void showPromoteBanner(String appID) {
        try {

            if (ConnectionPromote.adList != null && !ConnectionPromote.adList.isEmpty()) {
                int size = ConnectionPromote.adList.size();
                Random random = new Random();
                int bannerIndex = random.nextInt(size);
                buildBanner(bannerIndex, appID, "");
            } else {
                //the ad list is empty.
                if (onLoadBannerView != null) {
                    onLoadBannerView.onBannerAdFailedToLoad("The Ad list is empty ! please check your json file.");
                }
                setVisibility(GONE);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @SuppressLint("SetTextI18n")
    private void buildBanner(int index, String appID, String adsID) {
        initializeInflateUI();
        String short_Appview = ConnectionPromote.adList.get(index).getAppPreview();
        String data_packageName = ConnectionPromote.adList.get(index).getPackageName();
        if (onLoadBannerView != null) {
            onLoadBannerView.onBannerAdLoaded();
        }
        loadIcon(short_Appview);
        bannerBody.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onLoadBannerView != null) {
                    onLoadBannerView.onBannerAdClicked();
                }
                AppsConfig.openAdLink(context, "https://ad.clickmobile.id/v1/do?ad_id=" + data_packageName + "&placement_id=" + APPID);
            }
        });

    }

    private void loadIcon(String icons) {
        Glide.with(context)
                .load(icons)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(icon);
    }

    public void setOnBannerListener(OnLoadBannerView onLoadBannerView) {
        this.onLoadBannerView = onLoadBannerView;
    }
}
