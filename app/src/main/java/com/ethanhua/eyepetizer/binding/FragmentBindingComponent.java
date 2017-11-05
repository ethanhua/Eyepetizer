package com.ethanhua.eyepetizer.binding;

import android.databinding.DataBindingComponent;
import android.support.v4.app.Fragment;

import javax.inject.Inject;

/**
 * Created by ethanhua on 2017/9/17.
 */

public class FragmentBindingComponent implements DataBindingComponent {
    private final AbsBindingAdapter fragmentBindingAdapter;

    @Inject
    public FragmentBindingComponent(Fragment fragment) {
        this.fragmentBindingAdapter = new FragmentBindingAdapter(fragment);
    }

    @Override
    public AbsBindingAdapter getAbsBindingAdapter() {
        return fragmentBindingAdapter;
    }
}
