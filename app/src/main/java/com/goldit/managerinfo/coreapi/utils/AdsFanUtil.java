package com.goldit.managerinfo.coreapi.utils;//package com.goldit.makemoneyv1.coreapi.utils;
//
//import android.support.v4.app.FragmentActivity;
//import android.widget.RelativeLayout;
//
//import com.facebook.ads.Ad;
//import com.facebook.ads.AdError;
//import com.facebook.ads.AdSettings;
//import com.facebook.ads.AdView;
//import com.facebook.ads.InterstitialAd;
//import com.facebook.ads.InterstitialAdListener;
//
//import java.util.Random;
//
///**
// * Created by baonguyen on 11/12/17.
// */
//
//public class AdsFanUtil {
//
//
//    public static void showInterstitialFAN(final InterstitialAd interstitialAd, int rd) {
//        if (new Random().nextInt(100) > rd) {
//            return;
//        }
//        AdSettings.addTestDevice("a4ae6d2da2fff0cf9f65b7ec491d5d8b");
//        AdSettings.addTestDevice("46c80b78115607fd1e3a363cc614202d");
//        interstitialAd.setAdListener(new InterstitialAdListener() {
//            @Override
//            public void onInterstitialDisplayed(Ad ad) {
//                // Interstitial displayed callback
//            }
//
//            @Override
//            public void onInterstitialDismissed(Ad ad) {
//                // Interstitial dismissed callback
//            }
//
//            @Override
//            public void onError(Ad ad, AdError adError) {
//            }
//
//            @Override
//            public void onAdLoaded(Ad ad) {
//                interstitialAd.show();
//            }
//
//            @Override
//            public void onAdClicked(Ad ad) {
//                // Ad clicked callback
//            }
//
//            @Override
//            public void onLoggingImpression(Ad ad) {
//                // Ad impression logged callback
//            }
//        });
//
//        // For auto play video ads, it's recommended to load the ad
//        // at least 30 seconds before it is shown
//        interstitialAd.loadAd();
//    }
//
//
//    public static void onDestroyFANInterstitial(InterstitialAd interstitialAd) {
//        if (interstitialAd != null) {
//            interstitialAd.destroy();
//        }
//    }
//
//    public static void onDestroyFANBanner(AdView adView) {
//        if (adView != null) {
//            adView.destroy();
//        }
//    }
//
//    public static void initAdBanner(FragmentActivity activity, RelativeLayout bannerAdView, AdView adView) {
//        AdSettings.addTestDevice("46c80b78115607fd1e3a363cc614202d");
//        AdSettings.addTestDevice("a4ae6d2da2fff0cf9f65b7ec491d5d8b");
//        bannerAdView.addView(adView);
//        adView.loadAd();
//    }
//}
