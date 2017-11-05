package com.ethanhua.eyepetizer.module.ranklist;

import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;

import com.ethanhua.commonlib.adapter.FragmentAdapter;
import com.ethanhua.eyepetizer.BaseActivity;
import com.ethanhua.eyepetizer.R;
import com.ethanhua.eyepetizer.databinding.ActivityRankListBinding;

import java.util.ArrayList;
import java.util.List;

import static com.ethanhua.eyepetizer.module.ranklist.RankListFragment.STRATEGY_RANK_MONTH;
import static com.ethanhua.eyepetizer.module.ranklist.RankListFragment.STRATEGY_RANK_TOTAL;
import static com.ethanhua.eyepetizer.module.ranklist.RankListFragment.STRATEGY_RANK_WEEK;

/**
 * Created by ethanhua on 2017/10/30.
 */

public class RankListActivity extends BaseActivity {
    private static final String PARAMS_TAB_INDEX = "tabIndex";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRankListBinding binding = DataBindingUtil.setContentView(this,
                R.layout.activity_rank_list);
        List<Fragment> mFragments = new ArrayList<>(3);
        mFragments.add(RankListFragment.newInstance(STRATEGY_RANK_WEEK));
        mFragments.add(RankListFragment.newInstance(STRATEGY_RANK_MONTH));
        mFragments.add(RankListFragment.newInstance(STRATEGY_RANK_TOTAL));
        FragmentAdapter mFragmentAdapter = new FragmentAdapter(
                getSupportFragmentManager(),
                mFragments,
                new String[]{"周排行", "月排行", "总排行"});
        binding.layoutTab.setupWithViewPager(binding.viewPager);
        binding.viewPager.setAdapter(mFragmentAdapter);
        binding.viewPager.setOffscreenPageLimit(mFragments.size() - 1);
        binding.toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_white_36dp);
        binding.toolbar.setNavigationOnClickListener(v -> finish());
        Uri uri = getIntent().getData();
        if (uri != null) {
            String tabIndexStr = uri.getQueryParameter(PARAMS_TAB_INDEX);
            if (!TextUtils.isEmpty(tabIndexStr)) {
                int tabIndex = Integer.parseInt(tabIndexStr);
                binding.viewPager.setCurrentItem(tabIndex);
            }
        }
    }

}
