package com.example.imxbkslibrary.bean;

/**
 * Time:2020/5/16
 * <p>
 * Author:lichao
 * <p>
 * Description:
 */
public class PullHistoryData {
    String instruct="b003";
    Params params;

    public String getInstruct() {
        return instruct;
    }

    public void setInstruct(String instruct) {
        this.instruct = instruct;
    }

    public Params getParams() {
        return params;
    }

    public void setParams(Params params) {
        this.params = params;
    }

    public static class Params{
        String type;
        String userid;
        String  page;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
            this.page = page;
        }
    }
}
