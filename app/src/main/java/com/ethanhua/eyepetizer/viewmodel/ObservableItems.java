package com.ethanhua.eyepetizer.viewmodel;

import android.databinding.ListChangeRegistry;
import android.databinding.ObservableList;
import android.support.v7.widget.RecyclerView;

import java.util.Collection;

import me.drakeet.multitype.Items;

/**
 * Created by ethanhua on 2017/9/15.
 */

public class ObservableItems extends Items implements ObservableList<Object> {
    private transient ListChangeRegistry mListeners = new ListChangeRegistry();

    @Override
    public void addOnListChangedCallback(
            OnListChangedCallback<? extends ObservableList<Object>> callback) {
        if (mListeners == null) {
            mListeners = new ListChangeRegistry();
        }
        mListeners.add(callback);
    }

    @Override
    public void removeOnListChangedCallback(
            OnListChangedCallback<? extends ObservableList<Object>> callback) {
        if (mListeners != null) {
            mListeners.remove(callback);
        }
    }

    @Override
    public boolean add(Object object) {
        super.add(object);
        notifyAdd(size() - 1, 1);
        return true;
    }

    @Override
    public void add(int index, Object object) {
        super.add(index, object);
        notifyAdd(index, 1);
    }

    @Override
    public boolean addAll(Collection<? extends Object> collection) {
        int oldSize = size();
        boolean added = super.addAll(collection);
        if (added) {
            notifyAdd(oldSize, size() - oldSize);
        }
        return added;
    }

    @Override
    public boolean addAll(int index, Collection<? extends Object> collection) {
        boolean added = super.addAll(index, collection);
        if (added) {
            notifyAdd(index, collection.size());
        }
        return added;
    }

    @Override
    public void clear() {
        super.clear();
        notifyDataSetChanged();
    }

    @Override
    public Object remove(int index) {
        Object val = super.remove(index);
        notifyRemove(index, 1);
        return val;
    }

    @Override
    public boolean remove(Object object) {
        int index = indexOf(object);
        if (index >= 0) {
            remove(index);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Object set(int index, Object object) {
        Object val = super.set(index, object);
        if (mListeners != null) {
            mListeners.notifyChanged(this, index, 1);
        }
        return val;
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        super.removeRange(fromIndex, toIndex);
        notifyRemove(fromIndex, toIndex - fromIndex);
    }

    private void notifyAdd(int start, int count) {
        if (mListeners != null) {
            mListeners.notifyInserted(this, start, count);
        }
    }

    private void notifyRemove(int start, int count) {
        if (mListeners != null) {
            mListeners.notifyRemoved(this, start, count);
        }
    }

    private void notifyDataSetChanged() {
        if (mListeners != null) {
            mListeners.notifyChanged(this);
        }
    }

    public void bindToAdapter(RecyclerView.Adapter mAdapter) {
         addOnListChangedCallback(
                new ObservableList.OnListChangedCallback<ObservableList<Object>>() {
                    @Override
                    public void onChanged(ObservableList<Object> sender) {
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onItemRangeChanged(ObservableList<Object> sender,
                                                   int positionStart,
                                                   int itemCount) {
                        mAdapter.notifyItemRangeChanged(positionStart, itemCount);
                    }

                    @Override
                    public void onItemRangeInserted(ObservableList<Object> sender,
                                                    int positionStart,
                                                    int itemCount) {
                        mAdapter.notifyItemRangeInserted(positionStart, itemCount);
                    }

                    @Override
                    public void onItemRangeMoved(ObservableList<Object> sender,
                                                 int fromPosition,
                                                 int toPosition,
                                                 int itemCount) {
                        mAdapter.notifyItemMoved(fromPosition, toPosition);
                    }

                    @Override
                    public void onItemRangeRemoved(ObservableList<Object> sender,
                                                   int positionStart,
                                                   int itemCount) {
                        mAdapter.notifyItemRangeRemoved(positionStart, itemCount);
                    }
                });
    }
}
