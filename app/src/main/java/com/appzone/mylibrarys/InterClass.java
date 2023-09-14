package com.appzone.mylibrarys;

import static com.appzone.mylibrarys.MyHelpers.BtnAutolink;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.facebook.ads.Ad;
import com.facebook.ads.InterstitialAdListener;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class InterClass {
    public static int mix_adsInter = 0;
    public static int auto_notShow_ads_inter = 0;
    public static int google_inter_count_number = 0;
    public static int fb_inter_count_number = 0;
    public static com.facebook.ads.InterstitialAd facebook_interstitial_loading;
    public static com.facebook.ads.InterstitialAd facebook_interstitial_loading_main;
    public static com.facebook.ads.InterstitialAd facebook_interstitial_loading_main_1;
    public static com.facebook.ads.InterstitialAd facebook_interstitial_loading_main_2;

    /*Helper*/
    @SuppressLint("StaticFieldLeak")
    public static Activity main_context;
    public static int auto_notShow_adsBack = 0;
    public static Intent main_intent;
    public static int intent_status;
    public static Dialog on_loading_dialog;
    public static Intent qureka_intent;


    /**
     * INTERSTITIAL ADS CODE START
     */
    public static void Interstitial(Activity context, Intent intent, int i) {
        main_context = context;
        main_intent = intent;
        intent_status = i;
        /*
          ActivityFinish == 0 next activity
          ActivityFinish == 1 next and finish activity
          ActivityFinish == 2 finish activity
         */

        //Internet
        if (!MyHelpers.isOnline(context)) {
            context.startActivity(new Intent(context, InternetErrorActivity.class));
            return;
        }

        /*Stop Ads*/
        if (MyHelpers.getCounter_Inter() == 0) {
            NextIntent();
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
            NextIntent();
            return;
        }

        /*Mix and Regular ads*/
        if (MyHelpers.getmix_ad_on_off().equals("1")) {
            MixAds();
        } else {
            RegularADS();
        }

    }

    /**
     * Back Btn Interstitial
     */
    public static void BackInterstitial(Activity context, Intent intent) {
        main_context = context;
        main_intent = intent;

        if (MyHelpers.getBackAdsOnOff().equals("1")) {

            //Internet
            if (!MyHelpers.isOnline(context)) {
                context.startActivity(new Intent(context, InternetErrorActivity.class));
                return;
            }

            /*Stop Ads*/
            if (MyHelpers.getBackCounter() == 0) {
                NextIntent();
                return;
            }

                /*
                  Skip Ads
                 */

            if (MyHelpers.getBackCounter() != 5000) {
                auto_notShow_adsBack++;
                if (MyHelpers.getBackCounter() + 1 == auto_notShow_adsBack) {
                    auto_notShow_adsBack = 0;
                    if (MyHelpers.getmix_ad_on_off().equals("1")) {
                        MixAds();
                    } else {
                        RegularADS();
                    }

                    return;
                }
                NextIntent();
                return;
            }

            /*
             Mix Ads
             */
            if (MyHelpers.getmix_ad_on_off().equals("1")) {
                MixAds();
            } else {
                RegularADS();
            }

        } else {
            NextIntent();
        }

    }

    /**
     * Regular Ads
     */
    private static void RegularADS() {

        if (MyHelpers.getGoogleEnable().equals("1")) {

            if (google_inter_count_number > 2) {
                google_inter_count_number = 0;
            }

            if (google_inter_count_number == 0) {
                GoogleADSShowONDemand();
            } else if (google_inter_count_number == 1) {
                GoogleADSShowONDemand_1();
            } else if (google_inter_count_number == 2) {
                GoogleADSShowONDemand_2();
            }


        } else if (MyHelpers.getFacebookEnable().equals("1")) {

            if (fb_inter_count_number > 2) {
                fb_inter_count_number = 0;
            }

            if (fb_inter_count_number == 0) {
                FacebookADSShowONDemand();
            } else if (fb_inter_count_number == 1) {
                FacebookADSShowONDemand_1();
            } else if (fb_inter_count_number == 2) {
                FacebookADSShowONDemand_2();
            }

        } else if (MyHelpers.get_q_link_btn_on_off().equals("1")) {
            showLoading(main_context, false);
            QurekaInter();
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

                        hideLoading();
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
                                google_inter_count_number++;
                                NextIntent();
                            }

                        });

                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        super.onAdFailedToLoad(loadAdError);
                        google_inter_count_number++;
                        GoogleAdsShowOnDemandFail_showFB();
                    }
                });

            } else {
                showLoading(main_context, false);
                google_inter_count_number++;
                GoogleAdsShowOnDemandFail_showFB();
            }

        } catch (Exception e) {
            e.printStackTrace();
            hideLoading();
        }
    }

    private static void GoogleADSShowONDemand_1() {

        try {

            if (MyHelpers.getGoogleInter1() != null && !MyHelpers.getGoogleInter1().isEmpty()) {

                showLoading(main_context, false);
                AdRequest adRequest = new AdRequest.Builder().build();
                com.google.android.gms.ads.interstitial.InterstitialAd.load(main_context, MyHelpers.getGoogleInter1(), adRequest, new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull com.google.android.gms.ads.interstitial.InterstitialAd interstitialAd) {
                        super.onAdLoaded(interstitialAd);

                        hideLoading();
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
                                google_inter_count_number++;
                                NextIntent();
                            }

                        });

                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        super.onAdFailedToLoad(loadAdError);
                        google_inter_count_number++;
                        GoogleAdsShowOnDemandFail_showFB();
                    }
                });

            } else {
                showLoading(main_context, false);
                google_inter_count_number++;
                GoogleAdsShowOnDemandFail_showFB();
            }

        } catch (Exception e) {
            e.printStackTrace();
            hideLoading();
        }
    }

    private static void GoogleADSShowONDemand_2() {

        try {

            if (MyHelpers.getGoogleInter2() != null && !MyHelpers.getGoogleInter2().isEmpty()) {

                showLoading(main_context, false);
                AdRequest adRequest = new AdRequest.Builder().build();
                com.google.android.gms.ads.interstitial.InterstitialAd.load(main_context, MyHelpers.getGoogleInter2(), adRequest, new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull com.google.android.gms.ads.interstitial.InterstitialAd interstitialAd) {
                        super.onAdLoaded(interstitialAd);

                        hideLoading();
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
                                google_inter_count_number++;
                                NextIntent();
                            }
                        });
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        super.onAdFailedToLoad(loadAdError);
                        google_inter_count_number++;
                        GoogleAdsShowOnDemandFail_showFB();
                    }
                });

            } else {
                showLoading(main_context, false);
                google_inter_count_number++;
                GoogleAdsShowOnDemandFail_showFB();
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
                    NextIntent();
                }

                @Override
                public void onError(Ad ad, com.facebook.ads.AdError adError) {
                    facebook_interstitial_loading = null;
                    QurekaInter();
                }

                @Override
                public void onAdLoaded(Ad ad) {
                    hideLoading();
                    if (facebook_interstitial_loading != null && facebook_interstitial_loading.isAdLoaded()) {
                        facebook_interstitial_loading.show();
                    }
                }

                @Override
                public void onAdClicked(Ad ad) {

                }

                @Override
                public void onLoggingImpression(Ad ad) {

                }
            };
            facebook_interstitial_loading.loadAd(facebook_interstitial_loading.buildLoadAdConfig().withAdListener(adListener).build());
        } else {
            QurekaInter();
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
                        fb_inter_count_number++;
                        NextIntent();
                    }

                    @Override
                    public void onError(Ad ad, com.facebook.ads.AdError adError) {
                        facebook_interstitial_loading_main = null;
                        fb_inter_count_number++;
                        FacebookAdsShowOnDemandFail_showG();
                    }

                    @Override
                    public void onAdLoaded(Ad ad) {
                        hideLoading();
                        if (facebook_interstitial_loading_main != null && facebook_interstitial_loading_main.isAdLoaded()) {
                            facebook_interstitial_loading_main.show();
                        }
                    }

                    @Override
                    public void onAdClicked(Ad ad) {

                    }

                    @Override
                    public void onLoggingImpression(Ad ad) {

                    }
                };
                facebook_interstitial_loading_main.loadAd(facebook_interstitial_loading_main.buildLoadAdConfig().withAdListener(adListener).build());
            } else {
                showLoading(main_context, false);
                fb_inter_count_number++;
                FacebookAdsShowOnDemandFail_showG();
            }

        } catch (Exception e) {
            e.printStackTrace();
            hideLoading();
        }
    }

    private static void FacebookADSShowONDemand_1() {

        try {

            if (MyHelpers.getFacebookInter1() != null && !MyHelpers.getFacebookInter1().isEmpty()) {

                showLoading(main_context, false);
                facebook_interstitial_loading_main_1 = new com.facebook.ads.InterstitialAd(main_context, MyHelpers.getFacebookInter1());
                InterstitialAdListener adListener = new InterstitialAdListener() {
                    @Override
                    public void onInterstitialDisplayed(Ad ad) {

                    }

                    @Override
                    public void onInterstitialDismissed(Ad ad) {
                        fb_inter_count_number++;
                        NextIntent();
                    }

                    @Override
                    public void onError(Ad ad, com.facebook.ads.AdError adError) {
                        facebook_interstitial_loading_main_1 = null;
                        fb_inter_count_number++;
                        FacebookAdsShowOnDemandFail_showG();
                    }

                    @Override
                    public void onAdLoaded(Ad ad) {
                        hideLoading();
                        if (facebook_interstitial_loading_main_1 != null && facebook_interstitial_loading_main_1.isAdLoaded()) {
                            facebook_interstitial_loading_main_1.show();
                        }
                    }

                    @Override
                    public void onAdClicked(Ad ad) {

                    }

                    @Override
                    public void onLoggingImpression(Ad ad) {

                    }
                };
                facebook_interstitial_loading_main_1.loadAd(facebook_interstitial_loading_main_1.buildLoadAdConfig().withAdListener(adListener).build());
            } else {
                showLoading(main_context, false);
                fb_inter_count_number++;
                FacebookAdsShowOnDemandFail_showG();
            }

        } catch (Exception e) {
            e.printStackTrace();
            hideLoading();
        }
    }

    private static void FacebookADSShowONDemand_2() {

        try {

            if (MyHelpers.getFacebookInter2() != null && !MyHelpers.getFacebookInter2().isEmpty()) {

                showLoading(main_context, false);
                facebook_interstitial_loading_main_2 = new com.facebook.ads.InterstitialAd(main_context, MyHelpers.getFacebookInter2());
                InterstitialAdListener adListener = new InterstitialAdListener() {
                    @Override
                    public void onInterstitialDisplayed(Ad ad) {

                    }

                    @Override
                    public void onInterstitialDismissed(Ad ad) {
                        fb_inter_count_number++;
                        NextIntent();
                    }

                    @Override
                    public void onError(Ad ad, com.facebook.ads.AdError adError) {
                        facebook_interstitial_loading_main_2 = null;
                        fb_inter_count_number++;
                        FacebookAdsShowOnDemandFail_showG();
                    }

                    @Override
                    public void onAdLoaded(Ad ad) {
                        hideLoading();
                        if (facebook_interstitial_loading_main_2 != null && facebook_interstitial_loading_main_2.isAdLoaded()) {
                            facebook_interstitial_loading_main_2.show();
                        }
                    }

                    @Override
                    public void onAdClicked(Ad ad) {

                    }

                    @Override
                    public void onLoggingImpression(Ad ad) {

                    }
                };
                facebook_interstitial_loading_main_2.loadAd(facebook_interstitial_loading_main_2.buildLoadAdConfig().withAdListener(adListener).build());
            } else {
                showLoading(main_context, false);
                fb_inter_count_number++;
                FacebookAdsShowOnDemandFail_showG();
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

                    hideLoading();
                    interstitialAd.show(main_context);
                    interstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                        @Override
                        public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                            super.onAdFailedToShowFullScreenContent(adError);
                            QurekaInter();
                        }

                        @Override
                        public void onAdShowedFullScreenContent() {
                            super.onAdShowedFullScreenContent();

                        }

                        @Override
                        public void onAdDismissedFullScreenContent() {
                            super.onAdDismissedFullScreenContent();
                            NextIntent();
                        }
                    });
                }

                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);
                    QurekaInter();
                }
            });

        } else {
            QurekaInter();
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

    /*Open Link*/
    public static void OpenLink() {
        if (MyHelpers.get_q_link_array() != null && !MyHelpers.get_q_link_array().isEmpty()) {
            BtnAutolink();
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
        if (value.equals("g")) {
            if (google_inter_count_number > 2) {
                google_inter_count_number = 0;
            }

            if (google_inter_count_number == 0) {
                GoogleADSShowONDemand();
            } else if (google_inter_count_number == 1) {
                GoogleADSShowONDemand_1();
            } else if (google_inter_count_number == 2) {
                GoogleADSShowONDemand_2();
            }
        } else if (value.equals("f")) {
            if (fb_inter_count_number > 2) {
                fb_inter_count_number = 0;
            }

            if (fb_inter_count_number == 0) {
                FacebookADSShowONDemand();
            } else if (fb_inter_count_number == 1) {
                FacebookADSShowONDemand_1();
            } else if (fb_inter_count_number == 2) {
                FacebookADSShowONDemand_2();
            }
        } else if (value.equals("q")) {
            showLoading(main_context, false);
            QurekaInter();
        }
    }

    /**
     * Qureka
     */
    private static void QurekaInter() {
        hideLoading();
        qureka_intent = main_intent;
        if (main_intent == null) {
            main_context.startActivity(new Intent(main_context, QurekaInterActivity.class));
            main_context.finish();
        } else {
            if (intent_status == 0) {
                main_context.startActivity(new Intent(main_context, QurekaInterActivity.class));
            } else if (intent_status == 1) {
                main_context.startActivity(new Intent(main_context, QurekaInterActivity.class));
                main_context.finish();
            }
        }
    }

    public static void NextIntent() {
        if (main_intent == null) {
            main_context.finish();
        } else {
            if (intent_status == 0) {
                main_context.startActivity(main_intent);
            } else if (intent_status == 1) {
                main_context.startActivity(main_intent);
                main_context.finish();
            }
        }
    }
}
