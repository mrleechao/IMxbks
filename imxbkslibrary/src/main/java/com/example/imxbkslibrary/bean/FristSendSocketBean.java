package com.example.imxbkslibrary.bean;

/**
 * Time:2020/5/12
 * <p>
 * Author:lichao
 * <p>
 * Description:
 */
public class FristSendSocketBean {
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

    public class Params{
        String type="system";//text image
        String uid; //用户id
        String uname;//用户名
        String mobile;//电话
        String avatar;//头像
        String is_shops="0";//身份(客服1，用户0),
        String city_text;//用户地区(get-user-info返回)
        String role;//身份(客服1，用户0) ,
        int service ;
        String fid;

        public String getFid() {
            return fid;
        }

        public void setFid(String fid) {
            this.fid = fid;
        }

        public int getService() {
            return service;
        }

        public void setService(int service) {
            this.service = service;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getUname() {
            return uname;
        }

        public void setUname(String uname) {
            this.uname = uname;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getIs_shops() {
            return is_shops;
        }

        public void setIs_shops(String is_shops) {
            this.is_shops = is_shops;
        }

        public String getCity_text() {
            return city_text;
        }

        public void setCity_text(String city_text) {
            this.city_text = city_text;
        }
    }
}
