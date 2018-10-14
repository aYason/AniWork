package com.qgstudio.core.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;
import com.qgstudio.core.R;
import com.qgstudio.core.fragments.BaseFragment;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * @author Yason
 * @since 2018/1/6
 */

public abstract class ProxyActivity extends SupportActivity {

  protected abstract BaseFragment setRootFragment();

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initContainer(savedInstanceState);
  }

  private void initContainer(Bundle savedInstanceState) {
    final ContentFrameLayout container = new ContentFrameLayout(this);
    container.setId(R.id.fragment_container);
    setContentView(container);
    if (savedInstanceState == null) {
      loadRootFragment(R.id.fragment_container,setRootFragment());
    }
  }

}
