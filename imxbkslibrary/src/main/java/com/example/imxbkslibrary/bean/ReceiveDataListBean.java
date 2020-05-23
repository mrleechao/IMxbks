package com.example.imxbkslibrary.bean;

import java.util.List;

/**
 * Time:2020/5/13
 * <p>
 * Author:lichao
 * <p>
 * Description:
 */
public class ReceiveDataListBean {

    /**
     * code : 0
     * message : {"type":"text","data":{"avatar":"https://imgxbhc.xbhc.com.cn/Uploads/images/202005/2020051116340713560671580.jpg","content":"咋了","fid":"","img_path":"/storage/emulated/0/Pictures/1587636754747.jpg","mobile":"13560671580","role":"user","status":1,"type":"text","uid":"1761191","uname":"momo"}}
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
         * type : text
         * data : {"avatar":"https://imgxbhc.xbhc.com.cn/Uploads/images/202005/2020051116340713560671580.jpg","content":"咋了","fid":"","img_path":"/storage/emulated/0/Pictures/1587636754747.jpg","mobile":"13560671580","role":"user","status":1,"type":"text","uid":"1761191","uname":"momo"}
         */

        private String type;
        private List<SocketDataBean.Params > data;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<SocketDataBean.Params > getData() {
            return data;
        }

        public void setData(List<SocketDataBean.Params > data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * avatar : https://imgxbhc.xbhc.com.cn/Uploads/images/202005/2020051116340713560671580.jpg
             * content : 咋了
             * fid :
             * img_path : /storage/emulated/0/Pictures/1587636754747.jpg
             * mobile : 13560671580
             * role : user
             * status : 1
             * type : text
             * uid : 1761191
             * uname : momo
             */

            private String avatar;
            private String content;
            private String fid;
            private String img_path;
            private String mobile;
            private String role;
            private int status;
            private String type;
            private String uid;
            private String uname;

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getFid() {
                return fid;
            }

            public void setFid(String fid) {
                this.fid = fid;
            }

            public String getImg_path() {
                return img_path;
            }

            public void setImg_path(String img_path) {
                this.img_path = img_path;
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

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
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
        }
    }
}
