package com.qgstudio.ec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.qgstudio.core.config.AccountManager;
import com.qgstudio.ec.database.DatabaseManager;
import com.qgstudio.ec.entity.User;
import com.qgstudio.ec.entity.format.ResponseFormat;

/**
 * @author Yason
 * @since 2018/3/5
 */

public class SignHanler {

  public static void onSignIn(String response, ISignListener mISignListener) {

    final ResponseFormat<User> formatResponse = JSON
        .parseObject(response, new TypeReference<ResponseFormat<User>>() {});
    if (formatResponse.getState() == 1) {
      final User user = formatResponse.getData();
      DatabaseManager.getInstance().getSession().getUserDao().insert(user);

      //保存登入状态
      AccountManager.setSignState(true);
      mISignListener.onSignInSuccess();
    }
  }

  public static void onSignUp(String response, ISignListener mISignListener) {
    final ResponseFormat<Integer> formatResponse = JSON
        .parseObject(response, new TypeReference<ResponseFormat<Integer>>(){});
    if (formatResponse.getState() == 1) {
      mISignListener.onSignUpSuccess();
    }
  }
}
