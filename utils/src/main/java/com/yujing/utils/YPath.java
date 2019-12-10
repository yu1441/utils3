package com.yujing.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.util.Objects;

import static android.os.Environment.MEDIA_MOUNTED;

/**
 * 获取安卓各种默认目录
 *
 * @author yujing  2019年3月28日09:48:19
 */

@SuppressWarnings({"unused"})
public class YPath {
    /**
     * 获取存储路径，优先使用外部储存
     * /storage/emulated/0/Android/data/com.xx.xx/files/xxDir
     * 忽略警告：1,返回值不处理：ResultOfMethodCallIgnored，2,空指针：ConstantConditions
     *
     * @param context 上下文
     * @param dir     自己的路径，dir前后加"/"都会被去掉
     * @return 路径
     */
    @SuppressWarnings({"ResultOfMethodCallIgnored", "ConstantConditions"})
    public static String getFilePath(Context context, String dir) {
        String directoryPath;
        if (MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {//判断外部存储是否可用
            directoryPath = context.getExternalFilesDir(dir).getAbsolutePath();
        } else {//没外部存储就使用内部存储
            directoryPath = context.getFilesDir() + File.separator + dir;
        }
        File file = new File(directoryPath);
        if (!file.exists()) {//判断文件目录是否存在
            file.mkdirs();
        }
        return directoryPath;
    }

    /**
     * 获得SD卡目录（获取的是手机外置sd卡的路径）
     * /storage/emulated/0
     *
     * @return 路径
     */
    public static String getSDCard() {
        return Environment.getExternalStorageDirectory().getPath();
    }

    /**
     * 获取照片文件夹
     *
     * @return 路径
     */
    public static String getDCIM() {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getPath();
    }

    /**
     * 获取音乐文件夹
     *
     * @return 路径
     */
    public static String getMUSIC() {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).getPath();
    }

    /**
     * 获取图片文件夹
     *
     * @return 路径
     */
    public static String getPICTURES() {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath();
    }

    /**
     * 获取电影文件夹
     *
     * @return 路径
     */
    public static String getMOVIES() {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES).getPath();
    }

    /**
     * 获取下载文件夹
     *
     * @return 路径
     */
    public static String getDOWNLOADS() {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
    }

    /**
     * 获取文档文件夹
     *
     * @return 路径
     */
    public static String getDOCUMENTS() {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getPath();
    }

    /**
     * 获取播客文件夹
     *
     * @return 路径
     */

    public static String getPODCASTS() {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PODCASTS).getPath();
    }

    /**
     * 获取铃声文件夹
     *
     * @return 路径
     */
    public static String getRINGTONES() {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_RINGTONES).getPath();
    }

    /**
     * 获取警告文件夹
     *
     * @return 路径
     */
    public static String getALARMS() {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_ALARMS).getPath();
    }

    /**
     * 获取通知文件夹
     *
     * @return 路径
     */
    public static String getNOTIFICATIONS() {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_NOTIFICATIONS).getPath();
    }

    /**
     * 获得根目录(内部存储路径)
     * /data
     *
     * @return 路径
     */
    public static String getData() {
        return Environment.getDataDirectory().getPath();
    }

    /**
     * 获得缓存目录
     * /data/cache
     *
     * @return 路径
     */
    public static String getCache() {
        return Environment.getDownloadCacheDirectory().getPath();
    }

    /**
     * 获得系统目录
     * /system
     *
     * @return 路径
     */
    public static String getRoot() {
        return Environment.getRootDirectory().getPath();
    }

    /**
     * 获取该程序的安装包路径
     * /data/app/com.xx.xx/base.apk
     *
     * @param context 上下文
     * @return 路径
     */
    public static String getPackageResourcePath(Context context) {
        return context.getPackageResourcePath();
    }

    /**
     * 获取该程序对应的apk文件的路径
     * /data/app/com.xx.xx/base.apk
     *
     * @param context 上下文
     * @return 路径
     */
    public static String getPackageCodePath(Context context) {
        return context.getPackageCodePath();
    }

    /**
     * 返回通过Context.openOrCreateDatabase 创建的数据库文件
     * /data/data/com.xx.xx/databases/xxFileName
     * /data/user/0/com.xx.xx/databases/xxFileName
     *
     * @param context  context
     * @param fileName fileName
     * @return File
     */
    public static File getDatabasePath(Context context, String fileName) {
        return context.getDatabasePath(fileName);
    }

    /**
     * 用于获取APP的files目录
     * /data/data/com.xx.xx/files
     * /data/user/0/com.xx.xx/files
     *
     * @param context 上下文
     * @return 路径
     */
    public static String getFilesDir(Context context) {
        return context.getFilesDir().getPath();
    }

    /**
     * 用于获取APP的cache目录
     * /data/data/com.xx.xx/cache
     * /data/user/0/com.xx.xx/cache
     *
     * @param context 上下文
     * @return 路径
     */
    public static String getCacheDir(Context context) {
        return context.getCacheDir().getPath();
    }

    /**
     * 用于获取APP的在SD卡中的cache目录
     * /mnt/sdcard/Android/data/com.xx.xx/cache
     * /storage/emulated/0/Android/data/com.xx.xx/cache
     *
     * @param context 上下文
     * @return 路径
     */
    public static String getSDCardCacheDir(Context context) {
        return Objects.requireNonNull(context.getExternalCacheDir()).getPath();
    }

    /**
     * 用于获取APP SDK中的obb目录
     * /mnt/sdcard/Android/obb/com.xx.xx
     * /storage/emulated/0/Android/obb/com.xx.xx
     *
     * @param context 上下文
     * @return 路径
     */
    public static String getObbDir(Context context) {
        return context.getObbDir().getPath();
    }
}
