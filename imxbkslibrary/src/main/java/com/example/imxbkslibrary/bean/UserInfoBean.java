package com.example.imxbkslibrary.bean;

/**
 * Time:2020/5/12
 * <p>
 * Author:lichao
 * <p>
 * Description:
 */
public class UserInfoBean {

    /**
     * name : user/get-user-info
     * code : 0
     * message :
     * duration : 0.0382
     * data :
     * status : 200
     */

    private String name;
    private int code;
    private String message;
    private double duration;
    private DataBean data;
    private int status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class DataBean {
        /**
         * ban_status : 1
         * role : user
         * user_id : 1761191
         * nickname : 初凉
         * sex : 1
         * mobile : 13560671580
         * birthday : 2000-07-31
         * avatar : https://imgxbhc.xbhc.com.cn/Uploads/images/202005/2020051116340713560671580.jpg
         * score : 0
         * is_pay : 0
         * is_pay_password : 0
         * access_token : 8109236b59d89e05dac69e750f971500
         * is_service : 0
         * city_text : 广东-中山
         * is_shops : 0
         * debug_data : {"user_id":"1761191","provider_id":0,"client_id":"2af5636447c835c642d3d9e76b84b4af","role":"user"}
         */

        private String ban_status;
        private String role;
        private String user_id;
        private String nickname;
        private int sex;
        private String mobile;
        private String birthday;
        private String avatar;
        private String score;
        private int is_pay;
        private int is_pay_password;
        private String access_token;
        private String is_service;
        private String city_text;
        private int is_shops;
        private DebugDataBean debug_data;

        public String getBan_status() {
            return ban_status;
        }

        public void setBan_status(String ban_status) {
            this.ban_status = ban_status;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public int getIs_pay() {
            return is_pay;
        }

        public void setIs_pay(int is_pay) {
            this.is_pay = is_pay;
        }

        public int getIs_pay_password() {
            return is_pay_password;
        }

        public void setIs_pay_password(int is_pay_password) {
            this.is_pay_password = is_pay_password;
        }

        public String getAccess_token() {
            return access_token;
        }

        public void setAccess_token(String access_token) {
            this.access_token = access_token;
        }

        public String getIs_service() {
            return is_service;
        }

        public void setIs_service(String is_service) {
            this.is_service = is_service;
        }

        public String getCity_text() {
            return city_text;
        }

        public void setCity_text(String city_text) {
            this.city_text = city_text;
        }

        public int getIs_shops() {
            return is_shops;
        }

        public void setIs_shops(int is_shops) {
            this.is_shops = is_shops;
        }

        public DebugDataBean getDebug_data() {
            return debug_data;
        }

        public void setDebug_data(DebugDataBean debug_data) {
            this.debug_data = debug_data;
        }

        public static class DebugDataBean {
            /**
             * user_id : 1761191
             * provider_id : 0
             * client_id : 2af5636447c835c642d3d9e76b84b4af
             * role : user
             */

            private String user_id;
            private int provider_id;
            private String client_id;
            private String role;

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public int getProvider_id() {
                return provider_id;
            }

            public void setProvider_id(int provider_id) {
                this.provider_id = provider_id;
            }

            public String getClient_id() {
                return client_id;
            }

            public void setClient_id(String client_id) {
                this.client_id = client_id;
            }

            public String getRole() {
                return role;
            }

            public void setRole(String role) {
                this.role = role;
            }
        }
    }
}
