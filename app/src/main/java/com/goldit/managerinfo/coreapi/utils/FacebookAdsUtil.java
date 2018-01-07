package com.goldit.managerinfo.coreapi.utils;//package com.goldit.makemoneyv1.coreapi.utils;
//
//import android.app.Activity;
//import android.widget.RelativeLayout;
//
//import com.facebook.ads.Ad;
//import com.facebook.ads.AdError;
//import com.facebook.ads.AdListener;
//import com.facebook.ads.AdSettings;
//import com.facebook.ads.AdView;
//import com.facebook.ads.InterstitialAd;
//import com.facebook.ads.InterstitialAdListener;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
//
///**
// * Created by Kill on 11/28/2016.
// */
//
//public class FacebookAdsUtil {
//
//    public static void setBannerAds(Activity activity, RelativeLayout relativeLayout, AdView adView) {
//        if (SharedPrefUtils.getInt("RemoveAds", 0) == 1 || SharedPrefUtils.getInt("Banner", 1) == 0)
//            return;
//        List<String> testDevices = new ArrayList<>();
//        testDevices.add("a45963bd1fc0f55b83bd64c7757de2a1");
//        testDevices.add("8107b424cf5c17ce83e487c1b90c61b4");
//        AdSettings.addTestDevices(testDevices);
//
//        relativeLayout.addView(adView);
//        adView.setAdListener(new AdListener() {
//            @Override
//            public void onError(Ad ad, AdError adError) {
//
//            }
//
//            @Override
//            public void onAdLoaded(Ad ad) {
//
//            }
//
//            @Override
//            public void onAdClicked(Ad ad) {
//                GoogleAnalyticsHelper.sendEvent("onAdClicked Banner Facebook");
//            }
//        });
//        adView.loadAd();
//    }
//
//
//    public static void randomFullAds(Activity activity, int input) {
//        if (SharedPrefUtils.getInt("RemoveAds", 0) == 1) return;
//        Random random = new Random();
//        if (random.nextInt(100) < input) {
//            loadInterstitialAd(activity);
//        }
//    }
//
//
//    private static void loadInterstitialAd(Activity activity) {
//        System.out.println("load full ads------------>");
//        final InterstitialAd interstitialAd = new InterstitialAd(activity, "215935125517018_215940958849768");
//        List<String> testDevices = new ArrayList<>();
//        testDevices.add("a45963bd1fc0f55b83bd64c7757de2a1");
//        testDevices.add("8107b424cf5c17ce83e487c1b90c61b4");
//        AdSettings.addTestDevices(testDevices);
//
//        interstitialAd.setAdListener(new InterstitialAdListener() {
//            @Override
//            public void onInterstitialDisplayed(Ad ad) {
//
//            }
//
//            @Override
//            public void onInterstitialDismissed(Ad ad) {
//
//            }
//
//            @Override
//            public void onError(Ad ad, AdError adError) {
//
//            }
//
//            @Override
//            public void onAdLoaded(Ad ad) {
//                interstitialAd.show();
//            }
//
//            @Override
//            public void onAdClicked(Ad ad) {
//                GoogleAnalyticsHelper.sendEvent("onAdClicked Facebook");
//            }
//        });
//        interstitialAd.loadAd();
//    }
//}
