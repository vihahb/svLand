package com.goldit.managerinfo.main;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.goldit.managerinfo.R;
import com.goldit.managerinfo.coreapi.BaseActivity;
import com.goldit.managerinfo.coreapi.utils.FragmentUtil;
import com.goldit.managerinfo.fragment.home.HomeFragment;

public class MainActivity extends BaseActivity {

    @Override
    public int setContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        Bundle bundle = getIntent().getBundleExtra("Bundle");
        FragmentUtil.pushFragment(this, new HomeFragment(), bundle);
    }
    private static final int TIME_DELAY = 2000;
    private static long back_pressed;
    @Override
    public void onBackPressed() {
        if (back_pressed + TIME_DELAY > System.currentTimeMillis()) {
            finish();
            super.onBackPressed();

        } else {
            Toast.makeText(getBaseContext(), "Ấn back lần nữa để thoát ứng dụng!",
                    Toast.LENGTH_SHORT).show();
        }
        back_pressed = System.currentTimeMillis();
    }
}
