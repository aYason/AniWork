package com.qgstudio.core.fragments.page;

/**
 * @author Yason
 * @since 2018/1/27
 */

public final class PageTabBean {

  private final String ICON;
  private final String TITLE;


  public PageTabBean(String icon, String title) {
    this.ICON = icon;
    this.TITLE = title;
  }

  public String getICON() {
    return ICON;
  }

  public String getTITLE() {
    return TITLE;
  }
}
