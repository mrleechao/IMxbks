package com.example.imxbkslibrary.bean;

/**
 * Time:2020/5/11
 * <p>
 * Author:lichao
 * <p>
 * Description:
 */
public class ChatListBean {
    String massage;
    int status=1;
    String time;




    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public ChatListBean(String massage, int status,String time) {
        this.massage = massage;
        this.status = status;
        this.time=time;

    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
