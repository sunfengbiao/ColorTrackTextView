package com.sunfb.colortracktextview;

import android.animation.ValueAnimator;
import android.app.ActionBar;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class ViewPageActivity extends AppCompatActivity {

    private LinearLayout ll_navigation;
    private ViewPager viewPager;
    private String[] titles = new String[]{"直播","视频","图片","段子","新闻","足球"};
    private List<ColorTrackTextView> cctvList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_page);
        initView();
        initIndicator();
        initViewPage();

    }

    void initView() {
        ll_navigation = findViewById(R.id.ll_navigation);
        viewPager = findViewById(R.id.vp_content);
    }

    private void initViewPage() {
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

            @NonNull
            @Override
            public Fragment getItem(int position) {
                return PageFragment.getInstance(titles[position]);
            }

            @Override
            public int getCount() {
                return titles.length;
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                try {
                    ColorTrackTextView mLeft = cctvList.get(position);
                    mLeft.setDirection(ColorTrackTextView.Direction.RIGHT_TO_LEFT);
                    mLeft.setProgress(1 - positionOffset);
                    ColorTrackTextView mRight = cctvList.get(position + 1);
                    mRight.setDirection(ColorTrackTextView.Direction.LET_TO_RIGHT);
                    mRight.setProgress(positionOffset);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initIndicator() {
        for (int i = 0; i < titles.length; i++) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.weight = 1;
            ColorTrackTextView cctv = new ColorTrackTextView(this);
            cctv.setText(titles[i]);
            cctv.setLayoutParams(layoutParams);
            ll_navigation.addView(cctv);
            cctvList.add(cctv);
        }
    }

}