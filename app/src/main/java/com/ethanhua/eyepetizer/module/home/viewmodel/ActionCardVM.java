package com.ethanhua.eyepetizer.module.home.viewmodel;

import android.databinding.ObservableField;

import com.ethanhua.domain.model.VideoData;
import com.ethanhua.commonlib.viewmodel.ViewModel;

/**
 * Created by ethanhua on 2017/9/18.
 */

public class ActionCardVM extends ViewModel {
    public final ObservableField<String> text = new ObservableField<>();
    public String actionUrl;

    public static ActionCardVM mapFrom(VideoData videoData) {
        ActionCardVM actionCardVM = new ActionCardVM();
        actionCardVM.text.set(videoData.text);
        actionCardVM.actionUrl = videoData.actionUrl;
        return actionCardVM;
    }
}
