package com.ethanhua.eyepetizer.module.video;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ethanhua.eyepetizer.BaseActivity;
import com.ethanhua.eyepetizer.R;
import com.ethanhua.eyepetizer.databinding.ActivityWatchRecordBinding;
import com.ethanhua.eyepetizer.di.components.DaggerActivityComponent;
import com.ethanhua.eyepetizer.module.video.viewadapter.VideoWatchRecordVB;
import com.ethanhua.eyepetizer.module.video.viewmodel.VideoBaseVM;
import com.ethanhua.eyepetizer.module.video.viewmodel.VideoWatchRecordVM;

import javax.inject.Inject;

/**
 * Created by ethanhua on 2017/12/16.
 */

public class WatchRecordActivity extends BaseActivity {
    @Inject
    public VideoWatchRecordVM videoWatchRecordVM;

    public static void actionStart(Context context) {
        if (context == null) {
            return;
        }
        context.startActivity(new Intent(context, WatchRecordActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerActivityComponent.builder()
                .activityModule(getActivityModule())
                .appComponent(getAppComponent())
                .build()
                .inject(this);
        ActivityWatchRecordBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_watch_record);
        binding.toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_white_36dp);
        binding.toolbar.setNavigationOnClickListener(view -> finish());
        videoWatchRecordVM.getAdapter().register(VideoBaseVM.class, new VideoWatchRecordVB());
        binding.setVm(videoWatchRecordVM);
        videoWatchRecordVM.refresh();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        videoWatchRecordVM.clean();
    }
}
