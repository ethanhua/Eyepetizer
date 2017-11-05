package com.ethanhua.eyepetizer.module.discover.viewadapter;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.PagerSnapHelper;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ethanhua.commonlib.adapter.BindRcvViewHolder;
import com.ethanhua.eyepetizer.R;
import com.ethanhua.eyepetizer.databinding.ItemTitleBannerBinding;
import com.ethanhua.eyepetizer.module.discover.viewmodel.VideoBannerVM;

import me.drakeet.multitype.ItemViewBinder;

public class VideoBannerVB extends
        ItemViewBinder<VideoBannerVM,
                BindRcvViewHolder<ItemTitleBannerBinding>> {
    private final DataBindingComponent mBindingComponent;

    public VideoBannerVB(DataBindingComponent bindingComponent) {
        this.mBindingComponent = bindingComponent;
    }

    @NonNull
    @Override
    protected BindRcvViewHolder<ItemTitleBannerBinding> onCreateViewHolder(
            @NonNull LayoutInflater inflater,
            @NonNull ViewGroup parent) {
        return new BindRcvViewHolder<>(DataBindingUtil.inflate(inflater,
                R.layout.item_title_banner,
                parent,
                false,
                mBindingComponent));
    }

    @Override
    protected void onBindViewHolder(
            @NonNull BindRcvViewHolder<ItemTitleBannerBinding> holder,
            @NonNull VideoBannerVM item) {
        if (holder.getBinding().recyclerView.getOnFlingListener() == null) {
            PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
            pagerSnapHelper.attachToRecyclerView(holder.getBinding().recyclerView);
        }
        item.mAdapter.setBindingComponent(mBindingComponent);
        holder.getBinding().recyclerView.scrollToPosition(Integer.MAX_VALUE / 2);
        holder.getBinding().setVm(item);
    }
}