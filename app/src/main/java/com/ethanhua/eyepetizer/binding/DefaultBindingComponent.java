package com.ethanhua.eyepetizer.binding;

import android.databinding.DataBindingComponent;

/**
 * Created by ethanhua on 2017/10/25.
 */

public class DefaultBindingComponent implements DataBindingComponent {

    private final AbsBindingAdapter absBindingAdapter = new DefaultBindingAdapter();

    @Override
    public AbsBindingAdapter getAbsBindingAdapter() {
        return absBindingAdapter;
    }
}

