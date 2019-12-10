package com.yujing.contract;

/**
 * 通用成功失败监听
 *
 * @param <X> 成功类型
 * @param <Y> 失败返回类型
 * @author yujing 2019年12月5日09:56:39
 */
public interface YSuccessFailListener<X, Y> {
    /**
     * 成功
     *
     * @param value 值
     */
    void success(X value);

    /**
     * 失败
     *
     * @param value 值
     */
    void fail(Y value);
}
