package com.ethanhua.eyepetizer.module.category;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ethanhua.eyepetizer.BaseFragment;
import com.ethanhua.eyepetizer.R;
import com.ethanhua.eyepetizer.module.category.viewmodel.CategoryAllVM;
import com.ethanhua.eyepetizer.databinding.FragmentCategoryAllBinding;
import com.ethanhua.eyepetizer.di.components.DaggerFragmentComponent;
import com.ethanhua.eyepetizer.module.home.viewadapter.VideoItemVB;
import com.ethanhua.eyepetizer.module.video.viewmodel.VideoBaseVM;

import javax.inject.Inject;

/**
 * Created by ethanhua on 2017/10/4.
 */

public class CategoryAllFragment extends BaseFragment {
    private static final String PARAMS_ID = "id";
    private long mCategoryId = -1;
    @Inject
    public CategoryAllVM categoryAllVM;

    public static CategoryAllFragment newInstance(long id) {
        Bundle args = new Bundle();
        args.putLong(PARAMS_ID, id);
        CategoryAllFragment fragment = new CategoryAllFragment();
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
        FragmentCategoryAllBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_category_all,
                container,
                false,
                bindComponent);
        categoryAllVM.getAdapter().register(VideoBaseVM.class,
                new VideoItemVB(bindComponent));
        categoryAllVM.setCategoryId(mCategoryId);
        categoryAllVM.refresh();
        binding.setVm(categoryAllVM);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        categoryAllVM.clean();
    }
}
