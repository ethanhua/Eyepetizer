package com.ethanhua.eyepetizer.module.discover.viewmodel;

import android.databinding.ObservableField;

import com.ethanhua.commonlib.viewmodel.ViewModel;

/**
 * Created by ethanhua on 2017/10/13.
 */

public class TextVM extends ViewModel{
    public TextVM(String text) {
        this.text.set(text);
    }

    public final ObservableField<String> text = new ObservableField<>();
}
