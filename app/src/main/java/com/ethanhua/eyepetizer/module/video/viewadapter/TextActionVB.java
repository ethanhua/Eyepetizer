package com.ethanhua.eyepetizer.module.video.viewadapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ethanhua.commonlib.adapter.BindRcvViewHolder;
import com.ethanhua.eyepetizer.R;
import com.ethanhua.eyepetizer.databinding.ItemActionTextBinding;
import com.ethanhua.eyepetizer.module.video.viewmodel.TextActionVM;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by ethanhua on 2017/10/3.
 */

public class TextActionVB extends ItemViewBinder<TextActionVM, BindRcvViewHolder<ItemActionTextBinding>> {
    @NonNull
    @Override
    protected BindRcvViewHolder<ItemActionTextBinding> onCreateViewHolder(
            @NonNull LayoutInflater inflater,
            @NonNull ViewGroup parent) {
        return new BindRcvViewHolder<>(DataBindingUtil.inflate(inflater,
                R.layout.item_action_text,
                parent,
                false));
    }

    @Override
    protected void onBindViewHolder(
            @NonNull BindRcvViewHolder<ItemActionTextBinding> holder,
            @NonNull TextActionVM item) {
        holder.getBinding().setVm(item);
    }
}
