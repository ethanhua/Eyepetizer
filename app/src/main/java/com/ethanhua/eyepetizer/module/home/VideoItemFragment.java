package com.ethanhua.eyepetizer.module.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ethanhua.eyepetizer.BaseFragment;
import com.ethanhua.eyepetizer.R;
import com.ethanhua.eyepetizer.databinding.FragmentVideoItemBinding;
import com.ethanhua.eyepetizer.module.video.viewmodel.VideoBaseVM;

/**
 * Created by ethanhua on 2017/10/22.
 */

public class VideoItemFragment extends BaseFragment {

    public static final String PARAMS_VIDEO = "params_video";

    public static VideoItemFragment newInstance(VideoBaseVM videoBaseVM) {
        Bundle args = new Bundle();
        VideoItemFragment fragment = new VideoItemFragment();
        args.putParcelable(PARAMS_VIDEO, videoBaseVM);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentVideoItemBinding binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_video_item,
                container,
                false,
                bindComponent);
        if (getArguments() != null) {
            binding.setVm(getArguments().getParcelable(PARAMS_VIDEO));
        }
        getActivity().getLifecycle().addObserver(binding.videoView);
        binding.videoView.setOnCompletionListener(iMediaPlayer -> binding.videoView.start());
        return binding.getRoot();
    }
}
