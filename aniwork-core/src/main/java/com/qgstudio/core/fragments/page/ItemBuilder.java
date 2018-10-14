package com.qgstudio.core.fragments.page;

import java.util.LinkedHashMap;

/**
 * @author Yason
 * @since 2018/1/27
 */

public final class ItemBuilder {

  private final LinkedHashMap<PageTabBean, PageItemFragment> ITEMS = new LinkedHashMap();

  static ItemBuilder builder() {
    return new ItemBuilder();
  }

  public ItemBuilder addItem(PageTabBean tab, PageItemFragment page) {
    ITEMS.put(tab, page);
    return this;
  }

  public ItemBuilder addAll(LinkedHashMap<PageTabBean, PageItemFragment> items) {
    ITEMS.putAll(items);
    return this;
  }

  public LinkedHashMap<PageTabBean, PageItemFragment> build() {
    return ITEMS;
  }

}
