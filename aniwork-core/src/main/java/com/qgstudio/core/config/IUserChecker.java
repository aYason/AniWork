package com.qgstudio.core.config;

/**
 * @author Yason
 * @since 2018/3/5
 */

public interface IUserChecker {
  void onSignIn();
  void onNotSignIn();
}
