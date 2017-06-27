package com.fc.jisx.jlog.model;


import com.fc.jisx.jlog.JLogLevel;

/**
 * Created by jisx on 2017/6/21.
 */

public interface Print {

    void print(JLogLevel jLogLevel, String tag, Object object);

    /**
     * 为了获取日志打印的类和行数 参数是DEBUG 查看下标获取的
     * @param tag 文本
     * @return 下标
     */
    int getStackTraceIndex(String tag);
}
