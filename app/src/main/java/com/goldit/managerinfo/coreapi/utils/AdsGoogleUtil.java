package com.goldit.managerinfo.coreapi.utils;//
//package com.goldit.managerinfo.coreapi.utils;
//
//import android.animation.Animator;
//import android.animation.ObjectAnimator;
//import android.app.Activity;
//import android.content.Context;
//import android.view.View;
//import android.widget.RelativeLayout;
//
//import com.goldit.managerinfo.models.Setting;
//import com.goldit.managerinfo.realm.RealmController;
//import com.google.android.gms.ads.AdListener;
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdSize;
//import com.google.android.gms.ads.AdView;
//import com.google.android.gms.ads.InterstitialAd;
//import com.google.android.gms.ads.MobileAds;
//import com.google.android.gms.ads.NativeExpressAdView;
//
//import java.util.Random;
//
//
///**
// * Created by Linhnh on 8/7/2016.
// */
//public class AdsGoogleUtil {
//    private static InterstitialAd mInterstitialAd;
//
//    //---------------------------------Ads Full---------------------------------------
//    public static void ranDomAds(final Activity activity, int rd) {
//        if (new Random().nextInt(100) <= rd) {
//            initAds_FULL(activity);
//        }
//    }
//
//    private static void initAds_FULL(Activity activity) {
//        mInterstitialAd = new InterstitialAd(activity);
//        // set the ad unit ID
//        Setting adsGoogle = null;
//        try {
//            adsGoogle = (Setting) RealmController.with(activity).querryObject(Setting.class, "setting_key", "Admob_Intertitial").first();
//        } catch (Exception e) {
//        }
//
//        if (adsGoogle == null) return;
//        mInterstitialAd.setAdUnitId(SharedPrefUtils.getString("AutoIntertital", adsGoogle.getSetting_string_values()));
//
//        AdRequest adRequest = new AdRequest.Builder()
////                .addTestDevice("6B8B9935CA450616884B76D5F44B76FF")
//                .build();
//        // Load ads into Interstitial Ads
//        mInterstitialAd.loadAd(adRequest);
//        mInterstitialAd.setAdListener(new AdListener() {
//            public void onAdLoaded() {
//                showInterstitial();
//                SharedPrefUtils.putString("View_Full", (Integer.parseInt(SharedPrefUtils.getString("View_Full", "0")) + 1) + "");
//            }
//
//            @Override
//            public void onAdClosed() {
//                super.onAdClosed();
//            }
//
//            @Override
//            public void onAdOpened() {
//                super.onAdOpened();
//                SharedPrefUtils.putString("Clicked_Full", (Integer.parseInt(SharedPrefUtils.getString("Clicked_Full", "0")) + 1) + "");
//            }
//        });
//    }
//
//    private static void showInterstitial() {
//        if (mInterstitialAd.isLoaded()) {
//            mInterstitialAd.show();
//        }
//    }
//
//
//    //---------------------------------Ads Item List---------------------------------------
//    public static void adsItemList(Context context, final NativeExpressAdView adView) {
//        AdRequest request = new AdRequest.Builder()
//                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
//                .build();
//        adView.loadAd(request);
//        adView.setAdListener(new AdListener() {
//
//            @Override
//            public void onAdFailedToLoad(int errorCode) {
//                adView.setVisibility(View.GONE);
//
//            }
//
//            @Override
//            public void onAdOpened() {
//            }
//
//            @Override
//            public void onAdLeftApplication() {
//                super.onAdLeftApplication();
//                SharedPrefUtils.putString("Clicked_Native", ((Integer.parseInt(SharedPrefUtils.getString("Clicked_Native", "0")) + 1)) + "");
//            }
//
//            @Override
//            public void onAdLoaded() {
//                SharedPrefUtils.putString("View_Native", ((Integer.parseInt(SharedPrefUtils.getString("View_Native", "0")) + 1)) + "");
//                adView.setVisibility(View.VISIBLE);
//            }
//        });
//    }
//
//
//    public static void adsItemListDiamic(Activity activity, final RelativeLayout container) {
//        Setting adsGoogle = null;
//        try {
//            adsGoogle = (Setting) RealmController.with(activity).querryObject(Setting.class, "setting_key", "Android_GGAdmob_AdsID_Native_V2").first();
//        } catch (Exception e) {
//        }
//        if (adsGoogle == null) {
//            SharedPrefUtils.putString("Native", "ca-app-pub-1143076622592509/9257194076");
//        } else {
//            SharedPrefUtils.putString("Native", adsGoogle.getSetting_string_values());
//
//        }
//
//
//        if (SharedPrefUtils.getInt("RunningAuto", 0) != 1) {
//            return;
//        }
//
//        AdRequest request = new AdRequest.Builder()
//                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
//                .build();
//
//        final NativeExpressAdView mAdView = new NativeExpressAdView(activity);
//        mAdView.setAdUnitId(SharedPrefUtils.getString("Native", "ca-app-pub-1143076622592509/9257194076"));
//        mAdView.setAdSize(new AdSize(AdSize.FULL_WIDTH, 250));
//        container.addView(mAdView);
//
//        mAdView.loadAd(request);
//        mAdView.setAdListener(new AdListener() {
//
//            @Override
//            public void onAdFailedToLoad(int errorCode) {
//                mAdView.setVisibility(View.GONE);
//
//            }
//
//            @Override
//            public void onAdOpened() {
//            }
//
//            @Override
//            public void onAdLeftApplication() {
//                super.onAdLeftApplication();
//                SharedPrefUtils.putString("Clicked_Native", ((Integer.parseInt(SharedPrefUtils.getString("Clicked_Native", "0")) + 1)) + "");
//            }
//
//            @Override
//            public void onAdLoaded() {
//                SharedPrefUtils.putString("View_Native", ((Integer.parseInt(SharedPrefUtils.getString("View_Native", "0")) + 1)) + "");
//                mAdView.setVisibility(View.VISIBLE);
//            }
//        });
//    }
//
//
//    public static void adsItemNativeDiamicFlip(Activity activity, final RelativeLayout container, final View viewToFlip) {
//        Setting adsGoogle = null;
//        try {
//            adsGoogle = (Setting) RealmController.with(activity).querryObject(Setting.class, "setting_key", "AdAdmob_LargeNative_Unit_ID_Android").first();
//
//        } catch (Exception e) {
//        }
//        if (adsGoogle == null) {
//            SharedPrefUtils.putString("Native", "ca-app-pub-1143076622592509/6288014876");
//        } else {
//            SharedPrefUtils.putString("Native", adsGoogle.getSetting_string_values());
//        }
//
//
//        AdRequest request = new AdRequest.Builder()
//                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
//                .build();
//
//        final NativeExpressAdView mAdView = new NativeExpressAdView(activity);
//        mAdView.setAdUnitId(SharedPrefUtils.getString("Native", "ca-app-pub-1143076622592509/6288014876"));
//        mAdView.setAdSize(new AdSize(AdSize.FULL_WIDTH, 250));
//        container.addView(mAdView);
//
//        mAdView.loadAd(request);
//        mAdView.setAdListener(new AdListener() {
//
//            @Override
//            public void onAdFailedToLoad(int errorCode) {
//                mAdView.setVisibility(View.GONE);
//
//            }
//
//            @Override
//            public void onAdOpened() {
//            }
//
//            @Override
//            public void onAdLeftApplication() {
//                super.onAdLeftApplication();
//            }
//
//            @Override
//            public void onAdLoaded() {
//                ObjectAnimator flip = ObjectAnimator.ofFloat(viewToFlip, "rotationY", 0f, 180f);
//                flip.setDuration(1000);
//                flip.addListener(new Animator.AnimatorListener() {
//                    @Override
//                    public void onAnimationStart(Animator animation) {
//
//                    }
//
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
//                        container.setVisibility(View.VISIBLE);
//                    }
//
//                    @Override
//                    public void onAnimationCancel(Animator animation) {
//
//                    }
//
//                    @Override
//                    public void onAnimationRepeat(Animator animation) {
//
//                    }
//                });
//                flip.start();
//            }
//        });
//    }
//
//
//    //---------------------------------Ads Banner -----------------------------------------
//    public static void initAdBanner(Activity activity, RelativeLayout container) {
//        Setting adsGoogle = null;
//        try {
//            adsGoogle = (Setting) RealmController.with(activity).querryObject(Setting.class, "setting_key", "Admob_Banner").first();
//
//        } catch (Exception e) {
//        }
//        if (adsGoogle == null) {
//            SharedPrefUtils.putString("AutoBanner", "ca-app-pub-8890226785436898/7964268788");
//            return;
//        } else {
//            SharedPrefUtils.putString("AutoBanner", adsGoogle.getSetting_string_values());
//        }
//
//
//        MobileAds.initialize(activity);
//        AdRequest adRequest = new AdRequest.Builder()
//                .build();
//        final AdView mAdView = new AdView(activity);
//        mAdView.setAdUnitId(SharedPrefUtils.getString("AutoBanner", "ca-app-pub-8890226785436898/7964268788"));
//        mAdView.setAdSize(new AdSize(AdSize.FULL_WIDTH, 50));
//        container.addView(mAdView);
//        mAdView.loadAd(adRequest);
//        mAdView.setAdListener(new AdListener() {
//            @Override
//            public void onAdLoaded() {
//                super.onAdLoaded();
//                mAdView.setVisibility(View.VISIBLE);
//                SharedPrefUtils.putString("View_Banner", ((Integer.parseInt(SharedPrefUtils.getString("View_Banner", "0")) + 1)) + "");
//            }
//
//            @Override
//            public void onAdOpened() {
//                super.onAdOpened();
//                SharedPrefUtils.putString("Clicked_Banner", ((Integer.parseInt(SharedPrefUtils.getString("Clicked_Banner", "0"))) + 1) + "");
//            }
//
//            @Override
//            public void onAdFailedToLoad(int errorCode) {
//                super.onAdFailedToLoad(errorCode);
//                mAdView.setVisibility(View.GONE);
//            }
//
//            @Override
//            public void onAdClosed() {
//                super.onAdClosed();
//                mAdView.setVisibility(View.GONE);
//
//            }
//        });
//    }
//
//
//}
