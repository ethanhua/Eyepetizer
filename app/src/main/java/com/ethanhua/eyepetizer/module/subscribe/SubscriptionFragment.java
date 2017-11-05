package com.ethanhua.eyepetizer.module.subscribe;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ethanhua.eyepetizer.BaseFragment;
import com.ethanhua.eyepetizer.R;
import com.ethanhua.eyepetizer.databinding.FragmentFollowBinding;
import com.ethanhua.eyepetizer.di.components.DaggerFragmentComponent;
import com.ethanhua.eyepetizer.module.home.viewadapter.VideoItemVB;
import com.ethanhua.eyepetizer.module.subscribe.viewadapter.VideoListBriefVB;
import com.ethanhua.eyepetizer.module.subscribe.viewadapter.VideoListHzCardVB;
import com.ethanhua.eyepetizer.module.subscribe.viewmodel.SubscriptionVM;
import com.ethanhua.eyepetizer.module.subscribe.viewmodel.VideoListBriefVM;
import com.ethanhua.eyepetizer.module.subscribe.viewmodel.VideoListHzScrollCardVM;
import com.ethanhua.eyepetizer.module.video.viewmodel.VideoBaseVM;

import javax.inject.Inject;

/**
 * Created by ethanhua on 2017/9/13.
 */

public class SubscriptionFragment extends BaseFragment {

    @Inject
    public SubscriptionVM subscriptionVM;

    public static SubscriptionFragment newInstance() {
        return new SubscriptionFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        DaggerFragmentComponent.builder()
                .applicationComponent(getAppComponent())
                .fragmentModule(getFragmentModule())
                .build().inject(this);
        FragmentFollowBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_follow,
                container,
                false,
                bindComponent);
        subscriptionVM.getAdapter().register(VideoBaseVM.class,
                new VideoItemVB(bindComponent));
        subscriptionVM.getAdapter().register(VideoListBriefVM.class,
                new VideoListBriefVB(bindComponent));
        subscriptionVM.getAdapter().register(VideoListHzScrollCardVM.class,
                new VideoListHzCardVB(bindComponent));
        subscriptionVM.refresh();
        binding.setSubVM(subscriptionVM);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        subscriptionVM.clean();
    }
}
