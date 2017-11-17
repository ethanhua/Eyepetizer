package com.ethanhua.eyepetizer.module.video;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ethanhua.eyepetizer.BaseFragment;
import com.ethanhua.eyepetizer.R;
import com.ethanhua.eyepetizer.databinding.FragmentVideoCommentsBinding;
import com.ethanhua.eyepetizer.di.components.DaggerFragmentComponent;
import com.ethanhua.eyepetizer.module.video.viewadapter.TextActionVB;
import com.ethanhua.eyepetizer.module.video.viewadapter.VideoCommentVB;
import com.ethanhua.eyepetizer.module.video.viewmodel.TextActionVM;
import com.ethanhua.eyepetizer.module.video.viewmodel.VideoCommentVM;
import com.ethanhua.eyepetizer.module.video.viewmodel.VideoCommentsVM;

import javax.inject.Inject;

/**
 * Created by ethanhua on 2017/9/28.
 */

public class VideoCommentsFragment extends BaseFragment {
    private static final String PARAMS_VIDEO_ID = "params_video_id";
    private static final String PARAMS_BACKGROUND_URL = "params_background_url";
    private long mVideoId = -1;
    private String mBackgroundUrl;
    @Inject
    public VideoCommentsVM videoCommentsVM;

    public static VideoCommentsFragment newInstance(long videoId, String blurredUrl) {
        Bundle args = new Bundle();
        args.putLong(PARAMS_VIDEO_ID, videoId);
        args.putString(PARAMS_BACKGROUND_URL, blurredUrl);
        VideoCommentsFragment fragment = new VideoCommentsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerFragmentComponent.builder()
                .appComponent(getAppComponent())
                .fragmentModule(getFragmentModule())
                .build()
                .inject(this);
        if (getArguments() != null) {
            mVideoId = getArguments().getLong(PARAMS_VIDEO_ID);
            mBackgroundUrl = getArguments().getString(PARAMS_BACKGROUND_URL);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentVideoCommentsBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_video_comments,
                container,
                false,
                bindComponent);
        videoCommentsVM.getAdapter().register(TextActionVM.class,
                new TextActionVB());
        videoCommentsVM.getAdapter().register(VideoCommentVM.class,
                new VideoCommentVB(bindComponent));
        videoCommentsVM.setVideoId(mVideoId);
        videoCommentsVM.refresh();
        binding.setVm(videoCommentsVM);
        binding.setBlurredUrl(mBackgroundUrl);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        videoCommentsVM.clean();
    }
}
