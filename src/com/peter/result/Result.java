package com.peter.result;

public class Result<T> {
    private int code;//������
    private String msg;//������Ϣ
    private T data;//��������
    public Result(T data, int code, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    public Result() {
    }

    public Result<T> result(NetReturn netReturn){
        this.code=netReturn.code();
        this.msg=netReturn.msg();
        return this;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }
}
