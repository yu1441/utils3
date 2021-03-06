package com.yujing.url;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.Map;

/**
 * 网络请求类
 *
 * @author yujing 2019年5月31日15:52:55
 */
public class YUrl {
    private static final String TAG = "YUrl";
    static boolean showLog = true;
    String contentType = "application/x-www-form-urlencoded;charset=utf-8";
    int connectTimeout = 1000 * 20;
    String crtSSL;

    /**
     * 创建YUrl对象
     *
     * @return YUrl
     */
    public static YUrl create() {
        return new YUrl();
    }

    public String getCrtSSL() {
        return crtSSL;
    }

    /**
     * 设置https请求SSL的crt证书
     *
     * @param crtSSL crt证书
     * @return YUrl
     */
    public YUrl setCrtSSL(String crtSSL) {
        this.crtSSL = crtSSL;
        return this;
    }

    public static void setShowLog(boolean showLog) {
        YUrl.showLog = showLog;
    }

    public String getContentType() {
        return contentType;
    }

    /**
     * 设置contentType
     *
     * @param contentType contentType
     * @return YUrl
     */
    public YUrl setContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    /**
     * 设置超时时间
     *
     * @param connectTimeout connectTimeout毫秒
     * @return YUrl
     */
    public YUrl setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
        return this;
    }


    /**
     * get请求
     *
     * @param requestUrl url
     * @param listener   监听
     */
    public void get(final String requestUrl, final YUrlListener listener) {
        Thread thread = new Thread(() -> {
            try {
                YUrlBase yUrlBase = new YUrlBase();
                yUrlBase.setContentType(contentType);
                yUrlBase.setConnectTimeout(connectTimeout);
                yUrlBase.setCrtSSL(crtSSL);
                byte[] bytes = yUrlBase.get(requestUrl);
                String result = new String(bytes);
                if (showLog)
                    System.out.println("请求地址↓：\nGet--->" + requestUrl + "\n请求结果：" + result);
                listener.success(bytes, result);
            } catch (Exception e) {
                exception(e, listener);
            } finally {
                YUrlThreadPool.shutdown();
            }
        });
        YUrlThreadPool.add(thread);
    }

    /**
     * post请求
     *
     * @param requestUrl url
     * @param paramsMap  key，value
     * @param listener   监听
     */
    public void post(final String requestUrl, Map<String, Object> paramsMap, final YUrlListener listener) {
        post(requestUrl, YUrlUtils.mapToParams(paramsMap).toString().getBytes(), listener);
    }

    /**
     * post请求
     *
     * @param requestUrl url
     * @param params     文本
     * @param listener   监听
     */
    public void post(final String requestUrl, String params, final YUrlListener listener) {
        post(requestUrl, params.getBytes(), listener);
    }

    /**
     * post请求
     *
     * @param requestUrl   url
     * @param requestBytes bytes
     * @param listener     监听
     */
    public void post(final String requestUrl, final byte[] requestBytes, final YUrlListener listener) {
        Thread thread = new Thread(() -> {
            try {
                YUrlBase yUrlBase = new YUrlBase();
                yUrlBase.setContentType(contentType);
                yUrlBase.setConnectTimeout(connectTimeout);
                yUrlBase.setCrtSSL(crtSSL);
                byte[] bytes = yUrlBase.post(requestUrl, requestBytes);
                String result = new String(bytes);
                if (showLog)
                    System.out.println("请求地址↓：\nPost--->" + requestUrl + (requestBytes == null ? "" : ("\n请求参数：" + new String(requestBytes))) + "\n请求结果：" + result);
                listener.success(bytes, result);
            } catch (Exception e) {
                exception(e, listener);
            } finally {
                YUrlThreadPool.shutdown();
            }
        });
        YUrlThreadPool.add(thread);
    }

    /**
     * 文件上传post
     *
     * @param requestUrl url
     * @param paramsMap  key，value
     * @param fileMap    文件列表
     * @param listener   监听
     */
    public void upload(final String requestUrl, Map<String, Object> paramsMap, Map<String, File> fileMap, final YUrlListener listener) {
        upload(requestUrl, YUrlUtils.mapToParams(paramsMap).toString().getBytes(), fileMap, listener);
    }

    /**
     * 文件上传post
     *
     * @param requestUrl url
     * @param params     文本
     * @param fileMap    文件列表
     * @param listener   监听
     */
    public void upload(final String requestUrl, String params, Map<String, File> fileMap, final YUrlListener listener) {
        upload(requestUrl, params.getBytes(), fileMap, listener);
    }

    /**
     * 文件上传post
     *
     * @param requestUrl   url
     * @param requestBytes bytes
     * @param fileMap      文件列表
     * @param listener     监听
     */
    public void upload(final String requestUrl, final byte[] requestBytes, final Map<String, File> fileMap, final YUrlListener listener) {
        Thread thread = new Thread(() -> {
            try {
                YUrlComplex yUrlComplex = new YUrlComplex();
                yUrlComplex.setContentType(contentType);
                yUrlComplex.setConnectTimeout(connectTimeout);
                yUrlComplex.setCrtSSL(crtSSL);
                byte[] bytes = yUrlComplex.upload(requestUrl, requestBytes, fileMap);
                String result = new String(bytes);
                if (showLog)
                    System.out.println("请求地址↓：\nupload--->" + requestUrl + (requestBytes == null ? "" : ("\n文件数：" + fileMap.size() + "\n请求参数：" + new String(requestBytes))) + "\n请求结果：" + result);
                listener.success(bytes, result);
            } catch (Exception e) {
                exception(e, listener);
            } finally {
                YUrlThreadPool.shutdown();
            }
        });
        YUrlThreadPool.add(thread);
    }

    /**
     * 文件下载,get请求，回调进度
     *
     * @param requestUrl url
     * @param file       保存的文件
     * @param listener   监听
     */
    public void downloadFile(final String requestUrl, final File file, final YUrlDownloadFileListener listener) {
        Thread thread = new Thread(() -> {
            try {
                YUrlBase yUrlBase = new YUrlBase();
                yUrlBase.setConnectTimeout(connectTimeout);
                yUrlBase.setCrtSSL(crtSSL);
                YUrlProgressListener progressListener = listener::progress;
                yUrlBase.downloadFile(requestUrl, file, progressListener);
                if (showLog)
                    System.out.println("文件下载↓：\nGet--->" + requestUrl + "\n保存路径：" + file.getPath());
                listener.success(file);
            } catch (Exception e) {
                exception(e, listener);
            } finally {
                YUrlThreadPool.shutdown();
            }
        });
        YUrlThreadPool.add(thread);
    }

    /**
     * 加载get请求，回调进度
     *
     * @param requestUrl url
     * @param listener   监听
     */
    public void load(final String requestUrl, final YUrlLoadListener listener) {
        Thread thread = new Thread(() -> {
            try {
                YUrlBase yUrlBase = new YUrlBase();
                yUrlBase.setConnectTimeout(connectTimeout);
                yUrlBase.setCrtSSL(crtSSL);
                YUrlProgressListener progressListener = listener::progress;
                byte[] bytes = yUrlBase.load(requestUrl, progressListener);
                if (showLog)
                    System.out.println("文件加载↓：\nGet--->" + requestUrl);
                listener.success(bytes);
            } catch (Exception e) {
                exception(e, listener);
            } finally {
                YUrlThreadPool.shutdown();
            }
        });
        YUrlThreadPool.add(thread);
    }

    /**
     * 错误情况处理
     *
     * @param e        错误
     * @param listener 监听
     */
    void exception(Exception e, Object listener) {
        if (e instanceof MalformedURLException) {
            error("URL地址不规范", listener);
        } else if (e instanceof java.net.SocketTimeoutException) {
            error("网络连接超时", listener);
        } else if (e instanceof UnsupportedEncodingException) {
            error("不支持的编码", listener);
        } else if (e instanceof FileNotFoundException) {
            error("找不到该地址", listener);
        } else if (e instanceof IOException) {
            error("连接服务器失败", listener);
        } else {
            error("错误 " + e.getMessage(), listener);
        }
    }

    /**
     * 错误回调
     *
     * @param error    错误
     * @param listener 监听
     */
    void error(String error, Object listener) {
        if (listener instanceof YUrlListener) {
            System.out.println(TAG + error);
            ((YUrlListener) listener).fail(error);
        } else if (listener instanceof YUrlLoadListener) {
            System.out.println(TAG + error);
            ((YUrlLoadListener) listener).fail(error);
        } else if (listener instanceof YUrlDownloadFileListener) {
            System.out.println(TAG + error);
            ((YUrlDownloadFileListener) listener).fail(error);
        }
    }
}
