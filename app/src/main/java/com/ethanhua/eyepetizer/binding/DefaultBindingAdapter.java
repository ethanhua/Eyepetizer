package com.ethanhua.eyepetizer.binding;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.ethanhua.commonlib.media.IjkVideoViewEx;

/**
 * Created by ethanhua on 2017/9/17.
 */

public class DefaultBindingAdapter extends AbsBindingAdapter {

    @Override
    public void bindImage(ImageView imageView,
                          String url) {
        Glide.with(imageView.getContext())
                .load(url)
                .into(imageView);
    }

    @Override
    public void bindBackground(View view, String url) {
        Glide.with(view.getContext())
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
        Glide.with(ijkVideoViewEx.getContext())
                .load(url)
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(Drawable resource,
                                                Transition<? super Drawable> transition) {
                        //ijkVideoViewEx.getRenderView().getView().setBackgroundDrawable(resource);
                        ijkVideoViewEx.getCoverImageView().setImageDrawable(resource);
                    }
                });
    }
}
