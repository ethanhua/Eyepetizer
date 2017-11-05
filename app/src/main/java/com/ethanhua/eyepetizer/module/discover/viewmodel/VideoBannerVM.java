package com.ethanhua.eyepetizer.module.discover.viewmodel;

import android.databinding.ObservableField;

import com.ethanhua.commonlib.viewmodel.ViewModel;
import com.ethanhua.domain.model.ItemData;
import com.ethanhua.domain.model.VideoData;
import com.ethanhua.domain.model.VideoListData;
import com.ethanhua.eyepetizer.viewmodel.ObservableItems;
import com.ethanhua.eyepetizer.module.discover.viewadapter.BannerEndlessAdapter;
import com.ethanhua.eyepetizer.module.video.viewmodel.VideoBaseVM;

/**
 * Created by ethanhua on 2017/10/30.
 */

public class VideoBannerVM extends ViewModel {
    public String actionUrl;
    public final ObservableItems items = new ObservableItems();
    public final ObservableField<String> title = new ObservableField<>();
    public final BannerEndlessAdapter mAdapter = new BannerEndlessAdapter(items);

    public static VideoBannerVM mapFrom(VideoListData videoListData) {
        VideoBannerVM scrollCardVM = new VideoBannerVM();
        if (videoListData.header != null) {
            scrollCardVM.title.set(videoListData.header.title);
            scrollCardVM.actionUrl = videoListData.header.actionUrl;
        }
        for (ItemData<VideoData> itemDataContent : videoListData.itemList) {
            scrollCardVM.items.add(VideoBaseVM.mapFromVideo(itemDataContent));
        }
        return scrollCardVM;
    }
}
