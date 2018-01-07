package com.goldit.managerinfo.coreapi.utils;

import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Rect;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;


/**
 * Created by Kill on 6/18/2016.
 */
public class AnimationUtil {
    public static void animZoomIn(final RelativeLayout relativeLayout, Rect startBounds, float startScaleFinal, AnimatorListenerAdapter animationAdapter) {
        AnimatorSet set = new AnimatorSet();
        set.play(
                ObjectAnimator.ofFloat(relativeLayout, View.X,
                        startBounds.left))
                .with(ObjectAnimator.ofFloat(relativeLayout, View.Y,
                        startBounds.top - 70))
                .with(ObjectAnimator.ofFloat(relativeLayout,
                        View.SCALE_X, startScaleFinal))
                .with(ObjectAnimator.ofFloat(relativeLayout,
                        View.SCALE_Y, startScaleFinal));
        set.setDuration(500);
        set.setInterpolator(new DecelerateInterpolator());

        set.addListener(animationAdapter);
        set.start();
    }
}
