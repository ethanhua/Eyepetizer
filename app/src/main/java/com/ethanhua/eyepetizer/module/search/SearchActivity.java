package com.ethanhua.eyepetizer.module.search;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.ethanhua.eyepetizer.BaseActivity;
import com.ethanhua.eyepetizer.R;
import com.ethanhua.eyepetizer.databinding.ActivitySearchBinding;
import com.ethanhua.eyepetizer.di.components.DaggerActivityComponent;
import com.ethanhua.eyepetizer.module.discover.viewadapter.VideoListBriefVB;
import com.ethanhua.eyepetizer.module.discover.viewmodel.VideoListBriefVM;
import com.ethanhua.eyepetizer.module.home.viewadapter.SquareListVB;
import com.ethanhua.eyepetizer.module.home.viewadapter.VideoItemVB;
import com.ethanhua.eyepetizer.module.home.viewadapter.VideoListCoverVB;
import com.ethanhua.eyepetizer.module.home.viewmodel.SquareListVM;
import com.ethanhua.eyepetizer.module.home.viewmodel.VideoListVM;
import com.ethanhua.eyepetizer.module.search.viewmodel.SearchVM;
import com.ethanhua.eyepetizer.module.video.viewmodel.VideoBaseVM;
import com.google.android.flexbox.AlignSelf;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import javax.inject.Inject;

import static android.support.v7.appcompat.R.id.search_src_text;
import static android.support.v7.widget.SearchView.SearchAutoComplete;

/**
 * Created by ethanhua on 2017/10/24.
 */

public class SearchActivity extends BaseActivity {

    @Inject
    SearchVM searchVM;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerActivityComponent.builder()
                .appComponent(getAppComponent())
                .activityModule(getActivityModule())
                .build()
                .inject(this);
        initView();
        searchVM.loadHotSearchTags();
    }

    private void initView() {
        ActivitySearchBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_search);

        //init hot query tag recyclerView
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(this);
        flexboxLayoutManager.setJustifyContent(JustifyContent.CENTER);
        flexboxLayoutManager.setAlignItems(AlignSelf.CENTER);
        binding.layoutQueryTag.recyclerView.setLayoutManager(flexboxLayoutManager);
        binding.layoutQueryTag.recyclerView.setAdapter(searchVM.getSearchTagAdapter());

        //init search result recyclerView item binder view type
        searchVM.getAdapter().register(VideoBaseVM.class,
                new VideoItemVB());
        searchVM.getAdapter().register(VideoListBriefVM.class,
                new VideoListBriefVB());
        searchVM.getAdapter().register(VideoListVM.class,
                new VideoListCoverVB());
        searchVM.getAdapter().register(SquareListVM.class,
                new SquareListVB());

        binding.tvCancel.setOnClickListener(v -> finish());
        //init search view
        SearchAutoComplete searchAutoComplete = binding.searchView.findViewById(search_src_text);
        searchAutoComplete.setHintTextColor(ContextCompat.getColor(this, R.color.white70));
        searchAutoComplete.setTextSize(14);
        binding.setVm(searchVM);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        searchVM.clean();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideKeyboard(v, ev)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(
                        Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            return !(event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom);
        }
        return false;
    }

}