package com.qgstudio.ec.main;


import android.graphics.Color;
import com.qgstudio.core.fragments.page.BasePageFragment;
import com.qgstudio.core.fragments.page.ItemBuilder;
import com.qgstudio.core.fragments.page.PageItemFragment;
import com.qgstudio.core.fragments.page.PageTabBean;
import com.qgstudio.ec.main.discover.DiscoverFragment;
import com.qgstudio.ec.main.index.IndexFragment;
import com.qgstudio.ec.main.personal.PersonalFragment;
import java.util.LinkedHashMap;

/**
 * @author Yason
 * @since 2018/1/6
 */

public class EcPageFragment extends BasePageFragment{

  @Override
  protected LinkedHashMap<PageTabBean, PageItemFragment> setItems(ItemBuilder itemBuilder) {
    itemBuilder
        .addItem(new PageTabBean("{fa-home}", "主页"), new IndexFragment())
        .addItem(new PageTabBean("{fa-compass}", "发现"), new DiscoverFragment())
        .addItem(new PageTabBean("{fa-user}", "我的"), new PersonalFragment());
    return itemBuilder.build();
  }

  @Override
  protected int setFirstIndex() {
    return 0;
  }

  @Override
  protected int setClickedColor() {
    return Color.parseColor("#ffff8800");
  }

}
