package com.ethanhua.eyepetizer.module.video.viewadapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ethanhua.commonlib.adapter.BindRcvViewHolder;
import com.ethanhua.eyepetizer.R;
import com.ethanhua.eyepetizer.databinding.ItemVideoSmallCardBinding;
import com.ethanhua.eyepetizer.module.video.viewmodel.VideoBaseVM;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by ethanhua on 2017/10/2.
 */

public class VideoSmallCardVB extends ItemViewBinder<VideoBaseVM, BindRcvViewHolder<ItemVideoSmallCardBinding>> {

    @NonNull
    @Override
    protected BindRcvViewHolder<ItemVideoSmallCardBinding> onCreateViewHolder(
            @NonNull LayoutInflater inflater,
            @NonNull ViewGroup parent) {
        return new BindRcvViewHolder<>(DataBindingUtil.inflate(inflater,
                R.layout.item_video_small_card,
                parent,
                false));
    }

    @Override
    protected void onBindViewHolder(
            @NonNull BindRcvViewHolder<ItemVideoSmallCardBinding> holder,
            @NonNull VideoBaseVM item) {
        holder.getBinding().setVm(item);
    }
}
