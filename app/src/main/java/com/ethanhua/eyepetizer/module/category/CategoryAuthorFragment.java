package com.ethanhua.eyepetizer.module.category;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ethanhua.eyepetizer.BaseFragment;
import com.ethanhua.eyepetizer.R;
import com.ethanhua.eyepetizer.module.category.viewmodel.CategoryAuthorVM;
import com.ethanhua.eyepetizer.databinding.FragmentCategoryAuthorBinding;
import com.ethanhua.eyepetizer.di.components.DaggerFragmentComponent;
import com.ethanhua.eyepetizer.module.subscribe.viewadapter.VideoListBriefVB;
import com.ethanhua.eyepetizer.module.subscribe.viewmodel.VideoListBriefVM;

import javax.inject.Inject;

/**
 * Created by ethanhua on 2017/10/4.
 */

public class CategoryAuthorFragment extends BaseFragment {
    private static final String PARAMS_ID = "id";
    private long mCategoryId = -1;
    @Inject
    public CategoryAuthorVM categoryAuthorVM;

    public static CategoryAuthorFragment newInstance(long id) {
        Bundle args = new Bundle();
        args.putLong(PARAMS_ID, id);
        CategoryAuthorFragment fragment = new CategoryAuthorFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerFragmentComponent.builder()
                .appComponent(getAppComponent())
                .fragmentModule(getFragmentModule())
                .build()
                .inject(this);
        if (getArguments() != null) {
            mCategoryId = getArguments().getLong(PARAMS_ID);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentCategoryAuthorBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_category_author,
                container,
                false,
                bindComponent);
        categoryAuthorVM.getAdapter().register(VideoListBriefVM.class,
                new VideoListBriefVB(bindComponent));
        categoryAuthorVM.setCategoryId(mCategoryId);
        binding.setVm(categoryAuthorVM);
        categoryAuthorVM.refresh();
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        categoryAuthorVM.clean();
    }
}
