package com.aliendroid.alienads.model;

public class CPAModel {

    private String url;
    private String imageUrl;
    private String country;

    public CPAModel(String url, String imageUrl, String country) {
        this.url = url;
        this.imageUrl = imageUrl;
        this.country = country ;
    }
    public String getUrl() {
        return url;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public String getCountry() {
        return country;
    }

}
