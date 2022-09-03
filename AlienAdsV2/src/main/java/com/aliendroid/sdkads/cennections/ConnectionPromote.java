package com.aliendroid.sdkads.cennections;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.util.Log;


import com.aliendroid.alienads.BuildConfig;
import com.aliendroid.sdkads.config.AppsConfig;
import com.aliendroid.sdkads.interfaces.OnConnectedListener;
import com.aliendroid.sdkads.model.AdsData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ConnectionPromote {

    protected String TAG = "connectionPromote";
    private final String ConnectionAdOff = "AdPromoteCache.json";

    private Activity activity;
    private Context context;
    private final String adLink;
    private URL url;
    private int indexConnection;
   // private final DBAds DBAds;
    private OnConnectedListener onConnectedListener;
    public static ArrayList<AdsData> adList = new ArrayList<>();

    public ConnectionPromote(Context context, String adLink) {
        this.context = context;
        this.adLink = adLink;
        new setPromotionTask().execute();

    }

    public ConnectionPromote(Activity activity, String adLink) {
        this.activity = activity;
        this.adLink = adLink;
        this.context = activity.getApplicationContext();
        setAppPromotionThread();
    }


    //Execute connection in Android 30 or Higher : this method need Activity.
    private void setAppPromotionThread() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                String task = doInBackgroundTask();
                if (BuildConfig.DEBUG) {
                    setLog("Thread Connecting Running...");
                }
                activity.runOnUiThread(new Runnable() {
                    public void run() {
                        startConnection(task, true, indexConnection);
                        if (BuildConfig.DEBUG) {
                            setLog("Thread Finished with : " + task);

                        }
                    }
                });
            }
        }).start();
    }


    //Deprecated on Android 30 or later
    @SuppressLint("StaticFieldLeak")
    private class setPromotionTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            return doInBackgroundTask();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            startConnection(s, false, indexConnection);

        }
    }


    private void startConnection(String result, boolean isThread, int index) {
        if (index == 0) { //AppsPromote task.
            setAppsFetch(result, isThread);
        }
    }


    protected void setAppsFetch(String values, boolean isThread) {
        try {
            JSONObject jsonAdObject = new JSONObject(values);
            JSONObject jData = jsonAdObject.getJSONObject("data");
            JSONArray jsonApps = jData.getJSONArray(AppsConfig.appPromote);
            for (int j = 0; j < jsonApps.length(); j++) {
                JSONObject object = jsonApps.getJSONObject(j);
                String name = object.getString(AppsConfig.appName);
                String packageName = object.getString(AppsConfig.appPackage);
                String appPreview = object.getString(AppsConfig.appPreview);
                AdsData apps = new AdsData(name, packageName,appPreview);
                adList.add(apps);
            }

            if (onConnectedListener != null) {
                onConnectedListener.onAppConnected();
            }

        } catch (JSONException e) {
            if (onConnectedListener != null) {
                onConnectedListener.onAppFailed(e.getMessage());
            }

            try {
                if (checkConnection()) {


                    try {
                        if (BuildConfig.DEBUG) {
                            setLog("reconnecting in 5 second !");
                        }
                        new CountDownTimer(1000 * 5, 1000) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                if (BuildConfig.DEBUG) {
                                    setLog("" + millisUntilFinished / 1000);
                                }
                            }

                            @Override
                            public void onFinish() {
                                if (BuildConfig.DEBUG) {
                                    setLog("Execute Connection...");
                                }
                                if (isThread) {
                                    setAppPromotionThread();
                                } else {
                                    new setPromotionTask().execute();
                                }
                            }
                        }.start();
                    } catch (Exception countError) {
                        countError.printStackTrace();
                    }

                } else {
                    if (onConnectedListener != null) {
                        onConnectedListener.onAppFailed("Connecting stopped = user turn-off the connection.");
                    }
                }

            } catch (Exception e2) {
                if (onConnectedListener != null) {
                    onConnectedListener.onAppFailed(e2.getMessage());
                }
            }

        }

    }

    private void setLog(String log) {
        Log.d(TAG, log);
    }
    private String doInBackgroundTask() {
        File file = new File(context.getFilesDir().getPath() + "/" + ConnectionAdOff);
        if (checkConnection()) {

            try {
                url = new URL(adLink);
            } catch (MalformedURLException e) {
                if (BuildConfig.DEBUG) {
                    Log.d(TAG, "URL Malformed cause : " + e.getMessage());
                }
            }

            HttpURLConnection connection;
            try {
                connection = (HttpURLConnection) url.openConnection();
                connection.setReadTimeout(15000);
                connection.setConnectTimeout(10000);
                connection.setRequestMethod("GET");

            } catch (IOException e1) {
                return e1.toString();
            }

            try {

                int responseCode = connection.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) {

                    InputStream inputStream = connection.getInputStream();
                    return buffToString(new InputStreamReader(inputStream), true);

                } else {

                    if (file.exists()) {
                        return buffToString(new FileReader(file), false);
                    }

                }
            } catch (IOException e2) {
                return e2.toString();
            } finally {
                connection.disconnect();
            }

        } else {

            try {
                return buffToString(new FileReader(file), false);
            } catch (IOException e) {
                return e.toString();
            }
        }
        return "";
    }

    private String buffToString(Reader ourReader, boolean save) {
        try {
            BufferedReader reader = new BufferedReader(ourReader);
            StringBuilder result = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            if (save) {
                if (!result.toString().equals(null)) {
                    writeJsonToFile(result.toString());
                }
            }

            return (result.toString());
        } catch (IOException e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    private void writeJsonToFile(String data) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(ConnectionAdOff, Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean checkConnection() {
        final ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null) {
            if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                return true;
            } else if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                return true;
            }
            if (activeNetworkInfo.isConnectedOrConnecting()) {

                return true;
            }
        }
        return false;
    }

    public void setOnPromoteConnected(OnConnectedListener onConnectedListener) {
        this.onConnectedListener = onConnectedListener;
    }


}
