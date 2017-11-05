package com.ethanhua.eyepetizer.module.video.viewadapter;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ethanhua.commonlib.adapter.BindRcvViewHolder;
import com.ethanhua.eyepetizer.R;
import com.ethanhua.eyepetizer.databinding.ItemVideoCommentBinding;
import com.ethanhua.eyepetizer.module.video.viewmodel.VideoCommentVM;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by ethanhua on 2017/10/3.
 */

public class VideoCommentVB extends
        ItemViewBinder<VideoCommentVM, BindRcvViewHolder<ItemVideoCommentBinding>> {
    private final DataBindingComponent dataBindingComponent;

    public VideoCommentVB(DataBindingComponent dataBindingComponent) {
        this.dataBindingComponent = dataBindingComponent;
    }

    @NonNull
    @Override
    protected BindRcvViewHolder<ItemVideoCommentBinding> onCreateViewHolder(
            @NonNull LayoutInflater inflater,
            @NonNull ViewGroup parent) {
        return new BindRcvViewHolder<>(DataBindingUtil.inflate(inflater,
                R.layout.item_video_comment,
                parent,
                false,
                dataBindingComponent));
    }

    @Override
    protected void onBindViewHolder(
            @NonNull BindRcvViewHolder<ItemVideoCommentBinding> holder,
            @NonNull VideoCommentVM item) {
        holder.getBinding().setVm(item);
    }
}
