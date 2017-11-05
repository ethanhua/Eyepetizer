package com.ethanhua.domain.interactor;

import com.ethanhua.domain.ThreadTransformer;
import com.ethanhua.domain.model.Comment;
import com.ethanhua.domain.model.ItemData;
import com.ethanhua.domain.model.ListData;
import com.ethanhua.domain.respository.CommentRepository;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by ethanhua on 2017/10/3.
 */

public class GetVideoCommentsCase {
    private final CommentRepository mCommentRepository;
    private final ThreadTransformer mThreadTransformer;

    @Inject
    public GetVideoCommentsCase(CommentRepository commentRepository,
                                ThreadTransformer threadTransformer) {
        this.mCommentRepository = commentRepository;
        this.mThreadTransformer = threadTransformer;
    }

    public Single<ListData<ItemData<Comment>>> execute(long videoId,Map map) {
        return mCommentRepository.list(videoId,map).compose(mThreadTransformer);
    }
}
