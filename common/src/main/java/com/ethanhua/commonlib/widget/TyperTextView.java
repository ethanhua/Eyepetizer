package com.ethanhua.commonlib.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Parcel;
import android.support.v7.widget.AppCompatTextView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;

import com.ethanhua.commonlib.R;

import java.util.ArrayList;

public class TyperTextView extends AppCompatTextView {
    private static final int TYPE_INTERVAL = 20;
    private CharSequence mTyperText;
    private long duration = 500;
    private float spanGroupAlpha = 0f;
    private FireworksSpanGroup spanGroup;

    public TyperTextView(Context context) {
        this(context, null);
    }

    public TyperTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TyperTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TyperTextView);
        mTyperText = a.getString(R.styleable.TyperTextView_typerText);
        a.recycle();
        if (!TextUtils.isEmpty(mTyperText)) {
            startTypeText();
        }
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    private void typeText(final SpannableString spannableString) {
        getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                spanGroupAlpha = spanGroupAlpha + 1.0f / (duration / TYPE_INTERVAL);
                if (spanGroup != null) {
                    spanGroup.setAlpha(spanGroupAlpha);
                }
                setText(spannableString);
                if (spanGroupAlpha > 1) {
                    return;
                }
                typeText(spannableString);
            }
        }, TYPE_INTERVAL);
    }

    public void startTypeText() {
        if(!isAttachedToWindow()){
            return;
        }
        reset();
        spanGroup = new FireworksSpanGroup(0);
        final SpannableString spannableString = new SpannableString(mTyperText);
        for (int i = 0; i < spannableString.length(); i++) {
            int color = getTextColors().getDefaultColor();
            MutableForegroundColorSpan span = new MutableForegroundColorSpan(0, color);
            spanGroup.addSpan(span);
            spannableString.setSpan(span, i, i + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        typeText(spannableString);
    }

    private void reset() {
        getHandler().removeCallbacksAndMessages(this);
        spanGroupAlpha = 0f;
    }

    public void setTyperText(CharSequence typerText) {
        if (typerText == null || (getText() != null && getText().equals(typerText))) {
            return;
        }
        mTyperText = typerText;
        startTypeText();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getHandler().removeCallbacksAndMessages(this);
    }

    private static final class FireworksSpanGroup {
        private final float mAlpha;
        private final ArrayList<MutableForegroundColorSpan> mSpans;

        private FireworksSpanGroup(float alpha) {
            mAlpha = alpha;
            mSpans = new ArrayList<>();
        }

        public void addSpan(MutableForegroundColorSpan span) {
            span.setAlpha((int) (mAlpha * 255));
            mSpans.add(span);
        }

        public void setAlpha(float alpha) {
            int size = mSpans.size();
            float total = 1.0f * size * alpha;

            for (int index = 0; index < size; index++) {
                MutableForegroundColorSpan span = mSpans.get(index);
                if (total >= 1.0f) {
                    span.setAlpha(255);
                    total -= 1.0f;
                } else {
                    span.setAlpha((int) (total * 255));
                    total = 0.0f;
                }
            }
        }


        public float getAlpha() {
            return mAlpha;
        }
    }

    private static class MutableForegroundColorSpan extends ForegroundColorSpan {

        private int mAlpha = 255;
        private int mForegroundColor;


        public MutableForegroundColorSpan(int alpha, int color) {
            super(color);
            mAlpha = alpha;
            mForegroundColor = color;
        }

        public MutableForegroundColorSpan(Parcel src) {
            super(src);
            mForegroundColor = src.readInt();
            mAlpha = src.readInt();
        }

        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeInt(mForegroundColor);
            dest.writeFloat(mAlpha);
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(getForegroundColor());
        }

        /**
         * @param alpha from 0 to 255
         */
        public void setAlpha(int alpha) {
            mAlpha = alpha;
        }

        public void setForegroundColor(int foregroundColor) {
            mForegroundColor = foregroundColor;
        }

        public float getAlpha() {
            return mAlpha;
        }

        @Override
        public int getForegroundColor() {
            return Color.argb(mAlpha,
                    Color.red(mForegroundColor),
                    Color.green(mForegroundColor),
                    Color.blue(mForegroundColor));
        }
    }

}