package com.ethanhua.data.datasource;

import com.ethanhua.domain.model.Comment;
import com.ethanhua.domain.model.ItemData;
import com.ethanhua.domain.model.ListData;

import java.util.Map;

import io.reactivex.Single;

/**
 * Created by ethanhua on 2017/10/3.
 */

public interface CommentDataStore {

    Single<ListData<ItemData<Comment>>> list(long videoId, Map map);

    Single<ListData<ItemData<Comment>>> listConversation(long commentId);
}
