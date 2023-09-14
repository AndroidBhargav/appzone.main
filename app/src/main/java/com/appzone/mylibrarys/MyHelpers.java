package com.appzone.mylibrarys;


import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Handler;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.content.res.ResourcesCompat;

import com.bumptech.glide.Glide;
import com.facebook.ads.AudienceNetworkAds;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;
import java.util.Random;


public class MyHelpers extends Application {

    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;
    public static MyHelpers app;
    public static MyHelpers instance;

    public static ArrayList<QurekaModel> native_ads = new ArrayList<>();
    public static ArrayList<QurekaModel> inter_ads = new ArrayList<>();
    public static ArrayList<Integer> round_ads = new ArrayList<>();


    public static synchronized MyHelpers getInstanceHelp() {
        MyHelpers application;
        synchronized (MyHelpers.class) {
            application = instance;
        }
        return application;
    }

    public static MyHelpers getInstant() {
        return instance;
    }

    @Override
    public void onCreate() {
        instance = this;
        /*Google*/
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {
            }
        });
        /*Facebook*/
        AudienceNetworkAds.initialize(this);

        sharedPreferences = getSharedPreferences("baba", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        super.onCreate();

        AllArrayList();
    }

    private void AllArrayList() {

        native_ads.add(new QurekaModel(R.drawable.q_native_1, "SSC, Bank Po kaliyar karna hai?", "Not App Install, Click and play game now..."));
        native_ads.add(new QurekaModel(R.drawable.q_native_2, "50,000 coin k liye....", "Not App Install, Click and play game now..."));
        native_ads.add(new QurekaModel(R.drawable.q_native_3, "Play quiz 50,000 coin earn.", "Not App Install, Click and play game now..."));
        native_ads.add(new QurekaModel(R.drawable.q_native_4, "50,000 coin k liye 10+2 quiz live hai..", "Not App Install, Click and play game now..."));
        native_ads.add(new QurekaModel(R.drawable.q_native_5, "Play quiz 10+2 AND Earn up to 50,000 coin ", "Not App Install, Click and play game now..."));
        native_ads.add(new QurekaModel(R.drawable.q_native_6, "PLAY BANK PO 50,000 COINS", "Not App Install, Click and play game now..."));
        native_ads.add(new QurekaModel(R.drawable.q_native_7, "10+2 EXAM QUIZ FOR 50,000 COIN IS LIVE", "Not App Install, Click and play game now..."));
        native_ads.add(new QurekaModel(R.drawable.q_native_8, "HISTORY QUIZ FOR 15,000 COINS", "Not App Install, Click and play game now..."));
        native_ads.add(new QurekaModel(R.drawable.q_native_9, "PLAY GK QUIZ Earn upto 50,000 coins", "Not App Install, Click and play game now..."));
        native_ads.add(new QurekaModel(R.drawable.q_native_10, "PLAY QUIZZES Earn upto 50,000 coins daily", "Not App Install, Click and play game now..."));


        inter_ads.add(new QurekaModel(R.drawable.q_inter_1, "MEGA QUIZ FOR 5,00,000 COIN OPEN", "Not App Install, Click and play game now..."));
        inter_ads.add(new QurekaModel(R.drawable.q_inter_2, "PLAY TECH QUIZ & EARN UP TO 15,000 COIN", "Not App Install, Click and play game now..."));
        inter_ads.add(new QurekaModel(R.drawable.q_inter_3, "PLAY GK QUIZ AND Earn up to 50,000 coins", "Not App Install, Click and play game now..."));
        inter_ads.add(new QurekaModel(R.drawable.q_inter_4, "Play Quizzes In Categories Earn up to 50,000 coins", "Not App Install, Click and play game now..."));
        inter_ads.add(new QurekaModel(R.drawable.q_inter_5, "Play IPL QUIZ NOW & Earn up to 50,000 coins", "Not App Install, Click and play game now..."));
        inter_ads.add(new QurekaModel(R.drawable.q_inter_6, "Play Cricket Quiz and win up tp 50,000 coins", "Not App Install, Click and play game now..."));
        inter_ads.add(new QurekaModel(R.drawable.q_inter_7, "History quiz for 15,000 COIN LIVE NOW", "Not App Install, Click and play game now..."));

        round_ads.add(R.drawable.q_round_1);
        round_ads.add(R.drawable.q_round_2);
        round_ads.add(R.drawable.q_round_3);
        round_ads.add(R.drawable.q_round_4);
        round_ads.add(R.drawable.q_round_5);
        round_ads.add(R.drawable.q_round_6);

    }

    public static boolean isOnline(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    public static void setBackAdsOnOff(String BackAdsOnOff) {
        editor.putString("BackAdsOnOff", BackAdsOnOff).commit();
    }

    public static String getBackAdsOnOff() {
        return sharedPreferences.getString("BackAdsOnOff", null);
    }

    /**
     * Google ads
     */
    public static void setGoogleEnable(String GoogleEnable) {
        editor.putString("GoogleEnable", GoogleEnable).commit();
    }

    public static String getGoogleEnable() {
        return sharedPreferences.getString("GoogleEnable", null);
    }

    public static void setGoogle_OpenADS(String Google_OpenADS) {
        editor.putString("Google_OpenADS", Google_OpenADS).commit();
    }

    public static String getGoogle_OpenADS() {
        return sharedPreferences.getString("Google_OpenADS", null);
    }

    public static void setGooglebutton_color(String Googlebutton_color) {
        editor.putString("Googlebutton_color", Googlebutton_color).commit();
    }

    public static String getGooglebutton_color() {
        return sharedPreferences.getString("Googlebutton_color", null);
    }

    public static void SetGoogleInter(String GoogleInter) {
        editor.putString("GoogleInter", GoogleInter).commit();
    }

    public static String getGoogleInter() {
        return sharedPreferences.getString("GoogleInter", null);
    }

    public static void SetGoogleInter1(String GoogleInter1) {
        editor.putString("GoogleInter1", GoogleInter1).commit();
    }

    public static String getGoogleInter1() {
        return sharedPreferences.getString("GoogleInter1", null);
    }

    public static void SetGoogleInter2(String GoogleInter2) {
        editor.putString("GoogleInter2", GoogleInter2).commit();
    }

    public static String getGoogleInter2() {
        return sharedPreferences.getString("GoogleInter2", null);
    }

    public static void SetGoogleNative(String GoogleNative) {
        editor.putString("GoogleNative", GoogleNative).commit();
    }

    public static String getGoogleNative() {
        return sharedPreferences.getString("GoogleNative", null);
    }

    public static void SetGoogleNative1(String GoogleNative1) {
        editor.putString("GoogleNative1", GoogleNative1).commit();
    }

    public static String getGoogleNative1() {
        return sharedPreferences.getString("GoogleNative1", null);
    }

    public static void SetGoogleNative2(String GoogleNative2) {
        editor.putString("GoogleNative2", GoogleNative2).commit();
    }

    public static String getGoogleNative2() {
        return sharedPreferences.getString("GoogleNative2", null);
    }

    /**
     * Facebook
     */
    public static void setFacebookEnable(String FacebookEnable) {
        editor.putString("FacebookEnable", FacebookEnable).commit();
    }

    public static String getFacebookEnable() {
        return sharedPreferences.getString("FacebookEnable", null);
    }

    public static void setfacebook_open_ad_id(String facebook_open_ad_id) {
        editor.putString("facebook_open_ad_id", facebook_open_ad_id).commit();
    }

    public static String getfacebook_open_ad_id() {
        return sharedPreferences.getString("facebook_open_ad_id", null);
    }

    public static void SetFacebookInter(String FacebookInter) {
        editor.putString("FacebookInter", FacebookInter).commit();
    }

    public static String getFacebookInter() {
        return sharedPreferences.getString("FacebookInter", null);
    }

    public static void SetFacebookInter1(String FacebookInter1) {
        editor.putString("FacebookInter1", FacebookInter1).commit();
    }

    public static String getFacebookInter1() {
        return sharedPreferences.getString("FacebookInter1", null);
    }

    public static void SetFacebookInter2(String FacebookInter2) {
        editor.putString("FacebookInter2", FacebookInter2).commit();
    }

    public static String getFacebookInter2() {
        return sharedPreferences.getString("FacebookInter2", null);
    }

    public static void SetFacebookNative(String FacebookNative) {
        editor.putString("FacebookNative", FacebookNative).commit();
    }

    public static String getFacebookNative() {
        return sharedPreferences.getString("FacebookNative", null);
    }

    public static void SetFacebookNative1(String FacebookNative1) {
        editor.putString("FacebookNative1", FacebookNative1).commit();
    }

    public static String getFacebookNative1() {
        return sharedPreferences.getString("FacebookNative1", null);
    }

    public static void SetFacebookNative2(String FacebookNative2) {
        editor.putString("FacebookNative2", FacebookNative2).commit();
    }

    public static String getFacebookNative2() {
        return sharedPreferences.getString("FacebookNative2", null);
    }

    /**
     * Qureka link
     */

    public static void setQurekaInterSkipTime(String QurekaInterSkipTime) {
        editor.putString("QurekaInterSkipTime", QurekaInterSkipTime).commit();
    }

    public static String getQurekaInterSkipTime() {
        return sharedPreferences.getString("QurekaInterSkipTime", "0");
    }

    public static void setQurekaCloseBTNAutoOpenLink(String QurekaCloseBTNAutoOpenLink) {
        editor.putString("QurekaCloseBTNAutoOpenLink", QurekaCloseBTNAutoOpenLink).commit();
    }

    public static String getQurekaCloseBTNAutoOpenLink() {
        return sharedPreferences.getString("QurekaCloseBTNAutoOpenLink", null);
    }

    public static void set_q_link_btn_on_off(String _q_link_btn_on_off) {
        editor.putString("_q_link_btn_on_off", _q_link_btn_on_off).commit();
    }

    public static String get_q_link_btn_on_off() {
        return sharedPreferences.getString("_q_link_btn_on_off", null);
    }

    public static void set_q_link_array(String _q_link_array) {
        editor.putString("_q_link_array", _q_link_array).commit();
    }

    public static String get_q_link_array() {
        return sharedPreferences.getString("_q_link_array", null);
    }


    /**
     * Skip ADS
     */
    public static void setCounter_Inter(Integer Counter) {
        editor.putInt("Counter", Counter).commit();
    }

    public static Integer getCounter_Inter() {
        return sharedPreferences.getInt("Counter", 5000);
    }

    public static void setCounter_Native(Integer CounterNative) {
        editor.putInt("CounterNative", CounterNative).commit();
    }

    public static Integer getCounter_Native() {
        return sharedPreferences.getInt("CounterNative", 5000);
    }

    public static void setBackCounter(Integer BackCounter) {
        editor.putInt("BackCounter", BackCounter).commit();
    }

    public static Integer getBackCounter() {
        return sharedPreferences.getInt("BackCounter", 5000);
    }

    /**
     * MIX ADS
     */
    public static void setmix_ad_on_off(String mix_ad_on_off) {
        editor.putString("mix_ad_on_off", mix_ad_on_off).commit();
    }

    public static String getmix_ad_on_off() {
        return sharedPreferences.getString("mix_ad_on_off", null);
    }

    public static void setmix_ad_open(String setmix_ad_open) {
        editor.putString("setmix_ad_open", setmix_ad_open).commit();
    }

    public static String getmix_ad_open() {
        return sharedPreferences.getString("setmix_ad_open", null);
    }

    public static void setmix_ad_native(String mix_ad_native) {
        editor.putString("mix_ad_native", mix_ad_native).commit();
    }

    public static String getmix_ad_native() {
        return sharedPreferences.getString("mix_ad_native", null);
    }

    public static void setmix_ad_inter(String mix_ad_inter) {
        editor.putString("mix_ad_inter", mix_ad_inter).commit();
    }

    public static String getmix_ad_inter() {
        return sharedPreferences.getString("mix_ad_inter", null);
    }

    public static void setmix_ad_counter_inter(Integer mix_ad_counter) {
        editor.putInt("mix_ad_counter", mix_ad_counter).commit();
    }

    public static Integer getmix_ad_counter_inter() {
        return sharedPreferences.getInt("mix_ad_counter", 5000);
    }

    public static void setmix_ad_counter_native(Integer mix_ad_counter_native) {
        editor.putInt("mix_ad_counter_native", mix_ad_counter_native).commit();
    }

    public static Integer getmix_ad_counter_native() {
        return sharedPreferences.getInt("mix_ad_counter_native", 5000);
    }

    public static void setExtraBtn_1(String ExtraBtn_1) {
        editor.putString("ExtraBtn_1", ExtraBtn_1).commit();
    }

    public static String getExtraBtn_1() {
        return sharedPreferences.getString("ExtraBtn_1", null);
    }

    public static void setExtraBtn_2(String ExtraBtn_2) {
        editor.putString("ExtraBtn_2", ExtraBtn_2).commit();
    }

    public static String getExtraBtn_2() {
        return sharedPreferences.getString("ExtraBtn_2", null);
    }

    public static void setExtraBtn_3(String ExtraBtn_3) {
        editor.putString("ExtraBtn_3", ExtraBtn_3).commit();
    }

    public static String getExtraBtn_3() {
        return sharedPreferences.getString("ExtraBtn_3", null);
    }

    public static void setExtraBtn_4(String ExtraBtn_4) {
        editor.putString("ExtraBtn_4", ExtraBtn_4).commit();
    }

    public static String getExtraBtn_4() {
        return sharedPreferences.getString("ExtraBtn_4", null);
    }

    public static void setExtraText_1(String ExtraText_1) {
        editor.putString("ExtraText_1", ExtraText_1).commit();
    }

    public static String getExtraText_1() {
        return sharedPreferences.getString("ExtraText_1", null);
    }

    public static void setExtraText_2(String ExtraText_2) {
        editor.putString("ExtraText_2", ExtraText_2).commit();
    }

    public static String getExtraText_2() {
        return sharedPreferences.getString("ExtraText_2", null);
    }

    public static void setExtraText_3(String ExtraText_3) {
        editor.putString("ExtraText_3", ExtraText_3).commit();
    }

    public static String getExtraText_3() {
        return sharedPreferences.getString("ExtraText_3", null);
    }

    public static void setExtraText_4(String ExtraText_4) {
        editor.putString("ExtraText_4", ExtraText_4).commit();
    }

    public static String getExtraText_4() {
        return sharedPreferences.getString("ExtraText_4", null);
    }

    /**
     * Facebook Sdk
     */

    public static void setFacebookSDK(String FacebookSDK) {
        editor.putString("FacebookSDK", FacebookSDK).commit();
    }

    public static String getFacebookSDK() {
        return sharedPreferences.getString("FacebookSDK", null);
    }

    public static void setACCESS_TOKEN(String ACCESS_TOKEN) {
        editor.putString("ACCESS_TOKEN", ACCESS_TOKEN).commit();
    }

    public static String getACCESS_TOKEN() {
        return sharedPreferences.getString("ACCESS_TOKEN", null);
    }

    public static void setAPP_SECRET(String APP_SECRET) {
        editor.putString("APP_SECRET", APP_SECRET).commit();
    }

    public static String getAPP_SECRET() {
        return sharedPreferences.getString("APP_SECRET", null);
    }

    public static void setACCOUNT_ID(String ACCOUNT_ID) {
        editor.putString("ACCOUNT_ID", ACCOUNT_ID).commit();
    }

    public static String getACCOUNT_ID() {
        return sharedPreferences.getString("ACCOUNT_ID", null);
    }

    public static boolean isAppInstalled(String packageName, Context context) {
        PackageManager pm = context.getPackageManager();
        boolean installed;
        try {
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            installed = false;
        }
        return installed;
    }


    public static int getRandomNumber(int min, int max) {
        return (new Random()).nextInt((max - min) + 1) + min;
    }

    public static void LinkOpenChromeCustomTabUrl(Context context, String Link) {
        try {
            if (MyHelpers.isAppInstalled("com.android.chrome", context)) {
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                builder.setToolbarColor(ResourcesCompat.getColor(context.getResources(), R.color.purple_700, null));
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.intent.setPackage("com.android.chrome");
                customTabsIntent.intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                customTabsIntent.launchUrl(context, Uri.parse(Link));

            } else {
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                builder.setToolbarColor(ResourcesCompat.getColor(context.getResources(), R.color.purple_700, null));
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                customTabsIntent.launchUrl(context, Uri.parse(Link));
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

    public static void BtnAutolink() {
        String[] Auto_Link = MyHelpers.get_q_link_array().split(",");
        if (Auto_Link.length == 1) {
            LinkOpenChromeCustomTabUrl(instance, Auto_Link[0]);
            return;
        }
        LinkOpenChromeCustomTabUrl(instance, Auto_Link[getRandomNumber(0, Auto_Link.length - 1)]);
    }
}
