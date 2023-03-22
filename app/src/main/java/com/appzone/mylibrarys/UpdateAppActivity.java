package com.appzone.mylibrarys;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


public class UpdateAppActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        setContentView(R.layout.activity_update_app);
    }

    public void Update(View view) {

        if (MyHelpers.Entery_UpdateApps == 1) {
            MyHelpers.LinkopenChromeCustomTabUrl(this, "https://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName());

        } else if (MyHelpers.Entery_UpdateApps == 2) {
            MyHelpers.LinkopenChromeCustomTabUrl(this, MyHelpers.getOtherAppsShowLink());
        }
    }

    @Override
    public void onBackPressed() {

    }
}