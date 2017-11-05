package com.ethanhua.domain.respository;

import com.ethanhua.domain.model.Category;
import com.ethanhua.domain.model.CategoryInfoData;
import com.ethanhua.domain.model.ItemData;
import com.ethanhua.domain.model.ListData;

import io.reactivex.Single;

/**
 * Created by ethanhua on 2017/10/3.
 */

public interface CategoryRepository {

    Single<ListData<ItemData<Category>>> list();

    Single<CategoryInfoData> get(long id);

}

