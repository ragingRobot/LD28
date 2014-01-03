package com.intelligentbeans.dare;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;



public class MainActivity extends AndroidApplication {
	private boolean adsEnabled = true;
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
        
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Create the libgdx View
        View gameView = initializeForView(new Dare(), true);
        // Hook it all up
        setContentView(layout);
        
        // Add the libgdx view
        layout.addView(gameView);
        
       if(adsEnabled){
		        // Create and setup the AdMob view
		        AdView adView = new AdView(this);
		        adView.setAdUnitId("ca-app-pub-8338177560598610/2755578680");
		        adView.setAdSize(AdSize.BANNER);
		
		        
		       
		        // Add the AdMob view
		        RelativeLayout.LayoutParams adParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		        adParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		        adParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		        layout.addView(adView, adParams);
		
		      
		    
		        //WHY DOESNT THIS WORK  :(
		        // Initiate a generic request.
		        AdRequest adRequest = new AdRequest.Builder().build();
		        // Load the adView with the ad request.
		        adView.loadAd(adRequest);
       }
      
        
        
    }
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
    public void onWindowFocusChanged(boolean hasFocus)
    {
               if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {  
                       if (hasFocus) {
                               //Log.d("SCREEN","IMMERSIVE MODE ACTIVE");
                               getWindow().getDecorView().setSystemUiVisibility(
                                              View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                              | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                              | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                              | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                              | View.SYSTEM_UI_FLAG_FULLSCREEN
                                              | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);}
               }
       
   
 
       
    }
    
}