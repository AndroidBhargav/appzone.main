package com.appzone.mylibrarys;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.facebook.ads.Ad;
import com.facebook.ads.InterstitialAdListener;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.unity3d.ads.IUnityAdsLoadListener;
import com.unity3d.ads.IUnityAdsShowListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.ads.UnityAdsShowOptions;

public class InterClass {

    /*Google*/
    public static com.google.android.gms.ads.interstitial.InterstitialAd google_InterstitialAd;
    public static com.google.android.gms.ads.interstitial.InterstitialAd google_InterstitialAd_1;
    public static com.google.android.gms.ads.interstitial.InterstitialAd google_InterstitialAd_2;
    public static com.google.android.gms.ads.interstitial.InterstitialAd google_InterstitialAd_3;
    public static int inter_show_id = 0;
    public static int fb_inter_show_id = 0;
    public static int mix_adsInter = 0;
    public static int auto_notShow_ads_inter = 0;

    //facebook
    public static com.facebook.ads.InterstitialAd facebook_interstitialAd;
    public static com.facebook.ads.InterstitialAd facebook_interstitialAd1;
    public static com.facebook.ads.InterstitialAd facebook_interstitialAd2;
    public static com.facebook.ads.InterstitialAd facebook_interstitialAd3;

    public static com.facebook.ads.InterstitialAd facebook_interstitial_loading;
    public static com.facebook.ads.InterstitialAd facebook_interstitial_loading_main;

    //App Loving
    public static MaxInterstitialAd applovin_interstitialAd;

    //Unity
    public static boolean UnityAdLoadChecker = false;

    /*Helper*/
    public static Activity main_context;
    public static int auto_notShow_adsBack = 0;

    public static Dialog on_loading_dialog;

    /**
     * INTERNET CHECK CODE
     */
    public static boolean checkConnection(Context context) {
        final ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connMgr.getActiveNetworkInfo();

        if (activeNetworkInfo != null) { // connected to the internet

            if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                // connected to wifi
                return true;
            } else if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                // connected to the mobile provider's data plan
                return true;
            }
        }
        return false;
    }

    /**
     * INTERSTITIAL ADS CODE START
     */
    public static void Interstitial(Activity context) {
        main_context = context;
        /**
         * ActivityFinish == 0 next activity
         * ActivityFinish == 1 next and finish activity
         * ActivityFinish == 2 finish activity
         */
        if (InterClass.checkConnection(context)) {

            /*Stop Ads*/
            if (MyHelpers.getCounter_Inter() == 0) {
                return;
            }

            /*Skip Ads*/
            if (MyHelpers.getCounter_Inter() != 5000) {
                auto_notShow_ads_inter++;
                if (MyHelpers.getCounter_Inter() + 1 == auto_notShow_ads_inter) {
                    auto_notShow_ads_inter = 0;
                    if (MyHelpers.getmix_ad_on_off().equals("1")) {
                        MixAds();
                    } else {
                        RegularADS();
                    }
                    return;
                }
                return;
            }

            /*Mix and Regular ads*/
            if (MyHelpers.getmix_ad_on_off().equals("1")) {
                MixAds();
            } else {
                RegularADS();
            }
        }
    }

    /**
     * Back Btn Interstitial
     */
    public static void BackInterstitial(Activity context) {
        main_context = context;

        if (InterClass.checkConnection(context)) {

            if (MyHelpers.getBackAdsOnOff().equals("1")) {
                /**
                 * Skip Ads
                 */

                if (MyHelpers.getBackCounter() != 5000) {
                    auto_notShow_adsBack++;
                    if (MyHelpers.getBackCounter() + 1 == auto_notShow_adsBack) {
                        if (MyHelpers.getmix_ad_on_off().equals("1")) {
                            MixAds();
                        } else {
                            RegularADS();
                        }
                        auto_notShow_adsBack = 0;
                        return;
                    }
                    return;
                }

                /**
                 * Mix Ads
                 */
                if (MyHelpers.getmix_ad_on_off().equals("1")) {
                    MixAds();
                } else {
                    RegularADS();
                }
            } else {
                context.finish();
            }
        } else {
            context.finish();
        }
    }

    /**
     * Regular Ads
     */
    private static void RegularADS() {

        if (MyHelpers.getExtraBtn_1().equals("1")) {

            if (MyHelpers.getGoogleEnable().equals("1") && MyHelpers.getlive_status().equals("1")) {
                GoogleADSShowONDemand();
            } else if (MyHelpers.getFacebookEnable().equals("1") && MyHelpers.getlive_status().equals("1")) {
                FacebookADSShowONDemand();
            }

        } else {
            if (MyHelpers.getGoogleEnable().equals("1") && MyHelpers.getlive_status().equals("1")) {
                GoogleADSShow("r");
            } else if (MyHelpers.getFacebookEnable().equals("1") && MyHelpers.getlive_status().equals("1")) {
                FacebookADSShow("f");
            } else if (MyHelpers.getAppLovinEnable().equals("1")) {
                RegularAppLovingShow();
            } else if (MyHelpers.getUnityEnable().equals("1")) {
                UnityADSShow();
            } else if (MyHelpers.get_q_link_btn_on_off().equals("1")) {
                MyHelpers.BtnAutolink();
            } else if (MyHelpers.getCustomEnable().equals("1")) {
                CustomADSInter();
            }
        }
    }


    private static void GoogleADSShowONDemand() {

        try {

            if (MyHelpers.getGoogleInter() != null && !MyHelpers.getGoogleInter().isEmpty()) {

                showLoading(main_context, false);
                AdRequest adRequest = new AdRequest.Builder().build();
                com.google.android.gms.ads.interstitial.InterstitialAd.load(main_context, MyHelpers.getGoogleInter(), adRequest, new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull com.google.android.gms.ads.interstitial.InterstitialAd interstitialAd) {
                        super.onAdLoaded(interstitialAd);

                        interstitialAd.show(main_context);
                        interstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                                super.onAdFailedToShowFullScreenContent(adError);
                                GoogleAdsShowOnDemandFail_showFB();
                            }

                            @Override
                            public void onAdShowedFullScreenContent() {
                                super.onAdShowedFullScreenContent();

                            }

                            @Override
                            public void onAdDismissedFullScreenContent() {
                                super.onAdDismissedFullScreenContent();
                            }

                        });

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                hideLoading();
                            }
                        }, 1000);

                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        super.onAdFailedToLoad(loadAdError);
                        GoogleAdsShowOnDemandFail_showFB();
                    }
                });

            }

        } catch (Exception e) {
            e.printStackTrace();
            hideLoading();
        }
    }

    private static void GoogleAdsShowOnDemandFail_showFB() {

        if (MyHelpers.getFacebookInter() != null && !MyHelpers.getFacebookInter().isEmpty()) {

            facebook_interstitial_loading = new com.facebook.ads.InterstitialAd(main_context, MyHelpers.getFacebookInter());
            InterstitialAdListener adListener = new InterstitialAdListener() {
                @Override
                public void onInterstitialDisplayed(Ad ad) {

                }

                @Override
                public void onInterstitialDismissed(Ad ad) {

                }

                @Override
                public void onError(Ad ad, com.facebook.ads.AdError adError) {
                    facebook_interstitial_loading = null;
                    hideLoading();
                }

                @Override
                public void onAdLoaded(Ad ad) {
                    if (facebook_interstitial_loading != null && facebook_interstitial_loading.isAdLoaded()) {
                        facebook_interstitial_loading.show();
                    }
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            hideLoading();
                        }
                    }, 1000);

                }

                @Override
                public void onAdClicked(Ad ad) {

                }

                @Override
                public void onLoggingImpression(Ad ad) {

                }
            };
            facebook_interstitial_loading.loadAd(facebook_interstitial_loading.buildLoadAdConfig().withAdListener(adListener).build());
        }
    }

    private static void FacebookADSShowONDemand() {

        try {

            if (MyHelpers.getFacebookInter() != null && !MyHelpers.getFacebookInter().isEmpty()) {

                showLoading(main_context, false);
                facebook_interstitial_loading_main = new com.facebook.ads.InterstitialAd(main_context, MyHelpers.getFacebookInter());
                InterstitialAdListener adListener = new InterstitialAdListener() {
                    @Override
                    public void onInterstitialDisplayed(Ad ad) {

                    }

                    @Override
                    public void onInterstitialDismissed(Ad ad) {

                    }

                    @Override
                    public void onError(Ad ad, com.facebook.ads.AdError adError) {
                        facebook_interstitial_loading_main = null;
                        FacebookAdsShowOnDemandFail_showG();
                    }

                    @Override
                    public void onAdLoaded(Ad ad) {
                        if (facebook_interstitial_loading_main != null && facebook_interstitial_loading_main.isAdLoaded()) {
                            facebook_interstitial_loading_main.show();
                        }
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                hideLoading();
                            }
                        }, 1000);
                    }

                    @Override
                    public void onAdClicked(Ad ad) {

                    }

                    @Override
                    public void onLoggingImpression(Ad ad) {

                    }
                };
                facebook_interstitial_loading_main.loadAd(facebook_interstitial_loading_main.buildLoadAdConfig().withAdListener(adListener).build());
            }

        } catch (Exception e) {
            e.printStackTrace();
            hideLoading();
        }
    }

    private static void FacebookAdsShowOnDemandFail_showG() {

        if (MyHelpers.getGoogleInter() != null && !MyHelpers.getGoogleInter().isEmpty()) {

            AdRequest adRequest = new AdRequest.Builder().build();
            com.google.android.gms.ads.interstitial.InterstitialAd.load(main_context, MyHelpers.getGoogleInter(), adRequest, new InterstitialAdLoadCallback() {
                @Override
                public void onAdLoaded(@NonNull com.google.android.gms.ads.interstitial.InterstitialAd interstitialAd) {
                    super.onAdLoaded(interstitialAd);

                    interstitialAd.show(main_context);
                    interstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                        @Override
                        public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                            super.onAdFailedToShowFullScreenContent(adError);

                        }

                        @Override
                        public void onAdShowedFullScreenContent() {
                            super.onAdShowedFullScreenContent();

                        }

                        @Override
                        public void onAdDismissedFullScreenContent() {
                            super.onAdDismissedFullScreenContent();
                        }

                    });

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            hideLoading();
                        }
                    }, 1000);
                }

                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);
                    hideLoading();
                }
            });

        }

    }

    public static void showLoading(Activity activity, boolean cancelable) {

        on_loading_dialog = new Dialog(activity);
        on_loading_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        on_loading_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        on_loading_dialog.setContentView(R.layout.loading_dialog);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(on_loading_dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        on_loading_dialog.getWindow().setAttributes(lp);

        on_loading_dialog.setCancelable(cancelable);

        if (!on_loading_dialog.isShowing() && !activity.isFinishing()) {
            on_loading_dialog.show();
        }
    }

    public static void hideLoading() {
        if (on_loading_dialog != null && on_loading_dialog.isShowing()) {
            on_loading_dialog.cancel();
        }
    }

    /**
     * Ads Show
     */
    /*Google Inter Show*/
    private static void GoogleADSShow(String adview) {
        if (MyHelpers.Google_inter_number == 1) {
            GoogleInterShow(adview);
        } else if (MyHelpers.Google_inter_number == 2) {
            if (inter_show_id == 0) {
                inter_show_id = 1;
                googleInterShow1(adview);
            } else {
                inter_show_id = 0;
                googleInterShow2(adview);
            }
        } else if (MyHelpers.Google_inter_number == 3) {
            if (inter_show_id == 0) {
                inter_show_id = 1;
                googleInterShow1(adview);
            } else if (inter_show_id == 1) {
                inter_show_id = 2;
                googleInterShow2(adview);
            } else {
                inter_show_id = 0;
                googleInterShow3(adview);
            }
        }
    }

    private static void GoogleInterShow(String adview) {
        if (google_InterstitialAd != null) {
            google_InterstitialAd.show(main_context);
            google_InterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                    super.onAdFailedToShowFullScreenContent(adError);
                    AllGoogle_Fails_OtherAdShow(adview);
                }

                @Override
                public void onAdShowedFullScreenContent() {
                    super.onAdShowedFullScreenContent();
                }

                @Override
                public void onAdDismissedFullScreenContent() {
                    super.onAdDismissedFullScreenContent();
                }

            });
        } else {
            AllGoogle_Fails_OtherAdShow(adview);
        }
        AllAdsPreLoadsInter("g");
    }

    /*Google Inter Show 1 ID*/
    private static void googleInterShow1(String adview) {
        if (google_InterstitialAd_1 != null) {
            google_InterstitialAd_1.show(main_context);
            google_InterstitialAd_1.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                    super.onAdFailedToShowFullScreenContent(adError);
                    AllGoogle_Fails_OtherAdShow(adview);
                }

                @Override
                public void onAdShowedFullScreenContent() {
                    super.onAdShowedFullScreenContent();
                }

                @Override
                public void onAdDismissedFullScreenContent() {
                    super.onAdDismissedFullScreenContent();
                }
            });
        } else {
            AllGoogle_Fails_OtherAdShow(adview);
        }
        AllAdsPreLoadsInter("g1");

    }

    /*Google Inter Show 2 ID*/
    private static void googleInterShow2(String adview) {
        if (google_InterstitialAd_2 != null) {
            google_InterstitialAd_2.show(main_context);
            google_InterstitialAd_2.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                    super.onAdFailedToShowFullScreenContent(adError);
                    AllGoogle_Fails_OtherAdShow(adview);
                }

                @Override
                public void onAdShowedFullScreenContent() {
                    super.onAdShowedFullScreenContent();
                }

                @Override
                public void onAdDismissedFullScreenContent() {
                    super.onAdDismissedFullScreenContent();

                }
            });
        } else {
            AllGoogle_Fails_OtherAdShow(adview);

        }

        AllAdsPreLoadsInter("g2");

    }

    /*Google Inter Show 3 ID*/
    private static void googleInterShow3(String adview) {
        if (google_InterstitialAd_3 != null) {
            google_InterstitialAd_3.show(main_context);
            google_InterstitialAd_3.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                    super.onAdFailedToShowFullScreenContent(adError);
                    AllGoogle_Fails_OtherAdShow(adview);
                }

                @Override
                public void onAdShowedFullScreenContent() {
                    super.onAdShowedFullScreenContent();
                }

                @Override
                public void onAdDismissedFullScreenContent() {
                    super.onAdDismissedFullScreenContent();
                }
            });
        } else {
            AllGoogle_Fails_OtherAdShow(adview);
        }

        AllAdsPreLoadsInter("g3");

    }

    private static void AllAds_Fails_Unity_Show() {

        if (UnityAdLoadChecker) {

            UnityAds.show((Activity) main_context, MyHelpers.getUnityInterID(), new UnityAdsShowOptions(), new IUnityAdsShowListener() {
                @Override
                public void onUnityAdsShowFailure(String placementId, UnityAds.UnityAdsShowError error, String message) {
                    AllAdsPreLoadsInter("u");
                    CustomADSInter();

                }

                @Override
                public void onUnityAdsShowStart(String placementId) {


                }

                @Override
                public void onUnityAdsShowClick(String placementId) {

                }

                @Override
                public void onUnityAdsShowComplete(String placementId, UnityAds.UnityAdsShowCompletionState state) {
                    AllAdsPreLoadsInter("u");
                }
            });
        } else {
            CustomADSInter();
        }
    }

    private static void AllGoogle_Fails_OtherAdShow(String adview) {
        if (adview.equals("r")) {
            FacebookADSShow(adview);
        } else if (adview.equals("f")) {
            Facebook_Fails_RegularAppLovingShow();
        } else if (adview.equals("a")) {
            FacebookADSShow(adview);
        } else if (adview.equals("u")) {
            FacebookADSShow(adview);
        }
    }

    /*Facebook Inter Show*/
    private static void FacebookADSShow(String adview) {
        if (MyHelpers.fb_inter_number == 1) {
            FacebookInterShow(adview);
        } else if (MyHelpers.fb_inter_number == 2) {
            if (fb_inter_show_id == 0) {
                fb_inter_show_id = 1;
                FacebookInterShow1(adview);
            } else {
                fb_inter_show_id = 0;
                FacebookInterShow2(adview);
            }
        } else if (MyHelpers.fb_inter_number == 3) {
            if (fb_inter_show_id == 0) {
                fb_inter_show_id = 1;
                FacebookInterShow1(adview);
            } else if (fb_inter_show_id == 1) {
                fb_inter_show_id = 2;
                FacebookInterShow2(adview);
            } else {
                fb_inter_show_id = 0;
                FacebookInterShow3(adview);
            }
        }
    }

    private static void FacebookInterShow(String adview) {
        if (facebook_interstitialAd != null && facebook_interstitialAd.isAdLoaded()) {
            facebook_interstitialAd.show();
        } else {
            AllFacebook_Fails_OtherAdShow(adview);
        }
        AllAdsPreLoadsInter("f");
    }

    private static void FacebookInterShow1(String adview) {
        if (facebook_interstitialAd1 != null && facebook_interstitialAd1.isAdLoaded()) {
            facebook_interstitialAd1.show();
        } else {
            AllFacebook_Fails_OtherAdShow(adview);
        }
        AllAdsPreLoadsInter("f1");
    }

    private static void FacebookInterShow2(String adview) {
        if (facebook_interstitialAd2 != null && facebook_interstitialAd2.isAdLoaded()) {
            facebook_interstitialAd2.show();
        } else {
            AllFacebook_Fails_OtherAdShow(adview);
        }
        AllAdsPreLoadsInter("f2");
    }

    private static void FacebookInterShow3(String adview) {
        if (facebook_interstitialAd3 != null && facebook_interstitialAd3.isAdLoaded()) {
            facebook_interstitialAd3.show();
        } else {
            AllFacebook_Fails_OtherAdShow(adview);
        }
        AllAdsPreLoadsInter("f3");
    }

    private static void AllFacebook_Fails_OtherAdShow(String adview) {

        if (adview.equals("r")) {
            Facebook_Fails_RegularAppLovingShow();
        } else if (adview.equals("f")) {
            GoogleADSShow("f");
        } else if (adview.equals("a")) {
            AllAds_Fails_Unity_Show();
        } else {
            Unity_Google_Facebook_ShowFails_ApplovinShowLisner();
        }

    }

    private static void Facebook_Fails_RegularAppLovingShow() {
        try {

            if (MyHelpers.getAppLovinInter() != null && !MyHelpers.getAppLovinInter().isEmpty()) {
                if (applovin_interstitialAd.isReady()) {
                    applovin_interstitialAd.showAd();
                } else {
                    AllAds_Fails_Unity_Show();
                }
            } else {
                AllAds_Fails_Unity_Show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        AllAdsPreLoadsInter("a");

    }

    /*AppLoving Inter Show*/
    private static void RegularAppLovingShow() {
        try {

            if (MyHelpers.getAppLovinInter() != null && !MyHelpers.getAppLovinInter().isEmpty()) {
                if (applovin_interstitialAd.isReady()) {
                    applovin_interstitialAd.showAd();
                } else {
                    GoogleADSShow("a");
                }
            } else {
                GoogleADSShow("a");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        AllAdsPreLoadsInter("a");

    }


    /*Unity Inter Show*/
    private static void UnityADSShow() {

        if (UnityAdLoadChecker) {
            UnityAds.show((Activity) main_context, MyHelpers.getUnityInterID(), new UnityAdsShowOptions(), new IUnityAdsShowListener() {
                @Override
                public void onUnityAdsShowFailure(String placementId, UnityAds.UnityAdsShowError error, String message) {
                    GoogleADSShow("u");
                    AllAdsPreLoadsInter("u");

                }

                @Override
                public void onUnityAdsShowStart(String placementId) {


                }

                @Override
                public void onUnityAdsShowClick(String placementId) {

                }

                @Override
                public void onUnityAdsShowComplete(String placementId, UnityAds.UnityAdsShowCompletionState state) {
                    AllAdsPreLoadsInter("u");

                }
            });
        } else {
            GoogleADSShow("u");
        }
    }

    private static void Unity_Google_Facebook_ShowFails_ApplovinShowLisner() {
        try {

            if (MyHelpers.getAppLovinInter() != null && !MyHelpers.getAppLovinInter().isEmpty()) {
                if (applovin_interstitialAd.isReady()) {
                    applovin_interstitialAd.showAd();
                } else {
                    CustomADSInter();
                }
            } else {
                CustomADSInter();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        AllAdsPreLoadsInter("a");

    }

    /*Custom Inter Show*/
    private static void CustomADSInter() {
        if (SplashHelp.adsModals != null && !SplashHelp.adsModals.isEmpty()) {
            main_context.startActivity(new Intent(main_context, CustomAdsInterActivity.class));
        }
    }

    /*Open Link*/
    public static void OpenLink() {
        if (MyHelpers.get_q_link_array() != null && !MyHelpers.get_q_link_array().isEmpty()) {
            MyHelpers.BtnAutolink();
        }
    }

    /**
     * Mix Ads
     */
    /*Helper*/
    private static void MixAds() {
        if (MyHelpers.getmix_ad_inter() != null && !MyHelpers.getmix_ad_inter().isEmpty()) {
            if (MyHelpers.getmix_ad_inter().length() == 1) {
                Mix1Ads(MyHelpers.getmix_ad_inter()); //1 ads
            } else if (MyHelpers.getmix_ad_inter().length() == 2) {
                Mix2Ads(MyHelpers.getmix_ad_inter());  // 2 ads
            } else {
                MixUnlimitedAdsInter(MyHelpers.getmix_ad_inter()); // Unlimited
            }
        }
    }

    private static void Mix1Ads(String s) {
        MixAdsShow(String.valueOf(s.charAt(0)));
    }

    private static void Mix2Ads(String s) {
        if (MyHelpers.getmix_ad_counter_inter() != 5000) {
            mix_adsInter++;
            if (MyHelpers.getmix_ad_counter_inter() + 1 == mix_adsInter) {
                MixAdsShow(String.valueOf(s.charAt(1)));
                mix_adsInter = 0;
            } else {
                MixAdsShow(String.valueOf(s.charAt(0)));
            }
        } else {
            if (mix_adsInter == 0) {
                mix_adsInter = 1;
                MixAdsShow(String.valueOf(s.charAt(0)));
            } else if (mix_adsInter == 1) {
                mix_adsInter = 0;
                MixAdsShow(String.valueOf(s.charAt(1)));
            }
        }
    }

    private static void MixUnlimitedAdsInter(String s) {
        MixAdsShow(String.valueOf(s.charAt(mix_adsInter)));
        if (MyHelpers.getmix_ad_inter().length() - 1 == mix_adsInter) {
            mix_adsInter = 0;
        } else {
            mix_adsInter++;
        }
    }

    private static void MixAdsShow(String value) {
        if (value.equals("g") && MyHelpers.getlive_status().equals("1")) {
            GoogleADSShow("r");
        } else if (value.equals("f") && MyHelpers.getlive_status().equals("1")) {
            FacebookADSShow("f");
        } else if (value.equals("a")) {
            RegularAppLovingShow();
        } else if (value.equals("u")) {
            UnityADSShow();
        } else if (value.equals("q")) {
            MyHelpers.BtnAutolink();
        } else if (value.equals("c")) {
            CustomADSInter();
        }
    }

    /**
     * PreLoad
     */
    /*Google*/
    public static void GoogleInterPreload() {
        try {
            AdRequest adRequest = new AdRequest.Builder().build();
            google_InterstitialAd.load(main_context, MyHelpers.getGoogleInter(), adRequest, new InterstitialAdLoadCallback() {
                @Override
                public void onAdLoaded(@NonNull com.google.android.gms.ads.interstitial.InterstitialAd interstitialAd) {
                    super.onAdLoaded(interstitialAd);
                    google_InterstitialAd = interstitialAd;
                }

                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);
                    google_InterstitialAd = null;
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void GoogleInterPreload1() {

        try {
            AdRequest adRequest = new AdRequest.Builder().build();
            google_InterstitialAd_1.load(main_context, MyHelpers.getGoogleInter(), adRequest, new InterstitialAdLoadCallback() {
                @Override
                public void onAdLoaded(@NonNull com.google.android.gms.ads.interstitial.InterstitialAd interstitialAd) {
                    super.onAdLoaded(interstitialAd);
                    google_InterstitialAd_1 = interstitialAd;
                }

                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);
                    google_InterstitialAd_1 = null;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void GoogleInterPreload2() {
        try {
            AdRequest adRequest = new AdRequest.Builder().build();
            google_InterstitialAd_2.load(main_context, MyHelpers.getGoogleInter1(), adRequest, new InterstitialAdLoadCallback() {
                @Override
                public void onAdLoaded(@NonNull com.google.android.gms.ads.interstitial.InterstitialAd interstitialAd) {
                    super.onAdLoaded(interstitialAd);
                    google_InterstitialAd_2 = interstitialAd;

                }

                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);
                    google_InterstitialAd_2 = null;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void GoogleInterPreload3() {
        try {
            AdRequest adRequest = new AdRequest.Builder().build();
            google_InterstitialAd_3.load(main_context, MyHelpers.getGoogleInter2(), adRequest, new InterstitialAdLoadCallback() {
                @Override
                public void onAdLoaded(@NonNull com.google.android.gms.ads.interstitial.InterstitialAd interstitialAd) {
                    super.onAdLoaded(interstitialAd);
                    google_InterstitialAd_3 = interstitialAd;
                }

                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);
                    google_InterstitialAd_3 = null;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*Facebook*/
    public static void FacebookInterPreLoad() {
        try {
            facebook_interstitialAd = new com.facebook.ads.InterstitialAd(main_context, MyHelpers.getFacebookInter());
            InterstitialAdListener adListener = new InterstitialAdListener() {
                @Override
                public void onInterstitialDisplayed(Ad ad) {
                }

                @Override
                public void onInterstitialDismissed(Ad ad) {

                }

                @Override
                public void onError(Ad ad, com.facebook.ads.AdError adError) {
                    facebook_interstitialAd = null;
                }

                @Override
                public void onAdLoaded(Ad ad) {

                }

                @Override
                public void onAdClicked(Ad ad) {

                }

                @Override
                public void onLoggingImpression(Ad ad) {

                }
            };
            facebook_interstitialAd.loadAd(facebook_interstitialAd.buildLoadAdConfig().withAdListener(adListener).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void FacebookInterPreLoad1() {
        try {
            facebook_interstitialAd1 = new com.facebook.ads.InterstitialAd(main_context, MyHelpers.getFacebookInter());
            InterstitialAdListener adListener = new InterstitialAdListener() {
                @Override
                public void onInterstitialDisplayed(Ad ad) {
                }

                @Override
                public void onInterstitialDismissed(Ad ad) {

                }

                @Override
                public void onError(Ad ad, com.facebook.ads.AdError adError) {
                    facebook_interstitialAd1 = null;
                }

                @Override
                public void onAdLoaded(Ad ad) {

                }

                @Override
                public void onAdClicked(Ad ad) {

                }

                @Override
                public void onLoggingImpression(Ad ad) {

                }
            };
            facebook_interstitialAd1.loadAd(facebook_interstitialAd1.buildLoadAdConfig().withAdListener(adListener).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void FacebookInterPreLoad2() {
        try {
            facebook_interstitialAd2 = new com.facebook.ads.InterstitialAd(main_context, MyHelpers.getFacebookInter1());
            InterstitialAdListener adListener = new InterstitialAdListener() {
                @Override
                public void onInterstitialDisplayed(Ad ad) {
                }

                @Override
                public void onInterstitialDismissed(Ad ad) {

                }

                @Override
                public void onError(Ad ad, com.facebook.ads.AdError adError) {
                    facebook_interstitialAd2 = null;
                }

                @Override
                public void onAdLoaded(Ad ad) {

                }

                @Override
                public void onAdClicked(Ad ad) {

                }

                @Override
                public void onLoggingImpression(Ad ad) {

                }
            };
            facebook_interstitialAd2.loadAd(facebook_interstitialAd2.buildLoadAdConfig().withAdListener(adListener).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void FacebookInterPreLoad3() {
        try {
            facebook_interstitialAd3 = new com.facebook.ads.InterstitialAd(main_context, MyHelpers.getFacebookInter2());
            InterstitialAdListener adListener = new InterstitialAdListener() {
                @Override
                public void onInterstitialDisplayed(Ad ad) {
                }

                @Override
                public void onInterstitialDismissed(Ad ad) {

                }

                @Override
                public void onError(Ad ad, com.facebook.ads.AdError adError) {
                    facebook_interstitialAd3 = null;
                }

                @Override
                public void onAdLoaded(Ad ad) {

                }

                @Override
                public void onAdClicked(Ad ad) {

                }

                @Override
                public void onLoggingImpression(Ad ad) {

                }
            };
            facebook_interstitialAd3.loadAd(facebook_interstitialAd3.buildLoadAdConfig().withAdListener(adListener).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*AppLoving*/
    public static void AppLovingInterPreLoad() {

        applovin_interstitialAd = new MaxInterstitialAd(MyHelpers.getAppLovinInter(), (Activity) main_context);
        applovin_interstitialAd.setListener(new MaxAdListener() {
            @Override
            public void onAdLoaded(MaxAd ad) {

            }

            @Override
            public void onAdDisplayed(MaxAd ad) {
            }

            @Override
            public void onAdHidden(MaxAd ad) {
//                AllAdsPreLoadsInter("a");
            }

            @Override
            public void onAdClicked(MaxAd ad) {
            }

            @Override
            public void onAdLoadFailed(String adUnitId, MaxError error) {
                applovin_interstitialAd = null;

            }

            @Override
            public void onAdDisplayFailed(MaxAd ad, MaxError error) {

            }
        });
        applovin_interstitialAd.loadAd();

    }

    /*Unity*/
    public static void UnityInterPreLoad() {

        UnityAds.load(MyHelpers.getUnityInterID(), new IUnityAdsLoadListener() {
            @Override
            public void onUnityAdsAdLoaded(String placementId) {
                UnityAdLoadChecker = true;

            }

            @Override
            public void onUnityAdsFailedToLoad(String placementId, UnityAds.UnityAdsLoadError error, String message) {
                UnityAdLoadChecker = false;

            }
        });
    }

    /*All Preload*/
    public static void AllAdsPreLoadsInter(String refresh) {

        if (refresh.equals("g")) {
            google_InterstitialAd = null;
        } else if (refresh.equals("g1")) {
            google_InterstitialAd_1 = null;
        } else if (refresh.equals("g2")) {
            google_InterstitialAd_2 = null;
        } else if (refresh.equals("g3")) {
            google_InterstitialAd_3 = null;
        } else if (refresh.equals("f")) {
            facebook_interstitialAd = null;
        } else if (refresh.equals("f1")) {
            facebook_interstitialAd1 = null;
        } else if (refresh.equals("f2")) {
            facebook_interstitialAd2 = null;
        } else if (refresh.equals("f3")) {
            facebook_interstitialAd3 = null;
        } else if (refresh.equals("a")) {
            applovin_interstitialAd = null;
        } else if (refresh.equals("u")) {
            UnityAdLoadChecker = false;
        }


        /*Google*/
        if (MyHelpers.Google_inter_number == 1) {
            if (MyHelpers.getGoogleInter() != null && !MyHelpers.getGoogleInter().isEmpty()) {
                if (google_InterstitialAd == null) {
                    GoogleInterPreload();
                }
            }
        } else if (MyHelpers.Google_inter_number == 2) {
            if (MyHelpers.getGoogleInter() != null && !MyHelpers.getGoogleInter().isEmpty()) {
                if (google_InterstitialAd_1 == null) {
                    GoogleInterPreload1();
                }

            }
            if (MyHelpers.getGoogleInter1() != null && !MyHelpers.getGoogleInter1().isEmpty()) {
                if (google_InterstitialAd_2 == null) {
                    GoogleInterPreload2();
                }

            }
        } else if (MyHelpers.Google_inter_number == 3) {
            if (MyHelpers.getGoogleInter() != null && !MyHelpers.getGoogleInter().isEmpty()) {
                if (google_InterstitialAd_1 == null) {
                    GoogleInterPreload1();
                }
            }
            if (MyHelpers.getGoogleInter1() != null && !MyHelpers.getGoogleInter1().isEmpty()) {
                if (google_InterstitialAd_2 == null) {
                    GoogleInterPreload2();
                }
            }
            if (MyHelpers.getGoogleInter2() != null && !MyHelpers.getGoogleInter2().isEmpty()) {
                if (google_InterstitialAd_3 == null) {
                    GoogleInterPreload3();

                }
            }
        }

        /*Facebook*/
        if (MyHelpers.fb_inter_number == 1) {

            if (MyHelpers.getFacebookInter() != null && !MyHelpers.getFacebookInter().isEmpty()) {
                if (facebook_interstitialAd == null) {
                    FacebookInterPreLoad();
                }
            }

        } else if (MyHelpers.fb_inter_number == 2) {

            if (MyHelpers.getFacebookInter() != null && !MyHelpers.getFacebookInter().isEmpty()) {
                if (facebook_interstitialAd1 == null) {
                    FacebookInterPreLoad1();
                }
            }
            if (MyHelpers.getFacebookInter1() != null && !MyHelpers.getFacebookInter1().isEmpty()) {
                if (facebook_interstitialAd2 == null) {
                    FacebookInterPreLoad2();
                }
            }

        } else if (MyHelpers.fb_inter_number == 3) {

            if (MyHelpers.getFacebookInter() != null && !MyHelpers.getFacebookInter().isEmpty()) {
                if (facebook_interstitialAd1 == null) {
                    FacebookInterPreLoad1();
                }
            }
            if (MyHelpers.getFacebookInter1() != null && !MyHelpers.getFacebookInter1().isEmpty()) {
                if (facebook_interstitialAd2 == null) {
                    FacebookInterPreLoad2();
                }
            }
            if (MyHelpers.getFacebookInter2() != null && !MyHelpers.getFacebookInter2().isEmpty()) {
                if (facebook_interstitialAd3 == null) {
                    FacebookInterPreLoad3();
                }
            }

        }

        /*App Loving*/
        if (MyHelpers.getAppLovinInter() != null && !MyHelpers.getAppLovinInter().isEmpty()) {
            if (applovin_interstitialAd == null) {
                AppLovingInterPreLoad();
            }
        }

        /*Unity*/
        if (MyHelpers.getUnityInterID() != null && !MyHelpers.getUnityInterID().isEmpty()) {
            if (!UnityAdLoadChecker) {
                UnityInterPreLoad();
            }
        }

    }
}
