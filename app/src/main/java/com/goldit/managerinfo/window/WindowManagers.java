package com.goldit.managerinfo.window;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.goldit.managerinfo.R;
import com.goldit.managerinfo.window.blur.BlurDrawable;

public class WindowManagers {

    private Context context;
    private static WindowManager windowManager;
    private View subView;
    private WindowManager.LayoutParams mParams;

    public WindowManagers(Context context) {
        this.context = context;
        initView(context);
    }

    @SuppressLint("InflateParams")
    private void initView(Context context) {
        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        subView = layoutInflater.inflate(R.layout.window_view_blur, null);

        View view_background_blur = subView.findViewById(R.id.view_background_blur);
        View view_blur = subView.findViewById(R.id.view_blur);

        mParams = new WindowManager.LayoutParams();
        mParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        mParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        mParams.gravity = Gravity.CENTER;
        mParams.format = PixelFormat.TRANSLUCENT;//trong suot
        mParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;// noi tren all be mat
        mParams.flags = WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;// khong bi gioi han boi man hinh|Su duoc nut home


        BlurDrawable blurDrawable = new BlurDrawable(view_background_blur, 2);
        view_blur.setBackground(blurDrawable);

        TextView tv_close = subView.findViewById(R.id.tv_close);
        tv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeView();
            }
        });
    }

    public void show() {
        if (windowManager != null && subView != null && mParams != null)
            windowManager.addView(subView, mParams);
    }

    public void closeView() {
        if (windowManager != null && subView != null)
            windowManager.removeView(subView);
    }

    public boolean isShow() {
        if (subView != null)
            return subView.isShown();
        else
            return false;
    }
}
