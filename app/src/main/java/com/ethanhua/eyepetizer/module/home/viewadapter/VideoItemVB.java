package com.ethanhua.eyepetizer.module.home.viewadapter;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ethanhua.commonlib.adapter.BindRcvViewHolder;
import com.ethanhua.eyepetizer.R;
import com.ethanhua.eyepetizer.databinding.ItemHomeVideoBinding;
import com.ethanhua.eyepetizer.module.video.viewmodel.VideoBaseVM;

import me.drakeet.multitype.ItemViewBinder;

import static android.databinding.DataBindingUtil.inflate;

/**
 * Created by ethanhua on 2017/9/15.
 */

public class VideoItemVB extends ItemViewBinder<VideoBaseVM, BindRcvViewHolder<ItemHomeVideoBinding>> {
    private DataBindingComponent dataBindingComponent;

    public VideoItemVB() {

    }

    public VideoItemVB(DataBindingComponent dataBindingComponent) {
        this.dataBindingComponent = dataBindingComponent;
    }

    @NonNull
    @Override
    protected BindRcvViewHolder<ItemHomeVideoBinding> onCreateViewHolder(
            @NonNull LayoutInflater inflater,
            @NonNull ViewGroup parent) {
        return new BindRcvViewHolder<>(inflate(inflater,
                R.layout.item_home_video,
                parent,
                false,
                dataBindingComponent == null ? DataBindingUtil.getDefaultComponent() :
                        dataBindingComponent));
    }

    @Override
    protected void onBindViewHolder(@NonNull BindRcvViewHolder<ItemHomeVideoBinding> holder,
                                    @NonNull VideoBaseVM item) {
        holder.getBinding().setVm(item);
    }
}
