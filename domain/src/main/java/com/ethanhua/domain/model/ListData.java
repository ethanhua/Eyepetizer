package com.ethanhua.domain.model;

import java.util.List;

/**
 * Created by ethanhua on 2017/10/3.
 */

public class ListData<T extends ItemData<?>> {
    public List<T> itemList;
    public int count;
    public String nextPageUrl;
    public int lastStartId;
    public int total;
    public int refreshCount;
}
