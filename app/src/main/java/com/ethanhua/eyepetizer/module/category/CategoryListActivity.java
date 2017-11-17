package com.ethanhua.eyepetizer.module.category;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;

import com.ethanhua.commonlib.widget.GridSpaceItemDecoration;
import com.ethanhua.domain.model.ItemData;
import com.ethanhua.eyepetizer.BaseActivity;
import com.ethanhua.eyepetizer.R;
import com.ethanhua.eyepetizer.module.category.viewadapter.CategoryVB;
import com.ethanhua.eyepetizer.module.category.viewmodel.CategoriesVM;
import com.ethanhua.eyepetizer.module.category.viewmodel.CategoryBaseVM;
import com.ethanhua.eyepetizer.databinding.ActivityCategoryListBinding;
import com.ethanhua.eyepetizer.di.components.DaggerActivityComponent;

import javax.inject.Inject;

/**
 * Created by ethanhua on 2017/10/3.
 */

public class CategoryListActivity extends BaseActivity {
    @Inject
    public CategoriesVM categoriesVM;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerActivityComponent.builder()
                .appComponent(getAppComponent())
                .activityModule(getActivityModule())
                .build()
                .inject(this);
        ActivityCategoryListBinding binding = DataBindingUtil.setContentView(this,
                R.layout.activity_category_list);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return ((CategoryBaseVM) categoriesVM.getListData()
                        .get(position))
                        .type.equals(ItemData.TYPE_RECTANGLE_CARD) ? 2 : 1;
            }
        });
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.addItemDecoration(new GridSpaceItemDecoration(6));
        binding.toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_white_36dp);
        binding.toolbar.setNavigationOnClickListener(view -> finish());
        categoriesVM.getAdapter().register(CategoryBaseVM.class, new CategoryVB());
        categoriesVM.refresh();
        binding.setVm(categoriesVM);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        categoriesVM.clean();
    }
}
