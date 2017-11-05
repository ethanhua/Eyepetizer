package com.ethanhua.eyepetizer.module.discover.viewmodel;

import android.databinding.ObservableField;

import com.ethanhua.domain.model.VideoListData;
import com.ethanhua.commonlib.viewmodel.ViewModel;

/**
 * Created by ethanhua on 2017/9/20.
 */

public class AuthorItemVM extends ViewModel{
    public final ObservableField<String> name = new ObservableField<>();
    public final ObservableField<String> intro = new ObservableField<>();
    public final ObservableField<String> avatar = new ObservableField<>();
    public String actionUrl;

    public static AuthorItemVM mapFrom(VideoListData videoListData) {
        AuthorItemVM authorItemVM = new AuthorItemVM();
        authorItemVM.name.set(videoListData.title);
        authorItemVM.avatar.set(videoListData.icon);
        authorItemVM.intro.set(videoListData.description);
        authorItemVM.actionUrl = videoListData.actionUrl;
        return authorItemVM;
    }
}
