package com.ethanhua.commonlib.media;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.IdRes;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RadioGroup;

import com.ethanhua.commonlib.R;

import static com.ethanhua.commonlib.media.IRenderView.AR_16_9_FIT_PARENT;
import static com.ethanhua.commonlib.media.IRenderView.AR_4_3_FIT_PARENT;
import static com.ethanhua.commonlib.media.IRenderView.AR_FILL_PARENT;
import static com.ethanhua.commonlib.media.IRenderView.AR_FIT_PARENT;
import static com.ethanhua.commonlib.media.IRenderView.ClipStyle;

/**
 * Created by ethanhua on 2017/11/16.
 * <p>
 * 视频画面裁剪比例选择View
 */

public class VideoClipView extends PopupWindow {
    private OnClipStyleSelectedListener mOnClipStyleSelectedListener;

    public VideoClipView(Context context) {
        super(LayoutInflater.from(context).inflate(R.layout.view_video_clip, null),
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                false);
        setOutsideTouchable(true);
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setAnimationStyle(R.style.PopupDownAnim);
        ((RadioGroup) getContentView()
                .findViewById(R.id.aspect_ratio_group))
                .setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                        dismiss();
                        if (mOnClipStyleSelectedListener == null) {
                            return;
                        }
                        if (checkedId == R.id.aspect_fit_parent) {
                            mOnClipStyleSelectedListener.onClipStyleSelected(AR_FIT_PARENT);
                        } else if (checkedId == R.id.aspect_fit_screen) {
                            mOnClipStyleSelectedListener.onClipStyleSelected(AR_FILL_PARENT);
                        } else if (checkedId == R.id.aspect_16_and_9) {
                            mOnClipStyleSelectedListener.onClipStyleSelected(AR_16_9_FIT_PARENT);
                        } else if (checkedId == R.id.aspect_4_and_3) {
                            mOnClipStyleSelectedListener.onClipStyleSelected(AR_4_3_FIT_PARENT);
                        }
                    }
                });
    }

    public interface OnClipStyleSelectedListener {
        void onClipStyleSelected(@ClipStyle int clipStyle);
    }

    public void setOnClipStyleSelectedListener(OnClipStyleSelectedListener clipStyleSelectedListener) {
        mOnClipStyleSelectedListener = clipStyleSelectedListener;
    }
}
