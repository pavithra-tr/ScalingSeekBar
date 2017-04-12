package com.mobileapp.itech.scalingseekbar;


import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    private ScaleCustomSeekBar mSeekbar;
    private TextView mTextView;

    private float totalSpan = 1500;
    private float redSpan = 300;
    private float blueSpan = 300;
    private float greenSpan = 300;
    private float yellowSpan = 300;
    private float orangeSpan=300;
    int progressValue = 0;
    private ArrayList<ProgressItem> progressItemList;
    private ProgressItem mProgressItem;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSeekbar = ((ScaleCustomSeekBar) findViewById(R.id.seekBar0));
        mTextView = (TextView)findViewById(R.id.textView);
        initDataToSeekbar();
        mSeekbar.setOnSeekBarChangeListener(this);
    }

    private void initDataToSeekbar() {
        progressItemList = new ArrayList<ProgressItem>();
        // red span
        mProgressItem = new ProgressItem();
        mProgressItem.progressItemPercentage = ((redSpan / totalSpan) * 100);
        Log.i("Mainactivity", mProgressItem.progressItemPercentage + "");
        mProgressItem.color = R.color.red;
        progressItemList.add(mProgressItem);
        // blue span
        mProgressItem = new ProgressItem();
        mProgressItem.progressItemPercentage = (blueSpan / totalSpan) * 100;
        mProgressItem.color = R.color.blue;
        progressItemList.add(mProgressItem);
        // green span
        mProgressItem = new ProgressItem();
        mProgressItem.progressItemPercentage = (greenSpan / totalSpan) * 100;
        mProgressItem.color = R.color.green;
        progressItemList.add(mProgressItem);
        // yellow span
        mProgressItem = new ProgressItem();
        mProgressItem.progressItemPercentage = (yellowSpan / totalSpan) * 100;
        mProgressItem.color = R.color.yellow;
        progressItemList.add(mProgressItem);
        // greyspan
        mProgressItem = new ProgressItem();
        mProgressItem.progressItemPercentage = (orangeSpan / totalSpan) * 100;
        mProgressItem.color = R.color.orange;
        progressItemList.add(mProgressItem);

        mSeekbar.initData(progressItemList);
        mSeekbar.invalidate();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        progressValue = progress;
        // To set the text while changing the thumb
        RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        p.addRule(RelativeLayout.BELOW, seekBar.getId());
        Rect thumbRect = mSeekbar.getSeekBarThumb().getBounds();
        p.setMargins(thumbRect.centerX(),0, 0, 0);
        mTextView.setLayoutParams(p);
        mTextView.setText("..."+progressValue + "...");
        mTextView.setTextColor(getResources().getColor(R.color.white));
        mTextView.setBackgroundResource(R.drawable.background_fill);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        mTextView.setVisibility(View.VISIBLE);
        final Animation animationFadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        mTextView.startAnimation(animationFadeIn);
    }
}
