package com.ethanhua.eyepetizer.module.ranklist;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ethanhua.eyepetizer.BaseFragment;
import com.ethanhua.eyepetizer.R;
import com.ethanhua.eyepetizer.databinding.FragmentRankListBinding;
import com.ethanhua.eyepetizer.di.components.DaggerFragmentComponent;
import com.ethanhua.eyepetizer.module.home.viewadapter.VideoItemVB;
import com.ethanhua.eyepetizer.module.ranklist.viewmodel.RankListVM;
import com.ethanhua.eyepetizer.module.video.viewmodel.VideoBaseVM;

import javax.inject.Inject;

/**
 * Created by ethanhua on 2017/10/30.
 */

public class RankListFragment extends BaseFragment {
    private static final String PARAMS_RANK_TYPE = "rank_type";
    public static final String STRATEGY_RANK_WEEK = "weekly";
    public static final String STRATEGY_RANK_MONTH = "monthly";
    public static final String STRATEGY_RANK_TOTAL = "historical";

    @StringDef({STRATEGY_RANK_WEEK, STRATEGY_RANK_MONTH, STRATEGY_RANK_TOTAL})
    public @interface RankStrategy {
    }

    @Inject
    public RankListVM rankListVM;

    public static RankListFragment newInstance(@RankStrategy String rankType) {
        Bundle args = new Bundle();
        RankListFragment fragment = new RankListFragment();
        args.putString(PARAMS_RANK_TYPE, rankType);
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
            String rankStrategy = getArguments().getString(PARAMS_RANK_TYPE);
            rankListVM.setRankStrategy(rankStrategy);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentRankListBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_rank_list,
                container,
                false,
                bindComponent);
        rankListVM.getAdapter().register(VideoBaseVM.class,new VideoItemVB(bindComponent));
        binding.setVm(rankListVM);
        rankListVM.refresh();
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        rankListVM.clean();
    }
}
