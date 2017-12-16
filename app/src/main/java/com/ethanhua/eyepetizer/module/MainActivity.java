package com.ethanhua.eyepetizer.module;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import com.ethanhua.commonlib.adapter.FragmentAdapter;
import com.ethanhua.eyepetizer.BaseActivity;
import com.ethanhua.eyepetizer.R;
import com.ethanhua.eyepetizer.databinding.ActivityMainBinding;
import com.ethanhua.eyepetizer.module.discover.DiscoverFragment;
import com.ethanhua.eyepetizer.module.home.HomeFragment;
import com.ethanhua.eyepetizer.module.user.MineFragment;

import java.util.ArrayList;
import java.util.List;

import static android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener;

public class MainActivity extends BaseActivity {
    private static final int PAGE_POSITION_HOME = 0;
    private static final int PAGE_POSITION_DISCOVER = 1;
    private static final int PAGE_POSITION_MINE = 2;

    private final List<Fragment> mFragments = new ArrayList<>(3);
    private int mCurrentPagePosition = PAGE_POSITION_HOME;
    private ActivityMainBinding mViewBinding;


    private final OnNavigationItemSelectedListener mNavigationSelectedListener = item -> {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                mCurrentPagePosition = PAGE_POSITION_HOME;
                mViewBinding.viewPager.setCurrentItem(mCurrentPagePosition, false);
                return true;
            case R.id.navigation_discover:
                mCurrentPagePosition = PAGE_POSITION_DISCOVER;
                mViewBinding.viewPager.setCurrentItem(mCurrentPagePosition, false);
                return true;
            case R.id.navigation_me:
                mCurrentPagePosition = PAGE_POSITION_MINE;
                mViewBinding.viewPager.setCurrentItem(mCurrentPagePosition, false);
                return true;
        }
        return false;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initView();
    }

    private void initView() {
        mFragments.add(HomeFragment.newInstance());
//        mFragments.add(SubscriptionFragment.newInstance());
        mFragments.add(DiscoverFragment.newInstance());
        mFragments.add(MineFragment.newInstance());
        FragmentPagerAdapter pagerAdapter = new FragmentAdapter(getSupportFragmentManager(),
                mFragments);
        mViewBinding.viewPager.setAdapter(pagerAdapter);
        mViewBinding.viewPager.setOffscreenPageLimit(mFragments.size() - 1);
        mViewBinding.navigation.setOnNavigationItemSelectedListener(mNavigationSelectedListener);
        mViewBinding.viewPager.setCanScroll(false);
    }

}
