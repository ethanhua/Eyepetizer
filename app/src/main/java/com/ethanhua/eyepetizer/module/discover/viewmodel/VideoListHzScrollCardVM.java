package com.ethanhua.eyepetizer.module.discover.viewmodel;

import android.databinding.ObservableField;

import com.ethanhua.domain.model.ItemData;
import com.ethanhua.domain.model.VideoData;
import com.ethanhua.domain.model.VideoListData;
import com.ethanhua.eyepetizer.viewmodel.ObservableItems;
import com.ethanhua.commonlib.viewmodel.ViewModel;
import com.ethanhua.eyepetizer.module.discover.viewadapter.VideoListHzCardAdapter;
import com.ethanhua.eyepetizer.module.video.viewmodel.VideoBaseVM;

/**
 * Created by ethanhua on 2017/9/19.
 */

public class VideoListHzScrollCardVM extends ViewModel{
    public String actionUrl;
    public final ObservableItems items = new ObservableItems();
    public final ObservableField<String> title = new ObservableField<>();
    public final ObservableField<String> subTitle = new ObservableField<>();
    public final VideoListHzCardAdapter mAdapter = new VideoListHzCardAdapter(items);

    public static VideoListHzScrollCardVM mapFrom(VideoListData videoListData){
        VideoListHzScrollCardVM scrollCardVM = new VideoListHzScrollCardVM();
        if (videoListData.header != null) {
            scrollCardVM.title.set(videoListData.header.title);
            scrollCardVM.subTitle.set(videoListData.header.subTitle);
            scrollCardVM.actionUrl = videoListData.header.actionUrl;
        }
        for (ItemData<VideoData> itemDataContent : videoListData.itemList) {
            scrollCardVM.items.add(VideoBaseVM.mapFromVideo(itemDataContent));
        }
        return scrollCardVM;
    }
}
