package com.ethanhua.eyepetizer.module.video.viewmodel;

import com.ethanhua.domain.interactor.GetVideoCommentsCase;
import com.ethanhua.domain.model.Comment;
import com.ethanhua.domain.model.ItemData;
import com.ethanhua.domain.model.ListData;
import com.ethanhua.eyepetizer.viewmodel.AbsListViewModel;
import com.ethanhua.commonlib.viewmodel.ViewModel;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by ethanhua on 2017/9/28.
 */

public class VideoCommentsVM extends AbsListViewModel<Comment, ListData<ItemData<Comment>>> {

    private final GetVideoCommentsCase mGetVideoCommentsCase;
    private long mVideoId;

    @Inject
    public VideoCommentsVM(GetVideoCommentsCase getVideoCommentsCase) {
        this.mGetVideoCommentsCase = getVideoCommentsCase;
    }

    @Override
    protected ViewModel convertItem(ItemData<Comment> itemData) {
        if (ItemData.TYPE_COMMENT.equals(itemData.type)) {
            return VideoCommentVM.mapFrom(itemData.data);
        }
        if (ItemData.TYPE_TEXT_CARD.equals(itemData.type)) {
            return TextActionVM.mapFrom(itemData.data);
        }
        return null;
    }

    @Override
    protected Single<ListData<ItemData<Comment>>> provideSource(Map map) {
        return mGetVideoCommentsCase.execute(mVideoId,map);
    }

    public void setVideoId(long videoId) {
        this.mVideoId = videoId;
    }
}
