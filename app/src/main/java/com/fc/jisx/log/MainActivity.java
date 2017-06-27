package com.fc.jisx.log;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.fc.jisx.jlog.JBuilder;
import com.fc.jisx.jlog.JLog;
import com.fc.jisx.jlog.JLogLevel;
import com.fc.jisx.jlog.RuntimeType;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void logI(View view) {
        JLog.TEXT.i("打印测试");
    }

    public void logV(View view) {

        JLog.TEXT.v("打印测试");
    }

    public void logD(View view) {

        JLog.TEXT.d("打印测试");
    }

    public void logW(View view) {
        JLog.TEXT.w("打印测试");

    }

    public void logE(View view) {
        JLog.TEXT.e("打印测试");

    }

    public void logFile(View view) {
        JLog.JSON.d(this.getClass().getName(), "打印测试");

    }

    public void logJson(View view) {
        JLog.JSON.e(this.getClass().getName(), "[{\"CityId\":18,\"CityName\":\"西安\",\"ProvinceId\":27,\"CityOrder\":1},{\"CityId\":53,\"CityName\":\"广州\",\"ProvinceId\":27,\"CityOrder\":1}]");

    }

    public void logXml(View view) {
        JLog.XML.d("<?xml version='1.0' encoding='UTF-8'?>" +
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
        JBuilder jBuilder = new JBuilder();
        jBuilder.setRuntimeType(RuntimeType.ANDROID);
        jBuilder.setTag("main");
        jBuilder.setJLogLevelToFile(JLogLevel.WARN);
        jBuilder.setWriteToFile(true);
        jBuilder.setParentFile(Environment.getExternalStorageDirectory());
        jBuilder.setFileName("test.txt");
        JLog.setBuilder(jBuilder);
        ((TextView) view).setText(jBuilder.isWriteToFile() ? "可以写到文件" : "不可以写到文件");
    }

}
