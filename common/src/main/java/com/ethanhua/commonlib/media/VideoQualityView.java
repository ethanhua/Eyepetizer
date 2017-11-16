package com.ethanhua.commonlib.media;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.IdRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.CompoundButtonCompat;
import android.support.v7.widget.AppCompatRadioButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RadioGroup;

import com.ethanhua.commonlib.R;

/**
 * Created by ethanhua on 2017/11/16.
 *
 * 视频清晰度选择View
 */

public class VideoQualityView extends PopupWindow {
    public AppCompatRadioButton rbBd;
    public AppCompatRadioButton rbSupper;
    public AppCompatRadioButton rbHigh;
    public AppCompatRadioButton rbNormal;
    public AppCompatRadioButton rbLow;
    private OnVideoSourceSelectedListener mSourceSelectedListener;
    private int mHeight;
    private int mWidth;

    public VideoQualityView(Context context) {
        super(LayoutInflater.from(context).inflate(R.layout.view_video_quality, null),
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                false);
        setOutsideTouchable(true);
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setAnimationStyle(R.style.PopupUpAnim);
        ((RadioGroup) getContentView()
                .findViewById(R.id.quality_ratio_group))
                .setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                        dismiss();
                        if (mSourceSelectedListener == null) {
                            return;
                        }
                        if (checkedId == R.id.bd) {
                            mSourceSelectedListener.onVideoSourceSelected(
                                    VideoUrlSource.MEDIA_QUALITY_BD);
                            return;
                        }
                        if (checkedId == R.id.supperHigh) {
                            mSourceSelectedListener.onVideoSourceSelected(
                                    VideoUrlSource.MEDIA_QUALITY_SUPER);
                            return;
                        }
                        if (checkedId == R.id.high) {
                            mSourceSelectedListener.onVideoSourceSelected(
                                    VideoUrlSource.MEDIA_QUALITY_HIGH
                            );
                            return;
                        }
                        if (checkedId == R.id.normal) {
                            mSourceSelectedListener.onVideoSourceSelected(
                                    VideoUrlSource.MEDIA_QUALITY_NORMAL
                            );
                            return;
                        }
                        if (checkedId == R.id.low) {
                            mSourceSelectedListener.onVideoSourceSelected(
                                    VideoUrlSource.MEDIA_QUALITY_LOW);
                            return;
                        }
                    }
                });
        rbBd = getContentView().findViewById(R.id.bd);
        rbSupper = getContentView().findViewById(R.id.supperHigh);
        rbHigh = getContentView().findViewById(R.id.high);
        rbNormal = getContentView().findViewById(R.id.normal);
        rbLow = getContentView().findViewById(R.id.low);

        CompoundButtonCompat.setButtonTintList(rbBd, ContextCompat.getColorStateList(rbBd.getContext(), R.color.color_red_tint));
        CompoundButtonCompat.setButtonTintList(rbSupper, ContextCompat.getColorStateList(rbSupper.getContext(), R.color.color_red_tint));
        CompoundButtonCompat.setButtonTintList(rbHigh, ContextCompat.getColorStateList(rbHigh.getContext(), R.color.color_red_tint));
        CompoundButtonCompat.setButtonTintList(rbNormal, ContextCompat.getColorStateList(rbNormal.getContext(), R.color.color_red_tint));
        CompoundButtonCompat.setButtonTintList(rbLow, ContextCompat.getColorStateList(rbLow.getContext(), R.color.color_red_tint));
    }

    public interface OnVideoSourceSelectedListener {

        void onVideoSourceSelected(@VideoUrlSource.QualityType int qualityType);
    }

    public void setOnVideoSourceSelectedListener(OnVideoSourceSelectedListener sourceSelectedListener) {
        this.mSourceSelectedListener = sourceSelectedListener;
    }

    private void measureSize() {
        getContentView().measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        mHeight = getContentView().getMeasuredHeight();
        mWidth = getContentView().getMeasuredWidth();
    }

    public void showAtTop(View view, int xoff, int yoff) {
        measureSize();
        showAsDropDown(view, xoff - mWidth / 2 + view.getWidth() / 2, -(mHeight + view.getHeight() + yoff));
    }
}
