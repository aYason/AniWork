package com.qgstudio.aniwork;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import com.qgstudio.core.activities.ProxyActivity;
import com.qgstudio.core.config.AccountManager;
import com.qgstudio.core.config.Config;
import com.qgstudio.core.fragments.BaseFragment;
import com.qgstudio.core.net.cache.CacheManager;
import com.qgstudio.ec.main.EcPageFragment;
import com.qgstudio.ec.sign.EnterFragment;
import com.qgstudio.ec.sign.ISignListener;
import com.qgstudio.ec.sign.SignInFragment;
import java.util.concurrent.Executors;

/**
 * @author Yason
 * @since 2018/1/6
 */

public class ShellActivity extends ProxyActivity implements ISignListener {

  @Override
  protected BaseFragment setRootFragment() {
    if (!AccountManager.isSignIn()) {
      return new EnterFragment();
    } else {
      return new EcPageFragment();
    }
//    return new TestFragment();
  }


  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    final ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.hide();
    }
    Config.getConfigurator().withActivity(this);
    CacheManager.getInstance().init(this);
  }

  @Override
  public void onSignInSuccess() {
    startWithPop(new EcPageFragment());
  }

  @Override
  public void onSignUpSuccess() {
    startWithPop(new SignInFragment());
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    CacheManager.getInstance().close();
  }
}
