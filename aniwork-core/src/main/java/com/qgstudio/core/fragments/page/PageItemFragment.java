package com.qgstudio.core.fragments.page;

import com.qgstudio.core.R;
import com.qgstudio.core.config.Config;
import com.qgstudio.core.fragments.BaseFragment;
import com.qgstudio.core.util.toast.ToastUtil;

/**
 * @author Yason
 * @since 2018/1/27
 */

public abstract class PageItemFragment extends BaseFragment {

  // 再点一次退出程序时间设置
  private static final long WAIT_TIME = 2000L;
  private long TOUCH_TIME = 0;

  @Override
  public boolean onBackPressedSupport() {
    if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
      _mActivity.finish();
    } else {
      TOUCH_TIME = System.currentTimeMillis();
      ToastUtil.showShort("双击退出" + Config.getApplicationContext().getString(R.string.app_name));
    }
    return true;
  }

}
