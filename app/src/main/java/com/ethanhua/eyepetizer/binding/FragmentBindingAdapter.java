package com.ethanhua.eyepetizer.binding;

import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.ethanhua.commonlib.media.IjkVideoViewEx;

import javax.inject.Inject;

/**
 * Created by ethanhua on 2017/9/17.
 */

public class FragmentBindingAdapter extends AbsBindingAdapter {
    private final Fragment fragment;

    @Inject
    public FragmentBindingAdapter(Fragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public void bindImage(ImageView imageView, String url) {
        Glide.with(fragment)
                .load(url)
                .into(imageView);
    }

    @Override
    public void bindBackground(View view, String url) {
        Glide.with(fragment)
                .load(url)
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(Drawable resource,
                                                Transition<? super Drawable> transition) {
                        view.setBackgroundDrawable(resource);
                    }
                });
    }

    @Override
    public void bindVideoCoverImage(IjkVideoViewEx ijkVideoViewEx,
                                    String url) {
        Glide.with(fragment)
                .load(url)
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(Drawable resource,
                                                Transition<? super Drawable> transition) {
                        ijkVideoViewEx.getCoverImageView().setImageDrawable(resource);
                    }
                });
    }
}
