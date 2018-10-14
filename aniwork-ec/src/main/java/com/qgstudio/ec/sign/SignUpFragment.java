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
import com.qgstudio.ec.R;
import com.qgstudio.ec.R2;
import java.util.HashMap;

/**
 * 注册
 * @author Yason
 * @since 2018/3/4
 */

public class SignUpFragment extends BaseFragment{

  @BindView(R2.id.account) TextInputEditText account;
  @BindView(R2.id.name) TextInputEditText name;
  @BindView(R2.id.phone) TextInputEditText phone;
  @BindView(R2.id.password) TextInputEditText password;
  @BindView(R2.id.password2) TextInputEditText password2;

  private ISignListener mISignListener;

  @Override
  public void onAttach(Activity activity) {
    super.onAttach(activity);
    if (activity instanceof ISignListener) {
      mISignListener = (ISignListener) activity;
    }
  }

  @OnClick(R2.id.sign_up)
  public void onSignUpClick() {
    if (checkForm()) {
      RestClient.builder()
          .url("user/register")
          .method(HttpMethod.POST_RAW)
          .raw(getPostString())
          .success(new ISuccess() {
            @Override
            public void onSuccess(String response) {
              SignHanler.onSignUp(response, mISignListener);
            }
          })
          .build()
          .execute();
    }
  }

  private String getPostString() {
    HashMap<String, String> user = new HashMap<>();
    user.put("email", account.getText().toString());
    user.put("password", password.getText().toString());
    user.put("userName", name.getText().toString());
    user.put("phone", phone.getText().toString());
    user.put("mark", "0");
    user.put("valcode", "0");

    return JSON.toJSONString(user);
  }

  private boolean checkForm() {

    final String a = account.getText().toString();
    final String n = name.getText().toString();
    final String p = phone.getText().toString();
    final String pass1 = password.getText().toString();
    final String pass2 = password2.getText().toString();

    boolean isPass = true;

    if (!n.matches("[a-z0-9A-Z\\u4e00-\\u9fa5]{1,15}")) {
      name.setError("请输入1-15个字符的姓名");
      isPass = false;
    } else {
      name.setError(null);
    }

    if (!a.matches("\\w+@\\w+(\\.\\w{2,3})*\\.\\w{2,3}")) {
      account.setError("请输入正确的邮箱地址");
      isPass = false;
    } else {
      account.setError(null);
    }

    if (!p.matches("^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$")) {
      phone.setError("请输入正确的电话号码");
      isPass = false;
    } else {
      phone.setError(null);
    }

    if (!pass1.matches("[a-z0-9A-Z]{6,15}")) {
      password.setError("请输入6-15位的密码");
      isPass = false;
    } else {
      password.setError(null);

    }

    if (pass2.length() ==0 || !pass2.equals(pass1)) {
      password2.setError("请输入相同的确认密码！");
      isPass = false;
    } else {
      password2.setError(null);
    }

    return isPass;
  }


  @Override
  protected Object setLayout() {
    return R.layout.fragment_signup;
  }

  @Override
  protected void onBindView(Bundle savedInstanceState, View rootView) {

  }
}
