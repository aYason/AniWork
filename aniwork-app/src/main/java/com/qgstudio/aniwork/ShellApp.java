package com.qgstudio.aniwork;

import android.app.Application;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.qgstudio.core.config.Config;
import com.qgstudio.core.net.interceptor.MockInterceptor;
import com.qgstudio.ec.database.DatabaseManager;

/**
 * @author Yason
 * @since 2018/1/6
 */

public class ShellApp extends Application{

  @Override
  public void onCreate() {
    super.onCreate();

    Config.init(this)
        .withApiHost("http://10.21.48.11:8080/anywork/")
        .withIcon(new FontAwesomeModule())
        .withInterceptor(new MockInterceptor("user/register", R.raw.sign_up))
        .withInterceptor(new MockInterceptor("user/login", R.raw.sign_in))
        .configure();
    DatabaseManager.getInstance().init(this);
//    initStetho();
  }

//  private void initStetho() {
//    Stetho.initialize(
//        Stetho.newInitializerBuilder(this)
//            .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
//            .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
//            .build());
//  }

}
