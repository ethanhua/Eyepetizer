package com.ethanhua.eyepetizer.module.discover.viewadapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ethanhua.commonlib.adapter.BindRcvViewHolder;
import com.ethanhua.eyepetizer.R;
import com.ethanhua.eyepetizer.databinding.ItemTextBinding;
import com.ethanhua.eyepetizer.module.discover.viewmodel.TextVM;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by ethanhua on 2017/9/20.
 */

public class TextItemVB extends ItemViewBinder<TextVM, BindRcvViewHolder<ItemTextBinding>> {


    @NonNull
    @Override
    protected BindRcvViewHolder<ItemTextBinding> onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new BindRcvViewHolder<>(DataBindingUtil.inflate(inflater, R.layout.item_text, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull BindRcvViewHolder<ItemTextBinding> holder, @NonNull TextVM item) {
        holder.getBinding().setVm(item);
    }
}
