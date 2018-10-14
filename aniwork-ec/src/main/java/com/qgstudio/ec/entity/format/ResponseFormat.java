package com.qgstudio.ec.entity.format;

/**
 * @author Yason
 * @since 2018/3/5
 */

public class ResponseFormat<T> {

  private int state;
  private String stateInfo;
  private T data;

  public void setState(int state) {
    this.state = state;
  }

  public void setStateInfo(String stateInfo) {
    this.stateInfo = stateInfo;
  }

  public void setData(T data) {
    this.data = data;
  }

  public int getState() {
    return state;
  }

  public String getStateInfo() {
    return stateInfo;
  }

  public T getData() {
    return data;
  }

  @Override
  public String toString() {
    return "ResponseFormat{" +
        "state=" + state +
        ", stateInfo='" + stateInfo + '\'' +
        ", data=" + data +
        '}';
  }
}
