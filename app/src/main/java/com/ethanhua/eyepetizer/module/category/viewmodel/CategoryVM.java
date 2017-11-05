package com.ethanhua.eyepetizer.module.category.viewmodel;

import android.databinding.ObservableField;

import com.ethanhua.commonlib.viewmodel.ViewModel;
import com.ethanhua.domain.interactor.GetCategoryInfoCase;

import javax.inject.Inject;

import static com.ethanhua.eyepetizer.module.category.viewmodel.CategoryBaseVM.mapFromCategoryInfo;

/**
 * Created by ethanhua on 2017/10/3.
 */

public class CategoryVM extends ViewModel {
    public ObservableField<CategoryBaseVM> categoryBaseVM = new ObservableField<>(new CategoryBaseVM());
    private GetCategoryInfoCase mGetCategoryInfoCase;

    @Inject
    public CategoryVM(GetCategoryInfoCase getCategoryInfoCase) {
        this.mGetCategoryInfoCase = getCategoryInfoCase;
    }

    public void getCategoryInfo(long id) {
        mGetCategoryInfoCase.execute(id)
                .subscribe(infoData -> categoryBaseVM.set(mapFromCategoryInfo(infoData)),
                        Throwable::printStackTrace);
    }
}
