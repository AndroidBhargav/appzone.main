package com.appzone.mylibrarys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdViewAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxAdView;
import com.bumptech.glide.Glide;
import com.facebook.ads.Ad;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.unity3d.services.banners.BannerErrorInfo;
import com.unity3d.services.banners.BannerView;
import com.unity3d.services.banners.UnityBannerSize;

import java.util.Objects;

public class BannerClass {

    /*Google*/
    public static AdLoader regular_google_banner_ad_loader;
    public static AdLoader regular_google_banner_ad_loader_1;
    public static AdLoader regular_google_banner_ad_loader_2;
    public static AdLoader regular_google_banner_ad_loader_3;
    public static com.google.android.gms.ads.nativead.NativeAd regular_google_native_banner = null;
    public static com.google.android.gms.ads.nativead.NativeAd regular_google_native_banner_1 = null;
    public static com.google.android.gms.ads.nativead.NativeAd regular_google_native_banner_2 = null;
    public static com.google.android.gms.ads.nativead.NativeAd regular_google_native_banner_3 = null;
    public static int AutoGoogleBannerID;

    /*Facebook*/
    public static com.facebook.ads.AdView regular_facebook_banner_adView = null;
    public static int AutoLoadFBBannerID;

    /*AppLovin*/
    public static MaxAdView regular_applovin_banner_adView = null;

    /*Unity*/
    public static BannerView regular_unity_banner_adView;

    /*Mix*/
    public static int banner_skip_ads = 0;
    public static int mix_ads_banner = 0;
    public static int auto_banner_show_id = 0;

    /*Helper*/
    public static RelativeLayout main_banner;
    public static Context main_context;

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
     * BANNER ADS CODE START
     */
    public static void Banner(Context context1, RelativeLayout main_banner1) {
        main_banner = main_banner1;
        main_context = context1;

        if (checkConnection(main_context)) {
            /**
             * Stop Ads
             */
            if (MyHelpers.getCounter_Banner() == 0) {
                return;
            }
            /**
             * Skip Ads
             */
            if (MyHelpers.getCounter_Banner() != 5000) {
                banner_skip_ads++;
                if (MyHelpers.getCounter_Banner() + 1 == banner_skip_ads) {
                    banner_skip_ads = 0;
                    return;
                }
            }

            /**
             * Mix Ads
             */
            if (MyHelpers.getmix_ad_on_off().equals("1")) {
                BannerMixAds();
            } else {
                RegularBannerAdsShow();
            }


        } else {
            RegularCustomBannerAdsShow();
        }
    }


    /**
     * Regular Banner AdsShow
     */
    /*Regula*/
    private static void RegularBannerAdsShow() {
        if (MyHelpers.getGoogleEnable().equals("1") && MyHelpers.getlive_status().equals("1")) {
            RegularGoogleADSBannerShow("r");
        } else if (MyHelpers.getFacebookEnable().equals("1") && MyHelpers.getlive_status().equals("1")) {
            RegularFacebookBannerShow();
        } else if (MyHelpers.getAppLovinEnable().equals("1")) {
            RegularAppLovingBannerShow();
        } else if (MyHelpers.getUnityEnable().equals("1")) {
            RegularUnityBannerShow();
        } else if (MyHelpers.getCustomEnable().equals("1")) {
            RegularCustomBannerAdsShow();
        } else {
            main_banner.removeAllViews();
        }
    }

    /*Google*/
    private static void RegularGoogleADSBannerShow(String checker) {
        if (MyHelpers.Google_banner_number == 1) {
            RegularGoogleBannerShow(checker);
        } else if (MyHelpers.Google_banner_number == 2) {
            if (auto_banner_show_id == 0) {
                auto_banner_show_id = 1;
                RegularGoogleBannerShow_1(checker);
            } else {
                auto_banner_show_id = 0;
                RegularGoogleBannerShow_2(checker);
            }
        } else if (MyHelpers.Google_banner_number == 3) {
            if (auto_banner_show_id == 0) {
                auto_banner_show_id = 1;
                RegularGoogleBannerShow_1(checker);
            } else if (auto_banner_show_id == 1) {
                auto_banner_show_id = 2;
                RegularGoogleBannerShow_2(checker);
            } else {
                auto_banner_show_id = 0;
                RegularGoogleBannerShow_3(checker);
            }
        }
    }

    public static void RegularGoogleBannerShow(String checker) {
        if (regular_google_native_banner != null) {
            RegularGoogleBannerPopulateShow(regular_google_native_banner);
        } else {
            AllGoogleBannerFails_OtherAdsShow(checker);
        }
        AllAdsPreLoadsBanner("g");
    }

    public static void RegularGoogleBannerShow_1(String checker) {
        if (regular_google_native_banner_1 != null) {
            RegularGoogleBannerPopulateShow(regular_google_native_banner_1);
        } else {
            AllGoogleBannerFails_OtherAdsShow(checker);
        }

        AllAdsPreLoadsBanner("g1");

    }

    public static void RegularGoogleBannerShow_2(String checker) {
        if (regular_google_native_banner_2 != null) {
            RegularGoogleBannerPopulateShow(regular_google_native_banner_2);
        } else {
            AllGoogleBannerFails_OtherAdsShow(checker);
        }
        AllAdsPreLoadsBanner("g2");
    }

    public static void RegularGoogleBannerShow_3(String checker) {
        if (regular_google_native_banner_3 != null) {
            RegularGoogleBannerPopulateShow(regular_google_native_banner_3);
        } else {
            AllGoogleBannerFails_OtherAdsShow(checker);
        }
        AllAdsPreLoadsBanner("g3");
    }

    private static void Google_Fails_Facebook_Banner_Show() {
        if (regular_facebook_banner_adView != null) {
            main_banner.removeAllViews();
            main_banner.addView(regular_facebook_banner_adView);
        } else {
            Google_Facebook_Fails_AppLovin_Unity_Banner_Show();
        }
        AllAdsPreLoadsBanner("f");
    }

    private static void Google_Facebook_Fails_AppLovin_Unity_Banner_Show() {
        if (regular_applovin_banner_adView != null) {
            main_banner.removeAllViews();
            main_banner.addView(regular_applovin_banner_adView);
        } else {
            Google_Facebook_AppLovin_Fails_Unity_Banner_Show();
        }
        AllAdsPreLoadsBanner("a");
    }

    private static void Google_Facebook_AppLovin_Fails_Unity_Banner_Show() {
        if (regular_unity_banner_adView != null) {
            main_banner.removeAllViews();
            main_banner.addView(regular_unity_banner_adView);
        } else {
            RegularCustomBannerAdsShow();
        }
        AllAdsPreLoadsBanner("u");
    }

    public static void RegularGoogleBannerPopulateShow(com.google.android.gms.ads.nativead.NativeAd nativeAd) {
        View layout_ad_view = LayoutInflater.from(main_context).inflate(R.layout.ad_google_native_small_banner, null);
        com.google.android.gms.ads.nativead.NativeAdView native_ad_view = layout_ad_view.findViewById(R.id.ad_view_small_banner);
        native_ad_view.setHeadlineView(native_ad_view.findViewById(R.id.ad_headline_small_banner));
        native_ad_view.setBodyView(native_ad_view.findViewById(R.id.ad_body_small_banner));
        native_ad_view.setCallToActionView(native_ad_view.findViewById(R.id.ad_call_to_action_small_banner));
        native_ad_view.setIconView(native_ad_view.findViewById(R.id.ad_app_icon_small_banner));
        ((TextView) Objects.requireNonNull(native_ad_view.getHeadlineView())).setText(nativeAd.getHeadline());
        ((TextView) Objects.requireNonNull(native_ad_view.getBodyView())).setText(nativeAd.getBody());
        ((TextView) Objects.requireNonNull(native_ad_view.getCallToActionView())).setText(nativeAd.getCallToAction());
        if (nativeAd.getIcon() == null) {
            Objects.requireNonNull(native_ad_view.getIconView()).setVisibility(View.GONE);
        } else {
            ((ImageView) Objects.requireNonNull(native_ad_view.getIconView())).setImageDrawable(nativeAd.getIcon().getDrawable());
            native_ad_view.getIconView().setVisibility(View.VISIBLE);
        }
        native_ad_view.setNativeAd(nativeAd);
        main_banner.removeAllViews();
        main_banner.addView(layout_ad_view);
    }

    private static void AllGoogleBannerFails_OtherAdsShow(String checker) {
        if (checker.equals("r")) {
            Google_Fails_Facebook_Banner_Show();
        } else if (checker.equals("f")) {
            Facebook_Google_Fails_Applovin_Unity_Banner_Show();
        } else if (checker.equals("a")) {
            Applovin_Google_Fails_Facebook_Unity_Banner_Show();
        } else if (checker.equals("u")) {
            Unity_Google_Fails_Facebook_Applovin_Banner_Show();
        }
    }

    /*Facebook*/
    public static void RegularFacebookBannerShow() {
        if (regular_facebook_banner_adView != null) {
            main_banner.removeAllViews();
            main_banner.addView(regular_facebook_banner_adView);
        } else {
            RegularGoogleADSBannerShow("f");
        }
        AllAdsPreLoadsBanner("f");
    }

    private static void Facebook_Google_Fails_Applovin_Unity_Banner_Show() {
        if (regular_applovin_banner_adView != null) {
            main_banner.removeAllViews();
            main_banner.addView(regular_applovin_banner_adView);
        } else {
            Google_Facebook_AppLovin_Fails_Unity_Banner_Show();
        }
        AllAdsPreLoadsBanner("a");
    }

    /*AppLoving*/
    public static void RegularAppLovingBannerShow() {
        if (regular_applovin_banner_adView != null) {
            main_banner.removeAllViews();
            main_banner.addView(regular_applovin_banner_adView);
        } else {
            RegularGoogleADSBannerShow("a");
        }
        AllAdsPreLoadsBanner("a");
    }

    private static void Applovin_Google_Fails_Facebook_Unity_Banner_Show() {
        if (regular_facebook_banner_adView != null) {
            main_banner.removeAllViews();
            main_banner.addView(regular_facebook_banner_adView);
        } else {
            Google_Facebook_AppLovin_Fails_Unity_Banner_Show();
        }
        AllAdsPreLoadsBanner("f");
    }

    /*Unity*/
    public static void RegularUnityBannerShow() {
        if (regular_unity_banner_adView != null) {
            main_banner.removeAllViews();
            main_banner.addView(regular_unity_banner_adView);
        } else {
            RegularGoogleADSBannerShow("u");
        }
        AllAdsPreLoadsBanner("u");
    }

    private static void Unity_Google_Fails_Facebook_Applovin_Banner_Show() {
        if (regular_facebook_banner_adView != null) {
            main_banner.removeAllViews();
            main_banner.addView(regular_facebook_banner_adView);
        } else {
            Unity_Google_Facebook_Fails_Applovin_Banner_Show();
        }
        AllAdsPreLoadsBanner("f");
    }

    private static void Unity_Google_Facebook_Fails_Applovin_Banner_Show() {
        if (regular_applovin_banner_adView != null) {
            main_banner.removeAllViews();
            main_banner.addView(regular_applovin_banner_adView);
        } else {
            RegularCustomBannerAdsShow();
        }
        AllAdsPreLoadsBanner("a");
    }

    /*Custom*/
    private static void RegularCustomBannerAdsShow() {
        if (SplashHelp.adsModals.size() == 0) {
            main_banner.removeAllViews();
            return;
        }

        int ads_number = MyHelpers.getRandomNumber(0, SplashHelp.adsModals.size() - 1);
        LinearLayout banner_view = (LinearLayout) ((Activity) main_context).getLayoutInflater().inflate(R.layout.custom_banner, (ViewGroup) null);
        TextView btn_install = (TextView) banner_view.findViewById(R.id.btn_install_banner);
        RelativeLayout full_click = banner_view.findViewById(R.id.full_click_banner);
        TextView app_name = banner_view.findViewById(R.id.app_name_banner);
        TextView app_shot = banner_view.findViewById(R.id.app_shot_banner);
        ImageView app_icon = banner_view.findViewById(R.id.app_icon_banner);
        Glide.with(main_context).load(SplashHelp.adsModals.get(ads_number).getApp_logo()).into(app_icon);
        app_name.setText(SplashHelp.adsModals.get(ads_number).getAd_app_name());
        app_shot.setText(SplashHelp.adsModals.get(ads_number).getApp_description());
        btn_install.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    main_context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + SplashHelp.adsModals.get(ads_number).getApp_name())));
                } catch (android.content.ActivityNotFoundException anfe) {
                    main_context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + SplashHelp.adsModals.get(ads_number).getApp_name())));
                }
            }
        });
        full_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    main_context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + SplashHelp.adsModals.get(ads_number).getApp_name())));
                } catch (android.content.ActivityNotFoundException anfe) {
                    main_context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + SplashHelp.adsModals.get(ads_number).getApp_name())));
                }
            }
        });
        main_banner.removeAllViews();
        main_banner.addView(banner_view);
    }

    /**
     * Mix Ads Show
     */

    /*Mix Ads Helper*/
    private static void BannerMixAds() {
        if (MyHelpers.getmix_ad_banner().length() == 0) {
            main_banner.removeAllViews();
        } else {
            if (MyHelpers.getmix_ad_banner().length() == 1) {
                Mix1AdsBanner(MyHelpers.getmix_ad_banner());  // 1 ads
            } else if (MyHelpers.getmix_ad_banner().length() == 2) {
                Mix2AdsBanner(MyHelpers.getmix_ad_banner());  // 2 ads
            } else{
                MixUnlimitedAdsBanner(MyHelpers.getmix_ad_banner()); // Unlimited
            }
        }
    }

    private static void Mix1AdsBanner(String s) {
        MixAdsShow(String.valueOf(s.charAt(0)));
    }

    private static void Mix2AdsBanner(String s) {
        if (MyHelpers.getmix_ad_counter_banner() != 5000) {
            mix_ads_banner++;
            if (MyHelpers.getmix_ad_counter_banner() + 1 == mix_ads_banner) {
                MixAdsShow(String.valueOf(s.charAt(1)));
                mix_ads_banner = 0;
            } else {
                MixAdsShow(String.valueOf(s.charAt(0)));
            }
        } else {
            if (mix_ads_banner == 0) {
                mix_ads_banner = 1;
                MixAdsShow(String.valueOf(s.charAt(0)));
            } else if (mix_ads_banner == 1) {
                mix_ads_banner = 0;
                MixAdsShow(String.valueOf(s.charAt(1)));
            }
        }
    }

    private static void MixUnlimitedAdsBanner(String s) {
        MixAdsShow(String.valueOf(s.charAt(mix_ads_banner)));
        if (MyHelpers.getmix_ad_banner().length() - 1 == mix_ads_banner) {
            mix_ads_banner = 0;
        } else {
            mix_ads_banner++;
        }
    }

    private static void MixAdsShow(String value) {
        if (value.equals("g") && MyHelpers.getlive_status().equals("1")) {
            RegularGoogleADSBannerShow("r");
        } else if (value.equals("f") && MyHelpers.getlive_status().equals("1")) {
            RegularFacebookBannerShow();
        } else if (value.equals("a")) {
            RegularAppLovingBannerShow();
        } else if (value.equals("u")) {
            RegularUnityBannerShow();
        } else if (value.equals("c")) {
            RegularCustomBannerAdsShow();
        } else {
            main_banner.removeAllViews();
        }
    }

    /**
     * PreLoad
     */
    /*Google*/
    public static void GoogleBannerPreload() {
        String google_load_id = null;
        if (AutoGoogleBannerID == 1) {
            google_load_id = MyHelpers.getGoogleBanner();
        } else if (AutoGoogleBannerID == 2) {
            google_load_id = MyHelpers.getGoogleBanner1();
        } else if (AutoGoogleBannerID == 3) {
            google_load_id = MyHelpers.getGoogleBanner2();
        }
        regular_google_banner_ad_loader = new AdLoader.Builder(main_context, google_load_id).forNativeAd(nativeAds -> {
            regular_google_native_banner = nativeAds;
        }).withAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError adError) {
                if (AutoGoogleBannerID == 1) {
                    AutoGoogleBannerID = 2;
                    GoogleBannerPreload();
                } else if (AutoGoogleBannerID == 2) {
                    AutoGoogleBannerID = 3;
                    GoogleBannerPreload();
                } else {
                    regular_google_native_banner = null;
                }
            }

            @Override
            public void onAdClicked() {
                super.onAdClicked();
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                AutoGoogleBannerID = 1;
            }

            @Override
            public void onAdImpression() {
                super.onAdImpression();
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }

        }).build();
        regular_google_banner_ad_loader.loadAd(new AdRequest.Builder().build());

    }

    public static void GoogleBannerPreload1() {
        regular_google_banner_ad_loader_1 = new AdLoader.Builder(main_context, MyHelpers.getGoogleBanner()).forNativeAd(nativeAds -> {
            regular_google_native_banner_1 = nativeAds;
        }).withAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError adError) {
                regular_google_native_banner_1 = null;

            }

            @Override
            public void onAdClicked() {
                super.onAdClicked();
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();

            }

            @Override
            public void onAdImpression() {
                super.onAdImpression();
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }

        }).build();
        regular_google_banner_ad_loader_1.loadAd(new AdRequest.Builder().build());
    }

    public static void GoogleBannerPreload2() {

        regular_google_banner_ad_loader_2 = new AdLoader.Builder(main_context, MyHelpers.getGoogleBanner1()).forNativeAd(nativeAds -> {
            regular_google_native_banner_2 = nativeAds;
        }).withAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError adError) {
                regular_google_native_banner_2 = null;

            }

            @Override
            public void onAdClicked() {
                super.onAdClicked();
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();

            }

            @Override
            public void onAdImpression() {
                super.onAdImpression();
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }

        }).build();
        regular_google_banner_ad_loader_2.loadAd(new AdRequest.Builder().build());

    }

    public static void GoogleBannerPreload3() {
        regular_google_banner_ad_loader_3 = new AdLoader.Builder(main_context, MyHelpers.getGoogleBanner2()).forNativeAd(nativeAds -> {
            regular_google_native_banner_3 = nativeAds;
        }).withAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError adError) {
                regular_google_native_banner_3 = null;

            }

            @Override
            public void onAdClicked() {
                super.onAdClicked();
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();

            }

            @Override
            public void onAdImpression() {
                super.onAdImpression();
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }

        }).build();
        regular_google_banner_ad_loader_3.loadAd(new AdRequest.Builder().build());
    }

    /*Facebook*/
    public static void FacebookBannerPreLoad() {

        String fb_load_id = null;
        if (AutoLoadFBBannerID == 1) {
            fb_load_id = MyHelpers.getFacebookBanner();
        } else if (AutoLoadFBBannerID == 2) {
            fb_load_id = MyHelpers.getFacebookBanner1();
        } else if (AutoLoadFBBannerID == 3) {
            fb_load_id = MyHelpers.getFacebookBanner2();
        }
        regular_facebook_banner_adView = new com.facebook.ads.AdView(main_context, fb_load_id, com.facebook.ads.AdSize.BANNER_HEIGHT_50);
        com.facebook.ads.AdListener adListener = new com.facebook.ads.AdListener() {
            @Override
            public void onError(Ad ad, com.facebook.ads.AdError adError) {
                if (AutoLoadFBBannerID == 1) {
                    AutoLoadFBBannerID = 2;
                    FacebookBannerPreLoad();
                } else if (AutoLoadFBBannerID == 2) {
                    AutoLoadFBBannerID = 3;
                    FacebookBannerPreLoad();
                } else {
                    regular_facebook_banner_adView = null;
                }


            }

            @Override
            public void onAdLoaded(Ad ad) {
                AutoLoadFBBannerID = 1;
            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }
        };
        com.facebook.ads.AdView.AdViewLoadConfig loadAdConfig = regular_facebook_banner_adView.buildLoadAdConfig().withAdListener(adListener).build();
        regular_facebook_banner_adView.loadAd(loadAdConfig);

    }

    /*AppLoving*/
    public static void AppLovingBannerPreLoad() {

        regular_applovin_banner_adView = new MaxAdView(MyHelpers.getAppLovinBanner(), main_context);
        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int dpHeightInPx = (int) (50 * main_context.getResources().getDisplayMetrics().density);
        regular_applovin_banner_adView.setLayoutParams(new FrameLayout.LayoutParams(width, dpHeightInPx));
        regular_applovin_banner_adView.setListener(new MaxAdViewAdListener() {
            @Override
            public void onAdExpanded(MaxAd ad) {

            }

            @Override
            public void onAdCollapsed(MaxAd ad) {

            }

            @Override
            public void onAdLoaded(MaxAd ad) {


            }

            @Override
            public void onAdDisplayed(MaxAd ad) {

            }

            @Override
            public void onAdHidden(MaxAd ad) {

            }

            @Override
            public void onAdClicked(MaxAd ad) {

            }

            @Override
            public void onAdLoadFailed(String adUnitId, MaxError error) {
                regular_applovin_banner_adView = null;

            }

            @Override
            public void onAdDisplayFailed(MaxAd ad, MaxError error) {

            }
        });
        regular_applovin_banner_adView.loadAd();
        regular_applovin_banner_adView.startAutoRefresh();

    }

    /*Unity*/
    public static void UnityBannerPreLoad() {

        regular_unity_banner_adView = new BannerView((Activity) main_context, MyHelpers.getUnityBannerID(), new UnityBannerSize(320, 50));
        regular_unity_banner_adView.setListener(new BannerView.IListener() {
            @Override
            public void onBannerLoaded(BannerView bannerAdView) {

            }

            @Override
            public void onBannerFailedToLoad(BannerView bannerAdView, BannerErrorInfo errorInfo) {
                regular_unity_banner_adView = null;
            }

            @Override
            public void onBannerClick(BannerView bannerAdView) {

            }

            @Override
            public void onBannerLeftApplication(BannerView bannerAdView) {

            }
        });
        regular_unity_banner_adView.load();

    }

    /*All Preload*/
    public static void AllAdsPreLoadsBanner(String refresh) {

        if (refresh.equals("g")) {
            regular_google_native_banner = null;
        } else if (refresh.equals("g1")) {
            regular_google_native_banner_1 = null;
        } else if (refresh.equals("g2")) {
            regular_google_native_banner_2 = null;
        } else if (refresh.equals("g3")) {
            regular_google_native_banner_3 = null;
        } else if (refresh.equals("f")) {
            regular_facebook_banner_adView = null;
        } else if (refresh.equals("a")) {
            regular_applovin_banner_adView = null;
        } else if (refresh.equals("u")) {
            regular_unity_banner_adView = null;
        }

        if (MyHelpers.Google_banner_number == 1) {

            if (MyHelpers.getGoogleBanner() != null && !MyHelpers.getGoogleBanner().isEmpty() && MyHelpers.getlive_status().equals("1")) {
                if (regular_google_native_banner == null) {
                    GoogleBannerPreload();
                }
            }

        } else if (MyHelpers.Google_banner_number == 2) {

            if (MyHelpers.getGoogleBanner() != null && !MyHelpers.getGoogleBanner().isEmpty() && MyHelpers.getlive_status().equals("1")) {
                if (regular_google_native_banner_1 == null) {
                    GoogleBannerPreload1();
                }
            }
            if (MyHelpers.getGoogleBanner1() != null && !MyHelpers.getGoogleBanner1().isEmpty() && MyHelpers.getlive_status().equals("1")) {
                if (regular_google_native_banner_2 == null) {
                    GoogleBannerPreload2();
                }
            }

        } else if (MyHelpers.Google_banner_number == 3) {

            if (MyHelpers.getGoogleBanner() != null && !MyHelpers.getGoogleBanner().isEmpty() && MyHelpers.getlive_status().equals("1")) {
                if (regular_google_native_banner_1 == null) {
                    GoogleBannerPreload1();
                }
            }
            if (MyHelpers.getGoogleBanner1() != null && !MyHelpers.getGoogleBanner1().isEmpty() && MyHelpers.getlive_status().equals("1")) {
                if (regular_google_native_banner_2 == null) {
                    GoogleBannerPreload2();
                }
            }

            if (MyHelpers.getGoogleBanner2() != null && !MyHelpers.getGoogleBanner2().isEmpty() && MyHelpers.getlive_status().equals("1")) {
                if (regular_google_native_banner_3 == null) {
                    GoogleBannerPreload3();
                }
            }
        }


        if (MyHelpers.getFacebookBanner() != null && !MyHelpers.getFacebookBanner().isEmpty() && MyHelpers.getlive_status().equals("1")) {
            if (regular_facebook_banner_adView == null) {
                FacebookBannerPreLoad();
            }
        }


        if (MyHelpers.getAppLovinBanner() != null && !MyHelpers.getAppLovinBanner().isEmpty() && MyHelpers.getlive_status().equals("1")) {
            if (regular_applovin_banner_adView == null) {
                AppLovingBannerPreLoad();
            }
        }

        if (MyHelpers.getUnityBannerID() != null && !MyHelpers.getUnityBannerID().isEmpty() && MyHelpers.getlive_status().equals("1")) {
            if (regular_unity_banner_adView == null) {
                UnityBannerPreLoad();
            }
        }
    }
}
