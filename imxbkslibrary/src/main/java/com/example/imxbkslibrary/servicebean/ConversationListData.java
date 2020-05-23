package com.example.imxbkslibrary.servicebean;

import java.util.List;

/**
 * Time:2020/5/19
 * <p>
 * Author:lichao
 * <p>
 * Description:
 */
public class ConversationListData {

    /**
     * code : 0
     * message : {"type":"system","system":100,"data":[{"avatar":"https://imgxbhc.xbhc.com.cn/Uploads/images/202005/2020051116340713560671580.jpg","city_text":"广东-中山","is_shops":"0","mobile":"13560671580","role":"0","uid":"1761191","uname":"momo","lastmsg":"6666","lasttime":1589873361}]}
     */

    private String code;
    private MessageBean message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public MessageBean getMessage() {
        return message;
    }

    public void setMessage(MessageBean message) {
        this.message = message;
    }

    public static class MessageBean {
        /**
         * type : system
         * system : 100
         * data : [{"avatar":"https://imgxbhc.xbhc.com.cn/Uploads/images/202005/2020051116340713560671580.jpg","city_text":"广东-中山","is_shops":"0","mobile":"13560671580","role":"0","uid":"1761191","uname":"momo","lastmsg":"6666","lasttime":1589873361}]
         */

        private String type;
        private int system;
        private List<DataBean> data;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getSystem() {
            return system;
        }

        public void setSystem(int system) {
            this.system = system;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * avatar : https://imgxbhc.xbhc.com.cn/Uploads/images/202005/2020051116340713560671580.jpg
             * city_text : 广东-中山
             * is_shops : 0
             * mobile : 13560671580
             * role : 0
             * uid : 1761191
             * uname : momo
             * lastmsg : 6666
             * lasttime : 1589873361
             */

            private String avatar;
            private String city_text;
            private String is_shops;
            private String mobile;
            private String role;
            private String uid;
            private String uname;
            private String lastmsg;
            private String lasttime;
            private String type="";

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getCity_text() {
                return city_text;
            }

            public void setCity_text(String city_text) {
                this.city_text = city_text;
            }

            public String getIs_shops() {
                return is_shops;
            }

            public void setIs_shops(String is_shops) {
                this.is_shops = is_shops;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getRole() {
                return role;
            }

            public void setRole(String role) {
                this.role = role;
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

            public String getLastmsg() {
                return lastmsg;
            }

            public void setLastmsg(String lastmsg) {
                this.lastmsg = lastmsg;
            }

            public String getLasttime() {
                return lasttime;
            }

            public void setLasttime(String lasttime) {
                this.lasttime = lasttime;
            }
        }
    }
}
