package com.appzone.mylibrarys;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.bumptech.glide.Glide;

public class CustomAdsInterActivity extends AppCompatActivity {

    private ImageView close;
    private AppCompatButton btnInstall;
    private AppCompatButton btnCancel;
    private RelativeLayout mainView;

    String ads_number_string = "";
    int ads_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        setContentView(R.layout.custom_inter);

        initView();

        btnInstall.setOnClickListener(v -> InstallApps());
        close.setOnClickListener(v -> finish());
        btnCancel.setOnClickListener(v -> finish());
        mainView.setOnClickListener(v -> InstallApps());
    }

    private void initView() {
        ImageView appIcon = findViewById(R.id.app_icon);
        ImageView adBanner = findViewById(R.id.ad_banner);
        TextView appName = findViewById(R.id.app_name);
        TextView appShot = findViewById(R.id.app_shot);
        close = findViewById(R.id.close);
        btnInstall = findViewById(R.id.btn_install);
        btnCancel = findViewById(R.id.btn_cancel);
        mainView = findViewById(R.id.main_view);

        if (SplashHelp.adsModals != null && !SplashHelp.adsModals.isEmpty()) {
            ads_number_string = String.valueOf(MyHelpers.getRandomNumber(0, SplashHelp.adsModals.size() - 1));

            if (!ads_number_string.isEmpty()) {
                ads_number = Integer.parseInt(ads_number_string);
            }

            Glide.with(this)
                    .load(SplashHelp.adsModals.get(ads_number).getApp_logo())
                    .into(appIcon);
            Glide.with(this)
                    .load(SplashHelp.adsModals.get(ads_number).getApp_banner())
                    .into(adBanner);
            appName.setText(SplashHelp.adsModals.get(ads_number).getAd_app_name());
            appShot.setText(SplashHelp.adsModals.get(ads_number).getApp_description());

        }
    }

    private void InstallApps() {

        if (SplashHelp.adsModals != null && !SplashHelp.adsModals.isEmpty()) {
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + SplashHelp.adsModals.get(ads_number).getApp_name())));
            } catch (ActivityNotFoundException anfe) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + SplashHelp.adsModals.get(ads_number).getApp_name())));
            }
        }
    }
}