package com.ethanhua.eyepetizer.module.discover;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ethanhua.eyepetizer.BaseFragment;
import com.ethanhua.eyepetizer.R;
import com.ethanhua.eyepetizer.databinding.FragmentHotBinding;
import com.ethanhua.eyepetizer.di.components.DaggerFragmentComponent;
import com.ethanhua.eyepetizer.module.discover.viewadapter.AuthorItemVB;
import com.ethanhua.eyepetizer.module.discover.viewadapter.TextItemVB;
import com.ethanhua.eyepetizer.module.discover.viewadapter.VideoBannerVB;
import com.ethanhua.eyepetizer.module.discover.viewmodel.AuthorItemVM;
import com.ethanhua.eyepetizer.module.discover.viewmodel.HotVM;
import com.ethanhua.eyepetizer.module.discover.viewmodel.TextVM;
import com.ethanhua.eyepetizer.module.discover.viewmodel.VideoBannerVM;
import com.ethanhua.eyepetizer.module.home.viewadapter.SquareListVB;
import com.ethanhua.eyepetizer.module.home.viewadapter.VideoItemVB;
import com.ethanhua.eyepetizer.module.home.viewmodel.SquareListVM;
import com.ethanhua.eyepetizer.module.video.viewmodel.VideoBaseVM;

import javax.inject.Inject;

/**
 * Created by ethanhua on 2017/9/20.
 */

public class HotFragment extends BaseFragment {
    @Inject
    public HotVM hotVM;

    public static HotFragment newInstance() {
        return new HotFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerFragmentComponent.builder()
                .appComponent(getAppComponent())
                .fragmentModule(getFragmentModule())
                .build()
                .inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentHotBinding fragmentHotBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_hot,
                container,
                false,
                bindComponent);
        hotVM.getAdapter().register(SquareListVM.class,
                new SquareListVB(bindComponent));
        hotVM.getAdapter().register(VideoBannerVM.class,
                new VideoBannerVB(bindComponent));
        hotVM.getAdapter().register(VideoBaseVM.class,
                new VideoItemVB(bindComponent));
        hotVM.getAdapter().register(TextVM.class,
                new TextItemVB());
        hotVM.getAdapter().register(AuthorItemVM.class,
                new AuthorItemVB(bindComponent));
        fragmentHotBinding.setHotVM(hotVM);
        hotVM.refresh();
        return fragmentHotBinding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        hotVM.clean();
    }
}
