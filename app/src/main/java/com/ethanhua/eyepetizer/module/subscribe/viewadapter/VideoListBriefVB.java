package com.ethanhua.eyepetizer.module.subscribe.viewadapter;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ethanhua.commonlib.adapter.BindRcvViewHolder;
import com.ethanhua.eyepetizer.R;
import com.ethanhua.eyepetizer.databinding.ItemVideoListBriefBinding;
import com.ethanhua.eyepetizer.module.home.viewadapter.VideoHorizontalItemVB;
import com.ethanhua.eyepetizer.module.subscribe.viewmodel.VideoListBriefVM;
import com.ethanhua.eyepetizer.module.video.viewmodel.VideoBaseVM;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by ethanhua on 2017/9/19.
 */

public class VideoListBriefVB extends
        ItemViewBinder<VideoListBriefVM, BindRcvViewHolder<ItemVideoListBriefBinding>> {
    private DataBindingComponent dataBindingComponent;

    public VideoListBriefVB() {

    }

    public VideoListBriefVB(DataBindingComponent dataBindingComponent) {
        this.dataBindingComponent = dataBindingComponent;
    }

    @NonNull
    @Override
    protected BindRcvViewHolder<ItemVideoListBriefBinding> onCreateViewHolder(
            @NonNull LayoutInflater inflater,
            @NonNull ViewGroup parent) {
        return new BindRcvViewHolder<>(DataBindingUtil.inflate(inflater,
                R.layout.item_video_list_brief,
                parent,
                false,
                dataBindingComponent == null ?
                        DataBindingUtil.getDefaultComponent() : dataBindingComponent));
    }

    @Override
    protected void onBindViewHolder(
            @NonNull BindRcvViewHolder<ItemVideoListBriefBinding> holder,
            @NonNull VideoListBriefVM item) {
        holder.getBinding().setVideoBriefVM(item);
        item.getAdapter().register(VideoBaseVM.class,
                new VideoHorizontalItemVB(dataBindingComponent));
    }
}
