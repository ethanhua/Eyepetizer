package com.ethanhua.commonlib.originmedia;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioManager;
import android.os.Build;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.ethanhua.commonlib.R;

import java.util.Formatter;
import java.util.Locale;

import tv.danmaku.ijk.media.player.IMediaPlayer;

import static com.ethanhua.commonlib.originmedia.IMediaPlayerView.STATE_ERROR;
import static com.ethanhua.commonlib.originmedia.IMediaPlayerView.STATE_IDLE;
import static com.ethanhua.commonlib.originmedia.IMediaPlayerView.STATE_PREPARING;

/**
 * Created by ethanhua on 2017/11/7.
 */

public class MediaControllerView
        extends FrameLayout
        implements IMediaControllerView, View.OnClickListener {
    private static int INVALID_VALUE = -1;
    private String TAG = MediaControllerView.class.getSimpleName();
    private final int DEFAULT_SHOW_TIME = 3000;
    private boolean mShowing = false;

    private ImageButton btnNavFullScreenBack;
    private ImageButton btnNavNormalBack;
    private ImageButton btnPause;
    private ImageButton btnMinPause;
    private ImageButton btnFullScreen;
    private ViewGroup layoutTopController;
    private ViewGroup layoutBottomController;

    private SeekBar progressSeekBar;
    private TextView tvTitle;
    private TextView tvCurrentTime;
    private TextView tvDurationTime;

    private FrameLayout layoutGesturesAction;//手势操作显示布局（声音、亮度、快进、快退控制）
    private TextView tvVolume;
    private TextView tvBrightness;
    private TextView tvFastForward;

    private IMediaPlayerView mediaPlayerView;
    private StringBuilder mFormatBuilder;
    private Formatter mFormatter;
    private OrientationEventListener mOrientationEventListener;
    private AppCompatActivity mAttachActivity;
    private GestureDetector mGestureDetector;

    private static final int ACTION_VOLUME = 0; //声音调节
    private static final int ACTION_BRIGHTNESS = 1;//亮度调节
    private static final int ACTION_FAST_BACKWARD = 2;//快退
    private static final int ACTION_FAST_FORWARD = 3;//快进
    private static final int ACTION_NONE = -1;//无手势动作
    private long mTargetPosition;
    private int mCurrentAction = ACTION_NONE;
    private AudioManager mAudioManager;
    private int mMaxVolume;
    private int mStartVolume = INVALID_VALUE;

    public MediaControllerView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public MediaControllerView(@NonNull Context context,
                               @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MediaControllerView(@NonNull Context context,
                               @Nullable AttributeSet attrs,
                               @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MediaControllerView(@NonNull Context context,
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
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rootView = inflater.inflate(R.layout.layout_media_controller_view, null);
        layoutTopController = rootView.findViewById(R.id.layout_top_controller);
        layoutBottomController = rootView.findViewById(R.id.layout_bottom_controller);
        btnNavFullScreenBack = rootView.findViewById(R.id.btn_nav_back);
        btnNavNormalBack = rootView.findViewById(R.id.btn_nav_back_normal);
        btnPause = rootView.findViewById(R.id.btn_pause);
        btnMinPause = rootView.findViewById(R.id.btn_min_pause);
        btnFullScreen = rootView.findViewById(R.id.btn_fullscreen);

        progressSeekBar = rootView.findViewById(R.id.seekBar_progress);
        tvCurrentTime = rootView.findViewById(R.id.tv_time_current);
        tvDurationTime = rootView.findViewById(R.id.tv_time);

        layoutGesturesAction = rootView.findViewById(R.id.layout_gestures_action);
        tvVolume = rootView.findViewById(R.id.tv_volume);
        tvBrightness = rootView.findViewById(R.id.tv_brightness);
        tvFastForward = rootView.findViewById(R.id.tv_fast_forward);

        mGestureDetector = new GestureDetector(mAttachActivity, simpleOnGestureListener);
        progressSeekBar.setMax(1000);
        progressSeekBar.setOnSeekBarChangeListener(mSeekListener);
        btnNavFullScreenBack.setOnClickListener(this);
        btnNavNormalBack.setOnClickListener(this);
        btnPause.setOnClickListener(this);
        btnMinPause.setOnClickListener(this);
        btnFullScreen.setOnClickListener(this);
        mFormatBuilder = new StringBuilder();
        mFormatter = new Formatter(mFormatBuilder, Locale.getDefault());

        // 声音
        mAudioManager = (AudioManager) mAttachActivity.getSystemService(Context.AUDIO_SERVICE);
        mMaxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        addView(rootView);
        hide();
    }

    private void back() {
        if (Utils.isFullScreen(mAttachActivity)) {
            Utils.switchFullScreen(mAttachActivity, false);
            return;
        }
        mAttachActivity.finish();
    }

    /**
     * 开关播放器
     */
    private void togglePlayer() {
        if (mediaPlayerView == null) {
            return;
        }
        if (mediaPlayerView.isPlaying()) {
            mediaPlayerView.pause();
        } else {
            mediaPlayerView.start();
        }
    }

    private void updatePlayerStateUI() {
        if (mediaPlayerView != null) {
            updatePlayerStateUI(mediaPlayerView.getCurrentState());
        }
    }

    /**
     * 当播放器状态改变时更新相关UI
     *
     * @param state 播放器状态
     */
    private void updatePlayerStateUI(int state) {
        Log.e(TAG, "updatePlayerStatusUI:" + state);
        switch (state) {
            case IMediaPlayerView.STATE_PLAYBACK_COMPLETED:
            case IMediaPlayerView.STATE_PAUSED:
                btnPause.setSelected(false);
                btnMinPause.setSelected(false);
                break;
            case IMediaPlayerView.STATE_PLAYING:
                btnPause.setSelected(true);
                btnMinPause.setSelected(true);
                break;
            default:
                break;
        }
    }


    private boolean isInPlaybackState() {
        if (mediaPlayerView == null) {
            return false;
        }
        int mCurrentState = mediaPlayerView.getCurrentState();
        return (mCurrentState != STATE_ERROR &&
                mCurrentState != STATE_IDLE &&
                mCurrentState != STATE_PREPARING);
    }

    private void toggleFullScreen() {
        Utils.toggleFullScreen(mAttachActivity);
        updateScreenStateUI();
    }

    /**
     * 当屏幕状态改变时（切换全屏） 更新相关UI
     */
    private void updateScreenStateUI() {
        Log.e(TAG, "update FullScreen Button:" + Utils.isFullScreen(mAttachActivity));
        boolean isFullscreen = Utils.isFullScreen(mAttachActivity);
        btnFullScreen.setSelected(isFullscreen);
        btnNavNormalBack.setVisibility(isFullscreen ? GONE : VISIBLE);
        layoutTopController.setVisibility(isFullscreen ? VISIBLE : GONE);
    }

    /**
     * 更新当前播放进度相关UI
     *
     * @return 播放器的当前播放进度
     */
    private int updateProgressUI() {
        if (mediaPlayerView == null) {
            return 0;
        }
        int currentPosition = mediaPlayerView.getCurrentPosition();
        int duration = mediaPlayerView.getDuration();

        if (progressSeekBar != null) {
            if (duration > 0) {
                // use long to avoid overflow
                long pos = 1000L * currentPosition / duration;
                progressSeekBar.setProgress((int) pos);
            }
            int percent = mediaPlayerView.getBufferPercentage();
            progressSeekBar.setSecondaryProgress(percent * 10);
        }

        if (tvCurrentTime != null) {
            tvCurrentTime.setText(stringForTime(currentPosition));
        }
        if (tvDurationTime != null) {
            tvDurationTime.setText(stringForTime(duration));
        }
        return currentPosition;
    }

    private Runnable mUpdateProgressUIRunnable = new Runnable() {
        @Override
        public void run() {
            postDelayed(this, 1000 - updateProgressUI() % 1000);
        }
    };

    private Runnable mFadeOutRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };

    private IMediaPlayer.OnInfoListener mOnInfoListener = new IMediaPlayer.OnInfoListener() {
        @Override
        public boolean onInfo(IMediaPlayer iMediaPlayer, int state, int i1) {
            updatePlayerStateUI(state);
            return false;
        }
    };

    private SeekBar.OnSeekBarChangeListener mSeekListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (!fromUser) {
                return;
            }
            long duration = mediaPlayerView.getDuration();
            long seekPosition = (duration * progress) / 1000L;
            mediaPlayerView.seekTo((int) seekPosition);
            if (tvCurrentTime != null) {
                tvCurrentTime.setText(stringForTime((int) seekPosition));
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            show(3600000);
            removeCallbacks(mUpdateProgressUIRunnable);
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            show();
        }
    };

    private GestureDetector.SimpleOnGestureListener simpleOnGestureListener =
            new GestureDetector.SimpleOnGestureListener() {

                @Override
                public boolean onDown(MotionEvent e) {
                    return true;
                }

                @Override
                public boolean onSingleTapConfirmed(MotionEvent e) {
                    Log.e(TAG, "on single tap confirmed");
                    switchVisible();
                    return true;
                }

                @Override
                public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                    Log.e(TAG, e2.getAction() + "-gesturesAction");
                    hide();
                    float mOldX = e1.getX();
                    float mOldY = e1.getY();
                    float deltaY = mOldY - e2.getY();
                    float deltaX = mOldX - e2.getX();
                    //快进或快退
                    if (mCurrentAction == ACTION_NONE) {
                        if (Math.abs(distanceX) >= Math.abs(distanceY)) {
                            mCurrentAction = -deltaY > 0 ? ACTION_FAST_FORWARD : ACTION_FAST_BACKWARD;
                        } else {
                            //音量调节
                            if (mOldX > getResources().getDisplayMetrics().widthPixels * 0.5f) {
                                mCurrentAction = ACTION_VOLUME;
                            } else {  //亮度调节
                                mCurrentAction = ACTION_BRIGHTNESS;
                            }
                        }
                    }
                    if (mCurrentAction == ACTION_FAST_BACKWARD
                            || mCurrentAction == ACTION_FAST_FORWARD) {
                        fastForwardProgress(-deltaX / getWidth());
                    } else if (mCurrentAction == ACTION_VOLUME) {
                        controlVolume(deltaY / getHeight());
                    } else if (mCurrentAction == ACTION_BRIGHTNESS) {
                        controlBrightness(distanceY / getHeight());
                    }
                    return true;
                }
            };


    private OnTouchListener onTouchListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            Log.e(TAG, "onTouch" + event.getAction());
            if (mGestureDetector.onTouchEvent(event)) {
                return true;
            }
            if (event.getAction() == MotionEvent.ACTION_UP) {
                endGestureAction();
            }
            return false;
        }
    };

    private void switchVisible() {
        if (isShowing()) {
            hide();
        } else {
            show();
        }
    }

    private void endGestureAction() {
        layoutGesturesAction.setVisibility(GONE);
        if (mCurrentAction == ACTION_FAST_BACKWARD
                || mCurrentAction == ACTION_FAST_FORWARD
                && mTargetPosition >= 0
                && mTargetPosition != mediaPlayerView.getCurrentPosition()) {
            mediaPlayerView.seekTo((int) mTargetPosition);
            updateProgressUI();
        }
        mCurrentAction = ACTION_NONE;
        mStartVolume = INVALID_VALUE;
    }

    private void fastForwardProgress(float deltaPercent) {

        int position = mediaPlayerView.getCurrentPosition();
        long duration = mediaPlayerView.getDuration();
        // 单次拖拽最大时间差为100秒或播放时长的1/2
        long deltaMax = Math.min(100 * 1000, duration / 2);
        // 计算滑动时间
        long delta = (long) (deltaMax * deltaPercent);
        // 目标位置
        mTargetPosition = delta + position;
        if (mTargetPosition > duration) {
            mTargetPosition = duration;
        } else if (mTargetPosition <= 0) {
            mTargetPosition = 0;
        }
        int deltaTime = (int) ((mTargetPosition - position) / 1000);
        String desc;
        // 对比当前位置来显示快进或后退
        if (mTargetPosition > position) {
            desc = stringForTime((int) mTargetPosition) + "/" + stringForTime((int) duration)
                    + "\n" + "+" + deltaTime + "秒";
        } else {
            desc = stringForTime((int) mTargetPosition) + "/" + stringForTime((int) duration)
                    + "\n" + deltaTime + "秒";
        }
        layoutGesturesAction.setVisibility(VISIBLE);
        tvFastForward.setText(desc);
        tvVolume.setVisibility(GONE);
        tvBrightness.setVisibility(GONE);
        tvFastForward.setVisibility(VISIBLE);
    }

    private void controlVolume(float deltaPercent) {
        if (mStartVolume == INVALID_VALUE) {
            mStartVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        }
        int targetVolume = mStartVolume + (int) (deltaPercent * mMaxVolume);
        if (targetVolume > mMaxVolume) {
            targetVolume = mMaxVolume;
        }
        if (targetVolume < 0) {
            targetVolume = 0;
        }
        layoutGesturesAction.setVisibility(VISIBLE);
        tvVolume.setVisibility(VISIBLE);
        tvFastForward.setVisibility(GONE);
        tvBrightness.setVisibility(GONE);
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, targetVolume, 0);
        tvVolume.setText((targetVolume * 100 / mMaxVolume) + "%");
    }

    private void controlBrightness(float deltaPercent) {
        float mCurBrightness = mAttachActivity.getWindow().getAttributes().screenBrightness;
        if (mCurBrightness < 0.0f) {
            mCurBrightness = 0.5f;
        } else if (mCurBrightness < 0.01f) {
            mCurBrightness = 0.01f;
        }

        WindowManager.LayoutParams attributes = mAttachActivity.getWindow().getAttributes();
        attributes.screenBrightness = mCurBrightness + deltaPercent;
        if (attributes.screenBrightness > 1.0f) {
            attributes.screenBrightness = 1.0f;
        } else if (attributes.screenBrightness < 0.01f) {
            attributes.screenBrightness = 0.01f;
        }
        layoutGesturesAction.setVisibility(VISIBLE);
        tvFastForward.setVisibility(GONE);
        tvVolume.setVisibility(GONE);
        tvBrightness.setVisibility(VISIBLE);
        tvBrightness.setText((int) Math.ceil(attributes.screenBrightness * 100) + "%");
        mAttachActivity.getWindow().setAttributes(attributes);
    }

    private String stringForTime(int timeMs) {
        int totalSeconds = timeMs / 1000;

        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        int hours = totalSeconds / 3600;

        mFormatBuilder.setLength(0);
        if (hours > 0) {
            return mFormatter.format("%d:%02d:%02d", hours, minutes, seconds).toString();
        } else {
            return mFormatter.format("%02d:%02d", minutes, seconds).toString();
        }
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.btn_nav_back
                || viewId == R.id.btn_nav_back_normal) {
            back();
            return;
        }
        if (viewId == R.id.btn_fullscreen) {
            toggleFullScreen();
            hide();
            return;
        }
        if (viewId == R.id.btn_pause
                || viewId == R.id.btn_min_pause) {
            togglePlayer();
            show();
            return;
        }
    }

    /**
     * ============================ 暴露给外面的接口 ===========================================
     */
    @Override
    public void setEnabled(boolean enabled) {
        Log.e(TAG, "set enabled:" + enabled);
        //setEnabled(enabled);
    }

    @Override
    public void setMediaPlayerView(IMediaPlayerView playerView) {
        mediaPlayerView = playerView;
        mediaPlayerView.setOnInfoListener(mOnInfoListener);
        ((View) mediaPlayerView).setOnTouchListener(onTouchListener);
    }

    @Override
    public void show(int timeout) {
        if (!isInPlaybackState()) {
            return;
        }
        if (!mShowing) {
            mShowing = true;
            layoutBottomController.setVisibility(VISIBLE);
            btnPause.setVisibility(VISIBLE);
        }
        updateScreenStateUI();
        updatePlayerStateUI();
        post(mUpdateProgressUIRunnable);
        if (timeout != 0) {
            //reset hide time delay
            removeCallbacks(mFadeOutRunnable);
            postDelayed(mFadeOutRunnable, timeout);
        }
    }

    @Override
    public void show() {
        show(DEFAULT_SHOW_TIME);
    }

    @Override
    public void hide() {
        mShowing = false;
        removeCallbacks(mUpdateProgressUIRunnable);
        layoutTopController.setVisibility(GONE);
        layoutBottomController.setVisibility(GONE);
        btnNavNormalBack.setVisibility(GONE);
        btnPause.setVisibility(GONE);
    }

    @Override
    public boolean isShowing() {
        return mShowing;
    }
}
