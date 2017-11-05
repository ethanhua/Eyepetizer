package com.ethanhua.domain.interactor;

import com.ethanhua.domain.ThreadTransformer;
import com.ethanhua.domain.model.Comment;
import com.ethanhua.domain.model.ItemData;
import com.ethanhua.domain.model.ListData;
import com.ethanhua.domain.respository.CommentRepository;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by ethanhua on 2017/10/3.
 */

public class GetCommentConversationCase {
    private final CommentRepository mCommentRepository;
    private final ThreadTransformer mThreadTransformer;

    @Inject
    public GetCommentConversationCase(CommentRepository commentRepository,
                                      ThreadTransformer threadTransformer) {
        this.mCommentRepository = commentRepository;
        this.mThreadTransformer = threadTransformer;
    }

    public Single<ListData<ItemData<Comment>>> execute(long commentId) {
        return mCommentRepository.listConversation(commentId).compose(mThreadTransformer);
    }
}
