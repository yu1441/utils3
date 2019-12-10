package com.yujing.kotlinapp.activity.page04

import com.yujing.kotlinapp.R
import com.yujing.kotlinapp.activity.BaseActivity
import com.yujing.url.YUrl
import com.yujing.url.YUrlAndroid
import com.yujing.url.YUrlListener
import com.yujing.utils.YConvert
import com.yujing.utils.YImageDialog
import com.yujing.utils.YShow
import com.yujing.utils.YToast
import com.yujing.ycrash.YCrash
import com.yujing.ycrash.YJson
import kotlinx.android.synthetic.main.activity_0401.*
import java.util.*

class Activity0401 : BaseActivity() {
    override fun setContentView(): Int {
        return R.layout.activity_0401
    }

    override fun initView() {

    }

    override fun initData() {
        button1.setOnClickListener {
            val url = "https://www.baidu.com"
            YUrl.create().get(url, object : YUrlListener {
                override fun success(bytes: ByteArray, value: String) {
                    YToast.show(applicationContext, value)
                }

                override fun fail(value: String) {
                    YToast.show(applicationContext, value)
                }
            })
        }
        button2.setOnClickListener { }

        button3.setOnClickListener {
            http()
        }

        button4.setOnClickListener { https() }
        button5.setOnClickListener { showImage() }
        button6.setOnClickListener {
            YUrlAndroid.setShowLog(false)
            val url =
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1568020924829&di=59810d1c5993a9d229bf4a129239f1d3&imgtype=0&src=http%3A%2F%2Fdingyue.ws.126.net%2F3SKYFUYP2%3DC5hxGxU9CkXvqPuBhYZHW7Pq1%3DMfvBL1Ot61551854534253compressflag.jpg"
            YUrlAndroid.create().get(url, object : YUrlListener {
                override fun success(bytes: ByteArray?, value: String?) {
                    val bitmap = YConvert.bytes2Bitmap(bytes)
                    YImageDialog.show(this@Activity0401, bitmap)
                }

                override fun fail(value: String?) {
                    YShow.show(this@Activity0401, value)
                }
            })

        }
    }

    private fun showImage() {
        val url =
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1568020837996&di=a1370c2ba49ca190f04ce69c81066680&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fq_70%2Cc_zoom%2Cw_640%2Fimages%2F20180724%2Fb24397f0491c47ba9ecd4450d117bb81.jpeg"
        YUrlAndroid.create().get(url, object : YUrlListener {
            override fun success(bytes: ByteArray?, value: String?) {
                val bitmap = YConvert.bytes2Bitmap(bytes)
                YImageDialog.show(this@Activity0401, bitmap)
            }

            override fun fail(value: String?) {
                YShow.show(this@Activity0401, value)
            }
        })
    }


    fun http() {
        val paramsMap = HashMap<String, Any>()
        paramsMap["name"] = "yujing"
        paramsMap["password"] = "yujing"
        YUrlAndroid.create()
            .post("http://39.106.6.5:8080/user/login", paramsMap, object : YUrlListener {
                override fun success(bytes: ByteArray, value: String) {
                    YToast.show(applicationContext, value)
                }

                override fun fail(value: String) {
                    YToast.show(applicationContext, value)
                }
            })
    }

    fun https() {
        val crt = "-----BEGIN CERTIFICATE-----\n" +
                "MIIFPDCCBCSgAwIBAgIBATANBgkqhkiG9w0BAQsFADCBpDELMAkGA1UEBhMCQ04x\n" +
                "EDAOBgNVBAgTB3NpY2h1YW4xEDAOBgNVBAcTB2NoZW5nZHUxGTAXBgNVBAoTEHl1\n" +
                "amluZ2dvbmd6dW9zaGkxEDAOBgNVBAsTB2thaWZhYnUxFTATBgNVBAMTDDMyMDAw\n" +
                "LjMyMC5pbzEPMA0GA1UEKRMGeXVqaW5nMRwwGgYJKoZIhvcNAQkBFg15dTE0NDFA\n" +
                "cXEuY29tMB4XDTE5MDcwNDAzMjY1NFoXDTI5MDcwMTAzMjY1NFowgaQxCzAJBgNV\n" +
                "BAYTAkNOMRAwDgYDVQQIEwdzaWNodWFuMRAwDgYDVQQHEwdjaGVuZ2R1MRkwFwYD\n" +
                "VQQKExB5dWppbmdnb25nenVvc2hpMRAwDgYDVQQLEwdrYWlmYWJ1MRUwEwYDVQQD\n" +
                "EwwzMjAwMC4zMjAuaW8xDzANBgNVBCkTBnl1amluZzEcMBoGCSqGSIb3DQEJARYN\n" +
                "eXUxNDQxQHFxLmNvbTCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBALmy\n" +
                "LHD9GHoks2SRC6Ee2hLuWariPjPLYy7V8wVS2BC8b6IYp44u5Npqwu80pd5l79hN\n" +
                "x+MrCRDKf/YaWasjIb+RjYhiltYlAtvXYN81fWbEe9KITMNULV79nJ5J2nhCNqgL\n" +
                "3hO/x/5gMKkhj0lssD/DFlZRWB7R12MAOavUs6ipK2MGvViCLrcpcerW0kBe+/f9\n" +
                "7dactWJjVHa/e1hLL4TZg07WAf3soiBjAs1DDXgMVYLyhlfgXiqJu3lZWxylRvp2\n" +
                "z69QNPGDTDo3/qfHuMgsHSFO8ohQ7X7Veynxk2MGVWkwio2+Ywq2FT2krB4bhdXT\n" +
                "9zAKl04WyRgtfb1tX6MCAwEAAaOCAXUwggFxMAkGA1UdEwQCMAAwEQYJYIZIAYb4\n" +
                "QgEBBAQDAgZAMDQGCWCGSAGG+EIBDQQnFiVFYXN5LVJTQSBHZW5lcmF0ZWQgU2Vy\n" +
                "dmVyIENlcnRpZmljYXRlMB0GA1UdDgQWBBTAN4ohNUiZdCsaxc5kPaEXgwjh2jCB\n" +
                "2QYDVR0jBIHRMIHOgBTx/bque5MEcW7jkc+Sa+oT0FTdsqGBqqSBpzCBpDELMAkG\n" +
                "A1UEBhMCQ04xEDAOBgNVBAgTB3NpY2h1YW4xEDAOBgNVBAcTB2NoZW5nZHUxGTAX\n" +
                "BgNVBAoTEHl1amluZ2dvbmd6dW9zaGkxEDAOBgNVBAsTB2thaWZhYnUxFTATBgNV\n" +
                "BAMTDDMyMDAwLjMyMC5pbzEPMA0GA1UEKRMGeXVqaW5nMRwwGgYJKoZIhvcNAQkB\n" +
                "Fg15dTE0NDFAcXEuY29tggkA5OdOOv2/2vcwEwYDVR0lBAwwCgYIKwYBBQUHAwEw\n" +
                "CwYDVR0PBAQDAgWgMA0GCSqGSIb3DQEBCwUAA4IBAQCMOm87KtOuRkHA7EJnFXRt\n" +
                "qqZn3raOFH4xTnd6bOcPs0nf2PF+YcQnqIACOUZMyQVAUUTMtaZ+0KK+yMhtS4xt\n" +
                "IUbB/rdg0kQV+bKrXs+p59vG5ji9Ej5McmsDZ/xQpAQznYuQaaiY9LF+i4r7k89W\n" +
                "oA9gP0b8FdJzpVKHqTkQmqWFXqWH5qNocUoaN1IEIxA8+I+JkS348zBx9AkCG13o\n" +
                "4rBI+PGmEB4T4kcULVOBpcZOgTDD+SRFkol+RBYZn5tzeTOuFDPSbbd+5eijNj3T\n" +
                "fKBAAb70RAWg5NOSRGxatrV6HD9DmwlisWDO4B0lnXccz/eaZXrgUa6bbx7kegJ3\n" +
                "-----END CERTIFICATE-----"

        val appInfo = YCrash.AppInfo()
        appInfo.imei = "111"
        appInfo.其他信息 = "222"
        appInfo.崩溃信息 = "333"
        appInfo.isDebug = "true"
        appInfo.软件名称 = " 测试"
        appInfo.包名 = "com"
        appInfo.版本号 = "1"
        appInfo.版本名 = "1.0"

        val paramsMap1 = HashMap<String, Any>()
        paramsMap1["appInfo"] = YJson.toJson(appInfo)
        val url1 = "https://192.168.1.78:8080/crash/submit"
        YUrlAndroid.create().setCrtSSL(crt).post(url1, paramsMap1, object : YUrlListener {
            override fun success(bytes: ByteArray, value: String) {
                show(value)
            }

            override fun fail(value: String) {
                show(value)
            }
        })
    }
}