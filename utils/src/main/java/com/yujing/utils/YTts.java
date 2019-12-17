package com.yujing.utils;

import android.content.Context;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import com.yujing.contract.YListener;

import java.util.Locale;

/**
 * TTS语音合成
 *
 * @author yujing 2019年12月5日09:21:06
 */
public class YTts {
    private static String TAG = "YTts";
    private TextToSpeech textToSpeech; // TTS对象
    private float speechRate = 1.0f;//速度
    private float pitch = 1.0f;//音调
    private boolean initSuccess = false;//初始化状态

    public YTts(final Context context) {
        textToSpeech = new TextToSpeech(context, status -> {
            //TTS语音不支持
        });
    }

    public YTts(final Context context, YListener<Boolean> initListener) {
        textToSpeech = new TextToSpeech(context, status -> {
            if (status == TextToSpeech.SUCCESS) {
                int result = textToSpeech.setLanguage(Locale.CHINA);
                Log.e(TAG, "TTS状态:" + result);
                if (result == TextToSpeech.LANG_MISSING_DATA) {
                    Log.e(TAG, "语言包丢失");
                    initSuccess = false;
                } else if (result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Log.e(TAG, "语音不支持");
                    initSuccess = false;
                }
                initSuccess = true;
            }
            initListener.value(initSuccess);
        });
    }

    /**
     * 获取初始化状态
     * @return 是否初始化成功
     */
    public boolean isInitSuccess() {
        return initSuccess;
    }

    /**
     * 获取textToSpeech对象
     *
     * @return textToSpeech
     */
    public TextToSpeech getTextToSpeech() {
        return textToSpeech;
    }

    /**
     * 获取播放速度
     *
     * @return 速度
     */
    public float getSpeechRate() {
        return speechRate;
    }

    /**
     * 设置播放速度
     *
     * @param speechRate 速度
     */
    public void setSpeechRate(float speechRate) {
        this.speechRate = speechRate;
    }

    /**
     * 获取音调
     *
     * @return 音调
     */
    public float getPitch() {
        return pitch;
    }

    /**
     * 设置音调
     *
     * @param pitch 音调
     */
    public void setPitch(float pitch) {
        this.pitch = pitch;
    }


    /**
     * 设置速度和音调
     *
     * @param speechRate 速度
     * @param pitch      音调
     */
    public void setSpeechRatePitch(float speechRate, float pitch) {
        setSpeechRate(speechRate);
        setPitch(pitch);
    }

    /**
     * 语音播放
     *
     * @param speak      语音播放文字内容
     * @param speechRate 速度
     * @param pitch      音调
     */
    public void speak(String speak, float speechRate, float pitch) {
        setSpeechRate(speechRate);
        setPitch(pitch);
        speak(speak);
    }

    /**
     * 语音播放
     *
     * @param speak 语音播放文字内容
     */
    public void speak(String speak) {
        if (textToSpeech != null) {
            textToSpeech.setSpeechRate(speechRate);//速度
            textToSpeech.setPitch(pitch);// 设置音调，值越大声音越尖（女生），值越小则变成男声,1.0是常规
            if (Build.VERSION.SDK_INT >= 21) {
                textToSpeech.speak(speak, TextToSpeech.QUEUE_FLUSH, null, null);
            } else {
                textToSpeech.speak(speak, TextToSpeech.QUEUE_FLUSH, null);
            }
        }
    }

    /**
     * 语音队列播放
     *
     * @param speak      语音播放文字内容
     * @param speechRate 速度
     * @param pitch      音调
     */
    public void speakQueue(String speak, float speechRate, float pitch) {
        setSpeechRate(speechRate);
        setPitch(pitch);
        speakQueue(speak);
    }

    /**
     * 语音队列播放
     *
     * @param speak 语音播放文字内容
     */
    public void speakQueue(String speak) {
        if (textToSpeech != null) {
            textToSpeech.setSpeechRate(speechRate);//速度
            textToSpeech.setPitch(pitch);// 设置音调，值越大声音越尖（女生），值越小则变成男声,1.0是常规
            if (Build.VERSION.SDK_INT >= 21) {
                textToSpeech.speak(speak, TextToSpeech.QUEUE_ADD, null, null);
            } else {
                textToSpeech.speak(speak, TextToSpeech.QUEUE_ADD, null);
            }
        }
    }


    /**
     * 停止
     */
    public void onStop() {
        if (textToSpeech != null && textToSpeech.isSpeaking())
            textToSpeech.stop(); // 不管是否正在朗读TTS都被打断
    }

    /**
     * 关闭，释放资源
     */
    public void onDestroy() {
        if (textToSpeech != null) textToSpeech.shutdown(); // 关闭，释放资源
    }
}
