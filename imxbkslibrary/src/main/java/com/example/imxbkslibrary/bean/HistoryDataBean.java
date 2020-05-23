package com.example.imxbkslibrary.bean;

import java.util.List;

/**
 * Time:2020/5/14
 * <p>
 * Author:lichao
 * <p>
 * Description:
 */
public class HistoryDataBean<T> {


    /**
     * code : 0
     * message : {"type":"system","system":200,"data":{"fid":0,"history":[{"avatar":"https://imgxbhc.xbhc.com.cn/Uploads/images/202005/2020051116340713560671580.jpg","content":"https://imgxbhc.xbhc.com.cn/default/prod/20200514/2020051410182726.png","fid":"","img_path":"/storage/emulated/0/Pictures/1587637001618.jpg","mobile":"13560671580","role":"user","status":1,"type":"image","uid":"1761191","uname":"momo"},{"avatar":"https://imgxbhc.xbhc.com.cn/Uploads/images/202005/2020051116340713560671580.jpg","content":"好","fid":"","img_path":"/storage/emulated/0/Pictures/1587637012275.jpg","mobile":"13560671580","role":"user","status":1,"type":"text","uid":"1761191","uname":"momo"},{"avatar":"https://imgxbhc.xbhc.com.cn/Uploads/images/202005/2020051116340713560671580.jpg","content":"https://imgxbhc.xbhc.com.cn/default/prod/20200514/2020051410123656.png","fid":"","img_path":"/storage/emulated/0/Pictures/1587637012275.jpg","mobile":"13560671580","role":"user","status":1,"type":"image","uid":"1761191","uname":"momo"},{"avatar":"https://imgxbhc.xbhc.com.cn/Uploads/images/202005/2020051116340713560671580.jpg","content":"https://imgxbhc.xbhc.com.cn/default/prod/20200514/2020051410043011.png","fid":"","img_path":"/storage/emulated/0/Pictures/1587636754747.jpg","mobile":"13560671580","role":"user","status":1,"type":"image","uid":"1761191","uname":"momo"},{"avatar":"https://imgxbhc.xbhc.com.cn/Uploads/images/202005/2020051116340713560671580.jpg","content":"该字段为图片链接","fid":"","img_path":"/storage/emulated/0/Mob/com.xbhc.business/cache/images/3e048de75cd9161367c982ea722958dd.png","mobile":"13560671580","role":"user","status":1,"type":"image","uid":"1761191","uname":"momo"},{"avatar":"https://imgxbhc.xbhc.com.cn/Uploads/images/202005/2020051116340713560671580.jpg","content":"该字段为图片链接","fid":"","img_path":"/storage/emulated/0/Pictures/1587636754747.jpg","mobile":"13560671580","role":"user","status":1,"type":"image","uid":"1761191","uname":"momo"},{"avatar":"https://imgxbhc.xbhc.com.cn/Uploads/images/202005/2020051116340713560671580.jpg","content":"哈哈","fid":"","mobile":"13560671580","role":"user","status":1,"type":"text","uid":"1761191","uname":"momo"},{"avatar":"https://imgxbhc.xbhc.com.cn/Uploads/images/202005/2020051116340713560671580.jpg","content":"哈哈","fid":"","img_path":"/storage/emulated/0/Mob/com.xbhc.business/cache/images/3e048de75cd9161367c982ea722958dd.png","mobile":"13560671580","role":"user","status":1,"type":"text","uid":"1761191","uname":"momo"},{"avatar":"https://imgxbhc.xbhc.com.cn/Uploads/images/202005/2020051116340713560671580.jpg","content":"该字段为图片链接","fid":"","img_path":"/storage/emulated/0/Mob/com.xbhc.business/cache/images/3e048de75cd9161367c982ea722958dd.png","mobile":"13560671580","role":"user","status":1,"type":"image","uid":"1761191","uname":"momo"},{"avatar":"https://imgxbhc.xbhc.com.cn/Uploads/images/202005/2020051116340713560671580.jpg","content":"哈哈","fid":"","mobile":"13560671580","role":"user","status":1,"type":"text","uid":"1761191","uname":"momo"},{"type":"text","uid":"1757744","fid":"1761191","uname":"Youzx","mobile":"15819988853","avatar":"https://imgxbhc.xbhc.com.cn/Uploads/51bangbang/userphotos/default.png","role":1,"content":"123","time":1589363685}]}}
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

    public static class MessageBean<T> {
        /**
         * type : system
         * system : 200
         * data : {"fid":0,"history":[{"avatar":"https://imgxbhc.xbhc.com.cn/Uploads/images/202005/2020051116340713560671580.jpg","content":"https://imgxbhc.xbhc.com.cn/default/prod/20200514/2020051410182726.png","fid":"","img_path":"/storage/emulated/0/Pictures/1587637001618.jpg","mobile":"13560671580","role":"user","status":1,"type":"image","uid":"1761191","uname":"momo"},{"avatar":"https://imgxbhc.xbhc.com.cn/Uploads/images/202005/2020051116340713560671580.jpg","content":"好","fid":"","img_path":"/storage/emulated/0/Pictures/1587637012275.jpg","mobile":"13560671580","role":"user","status":1,"type":"text","uid":"1761191","uname":"momo"},{"avatar":"https://imgxbhc.xbhc.com.cn/Uploads/images/202005/2020051116340713560671580.jpg","content":"https://imgxbhc.xbhc.com.cn/default/prod/20200514/2020051410123656.png","fid":"","img_path":"/storage/emulated/0/Pictures/1587637012275.jpg","mobile":"13560671580","role":"user","status":1,"type":"image","uid":"1761191","uname":"momo"},{"avatar":"https://imgxbhc.xbhc.com.cn/Uploads/images/202005/2020051116340713560671580.jpg","content":"https://imgxbhc.xbhc.com.cn/default/prod/20200514/2020051410043011.png","fid":"","img_path":"/storage/emulated/0/Pictures/1587636754747.jpg","mobile":"13560671580","role":"user","status":1,"type":"image","uid":"1761191","uname":"momo"},{"avatar":"https://imgxbhc.xbhc.com.cn/Uploads/images/202005/2020051116340713560671580.jpg","content":"该字段为图片链接","fid":"","img_path":"/storage/emulated/0/Mob/com.xbhc.business/cache/images/3e048de75cd9161367c982ea722958dd.png","mobile":"13560671580","role":"user","status":1,"type":"image","uid":"1761191","uname":"momo"},{"avatar":"https://imgxbhc.xbhc.com.cn/Uploads/images/202005/2020051116340713560671580.jpg","content":"该字段为图片链接","fid":"","img_path":"/storage/emulated/0/Pictures/1587636754747.jpg","mobile":"13560671580","role":"user","status":1,"type":"image","uid":"1761191","uname":"momo"},{"avatar":"https://imgxbhc.xbhc.com.cn/Uploads/images/202005/2020051116340713560671580.jpg","content":"哈哈","fid":"","mobile":"13560671580","role":"user","status":1,"type":"text","uid":"1761191","uname":"momo"},{"avatar":"https://imgxbhc.xbhc.com.cn/Uploads/images/202005/2020051116340713560671580.jpg","content":"哈哈","fid":"","img_path":"/storage/emulated/0/Mob/com.xbhc.business/cache/images/3e048de75cd9161367c982ea722958dd.png","mobile":"13560671580","role":"user","status":1,"type":"text","uid":"1761191","uname":"momo"},{"avatar":"https://imgxbhc.xbhc.com.cn/Uploads/images/202005/2020051116340713560671580.jpg","content":"该字段为图片链接","fid":"","img_path":"/storage/emulated/0/Mob/com.xbhc.business/cache/images/3e048de75cd9161367c982ea722958dd.png","mobile":"13560671580","role":"user","status":1,"type":"image","uid":"1761191","uname":"momo"},{"avatar":"https://imgxbhc.xbhc.com.cn/Uploads/images/202005/2020051116340713560671580.jpg","content":"哈哈","fid":"","mobile":"13560671580","role":"user","status":1,"type":"text","uid":"1761191","uname":"momo"},{"type":"text","uid":"1757744","fid":"1761191","uname":"Youzx","mobile":"15819988853","avatar":"https://imgxbhc.xbhc.com.cn/Uploads/51bangbang/userphotos/default.png","role":1,"content":"123","time":1589363685}]}
         */

        private String type;
        private int system;
        private DataBean data;
        /**
         * cur_user_info : {"avatar":"https://imgxbhc.xbhc.com.cn/Uploads/images/202005/2020051116340713560671580.jpg","city_text":"广东-中山","is_shops":"0","mobile":"13560671580","role":"0","service":0,"uid":"1761191","uname":"momo"}
         */

        private CurUserInfoBean cur_user_info;


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

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public CurUserInfoBean getCur_user_info() {
            return cur_user_info;
        }

        public void setCur_user_info(CurUserInfoBean cur_user_info) {
            this.cur_user_info = cur_user_info;
        }

        public static class DataBean<T> {
            /**
             * fid : 0
             * history : [{"avatar":"https://imgxbhc.xbhc.com.cn/Uploads/images/202005/2020051116340713560671580.jpg","content":"https://imgxbhc.xbhc.com.cn/default/prod/20200514/2020051410182726.png","fid":"","img_path":"/storage/emulated/0/Pictures/1587637001618.jpg","mobile":"13560671580","role":"user","status":1,"type":"image","uid":"1761191","uname":"momo"},{"avatar":"https://imgxbhc.xbhc.com.cn/Uploads/images/202005/2020051116340713560671580.jpg","content":"好","fid":"","img_path":"/storage/emulated/0/Pictures/1587637012275.jpg","mobile":"13560671580","role":"user","status":1,"type":"text","uid":"1761191","uname":"momo"},{"avatar":"https://imgxbhc.xbhc.com.cn/Uploads/images/202005/2020051116340713560671580.jpg","content":"https://imgxbhc.xbhc.com.cn/default/prod/20200514/2020051410123656.png","fid":"","img_path":"/storage/emulated/0/Pictures/1587637012275.jpg","mobile":"13560671580","role":"user","status":1,"type":"image","uid":"1761191","uname":"momo"},{"avatar":"https://imgxbhc.xbhc.com.cn/Uploads/images/202005/2020051116340713560671580.jpg","content":"https://imgxbhc.xbhc.com.cn/default/prod/20200514/2020051410043011.png","fid":"","img_path":"/storage/emulated/0/Pictures/1587636754747.jpg","mobile":"13560671580","role":"user","status":1,"type":"image","uid":"1761191","uname":"momo"},{"avatar":"https://imgxbhc.xbhc.com.cn/Uploads/images/202005/2020051116340713560671580.jpg","content":"该字段为图片链接","fid":"","img_path":"/storage/emulated/0/Mob/com.xbhc.business/cache/images/3e048de75cd9161367c982ea722958dd.png","mobile":"13560671580","role":"user","status":1,"type":"image","uid":"1761191","uname":"momo"},{"avatar":"https://imgxbhc.xbhc.com.cn/Uploads/images/202005/2020051116340713560671580.jpg","content":"该字段为图片链接","fid":"","img_path":"/storage/emulated/0/Pictures/1587636754747.jpg","mobile":"13560671580","role":"user","status":1,"type":"image","uid":"1761191","uname":"momo"},{"avatar":"https://imgxbhc.xbhc.com.cn/Uploads/images/202005/2020051116340713560671580.jpg","content":"哈哈","fid":"","mobile":"13560671580","role":"user","status":1,"type":"text","uid":"1761191","uname":"momo"},{"avatar":"https://imgxbhc.xbhc.com.cn/Uploads/images/202005/2020051116340713560671580.jpg","content":"哈哈","fid":"","img_path":"/storage/emulated/0/Mob/com.xbhc.business/cache/images/3e048de75cd9161367c982ea722958dd.png","mobile":"13560671580","role":"user","status":1,"type":"text","uid":"1761191","uname":"momo"},{"avatar":"https://imgxbhc.xbhc.com.cn/Uploads/images/202005/2020051116340713560671580.jpg","content":"该字段为图片链接","fid":"","img_path":"/storage/emulated/0/Mob/com.xbhc.business/cache/images/3e048de75cd9161367c982ea722958dd.png","mobile":"13560671580","role":"user","status":1,"type":"image","uid":"1761191","uname":"momo"},{"avatar":"https://imgxbhc.xbhc.com.cn/Uploads/images/202005/2020051116340713560671580.jpg","content":"哈哈","fid":"","mobile":"13560671580","role":"user","status":1,"type":"text","uid":"1761191","uname":"momo"},{"type":"text","uid":"1757744","fid":"1761191","uname":"Youzx","mobile":"15819988853","avatar":"https://imgxbhc.xbhc.com.cn/Uploads/51bangbang/userphotos/default.png","role":1,"content":"123","time":1589363685}]
             */

            private int fid;
            private List<SocketDataBean.Params > history;

            public int getFid() {
                return fid;
            }

            public void setFid(int fid) {
                this.fid = fid;
            }

            public List<SocketDataBean.Params> getHistory() {
                return history;
            }

            public void setHistory(List<SocketDataBean.Params > history) {
                this.history = history;
            }

          /*  public static class HistoryBean {
                *//**
                 * avatar : https://imgxbhc.xbhc.com.cn/Uploads/images/202005/2020051116340713560671580.jpg
                 * content : https://imgxbhc.xbhc.com.cn/default/prod/20200514/2020051410182726.png
                 * fid :
                 * img_path : /storage/emulated/0/Pictures/1587637001618.jpg
                 * mobile : 13560671580
                 * role : user
                 * status : 1
                 * type : image
                 * uid : 1761191
                 * uname : momo
                 * time : 1589363685
                 *//*

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
                private int time;

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

                public int getTime() {
                    return time;
                }

                public void setTime(int time) {
                    this.time = time;
                }
            }*/
        }

        public static class CurUserInfoBean {
            /**
             * avatar : https://imgxbhc.xbhc.com.cn/Uploads/images/202005/2020051116340713560671580.jpg
             * city_text : 广东-中山
             * is_shops : 0
             * mobile : 13560671580
             * role : 0
             * service : 0
             * uid : 1761191
             * uname : momo
             */

            private String avatar;
            private String city_text;
            private String is_shops;
            private String mobile;
            private String role;
            private int service;
            private String uid;
            private String uname;

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

            public int getService() {
                return service;
            }

            public void setService(int service) {
                this.service = service;
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
