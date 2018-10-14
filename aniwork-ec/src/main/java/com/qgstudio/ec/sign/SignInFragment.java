package com.qgstudio.ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import butterknife.BindView;
import butterknife.OnClick;
import com.alibaba.fastjson.JSON;
import com.qgstudio.core.fragments.BaseFragment;
import com.qgstudio.core.net.HttpMethod;
import com.qgstudio.core.net.RestClient;
import com.qgstudio.core.net.callback.ISuccess;
import com.qgstudio.core.util.log.LoggerUtil;
import com.qgstudio.core.util.toast.ToastUtil;
import com.qgstudio.ec.R;
import com.qgstudio.ec.R2;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Yason
 * @since 2018/3/4
 */

public class SignInFragment extends BaseFragment {


  @BindView(R2.id.account) TextInputEditText account;
  @BindView(R2.id.password) TextInputEditText password;

  private ISignListener mISignListener;

  @Override
  public void onAttach(Activity activity) {
    super.onAttach(activity);
    if (activity instanceof ISignListener) {
      mISignListener = (ISignListener) activity;
    }
  }

  @OnClick(R2.id.sign_in)
  public void onSignInClick() {
    if (checkForm()) {
      RestClient.builder()
          .url("user/login")
          .method(HttpMethod.POST_RAW)
          .raw(getPostString())
          .success(new ISuccess() {
            @Override
            public void onSuccess(String response) {
              ToastUtil.showLong(response);
              SignHanler.onSignIn(response, mISignListener);
            }
          })
          .build()
          .execute();
    }
  }

  private String getPostString() {
    Map<String, String> loginInfo = new HashMap<>();
    loginInfo.put("valcode", "0");
    loginInfo.put("email", account.getText().toString());
    loginInfo.put("password", password.getText().toString());

    return JSON.toJSONString(loginInfo);
  }

  @OnClick(R2.id.forget)
  public void onForgetClick() {
    ToastUtil.showShort("请登入网页端进行操作！");
  }

  @OnClick(R2.id.cancel)
  public void onCancelClick() {
    pop();
  }

  private boolean checkForm() {

    final String a = account.getText().toString();
    final String pass = password.getText().toString();

    boolean isPass = true;

    if (!a.matches("\\w+@\\w+(\\.\\w{2,3})*\\.\\w{2,3}")) {
      account.setError("请输入正确的邮箱地址");
      isPass = false;
    } else {
      account.setError(null);
    }

    if (!pass.matches("[a-z0-9A-Z]{6,15}")) {
      password.setError("请输入6-15位的密码");
      isPass = false;
    } else {
      password.setError(null);

    }

    return isPass;
  }

  @Override
  protected Object setLayout() {
    return R.layout.fragment_signin;
  }

  @Override
  protected void onBindView(Bundle savedInstanceState, View rootView) {
  }

}
