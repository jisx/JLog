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
        printLog(JLogLevel.VERBOSE, JLogType.TEXT, tag, object);
    }

    public static void d(Object object) {
        d(null, object);
    }

    public static void d(String tag, Object object) {
        printLog(JLogLevel.DEBUG, JLogType.TEXT, tag, object);
    }

    public static void i(Object object) {
        i(null, object);
    }

    public static void i(String tag, Object object) {
        printLog(JLogLevel.INFO, JLogType.TEXT, tag, object);
    }

    public static void w(Object object) {
        w(null, object);
    }

    public static void w(String tag, Object object) {
        printLog(JLogLevel.WARN, JLogType.TEXT, tag, object);
    }

    public static void e(Object object) {
        e(null, object);
    }

    public static void e(String tag, Object object) {
        printLog(JLogLevel.ERROR, JLogType.TEXT, tag, object);
    }

    public static void json(Object object) {
        json(null, object);
    }

    public static void json(String tag, Object object) {
        printLog(JLogLevel.DEBUG, JLogType.JSON, tag, object);
    }

    public static void xml(Object object) {
        xml(null, object);
    }

    public static void xml(String tag, Object object) {
        printLog(JLogLevel.DEBUG, JLogType.XML, tag, object);
    }

    private static void printLog(JLogLevel logLevel, JLogType jLogType, String tag, Object object) {
        if (mBuilder == null) {
            mBuilder = new JBuilder();
        }

        if (mBuilder.isShowLog()) {
            LogFactory.INSTANCE.getLog(jLogType).print(logLevel, tag, object);
        }
    }

    public static void setBuilder(JBuilder builder) {
        mBuilder = builder;
    }

    public static JBuilder getBuilder() {
        return mBuilder;
    }
}
