package com.ethanhua.commonlib.media;

import android.net.Uri;
import android.support.annotation.IntDef;
import android.view.View;

import tv.danmaku.ijk.media.player.IMediaPlayer;

/**
 * Created by ethanhua on 2017/11/7.
 */

public interface IMediaPlayerView {

    // all possible internal states
    int STATE_ERROR = -1;
    int STATE_IDLE = 300;
    int STATE_PREPARING = 301;
    int STATE_PREPARED = 302;
    int STATE_PLAYING = 303;
    int STATE_PAUSED = 304;
    int STATE_PLAYBACK_COMPLETED = 305;
    @IntDef({STATE_ERROR,
            STATE_IDLE,
            STATE_PREPARING,
            STATE_PREPARED,
            STATE_PLAYING,
            STATE_PAUSED,
            STATE_PLAYBACK_COMPLETED})
    @interface State {}

    int RENDER_SURFACE_VIEW = 1;
    int RENDER_TEXTURE_VIEW = 2;
    @IntDef({RENDER_SURFACE_VIEW,
            RENDER_TEXTURE_VIEW})
    @interface RenderViewType {}

    int PLAYER_IJKEXO_MEDIA_PLAYER = 0;
    int PLAYER_ANDROID_MEDIA_PLAYER = 1;
    int PLAYER_IJK_MEDIA_PLAYER = 2;
    @IntDef({PLAYER_IJKEXO_MEDIA_PLAYER,
            PLAYER_ANDROID_MEDIA_PLAYER,
            PLAYER_IJK_MEDIA_PLAYER})
    @interface PlayerType {}

    void setVideoURI(Uri uri);

    int getDuration();

    int getCurrentPosition();

    int getCurrentState();

    boolean isPlaying();

    int getBufferPercentage();

    boolean canPause();

    boolean canSeekBackward();

    boolean canSeekForward();

    /**
     * Get the audio session id for the player used by this VideoView. This can be used to
     * apply audio effects to the audio track of a video.
     *
     * @return The audio session, or 0 if there was an error.
     */
    int getAudioSessionId();

    View getCoverView();

    void start();

    void pause();

    void seekTo(int pos);

    void switchClipStyle(@IRenderView.ClipStyle int clipStyle);

    void switchRenderView(@RenderViewType int renderViewType);

    void switchPlayer(@PlayerType int mediaPlayerType);

    void setOnPreparedListener(IMediaPlayer.OnPreparedListener l);

    void setOnCompletionListener(IMediaPlayer.OnCompletionListener l);

    void setOnErrorListener(IMediaPlayer.OnErrorListener l);

    void setOnInfoListener(IMediaPlayer.OnInfoListener l);

}
