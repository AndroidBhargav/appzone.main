package com.appzone.mylibrarys;

import static ProMex.classs.Utils.Util.DEc;

import android.annotation.SuppressLint;
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
import com.facebook.ads.AdSettings;
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

    @SuppressLint("StaticFieldLeak")
    public static Context contexts;
    public static Intent intents;

    public static boolean isShowOpen = false;
    @SuppressLint("StaticFieldLeak")
    public static AppOpenManager appOpenManager;
    public static String PackName = "";

    public static ArrayList<AdsModal> adsModals = new ArrayList<>();


    public static boolean customs_status = false;
    public static boolean OpenAdsStatus = false;

    public static boolean checkAppOpen = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
    }

    /**
     * Api Call
     */
    public static void splash_next(String packageName, String versionCode, Context context, Intent intent) {

        PackName = packageName;
        contexts = context;
        intents = intent;

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

                    /*
                      Google
                     */
                    MyHelpers.setGoogleEnable(response.getString("enable_google_admob_id"));
                    //google Banner
                    response.getString("google_admob_banner_id");
                    if (!response.getString("google_admob_banner_id").isEmpty()) {
                        MyHelpers.SetGoogleBanner(response.getString("google_admob_banner_id"));
                    } else {
                        MyHelpers.SetGoogleBanner(null);
                    }
                    response.getString("google_admob_banner_id_1");
                    if (!response.getString("google_admob_banner_id_1").isEmpty()) {
                        MyHelpers.SetGoogleBanner1(response.getString("google_admob_banner_id_1"));
                    } else {
                        MyHelpers.SetGoogleBanner1(null);
                    }
                    response.getString("google_admob_banner_id_2");
                    if (!response.getString("google_admob_banner_id_2").isEmpty()) {
                        MyHelpers.SetGoogleBanner2(response.getString("google_admob_banner_id_2"));
                    } else {
                        MyHelpers.SetGoogleBanner2(null);
                    }
                    //google Native
                    response.getString("google_admob_native_id");
                    if (!response.getString("google_admob_native_id").isEmpty()) {
                        MyHelpers.SetGoogleNative(response.getString("google_admob_native_id"));
                    } else {
                        MyHelpers.SetGoogleNative(null);
                    }
                    response.getString("google_admob_native_id_1");
                    if (!response.getString("google_admob_native_id_1").isEmpty()) {
                        MyHelpers.SetGoogleNative1(response.getString("google_admob_native_id_1"));
                    } else {
                        MyHelpers.SetGoogleNative1(null);
                    }
                    response.getString("google_admob_native_id_2");
                    if (!response.getString("google_admob_native_id_2").isEmpty()) {
                        MyHelpers.SetGoogleNative2(response.getString("google_admob_native_id_2"));
                    } else {
                        MyHelpers.SetGoogleNative2(null);
                    }
                    //google Native Btn Name
                    response.getString("google_button_name");
                    if (!response.getString("google_button_name").isEmpty()) {
                        MyHelpers.setGooglebutton_name(response.getString("google_button_name"));
                    } else {
                        MyHelpers.setGooglebutton_name(null);
                    }
                    //google Native Btn color
                    response.getString("google_button_color");
                    if (!response.getString("google_button_color").isEmpty()) {
                        MyHelpers.setGooglebutton_color(response.getString("google_button_color"));
                    } else {
                        MyHelpers.setGooglebutton_color("#000000");
                    }
                    // google Open ADS
                    response.getString("google_open_id");
                    if (!response.getString("google_open_id").isEmpty()) {
                        MyHelpers.setGoogle_OpenADS(response.getString("google_open_id"));
                    } else {
                        MyHelpers.setGoogle_OpenADS(null);
                    }
                    //google Interstitial
                    response.getString("google_admob_interstitial_id");
                    if (!response.getString("google_admob_interstitial_id").isEmpty()) {
                        MyHelpers.SetGoogleInter(response.getString("google_admob_interstitial_id"));
                    } else {
                        MyHelpers.SetGoogleInter(null);
                    }
                    response.getString("google_admob_interstitial_id_1");
                    if (!response.getString("google_admob_interstitial_id_1").isEmpty()) {
                        MyHelpers.SetGoogleInter1(response.getString("google_admob_interstitial_id_1"));
                    } else {
                        MyHelpers.SetGoogleInter1(null);
                    }
                    response.getString("google_admob_interstitial_id_2");
                    if (!response.getString("google_admob_interstitial_id_2").isEmpty()) {
                        MyHelpers.SetGoogleInter2(response.getString("google_admob_interstitial_id_2"));
                    } else {
                        MyHelpers.SetGoogleInter2(null);
                    }
                    /*
                      Facebook
                     */
                    MyHelpers.setFacebookEnable(response.getString("enable_facebook_id"));
                    //Facebook Banner
                    response.getString("facebook_banner_id");
                    if (!response.getString("facebook_banner_id").isEmpty()) {
                        MyHelpers.setFacebookBanner(response.getString("facebook_banner_id"));
                    } else {
                        MyHelpers.setFacebookBanner(null);
                    }
                    response.getString("facebook_banner_id_1");
                    if (!response.getString("facebook_banner_id_1").isEmpty()) {
                        MyHelpers.setFacebookBanner1(response.getString("facebook_banner_id_1"));
                    } else {
                        MyHelpers.setFacebookBanner1(null);
                    }
                    response.getString("facebook_banner_id_2");
                    if (!response.getString("facebook_banner_id_2").isEmpty()) {
                        MyHelpers.setFacebookBanner2(response.getString("facebook_banner_id_2"));
                    } else {
                        MyHelpers.setFacebookBanner2(null);
                    }
                    //Facebook Native
                    response.getString("facebook_native_id");
                    if (!response.getString("facebook_native_id").isEmpty()) {
                        MyHelpers.SetFacebookNative(response.getString("facebook_native_id"));
                    } else {
                        MyHelpers.SetFacebookNative(null);
                    }
                    response.getString("facebook_native_id_1");
                    if (!response.getString("facebook_native_id_1").isEmpty()) {
                        MyHelpers.SetFacebookNative1(response.getString("facebook_native_id_1"));
                    } else {
                        MyHelpers.SetFacebookNative1(null);
                    }
                    response.getString("facebook_native_id_2");
                    if (!response.getString("facebook_native_id_2").isEmpty()) {
                        MyHelpers.SetFacebookNative2(response.getString("facebook_native_id_2"));
                    } else {
                        MyHelpers.SetFacebookNative2(null);
                    }

                    //Facebook Open ADS
                    response.getString("facebook_open_id");
                    if (!response.getString("facebook_open_id").isEmpty()) {
                        MyHelpers.setfacebook_open_ad_id(response.getString("facebook_open_id"));
                    } else {
                        MyHelpers.setfacebook_open_ad_id(null);
                    }
                    //Facebook Interstitial
                    response.getString("facebook_interstitial_id");
                    if (!response.getString("facebook_interstitial_id").isEmpty()) {
                        MyHelpers.SetFacebookInter(response.getString("facebook_interstitial_id"));
                    } else {
                        MyHelpers.SetFacebookInter(null);
                    }
                    response.getString("facebook_interstitial_id_1");
                    if (!response.getString("facebook_interstitial_id_1").isEmpty()) {
                        MyHelpers.SetFacebookInter1(response.getString("facebook_interstitial_id_1"));
                    } else {
                        MyHelpers.SetFacebookInter1(null);
                    }
                    response.getString("facebook_interstitial_id_2");
                    if (!response.getString("facebook_interstitial_id_2").isEmpty()) {
                        MyHelpers.SetFacebookInter2(response.getString("facebook_interstitial_id_2"));
                    } else {
                        MyHelpers.SetFacebookInter2(null);
                    }

                    /*
                       Atmel and qureka Link
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

                    /*
                      App Lovin
                     */
                    MyHelpers.setAppLovingEnable(response.getString("enable_applovin_id"));  //on_off App Lovin
                    response.getString("applovin_banner");
                    if (!response.getString("applovin_banner").isEmpty()) {   //Banner
                        MyHelpers.setAppLovinBanner(response.getString("applovin_banner"));
                    } else {
                        MyHelpers.setAppLovinBanner(null);
                    }
                    response.getString("applovin_native");
                    if (!response.getString("applovin_native").isEmpty()) {   //Native
                        MyHelpers.setAppLovinNative(response.getString("applovin_native"));
                    } else {
                        MyHelpers.setAppLovinNative(null);
                    }
                    response.getString("applovin_interstitial");
                    if (!response.getString("applovin_interstitial").isEmpty()) {   //Inter
                        MyHelpers.setAppLovinInter(response.getString("applovin_interstitial"));

                    } else {
                        MyHelpers.setAppLovinInter(null);
                    }

                    /*

                      Unity
                     */
                    MyHelpers.setUnityEnable(response.getString("enable_unity_id"));  //on_off Unity
                    response.getString("unity_game_id");
                    if (!response.getString("unity_game_id").isEmpty()) {   //Unity ID
                        MyHelpers.setUnityAppID(response.getString("unity_game_id"));
                        UnityAds.initialize(MyHelpers.instance, MyHelpers.getUnityAppID(), true);
                    } else {
                        MyHelpers.setUnityAppID(null);
                    }
                    response.getString("unity_banner");
                    if (!response.getString("unity_banner").isEmpty()) { //Unity Banner ID
                        MyHelpers.setUnityBannerID(response.getString("unity_banner"));
                    } else {
                        MyHelpers.setUnityBannerID(null);
                    }
                    response.getString("unity_interstitial");
                    if (!response.getString("unity_interstitial").isEmpty()) {  //Unity Inter ID
                        MyHelpers.setUnityInterID(response.getString("unity_interstitial"));
                    } else {
                        MyHelpers.setUnityInterID(null);
                    }

                    /*
                      Custom
                     */
                    MyHelpers.setCustomEnable(response.getString("custom_ads_switch"));  //on_off Custom ads

                    /*
                      Back Button
                     */
                    MyHelpers.setBackAdsOnOff(response.getString("enable_back_button"));
                    response.getString("back_button_counter");
                    if (!response.getString("back_button_counter").isEmpty()) {
                        MyHelpers.setBackCounter(Integer.parseInt(response.getString("back_button_counter")));  //skip ads number
                    } else {
                        MyHelpers.setBackCounter(5000);
                    }

                    /*
                      Skip ads
                      1 - Inter
                      2 - Native
                      3 - Banner

                      btn number 0 stop ads
                      */
                    response.getString("regular_button_counter");
                    if (!response.getString("regular_button_counter").isEmpty()) {
                        MyHelpers.setCounter_Inter(Integer.parseInt(response.getString("regular_button_counter")));
                    } else {
                        MyHelpers.setCounter_Inter(5000);
                    }
                    response.getString("skip_native_ad");
                    if (!response.getString("skip_native_ad").isEmpty()) {
                        MyHelpers.setCounter_Native(Integer.parseInt(response.getString("skip_native_ad")));
                    } else {
                        MyHelpers.setCounter_Native(5000);
                    }
                    response.getString("skip_banner_ad");
                    if (!response.getString("skip_banner_ad").isEmpty()) {
                        MyHelpers.setCounter_Banner(Integer.parseInt(response.getString("skip_banner_ad")));
                    } else {
                        MyHelpers.setCounter_Banner(5000);
                    }

                    /*
                      App Live Status
                     */

                    if (PackName.equals("Test")) {
                        MyHelpers.setlive_status("1");
                        AdSettings.setTestMode(true);
                        //UnityAds.initialize(MyHelpers.instance, MyHelpers.getUnityAppID(), true);
                    } else {
                        MyHelpers.setlive_status(response.getString("live"));
                    }

                    /*
                      MIX ads
                      1 - Inter
                      2 - Native
                      3 - Banner
                      */
                    MyHelpers.setmix_ad_on_off(response.getString("mix_ad"));

                    //Mix Ads Counter
                    response.getString("mix_ad_name");
                    if (!response.getString("mix_ad_name").isEmpty()) {
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
                    response.getString("mix_ad_banner");
                    if (!response.getString("mix_ad_banner").isEmpty()) {
                        MyHelpers.setmix_ad_banner(response.getString("mix_ad_banner"));
                    } else {
                        MyHelpers.setmix_ad_banner(null);
                    }

                    response.getString("mix_ad_native");
                    if (!response.getString("mix_ad_native").isEmpty()) {
                        MyHelpers.setmix_ad_native(response.getString("mix_ad_native"));
                    } else {
                        MyHelpers.setmix_ad_native(null);
                    }

                    response.getString("mix_ad_counter");
                    if (!response.getString("mix_ad_counter").isEmpty()) {
                        MyHelpers.setmix_ad_inter(response.getString("mix_ad_counter"));
                    } else {
                        MyHelpers.setmix_ad_inter(null);
                    }

                    /*
                      Skip Country
                     */
                    MyHelpers.setSkip_country_on_off(response.getString("off_ad_country"));
                    if (MyHelpers.getSkip_country_on_off().equals("1")) {
                        response.getString("off_ad_country_name");
                        if (!response.getString("off_ad_country_name").isEmpty()) {
                            MyHelpers.setSkip_country_list(response.getString("off_ad_country_name"));
                        } else {
                            MyHelpers.setSkip_country_list(null);
                        }
                    }


                    /*
                      Extra data
                     */

                    MyHelpers.setExtraBtn_1(response.getString("extra_switch_1"));
                    MyHelpers.setExtraBtn_2(response.getString("extra_switch_2"));
                    MyHelpers.setExtraBtn_3(response.getString("extra_switch_3"));
                    MyHelpers.setExtraBtn_4(response.getString("extra_switch_4"));

                    MyHelpers.setExtraText_1(response.getString("extra_text_1"));
                    MyHelpers.setExtraText_2(response.getString("extra_text_2"));
                    MyHelpers.setExtraText_3(response.getString("extra_text_3"));
                    MyHelpers.setExtraText_4(response.getString("extra_text_4"));


                    /*
                      Other App Open
                     */
                    MyHelpers.setOtherAppsShow(response.getString("replace_app"));
                    MyHelpers.setOtherAppsShowLink(response.getString("new_app_link"));
                    if (MyHelpers.getOtherAppsShow().equals("1")) {
                        MyHelpers.Entry_UpdateApps = 2;
                        context.startActivity(new Intent(context, UpdateAppActivity.class));
                        return;
                    }

                    /*
                      Update Our App
                     */
                    MyHelpers.setUpdateApps(response.getString("update_app"));
                    MyHelpers.setAppversioncode(response.getString("version_code"));
                    if (MyHelpers.getUpdateApps().equals("1")) {
                        if (!MyHelpers.getAppversioncode().equals(versionCode)) {
                            MyHelpers.Entry_UpdateApps = 1;
                            context.startActivity(new Intent(context, UpdateAppActivity.class));
                            return;
                        }
                    }


                    /*
                      Next App
                     */
                    if (MyHelpers.getSkip_country_on_off().equals("1") && CheckCountry(MyHelpers.getSkip_country_list())) {
                        MyHelpers.setGoogleEnable("0");
                        MyHelpers.setFacebookEnable("0");
                        MyHelpers.setauto_link_on_off("0");
                        MyHelpers.set_q_link_btn_on_off("0");
                        MyHelpers.setAppLovingEnable("0");
                        MyHelpers.setUnityEnable("0");
                        MyHelpers.setCustomEnable("0");
                        MyHelpers.setmix_ad_on_off("0");
                        MyHelpers.setBackAdsOnOff("0");
                        NextIntent(contexts, intents);
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
            if (MyHelpers.getmix_ad_inter() != null && !MyHelpers.getmix_ad_inter().isEmpty()) {
                MixOpenAds(String.valueOf(MyHelpers.getmix_ad_inter().charAt(0)));
            } else {
                NextIntent(contexts, intents);
            }
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
            NextIntent(contexts, intents);
        }
    }

    private static void UnityAppOpen() {

        if (MyHelpers.getUnityInterID() != null && !MyHelpers.getUnityInterID().isEmpty()) {

            UnityAds.load(MyHelpers.getUnityInterID(), new IUnityAdsLoadListener() {
                @Override
                public void onUnityAdsAdLoaded(String placementId) {
                    UnityAds.show((Activity) contexts, MyHelpers.getUnityInterID(), new UnityAdsShowOptions(), new IUnityAdsShowListener() {
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
                            NextIntent(contexts, intents);
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
            MaxInterstitialAd interstitialAd = new MaxInterstitialAd(MyHelpers.getAppLovinInter(), (Activity) contexts);
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
                    NextIntent(contexts, intents);
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

            com.facebook.ads.InterstitialAd interstitialAd_FB_1 = new com.facebook.ads.InterstitialAd(contexts, MyHelpers.getfacebook_open_ad_id());
            InterstitialAdListener adListener = new InterstitialAdListener() {
                @Override
                public void onInterstitialDisplayed(Ad ad) {

                }

                @Override
                public void onInterstitialDismissed(Ad ad) {
                    NextIntent(contexts, intents);
                }

                @Override
                public void onError(Ad ad, com.facebook.ads.AdError adError) {
                    FailsAds("f");
                }

                @Override
                public void onAdLoaded(Ad ad) {
                    interstitialAd_FB_1.show();
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
                    public void onAppOpenClose() {

                        if (checkAppOpen) {
                            checkAppOpen = false;
                        }

                        if (isShowOpen) {
                            isShowOpen = false;
                        }

                        //   NextIntent(contexts, intents);

                        if (!OpenAdsStatus) {
                            OpenAdsStatus = true;
                            NextIntent(contexts, intents);
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
        new Handler().postDelayed(() -> {
            if (customs_status) {

                if (SplashHelp.adsModals != null && !SplashHelp.adsModals.isEmpty()) {
                    NextIntent(contexts, intents);
                    contexts.startActivity(new Intent(contexts, CustomAdsInterActivity.class));
                } else {
                    NextIntent(contexts, intents);
                }

            } else {
                CustomOpenAds();
            }
        }, 100);
    }

    /**
     * Fails Ads
     */
    public static void FailsAds(String Skip) {

        switch (Skip) {
            case "g":

                if (MyHelpers.getfacebook_open_ad_id() != null && !MyHelpers.getfacebook_open_ad_id().isEmpty() && MyHelpers.getlive_status().equals("1")) {
                    com.facebook.ads.InterstitialAd interstitialAd_FB_1 = new com.facebook.ads.InterstitialAd(contexts, MyHelpers.getfacebook_open_ad_id());
                    InterstitialAdListener adListener = new InterstitialAdListener() {
                        @Override
                        public void onInterstitialDisplayed(Ad ad) {

                        }

                        @Override
                        public void onInterstitialDismissed(Ad ad) {
                            NextIntent(contexts, intents);
                        }

                        @Override
                        public void onError(Ad ad, com.facebook.ads.AdError adError) {
                            GoogledFacebookFails();
                        }

                        @Override
                        public void onAdLoaded(Ad ad) {
                            interstitialAd_FB_1.show();
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
                    GoogledFacebookFails();
                }

                break;
            case "f":

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
                                GoogledFacebookFails();
                            }

                        }

                        @Override
                        public void onAppOpenClose() {

                            if (checkAppOpen) {
                                checkAppOpen = false;
                            }

                            if (isShowOpen) {
                                isShowOpen = false;
                            }
                            if (!OpenAdsStatus) {
                                OpenAdsStatus = true;
                                NextIntent(contexts, intents);
                            }

                        }
                    };
                    isShowOpen = true;
                    appOpenManager = new AppOpenManager(MyHelpers.getGoogle_OpenADS(), MyHelpers.getInstant(), onAppOpenClose);
                } else {
                    GoogledFacebookFails();
                }

                break;
            case "a":

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
                        public void onAppOpenClose() {

                            if (checkAppOpen) {
                                checkAppOpen = false;
                            }

                            if (isShowOpen) {
                                isShowOpen = false;
                            }
                            if (!OpenAdsStatus) {
                                OpenAdsStatus = true;
                                NextIntent(contexts, intents);
                            }
                        }
                    };
                    isShowOpen = true;
                    appOpenManager = new AppOpenManager(MyHelpers.getGoogle_OpenADS(), MyHelpers.getInstant(), onAppOpenClose);

                } else {
                    FailAdsAppLovin_ShowFacebookUnityCustom();
                }

                break;
            case "u":

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
                        public void onAppOpenClose() {

                            if (checkAppOpen) {
                                checkAppOpen = false;
                            }

                            if (isShowOpen) {
                                isShowOpen = false;
                            }

                            if (!OpenAdsStatus) {
                                OpenAdsStatus = true;
                                NextIntent(contexts, intents);
                            }
                        }
                    };
                    isShowOpen = true;
                    appOpenManager = new AppOpenManager(MyHelpers.getGoogle_OpenADS(), MyHelpers.getInstant(), onAppOpenClose);

                } else {
                    FailUnity_ShowFacebookAppLovinCustom();
                }

                break;
            default:
                CustomOpenAds();
                break;
        }
    }

    private static void GoogledFacebookFails() {

        if (MyHelpers.getAppLovinInter() != null && !MyHelpers.getAppLovinInter().isEmpty()) {

            MaxInterstitialAd interstitialAd = new MaxInterstitialAd(MyHelpers.getAppLovinInter(), (Activity) contexts);
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
                    NextIntent(contexts, intents);
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
            com.facebook.ads.InterstitialAd interstitialAd_FB_1 = new com.facebook.ads.InterstitialAd(contexts, MyHelpers.getfacebook_open_ad_id());
            InterstitialAdListener adListener = new InterstitialAdListener() {
                @Override
                public void onInterstitialDisplayed(Ad ad) {

                }

                @Override
                public void onInterstitialDismissed(Ad ad) {
                    NextIntent(contexts, intents);
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
                    interstitialAd_FB_1.show();
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
            com.facebook.ads.InterstitialAd interstitialAd_FB_1 = new com.facebook.ads.InterstitialAd(contexts, MyHelpers.getfacebook_open_ad_id());
            InterstitialAdListener adListener = new InterstitialAdListener() {
                @Override
                public void onInterstitialDisplayed(Ad ad) {

                }

                @Override
                public void onInterstitialDismissed(Ad ad) {
                    NextIntent(contexts, intents);
                }

                @Override
                public void onError(Ad ad, com.facebook.ads.AdError adError) {
                    if (MyHelpers.getAppLovinInter() != null && !MyHelpers.getAppLovinInter().isEmpty()) {

                        MaxInterstitialAd interstitialAd = new MaxInterstitialAd(MyHelpers.getAppLovinInter(), (Activity) contexts);
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
                                NextIntent(contexts, intents);
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
                    interstitialAd_FB_1.show();
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
            MaxInterstitialAd interstitialAd = new MaxInterstitialAd(MyHelpers.getAppLovinInter(), (Activity) contexts);
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
                    NextIntent(contexts, intents);
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
                UnityAds.show((Activity) contexts, MyHelpers.getUnityInterID(), new UnityAdsShowOptions(), new IUnityAdsShowListener() {
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
                        NextIntent(contexts, intents);
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
        InterClass.main_context = (Activity) contexts;
        BannerClass.main_context = contexts;
        NativeClass.main_context = (Activity) contexts;
        MixAdOnBanner();
        MixAdOnNative();
        MixAdOnInter();
    }

    private static void MixAdOnBanner() {
        /*
          Banner
         */

        if (MyHelpers.getlive_status().equals("1")) {

            /*Google Banner*/
            if (MyHelpers.getGoogleBanner() != null && !MyHelpers.getGoogleBanner().isEmpty() &&
                    MyHelpers.getGoogleBanner1() == null &&
                    MyHelpers.getGoogleBanner2() == null ) {

                MyHelpers.Google_banner_number = 1;
                BannerClass.GoogleBannerPreload();

            } else if (MyHelpers.getGoogleBanner() != null && !MyHelpers.getGoogleBanner().isEmpty() &&
                    MyHelpers.getGoogleBanner1() != null && !MyHelpers.getGoogleBanner1().isEmpty() &&
                    MyHelpers.getGoogleBanner2() == null ) {

                MyHelpers.Google_banner_number = 2;
                BannerClass.GoogleBannerPreload1();
                BannerClass.GoogleBannerPreload2();

            } else if (MyHelpers.getGoogleBanner() != null && !MyHelpers.getGoogleBanner().isEmpty() &&
                    MyHelpers.getGoogleBanner1() != null && !MyHelpers.getGoogleBanner1().isEmpty() &&
                    MyHelpers.getGoogleBanner2() != null && !MyHelpers.getGoogleBanner2().isEmpty()) {

                MyHelpers.Google_banner_number = 3;
                BannerClass.GoogleBannerPreload1();
                BannerClass.GoogleBannerPreload2();
                BannerClass.GoogleBannerPreload3();

            }

            /*Facebook Banner*/
            if (MyHelpers.getFacebookBanner() != null && !MyHelpers.getFacebookBanner().isEmpty() &&
                    MyHelpers.getFacebookBanner1() == null &&
                    MyHelpers.getFacebookBanner2() == null ) {

                MyHelpers.fb_banner_number = 1;
                BannerClass.FacebookBannerPreLoad();

            } else if (MyHelpers.getFacebookBanner() != null && !MyHelpers.getFacebookBanner().isEmpty() &&
                    MyHelpers.getFacebookBanner1() != null && !MyHelpers.getFacebookBanner1().isEmpty() &&
                    MyHelpers.getFacebookBanner2() == null ) {

                MyHelpers.fb_banner_number = 2;
                BannerClass.FacebookBannerPreLoad1();
                BannerClass.FacebookBannerPreLoad2();

            } else if (MyHelpers.getFacebookBanner() != null && !MyHelpers.getFacebookBanner().isEmpty() &&
                    MyHelpers.getFacebookBanner1() != null && !MyHelpers.getFacebookBanner1().isEmpty() &&
                    MyHelpers.getFacebookBanner2() != null && !MyHelpers.getFacebookBanner2().isEmpty()) {

                MyHelpers.fb_banner_number = 3;
                BannerClass.FacebookBannerPreLoad1();
                BannerClass.FacebookBannerPreLoad2();
                BannerClass.FacebookBannerPreLoad3();

            }


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
        /*
          Native
         */

        if (MyHelpers.getlive_status().equals("1")) {

            /*Google Native*/
            if (MyHelpers.getGoogleNative() != null && !MyHelpers.getGoogleNative().isEmpty() &&
                    MyHelpers.getGoogleNative1() == null &&
                    MyHelpers.getGoogleNative2() == null ) {

                MyHelpers.Google_native_number = 1;
                NativeClass.GoogleNativePreload();

            } else if (MyHelpers.getGoogleNative() != null && !MyHelpers.getGoogleNative().isEmpty() &&
                    MyHelpers.getGoogleNative1() != null && !MyHelpers.getGoogleNative1().isEmpty() &&
                    MyHelpers.getGoogleNative2() == null) {

                MyHelpers.Google_native_number = 2;
                NativeClass.GoogleNativePreload1();
                NativeClass.GoogleNativePreload2();

            } else if (MyHelpers.getGoogleNative() != null && !MyHelpers.getGoogleNative().isEmpty() &&
                    MyHelpers.getGoogleNative1() != null && !MyHelpers.getGoogleNative1().isEmpty() &&
                    MyHelpers.getGoogleNative2() != null && !MyHelpers.getGoogleNative2().isEmpty()) {

                MyHelpers.Google_native_number = 3;
                NativeClass.GoogleNativePreload1();
                NativeClass.GoogleNativePreload2();
                NativeClass.GoogleNativePreload3();

            }

            /*Facebook Native*/
            if (MyHelpers.getFacebookNative() != null && !MyHelpers.getFacebookNative().isEmpty() &&
                    MyHelpers.getFacebookNative1() == null &&
                    MyHelpers.getFacebookNative2() == null ) {

                MyHelpers.fb_native_number = 1;
                NativeClass.FacebookNativePreLoad();

            } else if (MyHelpers.getFacebookNative() != null && !MyHelpers.getFacebookNative().isEmpty() &&
                    MyHelpers.getFacebookNative1() != null && !MyHelpers.getFacebookNative1().isEmpty() &&
                    MyHelpers.getFacebookNative2() == null ) {

                MyHelpers.fb_native_number = 2;
                NativeClass.FacebookNativePreLoad1();
                NativeClass.FacebookNativePreLoad2();

            } else if (MyHelpers.getFacebookNative() != null && !MyHelpers.getFacebookNative().isEmpty() &&
                    MyHelpers.getFacebookNative1() != null && !MyHelpers.getFacebookNative1().isEmpty() &&
                    MyHelpers.getFacebookNative2() != null && !MyHelpers.getFacebookNative2().isEmpty()) {

                MyHelpers.fb_native_number = 3;
                NativeClass.FacebookNativePreLoad1();
                NativeClass.FacebookNativePreLoad2();
                NativeClass.FacebookNativePreLoad3();

            }

        }


        /*AppLoving Native*/
        if (MyHelpers.getAppLovinNative() != null && !MyHelpers.getAppLovinNative().isEmpty()) {
            NativeClass.AppLovingNativePreLoad();
        }
    }

    private static void MixAdOnInter() {
        /*
          Inter
         */
        /*Google Inter*/

        if (MyHelpers.getExtraBtn_1().equals("0")) {

            if (MyHelpers.getlive_status().equals("1")) {

                /*Google Inter*/
                if (MyHelpers.getGoogleInter() != null && !MyHelpers.getGoogleInter().isEmpty() &&
                        MyHelpers.getGoogleInter1() == null &&
                        MyHelpers.getGoogleInter2() == null ) {

                    MyHelpers.Google_inter_number = 1;
                    InterClass.GoogleInterPreload();

                } else if (MyHelpers.getGoogleInter() != null && !MyHelpers.getGoogleInter().isEmpty() &&
                        MyHelpers.getGoogleInter1() != null && !MyHelpers.getGoogleInter1().isEmpty() &&
                        MyHelpers.getGoogleInter2() == null ) {

                    MyHelpers.Google_inter_number = 2;
                    InterClass.GoogleInterPreload1();
                    InterClass.GoogleInterPreload2();

                } else if (MyHelpers.getGoogleInter() != null && !MyHelpers.getGoogleInter().isEmpty() &&
                        MyHelpers.getGoogleInter1() != null && !MyHelpers.getGoogleInter1().isEmpty() &&
                        MyHelpers.getGoogleInter2() != null && !MyHelpers.getGoogleInter2().isEmpty()) {

                    MyHelpers.Google_inter_number = 3;
                    InterClass.GoogleInterPreload1();
                    InterClass.GoogleInterPreload2();
                    InterClass.GoogleInterPreload3();

                }

                /*Facebook Inter*/
                if (MyHelpers.getFacebookInter() != null && !MyHelpers.getFacebookInter().isEmpty() &&
                        MyHelpers.getFacebookInter1() == null &&
                        MyHelpers.getFacebookInter2() == null ) {

                    MyHelpers.fb_inter_number = 1;
                    InterClass.FacebookInterPreLoad();

                } else if (MyHelpers.getFacebookInter() != null && !MyHelpers.getFacebookInter().isEmpty() &&
                        MyHelpers.getFacebookInter1() != null && !MyHelpers.getFacebookInter1().isEmpty() &&
                        MyHelpers.getFacebookInter2() == null ) {

                    MyHelpers.fb_inter_number = 2;
                    InterClass.FacebookInterPreLoad1();
                    InterClass.FacebookInterPreLoad2();

                } else if (MyHelpers.getFacebookInter() != null && !MyHelpers.getFacebookInter().isEmpty() &&
                        MyHelpers.getFacebookInter1() != null && !MyHelpers.getFacebookInter1().isEmpty() &&
                        MyHelpers.getFacebookInter2() != null && !MyHelpers.getFacebookInter2().isEmpty()) {

                    MyHelpers.fb_inter_number = 3;
                    InterClass.FacebookInterPreLoad1();
                    InterClass.FacebookInterPreLoad2();
                    InterClass.FacebookInterPreLoad3();

                }

            }
        }
    }


    /**
     * Mix Open Ads
     */

    /*Mix Open*/
    private static void MixOpenAds(String valueOf) {
        switch (valueOf) {
            case "g":
                GoogleAppOpen();
                break;
            case "f":
                FaceBookAppOpen();
                break;
            case "a":
                AppLovingAppOpen();
                break;
            case "u":
                UnityAppOpen();
                break;
            case "q":
                NextIntent(contexts, intents);
                MyHelpers.BtnAutolink();
                break;
            case "c":
                CustomOpenAds();
                break;
            default:
                NextIntent(contexts, intents);
                break;
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
                    customs_status = true;

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                customs_status = true;
            }
        });
    }

    /**
     * Country Check
     */
    public static String getCountryCode() {
        TelephonyManager tm = (TelephonyManager) contexts.getSystemService(TELEPHONY_SERVICE);
        return tm.getNetworkCountryIso();
    }

    public static Boolean CheckCountry(String Country_name) {
        try {
            List<String> COUNTRY = new ArrayList<>(Arrays.asList(Country_name.split(",")));
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
