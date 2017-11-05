package com.ethanhua.eyepetizer.module.category.viewadapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ethanhua.commonlib.adapter.BindRcvViewHolder;
import com.ethanhua.eyepetizer.R;
import com.ethanhua.eyepetizer.module.category.viewmodel.CategoryBaseVM;
import com.ethanhua.eyepetizer.databinding.ItemCategoryImageBinding;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by ethanhua on 2017/10/3.
 */

public class CategoryVB extends
        ItemViewBinder<CategoryBaseVM, BindRcvViewHolder<ItemCategoryImageBinding>> {
    @NonNull
    @Override
    protected BindRcvViewHolder<ItemCategoryImageBinding> onCreateViewHolder(
            @NonNull LayoutInflater inflater,
            @NonNull ViewGroup parent) {
        return new BindRcvViewHolder<>(DataBindingUtil.inflate(inflater,
                R.layout.item_category_image,
                parent,
                false));
    }

    @Override
    protected void onBindViewHolder(
            @NonNull BindRcvViewHolder<ItemCategoryImageBinding> holder,
            @NonNull CategoryBaseVM item) {
        holder.getBinding().setVm(item);
    }
}
