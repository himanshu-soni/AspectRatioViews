package me.himanshusoni.aspectratioview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by himanshusoni on 28/12/15.
 */
public class AspectRatioLinearLayout extends LinearLayout {
    private float mAspectRatio = 0f;

    public AspectRatioLinearLayout(Context context) {
        this(context, null, 0);
    }

    public AspectRatioLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AspectRatioLinearLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AspectRatioLinearLayout, defStyle, 0);

        mAspectRatio = a.getFloat(R.styleable.AspectRatioLinearLayout_aspectRatio, 0);

        if (mAspectRatio == 0f) {
            throw new IllegalArgumentException("You must specify an aspect ratio when using the AspectRatioLinearLayout.");
        }
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int width, height;
        if (mAspectRatio != 0) {
            width = widthSize;
            height = (int) (width / mAspectRatio);
            int exactWidthSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
            int exactHeightSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
            super.onMeasure(exactWidthSpec, exactHeightSpec);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
