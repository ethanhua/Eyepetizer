package com.ethanhua.eyepetizer.module.video.viewadapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.ethanhua.commonlib.adapter.BindRcvViewHolder;
import com.ethanhua.eyepetizer.R;
import com.ethanhua.eyepetizer.databinding.ItemVideoDetailsHeaderBinding;
import com.ethanhua.eyepetizer.module.video.viewmodel.VideoBaseVM;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by ethanhua on 2017/10/2.
 */

public class VideoDetailsHeaderVB extends ItemViewBinder<VideoBaseVM, BindRcvViewHolder<ItemVideoDetailsHeaderBinding>> {

    private final OnClickListener mOnItemClickListener;

    public VideoDetailsHeaderVB(OnClickListener clickListener) {
        this.mOnItemClickListener = clickListener;
    }

    @NonNull
    @Override
    protected BindRcvViewHolder<ItemVideoDetailsHeaderBinding> onCreateViewHolder(
            @NonNull LayoutInflater inflater,
            @NonNull ViewGroup parent) {
        return new BindRcvViewHolder<>(DataBindingUtil.inflate(inflater,
                R.layout.item_video_details_header,
                parent,
                false));
    }

    @Override
    protected void onBindViewHolder(
            @NonNull BindRcvViewHolder<ItemVideoDetailsHeaderBinding> holder,
            @NonNull VideoBaseVM item) {
        holder.getBinding().setVm(item);
        holder.getBinding().tvReply.setOnClickListener(view -> {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onClick(view);
            }
        });
    }

}
