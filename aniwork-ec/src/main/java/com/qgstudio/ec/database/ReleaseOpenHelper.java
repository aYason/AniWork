package com.qgstudio.ec.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import com.qgstudio.ec.entity.DaoMaster;
import com.qgstudio.ec.entity.DaoSession;

/**
 * @author Yason
 * @since 2018/3/5
 */

public class ReleaseOpenHelper extends DaoMaster.OpenHelper{

  public ReleaseOpenHelper(Context context, String name) {
    super(context, name);
  }

  public ReleaseOpenHelper(Context context, String name,
      CursorFactory factory) {
    super(context, name, factory);
  }
}
