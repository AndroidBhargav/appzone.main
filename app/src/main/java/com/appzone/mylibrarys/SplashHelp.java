package com.appzone.mylibrarys;

import static ProMex.classs.Utils.Util.DEc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;

import androidx.appcompat.app.AppCompatActivity;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.facebook.ads.Ad;
import com.facebook.ads.InterstitialAdListener;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.unity3d.ads.IUnityAdsLoadListener;
import com.unity3d.ads.IUnityAdsShowListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.ads.UnityAdsShowOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ProMex.classs.Utils.Util;
import cz.msebera.android.httpclient.Header;

public class SplashHelp extends AppCompatActivity {

    public static String extra_switch_1;
    public static String extra_switch_2;
    public static String extra_switch_3;
    public static String extra_switch_4;
    public static String extra_text_1;
    public static String extra_text_2;
    public static String extra_text_3;
    public static String extra_text_4;

    public static Context contextx;
    public static Intent intentx;

    public static boolean isShowOpen = false;
    public static AppOpenManager appOpenManager;
    public static String PackName = "";

    public static ArrayList<AdsModal> adsModals = new ArrayList<>();
    public static boolean customads_status = false;
    public static boolean OpenAdsStatus = false;
    public static boolean checkAppOpen = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
    }

    /**
     * Api Csll
     */
    public static void splash_next(String packageName, String versionCode, Context context, Intent intent) {

        PackName = packageName;
        contextx = context;
        intentx = intent;

        /*Custom*/
        SplashHelp.CustomAPICalls();


        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.addHeader(DEc(Util.pizzuhead), DEc(Util.pizzudians));
        asyncHttpClient.get(DEc(Util.pizzuli) + packageName, new JsonHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {

                    /**
                     * Google
                     */
                    MyHelpers.setGoogleEnable(response.getString("enable_google_admob_id"));
                    //google Banner
                    if (response.getString("google_admob_banner_id") != null && !response.getString("google_admob_banner_id").isEmpty()) {
                        MyHelpers.SetGoogleBanner(response.getString("google_admob_banner_id"));
                    } else {
                        MyHelpers.SetGoogleBanner(null);
                    }
                    if (response.getString("google_admob_banner_id_1") != null && !response.getString("google_admob_banner_id_1").isEmpty()) {
                        MyHelpers.SetGoogleBanner1(response.getString("google_admob_banner_id_1"));
                    } else {
                        MyHelpers.SetGoogleBanner1(null);
                    }
                    if (response.getString("google_admob_banner_id_2") != null && !response.getString("google_admob_banner_id_2").isEmpty()) {
                        MyHelpers.SetGoogleBanner2(response.getString("google_admob_banner_id_2"));
                    } else {
                        MyHelpers.SetGoogleBanner2(null);
                    }
                    //google Native
                    if (response.getString("google_admob_native_id") != null && !response.getString("google_admob_native_id").isEmpty()) {
                        MyHelpers.SetGoogleNative(response.getString("google_admob_native_id"));
                    } else {
                        MyHelpers.SetGoogleNative(null);
                    }
                    if (response.getString("google_admob_native_id_1") != null && !response.getString("google_admob_native_id_1").isEmpty()) {
                        MyHelpers.SetGoogleNative1(response.getString("google_admob_native_id_1"));
                    } else {
                        MyHelpers.SetGoogleNative1(null);
                    }
                    if (response.getString("google_admob_native_id_2") != null && !response.getString("google_admob_native_id_2").isEmpty()) {
                        MyHelpers.SetGoogleNative2(response.getString("google_admob_native_id_2"));
                    } else {
                        MyHelpers.SetGoogleNative2(null);
                    }
                    //google Native Btn Name
                    if (response.getString("google_button_name") != null && !response.getString("google_button_name").isEmpty()) {
                        MyHelpers.setGooglebutton_name(response.getString("google_button_name"));
                    } else {
                        MyHelpers.setGooglebutton_name(null);
                    }
                    //google Native Btn color
                    if (response.getString("google_button_color") != null && !response.getString("google_button_color").isEmpty()) {
                        MyHelpers.setGooglebutton_color(response.getString("google_button_color"));
                    } else {
                        MyHelpers.setGooglebutton_color("#000000");
                    }
                    // google Open ADS
                    if (response.getString("google_open_id") != null && !response.getString("google_open_id").isEmpty()) {
                        MyHelpers.setGoogle_OpenADS(response.getString("google_open_id"));
                    } else {
                        MyHelpers.setGoogle_OpenADS(null);
                    }
                    //google Interstitial
                    if (response.getString("google_admob_interstitial_id") != null && !response.getString("google_admob_interstitial_id").isEmpty()) {
                        InterClass.AutoGoogleInterID = 1;
                        MyHelpers.SetGoogleInter(response.getString("google_admob_interstitial_id"));
                    } else {
                        MyHelpers.SetGoogleInter(null);
                    }
                    if (response.getString("google_admob_interstitial_id_1") != null && !response.getString("google_admob_interstitial_id_1").isEmpty()) {
                        MyHelpers.SetGoogleInter1(response.getString("google_admob_interstitial_id_1"));
                    } else {
                        MyHelpers.SetGoogleInter1(null);
                    }
                    if (response.getString("google_admob_interstitial_id_2") != null && !response.getString("google_admob_interstitial_id_2").isEmpty()) {
                        MyHelpers.SetGoogleInter2(response.getString("google_admob_interstitial_id_2"));
                    } else {
                        MyHelpers.SetGoogleInter2(null);
                    }
                    /**
                     * Facebook
                     */
                    MyHelpers.setFacebookEnable(response.getString("enable_facebook_id"));
                    //Facebook Banner
                    if (response.getString("facebook_banner_id") != null && !response.getString("facebook_banner_id").isEmpty()) {
                        MyHelpers.setFacebookBanner(response.getString("facebook_banner_id"));
                    } else {
                        MyHelpers.setFacebookBanner(null);
                    }
                    if (response.getString("facebook_banner_id_1") != null && !response.getString("facebook_banner_id_1").isEmpty()) {
                        MyHelpers.setFacebookBanner1(response.getString("facebook_banner_id_1"));
                    } else {
                        MyHelpers.setFacebookBanner1(null);
                    }
                    if (response.getString("facebook_banner_id_2") != null && !response.getString("facebook_banner_id_2").isEmpty()) {
                        MyHelpers.setFacebookBanner2(response.getString("facebook_banner_id_2"));
                    } else {
                        MyHelpers.setFacebookBanner2(null);
                    }
                    //Facebook Native
                    if (response.getString("facebook_native_id") != null && !response.getString("facebook_native_id").isEmpty()) {
                        MyHelpers.SetFacebookNative(response.getString("facebook_native_id"));
                    } else {
                        MyHelpers.SetFacebookNative(null);
                    }
                    if (response.getString("facebook_native_id_1") != null && !response.getString("facebook_native_id_1").isEmpty()) {
                        MyHelpers.SetFacebookNative1(response.getString("facebook_native_id_1"));
                    } else {
                        MyHelpers.SetFacebookNative1(null);
                    }
                    if (response.getString("facebook_native_id_2") != null && !response.getString("facebook_native_id_2").isEmpty()) {
                        MyHelpers.SetFacebookNative2(response.getString("facebook_native_id_2"));
                    } else {
                        MyHelpers.SetFacebookNative2(null);
                    }

                    //Facebook Open ADS
                    if (response.getString("facebook_open_id") != null && !response.getString("facebook_open_id").isEmpty()) {
                        MyHelpers.setfacebook_open_ad_id(response.getString("facebook_open_id"));
                    } else {
                        MyHelpers.setfacebook_open_ad_id(null);
                    }
                    //Facebook Interstitial
                    if (response.getString("facebook_interstitial_id") != null && !response.getString("facebook_interstitial_id").isEmpty()) {
                        MyHelpers.SetFacebookInter(response.getString("facebook_interstitial_id"));
                    } else {
                        MyHelpers.SetFacebookInter(null);
                    }
                    if (response.getString("facebook_interstitial_id_1") != null && !response.getString("facebook_interstitial_id_1").isEmpty()) {
                        MyHelpers.SetFacebookInter1(response.getString("facebook_interstitial_id_1"));
                    } else {
                        MyHelpers.SetFacebookInter1(null);
                    }
                    if (response.getString("facebook_interstitial_id_2") != null && !response.getString("facebook_interstitial_id_2").isEmpty()) {
                        MyHelpers.SetFacebookInter2(response.getString("facebook_interstitial_id_2"));
                    } else {
                        MyHelpers.SetFacebookInter2(null);
                    }

                    /**
                     *  Atme and qureka Link
                     */
                    /*auto link*/
                    MyHelpers.setauto_link_on_off(response.getString("enable_auto_quereka_link"));  //on_off Auto link
                    if (MyHelpers.getauto_link_on_off().equals("1")) {
                        MyHelpers.setauto_link_array(response.getString("auto_quereka_link")); //link Array
                        MyHelpers.setauto_link_timer(response.getString("auto_quereka_time")); //open Timer
                        MyHelpers.Autolink();
                    }
                    /*btn link*/
                    MyHelpers.set_q_link_btn_on_off(response.getString("enable_quereka_link"));  //on_off Q link btn
                    MyHelpers.set_q_link_array(response.getString("quereka_link")); //link Array

                    /**
                     * App Lovin
                     */
                    MyHelpers.setAppLovinEnable(response.getString("enable_applovin_id"));  //on_off App Lovin
                    if (response.getString("applovin_banner") != null && !response.getString("applovin_banner").isEmpty()) {   //Banner
                        MyHelpers.setAppLovinBanner(response.getString("applovin_banner"));
                    } else {
                        MyHelpers.setAppLovinBanner(null);
                    }
                    if (response.getString("applovin_native") != null && !response.getString("applovin_native").isEmpty()) {   //Native
                        MyHelpers.setAppLovinNative(response.getString("applovin_native"));
                    } else {
                        MyHelpers.setAppLovinNative(null);
                    }
                    if (response.getString("applovin_interstitial") != null && !response.getString("applovin_interstitial").isEmpty()) {   //Inter
                        MyHelpers.setAppLovinInter(response.getString("applovin_interstitial"));

                    } else {
                        MyHelpers.setAppLovinInter(null);
                    }

                    /**
                     *
                     * Unity
                     */
                    MyHelpers.setUnityEnable(response.getString("enable_unity_id"));  //on_off Unity
                    if (response.getString("unity_game_id") != null && !response.getString("unity_game_id").isEmpty()) {   //Unity ID
                        MyHelpers.setUnityAppID(response.getString("unity_game_id"));
                        UnityAds.initialize(MyHelpers.instance, MyHelpers.getUnityAppID(), false);
                    } else {
                        MyHelpers.setUnityAppID(null);
                    }
                    if (response.getString("unity_banner") != null && !response.getString("unity_banner").isEmpty()) { //Unity Banner ID
                        MyHelpers.setUnityBannerID(response.getString("unity_banner"));
                    } else {
                        MyHelpers.setUnityBannerID(null);
                    }
                    if (response.getString("unity_interstitial") != null && !response.getString("unity_interstitial").isEmpty()) {  //Unity Inter ID
                        MyHelpers.setUnityInterID(response.getString("unity_interstitial"));
                    } else {
                        MyHelpers.setUnityInterID(null);
                    }

                    /**
                     * Custom
                     */
                    MyHelpers.setCustomEnable(response.getString("custom_ads_switch"));  //on_off Custom ads

                    /**
                     * Back Button
                     */
                    MyHelpers.setBackAdsOnOff(response.getString("enable_back_button"));
                    if (response.getString("back_button_counter") != null && !response.getString("back_button_counter").isEmpty()) {
                        MyHelpers.setBackCounter(Integer.parseInt(response.getString("back_button_counter")));  //skip ads number
                    } else {
                        MyHelpers.setBackCounter(5000);
                    }

                    /**
                     * Skip ads
                     * 1 - Inter
                     * 2 - Native
                     * 3 - Banner
                     *
                     * btn number 0 stop ads
                     * */
                    if (response.getString("regular_button_counter") != null && !response.getString("regular_button_counter").isEmpty()) {
                        MyHelpers.setCounter_Inter(Integer.parseInt(response.getString("regular_button_counter")));
                    } else {
                        MyHelpers.setCounter_Inter(5000);
                    }
                    if (response.getString("skip_native_ad") != null && !response.getString("skip_native_ad").isEmpty()) {
                        MyHelpers.setCounter_Native(Integer.parseInt(response.getString("skip_native_ad")));
                    } else {
                        MyHelpers.setCounter_Native(5000);
                    }
                    if (response.getString("skip_banner_ad") != null && !response.getString("skip_banner_ad").isEmpty()) {
                        MyHelpers.setCounter_Banner(Integer.parseInt(response.getString("skip_banner_ad")));
                    } else {
                        MyHelpers.setCounter_Banner(5000);
                    }

                    /**
                     * App Live Status
                     */

                    if (PackName.equals("Test")) {
                        MyHelpers.setlive_status("1");
                    } else {
                        MyHelpers.setlive_status(response.getString("live"));
                    }

                    /**
                     * MIX ads
                     * 1 - Inter
                     * 2 - Native
                     * 3 - Banner
                     * */
                    MyHelpers.setmix_ad_on_off(response.getString("mix_ad"));

                    //Mix Ads Counter
                    if (response.getString("mix_ad_name") != null && !response.getString("mix_ad_name").isEmpty()) {
                        MyHelpers.setmix_ad_name(response.getString("mix_ad_name"));
                        String[] Ads_number = MyHelpers.getmix_ad_name().split(",");
                        MyHelpers.setmix_ad_counter_banner(Integer.parseInt(Ads_number[0]));
                        MyHelpers.setmix_ad_counter_native(Integer.parseInt(Ads_number[1]));
                        MyHelpers.setmix_ad_counter_inter(Integer.parseInt(Ads_number[2]));
                    } else {
                        MyHelpers.setmix_ad_name(null);
                        MyHelpers.setmix_ad_counter_inter(5000);
                        MyHelpers.setmix_ad_counter_native(5000);
                        MyHelpers.setmix_ad_counter_banner(5000);
                    }


                    //Mix Ads Name
                    if (response.getString("mix_ad_banner") != null && !response.getString("mix_ad_banner").isEmpty()) {
                        MyHelpers.setmix_ad_banner(response.getString("mix_ad_banner"));
                    } else {
                        MyHelpers.setmix_ad_banner(null);
                    }

                    if (response.getString("mix_ad_native") != null && !response.getString("mix_ad_native").isEmpty()) {
                        MyHelpers.setmix_ad_native(response.getString("mix_ad_native"));
                    } else {
                        MyHelpers.setmix_ad_native(null);
                    }

                    if (response.getString("mix_ad_counter") != null && !response.getString("mix_ad_counter").isEmpty()) {
                        MyHelpers.setmix_ad_inter(response.getString("mix_ad_counter"));
                    } else {
                        MyHelpers.setmix_ad_inter(null);
                    }


                    /**
                     * Skip Country
                     */
                    MyHelpers.setSkip_country_on_off(response.getString("off_ad_country"));
                    if (MyHelpers.getSkip_country_on_off().equals("1")) {
                        if (response.getString("off_ad_country_name") != null && !response.getString("off_ad_country_name").isEmpty()) {
                            MyHelpers.setSkip_country_list(response.getString("off_ad_country_name"));
                        } else {
                            MyHelpers.setSkip_country_list(null);
                        }
                    }


                    /**
                     * Extra data
                     */
                    extra_switch_1 = response.getString("extra_switch_1");
                    extra_switch_2 = response.getString("extra_switch_2");
                    extra_switch_3 = response.getString("extra_switch_3");
                    extra_switch_4 = response.getString("extra_switch_4");
                    extra_text_1 = response.getString("extra_text_1");
                    extra_text_2 = response.getString("extra_text_2");
                    extra_text_3 = response.getString("extra_text_3");
                    extra_text_4 = response.getString("extra_text_4");

                    /**
                     * Other App Open
                     */
                    MyHelpers.setOtherAppsShow(response.getString("replace_app"));
                    MyHelpers.setOtherAppsShowLink(response.getString("new_app_link"));
                    if (MyHelpers.getOtherAppsShow().equals("1")) {
                        MyHelpers.Entery_UpdateApps = 2;
                        context.startActivity(new Intent(context, UpdateAppActivity.class));
                        return;
                    }

                    /**
                     * Update Our App
                     */
                    MyHelpers.setUpdateApps(response.getString("update_app"));
                    MyHelpers.setAppversioncode(response.getString("version_code"));
                    if (MyHelpers.getUpdateApps().equals("1")) {
                        if (!MyHelpers.getAppversioncode().equals(versionCode)) {
                            MyHelpers.Entery_UpdateApps = 1;
                            context.startActivity(new Intent(context, UpdateAppActivity.class));
                            return;
                        }
                    }


                    /**
                     * Next App
                     */
                    if (MyHelpers.getSkip_country_on_off().equals("1") && CheckCountry(MyHelpers.getSkip_country_list())) {
                        MyHelpers.setGoogleEnable("0");
                        MyHelpers.setFacebookEnable("0");
                        MyHelpers.setauto_link_on_off("0");
                        MyHelpers.set_q_link_btn_on_off("0");
                        MyHelpers.setAppLovinEnable("0");
                        MyHelpers.setUnityEnable("0");
                        MyHelpers.setCustomEnable("0");
                        MyHelpers.setmix_ad_on_off("0");
                        MyHelpers.setBackAdsOnOff("0");
                        NextIntent(contextx, intentx);
                    } else {
                        ShowADS();
                    }
                    AllAdsPreLoad();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }

    public static void NextIntent(Context context, Intent intent) {
        context.startActivity(intent);
        ((Activity) context).finish();
    }

    /**
     * Show Ads
     */
    private static void ShowADS() {

        if (MyHelpers.getmix_ad_on_off().equals("1")) {
            MixOpenAds(String.valueOf(MyHelpers.getmix_ad_inter().charAt(0)));
            return;
        }

        if (MyHelpers.getGoogleEnable().equals("1") && MyHelpers.getlive_status().equals("1")) {

            GoogleAppOpen();

        } else if (MyHelpers.getFacebookEnable().equals("1") && MyHelpers.getlive_status().equals("1")) {

            FaceBookAppOpen();

        } else if (MyHelpers.getAppLovinEnable().equals("1")) {

            AppLovingAppOpen();

        } else if (MyHelpers.getUnityEnable().equals("1")) {

            UnityAppOpen();

        } else if (MyHelpers.getCustomEnable().equals("1")) {

            CustomOpenAds();

        } else {
            NextIntent(contextx, intentx);
        }
    }

    private static void UnityAppOpen() {

        if (MyHelpers.getUnityInterID() != null && !MyHelpers.getUnityInterID().isEmpty()) {

            UnityAds.load(MyHelpers.getUnityInterID(), new IUnityAdsLoadListener() {
                @Override
                public void onUnityAdsAdLoaded(String placementId) {
                    UnityAds.show((Activity) contextx, MyHelpers.getUnityInterID(), new UnityAdsShowOptions(), new IUnityAdsShowListener() {
                        @Override
                        public void onUnityAdsShowFailure(String placementId, UnityAds.UnityAdsShowError error, String message) {
                            /*Unity Mix Auto Load Inter*/
                            if (MyHelpers.getUnityInterID() != null && !MyHelpers.getUnityInterID().isEmpty()) {
                                InterClass.UnityInterPreLoad();
                            }
                            FailsAds("u");
                        }

                        @Override
                        public void onUnityAdsShowStart(String placementId) {


                        }

                        @Override
                        public void onUnityAdsShowClick(String placementId) {

                        }

                        @Override
                        public void onUnityAdsShowComplete(String placementId, UnityAds.UnityAdsShowCompletionState state) {
                            NextIntent(contextx, intentx);
                            /*Unity Mix Auto Load Inter*/
                            if (MyHelpers.getUnityInterID() != null && !MyHelpers.getUnityInterID().isEmpty()) {
                                InterClass.UnityInterPreLoad();
                            }
                        }
                    });
                }

                @Override
                public void onUnityAdsFailedToLoad(String placementId, UnityAds.UnityAdsLoadError error, String message) {
                    /*Unity Mix Auto Load Inter*/
                    if (MyHelpers.getUnityInterID() != null && !MyHelpers.getUnityInterID().isEmpty()) {
                        InterClass.UnityInterPreLoad();
                    }
                    FailsAds("u");
                }
            });

        } else {
            FailsAds("u");
        }

    }

    private static void AppLovingAppOpen() {

        if (MyHelpers.getAppLovinInter() != null && !MyHelpers.getAppLovinInter().isEmpty()) {
            MaxInterstitialAd interstitialAd = new MaxInterstitialAd(MyHelpers.getAppLovinInter(), (Activity) contextx);
            interstitialAd.setListener(new MaxAdListener() {
                @Override
                public void onAdLoaded(MaxAd ad) {
                    if (interstitialAd.isReady()) {
                        interstitialAd.showAd();
                    } else {
                        FailsAds("a");
                        /*AppLoving Inter PreLoad*/
                        if (MyHelpers.getAppLovinInter() != null && !MyHelpers.getAppLovinInter().isEmpty()) {
                            InterClass.AppLovingInterPreLoad();
                        }
                    }
                }

                @Override
                public void onAdDisplayed(MaxAd ad) {
                }

                @Override
                public void onAdHidden(MaxAd ad) {
                    NextIntent(contextx, intentx);
                    /*AppLoving Inter PreLoad*/
                    if (MyHelpers.getAppLovinInter() != null && !MyHelpers.getAppLovinInter().isEmpty()) {
                        InterClass.AppLovingInterPreLoad();
                    }

                }

                @Override
                public void onAdClicked(MaxAd ad) {

                }

                @Override
                public void onAdLoadFailed(String adUnitId, MaxError error) {
                    FailsAds("a");
                    /*AppLoving Inter PreLoad*/
                    if (MyHelpers.getAppLovinInter() != null && !MyHelpers.getAppLovinInter().isEmpty()) {
                        InterClass.AppLovingInterPreLoad();
                    }
                }

                @Override
                public void onAdDisplayFailed(MaxAd ad, MaxError error) {

                }
            });
            interstitialAd.loadAd();
        } else {
            FailsAds("a");
        }

    }

    private static void FaceBookAppOpen() {

        if (MyHelpers.getfacebook_open_ad_id() != null && !MyHelpers.getfacebook_open_ad_id().isEmpty()) {

            com.facebook.ads.InterstitialAd interstitialAd_FB_1 = new com.facebook.ads.InterstitialAd(contextx, MyHelpers.getfacebook_open_ad_id());
            InterstitialAdListener adListener = new InterstitialAdListener() {
                @Override
                public void onInterstitialDisplayed(Ad ad) {

                }

                @Override
                public void onInterstitialDismissed(Ad ad) {
                    NextIntent(contextx, intentx);
                }

                @Override
                public void onError(Ad ad, com.facebook.ads.AdError adError) {
                    FailsAds("f");
                }

                @Override
                public void onAdLoaded(Ad ad) {
                    if (interstitialAd_FB_1 != null) {
                        interstitialAd_FB_1.show();
                    } else {
                        FailsAds("f");
                    }
                }

                @Override
                public void onAdClicked(Ad ad) {

                }

                @Override
                public void onLoggingImpression(Ad ad) {

                }
            };
            interstitialAd_FB_1.loadAd(interstitialAd_FB_1.buildLoadAdConfig().withAdListener(adListener).build());

        } else {
            FailsAds("f");
        }

    }

    private static void GoogleAppOpen() {

        if (MyHelpers.getGoogle_OpenADS() != null && !MyHelpers.getGoogle_OpenADS().isEmpty()) {

            try {
                isShowOpen = false;
                AppOpenManager.OnAppOpenClose onAppOpenClose = new AppOpenManager.OnAppOpenClose() {
                    @Override
                    public void OnAppOpenFailToLoad() {
                        if (isShowOpen) {
                            isShowOpen = false;
                        }

                        if (checkAppOpen) {
                            checkAppOpen = false;
                            FailsAds("g");
                        }
                    }

                    @Override
                    public void OnAppOpenClose() {

                        if (checkAppOpen) {
                            checkAppOpen = false;
                        }

                        if (isShowOpen) {
                            isShowOpen = false;
                        }

                        if (!OpenAdsStatus) {
                            OpenAdsStatus = true;
                            NextIntent(contextx, intentx);
                        }


                    }
                };
                isShowOpen = true;
                appOpenManager = new AppOpenManager(MyHelpers.getGoogle_OpenADS(), MyHelpers.getInstant(), onAppOpenClose);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            if (checkAppOpen) {
                checkAppOpen = false;
                FailsAds("g");
            }
        }

    }

    private static void CustomOpenAds() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (customads_status) {
                    if (SplashHelp.adsModals.size() == 0) {
                        NextIntent(contextx, intentx);
                        return;
                    }
                    NextIntent(contextx, intentx);
                    contextx.startActivity(new Intent(contextx, CustomAdsInterActivity.class));
                } else {
                    CustomOpenAds();
                }
            }
        }, 100);
    }

    /**
     * Fails Ads
     */
    public static void FailsAds(String Skip) {

        if (Skip.equals("g")) {

            if (MyHelpers.getfacebook_open_ad_id() != null && !MyHelpers.getfacebook_open_ad_id().isEmpty() && MyHelpers.getlive_status().equals("1")) {
                com.facebook.ads.InterstitialAd interstitialAd_FB_1 = new com.facebook.ads.InterstitialAd(contextx, MyHelpers.getfacebook_open_ad_id());
                InterstitialAdListener adListener = new InterstitialAdListener() {
                    @Override
                    public void onInterstitialDisplayed(Ad ad) {

                    }

                    @Override
                    public void onInterstitialDismissed(Ad ad) {
                        NextIntent(contextx, intentx);
                    }

                    @Override
                    public void onError(Ad ad, com.facebook.ads.AdError adError) {
                        GoogleandFacebookFails();
                    }

                    @Override
                    public void onAdLoaded(Ad ad) {
                        if (interstitialAd_FB_1 != null) {
                            interstitialAd_FB_1.show();
                        } else {
                            GoogleandFacebookFails();
                        }
                    }

                    @Override
                    public void onAdClicked(Ad ad) {

                    }

                    @Override
                    public void onLoggingImpression(Ad ad) {

                    }
                };
                interstitialAd_FB_1.loadAd(interstitialAd_FB_1.buildLoadAdConfig().withAdListener(adListener).build());
            } else {
                GoogleandFacebookFails();
            }

        } else if (Skip.equals("f")) {

            if (MyHelpers.getGoogle_OpenADS() != null && !MyHelpers.getGoogle_OpenADS().isEmpty() && MyHelpers.getlive_status().equals("1")) {
                isShowOpen = false;
                AppOpenManager.OnAppOpenClose onAppOpenClose = new AppOpenManager.OnAppOpenClose() {
                    @Override
                    public void OnAppOpenFailToLoad() {

                        if (isShowOpen) {
                            isShowOpen = false;
                        }

                        if (checkAppOpen) {
                            checkAppOpen = false;
                            GoogleandFacebookFails();
                        }

                    }

                    @Override
                    public void OnAppOpenClose() {

                        if (checkAppOpen) {
                            checkAppOpen = false;
                        }

                        if (isShowOpen) {
                            isShowOpen = false;
                        }
                        if (!OpenAdsStatus) {
                            OpenAdsStatus = true;
                            NextIntent(contextx, intentx);
                        }

                    }
                };
                isShowOpen = true;
                appOpenManager = new AppOpenManager(MyHelpers.getGoogle_OpenADS(), MyHelpers.getInstant(), onAppOpenClose);
            } else {
                GoogleandFacebookFails();
            }

        } else if (Skip.equals("a")) {

            if (MyHelpers.getGoogle_OpenADS() != null && !MyHelpers.getGoogle_OpenADS().isEmpty() && MyHelpers.getlive_status().equals("1")) {

                isShowOpen = false;
                AppOpenManager.OnAppOpenClose onAppOpenClose = new AppOpenManager.OnAppOpenClose() {
                    @Override
                    public void OnAppOpenFailToLoad() {
                        if (isShowOpen) {
                            isShowOpen = false;
                        }

                        if (checkAppOpen) {
                            checkAppOpen = false;
                            FailAdsAppLovin_ShowFacebookUnityCustom();
                        }


                    }


                    @Override
                    public void OnAppOpenClose() {

                        if (checkAppOpen) {
                            checkAppOpen = false;
                        }

                        if (isShowOpen) {
                            isShowOpen = false;
                        }
                        if (!OpenAdsStatus) {
                            OpenAdsStatus = true;
                            NextIntent(contextx, intentx);
                        }
                    }
                };
                isShowOpen = true;
                appOpenManager = new AppOpenManager(MyHelpers.getGoogle_OpenADS(), MyHelpers.getInstant(), onAppOpenClose);

            } else {
                FailAdsAppLovin_ShowFacebookUnityCustom();
            }

        } else if (Skip.equals("u")) {

            if (MyHelpers.getGoogle_OpenADS() != null && !MyHelpers.getGoogle_OpenADS().isEmpty() && MyHelpers.getlive_status().equals("1")) {
                isShowOpen = false;
                AppOpenManager.OnAppOpenClose onAppOpenClose = new AppOpenManager.OnAppOpenClose() {
                    @Override
                    public void OnAppOpenFailToLoad() {
                        if (isShowOpen) {
                            isShowOpen = false;
                        }

                        if (checkAppOpen) {
                            checkAppOpen = false;
                            FailUnity_ShowFacebookAppLovinCustom();
                        }


                    }

                    @Override
                    public void OnAppOpenClose() {

                        if (checkAppOpen) {
                            checkAppOpen = false;
                        }

                        if (isShowOpen) {
                            isShowOpen = false;
                        }

                        if (!OpenAdsStatus) {
                            OpenAdsStatus = true;
                            NextIntent(contextx, intentx);
                        }
                    }
                };
                isShowOpen = true;
                appOpenManager = new AppOpenManager(MyHelpers.getGoogle_OpenADS(), MyHelpers.getInstant(), onAppOpenClose);

            } else {
                FailUnity_ShowFacebookAppLovinCustom();
            }

        } else {
            CustomOpenAds();
        }
    }

    private static void GoogleandFacebookFails() {

        if (MyHelpers.getAppLovinInter() != null && !MyHelpers.getAppLovinInter().isEmpty()) {

            MaxInterstitialAd interstitialAd = new MaxInterstitialAd(MyHelpers.getAppLovinInter(), (Activity) contextx);
            interstitialAd.setListener(new MaxAdListener() {
                @Override
                public void onAdLoaded(MaxAd ad) {
                    if (interstitialAd.isReady()) {
                        interstitialAd.showAd();
                    } else {
                        /*AppLoving Inter PreLoad*/
                        if (MyHelpers.getAppLovinInter() != null && !MyHelpers.getAppLovinInter().isEmpty()) {
                            InterClass.AppLovingInterPreLoad();
                        }

                        if (MyHelpers.getUnityInterID() != null && !MyHelpers.getUnityInterID().isEmpty()) {
                            FailsAdsUnityShow();
                        } else {
                            CustomOpenAds();
                        }

                    }
                }

                @Override
                public void onAdDisplayed(MaxAd ad) {

                }

                @Override
                public void onAdHidden(MaxAd ad) {
                    NextIntent(contextx, intentx);
                    /*AppLoving Inter PreLoad*/
                    if (MyHelpers.getAppLovinInter() != null && !MyHelpers.getAppLovinInter().isEmpty()) {
                        InterClass.AppLovingInterPreLoad();
                    }
                }

                @Override
                public void onAdClicked(MaxAd ad) {

                }

                @Override
                public void onAdLoadFailed(String adUnitId, MaxError error) {
                    /*AppLoving Inter PreLoad*/
                    if (MyHelpers.getAppLovinInter() != null && !MyHelpers.getAppLovinInter().isEmpty()) {
                        InterClass.AppLovingInterPreLoad();
                    }
                    //Fail Code
                    if (MyHelpers.getUnityInterID() != null && !MyHelpers.getUnityInterID().isEmpty()) {
                        FailsAdsUnityShow();
                    } else {
                        CustomOpenAds();
                    }
                }

                @Override
                public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                    //
                }
            });
            interstitialAd.loadAd();
        } else if (MyHelpers.getUnityInterID() != null && !MyHelpers.getUnityInterID().isEmpty()) {
            FailsAdsUnityShow();
        } else {
            CustomOpenAds();
        }
    }

    public static void FailAdsAppLovin_ShowFacebookUnityCustom() {
        if (MyHelpers.getfacebook_open_ad_id() != null && !MyHelpers.getfacebook_open_ad_id().isEmpty()) {
            com.facebook.ads.InterstitialAd interstitialAd_FB_1 = new com.facebook.ads.InterstitialAd(contextx, MyHelpers.getfacebook_open_ad_id());
            InterstitialAdListener adListener = new InterstitialAdListener() {
                @Override
                public void onInterstitialDisplayed(Ad ad) {

                }

                @Override
                public void onInterstitialDismissed(Ad ad) {
                    NextIntent(contextx, intentx);
                }

                @Override
                public void onError(Ad ad, com.facebook.ads.AdError adError) {
                    if (MyHelpers.getUnityInterID() != null && !MyHelpers.getUnityInterID().isEmpty()) {
                        FailsAdsUnityShow();
                    } else {
                        CustomOpenAds();
                    }
                }

                @Override
                public void onAdLoaded(Ad ad) {
                    if (interstitialAd_FB_1 != null) {
                        interstitialAd_FB_1.show();
                    } else {
                        if (MyHelpers.getUnityInterID() != null && !MyHelpers.getUnityInterID().isEmpty()) {
                            FailsAdsUnityShow();
                        } else {
                            CustomOpenAds();
                        }
                    }
                }

                @Override
                public void onAdClicked(Ad ad) {

                }

                @Override
                public void onLoggingImpression(Ad ad) {

                }
            };
            interstitialAd_FB_1.loadAd(interstitialAd_FB_1.buildLoadAdConfig().withAdListener(adListener).build());
        } else if (MyHelpers.getUnityInterID() != null && !MyHelpers.getUnityInterID().isEmpty()) {
            FailsAdsUnityShow();
        } else {
            CustomOpenAds();
        }
    }

    public static void FailUnity_ShowFacebookAppLovinCustom() {

        if (MyHelpers.getfacebook_open_ad_id() != null && !MyHelpers.getfacebook_open_ad_id().isEmpty()) {
            com.facebook.ads.InterstitialAd interstitialAd_FB_1 = new com.facebook.ads.InterstitialAd(contextx, MyHelpers.getfacebook_open_ad_id());
            InterstitialAdListener adListener = new InterstitialAdListener() {
                @Override
                public void onInterstitialDisplayed(Ad ad) {

                }

                @Override
                public void onInterstitialDismissed(Ad ad) {
                    NextIntent(contextx, intentx);
                }

                @Override
                public void onError(Ad ad, com.facebook.ads.AdError adError) {
                    if (MyHelpers.getAppLovinInter() != null && !MyHelpers.getAppLovinInter().isEmpty()) {

                        MaxInterstitialAd interstitialAd = new MaxInterstitialAd(MyHelpers.getAppLovinInter(), (Activity) contextx);
                        interstitialAd.setListener(new MaxAdListener() {
                            @Override
                            public void onAdLoaded(MaxAd ad) {
                                if (interstitialAd.isReady()) {
                                    interstitialAd.showAd();
                                } else {
                                    /*AppLoving Inter PreLoad*/
                                    if (MyHelpers.getAppLovinInter() != null && !MyHelpers.getAppLovinInter().isEmpty()) {
                                        InterClass.AppLovingInterPreLoad();
                                    }
                                    CustomOpenAds();
                                }
                            }

                            @Override
                            public void onAdDisplayed(MaxAd ad) {

                            }

                            @Override
                            public void onAdHidden(MaxAd ad) {
                                NextIntent(contextx, intentx);
                                /*AppLoving Inter PreLoad*/
                                if (MyHelpers.getAppLovinInter() != null && !MyHelpers.getAppLovinInter().isEmpty()) {
                                    InterClass.AppLovingInterPreLoad();
                                }
                            }

                            @Override
                            public void onAdClicked(MaxAd ad) {

                            }

                            @Override
                            public void onAdLoadFailed(String adUnitId, MaxError error) {
                                //Fail Code
                                /*AppLoving Inter PreLoad*/
                                if (MyHelpers.getAppLovinInter() != null && !MyHelpers.getAppLovinInter().isEmpty()) {
                                    InterClass.AppLovingInterPreLoad();
                                }
                                CustomOpenAds();
                            }

                            @Override
                            public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                                //
                            }
                        });
                        interstitialAd.loadAd();
                    } else {
                        CustomOpenAds();
                    }
                }

                @Override
                public void onAdLoaded(Ad ad) {
                    if (interstitialAd_FB_1 != null) {
                        interstitialAd_FB_1.show();
                    } else {
                        if (MyHelpers.getAppLovinInter() != null && !MyHelpers.getAppLovinInter().isEmpty()) {

                            MaxInterstitialAd interstitialAd = new MaxInterstitialAd(MyHelpers.getAppLovinInter(), (Activity) contextx);
                            interstitialAd.setListener(new MaxAdListener() {
                                @Override
                                public void onAdLoaded(MaxAd ad) {
                                    if (interstitialAd.isReady()) {
                                        interstitialAd.showAd();
                                    } else {
                                        /*AppLoving Inter PreLoad*/
                                        if (MyHelpers.getAppLovinInter() != null && !MyHelpers.getAppLovinInter().isEmpty()) {
                                            InterClass.AppLovingInterPreLoad();
                                        }
                                        CustomOpenAds();
                                    }
                                }

                                @Override
                                public void onAdDisplayed(MaxAd ad) {

                                }

                                @Override
                                public void onAdHidden(MaxAd ad) {
                                    /*AppLoving Inter PreLoad*/
                                    if (MyHelpers.getAppLovinInter() != null && !MyHelpers.getAppLovinInter().isEmpty()) {
                                        InterClass.AppLovingInterPreLoad();
                                    }
                                    NextIntent(contextx, intentx);
                                }

                                @Override
                                public void onAdClicked(MaxAd ad) {

                                }

                                @Override
                                public void onAdLoadFailed(String adUnitId, MaxError error) {
                                    /*AppLoving Inter PreLoad*/
                                    if (MyHelpers.getAppLovinInter() != null && !MyHelpers.getAppLovinInter().isEmpty()) {
                                        InterClass.AppLovingInterPreLoad();
                                    }
                                    //Fail Code
                                    CustomOpenAds();

                                }

                                @Override
                                public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                                    //
                                }
                            });
                            interstitialAd.loadAd();
                        } else {
                            CustomOpenAds();
                        }
                    }
                }

                @Override
                public void onAdClicked(Ad ad) {

                }

                @Override
                public void onLoggingImpression(Ad ad) {

                }
            };
            interstitialAd_FB_1.loadAd(interstitialAd_FB_1.buildLoadAdConfig().withAdListener(adListener).build());

        } else if (MyHelpers.getAppLovinInter() != null && !MyHelpers.getAppLovinInter().isEmpty()) {
            MaxInterstitialAd interstitialAd = new MaxInterstitialAd(MyHelpers.getAppLovinInter(), (Activity) contextx);
            interstitialAd.setListener(new MaxAdListener() {
                @Override
                public void onAdLoaded(MaxAd ad) {
                    if (interstitialAd.isReady()) {
                        interstitialAd.showAd();
                    } else {
                        /*AppLoving Inter PreLoad*/
                        if (MyHelpers.getAppLovinInter() != null && !MyHelpers.getAppLovinInter().isEmpty()) {
                            InterClass.AppLovingInterPreLoad();
                        }
                        CustomOpenAds();
                    }
                }

                @Override
                public void onAdDisplayed(MaxAd ad) {

                }

                @Override
                public void onAdHidden(MaxAd ad) {
                    NextIntent(contextx, intentx);
                    /*AppLoving Inter PreLoad*/
                    if (MyHelpers.getAppLovinInter() != null && !MyHelpers.getAppLovinInter().isEmpty()) {
                        InterClass.AppLovingInterPreLoad();
                    }
                }

                @Override
                public void onAdClicked(MaxAd ad) {

                }

                @Override
                public void onAdLoadFailed(String adUnitId, MaxError error) {
                    /*AppLoving Inter PreLoad*/
                    if (MyHelpers.getAppLovinInter() != null && !MyHelpers.getAppLovinInter().isEmpty()) {
                        InterClass.AppLovingInterPreLoad();
                    }
                    CustomOpenAds();
                }

                @Override
                public void onAdDisplayFailed(MaxAd ad, MaxError error) {

                }
            });
            interstitialAd.loadAd();
        } else {
            CustomOpenAds();
        }
    }

    private static void FailsAdsUnityShow() {
        UnityAds.load(MyHelpers.getUnityInterID(), new IUnityAdsLoadListener() {
            @Override
            public void onUnityAdsAdLoaded(String placementId) {
                UnityAds.show((Activity) contextx, MyHelpers.getUnityInterID(), new UnityAdsShowOptions(), new IUnityAdsShowListener() {
                    @Override
                    public void onUnityAdsShowFailure(String placementId, UnityAds.UnityAdsShowError error, String message) {
                        /*Unity Mix Auto Load Inter*/
                        if (MyHelpers.getUnityInterID() != null && !MyHelpers.getUnityInterID().isEmpty()) {
                            InterClass.UnityInterPreLoad();
                        }
                        CustomOpenAds();
                    }

                    @Override
                    public void onUnityAdsShowStart(String placementId) {


                    }

                    @Override
                    public void onUnityAdsShowClick(String placementId) {

                    }

                    @Override
                    public void onUnityAdsShowComplete(String placementId, UnityAds.UnityAdsShowCompletionState state) {
                        NextIntent(contextx, intentx);
                        /*Unity Mix Auto Load Inter*/
                        if (MyHelpers.getUnityInterID() != null && !MyHelpers.getUnityInterID().isEmpty()) {
                            InterClass.UnityInterPreLoad();
                        }

                    }
                });
            }

            @Override
            public void onUnityAdsFailedToLoad(String placementId, UnityAds.UnityAdsLoadError error, String message) {
                /*Unity Mix Auto Load Inter*/
                if (MyHelpers.getUnityInterID() != null && !MyHelpers.getUnityInterID().isEmpty()) {
                    InterClass.UnityInterPreLoad();
                }
                CustomOpenAds();
            }
        });
    }

    /**
     * Interstitial PreLoad
     */

    public static void AllAdsPreLoad() {
        InterClass.main_context = (Activity) contextx;
        BannerClass.main_context = (Activity) contextx;
        NativeClass.main_context = (Activity) contextx;
        MixAdOnBanner();
        MixAdOnNative();
        MixAdOnInter();
    }

    private static void MixAdOnBanner() {
        /**
         * Banner
         */
        /*Google Banner*/
        if (MyHelpers.getGoogleBanner() != null && !MyHelpers.getGoogleBanner().isEmpty() && MyHelpers.getlive_status().equals("1")) {

            if (MyHelpers.getGoogleBanner().equals(MyHelpers.getGoogleBanner1()) && MyHelpers.getGoogleBanner().equals(MyHelpers.getGoogleBanner2()) && MyHelpers.getGoogleBanner1().equals(MyHelpers.getGoogleBanner2())) {
                MyHelpers.Google_banner_number = 1;
                BannerClass.AutoGoogleBannerID = 1;
                BannerClass.GoogleBannerPreload();

            } else {
                if (MyHelpers.getGoogleBanner2() == null) {
                    MyHelpers.Google_banner_number = 2;
                    BannerClass.GoogleBannerPreload1();
                    BannerClass.GoogleBannerPreload2();

                } else {
                    MyHelpers.Google_banner_number = 3;
                    BannerClass.GoogleBannerPreload1();
                    BannerClass.GoogleBannerPreload2();
                    BannerClass.GoogleBannerPreload3();

                }
            }
        }

        /*Facebook Banner*/
        if (MyHelpers.getFacebookBanner() != null && !MyHelpers.getFacebookBanner().isEmpty() && MyHelpers.getlive_status().equals("1")) {
            BannerClass.AutoLoadFBBannerID = 1;
            BannerClass.FacebookBannerPreLoad();
        }

        /*AppLoving Banner*/
        if (MyHelpers.getAppLovinBanner() != null && !MyHelpers.getAppLovinBanner().isEmpty()) {
            BannerClass.AppLovingBannerPreLoad();
        }

        /*Unity Banner*/
        if (MyHelpers.getUnityBannerID() != null && !MyHelpers.getUnityBannerID().isEmpty()) {
            BannerClass.UnityBannerPreLoad();
        }
    }

    private static void MixAdOnNative() {
        /**
         * Native
         */
        /*Google Native*/
        if (MyHelpers.getGoogleNative() != null && !MyHelpers.getGoogleNative().isEmpty() && MyHelpers.getlive_status().equals("1")) {
            if (MyHelpers.getGoogleNative().equals(MyHelpers.getGoogleNative1()) && MyHelpers.getGoogleNative().equals(MyHelpers.getGoogleNative2()) && MyHelpers.getGoogleNative1().equals(MyHelpers.getGoogleNative2())) {
                MyHelpers.Google_native_number = 1;
                NativeClass.AutoGoogleNativeID = 1;
                NativeClass.GoogleNativePreload();
            } else {
                if (MyHelpers.getGoogleNative2() == null) {
                    MyHelpers.Google_native_number = 2;
                    NativeClass.GoogleNativePreload1();
                    NativeClass.GoogleNativePreload2();
                } else {
                    MyHelpers.Google_native_number = 3;
                    NativeClass.GoogleNativePreload1();
                    NativeClass.GoogleNativePreload2();
                    NativeClass.GoogleNativePreload3();
                }
            }
        }

        /*Facebook Native*/
        if (MyHelpers.getFacebookNative() != null && !MyHelpers.getFacebookNative().isEmpty() && MyHelpers.getlive_status().equals("1")) {
            NativeClass.AutoLoadFBNativeID = 1;
            NativeClass.FacebookNativePreLoad();
        }

        /*AppLoving Native*/
        if (MyHelpers.getAppLovinNative() != null && !MyHelpers.getAppLovinNative().isEmpty()) {
            NativeClass.AppLovingNativePreLoad();
        }

    }

    private static void MixAdOnInter() {
        /**
         * Inter
         */
        /*Google Inter*/
        if (MyHelpers.getGoogleInter() != null && !MyHelpers.getGoogleInter().isEmpty() && MyHelpers.getlive_status().equals("1")) {

            //Inter
            if (MyHelpers.getGoogleInter().equals(MyHelpers.getGoogleInter1()) && MyHelpers.getGoogleInter().equals(MyHelpers.getGoogleInter2()) && MyHelpers.getGoogleInter1().equals(MyHelpers.getGoogleInter2())) {
                MyHelpers.Google_inter_number = 1;
                InterClass.AutoGoogleInterID = 1;
                InterClass.GoogleInterPreload();
            } else {
                if (MyHelpers.getGoogleInter2() == null) {
                    MyHelpers.Google_inter_number = 2;
                    InterClass.GoogleInterPreload1();
                    InterClass.GoogleInterPreload2();
                } else {
                    MyHelpers.Google_inter_number = 3;
                    InterClass.GoogleInterPreload1();
                    InterClass.GoogleInterPreload2();
                    InterClass.GoogleInterPreload3();
                }
            }
        }

        /*Facebook Mix Auto Load Inter*/
        if (MyHelpers.getFacebookInter() != null && !MyHelpers.getFacebookInter().isEmpty() && MyHelpers.getlive_status().equals("1")) {
            InterClass.AutoLoadFBInterID = 1;
            InterClass.FacebookInterPreLoad();
        }

    }


    /**
     * Mix Open Ads
     */

    /*Mix Open*/
    private static void MixOpenAds(String valueOf) {
        if (valueOf.equals("g")) {
            GoogleAppOpen();
        } else if (valueOf.equals("f")) {
            FaceBookAppOpen();
        } else if (valueOf.equals("a")) {
            AppLovingAppOpen();
        } else if (valueOf.equals("u")) {
            UnityAppOpen();
        } else if (valueOf.equals("q")) {
            NextIntent(contextx, intentx);
            MyHelpers.BtnAutolink();
        } else if (valueOf.equals("c")) {
            CustomOpenAds();
        } else {
            CustomOpenAds();
        }
    }

    /**
     * Custom All Ad Load
     */
    public static void CustomAPICalls() {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.addHeader(DEc(Util.pizzuhead), DEc(Util.pizzudians));
        asyncHttpClient.get(DEc(Util.custom) + PackName, new JsonHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);


                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject firstEvent = (JSONObject) response.get(i);
                        adsModals.add(new AdsModal(firstEvent.getString("app_name"), firstEvent.getString("enable_ads"), firstEvent.getString("ad_app_name"), firstEvent.getString("app_description"), firstEvent.getString("app_logo"), firstEvent.getString("app_banner")));
                    }
                    customads_status = true;

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                customads_status = true;
            }
        });
    }

    /**
     * Country Check
     */
    public static String getCountryCode() {
        TelephonyManager tm = (TelephonyManager) contextx.getSystemService(contextx.getApplicationContext().TELEPHONY_SERVICE);
        return tm.getNetworkCountryIso();
    }

    public static Boolean CheckCountry(String Country_name) {
        try {
            List<String> COUNTRY = new ArrayList<String>(Arrays.asList(Country_name.split(",")));
            String tm = getCountryCode();
            for (int i = 0; i < COUNTRY.size(); i++) {
                if (COUNTRY.get(i).equals(tm)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
