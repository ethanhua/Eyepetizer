package com.ethanhua.eyepetizer.module.category;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ethanhua.eyepetizer.BaseFragment;
import com.ethanhua.eyepetizer.R;
import com.ethanhua.eyepetizer.databinding.FragmentCategoryAlbumBinding;
import com.ethanhua.eyepetizer.di.components.DaggerFragmentComponent;
import com.ethanhua.eyepetizer.module.category.viewmodel.CategoryAlbumVM;
import com.ethanhua.eyepetizer.module.discover.viewadapter.VideoListBriefVB;
import com.ethanhua.eyepetizer.module.discover.viewmodel.VideoListBriefVM;

import javax.inject.Inject;

/**
 * Created by ethanhua on 2017/10/4.
 */

public class CategoryAlbumFragment extends BaseFragment {
    private static final String PARAMS_ID = "id";
    private long mCategoryId = -1;
    @Inject
    public CategoryAlbumVM categoryAlbumVM;

    public static CategoryAlbumFragment newInstance(long id) {
        Bundle args = new Bundle();
        args.putLong(PARAMS_ID, id);
        CategoryAlbumFragment fragment = new CategoryAlbumFragment();
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
        FragmentCategoryAlbumBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_category_album,
                container,
                false,
                bindComponent);
        categoryAlbumVM.getAdapter().register(VideoListBriefVM.class,
                new VideoListBriefVB(bindComponent));
        categoryAlbumVM.setCategoryId(mCategoryId);
        categoryAlbumVM.refresh();
        binding.setVm(categoryAlbumVM);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        categoryAlbumVM.clean();
    }
}
