package com.qgstudio.ec.database;


import android.content.Context;
import com.qgstudio.ec.entity.DaoMaster;
import com.qgstudio.ec.entity.DaoSession;
import org.greenrobot.greendao.database.Database;

/**
 * @author Yason
 * @since 2018/3/5
 */

public class DatabaseManager {

  private DaoSession mDaoSession;

  private DatabaseManager() {
  }

  public void init(Context context) {
    final ReleaseOpenHelper helper = new ReleaseOpenHelper(context, "aniwork_db");
    final Database db = helper.getWritableDb();
    mDaoSession = new DaoMaster(db).newSession();
  }

  public DaoSession getSession() {
    if (mDaoSession == null) {
      throw new IllegalStateException("call init()!");
    }
    return mDaoSession;
  }

  public static DatabaseManager getInstance() {
    return Holder.INSTANCE;
  }

  private static final class Holder{

    private static final DatabaseManager INSTANCE = new DatabaseManager();
  }
}
