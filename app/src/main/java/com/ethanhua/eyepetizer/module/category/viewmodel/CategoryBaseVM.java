package com.ethanhua.eyepetizer.module.category.viewmodel;

import android.databinding.ObservableField;

import com.ethanhua.commonlib.viewmodel.ViewModel;
import com.ethanhua.domain.model.Category;
import com.ethanhua.domain.model.CategoryInfoData;
import com.ethanhua.domain.model.ItemData;

/**
 * Created by ethanhua on 2017/10/3.
 */

public class CategoryBaseVM extends ViewModel {

    public long id;
    public String type;
    public String actionUrl;
    public final ObservableField<String> title = new ObservableField<>();
    public final ObservableField<String> description = new ObservableField<>();
    public final ObservableField<String> image = new ObservableField<>();

    public static CategoryBaseVM mapFrom(ItemData<Category> categoryItemData) {
        CategoryBaseVM categoryBaseVM = new CategoryBaseVM();
        categoryBaseVM.actionUrl = categoryItemData.data.actionUrl;
        categoryBaseVM.image.set(categoryItemData.data.image);
        categoryBaseVM.title.set(categoryItemData.data.title);
        categoryBaseVM.type = categoryItemData.type;
        categoryBaseVM.id = categoryItemData.data.id;
        return categoryBaseVM;
    }

    public static CategoryBaseVM mapFromCategoryInfo(CategoryInfoData categoryInfoData) {
        CategoryBaseVM categoryBaseVM = new CategoryBaseVM();
        categoryBaseVM.image.set(categoryInfoData.categoryInfo.headerImage);
        categoryBaseVM.title.set(categoryInfoData.categoryInfo.name);
        categoryBaseVM.description.set(categoryInfoData.categoryInfo.description);
        categoryBaseVM.id = categoryInfoData.categoryInfo.id;
        return categoryBaseVM;
    }
}
