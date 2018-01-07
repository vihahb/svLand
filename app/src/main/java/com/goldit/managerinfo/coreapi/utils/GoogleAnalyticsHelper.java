package com.goldit.managerinfo.coreapi.utils;//package com.goldit.makemoneyv1.coreapi.utils;
//import com.goldit.makemoneyv1.coreapi.BaseApplication;
//import com.google.android.gms.analytics.GoogleAnalytics;
//import com.google.android.gms.analytics.HitBuilders;
//import com.google.android.gms.analytics.Tracker;
//
//public class GoogleAnalyticsHelper {
//    private static final Tracker tracker = GoogleAnalytics.getInstance(BaseApplication.getInstance()).newTracker("UA-87672165-1");
//
//    public static void sendScreenName(String screenName) {
//        tracker.enableAdvertisingIdCollection(true);
//        tracker.setScreenName(screenName);
//        tracker.send(new HitBuilders.ScreenViewBuilder().build());
//    }
//
//    public static void sendEvent(String category) {
//        tracker.enableAdvertisingIdCollection(true);
//        tracker.send(new HitBuilders.EventBuilder()
//                .setCategory(category)
//                .build());
//    }
//
//    public static void sendEvent(String category, String action) {
//        tracker.enableAdvertisingIdCollection(true);
//        tracker.send(new HitBuilders.EventBuilder()
//                .setCategory(category)
//                .setAction(action)
//                .build());
//    }
//
//    public static void sendEvent(String category, String action, String label) {
//        tracker.enableAdvertisingIdCollection(true);
//        tracker.send(new HitBuilders.EventBuilder()
//                .setCategory(category)
//                .setAction(action)
//                .setLabel(label)
//                .build());
//    }
//
//    public static void sendEvent(String category, String action, String label, long value) {
//        tracker.enableAdvertisingIdCollection(true);
//        tracker.send(new HitBuilders.EventBuilder()
//                .setCategory(category)
//                .setAction(action)
//                .setLabel(label)
//                .setValue(value)
//                .build());
//    }
//}
