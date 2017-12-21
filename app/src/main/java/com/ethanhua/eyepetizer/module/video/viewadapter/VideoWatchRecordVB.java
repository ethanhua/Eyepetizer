package com.ethanhua.eyepetizer.module.video.viewadapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ethanhua.commonlib.adapter.BindRcvViewHolder;
import com.ethanhua.eyepetizer.R;
import com.ethanhua.eyepetizer.databinding.ItemVideoWatchRecordBinding;
import com.ethanhua.eyepetizer.module.video.viewmodel.VideoWatchRecordVM;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by ethanhua on 2017/12/17.
 */

public class VideoWatchRecordVB extends ItemViewBinder<VideoWatchRecordVM, BindRcvViewHolder<ItemVideoWatchRecordBinding>> {
    @NonNull
    @Override
    protected BindRcvViewHolder<ItemVideoWatchRecordBinding> onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new BindRcvViewHolder<>(DataBindingUtil.inflate(inflater, R.layout.item_video_watch_record, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull BindRcvViewHolder<ItemVideoWatchRecordBinding> holder, @NonNull VideoWatchRecordVM item) {
        holder.getBinding().setVm(item);
    }
}
