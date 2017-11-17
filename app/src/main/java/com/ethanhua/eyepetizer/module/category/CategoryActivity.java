package com.ethanhua.eyepetizer.module.category;

import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;

import com.ethanhua.commonlib.adapter.FragmentAdapter;
import com.ethanhua.eyepetizer.BaseActivity;
import com.ethanhua.eyepetizer.R;
import com.ethanhua.eyepetizer.module.category.viewmodel.CategoryVM;
import com.ethanhua.eyepetizer.databinding.ActivityCategoryDetailsBinding;
import com.ethanhua.eyepetizer.di.components.DaggerActivityComponent;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by ethanhua on 2017/10/3.
 */
public class CategoryActivity extends BaseActivity {
    private final String PARAMS_TITLE = "title";
    private ActivityCategoryDetailsBinding binding;
    @Inject
    public CategoryVM categoryVM;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerActivityComponent.builder()
                .appComponent(getAppComponent())
                .activityModule(getActivityModule())
                .build()
                .inject(this);
        getInputData();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_category_details);
        initView();
        categoryVM.getCategoryInfo(categoryVM.categoryBaseVM.get().id);
    }

    /**
     * input data include {categoryId,categoryTitle}
     */
    private void getInputData() {
        Uri uri = getIntent().getData();
        categoryVM.categoryBaseVM.get().id = Long.parseLong(uri.getLastPathSegment());
        categoryVM.categoryBaseVM.get().title.set(uri.getQueryParameter(PARAMS_TITLE));
    }

    private void initView() {
        binding.setVm(categoryVM);
        List<Fragment> mFragments = new ArrayList<>(4);
        mFragments.add(CategoryHomeFragment.newInstance(categoryVM.categoryBaseVM.get().id));
        mFragments.add(CategoryAllFragment.newInstance(categoryVM.categoryBaseVM.get().id));
        mFragments.add(CategoryAuthorFragment.newInstance(categoryVM.categoryBaseVM.get().id));
        mFragments.add(CategoryAlbumFragment.newInstance(categoryVM.categoryBaseVM.get().id));
        FragmentPagerAdapter mFragmentPagerAdapter = new FragmentAdapter(
                getSupportFragmentManager(),
                mFragments,
                new String[]{"首页", "全部", "作者", "专辑"});
        binding.toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_white_36dp);
        binding.toolbar.setNavigationOnClickListener(view -> finish());
        binding.layoutTab.setupWithViewPager(binding.viewPager);
        binding.viewPager.setAdapter(mFragmentPagerAdapter);
        binding.viewPager.setOffscreenPageLimit(mFragments.size() - 1);
        binding.appBarLayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange() - 50) {
                if (categoryVM.categoryBaseVM.get().title.get().equals(binding.toolbar.getTitle())) {
                    return;
                }
                binding.toolbar.setTitle(categoryVM.categoryBaseVM.get().title.get());
                binding.layoutTab.setBackgroundResource(R.color.colorPrimary);
                binding.layoutTab.setTabTextColors(
                        ContextCompat.getColor(this, R.color.white50),
                        ContextCompat.getColor(this, R.color.colorWhite));
                binding.layoutTab.setSelectedTabIndicatorColor(
                        ContextCompat.getColor(this, R.color.colorWhite));
                return;
            }
            if ("".equals(binding.toolbar.getTitle())) {
                return;
            }
            binding.toolbar.setTitle("");
            binding.layoutTab.setBackgroundResource(R.color.colorWhite);
            binding.layoutTab.setTabTextColors(
                    ContextCompat.getColor(this, R.color.colorGrey),
                    ContextCompat.getColor(this, R.color.colorPrimary));
            binding.layoutTab.setSelectedTabIndicatorColor(
                    ContextCompat.getColor(this, R.color.colorPrimary));

        });
    }
}
