package com.fc.jisx.log;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.fc.jisx.jlog.JLog;
import com.fc.jisx.jlog.JLogLevel;
import com.fc.jisx.jlog.LogConfiguration;
import com.fc.jisx.jlog.printer.config.FileConfig;
import com.fc.jisx.jlog.utils.SDCardUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        JLog.init();
        String s = "/test";
        String path = SDCardUtils.getSDPath() + s;
        JLog.init(new LogConfiguration.Builder().logLevel(JLogLevel.DEBUG.getLevel())
                .file(new FileConfig(path,7,500000,JLogLevel.INFO)).android().build());

    }


    public void logI(View view) {
        JLog.i("xxx", "打印测试");
    }

    public void logV(View view) {

        JLog.v("xxx", "打印测试");
    }

    public void logD(View view) {

        JLog.d("xxx", "打印测试");
    }

    public void logW(View view) {
        JLog.w("xxx", "打印测试");
    }

    public void logE(View view) {
        JLog.e("xxx", "打印测试");

    }

    public void logFile(View view) {

    }

    public void logJson(View view) {
        JLog.json("[{\"CityId\":18,\"CityName\":\"西安\",\"ProvinceId\":27,\"CityOrder\":1},{\"CityId\":53,\"CityName\":\"广州\",\"ProvinceId\":27,\"CityOrder\":1}]");

    }

    public void logXml(View view) {
        JLog.xml("<?xml version='1.0' encoding='UTF-8'?>" +
                "<SMSMSGRECEIVES>" +
                "<SMSMSGRECEIVE>" +
                "<MSGID>2014062940010629110513337600</MSGID>" +
                "<DESTTERMID>12345</DESTTERMID>" +
                "<SRCTERMID>13301639266</SRCTERMID>" +
                "<MSGCONTENT></MSGCONTENT>" +
                "<RECVTIME>2014-06-29 11:05:14</RECVTIME>" +
                "</SMSMSGRECEIVE>" +
                "<SMSMSGRECEIVE>" +
                "<MSGID>2014062940010629105000767600</MSGID>" +
                "<DESTTERMID>12345</DESTTERMID>" +
                "<SRCTERMID>13301639266</SRCTERMID>" +
                "<MSGCONTENT>行情波段定，不固定。</MSGCONTENT>" +
                "<RECVTIME>2014-06-29 10:50:02</RECVTIME>" +
                "</SMSMSGRECEIVE>" +
                "</SMSMSGRECEIVES>");

    }

    boolean flag = false;

    public void logWriteFile(View view) {
    }

}
