package com.ethanhua.eyepetizer.module.discover.viewadapter;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ethanhua.commonlib.adapter.BindRcvViewHolder;
import com.ethanhua.eyepetizer.R;
import com.ethanhua.eyepetizer.databinding.ItemAuthorBinding;
import com.ethanhua.eyepetizer.module.discover.viewmodel.AuthorItemVM;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by ethanhua on 2017/9/20.
 */

public class AuthorItemVB extends
        ItemViewBinder<AuthorItemVM, BindRcvViewHolder<ItemAuthorBinding>> {
    private final DataBindingComponent mDataBindingComponent;

    public AuthorItemVB(DataBindingComponent dataBindingComponent) {
        this.mDataBindingComponent = dataBindingComponent;
    }

    @NonNull
    @Override
    protected BindRcvViewHolder<ItemAuthorBinding> onCreateViewHolder(
            @NonNull LayoutInflater inflater,
            @NonNull ViewGroup parent) {
        return new BindRcvViewHolder<>(DataBindingUtil.inflate(inflater,
                R.layout.item_author,
                parent,
                false,
                mDataBindingComponent));
    }

    @Override
    protected void onBindViewHolder(@NonNull BindRcvViewHolder<ItemAuthorBinding> holder,
                                    @NonNull AuthorItemVM item) {
        holder.getBinding().setAuthorVM(item);
        int position = getAdapter().getItems().indexOf(item);
        if (position < getAdapter().getItems().size() - 1
                && !(getAdapter().getItems().get(position + 1) instanceof AuthorItemVM)) {
            holder.getBinding().dividerLine.setVisibility(View.INVISIBLE);
        } else {
            holder.getBinding().dividerLine.setVisibility(View.VISIBLE);
        }
    }
}
