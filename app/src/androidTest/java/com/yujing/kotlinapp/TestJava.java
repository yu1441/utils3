package com.yujing.kotlinapp;

import com.yujing.url.YUrl;
import com.yujing.url.YUrlListener;

public class TestJava {

    public static void main(String[] args) {
        YUrl.create().get("", new YUrlListener() {
            @Override
            public void success(byte[] bytes, String value) {
                System.out.println(value);
            }

            @Override
            public void fail(String value) {
                System.out.println(value);
            }
        });
    }
}
