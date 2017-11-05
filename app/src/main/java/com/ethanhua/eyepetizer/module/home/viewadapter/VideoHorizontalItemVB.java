package com.ethanhua.eyepetizer.module.home.viewadapter;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ethanhua.commonlib.adapter.BindRcvViewHolder;
import com.ethanhua.eyepetizer.R;
import com.ethanhua.eyepetizer.databinding.ItemVideoHorizontalBinding;
import com.ethanhua.eyepetizer.module.video.viewmodel.VideoBaseVM;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by ethanhua on 2017/9/18.
 */

public class VideoHorizontalItemVB extends
        ItemViewBinder<VideoBaseVM, BindRcvViewHolder<ItemVideoHorizontalBinding>> {
    private final DataBindingComponent dataBindingComponent;

    public VideoHorizontalItemVB(DataBindingComponent dataBindingComponent) {
        this.dataBindingComponent = dataBindingComponent;
    }

    @NonNull
    @Override
    protected BindRcvViewHolder<ItemVideoHorizontalBinding> onCreateViewHolder(
            @NonNull LayoutInflater inflater,
            @NonNull ViewGroup parent) {
        return new BindRcvViewHolder<>(DataBindingUtil.inflate(inflater,
                R.layout.item_video_horizontal,
                parent,
                false,
                dataBindingComponent == null ?
                        DataBindingUtil.getDefaultComponent() : dataBindingComponent));
    }

    @Override
    protected void onBindViewHolder(
            @NonNull BindRcvViewHolder<ItemVideoHorizontalBinding> holder,
            @NonNull VideoBaseVM item) {
        holder.getBinding().setVideoVM(item);
    }
}
