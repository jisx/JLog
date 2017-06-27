package com.fc.jisx.jlog;


import com.fc.jisx.jlog.log.BaseLog;
import com.fc.jisx.jlog.log.LogFactory;

import java.io.File;

/**
 * jsx
 */
public enum JLog {

    JSON, XML, TEXT;

    private static JBuilder mBuilder;

    public void v(Object object) {
        v(null, object);
    }

    public void v(String tag, Object object) {
        printLog(JLogLevel.VERBOSE, tag, object);
    }

    public void i(Object object) {
        i(null, object);
    }

    public void i(String tag, Object object) {
        printLog(JLogLevel.INFO, tag, object);
    }

    public void d(Object object) {
        d(null, object);
    }

    public void d(String tag, Object object) {
        printLog(JLogLevel.DEBUG, tag, object);
    }

    public void w(Object object) {
        w(null, object);
    }

    public void w(String tag, Object object) {
        printLog(JLogLevel.WARN, tag, object);
    }

    public void e(Object object) {
        e(null, object);
    }

    public void e(String tag, Object object) {
        printLog(JLogLevel.ERROR, tag, object);
    }

    private void printLog(JLogLevel logLevel, String tag, Object object) {
        if (mBuilder == null) {
            mBuilder = new JBuilder();
        }
        BaseLog baseLog = LogFactory.create(this, mBuilder);
        baseLog.print(logLevel, tag, object);
    }

    public static void setBuilder(JBuilder builder) {
        mBuilder = builder;
    }


}
