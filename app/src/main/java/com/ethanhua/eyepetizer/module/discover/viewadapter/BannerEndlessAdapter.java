package com.ethanhua.eyepetizer.module.discover.viewadapter;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ethanhua.commonlib.adapter.BindRcvViewHolder;
import com.ethanhua.eyepetizer.viewmodel.ObservableItems;
import com.ethanhua.eyepetizer.R;
import com.ethanhua.eyepetizer.databinding.ItemImageBinding;
import com.ethanhua.eyepetizer.module.video.viewmodel.VideoBaseVM;

/**
 * Created by ethanhua on 2017/10/30.
 */

public class BannerEndlessAdapter extends BaseEndlessAdapter<ItemImageBinding> {
    public BannerEndlessAdapter(ObservableItems observableItems) {
        super(observableItems);
    }

    @Override
    public BindRcvViewHolder<ItemImageBinding> onCreateViewHolder(
            ViewGroup parent,
            int viewType) {
        return new BindRcvViewHolder<>(DataBindingUtil.inflate(LayoutInflater.from(
                parent.getContext()),
                R.layout.item_image,
                parent,
                false,
                bindingComponent == null ?
                        DataBindingUtil.getDefaultComponent()
                        : bindingComponent));
    }

    @Override
    public void onBindViewHolder(BindRcvViewHolder<ItemImageBinding> holder,
                                 int position) {
        if (observableItems != null && observableItems.size() > 0) {
            int realPos = position % observableItems.size();
            VideoBaseVM item = (VideoBaseVM) observableItems.get(realPos);
            holder.getBinding().setActionUrl(item.actionUrl);
            holder.getBinding().setCoverUrl(item.imageUrl.get());
        }
    }
}
