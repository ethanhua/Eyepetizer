package com.ethanhua.eyepetizer.module.category.viewmodel;

import com.ethanhua.commonlib.viewmodel.ViewModel;
import com.ethanhua.domain.interactor.GetAllCategoryCase;
import com.ethanhua.domain.model.Category;
import com.ethanhua.domain.model.ItemData;
import com.ethanhua.domain.model.ListData;
import com.ethanhua.eyepetizer.viewmodel.AbsListViewModel;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by ethanhua on 2017/10/3.
 */

public class CategoriesVM extends AbsListViewModel<Category, ListData<ItemData<Category>>> {
    private final GetAllCategoryCase mGetAllCategoryCase;

    @Inject
    public CategoriesVM(GetAllCategoryCase getAllCategoryCase) {
        this.mGetAllCategoryCase = getAllCategoryCase;
    }


    @Override
    protected Single<ListData<ItemData<Category>>> provideSource(Map map) {
        return mGetAllCategoryCase.execute();
    }

    @Override
    protected ViewModel convertItem(ItemData<Category> itemData) {
        return CategoryBaseVM.mapFrom(itemData);
    }
}
