package com.fc.jisx.log;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.fc.jisx.jlog.JLog;
import com.fc.jisx.jlog.RuntimeType;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JLog.setRuntimeType(RuntimeType.ANDROID);
        JLog.setFile(Environment.getExternalStorageDirectory(),"log.txt");
    }


    public void logI(View view) {
        JLog.i("打印测试");
        JLog.i(this.getClass().getName(), "打印测试");
    }

    public void logV(View view) {

        JLog.v("打印测试");
        JLog.v(this.getClass().getName(), "打印测试");
    }

    public void logD(View view) {

        JLog.d("打印测试");
        JLog.d(this.getClass().getName(), "打印测试");
    }

    public void logW(View view) {
        JLog.w("打印测试");
        JLog.w(this.getClass().getName(), "打印测试");

    }

    public void logE(View view) {
        JLog.e("打印测试");
        JLog.e(this.getClass().getName(), "打印测试");

    }

    public void logFile(View view) {
        JLog.file("打印测试");
        JLog.file(this.getClass().getName(), "打印测试");

    }

    public void logJson(View view) {
        JLog.json("[{\"CityId\":18,\"CityName\":\"西安\",\"ProvinceId\":27,\"CityOrder\":1},{\"CityId\":53,\"CityName\":\"广州\",\"ProvinceId\":27,\"CityOrder\":1}]");
        JLog.json(this.getClass().getName(), "[{\"CityId\":18,\"CityName\":\"西安\",\"ProvinceId\":27,\"CityOrder\":1},{\"CityId\":53,\"CityName\":\"广州\",\"ProvinceId\":27,\"CityOrder\":1}]");

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
                "<SMSMSGRECEIVE>"+
                "<MSGID>2014062940010629105000767600</MSGID>"+
                "<DESTTERMID>12345</DESTTERMID>"+
                "<SRCTERMID>13301639266</SRCTERMID>"+
                "<MSGCONTENT>行情波段定，不固定。</MSGCONTENT>"+
                "<RECVTIME>2014-06-29 10:50:02</RECVTIME>"+
                "</SMSMSGRECEIVE>"+
                "</SMSMSGRECEIVES>");
        JLog.xml(this.getClass().getName(), "<?xml version='1.0' encoding='UTF-8'?>" +
                "<SMSMSGRECEIVES>" +
                "<SMSMSGRECEIVE>" +
                "<MSGID>2014062940010629110513337600</MSGID>" +
                "<DESTTERMID>12345</DESTTERMID>" +
                "<SRCTERMID>13301639266</SRCTERMID>" +
                "<MSGCONTENT></MSGCONTENT>" +
                "<RECVTIME>2014-06-29 11:05:14</RECVTIME>" +
                "</SMSMSGRECEIVE>" +
                "<SMSMSGRECEIVE>"+
                "<MSGID>2014062940010629105000767600</MSGID>"+
                "<DESTTERMID>12345</DESTTERMID>"+
                "<SRCTERMID>13301639266</SRCTERMID>"+
                "<MSGCONTENT>行情波段定，不固定。</MSGCONTENT>"+
                "<RECVTIME>2014-06-29 10:50:02</RECVTIME>"+
                "</SMSMSGRECEIVE>"+
                "</SMSMSGRECEIVES>");

    }

}
