package com.ethanhua.eyepetizer.module.video.viewmodel;

import android.databinding.ObservableField;

import com.ethanhua.domain.model.Comment;
import com.ethanhua.commonlib.viewmodel.ViewModel;

/**
 * Created by ethanhua on 2017/10/3.
 */

public class TextActionVM extends ViewModel{
    public final ObservableField<String> text = new ObservableField<>();
    public final ObservableField<String> actionUrl = new ObservableField<>();

    public static TextActionVM mapFrom(Comment comment) {
        TextActionVM textActionVM = new TextActionVM();
        textActionVM.actionUrl.set(comment.actionUrl);
        textActionVM.text.set(comment.text);
        return textActionVM;
    }
}
