package com.yujing.kotlinapp;

import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DecimalFormat;

/**
 * 计算屏幕宽度，实现分辨率兼容自动缩放
 *
 * @author yujing  2019年11月13日16:27:51
 * 像素密度=DPI/160
 * 屏幕宽度DP=实际宽度/像素密度
 * //获取屏幕信息
 * WindowManager manager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
 * if (manager != null) {
 * DisplayMetrics metrics = new DisplayMetrics();
 * manager.getDefaultDisplay().getMetrics(metrics);
 * int width = metrics.widthPixels;
 * int height = metrics.heightPixels;
 * int densityDPI = metrics.densityDpi;
 * }
 * //屏幕屏幕最小宽度
 * Configuration config = getResources().getConfiguration();
 * int smallestWidth = config.smallestScreenWidthDp;
 * //屏幕屏幕信息
 * DisplayMetrics dm = getResources().getDisplayMetrics();
 * show("分辨率：" + dm.widthPixels + "*" + dm.heightPixels + "\nDPI:" + dm.densityDpi + "\n最小宽度" + smallestWidth);
 *
 * // 2秒后显示分辨率宽度
 * addDisposable(Observable.timer(2, TimeUnit.SECONDS)
 * .subscribeOn(Schedulers.io())
 * .observeOn(AndroidSchedulers.mainThread())
 * .subscribe(value -> {
 * //屏幕屏幕最小宽度
 * Configuration config = getResources().getConfiguration();
 * int smallestWidth = config.smallestScreenWidthDp;
 * //屏幕屏幕信息
 * DisplayMetrics dm = getResources().getDisplayMetrics();
 * show("分辨率：" + dm.widthPixels + "*" + dm.heightPixels + "\nDPI:" + dm.densityDpi + "\n最小宽度" + smallestWidth);
 * }));
 */
public class Values_sw {
    private static DecimalFormat df = new DecimalFormat("#0.00");

    @Test
    public void run() {
        double defaultWidth = 1080;//默认宽度，开发采用的屏幕宽度
        double defaultDpi = 240d;//默认dpi，开发采用的设备dpi

        final double defaultSWidth = defaultWidth / (defaultDpi / 160d);//默认最小宽度,smallestWidth
        final double minDpi = 160d;//最小dpi
        final double maxDpi = 540d;//最大dpi
        final double step = 20;//步长

        setDimen(1d, "values");
        for (double dpi = minDpi; dpi <= maxDpi; dpi += step) {
            final double density = dpi / 160d;//密度
            final double sWidth = defaultWidth / density;//最小宽度,smallestWidth
            System.out.print("当前DPI=" + dpi + "  当前像素密度=" + density + "  屏幕宽度DP=" + df.format(sWidth));
            setDimen(sWidth / defaultSWidth, "values-sw" + ((int) sWidth) + "dp");
        }

//        setDimen(320d / 720d, "values-sw320dp");
//        setDimen(360d / 720d, "values-sw360dp");
//        setDimen(480d / 720d, "values-sw480dp");
//        setDimen(640d / 720d, "values-sw640dp");
    }

    static void setDimen(double scale, String name) {
        StringBuilder sb = new StringBuilder();
        PrintWriter out;
        try {
            sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" + "<resources>\n");
            //dp
            for (int i = 0; i <= 500; i++) {
                sb.append("<dimen name=\"dp" + i + "\">" + df.format(i * scale) + "dp</dimen>\n");
            }
            //sp
            for (int i = 0; i <= 500; i++) {
                sb.append("<dimen name=\"sp" + i + "\">" + df.format(i * scale) + "sp</dimen>\n");
            }
            sb.append("</resources>");

            //这里是文件名，1 注意修改 sw 后面的值，和转换值一一对应  2 文件夹和文件要先创建好否则要代码创建
            String fileDef = "src/main/res/" + name + "/dimens.xml";
            File file = new File(fileDef);
            file.getParentFile().mkdirs();
            out = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            out.println(sb.toString());
            out.close();
            System.out.println("\t 创建完成：" + name + " 比例为：" + df.format(scale) + " 路径为：" + file.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
