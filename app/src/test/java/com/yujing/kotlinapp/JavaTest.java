package com.yujing.kotlinapp;

import com.yujing.crypt.YDes;
import com.yujing.utils.YConvert;

import org.junit.Test;

public class JavaTest {
    @Test
    public void test() {
        try {
//            byte[] key= Y3des.getKey();
//            System.out.println(YConvert.bytesToHexString(key));

            byte[] key2 = YConvert.hexStringToByte("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF");
            System.out.println(YConvert.bytesToHexString(key2));

            byte[] value = YDes.encode(YConvert.hexStringToByte("5DA3F52DBC087263"), key2);
            System.out.println(YConvert.bytesToHexString(value));

            byte[] value2 = YDes.decode(value, key2);
            System.out.println(YConvert.bytesToHexString(value2));

            System.out.println(new String(value2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
