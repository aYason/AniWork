package com.qgstudio.ec.sign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import butterknife.OnClick;
import com.qgstudio.core.fragments.BaseFragment;
import com.qgstudio.core.util.toast.ToastUtil;
import com.qgstudio.ec.R;
import com.qgstudio.ec.R2;

/**
 * @author Yason
 * @since 2018/3/5
 */

public class EnterFragment extends BaseFragment {

  @OnClick(R2.id.sign_in)
  public void onSignInClick() {
    start(new SignInFragment());
  }

  @OnClick(R2.id.sign_up)
  public void onSignUpClick() {
    start(new SignUpFragment());
  }

  @OnClick(R2.id.visitors)
  public void onVisitorsClick() {
    ToastUtil.showShort("功能暂未开放！");
  }

  @Override
  protected Object setLayout() {
    return R.layout.fragment_enter;
  }

  @Override
  protected void onBindView(Bundle savedInstanceState, View rootView) {

  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }
}
