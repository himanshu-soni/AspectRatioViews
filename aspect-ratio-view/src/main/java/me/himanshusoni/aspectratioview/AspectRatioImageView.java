package me.himanshusoni.aspectratioview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by himanshusoni on 28/12/15.
 */
public class AspectRatioImageView extends ImageView {
    private float mAspectRatio = 0f;

    public AspectRatioImageView(Context context) {
        this(context, null, 0);
    }

    public AspectRatioImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AspectRatioImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AspectRatioImageView, defStyle, 0);

        mAspectRatio = a.getFloat(R.styleable.AspectRatioImageView_aspectRatio, 0);

        if (mAspectRatio == 0f) {
            throw new IllegalArgumentException("You must specify an aspect ratio when using the AspectRatioImageView.");
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
