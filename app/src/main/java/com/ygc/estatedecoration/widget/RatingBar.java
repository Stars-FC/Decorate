package com.ygc.estatedecoration.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ygc.estatedecoration.R;

import java.math.BigDecimal;

public class RatingBar extends LinearLayout {
    private boolean mClickable;
    private boolean halfstart;
    private int starCount;
    private int starNum;
    private OnRatingChangeListener onRatingChangeListener;
    private float starImageSize;
    private float starImageWidth;
    private float starImageHeight;
    private float starImagePadding;
    private Drawable starEmptyDrawable;
    private Drawable starFillDrawable;
    private Drawable starHalfDrawable;
    private int y = 1;
    private boolean isEmpty=true;

    public void setStarHalfDrawable(Drawable starHalfDrawable) {
        this.starHalfDrawable = starHalfDrawable;
    }


    public void setOnRatingChangeListener(OnRatingChangeListener onRatingChangeListener) {
        this.onRatingChangeListener = onRatingChangeListener;
    }

    public void setmClickable(boolean clickable) {
        this.mClickable = clickable;
    }

    public void halfStar(boolean halfstart) {
        this.halfstart = halfstart;
    }

    public void setStarFillDrawable(Drawable starFillDrawable) {
        this.starFillDrawable = starFillDrawable;
    }

    public void setStarEmptyDrawable(Drawable starEmptyDrawable) {
        this.starEmptyDrawable = starEmptyDrawable;
    }

    public void setStarImageSize(float starImageSize) {
        this.starImageSize = starImageSize;
    }

    public void setStarImageWidth(float starImageWidth) {
        this.starImageWidth = starImageWidth;
    }

    public void setStarImageHeight(float starImageHeight) {
        this.starImageHeight = starImageHeight;
    }


    public void setStarCount(int starCount) {
        this.starCount = starCount;
    }

    public void setImagePadding(float starImagePadding) {
        this.starImagePadding = starImagePadding;
    }


    public RatingBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(LinearLayout.HORIZONTAL);
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.RatingBar1);

        starHalfDrawable = mTypedArray.getDrawable(R.styleable.RatingBar1_starHalf1);
        starEmptyDrawable = mTypedArray.getDrawable(R.styleable.RatingBar1_starEmpty1);
        starFillDrawable = mTypedArray.getDrawable(R.styleable.RatingBar1_starFill1);
        starImageSize = mTypedArray.getDimension(R.styleable.RatingBar1_starImageSize1, 120);
        starImageWidth = mTypedArray.getDimension(R.styleable.RatingBar1_starImageWidth1, 60);
        starImageHeight = mTypedArray.getDimension(R.styleable.RatingBar1_starImageHeight1, 120);
        starImagePadding = mTypedArray.getDimension(R.styleable.RatingBar1_starImagePadding1, 15);
        starCount = mTypedArray.getInteger(R.styleable.RatingBar1_starCount1, 5);
        starNum = mTypedArray.getInteger(R.styleable.RatingBar1_starNum1, 0);
        mClickable = mTypedArray.getBoolean(R.styleable.RatingBar1_clickable1, true);
        halfstart = mTypedArray.getBoolean(R.styleable.RatingBar1_halfstart1, false);

        for (int i = 0; i < starNum; ++i) {
            ImageView imageView = getStarImageView(context,false);
            addView(imageView);
        }

        for (int i = 0; i < starCount; ++i) {
            ImageView imageView = getStarImageView(context,isEmpty);
            imageView.setOnClickListener(
                    new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (mClickable) {
                                if (halfstart) {
                                    //TODO:This is not the best way to solve half a star,
                                    //TODO:but That's what I can do,Please let me know if you have a better solution
                                    if (y % 2 == 0) {
                                        setStar(indexOfChild(v) + 1f);
                                    } else {
                                        setStar(indexOfChild(v) + 0.5f);
                                    }
                                    if (onRatingChangeListener != null) {
                                        if (y % 2 == 0) {
                                            onRatingChangeListener.onRatingChange(indexOfChild(v) + 1f);
                                            y++;
                                        } else {
                                            onRatingChangeListener.onRatingChange(indexOfChild(v) + 0.5f);
                                            y++;
                                        }
                                    }
                                } else {
                                    setStar(indexOfChild(v) + 1f);
                                    if (onRatingChangeListener != null) {
                                        onRatingChangeListener.onRatingChange(indexOfChild(v) + 1f);
                                    }
                                }

                            }

                        }
                    }
            );
            addView(imageView);
        }
    }


    private ImageView getStarImageView(Context context, boolean isEmpty) {
        ImageView imageView = new ImageView(context);
        ViewGroup.LayoutParams para = new ViewGroup.LayoutParams(
                Math.round(starImageWidth),
                Math.round(starImageHeight)
        );
        imageView.setLayoutParams(para);
        imageView.setPadding(0, 0, Math.round(starImagePadding), 0);
        if(isEmpty){
            imageView.setImageDrawable(starEmptyDrawable);
        }else{
            imageView.setImageDrawable(starFillDrawable);
        }
        return imageView;
    }

    public void setStar(float starCount) {

        int fint = (int) starCount;
        BigDecimal b1 = new BigDecimal(Float.toString(starCount));
        BigDecimal b2 = new BigDecimal(Integer.toString(fint));
        float fPoint = b1.subtract(b2).floatValue();


        starCount = fint > this.starCount ? this.starCount : fint;
        starCount = starCount < 0 ? 0 : starCount;

        //drawfullstar
        for (int i = 0; i < starCount; ++i) {
            ((ImageView) getChildAt(i)).setImageDrawable(starFillDrawable);
        }

        //drawhalfstar
        if (fPoint > 0) {
            ((ImageView) getChildAt(fint)).setImageDrawable(starHalfDrawable);

            //drawemptystar
            for (int i = this.starCount - 1; i >= starCount + 1; --i) {
                ((ImageView) getChildAt(i)).setImageDrawable(starEmptyDrawable);
            }

        } else {
            //drawemptystar
            for (int i = this.starCount - 1; i >= starCount; --i) {
                ((ImageView) getChildAt(i)).setImageDrawable(starEmptyDrawable);
            }

        }

    }

    /**
     * change start listener
     */
    public interface OnRatingChangeListener {

        void onRatingChange(float RatingCount);

    }

}
