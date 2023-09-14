package com.appzone.mylibrarys;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class QurekaInterActivity extends AppCompatActivity {

    private int counter;
    private TextView adTitle;
    private TextView adDis;
    private TextView txtSkip;
    private int getNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.qureka_inter);

        txtSkip = (TextView) findViewById(R.id.txt_skip);
        txtSkip.setText("Skip " + MyHelpers.getQurekaInterSkipTime());
        getNumber = MyHelpers.getRandomNumber(0, MyHelpers.inter_ads.size() - 1);
        Glide.with(QurekaInterActivity.this).load(MyHelpers.inter_ads.get(getNumber).getImage()).into((ImageView) findViewById(R.id.inter_image));
        ((ImageView) findViewById(R.id.inter_image)).setOnClickListener(v ->
                {
                    if (InterClass.qureka_intent == null) {
                        finish();
                    } else {
                        startActivity(InterClass.qureka_intent);
                        finish();
                    }
                    MyHelpers.BtnAutolink();
                }
        );
        initView();
    }

    public void close(View view) {
        if (InterClass.qureka_intent == null) {
            finish();
        } else {
            startActivity(InterClass.qureka_intent);
            finish();
        }
        if (MyHelpers.getQurekaCloseBTNAutoOpenLink().equals("1")) {
            MyHelpers.BtnAutolink();
        }
    }

    @Override
    public void onBackPressed() {

    }

    private void initView() {
        adTitle = (TextView) findViewById(R.id.ad_title);
        adDis = (TextView) findViewById(R.id.ad_dis);

        adTitle.setText(MyHelpers.inter_ads.get(getNumber).getTitle());
        adDis.setText(MyHelpers.inter_ads.get(getNumber).getDis());
        Glide.with(QurekaInterActivity.this).load(MyHelpers.round_ads.get(MyHelpers.getRandomNumber(0, MyHelpers.round_ads.size() - 1))).into((ImageView) findViewById(R.id.round));

        startCounter();
    }

    private void startCounter() {

        counter = Integer.valueOf(MyHelpers.getQurekaInterSkipTime());

        if (counter == 0) {
            findViewById(R.id.close).setVisibility(View.VISIBLE);
            findViewById(R.id.txt_skip).setVisibility(View.GONE);
            return;
        }

        new Handler().postDelayed(() -> {
            int A = counter - 1;
            txtSkip.setText("Skip " + A);
            counter = A;
            if (counter == 0) {
                findViewById(R.id.close).setVisibility(View.VISIBLE);
                findViewById(R.id.txt_skip).setVisibility(View.GONE);
            } else {
                startCounter();
            }
        }, 1000);
    }
}
