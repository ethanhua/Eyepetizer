package com.ethanhua.eyepetizer.module.home.viewadapter;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ethanhua.commonlib.adapter.BindRcvViewHolder;
import com.ethanhua.eyepetizer.R;
import com.ethanhua.eyepetizer.databinding.ItemHomeSquareListBinding;
import com.ethanhua.eyepetizer.module.home.viewmodel.ActionCardVM;
import com.ethanhua.eyepetizer.module.home.viewmodel.SquareItemVM;
import com.ethanhua.eyepetizer.module.home.viewmodel.SquareListVM;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by ethanhua on 2017/9/18.
 */

public class SquareListVB extends
        ItemViewBinder<SquareListVM, BindRcvViewHolder<ItemHomeSquareListBinding>> {
    private DataBindingComponent dataBindingComponent;

    public SquareListVB() {
    }

    public SquareListVB(DataBindingComponent dataBindingComponent) {
        this.dataBindingComponent = dataBindingComponent;
    }

    @NonNull
    @Override
    protected BindRcvViewHolder<ItemHomeSquareListBinding> onCreateViewHolder(
            @NonNull LayoutInflater inflater,
            @NonNull ViewGroup parent) {
        return new BindRcvViewHolder<>(DataBindingUtil.inflate(inflater,
                R.layout.item_home_square_list,
                parent,
                false,
                dataBindingComponent == null ? DataBindingUtil.getDefaultComponent()
                        : dataBindingComponent));
    }

    @Override
    protected void onBindViewHolder(
            @NonNull BindRcvViewHolder<ItemHomeSquareListBinding> holder,
            @NonNull SquareListVM item) {
        item.getAdapter().register(SquareItemVM.class,
                new SquareListItemVB(dataBindingComponent));
        item.getAdapter().register(ActionCardVM.class,
                new ListLastOneVB());
        holder.getBinding().setSquareListVM(item);
    }
}
