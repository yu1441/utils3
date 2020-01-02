package com.yujing.kotlinapp.activity.page07;

import android.app.Activity;
import android.util.Log;

import com.yujing.kotlinapp.App;
import com.yujing.url.YObjectListener;
import com.yujing.url.YUrlAndroidObject;
import com.yujing.utils.YConvert;
import com.yujing.utils.YConvertBytes;
import com.yujing.utils.YSave;
import com.yujing.utils.YShow;
import com.yujing.utils.YToast;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Presenter07<T> {

    public void getData() {
        String url = "http://39.106.6.5:8090/QrUser/getQrUser";
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("year", "2019");
        YUrlAndroidObject.create().post(url, paramsMap, new YObjectListener<Status07<User07>>() {
            @Override
            public void success(byte[] bytes, Status07<User07> value) {
                Log.d("★★★★★★★", value.getData().get(0).toString());
                YToast.show(App.Companion.get(), value.getData().get(0).toString());
            }

            @Override
            public void fail(String value) {

            }
        });
    }

    //全部成员变量泛型
    public void getType1() {
        String url = "http://39.106.6.5:8090/QrUser/getQrUser";
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("year", "2019");

        YUrlAndroidObject.create().post(url, paramsMap, new YObjectListener<byte[]>() {
            @Override
            public void success(byte[] bytes, byte[] value) {
                Log.d("★★★★★★★", new String(value));
                YToast.show(App.Companion.get(), new String(value));
            }

            @Override
            public void fail(String value) {

            }
        });
    }

    //成员变量泛型
    public void getType2() {
        String url = "http://39.106.6.5:8090/QrUser/getQrUser";
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("year", "2019");

        YUrlAndroidObject.create().post(url, paramsMap, new YObjectListener<Status07<User07>>() {
            @Override
            public void success(byte[] bytes, Status07<User07> value) {
                Log.d("★★★★★★★", value.getData().get(0).toString());
                YToast.show(App.Companion.get(), value.getData().get(0).toString());
//1.存对象
//                YSave.put(App.Companion.get(), "User071",value);
                //2.存bytes
//                YSave.put(App.Companion.get(), "User072",bytes);
                //3.序列化存对象
                YSave.put(App.Companion.get(), "User073", YConvert.object2Bytes(value));
            }

            @Override
            public void fail(String value) {

            }
        });
    }


    //方法泛型
    public void getType3() {
        //1.取对象
//        Status07<User07> value = (Status07<User071>) YSave.get(App.Companion.get(), "User07",new TypeToken<Status07<User07> >(){}.getType());
//        Log.d("★★★★★★★", value.getData().get(0).toString());
//        YToast.show(App.Companion.get(), value.getData().get(0).toString());

        //2.取bytes
//        byte[] bs = YSave.get(App.Companion.get(), "User072", byte[].class);
//        Gson gson = new Gson();
//        Status07<User07> value = gson.fromJson(new String(bs), new TypeToken<Status07<User07>>() {
//        }.getType());
//        Log.d("★★★★★★★", value.getData().get(0).toString());
//        YToast.show(App.Companion.get(), value.getData().get(0).toString());

        //3.序列化取对象
        byte[] bs = YSave.get(App.Companion.get(), "User073", byte[].class);
        Status07<User07> value = (Status07<User07>) YConvert.bytes2Object(bs);
        Log.d("★★★★★★★", value.getData().get(0).toString());
        YToast.show(App.Companion.get(), value.getData().get(0).toString());
    }

    public void getYSave1() {
        YSave.setUseCache(false);
        YSave.put(App.Companion.get(), "aaa", "fldkjgsjdgjt eoirjtjsfd dsfgj".getBytes());
    }

    public void getYSave2() {
//       Object value=YSave.get(App.Companion.get(), "aaa",new TypeToken<byte[]>(){}.getType());
//        YToast.show(App.Companion.get(),new String((byte[])value));
        YSave.setUseCache(false);
        byte[] value = YSave.get(App.Companion.get(), "aaa", byte[].class);
        YToast.show(App.Companion.get(), new String(value));
    }

    public void getTest1() {
        int i = 654;
        byte[] bi = YConvertBytes.intToBytes(i);
        byte[] bi2 = YConvertBytes.intTo2Bytes(i);
        Log.d("000", Arrays.toString(bi));
        Log.d("000", Arrays.toString(bi2));
        int i1 = YConvertBytes.bytesToInt(bi);
        int i2 = YConvertBytes.bytes2ToInt(bi2);
        Log.d("000", "i1=" + i1);
        Log.d("000", "i2=" + i2);
        cFloat();
        cDouble();
    }

    public void cFloat() {
        float i = 654545.5646f;
        byte[] bi = YConvertBytes.floatToBytes(i);
        Log.d("000", Arrays.toString(bi));
        float i1 = YConvertBytes.bytesToFloat(bi);
        Log.d("000", "i1=" + i1);
    }

    public void cDouble() {
        double i = 6549684646.1238928374d;
        byte[] bi = YConvertBytes.doubleToBytes(i);
        Log.d("000", Arrays.toString(bi));
        double i1 = YConvertBytes.bytesToDouble(bi);
        Log.d("000", "i1=" + i1);
    }

    public void getTest2(Activity activity) {
//        YShow.create(activity).setMessage1("11111").setMessage2("22").setCanCancel(true).setFullScreen(false).show();
        YShow.setDefaultFullScreen(true);
        YShow.show(activity,"1111","22222",false,false);
    }
}
