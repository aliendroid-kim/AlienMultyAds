package com.aliendroid.alienads;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.Settings;

import androidx.annotation.Nullable;

import com.applovin.sdk.AppLovinPrivacySettings;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkConfiguration;
import com.google.android.gms.ads.MobileAds;
import com.google.android.ump.ConsentDebugSettings;
import com.google.android.ump.ConsentForm;
import com.google.android.ump.ConsentInformation;
import com.google.android.ump.ConsentRequestParameters;
import com.google.android.ump.FormError;
import com.google.android.ump.UserMessagingPlatform;

import com.startapp.sdk.adsbase.StartAppSDK;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.atomic.AtomicBoolean;

public class AlienGDPR {
    public static ConsentInformation consentInformation;
    public static ConsentDebugSettings debugSettings;
    public static ConsentRequestParameters params;
    private static final AtomicBoolean isMobileAdsInitializeCalled = new AtomicBoolean(false);

    public static void loadGdpr(Activity activity, String selectAds, boolean childDirected) {
        switch (selectAds) {
            case "ADMOB":
                if (BuildConfig.DEBUG) {
                    String android_id = Settings.Secure.getString(activity.getContentResolver(), Settings.Secure.ANDROID_ID);
                    String deviceId = md5(android_id).toUpperCase();
                    debugSettings = new ConsentDebugSettings.Builder(activity)
                            .setDebugGeography(ConsentDebugSettings
                                    .DebugGeography
                                    .DEBUG_GEOGRAPHY_EEA)
                            .addTestDeviceHashedId(deviceId)
                            .build();
                    params = new ConsentRequestParameters
                            .Builder()
                            .setConsentDebugSettings(debugSettings)
                            .setTagForUnderAgeOfConsent(childDirected)
                            .build();
                } else {
                    params = new ConsentRequestParameters
                            .Builder()
                            .setTagForUnderAgeOfConsent(childDirected)
                            .build();
                }

                consentInformation = UserMessagingPlatform.getConsentInformation(activity);
                consentInformation.requestConsentInfoUpdate(
                        activity,
                        params,
                        (ConsentInformation.OnConsentInfoUpdateSuccessListener) () -> {
                            UserMessagingPlatform.loadAndShowConsentFormIfRequired(
                                    activity,
                                    (ConsentForm.OnConsentFormDismissedListener) loadAndShowError -> {
                                        if (loadAndShowError != null) {

                                        }
                                        // Consent has been gathered.
                                        if (consentInformation.canRequestAds()) {
                                            initializeMobileAdsSdk(activity);
                                        }

                                    }
                            );

                        },
                        (ConsentInformation.OnConsentInfoUpdateFailureListener) requestConsentError -> {
                            // Consent gathering failed.
                        });
                if (consentInformation.canRequestAds()) {
                    initializeMobileAdsSdk(activity);
                }
                break;
            case "STARTAPP":
                StartAppSDK.setUserConsent(activity,
                        "pas",
                        System.currentTimeMillis(),
                        true);
                break;
            case "IRON":
                break;
            case "APPLOVIN-M":

                AppLovinSdk.initializeSdk(activity, new AppLovinSdk.SdkInitializationListener() {
                    @Override
                    public void onSdkInitialized(final AppLovinSdkConfiguration configuration) {
                        if (configuration.getConsentDialogState() == AppLovinSdkConfiguration.ConsentDialogState.APPLIES) {
                            // Show user consent dialog
                        } else if (configuration.getConsentDialogState() == AppLovinSdkConfiguration.ConsentDialogState.DOES_NOT_APPLY) {
                            // No need to show consent dialog, proceed with initialization
                        } else {
                            // Consent dialog state is unknown. Proceed with initialization, but check if the consent
                            // dialog should be shown on the next application initialization
                        }
                    }
                });
                AppLovinPrivacySettings.setHasUserConsent(true, activity);
                AppLovinPrivacySettings.setIsAgeRestrictedUser(childDirected, activity);
                break;
            case "APPLOVIN-D":
                AppLovinPrivacySettings.setIsAgeRestrictedUser(childDirected, activity);
                AppLovinPrivacySettings.setHasUserConsent(true, activity);
                break;
            case "ALIEN-M":

                break;

        }
    }

    private static void initializeMobileAdsSdk(Activity activity) {
        if (isMobileAdsInitializeCalled.getAndSet(true)) {
            return;
        }
        MobileAds.initialize(activity);
    }

    public static void loadForm(Activity activity) {
        UserMessagingPlatform.loadConsentForm(
                activity,
                consentForm -> {
                    if (consentInformation.getConsentStatus() == ConsentInformation.ConsentStatus.REQUIRED) {
                        consentForm.show(
                                activity,
                                new ConsentForm.OnConsentFormDismissedListener() {
                                    @Override
                                    public void onConsentFormDismissed(@Nullable FormError formError) {
                                        // Handle dismissal by reloading form.
                                        loadForm(activity);
                                    }
                                });

                    }

                },
                formError -> {
                    /// Handle Error.
                }
        );
    }

    public static final String md5(final String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest
                    .getInstance("MD5");
            digest.update(s.getBytes());
            byte[] messageDigest = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            //Logger.logStackTrace(TAG,e);
        }
        return "";
    }


}
