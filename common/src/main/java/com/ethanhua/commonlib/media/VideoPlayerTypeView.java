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

/**
 * Created by ethanhua on 2017/11/17.
 */

public class VideoPlayerTypeView extends PopupWindow {
    private OnPlayerTypeSelectedListener mPlayerTypeSelectedListener;

    public VideoPlayerTypeView(Context context) {
        super(LayoutInflater.from(context).inflate(R.layout.view_player_type, null),
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                false);
        setOutsideTouchable(true);
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setAnimationStyle(R.style.PopupDownAnim);
        ((RadioGroup) getContentView()
                .findViewById(R.id.player_type_group))
                .setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                        dismiss();
                        if (mPlayerTypeSelectedListener == null) {
                            return;
                        }
                        if (checkedId == R.id.btn_bili) {
                            mPlayerTypeSelectedListener.onPlayerTypeSelected(
                                    MediaPlayerView.PLAYER_IJK_MEDIA_PLAYER);
                        } else if (checkedId == R.id.btn_exo) {
                            mPlayerTypeSelectedListener.onPlayerTypeSelected(
                                    MediaPlayerView.PLAYER_IJKEXO_MEDIA_PLAYER
                            );
                        } else if (checkedId == R.id.btn_android) {
                            mPlayerTypeSelectedListener.onPlayerTypeSelected(
                                    MediaPlayerView.PLAYER_ANDROID_MEDIA_PLAYER
                            );
                        }
                    }
                });
    }

    public interface OnPlayerTypeSelectedListener {

        void onPlayerTypeSelected(@MediaPlayerView.PlayerType int playerType);
    }

    public void setPlayerTypeSelectedListener(OnPlayerTypeSelectedListener playerTypeSelectedListener) {
        this.mPlayerTypeSelectedListener = playerTypeSelectedListener;
    }
}
