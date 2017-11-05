package com.ethanhua.domain.model;

/**
 * Created by ethanhua on 2017/9/14.
 */

public class HomeData extends ListData<ItemData<VideoListData>> {
    public ItemData<ListData<ItemData<VideoData>>> topIssue;
}
