package com.clone.demo.shyp;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import org.json.JSONObject;

public class DefaultController extends Activity {

    RestClient client; LoadingDialog objLoadingDialog; JSONObject objJSON;
    NetworkMaster mNetworkMaster; PublicDelegate objPublicDelegate;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        objLoadingDialog = new LoadingDialog(this);
        objPublicDelegate = new PublicDelegate(this);
        mNetworkMaster = new NetworkMaster(this);

        int color = getResources().getColor(R.color.action_bar_color);
        ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable((color)));


    }// end onCreate

}// end main class