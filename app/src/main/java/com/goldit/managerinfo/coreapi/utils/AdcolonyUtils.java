package com.goldit.managerinfo.coreapi.utils;//
//import android.app.Activity;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ProgressBar;
//
//import com.adcolony.sdk.AdColony;
//import com.adcolony.sdk.AdColonyAdOptions;
//import com.adcolony.sdk.AdColonyAppOptions;
//import com.adcolony.sdk.AdColonyInterstitial;
//import com.adcolony.sdk.AdColonyInterstitialListener;
//import com.adcolony.sdk.AdColonyUserMetadata;
//import com.adcolony.sdk.AdColonyZone;
//
//public class InterstitialActivity extends Activity
//{
//    //    final private String APP_ID = "app185a7e71e1714831a49ec7";
//    final private String APP_ID = "appa0c1f4367c634d18ba";
//    final private String ZONE_ID = "vz4c0535a8ac3f4c7aad";
//    final private String TAG = "AdColonyDemo";
//
//    private Button show_button;
//    private ProgressBar progress;
//    private AdColonyInterstitial ad;
//    private AdColonyInterstitialListener listener;
//    private AdColonyAdOptions ad_options;
//
//    @Override
//    protected void onCreate( Bundle bundle )
//    {
//        super.onCreate( bundle );
//        setContentView( R.layout.activity_interstitial );
//        progress = (ProgressBar) findViewById( R.id.progress );
//
//        /** Construct optional app options object to be sent with configure */
//        AdColonyAppOptions app_options = new AdColonyAppOptions()
//                .setUserID( "unique_user_id" );
//
//        /**
//         * Configure AdColony in your launching Activity's onCreate() method so that cached ads can
//         * be available as soon as possible.
//         */
//        AdColony.configure( this, app_options, APP_ID, ZONE_ID );
//
//        /** Optional user metadata sent with the ad options in each request */
//        AdColonyUserMetadata metadata = new AdColonyUserMetadata()
//                .setUserAge( 26 )
//                .setUserEducation( AdColonyUserMetadata.USER_EDUCATION_BACHELORS_DEGREE )
//                .setUserGender( AdColonyUserMetadata.USER_MALE );
//
//        /** Ad specific options to be sent with request */
//        ad_options = new AdColonyAdOptions()
//                .setUserMetadata( metadata );
//
//
//        /**
//         * Set up listener for interstitial ad callbacks. You only need to implement the callbacks
//         * that you care about. The only required callback is onRequestFilled, as this is the only
//         * way to get an ad object.
//         */
//        listener = new AdColonyInterstitialListener()
//        {
//            /** Ad passed back in request filled callback, ad can now be shown */
//            @Override
//            public void onRequestFilled( AdColonyInterstitial ad )
//            {
//                InterstitialActivity.this.ad = ad;
//                show_button.setEnabled( true );
//                progress.setVisibility( View.INVISIBLE );
//                Log.d( TAG, "onRequestFilled" );
//            }
//
//            /** Ad request was not filled */
//            @Override
//            public void onRequestNotFilled( AdColonyZone zone )
//            {
//                progress.setVisibility( View.INVISIBLE );
//                Log.d( TAG, "onRequestNotFilled");
//            }
//
//            /** Ad opened, reset UI to reflect state change */
//            @Override
//            public void onOpened( AdColonyInterstitial ad )
//            {
//                show_button.setEnabled( false );
//                progress.setVisibility( View.VISIBLE );
//                Log.d( TAG, "onOpened" );
//            }
//
//            /** Request a new ad if ad is expiring */
//            @Override
//            public void onExpiring( AdColonyInterstitial ad )
//            {
//                show_button.setEnabled( false );
//                progress.setVisibility( View.VISIBLE );
//                AdColony.requestInterstitial( ZONE_ID, this, ad_options );
//                Log.d( TAG, "onExpiring" );
//            }
//        };
//
//        /** Set up button to show an ad when clicked */
//        show_button = (Button) findViewById( R.id.showbutton );
//        show_button.setOnClickListener( new View.OnClickListener()
//        {
//            @Override
//            public void onClick( View view )
//            {
//                ad.show();
//            }
//        } );
//    }
//
//    @Override
//    protected void onResume()
//    {
//        super.onResume();
//
//        /**
//         * It's somewhat arbitrary when your ad request should be made. Here we are simply making
//         * a request if there is no valid ad available onResume, but really this can be done at any
//         * reasonable time before you plan on showing an ad.
//         */
//        if (ad == null || ad.isExpired())
//        {
//            /**
//             * Optionally update location info in the ad options for each request:
//             * LocationManager location_manager = (LocationManager) getSystemService( Context.LOCATION_SERVICE );
//             * Location location = location_manager.getLastKnownLocation( LocationManager.GPS_PROVIDER );
//             * ad_options.setUserMetadata( ad_options.getUserMetadata().setUserLocation( location ) );
//             */
//            progress.setVisibility( View.VISIBLE );
//            AdColony.requestInterstitial( ZONE_ID, listener, ad_options );
//        }
//
//    }
//}
//<!-- Force 32bpp -->
//<VideoView
//        android:layout_width="0dp"
//                android:layout_height="0dp"
//                android:id="@+id/videoView" />



//-------------------------------------------------------------------------------


//package com.adcolony.instantfeeddemo;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.util.Log;
//import android.widget.LinearLayout;
//import android.widget.Toast;
//
//import com.adcolony.sdk.*;
//
//public class InstantFeedActivity extends Activity
//{
//    private AdColonyNativeAdView ad_view;
//    private LinearLayout ad_layout;
//    private AdColonyNativeAdViewListener ad_listener;
//    private AdColonyAdOptions ad_options;
//
//    private final String APP_ID = "app185a7e71e1714831a49ec7";
//    private final String ZONE_ID = "vze4675ec2638048a789";
//    private final String TAG = "InstantFeed";
//
//    @Override
//    protected void onCreate( Bundle savedInstanceState )
//    {
//        super.onCreate( savedInstanceState );
//        setContentView( R.layout.layout );
//
//        ad_layout = (LinearLayout) findViewById( R.id.instant_feed_layout );
//
//        /** Construct optional app options object to be sent with configure */
//        AdColonyAppOptions app_options = new AdColonyAppOptions()
//                .setUserID( "unique_user_id" );
//
//        AdColony.configure( this, app_options, APP_ID, ZONE_ID );
//
//        /** Instantiate ad specific listener for native ad view callbacks */
//        ad_listener = new AdColonyNativeAdViewListener()
//        {
//            @Override
//            public void onRequestFilled( AdColonyNativeAdView view )
//            {
//                ad_view = view;
//                ad_layout.addView( ad_view );
//                Toast.makeText( InstantFeedActivity.this,
//                        "Instant-Feed ad added to layout. Scroll through your feed to find it.",
//                        Toast.LENGTH_LONG).show();
//
//                /** Add/style engagement button to your layout as appropriate */
//                if (ad_view.isEngagementEnabled())
//                {
//                    /**
//                     *  If you already have a button you want to use, you should use the engagement button's
//                     *  getOnClickListener method to use as your button's click listener
//                     */
//
//                    AdColonyNativeAdView.EngagementButton engagement_button = ad_view.getEngagementButton();
//                    ad_layout.addView( engagement_button );
//                }
//            }
//
//            @Override
//            public void onMuted( AdColonyNativeAdView view )
//            {
//                Log.d( TAG, "onMuted" );
//            }
//
//            @Override
//            public void onUnmuted( AdColonyNativeAdView view )
//            {
//                Log.d( TAG, "onUnmuted" );
//            }
//
//            @Override
//            public void onRequestNotFilled( AdColonyZone zone )
//            {
//                Log.d( TAG, "onRequestNotFilled" );
//            }
//
//            @Override
//            public void onNativeVideoFinished( AdColonyNativeAdView view )
//            {
//                Log.d( TAG, "onNativeVideoFinished" );
//            }
//        };
//
//        /** Ad specific options to be sent with the request */
//        AdColonyUserMetadata metadata = new AdColonyUserMetadata()
//                .setUserAge( 26 )
//                .setUserEducation( AdColonyUserMetadata.USER_EDUCATION_BACHELORS_DEGREE )
//                .setUserGender( AdColonyUserMetadata.USER_MALE );
//        ad_options = new AdColonyAdOptions().setUserMetadata( metadata );
//
//        /**
//         * It's somewhat arbitrary when your ad request should be made. Here we are simply making
//         * a request onCreate, but really this can be done at any reasonable time before you plan on
//         * displaying the ad view.
//         *
//         * Optionally update location info in the ad options for each request:
//         * LocationManager location_manager = (LocationManager) getSystemService( Context.LOCATION_SERVICE );
//         * Location location = location_manager.getLastKnownLocation( LocationManager.GPS_PROVIDER );
//         * ad_options.setUserMetadata( ad_options.getUserMetadata().setUserLocation( location ) );
//         */
//        AdColony.requestNativeAdView( ZONE_ID, ad_listener, AdColonyAdSize.MEDIUM_RECTANGLE, ad_options );
//    }
//
//    @Override
//    protected void onDestroy()
//    {
//        super.onDestroy();
//        if (ad_view != null)
//        {
//            ad_view.destroy();
//            ad_layout.removeView( ad_view );
//            ad_view = null;
//        }
//    }
//}
//<LinearLayout
//                android:layout_width="match_parent"
//                        android:layout_height="wrap_content"
//                        android:orientation="vertical"
//                        android:layout_marginBottom="16dp"
//                        android:id="@+id/instant_feed_layout">



//----------------------------------------------------------------


//package com.adcolony.rewardedinterstitialdemo;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ProgressBar;
//
//import com.adcolony.sdk.*;
//
//public class RewardedInterstitialActivity extends Activity
//{
//    final private String APP_ID = "app185a7e71e1714831a49ec7";
//    final private String ZONE_ID = "vz1fd5a8b2bf6841a0a4b826";
//    final private String TAG = "AdColonyDemo";
//
//    private Button show_button;
//    private ProgressBar progress;
//    private AdColonyInterstitial ad;
//    private AdColonyInterstitialListener listener;
//    private AdColonyAdOptions ad_options;
//
//    @Override
//    protected void onCreate( Bundle bundle )
//    {
//        super.onCreate( bundle );
//        setContentView( R.layout.activity_rewarded_interstitial );
//        progress = (ProgressBar) findViewById( R.id.progress );
//
//        /** Construct optional app options object to be sent with configure */
//        AdColonyAppOptions app_options = new AdColonyAppOptions()
//                .setUserID( "unique_user_id" );
//
//        /**
//         * Configure AdColony in your launching Activity's onCreate() method so that cached ads can
//         * be available as soon as possible.
//         */
//        AdColony.configure( this, app_options, APP_ID, ZONE_ID );
//
//        /** Optional user metadata sent with the ad options in each request */
//        AdColonyUserMetadata metadata = new AdColonyUserMetadata()
//                .setUserAge( 26 )
//                .setUserEducation( AdColonyUserMetadata.USER_EDUCATION_BACHELORS_DEGREE )
//                .setUserGender( AdColonyUserMetadata.USER_MALE );
//
//        /** Ad specific options to be sent with request */
//        ad_options = new AdColonyAdOptions()
//                .enableConfirmationDialog( true )
//                .enableResultsDialog( true )
//                .setUserMetadata( metadata );
//
//        /** Create and set a reward listener */
//        AdColony.setRewardListener( new AdColonyRewardListener()
//        {
//            @Override
//            public void onReward( AdColonyReward reward )
//            {
//                /** Query reward object for info here */
//                Log.d( TAG, "onReward" );
//            }
//        } );
//
//        /**
//         * Set up listener for interstitial ad callbacks. You only need to implement the callbacks
//         * that you care about. The only required callback is onRequestFilled, as this is the only
//         * way to get an ad object.
//         */
//        listener = new AdColonyInterstitialListener()
//        {
//            /** Ad passed back in request filled callback, ad can now be shown */
//            @Override
//            public void onRequestFilled( AdColonyInterstitial ad )
//            {
//                RewardedInterstitialActivity.this.ad = ad;
//                show_button.setEnabled( true );
//                progress.setVisibility( View.INVISIBLE );
//                Log.d( TAG, "onRequestFilled" );
//            }
//
//            /** Ad request was not filled */
//            @Override
//            public void onRequestNotFilled( AdColonyZone zone )
//            {
//                progress.setVisibility( View.INVISIBLE );
//                Log.d( TAG, "onRequestNotFilled");
//            }
//
//            /** Ad opened, reset UI to reflect state change */
//            @Override
//            public void onOpened( AdColonyInterstitial ad )
//            {
//                show_button.setEnabled( false );
//                progress.setVisibility( View.VISIBLE );
//                Log.d( TAG, "onOpened" );
//            }
//
//            /** Request a new ad if ad is expiring */
//            @Override
//            public void onExpiring( AdColonyInterstitial ad )
//            {
//                show_button.setEnabled( false );
//                progress.setVisibility( View.VISIBLE );
//                AdColony.requestInterstitial( ZONE_ID, this, ad_options );
//                Log.d( TAG, "onExpiring" );
//            }
//        };
//
//        /** Set up button to show an ad when clicked */
//        show_button = (Button) findViewById( R.id.showbutton );
//        show_button.setOnClickListener( new View.OnClickListener()
//        {
//            @Override
//            public void onClick( View view )
//            {
//                ad.show();
//            }
//        } );
//    }
//
//    @Override
//    protected void onResume()
//    {
//        super.onResume();
//
//        /**
//         * It's somewhat arbitrary when your ad request should be made. Here we are simply making
//         * a request if there is no valid ad available onResume, but really this can be done at any
//         * reasonable time before you plan on showing an ad.
//         */
//        if (ad == null || ad.isExpired())
//        {
//            /**
//             * Optionally update location info in the ad options for each request:
//             * LocationManager location_manager = (LocationManager) getSystemService( Context.LOCATION_SERVICE );
//             * Location location = location_manager.getLastKnownLocation( LocationManager.GPS_PROVIDER );
//             * ad_options.setUserMetadata( ad_options.getUserMetadata().setUserLocation( location ) );
//             */
//            progress.setVisibility( View.VISIBLE );
//            AdColony.requestInterstitial( ZONE_ID, listener, ad_options );
//        }
//
//    }
//}
//<!-- Force 32bpp -->
//<VideoView
//        android:layout_width="0dp"
//                android:layout_height="0dp"
//                android:id="@+id/videoView" />






