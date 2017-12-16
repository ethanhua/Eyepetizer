package com.ethanhua.eyepetizer.module.discover.viewadapter;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.PagerSnapHelper;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ethanhua.commonlib.adapter.BindRcvViewHolder;
import com.ethanhua.eyepetizer.R;
import com.ethanhua.eyepetizer.databinding.ItemVideoListHzScrollCardBinding;
import com.ethanhua.eyepetizer.module.discover.viewmodel.VideoListHzScrollCardVM;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by ethanhua on 2017/9/19.
 */

public class VideoListHzCardVB extends
        ItemViewBinder<VideoListHzScrollCardVM,
                BindRcvViewHolder<ItemVideoListHzScrollCardBinding>> {
    private final DataBindingComponent mBindingComponent;

    public VideoListHzCardVB(DataBindingComponent bindingComponent) {
        this.mBindingComponent = bindingComponent;
    }

    @NonNull
    @Override
    protected BindRcvViewHolder<ItemVideoListHzScrollCardBinding> onCreateViewHolder(
            @NonNull LayoutInflater inflater,
            @NonNull ViewGroup parent) {
        return new BindRcvViewHolder<>(DataBindingUtil.inflate(inflater,
                R.layout.item_video_list_hz_scroll_card,
                parent,
                false,
                mBindingComponent));
    }

    @Override
    protected void onBindViewHolder(
            @NonNull BindRcvViewHolder<ItemVideoListHzScrollCardBinding> holder,
            @NonNull VideoListHzScrollCardVM item) {
        if (holder.getBinding().recyclerView.getOnFlingListener() == null) {
            PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
            pagerSnapHelper.attachToRecyclerView(holder.getBinding().recyclerView);
        }
        item.mAdapter.setBindingComponent(mBindingComponent);
        holder.getBinding().recyclerView.scrollToPosition(Integer.MAX_VALUE / 2);
        holder.getBinding().setVideoHzCardVM(item);
    }
}
