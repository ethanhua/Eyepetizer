package com.ethanhua.commonlib.adapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * Created by ethanhua on 2017/9/15.
 */

public class BindRcvViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {
    private final T mBinding;

    public BindRcvViewHolder(T binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    public T getBinding() {
        return mBinding;
    }
}
