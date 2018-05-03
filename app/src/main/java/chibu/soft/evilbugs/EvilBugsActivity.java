package chibu.soft.evilbugs;


import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
//import android.view.View;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.content.pm.ActivityInfo;
import android.widget.RelativeLayout;
import chibu.soft.evilbugs.util.*;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;


public class EvilBugsActivity extends Activity  {
    /** Called when the activity is first created. */
	
	 public boolean mBackPressed = false;

	
	 public float volume = 0.8f;
	 
	 
	 //In-app purchase implementation things consumables
	
	 //game key from google console  //in - app purchase stuffs
	 static final String base64EncodedPublicKey = "in_app_purchase_key";
	 
	  IabHelper mHelper;
	  String istatus;

    //Advert implemetation
   // public static AdView mAdView;

   // Application MyApplication;
	 
	// (arbitrary) request code for the purchase flow
	//    static final int RC_REQUEST = 10001;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // requesting to turn the title OFF
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //requestWindowFeature(Window.);
        // making it full screen

        //This is used to force the screen to be in portrait
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
       getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        // set our MainGamePanel as the View  //we used relative layout because we want to
        //use ad and place it on top of the canvas
        //setContentView(new EvilBugsView(this, null)); //Same name with your view class

//        //advert implemetation
        //mAdView = new AdView(this);
        //mAdView.setAdUnitId("advert_key");
       // mAdView.setAdSize(AdSize.SMART_BANNER);

       // mAdView.setVisibility(View.VISIBLE);
       // AdRequest adRequest = new AdRequest.Builder().build();
       // mAdView.loadAd(adRequest);

//        AdRequest adRequest = new AdRequest.Builder()
//                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)        // All emulators
//                .addTestDevice("samsung-gt_i9500-4d00671aae0f306d")  // My Galaxy Nexus test phone
//                .build();
//        mAdView.loadAd(adRequest);

        // Create a RelativeLayout as the main layout and add the gameView.
        //we use this cause we want to use ads
        RelativeLayout mainLayout = new RelativeLayout(this);
        mainLayout.addView(new EvilBugsView(this));


        // Add adView to the bottom of the screen.
        RelativeLayout.LayoutParams adParams =
                new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
        adParams.addRule(RelativeLayout.ALIGN_TOP);
        adParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        //adParams.addRule(RelativeLayout.);


        //mainLayout.addView(mAdView, adParams);

        // Set the RelativeLayout as the main layout.
        setContentView(mainLayout);


        Settings.mysettings = this.getPreferences(0); //Here we save game parameters

        
        //volume control
        EvilBugsView.mvolume = volume;


        //in app stuffs
        // load game data
        //loadData(); here load game

        /* base64EncodedPublicKey should be YOUR APPLICATION'S PUBLIC KEY
         * (that you got from the Google Play developer console). This is not your
         * developer public key, it's the *app-specific* public key.
         *
         * Instead of just storing the entire literal string here embedded in the
         * program,  construct the key at runtime from pieces or
         * use bit manipulation (for example, XOR with some other string) to hide
         * the actual key.  The key itself is not secret information, but we don't
         * want to make it easy for an attacker to replace the public key with one
         * of their own and then fake messages from the server.
         */
        //String base64EncodedPublicKey = "CONSTRUCT_YOUR_KEY_AND_PLACE_IT_HERE";
        
        // Some sanity checks to see if the developer (that's you!) really followed the
        // instructions to run this sample (don't put these checks on your app!)
//        if (base64EncodedPublicKey.contains("CONSTRUCT_YOUR")) {
//            throw new RuntimeException("Please put your app's public key in MainActivity.java. See README.");
//        }
//        if (getPackageName().startsWith("com.example")) {
//            throw new RuntimeException("Please change the sample's package name! See README.");
//        }
        
     // Create the helper, passing it our context and the public key to verify signatures with
        mHelper = new IabHelper(this, base64EncodedPublicKey);
        
        // enable debug logging (for a production application, you should set this to false).
        mHelper.enableDebugLogging(true);
        
        EvilBugsView.mHelper = mHelper;
              
        EvilBugsView.activity = this;  //this is used for in app purchase

    }

//    @Override
//    public void onSaveInstanceState(Bundle savedInstanceState) {
//        super.onSaveInstanceState(savedInstanceState);
//        // Save UI state changes to the savedInstanceState.
//        // This bundle will be passed to onCreate if the process is
//        // killed and restarted.
//        //savedInstanceState.putBoolean("MyBoolean", true);
////        if(savedInstanceState != null && EvilBugsView.settings != null)
////        {
////            EvilBugsView.SaveGame();
////        }
//
//        // etc.
//    }


//    @Override
//    public void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        // Restore UI state from the savedInstanceState.
//        // This bundle has also been passed to onCreate.
//        //boolean myBoolean = savedInstanceState.getBoolean("MyBoolean");
////        if(savedInstanceState != null && EvilBugsView.settings != null)
////        {
////            EvilBugsView.SaveGame();
////        }
//
//    }


    @Override
    public void onPause()
    {
      //  Log.d("onPause", "Paused called");
        super.onPause();

        // Pause the AdView.
      // if(mAdView != null) mAdView.pause();

           //called when game is about to go to background
            try {
                if(EvilBugsView.settings != null) {
                    EvilBugsView.SaveGame();
                }
            }
            catch(Exception ex)
            {
                ex.getMessage();
               // System.exit(0);
            }

    }



    @Override
    public void onResume()
    {
        //called on game resume from background
        //Log.d("onResume", "Resume called");
        super.onResume();

        // Resume the AdView.
       // if(mAdView != null) mAdView.resume();
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);

        //this method onConfigurationChanged is to make sure activity doesnt restart and create screen on lock screen
        //please put code below in your andriod manifest
        //android:name="com.example.evilbugs.EvilBugsActivity"
        //android:configChanges="orientation|screenSize"
        //or depending on os
        //android:configChanges="keyboardHidden|orientation"
        //android:label="@string/app_name" >
    }

    
    @Override
    public void onDestroy() {
        super.onDestroy();

        // Destroy the AdView.
       // if(mAdView != null) mAdView.destroy();

        // very important:
        //Log.d(TAG, "Destroying helper.");
        if (mHelper != null) mHelper.dispose();
        mHelper = null;
    }
  
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
            	mBackPressed = true;
            	
            	EvilBugsView.backbuttonpressed = true;
            	
                break;  
                
            case KeyEvent.KEYCODE_VOLUME_DOWN:
            	//volume control
            	if(volume > 0.1) volume -= 0.1f;
                else if(volume <= 0.1)
                {
                   EvilBugsView.sound = false;
                  //  music = false;
                }
            	EvilBugsView.mvolume = volume;

               if(EvilBugsView.mp != null) EvilBugsView.mp.setVolume(volume - 0.2f,volume - 0.2f);
            	break;
            	
             case KeyEvent.KEYCODE_VOLUME_UP:
            	
            	//volume control
                 if(!EvilBugsView.sound) EvilBugsView.sound = true;
             	 else if(volume < 1) volume += 0.1f;
             	EvilBugsView.mvolume = volume;
                 if(EvilBugsView.mp != null) EvilBugsView.mp.setVolume(volume - 0.2f,volume - 0.2f);

            	break;
            }
            
        }
       return true;
       // return super.onKeyDown(keyCode, event);
    }

}
