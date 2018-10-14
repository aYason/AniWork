package com.qgstudio.ec.entity;

import android.os.Parcel;
import android.os.Parcelable;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 用户实体
 * Created by FunriLy on 2017/7/10.
 * From small beginnings comes great things.
 */
@Entity
public class User {

    @Id
    private long userId;         //id
    private String userName;    //昵称
    private String email;       //邮箱
    private String password;    //密码
    private String phone;       //手机
    private int mark;       //标志，区分是学生还是教师, 0学生，1老师
    private int valcode;    //验证码

    public User() {
        this.userId = -1;
        this.userName = "";
        this.email = "";
        this.password = "";
        this.phone = "";
        mark = 0;
        this.valcode = 0;
    }

    @Generated(hash = 1180798536)
    public User(long userId, String userName, String email, String password, String phone, int mark,
            int valcode) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.mark = mark;
        this.valcode = valcode;
    }

    public User(User user) {
        this.userId = user.userId;
        this.userName = user.userName;
        this.email = user.email;
        this.password = user.password;
        this.phone = user.phone;
        this.mark = user.mark;
        this.valcode = user.valcode;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", mark=" + mark +
                '}';
    }

    public int getMark() {
        return this.mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public int getValcode() {
        return this.valcode;
    }

    public void setValcode(int valcode) {
        this.valcode = valcode;
    }

}
