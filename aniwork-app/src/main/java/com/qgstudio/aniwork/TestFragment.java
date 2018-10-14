package com.qgstudio.aniwork;

import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.View.OnClickListener;
import com.qgstudio.core.fragments.BaseFragment;
import com.qgstudio.core.net.RestClient;
import com.qgstudio.core.net.callback.IError;
import com.qgstudio.core.net.callback.IFailure;
import com.qgstudio.core.net.callback.ISuccess;
import com.qgstudio.core.util.toast.ToastUtil;

/**
 * @author Yason
 * @since 2018/1/29
 */

public class TestFragment extends BaseFragment {

  AppCompatButton appCompatButton;

  @Override
  protected Object setLayout() {
    return R.layout.fragment_test;
  }

  @Override
  protected void onBindView(Bundle savedInstanceState, View rootView) {
    appCompatButton = rootView.findViewById(R.id.btn);
    appCompatButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        RestClient.builder()
            .url("https://www.baidu.com/?tn=90278658_hao_pg")
            .expire(60)
            .success(new ISuccess() {
              @Override
              public void onSuccess(String response) {
                ToastUtil.showShort(response);
              }
            })
            .error(new IError() {
              @Override
              public void onError(int code, String msg) {
                ToastUtil.showShort(msg);
              }
            })
            .failure(new IFailure() {
              @Override
              public void onFailure() {
                ToastUtil.showShort("failure");
              }
            })
            .build()
            .execute();

      }
    });
  }

}
