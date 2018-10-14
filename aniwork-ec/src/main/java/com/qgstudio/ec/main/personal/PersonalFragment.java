package com.qgstudio.ec.main.personal;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract.Data;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.alibaba.fastjson.JSON;
import com.qgstudio.core.fragments.page.PageItemFragment;
import com.qgstudio.core.net.HttpMethod;
import com.qgstudio.core.net.RestClient;
import com.qgstudio.core.net.callback.IError;
import com.qgstudio.core.net.callback.IFailure;
import com.qgstudio.core.net.callback.ISuccess;
import com.qgstudio.core.util.toast.ToastUtil;
import com.qgstudio.ec.R;
import com.qgstudio.ec.R2;
import com.qgstudio.ec.database.DatabaseManager;
import com.qgstudio.ec.entity.User;
import com.qgstudio.ec.entity.UserDao;
import de.hdodenhof.circleimageview.CircleImageView;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 我的
 *
 * @author Yason
 * @since 2018/1/27
 */

public class PersonalFragment extends PageItemFragment {

  private boolean isEdit = false;
  private boolean isFinish = true;
  private User user;

  @BindView(R2.id.head_pic)
  CircleImageView pic;

  @BindView(R2.id.name)
  EditText name;

  @BindView(R2.id.mail)
  TextView email;

  @BindView(R2.id.phone)
  EditText phone;

  @BindView(R2.id.edit)
  Button edit;

  @OnClick(R2.id.exit)
  public void exit() {
//    backResult();
  }

  @OnClick(R2.id.edit)
  public void edit() {
    if (isFinish) {
      changeEditState(true);
    } else {
      if (checkForm()) {
        changeInfo();
        changeEditState(false);
      }
    }
    isFinish = !isFinish;
  }

  private void changeInfo() {
    User nUser = new User(user);
    nUser.setUserName(name.getText().toString());
    nUser.setPhone(phone.getText().toString());

    Map<String, String> info = new HashMap<>();
    info.put("userName", user.getUserName());
    info.put("phone", user.getPhone());
    info.put("email", user.getEmail());

    RestClient.builder()
        .url("user/update")
        .method(HttpMethod.POST_RAW)
        .raw(JSON.toJSONString(info))
        .success(new ISuccess() {
          @Override
          public void onSuccess(String response) {
//            App.getInstance().setUser(user);
            ToastUtil.showShort("信息修改完成");
          }
        })
        .build()
        .execute();
  }

  private void changeEditState(boolean editable) {
    if (editable) {
      editFocusable(true);
      edit.setText("完成");
      edit.setBackgroundResource(R.drawable.bg_btn_blue);
    } else {
      editFocusable(false);
      edit.setText("编辑");
      edit.setBackgroundResource(R.drawable.bg_btn_yellow);
    }
  }

  private boolean checkForm() {
    boolean isPass = true;

    String n = name.getText().toString();
    String p = phone.getText().toString();
    if (!n.matches("[a-z0-9A-Z\\u4e00-\\u9fa5]{1,15}")) {
      ToastUtil.showShort("请输入1-15个字符的姓名");
      isPass = false;
    }
    if (!p.matches("^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$")) {
      ToastUtil.showShort("请输入正确的电话号码");
      isPass = false;
    }
    return isPass;
  }

  @OnClick(R2.id.head_pic)
  public void changePic() {
    // 调用系统图库获取图片
    Intent intent = new Intent(Intent.ACTION_PICK,
        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
    startActivityForResult(intent, 0);
  }

//  private void setInfo(User user1) {
//    name.setText(user1.getUserName());
//    email.setText(user1.getEmail());
//    phone.setText(user1.getPhone());
//    GlideUtil.setPictureWithOutCache(pic, user1.getUserId(), R.drawable.ic_user_default);
//  }
//
  private void editFocusable(boolean focusable) {
    name.setEnabled(focusable);
    phone.setEnabled(focusable);
    if (focusable) {
      name.requestFocus();
      phone.requestFocus();
    }
  }
//
//  @Override
//  public boolean onKeyDown(int keyCode, KeyEvent event) {
//    // 是否触发按键为 back 键，并为 back 键设置监听
//    if (keyCode == KeyEvent.KEYCODE_BACK) {
//      backResult();
//      return true;
//    } else {
//      return super.onKeyDown(keyCode, event);
//    }
//  }
//
//  private void backResult() {
//    // 实例化 Bundle，设置需要传递的参数
//    Bundle bundle = new Bundle();
//
//    if (isEdit) {
//      bundle.putParcelable("user", user);
//    } else {
//      bundle.putParcelable("user", null);
//    }
//    // 将修改后的用户信息返回给主页面
//    setResult(RESULT_OK, this.getIntent().putExtras(bundle));
//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//      finishAfterTransition();
//    } else {
//      finish();
//    }
//  }
//
//
//  @Override
//  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//    super.onActivityResult(requestCode, resultCode, data);
//    if (requestCode == 0 && resultCode == RESULT_OK) {
//      Uri uri = data.getData();
//      if (uri != null) {
//        // 将图片设置到头像中
//        Glide.with(this)
//            .load(uri)
//            .asBitmap()
//            .into(new SimpleTarget<Bitmap>(250, 250) {
//              @Override
//              public void onResourceReady(Bitmap resource,
//                  GlideAnimation<? super Bitmap> glideAnimation) {
//                saveHeadPic(resource);
//              }
//            });
//      }
//    }
//  }
//
//  /**
//   * 将获取的图片进行压缩并保存
//   */
//  private void saveHeadPic(Bitmap bitmap) {
//    if (!StorageUtil.isExternalStorageWritable()) {
//      ToastUtil.showToast("保存头像失败！");
//      return;
//    }
//    //创建目录
//    File dir = StorageUtil.getStoragePrivateDir(this, "anywork");
//    if (dir == null) {
//      ToastUtil.showToast("保存头像失败！");
//      return;
//    }
//    //创建图片
//    File file = new File(dir, user.getUserId() + ".jpg");
//    try {
//      if (!file.exists()) {
//        if (!file.createNewFile()) {
//          ToastUtil.showToast("保存头像失败！");
//          return;
//        }
//      }
//      //保存图片
//      saveImage(bitmap, file);
//      mPresenter.changePic(file.getAbsolutePath());
//    } catch (IOException e) {
//      ToastUtil.showToast("保存头像失败！");
//      LogUtil.e2(TAG, "saveHeadPic", "save image fail!");
//    }
//  }
//
//  /**
//   * bitmap后存到对应路径
//   */
//  public void saveImage(Bitmap photo, File file) {
//    try {
//      BufferedOutputStream bos = new BufferedOutputStream(
//          new FileOutputStream(file, false));
//      photo.compress(Bitmap.CompressFormat.JPEG, 100, bos);
//      bos.flush();
//      bos.close();
//    } catch (Exception e) {
//      LogUtil.e2(TAG, "saveImage", "error：" + e.getMessage());
//    }
//  }
//
//  @Override
//  public void changeImg() {
//    GlideUtil.setPictureWithOutCache(pic, user.getUserId(), R.drawable.ic_user_default);
//  }
//
//  @Override
//  public void onCreate(@Nullable Bundle savedInstanceState) {
//    super.onCreate(savedInstanceState);
//    UserDao userDao = DatabaseManager.getInstance().getSession().getUserDao();
//    User user =
//    setInfo(user);
//  }

  @Override
  protected Object setLayout() {
    return R.layout.fragment_personal;
  }

  @Override
  protected void onBindView(Bundle savedInstanceState, View rootView) {
    editFocusable(false);
  }


}
