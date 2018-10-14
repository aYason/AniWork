package com.qgstudio.core.fragments.page;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import butterknife.BindView;
import com.joanzapata.iconify.widget.IconTextView;
import com.qgstudio.core.R;
import com.qgstudio.core.R2;
import com.qgstudio.core.fragments.BaseFragment;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import me.yokeyword.fragmentation.ISupportFragment;

/**
 * @author Yason
 * @since 2018/1/27
 */

public abstract class BasePageFragment extends BaseFragment implements OnClickListener {

  protected abstract LinkedHashMap<PageTabBean,PageItemFragment> setItems(ItemBuilder itemBuilder);
  protected abstract int setFirstIndex();
  @ColorInt
  protected abstract int setClickedColor();

  private final LinkedHashMap<PageTabBean, PageItemFragment> ITEMS = new LinkedHashMap<>();
  private final ArrayList<PageItemFragment> PAGES = new ArrayList<>();
  private final ArrayList<PageTabBean> TABS = new ArrayList<>();

  private int mCurrentIndex = 0;
  private int mClickedColor = Color.RED;
  private int mUnClickedColor = Color.GRAY;

  @BindView(R2.id.bottom_bar)
  LinearLayoutCompat mBottomBar;

  @Override
  protected Object setLayout() {
    return R.layout.fragment_base_page;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    final ItemBuilder itemBuilder = ItemBuilder.builder();
    final LinkedHashMap<PageTabBean, PageItemFragment> items = setItems(itemBuilder);
    ITEMS.putAll(items);

    for (Entry<PageTabBean, PageItemFragment> item : ITEMS.entrySet()) {
      final PageTabBean tab = item.getKey();
      final PageItemFragment page = item.getValue();

      TABS.add(tab);
      PAGES.add(page);
    }

    final int currentIndex = setFirstIndex();
    if (currentIndex >= 0 && currentIndex < ITEMS.size()) {
      mCurrentIndex = currentIndex;
    }

    final int clickedColor = setClickedColor();
    if (clickedColor != 0) {
      mClickedColor = clickedColor;
    }

  }

  @Override
  protected void onBindView(Bundle savedInstanceState, View rootView) {

    final int size = ITEMS.size();
    for (int index = 0; index < size; index++) {
      LayoutInflater.from(_mActivity).inflate(R.layout.item_bottom_tab, mBottomBar);
      RelativeLayout tab = (RelativeLayout) mBottomBar.getChildAt(index);
      tab.setTag(index);
      tab.setOnClickListener(this);

      final IconTextView itemIcon = tab.findViewById(R.id.icon_tab_item);
      final AppCompatTextView itemTitle = tab.findViewById(R.id.tv_tab_item);

      final PageTabBean bean = TABS.get(index);

      itemIcon.setText(bean.getICON());
      itemTitle.setText(bean.getTITLE());

      if (index == mCurrentIndex) {
        itemIcon.setTextColor(mClickedColor);
        itemTitle.setTextColor(mClickedColor);
      }
    }

    final ISupportFragment[] fragments = PAGES.toArray(new ISupportFragment[ITEMS.size()]);
    loadMultipleRootFragment(R.id.page_container, mCurrentIndex, fragments);

  }


  @Override
  public void onClick(View v) {
    final int index = (int) v.getTag();
    resetColor();

    final RelativeLayout tab = (RelativeLayout) v;
    final IconTextView itemIcon = (IconTextView) tab.getChildAt(0);
    final AppCompatTextView itemTitle = (AppCompatTextView) tab.getChildAt(1);

    itemIcon.setTextColor(mClickedColor);
    itemTitle.setTextColor(mClickedColor);

    showHideFragment(PAGES.get(index), PAGES.get(mCurrentIndex));
    mCurrentIndex = index;

  }

  private void resetColor() {
    final int count = mBottomBar.getChildCount();
    for(int index = 0;index<count;index++) {
      final RelativeLayout tab = (RelativeLayout) mBottomBar.getChildAt(index);

      final IconTextView itemIcon = (IconTextView) tab.getChildAt(0);
      final AppCompatTextView itemTitle = (AppCompatTextView) tab.getChildAt(1);

      itemIcon.setTextColor(mUnClickedColor);
      itemTitle.setTextColor(mUnClickedColor);
    }

  }


}
