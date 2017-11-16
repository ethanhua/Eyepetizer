package com.ethanhua.commonlib.media;

/**
 * Created by ethanhua on 2017/11/7.
 */

public interface IMediaController {

    void setEnabled(boolean enabled);

    void setMediaPlayerView(IMediaPlayerView playerView);

    void show(int timeout);

    void show();

    void hide();

    boolean isShowing();

}
