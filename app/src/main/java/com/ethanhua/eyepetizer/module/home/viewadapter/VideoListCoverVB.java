package com.ethanhua.eyepetizer.module.home.viewadapter;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ethanhua.commonlib.adapter.BindRcvViewHolder;
import com.ethanhua.eyepetizer.R;
import com.ethanhua.eyepetizer.databinding.ItemHomeVideoListCoverBinding;
import com.ethanhua.eyepetizer.module.home.viewmodel.ActionCardVM;
import com.ethanhua.eyepetizer.module.home.viewmodel.VideoListVM;
import com.ethanhua.eyepetizer.module.video.viewmodel.VideoBaseVM;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by ethanhua on 2017/9/18.
 */

public class VideoListCoverVB extends ItemViewBinder<VideoListVM, BindRcvViewHolder<ItemHomeVideoListCoverBinding>> {
    private DataBindingComponent dataBindingComponent;

    public VideoListCoverVB() {

    }

    public VideoListCoverVB(DataBindingComponent dataBindingComponent) {
        this.dataBindingComponent = dataBindingComponent;
    }

    @NonNull
    @Override
    protected BindRcvViewHolder<ItemHomeVideoListCoverBinding> onCreateViewHolder(
            @NonNull LayoutInflater inflater,
            @NonNull ViewGroup parent) {
        return new BindRcvViewHolder<>(DataBindingUtil.inflate(inflater,
                R.layout.item_home_video_list_cover,
                parent,
                false,
                dataBindingComponent == null ? DataBindingUtil.getDefaultComponent()
                        : dataBindingComponent));
    }

    @Override
    protected void onBindViewHolder(
            @NonNull BindRcvViewHolder<ItemHomeVideoListCoverBinding> holder,
            @NonNull VideoListVM item) {
        item.getAdapter().register(VideoBaseVM.class,
                new VideoHorizontalItemVB(dataBindingComponent));
        item.getAdapter().register(ActionCardVM.class,
                new ListLastOneVB());
        holder.getBinding().setVideoListVM(item);
    }
}
