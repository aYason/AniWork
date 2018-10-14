package com.qgstudio.ec.main.index;

import android.os.Bundle;
import android.view.View;
import com.qgstudio.core.fragments.BaseFragment;
import com.qgstudio.core.fragments.page.PageItemFragment;
import com.qgstudio.ec.R;

/**
 * 主页
 * @author Yason
 * @since 2018/1/27
 */

public class IndexFragment extends PageItemFragment {

  @Override
  protected Object setLayout() {
    return R.layout.fragment_index;
  }

  @Override
  protected void onBindView(Bundle savedInstanceState, View rootView) {

  }
}
