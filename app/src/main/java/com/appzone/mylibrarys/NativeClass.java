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

    public static com.google.android.gms.ads.nativead.NativeAd main_on_demand_google_native_ads = null;
    public static com.google.android.gms.ads.nativead.NativeAd on_demand_google_native_ads = null;

    /*Facebook*/
    public static com.facebook.ads.NativeAd facebook_native_ads = null;
    public static com.facebook.ads.NativeAd facebook_native_ads1 = null;
    public static com.facebook.ads.NativeAd facebook_native_ads2 = null;
    public static com.facebook.ads.NativeAd facebook_native_ads3 = null;

    public static com.facebook.ads.NativeAd main_on_demand_facebook_native_ads = null;
    public static com.facebook.ads.NativeAd on_demand_facebook_native_ads = null;

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
    public static int fb_auto_native_show_id = 0;

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

            //  Stop Ad
            if (MyHelpers.getCounter_Native() == 0) {
                return;
            }

            // Skip Ads
            if (MyHelpers.getCounter_Native() != 5000) {
                auto_notShow_ads_native++;
                if (MyHelpers.getCounter_Native() + 1 == auto_notShow_ads_native) {
                    auto_notShow_ads_native = 0;
                    if (MyHelpers.getmix_ad_on_off().equals("1")) {
                        NativeMixAds();
                    } else {
                        RegularAds();
                    }
                    return;
                }
                return;
            }

            //   Mix Ads
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

        if (MyHelpers.getExtraBtn_3().equals("1")) {
            if (MyHelpers.getGoogleEnable().equals("1") && MyHelpers.getlive_status().equals("1")) {
                GoogleADSShowONDemandNative();
            } else if (MyHelpers.getFacebookEnable().equals("1") && MyHelpers.getlive_status().equals("1")) {
                FacebookADSShowONDemandNative();
            }
        } else {
            if (MyHelpers.getGoogleEnable().equals("1") && MyHelpers.getlive_status().equals("1")) {
                GoogleADSNativeShow("r");
            } else if (MyHelpers.getFacebookEnable().equals("1") && MyHelpers.getlive_status().equals("1")) {
                FacebookADSNativeShow("f");
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
    }

    public static void NativeLoader() {

        View layout_ad_view = LayoutInflater.from(main_context).inflate(R.layout.ad_big_native_loading_lay, null);
        main_native.removeAllViews();
        main_native.addView(layout_ad_view);

    }

    private static void GoogleADSShowONDemandNative() {

        if (MyHelpers.getGoogleNative() != null && !MyHelpers.getGoogleNative().isEmpty()) {

            NativeLoader();
            AdLoader.Builder builder = new AdLoader.Builder(main_context, MyHelpers.getGoogleNative());
            builder.forNativeAd(new com.google.android.gms.ads.nativead.NativeAd.OnNativeAdLoadedListener() {
                public void onNativeAdLoaded(com.google.android.gms.ads.nativead.NativeAd nativeAd) {
                    main_on_demand_google_native_ads = nativeAd;
                    if (main_on_demand_google_native_ads != null) {
                        GoogleNativePopulateShow(main_on_demand_google_native_ads);
                    }
                }
            });

            builder.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(false).build()).build());
            builder.withAdListener(new AdListener() {
                public void onAdFailedToLoad(LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);
                    main_on_demand_google_native_ads = null;
                    GoogleAdsShowOnDemandFail_showFB();
                }

                public void onAdClicked() {
                    super.onAdClicked();
                }
            }).build().loadAd(new AdRequest.Builder().build());

        }

    }

    private static void GoogleAdsShowOnDemandFail_showFB() {

        if (MyHelpers.getFacebookNative() != null && !MyHelpers.getFacebookNative().isEmpty()) {

            on_demand_facebook_native_ads = new com.facebook.ads.NativeAd(main_context, MyHelpers.getFacebookNative());
            NativeAdListener nativeAdListener = new NativeAdListener() {
                @Override
                public void onMediaDownloaded(Ad ad) {

                }

                @Override
                public void onError(Ad ad, com.facebook.ads.AdError adError) {
                    on_demand_facebook_native_ads = null;
                    main_native.removeAllViews();
                }

                @Override
                public void onAdLoaded(Ad ad) {
                    if (on_demand_facebook_native_ads != null && on_demand_facebook_native_ads.isAdLoaded()) {
                        FacebookNativePopulateShow(on_demand_facebook_native_ads);
                    }
                }

                @Override
                public void onAdClicked(Ad ad) {

                }

                @Override
                public void onLoggingImpression(Ad ad) {

                }
            };
            on_demand_facebook_native_ads.loadAd(on_demand_facebook_native_ads.buildLoadAdConfig().withAdListener(nativeAdListener).build());
        }
    }

    private static void FacebookADSShowONDemandNative() {

        if (MyHelpers.getFacebookNative() != null && !MyHelpers.getFacebookNative().isEmpty()) {

            NativeLoader();
            main_on_demand_facebook_native_ads = new com.facebook.ads.NativeAd(main_context, MyHelpers.getFacebookNative());
            NativeAdListener nativeAdListener = new NativeAdListener() {
                @Override
                public void onMediaDownloaded(Ad ad) {

                }

                @Override
                public void onError(Ad ad, com.facebook.ads.AdError adError) {
                    main_on_demand_facebook_native_ads = null;
                    FacebookAdsShowOnDemandFail_showG();
                }

                @Override
                public void onAdLoaded(Ad ad) {
                    if (main_on_demand_facebook_native_ads != null && main_on_demand_facebook_native_ads.isAdLoaded()) {
                        FacebookNativePopulateShow(main_on_demand_facebook_native_ads);
                    }
                }

                @Override
                public void onAdClicked(Ad ad) {

                }

                @Override
                public void onLoggingImpression(Ad ad) {

                }
            };
            main_on_demand_facebook_native_ads.loadAd(main_on_demand_facebook_native_ads.buildLoadAdConfig().withAdListener(nativeAdListener).build());
        }

    }

    private static void FacebookAdsShowOnDemandFail_showG() {

        if (MyHelpers.getGoogleNative() != null && !MyHelpers.getGoogleNative().isEmpty()) {

            AdLoader.Builder builder = new AdLoader.Builder(main_context, MyHelpers.getGoogleNative());
            builder.forNativeAd(new com.google.android.gms.ads.nativead.NativeAd.OnNativeAdLoadedListener() {
                public void onNativeAdLoaded(com.google.android.gms.ads.nativead.NativeAd nativeAd) {
                    on_demand_google_native_ads = nativeAd;
                    if (on_demand_google_native_ads != null) {
                        GoogleNativePopulateShow(on_demand_google_native_ads);
                    }
                }
            });

            builder.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(false).build()).build());
            builder.withAdListener(new AdListener() {
                public void onAdFailedToLoad(LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);
                    on_demand_google_native_ads = null;
                    main_native.removeAllViews();
                }

                public void onAdClicked() {
                    super.onAdClicked();
                }
            }).build().loadAd(new AdRequest.Builder().build());

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
            FacebookADSNativeShow(checker);
        } else if (checker.equals("f")) {
            Google_Facebook_Fails_AppLoving_Show();
        } else if (checker.equals("a")) {
            FacebookADSNativeShow(checker);
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

    private static void FacebookADSNativeShow(String checker) {
        if (MyHelpers.fb_native_number == 1) {
            FacebookNativeShow(checker);
        } else if (MyHelpers.fb_native_number == 2) {
            if (fb_auto_native_show_id == 0) {
                fb_auto_native_show_id = 1;
                FacebookNativeShow1(checker);
            } else {
                fb_auto_native_show_id = 0;
                FacebookNativeShow2(checker);
            }
        } else if (MyHelpers.fb_native_number == 3) {
            if (fb_auto_native_show_id == 0) {
                fb_auto_native_show_id = 1;
                FacebookNativeShow1(checker);
            } else if (fb_auto_native_show_id == 1) {
                fb_auto_native_show_id = 2;
                FacebookNativeShow2(checker);
            } else {
                fb_auto_native_show_id = 0;
                FacebookNativeShow3(checker);
            }
        }
    }

    private static void FacebookNativeShow(String checker) {

        if (facebook_native_ads != null && facebook_native_ads.isAdLoaded()) {
            FacebookNativePopulateShow(facebook_native_ads);
        } else {
            AllFacebook_Fails_Other_Ads_Show(checker);
        }
        AllAdsPreLoadsNative("f");

    }

    private static void FacebookNativeShow1(String checker) {

        if (facebook_native_ads1 != null && facebook_native_ads1.isAdLoaded()) {
            FacebookNativePopulateShow(facebook_native_ads1);
        } else {
            AllFacebook_Fails_Other_Ads_Show(checker);
        }
        AllAdsPreLoadsNative("f1");

    }

    private static void FacebookNativeShow2(String checker) {

        if (facebook_native_ads2 != null && facebook_native_ads2.isAdLoaded()) {
            FacebookNativePopulateShow(facebook_native_ads2);
        } else {
            AllFacebook_Fails_Other_Ads_Show(checker);
        }
        AllAdsPreLoadsNative("f2");

    }

    private static void FacebookNativeShow3(String checker) {

        if (facebook_native_ads3 != null && facebook_native_ads3.isAdLoaded()) {
            FacebookNativePopulateShow(facebook_native_ads3);
        } else {
            AllFacebook_Fails_Other_Ads_Show(checker);
        }
        AllAdsPreLoadsNative("f3");

    }

    private static void AllFacebook_Fails_Other_Ads_Show(String checker) {
        if (checker.equals("r")) {
            Google_Facebook_Fails_AppLoving_Show();
        } else if (checker.equals("f")) {
            GoogleADSNativeShow("f");
        } else {
            CustomADSNative();
        }
    }

    public static void FacebookNativePopulateShow(com.facebook.ads.NativeAd main_fb_native_ads) {

        LayoutInflater inflater = (LayoutInflater) main_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        RelativeLayout adView = (RelativeLayout) inflater.inflate(R.layout.ad_fb_native_layout, main_native, false);

        main_fb_native_ads.unregisterView();

        // Create native UI using the normal metadata.
        com.facebook.ads.MediaView nativeAdIcon = adView.findViewById(R.id.native_ad_icon);
        TextView nativeAdTitle = adView.findViewById(R.id.native_ad_title);
        com.facebook.ads.MediaView nativeAdMedia = adView.findViewById(R.id.native_ad_media);
        TextView nativeAdSocialContext = adView.findViewById(R.id.native_ad_social_context);
        TextView nativeAdBody = adView.findViewById(R.id.native_ad_body);
        TextView sponsoredLabel = adView.findViewById(R.id.native_ad_sponsored_label);
        TextView nativeAdCallToAction = adView.findViewById(R.id.native_ad_call_to_action);

        // Set the Text.
        nativeAdTitle.setText(main_fb_native_ads.getAdvertiserName());
        nativeAdBody.setText(main_fb_native_ads.getAdBodyText());
        nativeAdSocialContext.setText(main_fb_native_ads.getAdSocialContext());
        nativeAdCallToAction.setVisibility(main_fb_native_ads.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
        nativeAdCallToAction.setText(main_fb_native_ads.getAdCallToAction());
        sponsoredLabel.setText(main_fb_native_ads.getSponsoredTranslation());

        // Create a list of clickable views
        List<View> clickableViews = new ArrayList<>();
        clickableViews.add(nativeAdTitle);
        clickableViews.add(nativeAdCallToAction);

        // Register the Title and CTA button to listen for clicks.
        main_fb_native_ads.registerViewForInteraction(adView, nativeAdMedia, nativeAdIcon, clickableViews);

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

    /*Custom Native*/
    private static void CustomADSNative() {

        if (SplashHelp.adsModals != null && !SplashHelp.adsModals.isEmpty()) {
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
        } else {
            main_native.removeAllViews();
        }
    }

    /**
     * Mix Ads Show
     */
    /*Mix Ads Helper*/
    private static void NativeMixAds() {
        if (MyHelpers.getmix_ad_native() != null && !MyHelpers.getmix_ad_native().isEmpty()) {
            if (MyHelpers.getmix_ad_native().length() == 1) {
                Mix1AdsNative(MyHelpers.getmix_ad_native());  // 1 ads
            } else if (MyHelpers.getmix_ad_native().length() == 2) {
                Mix2AdsNative(MyHelpers.getmix_ad_native());  // 2 ads
            } else {
                MixUnlimitedAdsNative(MyHelpers.getmix_ad_native()); // Unlimited
            }
        } else {
            main_native.removeAllViews();
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
            FacebookADSNativeShow("f");
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
        AdLoader.Builder builder = new AdLoader.Builder(main_context, MyHelpers.getGoogleNative());
        builder.forNativeAd(new com.google.android.gms.ads.nativead.NativeAd.OnNativeAdLoadedListener() {
            public void onNativeAdLoaded(com.google.android.gms.ads.nativead.NativeAd nativeAd) {
                google_native_ads = nativeAd;
            }
        });
        builder.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(false).build()).build());
        builder.withAdListener(new AdListener() {
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                google_native_ads = null;
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
        facebook_native_ads = new com.facebook.ads.NativeAd(main_context, MyHelpers.getFacebookNative());
        NativeAdListener nativeAdListener = new NativeAdListener() {
            @Override
            public void onMediaDownloaded(Ad ad) {

            }

            @Override
            public void onError(Ad ad, com.facebook.ads.AdError adError) {
                facebook_native_ads = null;
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
        facebook_native_ads.loadAd(facebook_native_ads.buildLoadAdConfig().withAdListener(nativeAdListener).build());
    }

    public static void FacebookNativePreLoad1() {
        facebook_native_ads1 = new com.facebook.ads.NativeAd(main_context, MyHelpers.getFacebookNative());
        NativeAdListener nativeAdListener = new NativeAdListener() {
            @Override
            public void onMediaDownloaded(Ad ad) {

            }

            @Override
            public void onError(Ad ad, com.facebook.ads.AdError adError) {
                facebook_native_ads1 = null;
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
        facebook_native_ads1.loadAd(facebook_native_ads1.buildLoadAdConfig().withAdListener(nativeAdListener).build());
    }

    public static void FacebookNativePreLoad2() {
        facebook_native_ads2 = new com.facebook.ads.NativeAd(main_context, MyHelpers.getFacebookNative1());
        NativeAdListener nativeAdListener = new NativeAdListener() {
            @Override
            public void onMediaDownloaded(Ad ad) {

            }

            @Override
            public void onError(Ad ad, com.facebook.ads.AdError adError) {
                facebook_native_ads2 = null;
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
        facebook_native_ads2.loadAd(facebook_native_ads2.buildLoadAdConfig().withAdListener(nativeAdListener).build());
    }

    public static void FacebookNativePreLoad3() {
        facebook_native_ads3 = new com.facebook.ads.NativeAd(main_context, MyHelpers.getFacebookNative2());
        NativeAdListener nativeAdListener = new NativeAdListener() {
            @Override
            public void onMediaDownloaded(Ad ad) {

            }

            @Override
            public void onError(Ad ad, com.facebook.ads.AdError adError) {
                facebook_native_ads3 = null;
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
        facebook_native_ads3.loadAd(facebook_native_ads3.buildLoadAdConfig().withAdListener(nativeAdListener).build());
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
        } else if (refresh.equals("f1")) {
            facebook_native_ads1 = null;
        } else if (refresh.equals("f2")) {
            facebook_native_ads2 = null;
        } else if (refresh.equals("f3")) {
            facebook_native_ads3 = null;
        } else if (refresh.equals("a")) {
            appLoving_native_ads = null;
        }

        //G
        if (MyHelpers.Google_native_number == 1) {

            if (MyHelpers.getGoogleNative() != null && !MyHelpers.getGoogleNative().isEmpty()) {
                if (google_native_ads == null) {
                    GoogleNativePreload();
                }
            }

        } else if (MyHelpers.Google_native_number == 2) {

            if (MyHelpers.getGoogleNative() != null && !MyHelpers.getGoogleNative().isEmpty()) {
                if (google_native_ads1 == null) {
                    GoogleNativePreload1();
                }

            }
            if (MyHelpers.getGoogleNative1() != null && !MyHelpers.getGoogleNative1().isEmpty()) {
                if (google_native_ads2 == null) {
                    GoogleNativePreload2();
                }

            }

        } else if (MyHelpers.Google_native_number == 3) {

            if (MyHelpers.getGoogleNative() != null && !MyHelpers.getGoogleNative().isEmpty()) {
                if (google_native_ads1 == null) {
                    GoogleNativePreload1();

                }
            }
            if (MyHelpers.getGoogleNative1() != null && !MyHelpers.getGoogleNative1().isEmpty()) {
                if (google_native_ads2 == null) {
                    GoogleNativePreload2();

                }
            }

            if (MyHelpers.getGoogleNative2() != null && !MyHelpers.getGoogleNative2().isEmpty()) {
                if (google_native_ads3 == null) {
                    GoogleNativePreload3();
                }
            }

        }

        //F
        if (MyHelpers.fb_native_number == 1) {

            if (MyHelpers.getFacebookNative() != null && !MyHelpers.getFacebookNative().isEmpty()) {
                if (facebook_native_ads == null) {
                    FacebookNativePreLoad();
                }
            }

        } else if (MyHelpers.fb_native_number == 2) {

            if (MyHelpers.getFacebookNative() != null && !MyHelpers.getFacebookNative().isEmpty()) {
                if (facebook_native_ads1 == null) {
                    FacebookNativePreLoad1();
                }
            }

            if (MyHelpers.getFacebookNative1() != null && !MyHelpers.getFacebookNative1().isEmpty()) {
                if (facebook_native_ads2 == null) {
                    FacebookNativePreLoad2();
                }
            }

        } else if (MyHelpers.fb_native_number == 3) {

            if (MyHelpers.getFacebookNative() != null && !MyHelpers.getFacebookNative().isEmpty()) {
                if (facebook_native_ads1 == null) {
                    FacebookNativePreLoad1();
                }
            }

            if (MyHelpers.getFacebookNative1() != null && !MyHelpers.getFacebookNative1().isEmpty()) {
                if (facebook_native_ads2 == null) {
                    FacebookNativePreLoad2();
                }
            }

            if (MyHelpers.getFacebookNative2() != null && !MyHelpers.getFacebookNative2().isEmpty()) {
                if (facebook_native_ads3 == null) {
                    FacebookNativePreLoad3();
                }
            }

        }

        //A
        if (MyHelpers.getAppLovinNative() != null && !MyHelpers.getAppLovinNative().isEmpty()) {
            if (appLoving_native_ads == null) {
                AppLovingNativePreLoad();
            }
        }
    }

}

