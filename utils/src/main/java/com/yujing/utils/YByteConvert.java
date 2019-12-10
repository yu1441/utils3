package com.yujing.utils;

/**
 * Byte转换类，常用类型的转换
 *
 * @author yujing 2018年11月30日11:34:21
 * char 类型、 float、double 类型和 byte[] 数组之间的转换关系还需继续研究实现。
 */
@SuppressWarnings("unused")
public class YByteConvert {

    /**
     * long 和 网络字节序的 byte[] 数组之间的转换
     *
     * @param n 需要转换的long
     * @return 转换后的byte数组
     */
    public static byte[] longToBytes(long n) {
        byte[] b = new byte[8];
        b[7] = (byte) (n & 0xff);
        b[6] = (byte) (n >> 8 & 0xff);
        b[5] = (byte) (n >> 16 & 0xff);
        b[4] = (byte) (n >> 24 & 0xff);
        b[3] = (byte) (n >> 32 & 0xff);
        b[2] = (byte) (n >> 40 & 0xff);
        b[1] = (byte) (n >> 48 & 0xff);
        b[0] = (byte) (n >> 56 & 0xff);
        return b;
    }

    /**
     * long 和 网络字节序的 byte[] 数组之间的转换
     *
     * @param n      需要转换的long
     * @param array  目标数组
     * @param offset 目标数组的offset位置
     */
    public static void longToBytes(long n, byte[] array, int offset) {
        array[7 + offset] = (byte) (n & 0xff);
        array[6 + offset] = (byte) (n >> 8 & 0xff);
        array[5 + offset] = (byte) (n >> 16 & 0xff);
        array[4 + offset] = (byte) (n >> 24 & 0xff);
        array[3 + offset] = (byte) (n >> 32 & 0xff);
        array[2 + offset] = (byte) (n >> 40 & 0xff);
        array[1 + offset] = (byte) (n >> 48 & 0xff);
        array[offset] = (byte) (n >> 56 & 0xff);
    }

    /**
     * long 和 网络字节序的 byte[] 数组之间的转换
     *
     * @param array long的byte数组
     * @return 值
     */
    public static long bytesToLong(byte[] array) {
        return ((((long) array[0] & 0xff) << 56) | (((long) array[1] & 0xff) << 48) | (((long) array[2] & 0xff) << 40)
                | (((long) array[3] & 0xff) << 32) | (((long) array[4] & 0xff) << 24) | (((long) array[5] & 0xff) << 16)
                | (((long) array[6] & 0xff) << 8) | (((long) array[7] & 0xff)));
    }

    /**
     * long 和 网络字节序的 byte[] 数组之间的转换
     *
     * @param array  long的byte数组
     * @param offset long的byte数组，第offset位开始转换
     * @return 值
     */
    public static long bytesToLong(byte[] array, int offset) {
        return ((((long) array[offset] & 0xff) << 56) | (((long) array[offset + 1] & 0xff) << 48)
                | (((long) array[offset + 2] & 0xff) << 40) | (((long) array[offset + 3] & 0xff) << 32)
                | (((long) array[offset + 4] & 0xff) << 24) | (((long) array[offset + 5] & 0xff) << 16)
                | (((long) array[offset + 6] & 0xff) << 8) | (((long) array[offset + 7] & 0xff)));
    }

    /**
     * int 和 网络字节序的 byte[] 数组之间的转换
     *
     * @param n int数据
     * @return 转换后的byte数组
     */
    public static byte[] intToBytes(int n) {
        byte[] b = new byte[4];
        b[3] = (byte) (n & 0xff);
        b[2] = (byte) (n >> 8 & 0xff);
        b[1] = (byte) (n >> 16 & 0xff);
        b[0] = (byte) (n >> 24 & 0xff);
        return b;
    }

    /**
     * int 和 网络字节序的 byte[] 数组之间的转换
     *
     * @param n      int数据
     * @param array  byte数组
     * @param offset int数组的第offset位
     */
    public static void intToBytes(int n, byte[] array, int offset) {
        array[3 + offset] = (byte) (n & 0xff);
        array[2 + offset] = (byte) (n >> 8 & 0xff);
        array[1 + offset] = (byte) (n >> 16 & 0xff);
        array[offset] = (byte) (n >> 24 & 0xff);
    }

    /**
     * int 和 网络字节序的 byte[] 数组之间的转换
     *
     * @param b int的byte数组
     * @return 值
     */
    public static int bytesToInt(byte[] b) {
        return b[3] & 0xff | (b[2] & 0xff) << 8 | (b[1] & 0xff) << 16 | (b[0] & 0xff) << 24;
    }

    /**
     * int 和 网络字节序的 byte[] 数组之间的转换
     *
     * @param b      byte数组
     * @param offset byte数组的第offset位开始转换
     * @return 值
     */
    public static int bytesToInt(byte[] b, int offset) {
        return b[offset + 3] & 0xff | (b[offset + 2] & 0xff) << 8 | (b[offset + 1] & 0xff) << 16
                | (b[offset] & 0xff) << 24;
    }

    public static byte[] uintToBytes(long n) {
        byte[] b = new byte[4];
        b[3] = (byte) (n & 0xff);
        b[2] = (byte) (n >> 8 & 0xff);
        b[1] = (byte) (n >> 16 & 0xff);
        b[0] = (byte) (n >> 24 & 0xff);
        return b;
    }

    public static void uintToBytes(long n, byte[] array, int offset) {
        array[3 + offset] = (byte) (n);
        array[2 + offset] = (byte) (n >> 8 & 0xff);
        array[1 + offset] = (byte) (n >> 16 & 0xff);
        array[offset] = (byte) (n >> 24 & 0xff);
    }

    public static long bytesToUint(byte[] array) {
        return ((long) (array[3] & 0xff)) | ((long) (array[2] & 0xff)) << 8 | ((long) (array[1] & 0xff)) << 16
                | ((long) (array[0] & 0xff)) << 24;
    }

    public static long bytesToUint(byte[] array, int offset) {
        return ((long) (array[offset + 3] & 0xff)) | ((long) (array[offset + 2] & 0xff)) << 8
                | ((long) (array[offset + 1] & 0xff)) << 16 | ((long) (array[offset] & 0xff)) << 24;
    }

    /**
     * short 和 网络字节序的 byte[] 数组之间的转换
     *
     * @param n short
     * @return 转换后的byte数组
     */
    public static byte[] shortToBytes(short n) {
        byte[] b = new byte[2];
        b[1] = (byte) (n & 0xff);
        b[0] = (byte) ((n >> 8) & 0xff);
        return b;
    }

    /**
     * short 和 网络字节序的 byte[] 数组之间的转换
     *
     * @param n      short
     * @param array  byte数组
     * @param offset byte数组第offset位开始转换
     */
    public static void shortToBytes(short n, byte[] array, int offset) {
        array[offset + 1] = (byte) (n & 0xff);
        array[offset] = (byte) ((n >> 8) & 0xff);
    }

    /**
     * short 和 网络字节序的 byte[] 数组之间的转换
     *
     * @param b short的byte数组
     * @return 值
     */
    public static short bytesToShort(byte[] b) {
        return (short) (b[1] & 0xff | (b[0] & 0xff) << 8);
    }

    /**
     * short 和 网络字节序的 byte[] 数组之间的转换
     *
     * @param b      short的byte数组
     * @param offset byte数组第offset位开始转换
     * @return 值
     */
    public static short bytesToShort(byte[] b, int offset) {
        return (short) (b[offset + 1] & 0xff | (b[offset] & 0xff) << 8);
    }

    public static byte[] ushortToBytes(int n) {
        byte[] b = new byte[2];
        b[1] = (byte) (n & 0xff);
        b[0] = (byte) ((n >> 8) & 0xff);
        return b;
    }

    public static void ushortToBytes(int n, byte[] array, int offset) {
        array[offset + 1] = (byte) (n & 0xff);
        array[offset] = (byte) ((n >> 8) & 0xff);
    }

    public static int bytesToUshort(byte[] b) {
        return b[1] & 0xff | (b[0] & 0xff) << 8;
    }

    public static int bytesToUshort(byte[] b, int offset) {
        return b[offset + 1] & 0xff | (b[offset] & 0xff) << 8;
    }

    public static byte[] ubyteToBytes(int n) {
        byte[] b = new byte[1];
        b[0] = (byte) (n & 0xff);
        return b;
    }

    public static void ubyteToBytes(int n, byte[] array, int offset) {
        array[offset] = (byte) (n & 0xff);
    }

    public static int bytesToUbyte(byte[] array) {
        return array[0] & 0xff;
    }

    public static int bytesToUbyte(byte[] array, int offset) {
        return array[offset] & 0xff;
    }

}
