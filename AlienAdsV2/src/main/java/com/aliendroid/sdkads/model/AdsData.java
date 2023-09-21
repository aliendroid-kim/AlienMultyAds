package com.aliendroid.sdkads.model;

public class AdsData {

    private String name;
    private String packageName;
    private String appPreview;


    public AdsData(String name, String packageName, String appPreview) {
        this.name = name;
        this.packageName = packageName;
        this.appPreview = appPreview ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getAppPreview() {
        return appPreview;
    }

    public void setAppPreview(String appPreview) {
        this.appPreview = appPreview;
    }
}
