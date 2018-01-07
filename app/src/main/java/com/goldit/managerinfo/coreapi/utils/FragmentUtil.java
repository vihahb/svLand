package com.goldit.managerinfo.coreapi.utils;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.goldit.managerinfo.R;


public class FragmentUtil {

    public static void popBackStack(FragmentActivity activity) {
        if (activity.isFinishing()) return;

        if (activity == null) {
            return;
        }
        activity.getSupportFragmentManager().popBackStack();
    }

    public static void clearAllBackStack(FragmentActivity activity) {
        FragmentManager fm = activity.getSupportFragmentManager();
        fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    /**
     * @param fragmentManager
     * @param fragment
     * @param data
     */
    public static void pushFragment(FragmentManager fragmentManager, @NonNull Fragment fragment, @Nullable Bundle data) {
        DebugLog.e("bundle data:" + data);
        showFragment(fragmentManager, fragment, false, data, null, false);
    }


    /**
     * @param fragmentManager
     * @param fragment
     * @param data
     */
    public static void replaceFragment(FragmentManager fragmentManager, @NonNull Fragment fragment, @Nullable Bundle data) {
        showFragment(fragmentManager, fragment, false, data, null, false);
    }

    /**
     * Push fragment to container (with add backstack)
     *
     * @param activity
     * @param fragment
     * @param data
     */
    public static void pushFragment(FragmentActivity activity, @NonNull Fragment fragment, @Nullable Bundle data) {
        DebugLog.e("bundle data:" + data);
        showFragment(activity, fragment, true, data, null, false);
    }

    public static void pushFragmentAnimationRightToLeft(FragmentActivity activity, @NonNull Fragment fragment, @Nullable Bundle data) {
        DebugLog.e("bundle data:" + data);
        showFragment(activity, fragment, true, data, null, true);
    }

    public static void pushFragmentAnimationLeftToRight(FragmentActivity activity, @NonNull Fragment fragment, @Nullable Bundle data) {
        DebugLog.e("bundle data:" + data);
        showFragmentLeftToRight(activity, fragment, true, data, null, true);
    }


    /**
     * Replace fragment in container (without add backstack)
     *
     * @param activity
     * @param fragment
     * @param data
     */
    public static void replaceFragment(FragmentActivity activity, @NonNull Fragment fragment, @Nullable Bundle data) {
        showFragment(activity, fragment, false, data, null, false);
    }

    /**
     * Push fragment to container (with add backstack) with animation
     *
     * @param activity
     * @param fragment
     * @param data
     */
    public static void pushFragmentWithAnimation(FragmentActivity activity, @NonNull Fragment fragment, @Nullable Bundle data) {
        DebugLog.e("bundle data:" + data);
        showFragment(activity, fragment, true, data, null, true);
    }

    /**
     * Replace fragment in container (without add backstack) with animation
     *
     * @param activity
     * @param fragment
     * @param data
     */
    public static void replaceFragmentAnimation(FragmentActivity activity, @NonNull Fragment fragment, @Nullable Bundle data) {
        showFragment(activity, fragment, false, data, null, true);
    }

    /**
     * @param activity
     * @param fragment
     * @param isPushInsteadOfReplace
     * @param data
     * @param tag
     * @param isShowAnimation
     */


    public static void showFragment(FragmentActivity activity, @NonNull Fragment fragment, boolean isPushInsteadOfReplace, @Nullable Bundle data, @Nullable String tag, boolean isShowAnimation) {
        if (activity.isFinishing()) return;
        if (activity == null) {
            return;
        }

        if (data != null) {
            fragment.setArguments(data);
        }

        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        if (isShowAnimation) {
//            fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
        }

        fragmentTransaction.replace(R.id.layout_container, fragment, tag);
        if (isPushInsteadOfReplace) {
            fragmentTransaction.addToBackStack(null);
        }

        if (isShowAnimation) {
//            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_NONE);
        } else {
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_NONE);
        }
        fragmentTransaction.commitAllowingStateLoss();
    }

    public static void showFragmentLeftToRight(FragmentActivity activity, @NonNull Fragment fragment, boolean isPushInsteadOfReplace, @Nullable Bundle data, @Nullable String tag, boolean isShowAnimation) {
        if (activity == null) {
            return;
        }

        if (data != null) {
            fragment.setArguments(data);
        }

        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);

        if (isShowAnimation) {
//            fragmentTransaction.setCustomAnimations(R.anim.slide_out_right, R.anim.slide_in_left);

        }

        fragmentTransaction.replace(R.id.layout_container, fragment, tag);
        if (isPushInsteadOfReplace) {
            fragmentTransaction.addToBackStack(null);
        }

        if (isShowAnimation) {
//            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_NONE);
        } else {
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        }

        fragmentTransaction.commitAllowingStateLoss();
    }


    public static void showFragment(FragmentManager fragmentManager, @NonNull Fragment fragment, boolean isPushInsteadOfReplace, @Nullable Bundle data, @Nullable String tag, boolean isShowAnimation) {
        if (fragmentManager == null) {
            return;
        }

        if (data != null) {
            fragment.setArguments(data);
        }

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (isShowAnimation) {
//            fragmentTransaction.setCustomAnimations(R.anim.slide_in_up,
//                    R.anim.slide_out_up, R.anim.slide_in_up, R.anim.slide_out_up);
        }

        fragmentTransaction.replace(R.id.layout_container, fragment, tag);
        if (isPushInsteadOfReplace) {
            fragmentTransaction.addToBackStack(null);
        }

        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commitAllowingStateLoss();
    }


    public static void addFragment(FragmentActivity activity, @NonNull Fragment fragment, boolean isAddToBackStack) {
        if (activity.isFinishing()) return;
        if (activity == null) {
            return;
        }
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();

        fragmentTransaction.add(R.id.layout_container, fragment, null);

        if (isAddToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }

//        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commitAllowingStateLoss();
    }

    public static void addFragmentData(FragmentActivity activity, @NonNull Fragment fragment, boolean isAddToBackStack, Bundle bundle) {
        if (activity == null) {
            return;
        }
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        if (bundle != null)
            fragment.setArguments(bundle);
        fragmentTransaction.add(R.id.layout_container, fragment, null);
        if (isAddToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }

//        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commitAllowingStateLoss();
    }


    public static void showFragmentWithAnimation(FragmentActivity activity, @NonNull Fragment fragment, boolean isPushInsteadOfReplace, @Nullable Bundle data, @Nullable String tag, int enterAnim, int exitAnim) {
        if (activity == null) {
            return;
        }

        if (data != null) {
            fragment.setArguments(data);
        }

        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();

        fragmentTransaction.setCustomAnimations(enterAnim,
                exitAnim, enterAnim, exitAnim);

        fragmentTransaction.replace(R.id.layout_container, fragment, tag);
        if (isPushInsteadOfReplace) {
            fragmentTransaction.addToBackStack(null);
        }

        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commitAllowingStateLoss();
    }


    public static void pushFragmentLayoutMain(FragmentManager manager, int layout, Fragment fragment, Bundle bundle) {
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(layout, fragment);//R.id.content_frame is the layout you want to replace
        fragmentTransaction.commit();
    }


//    public static void showMenuFragment(FragmentActivity activity, @NonNull Fragment fragment, boolean isPushInsteadOfReplace, @Nullable Bundle data, @Nullable String tag, boolean isShowAnimation) {
//        if (activity == null) {
//            return;
//        }
//
//        if (data != null) {
//            fragment.setArguments(data);
//        }
//
//        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
//
//        if (isShowAnimation) {
//            fragmentTransaction.setCustomAnimations(R.anim.slide_in_up,
//                    R.anim.slide_out_up, R.anim.slide_in_up, R.anim.slide_out_up);
//        }
//
//        fragmentTransaction.replace(R.id.layout_fragment_navitaion, fragment, tag);
//        if (isPushInsteadOfReplace) {
//            fragmentTransaction.addToBackStack(null);
//        }
//
//        if (isShowAnimation) {
//            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_NONE);
//        } else {
//            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//        }
//
//        fragmentTransaction.commitAllowingStateLoss();
//    }
}
