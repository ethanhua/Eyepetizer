package com.ethanhua.eyepetizer.module.discover.viewadapter;

import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

import com.ethanhua.commonlib.adapter.BindRcvViewHolder;
import com.ethanhua.eyepetizer.viewmodel.ObservableItems;

/**
 * Created by ethanhua on 2017/10/30.
 */

public abstract class BaseEndlessAdapter<T extends ViewDataBinding>
        extends RecyclerView.Adapter<BindRcvViewHolder<T>> {

    protected final ObservableItems observableItems;
    protected DataBindingComponent bindingComponent;

    protected BaseEndlessAdapter(ObservableItems observableItems) {
        this.observableItems = observableItems;
        observableItems.bindToAdapter(this);
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    public void setBindingComponent(DataBindingComponent dataBindingComponent) {
        this.bindingComponent = dataBindingComponent;
    }
}
