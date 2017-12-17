package com.ethanhua.eyepetizer.module.user;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ethanhua.eyepetizer.BaseFragment;
import com.ethanhua.eyepetizer.R;
import com.ethanhua.eyepetizer.databinding.FragmentMineBinding;
import com.ethanhua.eyepetizer.module.video.WatchRecordActivity;

/**
 * Created by ethanhua on 2017/12/16.
 */

public class MineFragment extends BaseFragment {
    public static MineFragment newInstance() {
        MineFragment fragment = new MineFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentMineBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_mine,
                container,
                false,
                bindComponent);
        binding.layoutHistory.setOnClickListener(v -> {
            WatchRecordActivity.actionStart(v.getContext());
        });
        return binding.getRoot();
    }
}
