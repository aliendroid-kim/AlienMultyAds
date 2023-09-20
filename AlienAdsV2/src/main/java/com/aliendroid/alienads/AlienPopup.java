package com.aliendroid.alienads;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class AlienPopup {
    public static void CpaOpenAds_300_250(Activity activity, String url, String imageUrl){
        final Dialog dialog = new Dialog(activity,R.style.SheetDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.cpa__300_250);
        ImageView imgAds = dialog.findViewById(R.id.imgAds);
        ImageView imgBack = dialog.findViewById(R.id.imgBack);
        Glide.with(activity).load(imageUrl).fitCenter().into(imgAds);
        imgAds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                dialog.dismiss();
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }
}
