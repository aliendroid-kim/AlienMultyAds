package com.aliendroid.alienads;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.aliendroid.alienads.model.CPAModel;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

public class AlienPopup {
    public static void CpaOpenAds_300_250(Activity activity, String url, String imageUrl) {
        final Dialog dialog = new Dialog(activity, R.style.SheetDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.cpa_300_250);
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

    public static ArrayList<CPAModel> adsData;

    public static void CpaOpenAds_Json(Activity activity, String urlJson) {
        adsData = new ArrayList<>();
        TelephonyManager tm = (TelephonyManager) activity.getSystemService(Context.TELEPHONY_SERVICE);
        String countryCodeValue = tm.getNetworkCountryIso();
        if (countryCodeValue != null) {
            StringRequest stringRequest = new StringRequest(Request.Method.GET,
                    urlJson, new com.android.volley.Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray array = jsonObject.getJSONArray("Ads");
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject jo = array.getJSONObject(i);
                            if (jo.getString("country").equals(countryCodeValue.toUpperCase()) ||
                                    jo.getString("country").equals(countryCodeValue.toLowerCase())) {
                                CPAModel developers = new CPAModel(jo.getString("url")
                                        , jo.getString("image_url"), jo.getString("country"));
                                adsData.add(developers);
                            }
                        }
                        final Dialog dialog = new Dialog(activity, R.style.SheetDialog);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setCancelable(false);
                        dialog.setContentView(R.layout.cpa_dynamic);
                        ImageView imgAds = dialog.findViewById(R.id.imgAds);
                        ImageView imgBack = dialog.findViewById(R.id.imgBack);
                        ProgressBar cdAds = dialog.findViewById(R.id.loadAds);
                        cdAds.setVisibility(View.GONE);
                        dialog.show();
                        Random random = new Random();
                        if (adsData.size() == 0) {
                            dialog.dismiss();
                        } else {
                            int suf = random.nextInt(adsData.size());
                            Glide.with(activity).load(adsData.get(suf).getImageUrl()).fitCenter().into(imgAds);
                            imgAds.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(adsData.get(suf).getUrl())));
                                    dialog.dismiss();
                                }
                            });
                        }
                        imgBack.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new com.android.volley.Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(activity, "Error" + error.toString(), Toast.LENGTH_SHORT).show();
                }
            });

            RequestQueue requestQueue = Volley.newRequestQueue(activity);
            requestQueue.add(stringRequest);


        }


    }
}
