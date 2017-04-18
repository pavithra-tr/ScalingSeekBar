package com.mobileapp.itech.customseekbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import java.util.ArrayList;

public class ScaleCustomSeekBar extends android.support.v7.widget.AppCompatSeekBar {

	private ArrayList<ProgressItem> mProgressItemsList;

	Drawable mThumb;

	public ScaleCustomSeekBar(Context context) {
		super(context);
	}

	public ScaleCustomSeekBar(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ScaleCustomSeekBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public void initData(ArrayList<ProgressItem> progressItemsList) {
		this.mProgressItemsList = progressItemsList;
	}

	@Override
	public void setThumb(Drawable thumb) {
		super.setThumb(thumb);
		mThumb = thumb;
	}

	public Drawable getSeekBarThumb() {
		return mThumb;
	}

	@Override
	protected synchronized void onMeasure(int widthMeasureSpec,
										  int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	protected void onDraw(Canvas canvas) {
		if (mProgressItemsList.size() > 0) {
			int progressBarWidth = getWidth();
			int progressBarHeight = getHeight();
			int thumboffset = getThumbOffset();
			int lastProgressX = 0;
			int progressItemWidth, progressItemRight;
			for (int i = 0; i < mProgressItemsList.size(); i++) {
				ProgressItem progressItem = mProgressItemsList.get(i);
				Paint progressPaint = new Paint();
				progressPaint.setColor(getResources().getColor(progressItem.color));
				progressItemWidth = (int) (progressItem.progressItemPercentage * progressBarWidth / 100);
				progressItemRight = lastProgressX + progressItemWidth;

				// for last item give right to progress item to the width
				if (i == mProgressItemsList.size() - 1
						&& progressItemRight != progressBarWidth) {
					progressItemRight = progressBarWidth;
				}
				Rect progressRect = new Rect();
         		progressRect.set(lastProgressX, thumboffset / 2, progressItemRight, progressBarHeight - thumboffset / 2);
				canvas.drawRect(progressRect, progressPaint);
				lastProgressX = progressItemRight;
			}
			super.onDraw(canvas);
		}
	}
}