package com.peter.result;

public enum NetReturn {
    //��ͨ�������
    SERVER_ERROR(99,"�����쳣����ϵͳ����"),
    SUCCESS(100,"�����ɹ�"),

    //�û����
    USER_NOT_EXIST(201,"�û�������"),
    USER_PASS_ERR(202,"�������"),
    USER_NAME_EXIST(203,"���û����Ѿ�����"),
    USER_PHONE_EXIST(204,"���ֻ����Ѿ���ע��"),
    USER_OLD_PASS_ERR(205,"ԭ�������"),
    USER_MSG_LATEST(206,"��Ϣ�����Ѿ�����");

    //ͼƬ���
    private int code;
    private String msg;

    NetReturn(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int code() {
        return this.code;
    }

    public String msg() {
        return this.msg;
    }
}
