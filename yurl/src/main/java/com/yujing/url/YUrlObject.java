package com.yujing.url;

import com.google.gson.Gson;

import java.util.Map;

/**
 * 网络请求类
 *
 * @author yujing 2019年11月20日17:43:47
 */
public class YUrlObject extends YUrl {
    public static YUrlObject create() {
        return new YUrlObject();
    }

    /**
     * 设置超时时间
     *
     * @param connectTimeout connectTimeout毫秒
     * @return YUrl
     */
    @Override
    public YUrlObject setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
        return this;
    }

    /**
     * 设置contentType
     *
     * @param contentType contentType
     * @return YUrl
     */
    @Override
    public YUrlObject setContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    /**
     * 设置https请求SSL的crt证书
     *
     * @param crtSSL crt证书
     * @return YUrl
     */
    @Override
    public YUrlObject setCrtSSL(String crtSSL) {
        this.crtSSL = crtSSL;
        return this;
    }

    /**
     * get请求
     *
     * @param requestUrl url
     * @param listener   监听
     * @param <T>        类型
     */
    public <T> void get(final String requestUrl, final YObjectListener<T> listener) {
        super.get(requestUrl, new YUrlListener() {
            @Override
            public void success(byte[] bytes, String value) {
                System.out.println("对象转换类型：" + listener.getType());
                if (String.class.equals(listener.getType())) {
                    listener.success(bytes, (T) value);
                } else if ("byte[]".equals(listener.getType().toString())) {
                    listener.success(bytes, (T) bytes);
                } else {
                    Gson gson = new Gson();
                    T object = gson.fromJson(value, listener.getType());
                    listener.success(bytes, object);
                }
            }

            @Override
            public void fail(String value) {
                listener.fail(value);
            }
        });
    }


    /**
     * post请求
     *
     * @param requestUrl url
     * @param paramsMap  key，value
     * @param listener   监听
     * @param <T>        类型
     */
    public <T> void post(String requestUrl, Map<String, Object> paramsMap, YObjectListener<T> listener) {
        post(requestUrl, YUrlUtils.mapToParams(paramsMap).toString().getBytes(), listener);
    }

    /**
     * post请求
     *
     * @param requestUrl url
     * @param params     文本
     * @param listener   监听
     * @param <T>        类型
     */
    public <T> void post(String requestUrl, String params, YObjectListener<T> listener) {
        post(requestUrl, params.getBytes(), listener);
    }


    /**
     * post请求
     *
     * @param requestUrl   url
     * @param requestBytes bytes
     * @param listener     监听
     * @param <T>          类型
     */
    public <T> void post(String requestUrl, byte[] requestBytes, final YObjectListener<T> listener) {
        super.post(requestUrl, requestBytes, new YUrlListener() {
            @Override
            public void success(byte[] bytes, String value) {
                System.out.println("对象转换类型：" + listener.getType());
                if (String.class.equals(listener.getType())) {
                    listener.success(bytes, (T) value);
                } else if ("byte[]".equals(listener.getType().toString())) {
                    listener.success(bytes, (T) bytes);
                } else {
                    Gson gson = new Gson();
                    T object = gson.fromJson(value, listener.getType());
                    listener.success(bytes, object);
                }
            }

            @Override
            public void fail(String value) {
                listener.fail(value);
            }
        });
    }
}
