package com.example.imxbkslibrary.bean;

import java.util.List;

/**
 * Time:2020/5/14
 * <p>
 * Author:lichao
 * <p>
 * Description:
 */
public class RobotDataBean {

    /**
     * code : 131073
     * message : {"type":"robot","system":0,"data":{"type":"robot","uid":"robot","fid":"1761191","uname":"帮帮机器人","mobile":"4001355188","avatar":"https://img02.281.com.cn/default/prod/20200103/2020010309272292.png","role":1,"content":{"des":"亲爱的用户您好，我是智能小帮，很高兴为您服务！人工客服的工作时间是周一至周六9:00~18:00","event":[]},"time":1589422773}}
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
         * type : robot
         * system : 0
         * data : {"type":"robot","uid":"robot","fid":"1761191","uname":"帮帮机器人","mobile":"4001355188","avatar":"https://img02.281.com.cn/default/prod/20200103/2020010309272292.png","role":1,"content":{"des":"亲爱的用户您好，我是智能小帮，很高兴为您服务！人工客服的工作时间是周一至周六9:00~18:00","event":[]},"time":1589422773}
         */

        private String type;
        private int system;
        private DataBean data;

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

        public static class DataBean {
            /**
             * type : robot
             * uid : robot
             * fid : 1761191
             * uname : 帮帮机器人
             * mobile : 4001355188
             * avatar : https://img02.281.com.cn/default/prod/20200103/2020010309272292.png
             * role : 1
             * content : {"des":"亲爱的用户您好，我是智能小帮，很高兴为您服务！人工客服的工作时间是周一至周六9:00~18:00","event":[]}
             * time : 1589422773
             */

            private String type;
            private String uid;
            private String fid;
            private String uname;
            private String mobile;
            private String avatar;
            private int role;
            private ContentBean content;
            private int time;

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

            public String getFid() {
                return fid;
            }

            public void setFid(String fid) {
                this.fid = fid;
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

            public int getRole() {
                return role;
            }

            public void setRole(int role) {
                this.role = role;
            }

            public ContentBean getContent() {
                return content;
            }

            public void setContent(ContentBean content) {
                this.content = content;
            }

            public int getTime() {
                return time;
            }

            public void setTime(int time) {
                this.time = time;
            }

            public static class ContentBean {
                /**
                 * des : 亲爱的用户您好，我是智能小帮，很高兴为您服务！人工客服的工作时间是周一至周六9:00~18:00
                 * event : []
                 */

                private String des;
                private List<?> event;

                public String getDes() {
                    return des;
                }

                public void setDes(String des) {
                    this.des = des;
                }

                public List<?> getEvent() {
                    return event;
                }

                public void setEvent(List<?> event) {
                    this.event = event;
                }
            }
        }
    }
}
