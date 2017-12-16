package com.ethanhua.eyepetizer.module.category;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ethanhua.eyepetizer.BaseFragment;
import com.ethanhua.eyepetizer.R;
import com.ethanhua.eyepetizer.databinding.FragmentCategoryHomeBinding;
import com.ethanhua.eyepetizer.di.components.DaggerFragmentComponent;
import com.ethanhua.eyepetizer.module.category.viewmodel.CategoryHomeVM;
import com.ethanhua.eyepetizer.module.discover.viewadapter.TextItemVB;
import com.ethanhua.eyepetizer.module.discover.viewadapter.VideoListHzCardVB;
import com.ethanhua.eyepetizer.module.discover.viewmodel.TextVM;
import com.ethanhua.eyepetizer.module.discover.viewmodel.VideoListHzScrollCardVM;
import com.ethanhua.eyepetizer.module.home.viewadapter.SquareListVB;
import com.ethanhua.eyepetizer.module.home.viewadapter.VideoItemVB;
import com.ethanhua.eyepetizer.module.home.viewmodel.SquareListVM;
import com.ethanhua.eyepetizer.module.video.viewmodel.VideoBaseVM;

import javax.inject.Inject;

/**
 * Created by ethanhua on 2017/10/4.
 */

public class CategoryHomeFragment extends BaseFragment {
    private static final String PARAMS_ID = "id";
    private long mCategoryId = -1;
    @Inject
    public CategoryHomeVM categoryHomeVM;

    public static CategoryHomeFragment newInstance(long id) {
        Bundle args = new Bundle();
        args.putLong(PARAMS_ID, id);
        CategoryHomeFragment fragment = new CategoryHomeFragment();
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
        FragmentCategoryHomeBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_category_home,
                container,
                false,
                bindComponent);
        categoryHomeVM.getAdapter().register(SquareListVM.class,
                new SquareListVB(bindComponent));
        categoryHomeVM.getAdapter().register(VideoListHzScrollCardVM.class,
                new VideoListHzCardVB(bindComponent));
        categoryHomeVM.getAdapter().register(VideoBaseVM.class,
                new VideoItemVB(bindComponent));
        categoryHomeVM.getAdapter().register(TextVM.class,
                new TextItemVB());
        categoryHomeVM.setCategoryId(mCategoryId);
        categoryHomeVM.refresh();
        binding.setVm(categoryHomeVM);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        categoryHomeVM.clean();
    }
}
