package com.appzone.mylibrarys;

public class AdsModal {

    String app_name;
    String enable_ads;
    String ad_app_name;
    String app_description;
    String app_logo;
    String app_banner;


    public AdsModal(String app_name, String enable_ads, String ad_app_name, String app_description, String app_logo, String app_banner) {
        this.app_name = app_name;
        this.enable_ads = enable_ads;
        this.ad_app_name = ad_app_name;
        this.app_description = app_description;
        this.app_logo = app_logo;
        this.app_banner = app_banner;

    }


    public String getApp_name() {
        return app_name;
    }

    public String getAd_app_name() {
        return ad_app_name;
    }

    public String getApp_description() {
        return app_description;
    }

    public String getApp_logo() {
        return app_logo;
    }

    public String getApp_banner() {
        return app_banner;
    }


}
