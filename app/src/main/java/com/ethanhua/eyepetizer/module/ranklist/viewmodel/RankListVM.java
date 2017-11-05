package com.ethanhua.eyepetizer.module.ranklist.viewmodel;

import com.ethanhua.commonlib.viewmodel.ViewModel;
import com.ethanhua.domain.interactor.GetVideosByRankCase;
import com.ethanhua.domain.model.ItemData;
import com.ethanhua.domain.model.ListData;
import com.ethanhua.domain.model.VideoData;
import com.ethanhua.eyepetizer.viewmodel.AbsListViewModel;
import com.ethanhua.eyepetizer.module.video.viewmodel.VideoBaseVM;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Single;

import static com.ethanhua.domain.model.ItemData.TYPE_VIDEO;

/**
 * Created by ethanhua on 2017/10/30.
 */

public class RankListVM extends AbsListViewModel<VideoData, ListData<ItemData<VideoData>>> {
    private final GetVideosByRankCase getVideosByRankCase;
    private String rankStrategy;

    @Inject
    public RankListVM(GetVideosByRankCase getVideosByRankCase) {
        this.getVideosByRankCase = getVideosByRankCase;
    }

    @Override
    protected Single<ListData<ItemData<VideoData>>> provideSource(Map map) {
        return getVideosByRankCase.execute(rankStrategy, map);
    }

    @Override
    protected ViewModel convertItem(ItemData<VideoData> itemData) {
        if (TYPE_VIDEO.equals(itemData.type)) {
            return VideoBaseVM.mapFromVideo(itemData);
        }
        return null;
    }

    public void setRankStrategy(String rankStrategy) {
        this.rankStrategy = rankStrategy;
    }
}
