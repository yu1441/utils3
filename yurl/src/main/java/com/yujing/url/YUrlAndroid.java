package com.yujing.url;

import android.os.Handler;
import android.util.Log;

import java.io.File;
import java.util.Map;

/**
 * 网络请求类
 *
 * @author yujing 2019年6月14日10:49:34
 */
public class YUrlAndroid extends YUrl {
    private static final String TAG = "YUrlAndroid";
    private final Handler handler = new Handler();

    public static YUrlAndroid create() {
        return new YUrlAndroid();
    }

    /**
     * 设置超时时间
     *
     * @param connectTimeout connectTimeout毫秒
     * @return YUrl
     */
    @Override
    public YUrlAndroid setConnectTimeout(int connectTimeout) {
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
    public YUrlAndroid setContentType(String contentType) {
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
    public YUrlAndroid setCrtSSL(String crtSSL) {
        this.crtSSL = crtSSL;
        return this;
    }

    /**
     * get请求
     *
     * @param requestUrl url
     * @param listener   监听
     */
    @Override
    public void get(final String requestUrl, final YUrlListener listener) {
        super.get(requestUrl, new YUrlListener() {
            @Override
            public void success(byte[] bytes, String value) {
                handler.post(new YRunnable(() -> listener.success(bytes, value)));
            }

            @Override
            public void fail(String value) {
                handler.post(new YRunnable(() -> listener.fail(value)));
            }
        });
    }

    /**
     * post请求
     *
     * @param requestUrl   url
     * @param requestBytes bytes
     * @param listener     监听
     */
    @Override
    public void post(final String requestUrl, final byte[] requestBytes, final YUrlListener listener) {
        super.post(requestUrl, requestBytes, new YUrlListener() {
            @Override
            public void success(byte[] bytes, String value) {
                handler.post(new YRunnable(() -> listener.success(bytes, value)));
            }

            @Override
            public void fail(String value) {
                handler.post(new YRunnable(() -> listener.fail(value)));
            }
        });
    }

    /**
     * 文件上传post
     *
     * @param requestUrl   url
     * @param requestBytes bytes
     * @param fileMap      文件列表
     * @param listener     监听
     */
    @Override
    public void upload(final String requestUrl, final byte[] requestBytes, final Map<String, File> fileMap, final YUrlListener listener) {
        super.upload(requestUrl, requestBytes, fileMap, new YUrlListener() {
            @Override
            public void success(byte[] bytes, String value) {
                handler.post(new YRunnable(() -> listener.success(bytes, value)));
            }

            @Override
            public void fail(String value) {
                handler.post(new YRunnable(() -> listener.fail(value)));
            }
        });
    }

    /**
     * 文件下载,get请求，回调进度
     *
     * @param requestUrl url
     * @param file       保存的文件
     * @param listener   监听
     */
    @Override
    public void downloadFile(final String requestUrl, final File file, final YUrlDownloadFileListener listener) {

        super.downloadFile(requestUrl, file, new YUrlDownloadFileListener() {

            @Override
            public void progress(int downloadSize, int fileSize) {
                handler.post(new YRunnable(() -> listener.progress(downloadSize, fileSize)));
            }

            @Override
            public void success(File file) {
                handler.post(new YRunnable(() -> listener.success(file)));
            }

            @Override
            public void fail(String value) {
                handler.post(new YRunnable(() -> listener.fail(value)));
            }
        });
    }

    /**
     * 加载get请求，回调进度
     *
     * @param requestUrl url
     * @param listener   监听
     */
    @Override
    public void load(final String requestUrl, final YUrlLoadListener listener) {
        super.load(requestUrl, new YUrlLoadListener() {
            @Override
            public void progress(int downloadSize, int size) {
                handler.post(new YRunnable(() -> listener.progress(downloadSize, size)));
            }

            @Override
            public void success(byte[] bytes) {
                handler.post(new YRunnable(() -> listener.success(bytes)));
            }

            @Override
            public void fail(String value) {
                handler.post(new YRunnable(() -> listener.fail(value)));
            }
        });
    }


    /**
     * 错误回调
     *
     * @param error    错误
     * @param listener 监听
     */
    @Override
    void error(final String error, final Object listener) {
        handler.post(() -> {
            if (listener instanceof YUrlListener) {
                Log.e(TAG, error);
                ((YUrlListener) listener).fail(error);
            } else if (listener instanceof YUrlLoadListener) {
                Log.e(TAG, error);
                ((YUrlLoadListener) listener).fail(error);
            } else if (listener instanceof YUrlDownloadFileListener) {
                Log.e(TAG, error);
                ((YUrlDownloadFileListener) listener).fail(error);
            }
        });
    }
}
