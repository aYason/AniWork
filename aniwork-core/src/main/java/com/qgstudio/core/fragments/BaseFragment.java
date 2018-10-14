package com.qgstudio.core.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * @author Yason
 * @since 2018/1/6
 */

public abstract class BaseFragment extends SwipeBackFragment {

  protected abstract Object setLayout();
  protected abstract void onBindView(Bundle savedInstanceState, View rootView);

  private Unbinder mUnbinder = null;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    final View rootView;
    if (setLayout() instanceof Integer) {
      rootView = inflater.inflate((Integer) setLayout(), container, false);
    } else if (setLayout() instanceof View) {
      rootView = (View) setLayout();
    } else {
      throw new ClassCastException("setLayout() type must be int or View!");
    }

    mUnbinder = ButterKnife.bind(this, rootView);
    onBindView(savedInstanceState, rootView);

    return rootView;
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    if (mUnbinder != null) {
      mUnbinder.unbind();
    }
  }

}
