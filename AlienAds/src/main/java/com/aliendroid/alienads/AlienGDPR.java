package com.aliendroid.alienads;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.applovin.sdk.AppLovinPrivacySettings;
import com.google.android.ump.ConsentForm;
import com.google.android.ump.ConsentInformation;
import com.google.android.ump.ConsentRequestParameters;
import com.google.android.ump.FormError;
import com.google.android.ump.UserMessagingPlatform;
import com.ironsource.mediationsdk.IronSource;
import com.startapp.sdk.adsbase.StartAppSDK;

public class AlienGDPR {
    public static ConsentInformation consentInformation;

    public static void loadGdpr(Activity activity, String selectAds, boolean childDirected) {
        switch (selectAds) {
            case "ADMOB":
                // Set tag for underage of consent. false means users are not underage.
                ConsentRequestParameters params = new ConsentRequestParameters
                        .Builder()
                        .setTagForUnderAgeOfConsent(childDirected)
                        .build();

                consentInformation = UserMessagingPlatform.getConsentInformation(activity);
                consentInformation.requestConsentInfoUpdate(
                        activity,
                        params,
                        new ConsentInformation.OnConsentInfoUpdateSuccessListener() {
                            @Override
                            public void onConsentInfoUpdateSuccess() {
                                if (consentInformation.isConsentFormAvailable()) {
                                    loadForm(activity);
                                }

                            }
                        },
                        new ConsentInformation.OnConsentInfoUpdateFailureListener() {
                            @Override
                            public void onConsentInfoUpdateFailure(FormError formError) {
                                // Handle the error.
                            }
                        });

                break;
            case "STARTAPP":
                StartAppSDK.setUserConsent(activity,
                        "pas",
                        System.currentTimeMillis(),
                        true);
                break;
            case "IRON":
                IronSource.setConsent(true);
                IronSource.setMetaData("do_not_sell", "false");
                IronSource.setMetaData("is_child_directed", String.valueOf(childDirected));
                break;
            case "APPLOVIN-M":
                AppLovinPrivacySettings.setHasUserConsent(true, activity);
                AppLovinPrivacySettings.setIsAgeRestrictedUser(childDirected, activity);
                AppLovinPrivacySettings.setDoNotSell(false, activity);
                break;
        }
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

}
