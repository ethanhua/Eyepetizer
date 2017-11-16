package com.ethanhua.eyepetizer.binding;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.ethanhua.commonlib.media.MediaPlayerView;

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
    public void bindVideoCoverImage(MediaPlayerView mediaPlayerView,
                                    String url) {
        Glide.with(mediaPlayerView.getContext())
                .load(url)
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(Drawable resource,
                                                Transition<? super Drawable> transition) {
                        mediaPlayerView.getCoverView().setBackgroundDrawable(resource);
                    }
                });
    }
}
