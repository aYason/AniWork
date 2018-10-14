package com.qgstudio.core.util.dimen;

import android.content.res.Resources;
import android.util.DisplayMetrics;
import com.qgstudio.core.config.Config;

/**
 * @author Yason
 * @since 2018/1/29
 */

public final class DimenUtil {

  private DimenUtil() {}

  public static int getScreenWidth() {
    final Resources resources = Config.getApplicationContext().getResources();
    final DisplayMetrics dm = resources.getDisplayMetrics();
    return dm.widthPixels;
  }

  public static int getScreenHeight() {
    final Resources resources = Config.getApplicationContext().getResources();
    final DisplayMetrics dm = resources.getDisplayMetrics();
    return dm.heightPixels;
  }

}
