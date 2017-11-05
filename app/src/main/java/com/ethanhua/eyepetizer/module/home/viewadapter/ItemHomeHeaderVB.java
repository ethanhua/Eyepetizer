package com.ethanhua.eyepetizer.module.home.viewadapter;

import android.databinding.DataBindingComponent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ethanhua.commonlib.adapter.BindRcvViewHolder;
import com.ethanhua.commonlib.adapter.FragmentAdapter;
import com.ethanhua.eyepetizer.R;
import com.ethanhua.eyepetizer.databinding.ItemHomeHeaderBinding;
import com.ethanhua.eyepetizer.module.home.VideoItemFragment;
import com.ethanhua.eyepetizer.module.home.viewmodel.HomeTopVM;
import com.ethanhua.eyepetizer.module.video.viewmodel.VideoBaseVM;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.ItemViewBinder;

import static android.databinding.DataBindingUtil.inflate;

/**
 * Created by ethanhua on 2017/10/16.
 */

public class ItemHomeHeaderVB extends
        ItemViewBinder<HomeTopVM, BindRcvViewHolder<ItemHomeHeaderBinding>> {

    private DataBindingComponent bindComponent;
    private final FragmentManager fragmentManager;
    private final List<Fragment> fragments = new ArrayList<>();
    private HomeTopVM homeTopVM;

    public ItemHomeHeaderVB(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public ItemHomeHeaderVB(FragmentManager fragmentManager,
                            DataBindingComponent bindComponent) {
        this.bindComponent = bindComponent;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    protected BindRcvViewHolder<ItemHomeHeaderBinding> onCreateViewHolder(
            @NonNull LayoutInflater inflater,
            @NonNull ViewGroup parent) {
        return new BindRcvViewHolder<>(inflate(inflater,
                R.layout.item_home_header,
                parent,
                false,
                bindComponent));
    }

    @Override
    protected void onBindViewHolder(
            @NonNull BindRcvViewHolder<ItemHomeHeaderBinding> holder,
            @NonNull HomeTopVM item) {
        if (item.equals(homeTopVM)) {
            return;
        }
        homeTopVM = item;
        fragments.clear();
        for (VideoBaseVM videoBaseVM : item.videoBaseVMs) {
            fragments.add(VideoItemFragment.newInstance(videoBaseVM));
        }
        FragmentAdapter fragmentAdapter = new FragmentAdapter(fragmentManager, fragments);
        holder.getBinding().viewPager.setAdapter(fragmentAdapter);
        holder.getBinding().viewPager.setOffscreenPageLimit(fragments.size() - 1);
        holder.getBinding().viewPager.setOnPageChangeListener(
                new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                        super.onPageSelected(position);
                        holder.getBinding().setVm(item.videoBaseVMs.get(position));
                    }
                });
        holder.getBinding().circleIndicator.setViewPager(holder.getBinding().viewPager);
        holder.getBinding().setVm(item.videoBaseVMs.get(holder.getBinding().viewPager.getCurrentItem()));
    }
}
