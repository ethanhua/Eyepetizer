package com.ethanhua.eyepetizer.module.home.viewmodel;

import com.ethanhua.domain.model.ItemData;
import com.ethanhua.domain.model.ListData;
import com.ethanhua.domain.model.VideoData;
import com.ethanhua.eyepetizer.module.video.viewmodel.VideoBaseVM;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ethanhua on 2017/10/20.
 */

public class HomeTopVM {

    public List<VideoBaseVM> videoBaseVMs = new ArrayList<>();

    public HomeTopVM(ListData<ItemData<VideoData>> itemDataListData) {
        for (ItemData<VideoData> videoDataItemData : itemDataListData.itemList) {
            videoBaseVMs.add(VideoBaseVM.mapFromVideo(videoDataItemData));
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (videoBaseVMs != null && obj != null && obj instanceof HomeTopVM) {
            HomeTopVM homeTopVM = (HomeTopVM) obj;
            if (videoBaseVMs.size() == homeTopVM.videoBaseVMs.size()) {
                for (int i = 0; i < videoBaseVMs.size(); i++) {
                    if (videoBaseVMs.get(i).id != homeTopVM.videoBaseVMs.get(i).id) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        }
        return false;
    }
}
