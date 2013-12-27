package com.intelligentbeans.dare;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;


public class MainActivity extends AndroidApplication {
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	/*
        super.onCreate(savedInstanceState);
        
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.useGL20 = false;
        
        initialize(new Dare(), cfg);
        */
    	
    	
    	super.onCreate(savedInstanceState);

        // Create the layout
        RelativeLayout layout =  new RelativeLayout(this);

        // Do the stuff that initialize() would do for you
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

        // Create the libgdx View
        View gameView = initializeForView(new Dare(), false);
        // Hook it all up
        setContentView(layout);
        
        
        // Create and setup the AdMob view
        AdView adView = new AdView(this);
        adView.setAdUnitId("ca-app-pub-8338177560598610/2755578680");
        adView.setAdSize(AdSize.BANNER);

        // Add the libgdx view
        layout.addView(gameView);

        // Add the AdMob view
        RelativeLayout.LayoutParams adParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        adParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        adParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        layout.addView(adView, adParams);

      
    /*
        //WHY DOESNT THIS WORK  :(
        // Initiate a generic request.
        AdRequest adRequest = new AdRequest.Builder().build();
  
        // Load the adView with the ad request.
        adView.loadAd(adRequest);
        */
    }
}