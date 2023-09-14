package com.appzone.mylibrarys;

import static com.appzone.mylibrarys.MyHelpers.getRandomNumber;
import static com.appzone.mylibrarys.MyHelpers.native_ads;
import static com.appzone.mylibrarys.MyHelpers.round_ads;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.facebook.ads.Ad;
import com.facebook.ads.AdOptionsView;
import com.facebook.ads.NativeAdLayout;
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
import java.util.Objects;

public class NativeClass {

    /*Google*/

    public static int google_native_count_number = 0;

    public static com.google.android.gms.ads.nativead.NativeAd main_on_demand_google_native_ads = null;
    public static com.google.android.gms.ads.nativead.NativeAd main_on_demand_google_native_ads_1 = null;
    public static com.google.android.gms.ads.nativead.NativeAd main_on_demand_google_native_ads_2 = null;
    public static com.google.android.gms.ads.nativead.NativeAd on_demand_google_native_ads = null;

    /*Facebook*/

    public static int fb_native_count_number = 0;
    public static com.facebook.ads.NativeAd facebook_native_ads = null;

    public static com.facebook.ads.NativeAd main_on_demand_facebook_native_ads = null;
    public static com.facebook.ads.NativeAd main_on_demand_facebook_native_ads_1 = null;
    public static com.facebook.ads.NativeAd main_on_demand_facebook_native_ads_2 = null;
    public static com.facebook.ads.NativeAd on_demand_facebook_native_ads = null;

    /*Helper*/
    @SuppressLint("StaticFieldLeak")
    public static RelativeLayout main_native;
    @SuppressLint("StaticFieldLeak")
    public static Activity main_context;

    /*Mix*/
    public static int auto_notShow_ads_native = 0;
    public static int mix_ads_native = 0;


    /**
     * NATIVE ADS CODE START
     */
    public static void NativeAds(Activity context1, RelativeLayout main_native1) {
        main_context = context1;
        main_native = main_native1;

        if (!MyHelpers.isOnline(context1)) {
            context1.startActivity(new Intent(context1, InternetErrorActivity.class));
            return;
        }

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

    }

    private static void RegularAds() {

        if (MyHelpers.getGoogleEnable().equals("1")) {

            if (google_native_count_number > 2) {
                google_native_count_number = 0;
            }

            if (google_native_count_number == 0) {
                GoogleADSShowONDemandNative();
            } else if (google_native_count_number == 1) {
                GoogleADSShowONDemandNative_1();
            } else if (google_native_count_number == 2) {
                GoogleADSShowONDemandNative_2();
            }

        } else if (MyHelpers.getFacebookEnable().equals("1")) {

            if (fb_native_count_number > 2) {
                fb_native_count_number = 0;
            }

            if (fb_native_count_number == 0) {
                FacebookADSShowONDemandNative();
            } else if (fb_native_count_number == 1) {
                FacebookADSShowONDemandNative_1();
            } else if (fb_native_count_number == 2) {
                FacebookADSShowONDemandNative_2();
            }

        } else if (MyHelpers.get_q_link_btn_on_off().equals("1")) {
            QurekaNative();
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
        if (value.equals("g")) {

            if (google_native_count_number > 2) {
                google_native_count_number = 0;
            }

            if (google_native_count_number == 0) {
                GoogleADSShowONDemandNative();
            } else if (google_native_count_number == 1) {
                GoogleADSShowONDemandNative_1();
            } else if (google_native_count_number == 2) {
                GoogleADSShowONDemandNative_2();
            }

        } else if (value.equals("f")) {

            if (fb_native_count_number > 2) {
                fb_native_count_number = 0;
            }

            if (fb_native_count_number == 0) {
                FacebookADSShowONDemandNative();
            } else if (fb_native_count_number == 1) {
                FacebookADSShowONDemandNative_1();
            } else if (fb_native_count_number == 2) {
                FacebookADSShowONDemandNative_2();
            }

        } else if (value.equals("q")) {
            QurekaNative();
        } else {
            main_native.removeAllViews();
        }
    }

    public static void QurekaNative() {

        NativeLoader();
        LayoutInflater inflater = (LayoutInflater) main_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        RelativeLayout load_view = (RelativeLayout) inflater.inflate(R.layout.qureka_big_native, main_native, false);

        Glide.with(main_context).load(round_ads.get(getRandomNumber(0, round_ads.size() - 1))).into((ImageView) load_view.findViewById(R.id.round));
        int getNumber = getRandomNumber(0, native_ads.size() - 1);
        Glide.with(main_context).load(native_ads.get(getNumber).getImage()).into((ImageView) load_view.findViewById(R.id.q_image));
        ((TextView) load_view.findViewById(R.id.txt_title)).setText(native_ads.get(getNumber).getTitle());
        ((TextView) load_view.findViewById(R.id.txt_dis)).setText(native_ads.get(getNumber).getDis());

        load_view.findViewById(R.id.qureka_big_native).setOnClickListener(v -> MyHelpers.BtnAutolink());
        load_view.findViewById(R.id.q_btn).setOnClickListener(v -> MyHelpers.BtnAutolink());
        main_native.removeAllViews();
        main_native.addView(load_view);
    }

    public static void NativeLoader() {

        @SuppressLint("InflateParams") View layout_ad_view = LayoutInflater.from(main_context).inflate(R.layout.ad_big_native_loading_lay, null);
        main_native.removeAllViews();
        main_native.addView(layout_ad_view);

    }

    private static void GoogleADSShowONDemandNative() {

        if (MyHelpers.getGoogleNative() != null && !MyHelpers.getGoogleNative().isEmpty()) {

            NativeLoader();
            AdLoader.Builder builder = new AdLoader.Builder(main_context, MyHelpers.getGoogleNative());
            builder.forNativeAd(nativeAd -> {
                main_on_demand_google_native_ads = nativeAd;
                google_native_count_number++;
                GoogleNativePopulateShow(main_on_demand_google_native_ads);
            });

            builder.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(false).build()).build());
            builder.withAdListener(new AdListener() {
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);
                    main_on_demand_google_native_ads = null;
                    google_native_count_number++;
                    GoogleAdsShowOnDemandFail_showFB();
                }

                public void onAdClicked() {
                    super.onAdClicked();
                }
            }).build().loadAd(new AdRequest.Builder().build());

        } else {
            NativeLoader();
            google_native_count_number++;
            GoogleAdsShowOnDemandFail_showFB();
        }
    }

    private static void GoogleADSShowONDemandNative_1() {

        if (MyHelpers.getGoogleNative1() != null && !MyHelpers.getGoogleNative1().isEmpty()) {

            NativeLoader();
            AdLoader.Builder builder = new AdLoader.Builder(main_context, MyHelpers.getGoogleNative1());
            builder.forNativeAd(nativeAd -> {
                main_on_demand_google_native_ads_1 = nativeAd;
                google_native_count_number++;
                GoogleNativePopulateShow(main_on_demand_google_native_ads_1);
            });

            builder.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(false).build()).build());
            builder.withAdListener(new AdListener() {
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);
                    main_on_demand_google_native_ads_1 = null;
                    google_native_count_number++;
                    GoogleAdsShowOnDemandFail_showFB();
                }

                public void onAdClicked() {
                    super.onAdClicked();
                }
            }).build().loadAd(new AdRequest.Builder().build());

        } else {
            NativeLoader();
            google_native_count_number++;
            GoogleAdsShowOnDemandFail_showFB();
        }
    }

    private static void GoogleADSShowONDemandNative_2() {

        if (MyHelpers.getGoogleNative2() != null && !MyHelpers.getGoogleNative2().isEmpty()) {

            NativeLoader();
            AdLoader.Builder builder = new AdLoader.Builder(main_context, MyHelpers.getGoogleNative2());
            builder.forNativeAd(nativeAd -> {
                main_on_demand_google_native_ads_2 = nativeAd;
                google_native_count_number++;
                GoogleNativePopulateShow(main_on_demand_google_native_ads_2);
            });

            builder.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(false).build()).build());
            builder.withAdListener(new AdListener() {
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);
                    main_on_demand_google_native_ads_2 = null;
                    google_native_count_number++;
                    GoogleAdsShowOnDemandFail_showFB();
                }

                public void onAdClicked() {
                    super.onAdClicked();
                }
            }).build().loadAd(new AdRequest.Builder().build());

        } else {
            NativeLoader();
            google_native_count_number++;
            GoogleAdsShowOnDemandFail_showFB();
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
                    QurekaNative();
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
        } else {
            QurekaNative();
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
                    fb_native_count_number++;
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
                    fb_native_count_number++;
                }
            };
            main_on_demand_facebook_native_ads.loadAd(main_on_demand_facebook_native_ads.buildLoadAdConfig().withAdListener(nativeAdListener).build());
        } else {
            NativeLoader();
            fb_native_count_number++;
            FacebookAdsShowOnDemandFail_showG();
        }

    }

    private static void FacebookADSShowONDemandNative_1() {

        if (MyHelpers.getFacebookNative1() != null && !MyHelpers.getFacebookNative1().isEmpty()) {

            NativeLoader();
            main_on_demand_facebook_native_ads_1 = new com.facebook.ads.NativeAd(main_context, MyHelpers.getFacebookNative1());
            NativeAdListener nativeAdListener = new NativeAdListener() {
                @Override
                public void onMediaDownloaded(Ad ad) {

                }

                @Override
                public void onError(Ad ad, com.facebook.ads.AdError adError) {
                    main_on_demand_facebook_native_ads_1 = null;
                    fb_native_count_number++;
                    FacebookAdsShowOnDemandFail_showG();
                }

                @Override
                public void onAdLoaded(Ad ad) {
                    if (main_on_demand_facebook_native_ads_1 != null && main_on_demand_facebook_native_ads_1.isAdLoaded()) {
                        FacebookNativePopulateShow(main_on_demand_facebook_native_ads_1);
                    }
                }

                @Override
                public void onAdClicked(Ad ad) {

                }

                @Override
                public void onLoggingImpression(Ad ad) {
                    fb_native_count_number++;
                }
            };
            main_on_demand_facebook_native_ads_1.loadAd(main_on_demand_facebook_native_ads_1.buildLoadAdConfig().withAdListener(nativeAdListener).build());
        } else {
            NativeLoader();
            fb_native_count_number++;
            FacebookAdsShowOnDemandFail_showG();
        }

    }

    private static void FacebookADSShowONDemandNative_2() {

        if (MyHelpers.getFacebookNative2() != null && !MyHelpers.getFacebookNative2().isEmpty()) {

            NativeLoader();
            main_on_demand_facebook_native_ads_2 = new com.facebook.ads.NativeAd(main_context, MyHelpers.getFacebookNative2());
            NativeAdListener nativeAdListener = new NativeAdListener() {
                @Override
                public void onMediaDownloaded(Ad ad) {

                }

                @Override
                public void onError(Ad ad, com.facebook.ads.AdError adError) {
                    main_on_demand_facebook_native_ads_2 = null;
                    fb_native_count_number++;
                    FacebookAdsShowOnDemandFail_showG();
                }

                @Override
                public void onAdLoaded(Ad ad) {
                    if (main_on_demand_facebook_native_ads_2 != null && main_on_demand_facebook_native_ads_2.isAdLoaded()) {
                        FacebookNativePopulateShow(main_on_demand_facebook_native_ads_2);
                    }
                }

                @Override
                public void onAdClicked(Ad ad) {

                }

                @Override
                public void onLoggingImpression(Ad ad) {
                    fb_native_count_number++;
                }
            };
            main_on_demand_facebook_native_ads_2.loadAd(main_on_demand_facebook_native_ads_2.buildLoadAdConfig().withAdListener(nativeAdListener).build());
        } else {
            NativeLoader();
            fb_native_count_number++;
            FacebookAdsShowOnDemandFail_showG();
        }

    }


    private static void FacebookAdsShowOnDemandFail_showG() {

        if (MyHelpers.getGoogleNative() != null && !MyHelpers.getGoogleNative().isEmpty()) {

            AdLoader.Builder builder = new AdLoader.Builder(main_context, MyHelpers.getGoogleNative());
            builder.forNativeAd(nativeAd -> {
                on_demand_google_native_ads = nativeAd;
                GoogleNativePopulateShow(on_demand_google_native_ads);
            });

            builder.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(false).build()).build());
            builder.withAdListener(new AdListener() {
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);
                    on_demand_google_native_ads = null;
                    QurekaNative();
                }

                public void onAdClicked() {
                    super.onAdClicked();
                }
            }).build().loadAd(new AdRequest.Builder().build());

        } else {
            QurekaNative();
        }
    }

    public static void GoogleNativePopulateShow(com.google.android.gms.ads.nativead.NativeAd main_google_native_ads) {

        @SuppressLint("InflateParams") com.google.android.gms.ads.nativead.NativeAdView nativeAdView = (com.google.android.gms.ads.nativead.NativeAdView) main_context.getLayoutInflater().inflate(R.layout.ad_google_big_native, null);
        nativeAdView.setMediaView(nativeAdView.findViewById(R.id.ad_media));
        ((com.google.android.gms.ads.nativead.MediaView) nativeAdView.findViewById(R.id.ad_media)).setImageScaleType(ImageView.ScaleType.CENTER_INSIDE);
        nativeAdView.setHeadlineView(nativeAdView.findViewById(R.id.ad_headline));
        nativeAdView.setBodyView(nativeAdView.findViewById(R.id.ad_body));
        nativeAdView.setCallToActionView(nativeAdView.findViewById(R.id.ad_call_to_action));
        nativeAdView.setIconView(nativeAdView.findViewById(R.id.ad_app_icon));
        Objects.requireNonNull(nativeAdView.getMediaView()).setMediaContent(main_google_native_ads.getMediaContent());
        nativeAdView.findViewById(R.id.ad_call_to_action).setBackground(ContextCompat.getDrawable(main_context, R.drawable.app_btn));
        try {
            ((TextView) Objects.requireNonNull(nativeAdView.getHeadlineView())).setText(main_google_native_ads.getHeadline());
            if (main_google_native_ads.getBody() == null) {
                Objects.requireNonNull(nativeAdView.getBodyView()).setVisibility(View.INVISIBLE);
            } else {
                Objects.requireNonNull(nativeAdView.getBodyView()).setVisibility(View.VISIBLE);
                ((TextView) nativeAdView.getBodyView()).setText(main_google_native_ads.getBody());
            }
            if (main_google_native_ads.getCallToAction() == null) {
                Objects.requireNonNull(nativeAdView.getCallToActionView()).setVisibility(View.INVISIBLE);
            } else {
                Objects.requireNonNull(nativeAdView.getCallToActionView()).setVisibility(View.VISIBLE);
                ((Button) nativeAdView.getCallToActionView()).setText(main_google_native_ads.getCallToAction());
                nativeAdView.getCallToActionView().setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(MyHelpers.getGooglebutton_color())));
            }
            if (main_google_native_ads.getIcon() == null) {
                Objects.requireNonNull(nativeAdView.getIconView()).setVisibility(View.GONE);
            } else {
                ((ImageView) Objects.requireNonNull(nativeAdView.getIconView())).setImageDrawable(main_google_native_ads.getIcon().getDrawable());
                nativeAdView.getIconView().setVisibility(View.VISIBLE);
            }
            nativeAdView.setNativeAd(main_google_native_ads);
            VideoController videoController = Objects.requireNonNull(main_google_native_ads.getMediaContent()).getVideoController();
            if (videoController.hasVideoContent()) {
                videoController.setVideoLifecycleCallbacks(new VideoController.VideoLifecycleCallbacks() {
                    public void onVideoEnd() {
                        super.onVideoEnd();
                    }
                });
            }
        } catch (Exception ignored) {
        }
        main_native.removeAllViews();
        main_native.addView(nativeAdView);
    }

    /*Facebook*/

    public static void FacebookNativePopulateShow(com.facebook.ads.NativeAd main_fb_native_ads) {

        LayoutInflater inflater = (LayoutInflater) main_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        NativeAdLayout adView = (NativeAdLayout) inflater.inflate(R.layout.ad_fb_native_layout, main_native, false);

        main_fb_native_ads.unregisterView();

        // Add the AdOptionsView
        LinearLayout adChoicesContainer = adView.findViewById(R.id.ad_choices_container);
        AdOptionsView adOptionsView = new AdOptionsView(main_context, main_fb_native_ads, adView);
        adChoicesContainer.removeAllViews();
        adChoicesContainer.addView(adOptionsView, 0);

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

}

