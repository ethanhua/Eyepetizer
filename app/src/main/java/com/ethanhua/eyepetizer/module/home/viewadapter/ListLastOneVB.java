package com.ethanhua.eyepetizer.module.home.viewadapter;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ethanhua.commonlib.adapter.BindRcvViewHolder;
import com.ethanhua.eyepetizer.R;
import com.ethanhua.eyepetizer.databinding.ItemListLastOneBinding;
import com.ethanhua.eyepetizer.module.home.viewmodel.ActionCardVM;

import me.drakeet.multitype.ItemViewBinder;

import static android.databinding.DataBindingUtil.inflate;

/**
 * Created by ethanhua on 2017/9/18.
 */

public class ListLastOneVB extends
        ItemViewBinder<ActionCardVM, BindRcvViewHolder<ItemListLastOneBinding>> {
    @NonNull
    @Override
    protected BindRcvViewHolder<ItemListLastOneBinding> onCreateViewHolder(
            @NonNull LayoutInflater inflater,
            @NonNull ViewGroup parent) {
        return new BindRcvViewHolder<>(inflate(inflater,
                R.layout.item_list_last_one,
                parent,
                false));
    }

    @Override
    protected void onBindViewHolder(
            @NonNull BindRcvViewHolder<ItemListLastOneBinding> holder,
            @NonNull ActionCardVM item) {
        holder.getBinding().setLastOneVM(item);
    }
}
