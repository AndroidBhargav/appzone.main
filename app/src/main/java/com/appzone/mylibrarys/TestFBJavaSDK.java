package com.appzone.mylibrarys;

import com.facebook.ads.sdk.APIContext;
import com.facebook.ads.sdk.APINodeList;
import com.facebook.ads.sdk.AdAccount;
import com.facebook.ads.sdk.Campaign;

public class TestFBJavaSDK {

    public static final APIContext context = new APIContext(
            MyHelpers.getACCESS_TOKEN(),
            MyHelpers.getAPP_SECRET()
    );

    public static void main(String[] args) {
        AdAccount account = new AdAccount(MyHelpers.getACCOUNT_ID(), context);
        try {
            APINodeList<Campaign> campaigns = account.getCampaigns().requestAllFields().execute();
            for (Campaign campaign : campaigns) {
                System.out.println(campaign.getFieldName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}