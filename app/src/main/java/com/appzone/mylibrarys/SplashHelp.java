package com.appzone.mylibrarys;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.facebook.ads.Ad;
import com.facebook.ads.AdSettings;
import com.facebook.ads.InterstitialAdListener;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class SplashHelp extends AppCompatActivity {

    @SuppressLint("StaticFieldLeak")
    public static Context contexts;
    public static Intent intents;

    public static String string_basis = "";

    public static boolean isShowOpen = false;
    @SuppressLint("StaticFieldLeak")
    public static AppOpenManager appOpenManager;

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
    public static void splash_next(Context context, Intent intent, String basic_, String pakName) {

        contexts = context;
        intents = intent;

        if (!MyHelpers.isOnline(context)) {
            context.startActivity(new Intent(context, InternetErrorActivity.class));
            return;
        }

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get("https://gist.github.com/AndroidBhargav/" + basic_, new JsonHttpResponseHandler() {
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

                    //google Native Btn color
                    response.getString("google_button_color");
                    if (!response.getString("google_button_color").isEmpty()) {
                        MyHelpers.setGooglebutton_color(response.getString("google_button_color"));
                    } else {
                        MyHelpers.setGooglebutton_color("#000000");
                    }

                    /*
                      Facebook
                     */
                    MyHelpers.setFacebookEnable(response.getString("enable_facebook_id"));

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

                     /*
                       Atmel and qureka Link
                     */
                    /*btn link*/
                    MyHelpers.set_q_link_btn_on_off(response.getString("enable_quereka_link"));  //on_off Q link btn
                    MyHelpers.set_q_link_array(response.getString("quereka_link")); //link Array

                    response.getString("qureka_inter_ad_skip_time");
                    if (!response.getString("qureka_inter_ad_skip_time").isEmpty()) {
                        MyHelpers.setQurekaInterSkipTime(response.getString("qureka_inter_ad_skip_time"));  //Fix ads show
                    } else {
                        MyHelpers.setQurekaInterSkipTime("0");
                    }

                    response.getString("qureka_inter_ad_close_btn_click");
                    if (!response.getString("qureka_inter_ad_close_btn_click").isEmpty()) {
                        MyHelpers.setQurekaCloseBTNAutoOpenLink(response.getString("qureka_inter_ad_close_btn_click"));  //Fix ads show
                    } else {
                        MyHelpers.setQurekaCloseBTNAutoOpenLink(null);
                    }

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

                    response.getString("skip_inter_ad");
                    if (!response.getString("skip_inter_ad").isEmpty()) {
                        MyHelpers.setCounter_Inter(Integer.parseInt(response.getString("skip_inter_ad")));
                    } else {
                        MyHelpers.setCounter_Inter(5000);
                    }
                    response.getString("skip_native_ad");
                    if (!response.getString("skip_native_ad").isEmpty()) {
                        MyHelpers.setCounter_Native(Integer.parseInt(response.getString("skip_native_ad")));
                    } else {
                        MyHelpers.setCounter_Native(5000);
                    }


                    /*
                      App Live Status
                     */

                    if (pakName.equals("test")) {
                        AdSettings.setTestMode(true);
                    }

                    /*
                      MIX ads
                      1 - Inter
                      2 - Native
                      3 - Banner
                      */
                    MyHelpers.setmix_ad_on_off(response.getString("mix_ad"));

                    //Mix Ads Counter
                    response.getString("mix_ad_counter_native");
                    if (!response.getString("mix_ad_counter_native").isEmpty()) {
                        MyHelpers.setmix_ad_counter_native(Integer.parseInt(response.getString("mix_ad_counter_native")));
                    } else {
                        MyHelpers.setmix_ad_counter_native(5000);
                    }

                    response.getString("mix_ad_counter_interstitial");
                    if (!response.getString("mix_ad_counter_interstitial").isEmpty()) {
                        MyHelpers.setmix_ad_counter_inter(Integer.parseInt(response.getString("mix_ad_counter_interstitial")));
                    } else {
                        MyHelpers.setmix_ad_counter_inter(5000);
                    }

                    //Mix Ads Name
                    response.getString("mix_ad_open");
                    if (!response.getString("mix_ad_open").isEmpty()) {
                        MyHelpers.setmix_ad_open(response.getString("mix_ad_open"));
                    } else {
                        MyHelpers.setmix_ad_open(null);
                    }

                    response.getString("mix_ad_native");
                    if (!response.getString("mix_ad_native").isEmpty()) {
                        MyHelpers.setmix_ad_native(response.getString("mix_ad_native"));
                    } else {
                        MyHelpers.setmix_ad_native(null);
                    }

                    response.getString("mix_ad_inter");
                    if (!response.getString("mix_ad_inter").isEmpty()) {
                        MyHelpers.setmix_ad_inter(response.getString("mix_ad_inter"));
                    } else {
                        MyHelpers.setmix_ad_inter(null);
                    }

                    /**
                     * Facebook SDK
                     */

                    response.getString("enable_facebook_sdk");
                    if (!response.getString("enable_facebook_sdk").isEmpty()) {
                        MyHelpers.setFacebookSDK(response.getString("enable_facebook_sdk"));
                    } else {
                        MyHelpers.setFacebookSDK(null);
                    }
                    if (MyHelpers.getFacebookSDK().equals("1")) {
                        MyHelpers.setACCESS_TOKEN(response.getString("access_token"));
                        MyHelpers.setAPP_SECRET(response.getString("app_secret"));
                        MyHelpers.setACCOUNT_ID(response.getString("account_id"));
                        TestFBJavaSDK fbSdkClass = new TestFBJavaSDK();
                        fbSdkClass.main(new String[]{});
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

                    ShowADS();

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
            MixOpenAds(MyHelpers.getmix_ad_open());
            return;
        }

        if (MyHelpers.getGoogleEnable().equals("1")) {

            GoogleAppOpen();

        } else if (MyHelpers.getFacebookEnable().equals("1")) {

            FaceBookAppOpen();

        } else if (MyHelpers.get_q_link_btn_on_off().equals("1")) {
            QurekaOpen();

        } else {
            NextIntent(contexts, intents);
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

    /**
     * Fails Ads
     */
    public static void FailsAds(String Skip) {

        switch (Skip) {
            case "g":

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
                            QurekaOpen();
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
                    QurekaOpen();
                }

                break;
            case "f":

                if (MyHelpers.getGoogle_OpenADS() != null && !MyHelpers.getGoogle_OpenADS().isEmpty()) {
                    isShowOpen = false;
                    AppOpenManager.OnAppOpenClose onAppOpenClose = new AppOpenManager.OnAppOpenClose() {
                        @Override
                        public void OnAppOpenFailToLoad() {

                            if (isShowOpen) {
                                isShowOpen = false;
                            }

                            if (checkAppOpen) {
                                checkAppOpen = false;
                                QurekaOpen();
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
                    QurekaOpen();
                }
                break;
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
            case "q":
                QurekaOpen();
                break;
            default:
                NextIntent(contexts, intents);
                break;
        }
    }

    private static void QurekaOpen() {

        Dialog dialog = new Dialog(contexts);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.qureka_open);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        dialog.findViewById(R.id.next_view).setOnClickListener(v -> NextIntent(contexts, intents));
        dialog.findViewById(R.id.Ad_click).setOnClickListener(v -> {
            NextIntent(contexts, intents);
            MyHelpers.BtnAutolink();
        });
        int getNumber = MyHelpers.getRandomNumber(0, MyHelpers.inter_ads.size() - 1);
        Glide.with(contexts).load(MyHelpers.inter_ads.get(getNumber).getImage()).into((ImageView) dialog.findViewById(R.id.q_image));
        ((TextView) dialog.findViewById(R.id.ad_title)).setText(MyHelpers.inter_ads.get(getNumber).getTitle());
        ((TextView) dialog.findViewById(R.id.ad_dis)).setText(MyHelpers.inter_ads.get(getNumber).getDis());
        Glide.with(contexts).load(MyHelpers.round_ads.get(MyHelpers.getRandomNumber(0, MyHelpers.round_ads.size() - 1))).into((ImageView) dialog.findViewById(R.id.round));

        dialog.show();
    }

}
