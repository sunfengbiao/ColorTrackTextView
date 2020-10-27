package com.sunfb.colortracktextview;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    ColorTrackTextView colorTrackTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         colorTrackTextView =findViewById(R.id.cctv_text);

    }

    public void leftToRight(View view) {
        colorTrackTextView.setDirection(ColorTrackTextView.Direction.LET_TO_RIGHT);
        ValueAnimator valueAnimator =ValueAnimator.ofFloat(0,1);
        valueAnimator.setDuration(2000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float progress = (float) animation.getAnimatedValue();
                colorTrackTextView.setProgress(progress);
            }
        });
        valueAnimator.start();

    }

    public void rightToLeft(View view) {
        colorTrackTextView.setDirection(ColorTrackTextView.Direction.RIGHT_TO_LEFT);
        ValueAnimator valueAnimator =ValueAnimator.ofFloat(0,1);
        valueAnimator.setDuration(2000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float progress = (float) animation.getAnimatedValue();
                colorTrackTextView.setProgress(progress);
            }
        });
        valueAnimator.start();
    }
}