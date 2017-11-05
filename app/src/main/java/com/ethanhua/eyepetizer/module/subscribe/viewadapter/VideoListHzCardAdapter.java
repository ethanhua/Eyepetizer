package com.ethanhua.eyepetizer.module.subscribe.viewadapter;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ethanhua.commonlib.adapter.BindRcvViewHolder;
import com.ethanhua.eyepetizer.viewmodel.ObservableItems;
import com.ethanhua.eyepetizer.R;
import com.ethanhua.eyepetizer.databinding.ItemVideoMiddleBinding;
import com.ethanhua.eyepetizer.module.discover.viewadapter.BaseEndlessAdapter;
import com.ethanhua.eyepetizer.module.video.viewmodel.VideoBaseVM;

/**
 * Created by ethanhua on 2017/9/19.
 */

public class VideoListHzCardAdapter extends BaseEndlessAdapter<ItemVideoMiddleBinding> {
    public VideoListHzCardAdapter(ObservableItems observableItems) {
        super(observableItems);
    }

    @Override
    public BindRcvViewHolder<ItemVideoMiddleBinding> onCreateViewHolder(
            ViewGroup parent,
            int viewType) {
        return new BindRcvViewHolder<>(DataBindingUtil.inflate(LayoutInflater.from(
                parent.getContext()),
                R.layout.item_video_middle,
                parent,
                false,
                bindingComponent == null ?
                        DataBindingUtil.getDefaultComponent()
                        : bindingComponent));
    }

    @Override
    public void onBindViewHolder(
            BindRcvViewHolder<ItemVideoMiddleBinding> holder,
            int position) {
        if (observableItems != null && observableItems.size() > 0) {
            int realPos = position % observableItems.size();
            VideoBaseVM item = (VideoBaseVM) observableItems.get(realPos);
            holder.getBinding().setVideoVM(item);
        }
    }
}
