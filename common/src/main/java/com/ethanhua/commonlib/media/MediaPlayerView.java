package com.ethanhua.commonlib.media;

import android.annotation.TargetApi;
import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION_CODES;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ethanhua.commonlib.R;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;

import tv.danmaku.ijk.media.exo.IjkExoMediaPlayer;
import tv.danmaku.ijk.media.player.AndroidMediaPlayer;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import tv.danmaku.ijk.media.player.IjkTimedText;
import tv.danmaku.ijk.media.player.misc.IMediaDataSource;

import static com.ethanhua.commonlib.media.IRenderView.ClipStyle;

/**
 * Created by ethanhua on 2017/11/7.
 */

public class MediaPlayerView extends FrameLayout implements IMediaPlayerView, LifecycleObserver {

    private String TAG = MediaPlayerView.class.getSimpleName();
    private Uri mUri = null;
    private Map<String, String> mHeaders;
    // mCurrentState is a VideoView object's current state.
    // mTargetState is the state that a method caller intends to reach.
    // For instance, regardless the VideoView object's current state,
    // calling pause() intends to bring the object to a target state
    // of STATE_PAUSED.
    private int mCurrentState = STATE_IDLE;
    private int mTargetState = STATE_IDLE;

    private int mVideoWidth;
    private int mVideoHeight;
    private int mSurfaceWidth;
    private int mSurfaceHeight;
    private int mVideoRotationDegree;
    private String originDimensionRatio = "";
    private int originHeight;
    private int originScreenPixels;
    private IMediaController mMediaControllerView;
    private IMediaPlayer.OnPreparedListener mOnPreparedListener;
    private IMediaPlayer.OnErrorListener mOnErrorListener;
    private IMediaPlayer.OnInfoListener mOnInfoListener;
    private IMediaPlayer.OnCompletionListener mOnCompletionListener;
    private int mCurrentBufferPercentage;
    private int mSeekWhenPrepared;
    private boolean mCanPause = true;
    private boolean mCanSeekBack = true;
    private boolean mCanSeekForward = true;


    private Activity mAttachActivity;
    private int mRenderViewType = RENDER_TEXTURE_VIEW;
    private IRenderView mRenderView;
    private int mClipStyle;//视频裁剪样式

    private IRenderView.ISurfaceHolder mSurfaceHolder = null;
    private int mMediaPlayerType = PLAYER_IJK_MEDIA_PLAYER;
    private IMediaPlayer mMediaPlayer;
    private int mVideoSarNum;
    private int mVideoSarDen;
    private View mCoverView;
    private ProgressBar mLoadingView;

    /**
     * IjkPlayer config
     */
    private boolean mIsUsingMediaCodec;//是否使用硬解码
    private boolean mIsUsingMediaCodecAutoRotate = true;
    private boolean mIsMediaCodecHandleResolutionChange = true;
    private boolean mIsUsingOpenSLES;
    private boolean mIsUsingMediaDataSource;
    private String mPixelFormat = "";

    public MediaPlayerView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public MediaPlayerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MediaPlayerView(@NonNull Context context,
                           @Nullable AttributeSet attrs,
                           @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(VERSION_CODES.LOLLIPOP)
    public MediaPlayerView(@NonNull Context context,
                           @Nullable AttributeSet attrs,
                           @AttrRes int defStyleAttr,
                           @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        if (context instanceof AppCompatActivity) {
            mAttachActivity = (AppCompatActivity) context;
        } else {
            throw new IllegalArgumentException("Context must be AppCompatActivity");
        }
        setCoverView();
        setLoadingView();
        setFocusable(true);
        setFocusableInTouchMode(true);
        requestFocus();
    }

    private void setLoadingView() {
        mLoadingView = new ProgressBar(getContext(), null, android.R.attr.progressBarStyle);
        LayoutParams layoutParam = new LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT,
                Gravity.CENTER);
        mLoadingView.getIndeterminateDrawable().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
        addView(mLoadingView, layoutParam);
    }

    private void setCoverView() {
        mCoverView = new View(getContext());
        addView(mCoverView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
    }

    private void setRenderView(IRenderView renderView) {
        if (mRenderView != null) {
            if (mMediaPlayer != null) {
                mMediaPlayer.setDisplay(null);
            }
            View renderUIView = mRenderView.getView();
            mRenderView.removeRenderCallback(mRenderCallback);
            mRenderView = null;
            removeView(renderUIView);
        }
        if (renderView == null) {
            return;
        }
        mRenderView = renderView;
        renderView.setAspectRatio(mClipStyle);
        if (mVideoWidth > 0 && mVideoHeight > 0) {
            renderView.setVideoSize(mVideoWidth, mVideoHeight);
        }
        if (mVideoSarNum > 0 && mVideoSarDen > 0) {
            renderView.setVideoSampleAspectRatio(mVideoSarNum, mVideoSarDen);
        }
        View renderUIView = mRenderView.getView();
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT,
                Gravity.CENTER);
        renderUIView.setLayoutParams(lp);
        addView(renderUIView, 0);
        mRenderView.addRenderCallback(mRenderCallback);
        mRenderView.setVideoRotation(mVideoRotationDegree);
    }

    private IRenderView createRenderView(@RenderViewType int renderViewType) {
        IRenderView renderView = null;
        switch (renderViewType) {
            case RENDER_SURFACE_VIEW: {
                renderView = new SurfaceRenderView(getContext());
                break;
            }
            case RENDER_TEXTURE_VIEW: {
                renderView = new TextureRenderView(getContext());
                if (mMediaPlayer != null) {
                    ((TextureRenderView) renderView).getSurfaceHolder().bindToMediaPlayer(mMediaPlayer);
                    renderView.setVideoSize(mMediaPlayer.getVideoWidth(),
                            mMediaPlayer.getVideoHeight());
                    renderView.setVideoSampleAspectRatio(mMediaPlayer.getVideoSarNum(),
                            mMediaPlayer.getVideoSarDen());
                    renderView.setAspectRatio(mClipStyle);
                }
                break;
            }
            default:
                Log.d(TAG, String.format(Locale.getDefault(), "invalid render %d\n", renderViewType));
                break;
        }
        return renderView;
    }


    /**
     * ************************* MediaPlayer listener **********************
     */

    private IMediaPlayer.OnPreparedListener mPreparedListener =
            new IMediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(IMediaPlayer iMediaPlayer) {
                    Log.d(TAG, "media player on prepared");
                    mCurrentState = STATE_PREPARED;
                    notifyStateChange();
                    if (mOnPreparedListener != null) {
                        mOnPreparedListener.onPrepared(mMediaPlayer);
                    }
                    if (mMediaControllerView != null) {
                        mMediaControllerView.setEnabled(true);
                    }
                    mapVideoSize(iMediaPlayer);
                    // mSeekWhenPrepared may be changed after seekTo() call
                    int seekToPosition = mSeekWhenPrepared;
                    if (seekToPosition != 0) {
                        seekTo(seekToPosition);
                    }
                    if (mVideoWidth != 0 && mVideoHeight != 0 && mRenderView != null) {
                        mRenderView.setVideoSize(mVideoWidth, mVideoHeight);
                        mRenderView.setVideoSampleAspectRatio(mVideoSarNum, mVideoSarDen);
                        if (!mRenderView.shouldWaitForResize()
                                || mSurfaceWidth == mVideoWidth && mSurfaceHeight == mVideoHeight) {
                            // We didn't actually change the size (it was already at the size
                            // we need), so we won't get a "surface changed" callback, so
                            // start the video here instead of in the callback.
                            if (mTargetState == STATE_PLAYING) {
                                start();
                            } else if (!isPlaying() &&
                                    (seekToPosition != 0 || getCurrentPosition() > 0)) {
                                if (mMediaControllerView != null) {
                                    // Show the media controls when we're paused into a video and make 'em stick
                                    mMediaControllerView.show(0);
                                }
                            }
                        }
                    } else {
                        // We don't know the video size yet, but should start anyway.
                        // The video size might be reported to us later.
                        if (mTargetState == STATE_PLAYING) {
                            start();
                        }
                    }
                }
            };

    private IMediaPlayer.OnVideoSizeChangedListener mVideoSizeChangedListener =
            new IMediaPlayer.OnVideoSizeChangedListener() {
                @Override
                public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i, int i1, int i2, int i3) {
                    Log.d(TAG, "media player on videoSize changed:" + i + "-" + i1 + "-" + i2 + "-" + i3);
                    mapVideoSize(iMediaPlayer);
                    if (mVideoWidth != 0 && mVideoHeight != 0) {
                        if (mRenderView != null) {
                            mRenderView.setVideoSize(mVideoWidth, mVideoHeight);
                            mRenderView.setVideoSampleAspectRatio(mVideoSarNum, mVideoSarDen);
                        }
                        if (mLoadingView != null) {
                            Log.d(TAG, "dismiss loading");
                            mLoadingView.setVisibility(GONE);
                        }
                        if (mCoverView != null) {
                            mCoverView.setBackground(null);
                        }
                        requestLayout();
                    }
                }
            };

    private IMediaPlayer.OnInfoListener mInfoListener =
            new IMediaPlayer.OnInfoListener() {
                public boolean onInfo(IMediaPlayer mp, int arg1, int arg2) {
                    if (mOnInfoListener != null) {
                        mOnInfoListener.onInfo(mp, arg1, arg2);
                    }
                    switch (arg1) {
                        case IMediaPlayer.MEDIA_INFO_VIDEO_TRACK_LAGGING:
                            Log.d(TAG, "MEDIA_INFO_VIDEO_TRACK_LAGGING:");
                            break;
                        case IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START:
                            Log.d(TAG, "MEDIA_INFO_VIDEO_RENDERING_START:");
                            break;
                        case IMediaPlayer.MEDIA_INFO_BUFFERING_START:
                            Log.d(TAG, "MEDIA_INFO_BUFFERING_START:");
                            if (mLoadingView != null) {
                                mLoadingView.setVisibility(VISIBLE);
                            }
                            break;
                        case IMediaPlayer.MEDIA_INFO_BUFFERING_END:
                            Log.d(TAG, "MEDIA_INFO_BUFFERING_END:");
                            if (mLoadingView != null) {
                                mLoadingView.setVisibility(GONE);
                            }
                            break;
                        case IMediaPlayer.MEDIA_INFO_NETWORK_BANDWIDTH:
                            Log.d(TAG, "MEDIA_INFO_NETWORK_BANDWIDTH: " + arg2);
                            break;
                        case IMediaPlayer.MEDIA_INFO_BAD_INTERLEAVING:
                            Log.d(TAG, "MEDIA_INFO_BAD_INTERLEAVING:");
                            break;
                        case IMediaPlayer.MEDIA_INFO_NOT_SEEKABLE:
                            Log.d(TAG, "MEDIA_INFO_NOT_SEEKABLE:");
                            break;
                        case IMediaPlayer.MEDIA_INFO_METADATA_UPDATE:
                            Log.d(TAG, "MEDIA_INFO_METADATA_UPDATE:");
                            break;
                        case IMediaPlayer.MEDIA_INFO_UNSUPPORTED_SUBTITLE:
                            Log.d(TAG, "MEDIA_INFO_UNSUPPORTED_SUBTITLE:");
                            break;
                        case IMediaPlayer.MEDIA_INFO_SUBTITLE_TIMED_OUT:
                            Log.d(TAG, "MEDIA_INFO_SUBTITLE_TIMED_OUT:");
                            break;
                        case IMediaPlayer.MEDIA_INFO_VIDEO_ROTATION_CHANGED:
                            mVideoRotationDegree = arg2;
                            Log.d(TAG, "MEDIA_INFO_VIDEO_ROTATION_CHANGED: " + arg2);
                            if (mRenderView != null) {
                                mRenderView.setVideoRotation(arg2);
                            }
                            break;
                        case IMediaPlayer.MEDIA_INFO_AUDIO_RENDERING_START:
                            Log.d(TAG, "MEDIA_INFO_AUDIO_RENDERING_START:");
                            break;
                    }
                    return true;
                }
            };

    private IMediaPlayer.OnErrorListener mErrorListener =
            new IMediaPlayer.OnErrorListener() {
                public boolean onError(IMediaPlayer mp, int framework_err, int impl_err) {
                    Log.d(TAG, "Error: " + framework_err + "," + impl_err);
                    mCurrentState = STATE_ERROR;
                    mTargetState = STATE_ERROR;
                    notifyStateChange();
                    if (mMediaControllerView != null) {
                        mMediaControllerView.hide();
                    }

                    /* If an error handler has been supplied, use it and finish. */
                    if (mOnErrorListener != null) {
                        if (mOnErrorListener.onError(mMediaPlayer, framework_err, impl_err)) {
                            return true;
                        }
                    }

                    /* Otherwise, pop up an error dialog so the user knows that
                     * something bad has happened. Only try and pop up the dialog
                     * if we're attached to a window. When we're going away and no
                     * longer have a window, don't bother showing the user an error.
                     */
                    if (getWindowToken() != null) {
                        int messageId;
                        if (framework_err == MediaPlayer.MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK) {
                            messageId = R.string.VideoView_error_text_invalid_progressive_playback;
                        } else {
                            messageId = R.string.VideoView_error_text_unknown;
                        }
                        Toast.makeText(mAttachActivity,
                                getResources().getString(messageId),
                                Toast.LENGTH_LONG)
                                .show();
                        if (mOnCompletionListener != null) {
                            mOnCompletionListener.onCompletion(mMediaPlayer);
                        }
                    }
                    return true;
                }
            };

    private IMediaPlayer.OnBufferingUpdateListener mBufferingUpdateListener =
            new IMediaPlayer.OnBufferingUpdateListener() {
                public void onBufferingUpdate(IMediaPlayer mp, int percent) {
                    // Log.d(TAG, "on buffering update:" + percent);
                    mCurrentBufferPercentage = percent;
                }
            };

    private IMediaPlayer.OnSeekCompleteListener mSeekCompleteListener = new IMediaPlayer.OnSeekCompleteListener() {

        @Override
        public void onSeekComplete(IMediaPlayer mp) {
            Log.d(TAG, "on seek complete");
            if (mLoadingView != null) {
                mLoadingView.setVisibility(GONE);
            }
            if (mCoverView != null) {
                mCoverView.setBackground(null);
            }
        }
    };

    private IMediaPlayer.OnTimedTextListener mOnTimedTextListener = new IMediaPlayer.OnTimedTextListener() {
        @Override
        public void onTimedText(IMediaPlayer mp, IjkTimedText text) {
            Log.d(TAG, "onTimedText:" + mp.getCurrentPosition());

            if (text != null) {
                Log.d(TAG, "onTimedText:" + text.getText());
            }
        }
    };

    private IMediaPlayer.OnCompletionListener mCompletionListener =
            new IMediaPlayer.OnCompletionListener() {
                public void onCompletion(IMediaPlayer mp) {
                    mCurrentState = STATE_PLAYBACK_COMPLETED;
                    mTargetState = STATE_PLAYBACK_COMPLETED;
                    notifyStateChange();
                    if (mMediaControllerView != null) {
                        mMediaControllerView.hide();
                    }
                    if (mOnCompletionListener != null) {
                        mOnCompletionListener.onCompletion(mMediaPlayer);
                    }
                }
            };

    private IRenderView.IRenderCallback mRenderCallback = new IRenderView.IRenderCallback() {
        @Override
        public void onSurfaceChanged(@NonNull IRenderView.ISurfaceHolder holder,
                                     int format,
                                     int w,
                                     int h) {
            if (holder.getRenderView() != mRenderView) {
                Log.d(TAG, "onSurfaceChanged: unmatched render callback\n");
                return;
            }

            mSurfaceWidth = w;
            mSurfaceHeight = h;
            boolean isValidState = (mTargetState == STATE_PLAYING);
            boolean hasValidSize = !mRenderView.shouldWaitForResize()
                    || (mVideoWidth == w && mVideoHeight == h);
            if (mMediaPlayer != null && isValidState && hasValidSize) {
                if (mSeekWhenPrepared != 0) {
                    seekTo(mSeekWhenPrepared);
                }
                start();
            }
        }

        @Override
        public void onSurfaceCreated(@NonNull IRenderView.ISurfaceHolder holder,
                                     int width,
                                     int height) {
            if (holder.getRenderView() != mRenderView) {
                Log.d(TAG, "onSurfaceCreated: unmatched render callback\n");
                return;
            }
            mSurfaceHolder = holder;
            if (mMediaPlayer != null) {
                bindSurfaceHolder(mMediaPlayer, holder);
            } else {
                openVideo();
            }
        }

        @Override
        public void onSurfaceDestroyed(@NonNull IRenderView.ISurfaceHolder holder) {
            if (holder.getRenderView() != mRenderView) {
                Log.d(TAG, "onSurfaceDestroyed: unmatched render callback\n");
                return;
            }

            // after we return from this we can't use the surface any more
            mSurfaceHolder = null;
            if (mMediaPlayer != null) {
                mMediaPlayer.setDisplay(null);
            }
        }
    };

    private void bindSurfaceHolder(IMediaPlayer mediaPlayer,
                                   IRenderView.ISurfaceHolder holder) {
        if (mediaPlayer == null) {
            return;
        }
        if (holder == null) {
            mediaPlayer.setDisplay(null);
            return;
        }
        holder.bindToMediaPlayer(mediaPlayer);
    }

    private void mapVideoSize(IMediaPlayer iMediaPlayer) {
        mVideoWidth = iMediaPlayer.getVideoWidth();
        mVideoHeight = iMediaPlayer.getVideoHeight();
        mVideoSarNum = iMediaPlayer.getVideoSarNum();
        mVideoSarDen = iMediaPlayer.getVideoSarDen();
    }

    private void openVideo() {
        if (mUri == null || mSurfaceHolder == null) {
            // not ready for playback just yet, will try again later
            return;
        }
        // we shouldn't clear the target state, because somebody might have
        // called start() previously
        release(false);

        AudioManager am = (AudioManager) mAttachActivity.getSystemService(Context.AUDIO_SERVICE);
        am.requestAudioFocus(null, AudioManager.STREAM_MUSIC,
                AudioManager.AUDIOFOCUS_GAIN);

        try {
            mMediaPlayer = createPlayer(mMediaPlayerType);

            mMediaPlayer.setOnPreparedListener(mPreparedListener);
            mMediaPlayer.setOnVideoSizeChangedListener(mVideoSizeChangedListener);
            mMediaPlayer.setOnCompletionListener(mCompletionListener);
            mMediaPlayer.setOnErrorListener(mErrorListener);
            mMediaPlayer.setOnInfoListener(mInfoListener);
            mMediaPlayer.setOnBufferingUpdateListener(mBufferingUpdateListener);
            mMediaPlayer.setOnSeekCompleteListener(mSeekCompleteListener);
            mMediaPlayer.setOnTimedTextListener(mOnTimedTextListener);
            mCurrentBufferPercentage = 0;
            setPlayerDataSource();
            bindSurfaceHolder(mMediaPlayer, mSurfaceHolder);
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mMediaPlayer.setScreenOnWhilePlaying(true);
            mMediaPlayer.prepareAsync();

            // we don't set the target state here either, but preserve the
            // target state that was there before.
            mCurrentState = STATE_PREPARING;
            notifyStateChange();
            attachMediaController();
        } catch (IllegalArgumentException ex) {
            Log.w(TAG, "Unable to open content: " + mUri, ex);
            mCurrentState = STATE_ERROR;
            mTargetState = STATE_ERROR;
            notifyStateChange();
            mErrorListener.onError(mMediaPlayer, MediaPlayer.MEDIA_ERROR_UNKNOWN, 0);
        } finally {

        }
    }

    private void setPlayerDataSource() {
        String scheme = mUri.getScheme();
        try {
            if (Build.VERSION.SDK_INT >= VERSION_CODES.M
                    && mIsUsingMediaDataSource
                    && (TextUtils.isEmpty(scheme)
                    || scheme.equalsIgnoreCase("file"))) {
                IMediaDataSource dataSource = new FileMediaDataSource(
                        new File(mUri.toString()));
                mMediaPlayer.setDataSource(dataSource);
            } else if (Build.VERSION.SDK_INT >= VERSION_CODES.ICE_CREAM_SANDWICH) {
                mMediaPlayer.setDataSource(mAttachActivity, mUri, mHeaders);
            } else {
                mMediaPlayer.setDataSource(mUri.toString());
            }
        } catch (IOException ex) {
            Log.w(TAG, "Unable to open content: " + mUri, ex);
            mCurrentState = STATE_ERROR;
            mTargetState = STATE_ERROR;
            notifyStateChange();
            mErrorListener.onError(mMediaPlayer, MediaPlayer.MEDIA_ERROR_UNKNOWN, 0);
        }
    }

    private void attachMediaController() {
        if (mMediaPlayer != null && mMediaControllerView != null) {
            mMediaControllerView.setMediaPlayerView(this);
            mMediaControllerView.setEnabled(isInPlaybackState());
        }
    }

    private boolean isInPlaybackState() {
        return (mMediaPlayer != null &&
                mCurrentState != STATE_ERROR &&
                mCurrentState != STATE_IDLE &&
                mCurrentState != STATE_PREPARING);
    }

    @Override
    public boolean onTrackballEvent(MotionEvent ev) {
        if (isInPlaybackState() && mMediaControllerView != null) {
            switchControllerViewVisible();
        }
        return false;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (originHeight == 0) {
            originHeight = bottom - top;
            originScreenPixels = getResources().getDisplayMetrics().widthPixels;
            if (getLayoutParams() != null
                    && getLayoutParams() instanceof ConstraintLayout.LayoutParams) {
                originDimensionRatio = ((ConstraintLayout.LayoutParams)
                        getLayoutParams()).dimensionRatio;
            }
        }
    }

    /**
     * 全屏切换时调整宽高尺寸
     *
     * @param isFullscreen 是否是全屏
     */
    private void updateLayoutParams(boolean isFullscreen) {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (isFullscreen) {
            //高度扩展为横向全屏
            layoutParams.height = originScreenPixels;
            //取消宽高比约束
            if (layoutParams instanceof ConstraintLayout.LayoutParams) {
                ((ConstraintLayout.LayoutParams) layoutParams).dimensionRatio = "";
            }
        } else {
            // 还原高度
            layoutParams.height = originHeight;
            // 还原宽高比约束
            if (layoutParams instanceof ConstraintLayout.LayoutParams) {
                ((ConstraintLayout.LayoutParams) layoutParams).dimensionRatio = originDimensionRatio;
            }
        }
        Log.d(TAG, isFullscreen + "-" + layoutParams.height);
        setLayoutParams(layoutParams);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        boolean isKeyCodeSupported = keyCode != KeyEvent.KEYCODE_BACK &&
                keyCode != KeyEvent.KEYCODE_VOLUME_UP &&
                keyCode != KeyEvent.KEYCODE_VOLUME_DOWN &&
                keyCode != KeyEvent.KEYCODE_VOLUME_MUTE &&
                keyCode != KeyEvent.KEYCODE_MENU &&
                keyCode != KeyEvent.KEYCODE_CALL &&
                keyCode != KeyEvent.KEYCODE_ENDCALL;
        if (isInPlaybackState() && isKeyCodeSupported && mMediaControllerView != null) {
            if (keyCode == KeyEvent.KEYCODE_HEADSETHOOK ||
                    keyCode == KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE) {
                if (mMediaPlayer.isPlaying()) {
                    pause();
                    mMediaControllerView.show();
                } else {
                    start();
                    mMediaControllerView.hide();
                }
                return true;
            } else if (keyCode == KeyEvent.KEYCODE_MEDIA_PLAY) {
                if (!mMediaPlayer.isPlaying()) {
                    start();
                    mMediaControllerView.hide();
                }
                return true;
            } else if (keyCode == KeyEvent.KEYCODE_MEDIA_STOP
                    || keyCode == KeyEvent.KEYCODE_MEDIA_PAUSE) {
                if (mMediaPlayer.isPlaying()) {
                    pause();
                    mMediaControllerView.show();
                }
                return true;
            } else {
                switchControllerViewVisible();
            }
        }
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (Utils.isFullScreen(mAttachActivity)) {
                Utils.switchFullScreen(mAttachActivity, false);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private void switchControllerViewVisible() {
        if (mMediaControllerView.isShowing()) {
            mMediaControllerView.hide();
        } else {
            mMediaControllerView.show();
        }
    }

    private IMediaPlayer createPlayer(@PlayerType int playerType) {
        IMediaPlayer mediaPlayer = null;
        switch (playerType) {
            case PLAYER_IJKEXO_MEDIA_PLAYER:
                IjkExoMediaPlayer IjkExoMediaPlayer = new IjkExoMediaPlayer(mAttachActivity);
                mediaPlayer = IjkExoMediaPlayer;
                break;
            case PLAYER_ANDROID_MEDIA_PLAYER:
                AndroidMediaPlayer androidMediaPlayer = new AndroidMediaPlayer();
                mediaPlayer = androidMediaPlayer;
                break;
            case PLAYER_IJK_MEDIA_PLAYER:
            default:
                mediaPlayer = createIjkPlayer();
                break;
        }
        return mediaPlayer;
    }

    private IjkMediaPlayer createIjkPlayer() {
        if (mUri == null) {
            return null;
        }
        IjkMediaPlayer ijkMediaPlayer = new IjkMediaPlayer();
        ijkMediaPlayer.native_setLogLevel(IjkMediaPlayer.IJK_LOG_DEBUG);
        if (mIsUsingMediaCodec) {
            ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER,
                    "mediacodec", 1);
            if (mIsUsingMediaCodecAutoRotate) {
                ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER,
                        "mediacodec-auto-rotate", 1);
            } else {
                ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER,
                        "mediacodec-auto-rotate", 0);
            }
            if (mIsMediaCodecHandleResolutionChange) {
                ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER,
                        "mediacodec-handle-resolution-change", 1);
            } else {
                ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER,
                        "mediacodec-handle-resolution-change", 0);
            }
        } else {
            ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER,
                    "mediacodec", 0);
        }

        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER,
                "opensles",
                mIsUsingOpenSLES ? 1 : 0);
        if (TextUtils.isEmpty(mPixelFormat)) {
            ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER,
                    "overlay-format", IjkMediaPlayer.SDL_FCC_RV32);
        } else {
            ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER,
                    "overlay-format", mPixelFormat);
        }
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER,
                "framedrop", 1);
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER,
                "start-on-prepared", 0);

        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT,
                "http-detect-range-support", 0);

        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_CODEC,
                "skip_loop_filter", 48);
        return ijkMediaPlayer;
    }

    /**
     * Sets video URI using specific headers.
     *
     * @param uri     the URI of the video.
     * @param headers the headers for the URI request.
     *                Note that the cross domain redirection is allowed by default, but that can be
     *                changed with key/value pairs through the headers parameter with
     *                "android-allow-cross-domain-redirect" as the key and "0" or "1" as the value
     *                to disallow or allow cross domain redirection.
     */
    private void setVideoURI(Uri uri, Map<String, String> headers) {
        if (mUri == uri) {
            if (mLoadingView != null) {
                mLoadingView.setVisibility(GONE);
            }
            return;
        }
        mUri = uri;
        mHeaders = headers;
        mSeekWhenPrepared = 0;
        setRenderView(createRenderView(mRenderViewType));
        if (mMediaPlayer != null) {
            mMediaPlayer.reset();
            setPlayerDataSource();
            mCurrentState = STATE_PREPARING;
            mMediaPlayer.prepareAsync();
            notifyStateChange();
        } else {
            openVideo();
        }
    }

    private void notifyStateChange() {
        if (mInfoListener != null) {
            mInfoListener.onInfo(mMediaPlayer, mCurrentState, -1);
        }
    }


    /**
     * ============================ 暴露给外面的接口 ===========================================
     */

    public void onConfigurationChanged(Configuration configuration) {
        Log.d(TAG, "fullscreen switch");
        if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Utils.showActionBar(getContext(), false);
            Utils.switchFullScreenFlag(mAttachActivity, true);
            updateLayoutParams(true);
        } else if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Utils.showActionBar(getContext(), true);
            Utils.switchFullScreenFlag(mAttachActivity, false);
            updateLayoutParams(false);
        }
        if (mMediaControllerView != null) {
            mMediaControllerView.hide();
        }

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    @Override
    public void start() {
        Log.d(TAG, "start");
        if (isInPlaybackState()) {
            mMediaPlayer.start();
            mCurrentState = STATE_PLAYING;
            notifyStateChange();
        }
        mTargetState = STATE_PLAYING;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    @Override
    public void pause() {
        Log.d(TAG, "pause");
        if (isInPlaybackState()) {
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.pause();
                mCurrentState = STATE_PAUSED;
                notifyStateChange();
            }
        }
        mTargetState = STATE_PAUSED;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void destroy() {
        Log.d(TAG, "destroy");
        release(true);
        try {
            IjkMediaPlayer.native_profileEnd();
        } catch (Error error) {
            error.printStackTrace();
        }

    }

    @Override
    public int getDuration() {
        if (isInPlaybackState()) {
            return (int) mMediaPlayer.getDuration();
        }
        return -1;
    }

    @Override
    public int getCurrentPosition() {
        if (isInPlaybackState()) {
            return (int) mMediaPlayer.getCurrentPosition();
        }
        return 0;
    }

    @Override
    public int getCurrentState() {
        return mCurrentState;
    }

    @Override
    public void seekTo(int pos) {
        if (mLoadingView != null) {
            mLoadingView.setVisibility(VISIBLE);
        }
        if (isInPlaybackState()) {
            mMediaPlayer.seekTo(pos);
            mSeekWhenPrepared = 0;
        } else {
            mSeekWhenPrepared = pos;
        }
    }

    @Override
    public boolean isPlaying() {
        return isInPlaybackState() && mMediaPlayer.isPlaying();
    }

    @Override
    public int getBufferPercentage() {
        if (mMediaPlayer != null) {
            return mCurrentBufferPercentage;
        }
        return 0;
    }

    @Override
    public boolean canPause() {
        return mCanPause;
    }

    @Override
    public boolean canSeekBackward() {
        return mCanSeekBack;
    }

    @Override
    public boolean canSeekForward() {
        return mCanSeekForward;
    }

    @Override
    public int getAudioSessionId() {
        if (mMediaPlayer != null) {
            return mMediaPlayer.getAudioSessionId();
        }
        return 0;
    }

    @Override
    public View getCoverView() {
        return mCoverView;
    }

    @Override
    public void switchClipStyle(@ClipStyle int clipStyle) {
        if (clipStyle == mClipStyle) {
            return;
        }
        mClipStyle = clipStyle;
        if (mRenderView != null) {
            mRenderView.setAspectRatio(mClipStyle);
        }
    }

    @Override
    public void switchRenderView(@RenderViewType int renderViewType) {
        if (renderViewType == mRenderViewType) {
            return;
        }
        mRenderViewType = renderViewType;
        setRenderView(createRenderView(mRenderViewType));
    }

    @Override
    public void switchPlayer(@PlayerType int mediaPlayerType) {
        if (mMediaPlayerType == mediaPlayerType) {
            return;
        }
        int currentPosition = getCurrentPosition();
        release(true);
        setRenderView(createRenderView(mRenderViewType));
        openVideo();
        start();
        //定位到当前播放位置
        seekTo(currentPosition);
    }

    /**
     * release the media player in any state
     *
     * @param clearTargetState 是否清除目标状态
     */
    public void release(boolean clearTargetState) {
        if (mMediaPlayer != null) {
            mMediaPlayer.reset();
            mMediaPlayer.release();
            mMediaPlayer = null;
            // REMOVED: mPendingSubtitleTracks.clear();
            mCurrentState = STATE_IDLE;
            notifyStateChange();
            if (clearTargetState) {
                mTargetState = STATE_IDLE;
            }
            AudioManager am = (AudioManager) mAttachActivity.getSystemService(
                    Context.AUDIO_SERVICE);
            am.abandonAudioFocus(null);
        }
    }

    /**
     * Sets video path.
     *
     * @param path the path of the video.
     */
    public void setVideoPath(String path) {
        setVideoURI(Uri.parse(path));
    }

    /**
     * Sets video URI.
     *
     * @param uri the URI of the video.
     */
    @Override
    public void setVideoURI(Uri uri) {
        Log.d(TAG, "set Video uri:" + (uri == null ? "null" : uri.toString()));
        setVideoURI(uri, null);
    }

    public void setMediaControllerView(IMediaController mediaControllerView) {
        mMediaControllerView = mediaControllerView;
        attachMediaController();
    }

    /**
     * Register a callback to be invoked when the media file
     * is loaded and ready to go.
     *
     * @param l The callback that will be run
     */
    public void setOnPreparedListener(IMediaPlayer.OnPreparedListener l) {
        mOnPreparedListener = l;
    }

    /**
     * Register a callback to be invoked when the end of a media file
     * has been reached during playback.
     *
     * @param l The callback that will be run
     */
    public void setOnCompletionListener(IMediaPlayer.OnCompletionListener l) {
        mOnCompletionListener = l;
    }

    /**
     * Register a callback to be invoked when an error occurs
     * during playback or setup.  If no listener is specified,
     * or if the listener returned false, VideoView will inform
     * the user of any errors.
     *
     * @param l The callback that will be run
     */
    public void setOnErrorListener(IMediaPlayer.OnErrorListener l) {
        mOnErrorListener = l;
    }

    /**
     * Register a callback to be invoked when an informational event
     * occurs during playback or setup.
     *
     * @param l The callback that will be run
     */
    public void setOnInfoListener(IMediaPlayer.OnInfoListener l) {
        mOnInfoListener = l;
    }

}
