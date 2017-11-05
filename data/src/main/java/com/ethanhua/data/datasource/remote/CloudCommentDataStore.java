package com.ethanhua.data.datasource.remote;

import com.ethanhua.data.api.EyepetizerService;
import com.ethanhua.data.datasource.CommentDataStore;
import com.ethanhua.domain.model.Comment;
import com.ethanhua.domain.model.ItemData;
import com.ethanhua.domain.model.ListData;

import java.util.Map;

import io.reactivex.Single;

/**
 * Created by ethanhua on 2017/10/3.
 */

public class CloudCommentDataStore implements CommentDataStore {
    private EyepetizerService mEyepetizerService;

    public CloudCommentDataStore(EyepetizerService eyepetizerService) {
        this.mEyepetizerService = eyepetizerService;
    }

    @Override
    public Single<ListData<ItemData<Comment>>> list(long videoId,Map map) {
        return mEyepetizerService.listVideoComments(videoId,map);
    }

    @Override
    public Single<ListData<ItemData<Comment>>> listConversation(long commentId) {
        return mEyepetizerService.listCommentConversation(commentId);
    }
}
