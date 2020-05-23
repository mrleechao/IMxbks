package com.example.imxbkslibrary.bean;

/**
 * Time:2020/5/14
 * <p>
 * Author:lichao
 * <p>
 * Description:
 */
public class ImageDataBean {

    /**
     * name : site/upload
     * code : 0
     * message : 上传成功
     * duration : 0.3051
     * data : {"image_id":"309838","path":"https://imgxbhc.xbhc.com.cn/default/prod/20200514/2020051409415193.png","verify":false,"detail":false}
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
         * image_id : 309838
         * path : https://imgxbhc.xbhc.com.cn/default/prod/20200514/2020051409415193.png
         * verify : false
         * detail : false
         */

        private String image_id;
        private String path;
        private boolean verify;
        private boolean detail;

        public String getImage_id() {
            return image_id;
        }

        public void setImage_id(String image_id) {
            this.image_id = image_id;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public boolean isVerify() {
            return verify;
        }

        public void setVerify(boolean verify) {
            this.verify = verify;
        }

        public boolean isDetail() {
            return detail;
        }

        public void setDetail(boolean detail) {
            this.detail = detail;
        }
    }
}
