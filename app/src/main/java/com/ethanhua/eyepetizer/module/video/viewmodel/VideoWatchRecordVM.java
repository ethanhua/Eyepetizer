package com.ethanhua.eyepetizer.module.video.viewmodel;

import android.databinding.ObservableField;
import android.databinding.ObservableLong;

import com.ethanhua.commonlib.viewmodel.ViewModel;

/**
 * Created by ethanhua on 2017/12/21.
 */

public class VideoWatchRecordVM extends ViewModel {

    public ObservableLong watchTime = new ObservableLong();

    public ObservableField<String> subTitle = new ObservableField<>();

    public ObservableField<VideoBaseVM> videoBaseVM = new ObservableField<>();

}
