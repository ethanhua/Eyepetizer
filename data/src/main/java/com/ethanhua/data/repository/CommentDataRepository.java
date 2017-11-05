package com.ethanhua.data.repository;

import com.ethanhua.data.datasource.CommentDataStoreFactory;
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

public class CommentDataRepository implements CommentRepository {
    private final CommentDataStoreFactory mCommentDataStoreFactory;

    @Inject
    public CommentDataRepository(CommentDataStoreFactory commentDataStoreFactory) {
        this.mCommentDataStoreFactory = commentDataStoreFactory;
    }

    @Override
    public Single<ListData<ItemData<Comment>>> list(long videoId,Map map) {
        return mCommentDataStoreFactory.createCloudDataStore().list(videoId,map);
    }

    @Override
    public Single<ListData<ItemData<Comment>>> listConversation(long commentId) {
        return mCommentDataStoreFactory.createCloudDataStore().listConversation(commentId);
    }
}
