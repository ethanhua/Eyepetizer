package com.ethanhua.domain.model;

/**
 * Created by ethanhua on 2017/10/3.
 */

public class ItemData<T> {
    public static final String TYPE_TEXT = "textCard";
    public static final String TYPE_VIDEO = "video";
    public static final String TYPE_AUTHOR = "briefCard";
    public static final String TYPE_COMMENT = "reply";
    public static final String TYPE_TEXT_CARD = "textCard";
    public static final String TYPE_ACTION_CARD = "actionCard";
    public static final String TYPE_SQUARE_CARD = "squareCard";
    public static final String TYPE_FOLLOW_CARD = "followCard";
    public static final String TYPE_RECTANGLE_CARD = "rectangleCard";
    public static final String TYPE_VIDEO_SMALL_CARD = "videoSmallCard";
    public static final String TYPE_BANNER_COLLECTION = "bannerCollection";
    public static final String TYPE_VIDEO_COLLECTION_COVER = "videoCollectionWithCover";
    public static final String TYPE_VIDEO_COLLECTION_BRIEF = "videoCollectionWithBrief";
    public static final String TYPE_SQUARE_CARD_COLLECTION = "squareCardCollection";
    public static final String TYPE_VIDEO_COLLECTION_HZ_CARD = "videoCollectionOfHorizontalScrollCard";

    public String type;
    public T data;
    public Tag tag;
}
