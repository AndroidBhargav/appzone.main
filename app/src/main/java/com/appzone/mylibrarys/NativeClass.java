package com.appzone.mylibrarys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.nativeAds.MaxNativeAdListener;
import com.applovin.mediation.nativeAds.MaxNativeAdLoader;
import com.applovin.mediation.nativeAds.MaxNativeAdView;
import com.bumptech.glide.Glide;
import com.facebook.ads.Ad;
import com.facebook.ads.NativeAdListener;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.nativead.NativeAdOptions;

import java.util.ArrayList;
import java.util.List;

public class NativeClass {

    /*Google*/
    public static com.google.android.gms.ads.nativead.NativeAd google_native_ads = null;
    public static com.google.android.gms.ads.nativead.NativeAd google_native_ads1 = null;
    public static com.google.android.gms.ads.nativead.NativeAd google_native_ads2 = null;
    public static com.google.android.gms.ads.nativead.NativeAd google_native_ads3 = null;
    public static int AutoGoogleNativeID;

    /*Facebook*/
    public static com.facebook.ads.NativeAd facebook_native_ads = null;
    public static int AutoLoadFBNativeID;

    /*AppLoving*/
    public static MaxNativeAdLoader appLoving_native_ads_loader;
    public static MaxNativeAdView max_nativeAdView;
    public static MaxAd appLoving_native_ads = null;

    /*Helper*/
    public static RelativeLayout main_native;
    public static Activity main_context;

    /*Mix*/
    public static int auto_notShow_ads_native = 0;
    public static int mix_ads_native = 0;
    public static int auto_native_show_id = 0;

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
     * NATIVE ADS CODE START
     */
    public static void NativeAds(Activity context1, RelativeLayout main_native1) {
        main_context = context1;
        main_native = main_native1;

        if (checkConnection(main_context)) {

            /*Stop Ad*/
            if (MyHelpers.getCounter_Native() == 0) {
                return;
            }

            /*Skip Ads*/
            if (MyHelpers.getCounter_Native() != 5000) {
                auto_notShow_ads_native++;
                if (MyHelpers.getCounter_Native() + 1 == auto_notShow_ads_native) {
                    auto_notShow_ads_native = 0;
                    return;
                }
            }

            /*Mix Ads*/
            if (MyHelpers.getmix_ad_on_off().equals("1")) {
                NativeMixAds();
            } else {
                RegularAds();
            }

        } else {
            CustomADSNative();
        }
    }

    private static void RegularAds() {
        if (MyHelpers.getGoogleEnable().equals("1") && MyHelpers.getlive_status().equals("1")) {
            GoogleADSNativeShow("r");
        } else if (MyHelpers.getFacebookEnable().equals("1") && MyHelpers.getlive_status().equals("1")) {
            FacebookNativeShow();
        } else if (MyHelpers.getAppLovinEnable().equals("1")) {
            AppLovingNativeShow();
        } else if (MyHelpers.getUnityEnable().equals("1")) {
            CustomADSNative();
        } else if (MyHelpers.getCustomEnable().equals("1")) {
            CustomADSNative();
        } else {
            main_native.removeAllViews();
        }
    }

    /**
     * Regular Ads Show
     */
    /*Google*/
    private static void GoogleADSNativeShow(String checker) {
        if (MyHelpers.Google_native_number == 1) {
            RegularGoogleNativeShow(checker);
        } else if (MyHelpers.Google_native_number == 2) {
            if (auto_native_show_id == 0) {
                auto_native_show_id = 1;
                RegularGoogleNativeShow1(checker);
            } else {
                auto_native_show_id = 0;
                RegularGoogleNativeShow2(checker);
            }
        } else if (MyHelpers.Google_native_number == 3) {
            if (auto_native_show_id == 0) {
                auto_native_show_id = 1;
                RegularGoogleNativeShow1(checker);
            } else if (auto_native_show_id == 1) {
                auto_native_show_id = 2;
                RegularGoogleNativeShow2(checker);
            } else {
                auto_native_show_id = 0;
                RegularGoogleNativeShow3(checker);
            }
        }
    }

    private static void RegularGoogleNativeShow(String checker) {
        if (google_native_ads != null) {
            GoogleNativePopulateShow(google_native_ads);
        } else {
            AllGoogle_Fails_Other_Ads_Show(checker);
        }
        AllAdsPreLoadsNative("g");
    }

    private static void RegularGoogleNativeShow1(String checker) {
        if (google_native_ads1 != null) {
            GoogleNativePopulateShow(google_native_ads1);
        } else {
            AllGoogle_Fails_Other_Ads_Show(checker);
        }
        AllAdsPreLoadsNative("g1");
    }

    private static void RegularGoogleNativeShow2(String checker) {
        if (google_native_ads2 != null) {
            GoogleNativePopulateShow(google_native_ads2);
        } else {
            AllGoogle_Fails_Other_Ads_Show(checker);
        }
        AllAdsPreLoadsNative("g2");
    }

    private static void RegularGoogleNativeShow3(String checker) {
        if (google_native_ads3 != null) {
            GoogleNativePopulateShow(google_native_ads3);
        } else {
            AllGoogle_Fails_Other_Ads_Show(checker);
        }
        AllAdsPreLoadsNative("g3");
    }

    private static void Google_Fails_Facebook_Show() {
        if (facebook_native_ads != null) {
            FacebookNativePopulateShow();
        } else {
            Google_Facebook_Fails_AppLoving_Show();
        }
        AllAdsPreLoadsNative("f");
    }

    private static void Google_Facebook_Fails_AppLoving_Show() {
        if (appLoving_native_ads != null) {
            main_native.removeAllViews();
            main_native.addView(max_nativeAdView);
        } else {
            CustomADSNative();
        }
        AllAdsPreLoadsNative("a");
    }

    private static void AllGoogle_Fails_Other_Ads_Show(String checker) {
        if (checker.equals("r")) {
            Google_Fails_Facebook_Show();
        } else if (checker.equals("f")) {
            Google_Facebook_Fails_AppLoving_Show();
        } else if (checker.equals("a")) {
            AppLoving_Google_Fails_facebook_Show();
        }
    }

    public static void GoogleNativePopulateShow(com.google.android.gms.ads.nativead.NativeAd main_google_native_ads) {
        com.google.android.gms.ads.nativead.NativeAdView nativeAdView = (com.google.android.gms.ads.nativead.NativeAdView) main_context.getLayoutInflater().inflate(R.layout.ad_google_big_native, (ViewGroup) null);
        nativeAdView.setMediaView((com.google.android.gms.ads.nativead.MediaView) nativeAdView.findViewById(R.id.ad_media));
        ((com.google.android.gms.ads.nativead.MediaView) nativeAdView.findViewById(R.id.ad_media)).setImageScaleType(ImageView.ScaleType.CENTER_INSIDE);
        nativeAdView.setHeadlineView(nativeAdView.findViewById(R.id.ad_headline));
        nativeAdView.setBodyView(nativeAdView.findViewById(R.id.ad_body));
        nativeAdView.setCallToActionView(nativeAdView.findViewById(R.id.ad_call_to_action));
        nativeAdView.setIconView(nativeAdView.findViewById(R.id.ad_app_icon));
        nativeAdView.getMediaView().setMediaContent(main_google_native_ads.getMediaContent());
        nativeAdView.findViewById(R.id.ad_call_to_action).setBackground(ContextCompat.getDrawable(main_context, R.drawable.app_btn));
        try {
            ((TextView) nativeAdView.getHeadlineView()).setText(main_google_native_ads.getHeadline());
            if (main_google_native_ads.getBody() == null) {
                nativeAdView.getBodyView().setVisibility(View.INVISIBLE);
            } else {
                nativeAdView.getBodyView().setVisibility(View.VISIBLE);
                ((TextView) nativeAdView.getBodyView()).setText(main_google_native_ads.getBody());
            }
            if (main_google_native_ads.getCallToAction() == null) {
                nativeAdView.getCallToActionView().setVisibility(View.INVISIBLE);
            } else {
                nativeAdView.getCallToActionView().setVisibility(View.VISIBLE);
                if (MyHelpers.getGooglebutton_name() != null && !MyHelpers.getGooglebutton_name().isEmpty()) {
                    ((Button) nativeAdView.getCallToActionView()).setText(MyHelpers.getGooglebutton_name());
                } else {
                    ((Button) nativeAdView.getCallToActionView()).setText(main_google_native_ads.getCallToAction());
                }
                ((Button) nativeAdView.getCallToActionView()).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(MyHelpers.getGooglebutton_color())));
            }
            if (main_google_native_ads.getIcon() == null) {
                nativeAdView.getIconView().setVisibility(View.GONE);
            } else {
                ((ImageView) nativeAdView.getIconView()).setImageDrawable(main_google_native_ads.getIcon().getDrawable());
                nativeAdView.getIconView().setVisibility(View.VISIBLE);
            }
            nativeAdView.setNativeAd(main_google_native_ads);
            VideoController videoController = main_google_native_ads.getMediaContent().getVideoController();
            if (videoController.hasVideoContent()) {
                videoController.setVideoLifecycleCallbacks(new VideoController.VideoLifecycleCallbacks() {
                    public void onVideoEnd() {
                        super.onVideoEnd();
                    }
                });
            }
        } catch (Exception unused) {
        }
        main_native.removeAllViews();
        main_native.addView(nativeAdView);
    }

    /*Facebook*/
    private static void FacebookNativeShow() {
        if (facebook_native_ads != null && facebook_native_ads.isAdLoaded()) {
            FacebookNativePopulateShow();
        } else {
            GoogleADSNativeShow("f");
        }
        AllAdsPreLoadsNative("f");
    }

    public static void FacebookNativePopulateShow() {

        LayoutInflater inflater = (LayoutInflater) main_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        RelativeLayout adView = (RelativeLayout) inflater.inflate(R.layout.ad_fb_native_layout, main_native, false);

        facebook_native_ads.unregisterView();

        // Create native UI using the normal metadata.
        com.facebook.ads.MediaView nativeAdIcon = adView.findViewById(R.id.native_ad_icon);
        TextView nativeAdTitle = adView.findViewById(R.id.native_ad_title);
        com.facebook.ads.MediaView nativeAdMedia = adView.findViewById(R.id.native_ad_media);
        TextView nativeAdSocialContext = adView.findViewById(R.id.native_ad_social_context);
        TextView nativeAdBody = adView.findViewById(R.id.native_ad_body);
        TextView sponsoredLabel = adView.findViewById(R.id.native_ad_sponsored_label);
        TextView nativeAdCallToAction = adView.findViewById(R.id.native_ad_call_to_action);

        // Set the Text.
        nativeAdTitle.setText(facebook_native_ads.getAdvertiserName());
        nativeAdBody.setText(facebook_native_ads.getAdBodyText());
        nativeAdSocialContext.setText(facebook_native_ads.getAdSocialContext());
        nativeAdCallToAction.setVisibility(facebook_native_ads.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
        nativeAdCallToAction.setText(facebook_native_ads.getAdCallToAction());
        sponsoredLabel.setText(facebook_native_ads.getSponsoredTranslation());

        // Create a list of clickable views
        List<View> clickableViews = new ArrayList<>();
        clickableViews.add(nativeAdTitle);
        clickableViews.add(nativeAdCallToAction);

        // Register the Title and CTA button to listen for clicks.
        facebook_native_ads.registerViewForInteraction(adView, nativeAdMedia, nativeAdIcon, clickableViews);

        main_native.removeAllViews();
        main_native.addView(adView);

    }

    /*AppLoving*/
    private static void AppLovingNativeShow() {
        if (appLoving_native_ads != null) {
            main_native.removeAllViews();
            main_native.addView(max_nativeAdView);
        } else {
            GoogleADSNativeShow("a");
        }
        AllAdsPreLoadsNative("a");
    }

    private static void AppLoving_Google_Fails_facebook_Show() {
        if (facebook_native_ads != null) {
            FacebookNativePopulateShow();
        } else {
            CustomADSNative();
        }
        AllAdsPreLoadsNative("f");
    }

    /*Custom Native*/
    private static void CustomADSNative() {
        if (SplashHelp.adsModals.size() == 0) {
            main_native.removeAllViews();
            return;
        }
        int ads_number = MyHelpers.getRandomNumber(0, SplashHelp.adsModals.size() - 1);
        RelativeLayout native_view = (RelativeLayout) main_context.getLayoutInflater().inflate(R.layout.custom_native, (ViewGroup) null);
        AppCompatButton btn_install = native_view.findViewById(R.id.btn_install);
        RelativeLayout full_click = native_view.findViewById(R.id.full_click);
        TextView app_name = native_view.findViewById(R.id.app_name);
        TextView app_shot = native_view.findViewById(R.id.app_shot);
        ImageView app_icon = native_view.findViewById(R.id.app_icon);
        ImageView ads_banner = native_view.findViewById(R.id.ads_banner);
        Glide.with(main_context).load(SplashHelp.adsModals.get(ads_number).getApp_logo()).into(app_icon);
        Glide.with(main_context).load(SplashHelp.adsModals.get(ads_number).getApp_banner()).into(ads_banner);
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
        main_native.removeAllViews();
        main_native.addView(native_view);
    }

    /**
     * Mix Ads Show
     */
    /*Mix Ads Helper*/
    private static void NativeMixAds() {
        if (MyHelpers.getmix_ad_native().length() == 0) {
            main_native.removeAllViews();
        } else {
            if (MyHelpers.getmix_ad_native().length() == 1) {
                Mix1AdsNative(MyHelpers.getmix_ad_native());  // 1 ads
            } else if (MyHelpers.getmix_ad_native().length() == 2) {
                Mix2AdsNative(MyHelpers.getmix_ad_native());  // 2 ads
            } else {
                MixUnlimitedAdsNative(MyHelpers.getmix_ad_native()); // Unlimited
            }
        }
    }

    private static void Mix1AdsNative(String s) {
        MixAdsShowNative(String.valueOf(s.charAt(0)));
    }

    private static void Mix2AdsNative(String s) {
        if (MyHelpers.getmix_ad_counter_native() != 5000) {
            mix_ads_native++;
            if (MyHelpers.getmix_ad_counter_native() + 1 == mix_ads_native) {
                MixAdsShowNative(String.valueOf(s.charAt(1)));
                mix_ads_native = 0;
            } else {
                MixAdsShowNative(String.valueOf(s.charAt(0)));
            }
        } else {
            if (mix_ads_native == 0) {
                mix_ads_native = 1;
                MixAdsShowNative(String.valueOf(s.charAt(0)));
            } else if (mix_ads_native == 1) {
                mix_ads_native = 0;
                MixAdsShowNative(String.valueOf(s.charAt(1)));
            }
        }
    }

    private static void MixUnlimitedAdsNative(String s) {
        MixAdsShowNative(String.valueOf(s.charAt(mix_ads_native)));
        if (MyHelpers.getmix_ad_native().length() - 1 == mix_ads_native) {
            mix_ads_native = 0;
        } else {
            mix_ads_native++;
        }
    }

    private static void MixAdsShowNative(String value) {
        if (value.equals("g") && MyHelpers.getlive_status().equals("1")) {
            GoogleADSNativeShow("r");
        } else if (value.equals("f") && MyHelpers.getlive_status().equals("1")) {
            FacebookNativeShow();
        } else if (value.equals("a")) {
            AppLovingNativeShow();
        } else if (value.equals("c")) {
            CustomADSNative();
        } else {
            main_native.removeAllViews();
        }
    }


    /**
     * PreLoad
     */
    /*Google*/
    public static void GoogleNativePreload() {
        String google_load_id = null;
        if (AutoGoogleNativeID == 1) {
            google_load_id = MyHelpers.getGoogleNative();
        } else if (AutoGoogleNativeID == 2) {
            google_load_id = MyHelpers.getGoogleNative1();
        } else if (AutoGoogleNativeID == 3) {
            google_load_id = MyHelpers.getGoogleNative2();
        }
        AdLoader.Builder builder = new AdLoader.Builder(main_context, google_load_id);
        builder.forNativeAd(new com.google.android.gms.ads.nativead.NativeAd.OnNativeAdLoadedListener() {
            public void onNativeAdLoaded(com.google.android.gms.ads.nativead.NativeAd nativeAd) {
                google_native_ads = nativeAd;
                AutoGoogleNativeID = 1;

            }
        });
        builder.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(false).build()).build());
        builder.withAdListener(new AdListener() {
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                if (AutoGoogleNativeID == 1) {
                    AutoGoogleNativeID = 2;
                    GoogleNativePreload();
                } else if (AutoGoogleNativeID == 2) {
                    AutoGoogleNativeID = 3;
                    GoogleNativePreload();
                } else {
                    google_native_ads = null;
                }
            }

            public void onAdClicked() {
                super.onAdClicked();
            }
        }).build().loadAd(new AdRequest.Builder().build());
    }

    public static void GoogleNativePreload1() {
        AdLoader.Builder builder = new AdLoader.Builder(main_context, MyHelpers.getGoogleNative());
        builder.forNativeAd(new com.google.android.gms.ads.nativead.NativeAd.OnNativeAdLoadedListener() {
            public void onNativeAdLoaded(com.google.android.gms.ads.nativead.NativeAd nativeAd) {
                google_native_ads1 = nativeAd;
            }
        });
        builder.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(false).build()).build());
        builder.withAdListener(new AdListener() {
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                google_native_ads1 = null;
            }

            public void onAdClicked() {
                super.onAdClicked();
            }
        }).build().loadAd(new AdRequest.Builder().build());

    }

    public static void GoogleNativePreload2() {
        AdLoader.Builder builder = new AdLoader.Builder(main_context, MyHelpers.getGoogleNative1());
        builder.forNativeAd(new com.google.android.gms.ads.nativead.NativeAd.OnNativeAdLoadedListener() {
            public void onNativeAdLoaded(com.google.android.gms.ads.nativead.NativeAd nativeAd) {
                google_native_ads2 = nativeAd;
            }
        });
        builder.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(false).build()).build());
        builder.withAdListener(new AdListener() {
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                google_native_ads2 = null;
            }

            public void onAdClicked() {
                super.onAdClicked();
            }
        }).build().loadAd(new AdRequest.Builder().build());

    }

    public static void GoogleNativePreload3() {
        AdLoader.Builder builder = new AdLoader.Builder(main_context, MyHelpers.getGoogleNative2());
        builder.forNativeAd(new com.google.android.gms.ads.nativead.NativeAd.OnNativeAdLoadedListener() {
            public void onNativeAdLoaded(com.google.android.gms.ads.nativead.NativeAd nativeAd) {
                google_native_ads3 = nativeAd;
            }
        });
        builder.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(false).build()).build());
        builder.withAdListener(new AdListener() {
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                google_native_ads3 = null;
            }

            public void onAdClicked() {
                super.onAdClicked();
            }
        }).build().loadAd(new AdRequest.Builder().build());

    }

    /*Facebook*/
    public static void FacebookNativePreLoad() {
        String facebook_load_id = null;
        if (AutoLoadFBNativeID == 1) {
            facebook_load_id = MyHelpers.getFacebookNative();
        } else if (AutoLoadFBNativeID == 2) {
            facebook_load_id = MyHelpers.getFacebookNative1();
        } else if (AutoLoadFBNativeID == 3) {
            facebook_load_id = MyHelpers.getFacebookNative2();
        }
        facebook_native_ads = new com.facebook.ads.NativeAd(main_context, facebook_load_id);
        NativeAdListener nativeAdListener = new NativeAdListener() {
            @Override
            public void onMediaDownloaded(Ad ad) {

            }

            @Override
            public void onError(Ad ad, com.facebook.ads.AdError adError) {
                if (AutoLoadFBNativeID == 1) {
                    AutoLoadFBNativeID = 2;
                    FacebookNativePreLoad();
                } else if (AutoLoadFBNativeID == 2) {
                    AutoLoadFBNativeID = 3;
                    FacebookNativePreLoad();
                } else {
                    facebook_native_ads = null;
                }
            }

            @Override
            public void onAdLoaded(Ad ad) {
                AutoLoadFBNativeID = 1;

            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }
        };
        facebook_native_ads.loadAd(facebook_native_ads.buildLoadAdConfig().withAdListener(nativeAdListener).build());
    }

    /*AppLoving*/
    public static void AppLovingNativePreLoad() {
        appLoving_native_ads_loader = new MaxNativeAdLoader(MyHelpers.getAppLovinNative(), main_context);
        appLoving_native_ads_loader.setNativeAdListener(new MaxNativeAdListener() {
            @Override
            public void onNativeAdLoaded(final MaxNativeAdView nativeAdView, final MaxAd ad) {
                appLoving_native_ads = ad;
                max_nativeAdView = nativeAdView;
                int width = ViewGroup.LayoutParams.MATCH_PARENT;
                int dpHeightInPx = (int) (300 * main_context.getResources().getDisplayMetrics().density);
                max_nativeAdView.setLayoutParams(new FrameLayout.LayoutParams(width, dpHeightInPx));

            }

            @Override
            public void onNativeAdLoadFailed(final String adUnitId, final MaxError error) {
                appLoving_native_ads = null;
            }

            @Override
            public void onNativeAdClicked(final MaxAd ad) {

            }
        });
        appLoving_native_ads_loader.loadAd();
    }

    /*All Preload*/
    public static void AllAdsPreLoadsNative(String refresh) {
        if (refresh.equals("g")) {
            google_native_ads = null;
        } else if (refresh.equals("g1")) {
            google_native_ads1 = null;
        } else if (refresh.equals("g2")) {
            google_native_ads2 = null;
        } else if (refresh.equals("g3")) {
            google_native_ads3 = null;
        } else if (refresh.equals("f")) {
            facebook_native_ads = null;
        } else if (refresh.equals("a")) {
            appLoving_native_ads = null;
        }

        if (MyHelpers.Google_native_number == 1) {

            if (MyHelpers.getGoogleNative() != null && !MyHelpers.getGoogleNative().isEmpty() && MyHelpers.getlive_status().equals("1")) {
                if (google_native_ads == null) {
                    GoogleNativePreload();
                }
            }

        } else if (MyHelpers.Google_native_number == 2) {

            if (MyHelpers.getGoogleNative() != null && !MyHelpers.getGoogleNative().isEmpty() && MyHelpers.getlive_status().equals("1")) {
                if (google_native_ads1 == null) {
                    GoogleNativePreload1();
                }

            }
            if (MyHelpers.getGoogleNative1() != null && !MyHelpers.getGoogleNative1().isEmpty() && MyHelpers.getlive_status().equals("1")) {
                if (google_native_ads2 == null) {
                    GoogleNativePreload2();
                }

            }

        } else if (MyHelpers.Google_native_number == 3) {

            if (MyHelpers.getGoogleNative() != null && !MyHelpers.getGoogleNative().isEmpty() && MyHelpers.getlive_status().equals("1")) {
                if (google_native_ads1 == null) {
                    GoogleNativePreload1();

                }
            }
            if (MyHelpers.getGoogleNative1() != null && !MyHelpers.getGoogleNative1().isEmpty() && MyHelpers.getlive_status().equals("1")) {
                if (google_native_ads2 == null) {
                    GoogleNativePreload2();

                }
            }

            if (MyHelpers.getGoogleNative2() != null && !MyHelpers.getGoogleNative2().isEmpty() && MyHelpers.getlive_status().equals("1")) {
                if (google_native_ads3 == null) {
                    GoogleNativePreload3();
                }
            }

        }


        if (MyHelpers.getFacebookNative() != null && !MyHelpers.getFacebookNative().isEmpty() && MyHelpers.getlive_status().equals("1")) {
            if (facebook_native_ads == null) {
                FacebookNativePreLoad();
            }
        }

        if (MyHelpers.getAppLovinNative() != null && !MyHelpers.getAppLovinNative().isEmpty() && MyHelpers.getlive_status().equals("1")) {
            if (appLoving_native_ads == null) {
                AppLovingNativePreLoad();
            }
        }
    }

}

