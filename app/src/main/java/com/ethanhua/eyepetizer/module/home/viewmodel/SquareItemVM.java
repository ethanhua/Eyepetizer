package com.ethanhua.eyepetizer.module.home.viewmodel;

import android.databinding.ObservableField;

import com.ethanhua.commonlib.viewmodel.ViewModel;

/**
 * Created by ethanhua on 2017/9/18.
 */

public class SquareItemVM extends ViewModel{

    public final ObservableField<String> imageUrl = new ObservableField<>();
    public final ObservableField<String> title = new ObservableField<>();
    public String actionUrl;

}
