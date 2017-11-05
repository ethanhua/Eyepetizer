package com.ethanhua.eyepetizer.module.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ethanhua.eyepetizer.BaseFragment;
import com.ethanhua.eyepetizer.R;
import com.ethanhua.eyepetizer.databinding.FragmentHomeBinding;
import com.ethanhua.eyepetizer.di.components.DaggerFragmentComponent;
import com.ethanhua.eyepetizer.module.home.viewadapter.ItemHomeHeaderVB;
import com.ethanhua.eyepetizer.module.home.viewadapter.SquareListVB;
import com.ethanhua.eyepetizer.module.home.viewadapter.VideoItemVB;
import com.ethanhua.eyepetizer.module.home.viewadapter.VideoListCoverVB;
import com.ethanhua.eyepetizer.module.home.viewmodel.HomeTopVM;
import com.ethanhua.eyepetizer.module.home.viewmodel.HomeVM;
import com.ethanhua.eyepetizer.module.home.viewmodel.SquareListVM;
import com.ethanhua.eyepetizer.module.home.viewmodel.VideoListVM;
import com.ethanhua.eyepetizer.module.video.viewmodel.VideoBaseVM;

import javax.inject.Inject;

/**
 * Created by ethanhua on 2017/9/13.
 */

public class HomeFragment extends BaseFragment {
    @Inject
    public HomeVM mHomeVM;

    public static HomeFragment newInstance() {
        return new HomeFragment();
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
        FragmentHomeBinding homeBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_home,
                container,
                false,
                bindComponent);
        mHomeVM.getAdapter().register(HomeTopVM.class,
                new ItemHomeHeaderVB(getChildFragmentManager(),bindComponent));
        mHomeVM.getAdapter().register(VideoBaseVM.class,
                new VideoItemVB(bindComponent));
        mHomeVM.getAdapter().register(VideoListVM.class,
                new VideoListCoverVB(bindComponent));
        mHomeVM.getAdapter().register(SquareListVM.class,
                new SquareListVB(bindComponent));
        mHomeVM.refresh();
        homeBinding.setHomeVM(mHomeVM);
        return homeBinding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mHomeVM.clean();
    }
}
