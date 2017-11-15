package com.fc.jisx.jlog;


import com.fc.jisx.jlog.log.LogFactory;

/**
 * jsx
 */
public enum JLog {
    ;

    private static JBuilder mBuilder;

    public static void v(Object object) {
        v(null, object);
    }

    public static void v(String tag, Object object) {
        printLog(JLogLevel.VERBOSE, tag, object);
    }

    public static void i(Object object) {
        i(null, object);
    }

    public static void i(String tag, Object object) {
        printLog(JLogLevel.INFO, tag, object);
    }

    public static void d(Object object) {
        d(null, object);
    }

    public static void d(String tag, Object object) {
        printLog(JLogLevel.DEBUG, tag, object);
    }

    public static void w(Object object) {
        w(null, object);
    }

    public static void w(String tag, Object object) {
        printLog(JLogLevel.WARN, tag, object);
    }

    public static void e(Object object) {
        e(null, object);
    }

    public static void e(String tag, Object object) {
        printLog(JLogLevel.ERROR, tag, object);
    }

    private static void printLog(JLogLevel logLevel, String tag, Object object) {
        if (mBuilder == null) {
            mBuilder = new JBuilder();
        }

        if (mBuilder.isShowLog()) {
            LogFactory.create(object, mBuilder).print(logLevel, tag, object);
        }
    }

    public static void setBuilder(JBuilder builder) {
        mBuilder = builder;
    }


}
