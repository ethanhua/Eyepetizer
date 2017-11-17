package com.ethanhua.eyepetizer.module.discover;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ethanhua.commonlib.adapter.FragmentAdapter;
import com.ethanhua.eyepetizer.BaseFragment;
import com.ethanhua.eyepetizer.R;
import com.ethanhua.eyepetizer.databinding.FragmentDiscoverBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ethanhua on 2017/9/13.
 */

public class DiscoverFragment extends BaseFragment {

    public static DiscoverFragment newInstance() {
        return new DiscoverFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentDiscoverBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_discover,
                container,
                false,
                bindComponent);
        List<Fragment> mFragments = new ArrayList<>(2);
        String[] mTitles = new String[]{"热门", "分类"};
        mFragments.add(HotFragment.newInstance());
        mFragments.add(CategoryFragment.newInstance());
        FragmentAdapter mFragmentAdapter = new FragmentAdapter(
                getChildFragmentManager(),
                mFragments,
                mTitles);
        binding.layoutTab.setupWithViewPager(binding.viewPager);
        binding.viewPager.setAdapter(mFragmentAdapter);
        binding.viewPager.setCanScroll(false);
        return binding.getRoot();
    }

}
