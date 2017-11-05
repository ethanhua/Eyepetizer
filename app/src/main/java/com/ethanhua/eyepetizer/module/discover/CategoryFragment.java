package com.ethanhua.eyepetizer.module.discover;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ethanhua.eyepetizer.BaseFragment;
import com.ethanhua.eyepetizer.R;
import com.ethanhua.eyepetizer.databinding.FragmentCategoryBinding;
import com.ethanhua.eyepetizer.di.components.DaggerFragmentComponent;
import com.ethanhua.eyepetizer.module.discover.viewmodel.CategoryVM;
import com.ethanhua.eyepetizer.module.subscribe.viewadapter.VideoListHzCardVB;
import com.ethanhua.eyepetizer.module.subscribe.viewmodel.VideoListHzScrollCardVM;

import javax.inject.Inject;

/**
 * Created by ethanhua on 2017/9/20.
 */

public class CategoryFragment extends BaseFragment {

    @Inject
    public CategoryVM categoryVM;

    public static CategoryFragment newInstance() {
        return new CategoryFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerFragmentComponent.builder()
                .applicationComponent(getAppComponent())
                .fragmentModule(getFragmentModule())
                .build()
                .inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentCategoryBinding categoryBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_category,
                container,
                false,
                bindComponent);
        categoryVM.getAdapter().register(VideoListHzScrollCardVM.class,
                new VideoListHzCardVB(bindComponent));
        categoryBinding.setVm(categoryVM);
        categoryVM.refresh();
        return categoryBinding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        categoryVM.clean();
    }
}
