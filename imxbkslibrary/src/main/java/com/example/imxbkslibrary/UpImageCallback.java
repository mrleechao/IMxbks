package com.example.imxbkslibrary;

/**
 * Time:2020/5/23
 * <p>
 * Author:lichao
 * <p>
 * Description:图片上传回调
 */
public interface UpImageCallback {
    /**
     *
     * @param result 服务器返回的图片上传结果
     * @param id
     */
    void   upadd(String result,String id);
}
