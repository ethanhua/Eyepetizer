package com.ethanhua.data.datasource.local.database;

import com.ethanhua.domain.model.WatchRecord;

/**
 * Created by ethanhua on 2017/12/16.
 */

public class WatchRecordMapper {

    public static WatchRecord from(WatchHistoryRecord record) {
        WatchRecord item = new WatchRecord();
        item.id = record.id;
        item.position = record.position;
        item.actionUrl = record.actionUrl;
        item.playUrl = record.playUrl;
        item.blurredUrl = record.blurredUrl;
        item.title = record.title;
        item.slogan = record.slogan;
        item.subTitle = record.subTitle;
        item.description = record.description;
        item.collectionCount = record.collectionCount;
        item.shareCount = record.shareCount;
        item.replyCount = record.replyCount;
        item.authorName = record.authorName;
        item.authorIntro = record.authorIntro;
        item.authorAvatar = record.authorAvatar;
        item.bdPlayUrl = record.bdPlayUrl;
        item.supperPlayUrl = record.supperPlayUrl;
        item.highPlayUrl = record.highPlayUrl;
        item.normalPlayUrl = record.normalPlayUrl;
        item.lowPlayUrl = record.lowPlayUrl;
        return item;
    }
}
