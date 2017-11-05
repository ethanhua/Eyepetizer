package com.ethanhua.eyepetizer.module.home.viewadapter;

import android.databinding.DataBindingComponent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ethanhua.commonlib.adapter.BindRcvViewHolder;
import com.ethanhua.eyepetizer.R;
import com.ethanhua.eyepetizer.databinding.ItemSquareListItemBinding;
import com.ethanhua.eyepetizer.module.home.viewmodel.SquareItemVM;

import me.drakeet.multitype.ItemViewBinder;

import static android.databinding.DataBindingUtil.inflate;

/**
 * Created by ethanhua on 2017/9/18.
 */

public class SquareListItemVB extends
        ItemViewBinder<SquareItemVM, BindRcvViewHolder<ItemSquareListItemBinding>> {

    private final DataBindingComponent dataBindingComponent;

    public SquareListItemVB(DataBindingComponent dataBindingComponent) {
        this.dataBindingComponent = dataBindingComponent;
    }

    @NonNull
    @Override
    protected BindRcvViewHolder<ItemSquareListItemBinding> onCreateViewHolder(
            @NonNull LayoutInflater inflater,
            @NonNull ViewGroup parent) {
        return new BindRcvViewHolder<>(inflate(
                inflater,
                R.layout.item_square_list_item,
                parent,
                false,
                dataBindingComponent));
    }

    @Override
    protected void onBindViewHolder(
            @NonNull BindRcvViewHolder<ItemSquareListItemBinding> holder,
            @NonNull SquareItemVM item) {
        holder.getBinding().setSquareVM(item);
    }
}
