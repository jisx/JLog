package com.fc.jisx.jlog;


import com.fc.jisx.jlog.decorate.Decorate;
import com.fc.jisx.jlog.printer.Printer;

/**
 * jsx
 */
public class JLog {

    private static LogConfiguration sLogConfiguration;

    public static void init() {
        sLogConfiguration = new LogConfiguration.Builder().build();
    }

    public static void init(LogConfiguration logConfiguration) {

        if (logConfiguration == null) {
            throw new IllegalArgumentException("Please specify a LogConfiguration");
        }

        sLogConfiguration = logConfiguration;
    }

    public static void v(Object object) {
        v(null, object);
    }

    public static void v(String tag, Object object) {
        printLog(JLogLevel.VERBOSE, null, tag, object);
    }

    public static void d(Object object) {
        d(null, object);
    }

    public static void d(String tag, Object object) {
        printLog(JLogLevel.DEBUG, null, tag, object);
    }

    public static void i(Object object) {
        i(null, object);
    }

    public static void i(String tag, Object object) {
        printLog(JLogLevel.INFO, null, tag, object);
    }

    public static void w(Object object) {
        w(null, object);
    }

    public static void w(String tag, Object object) {
        printLog(JLogLevel.WARN, null, tag, object);
    }

    public static void e(Object object) {
        e(null, object);
    }

    public static void e(String tag, Object object) {
        printLog(JLogLevel.ERROR, null, tag, object);
    }

    public static void json(String object) {
        json(null, object);
    }

    public static void json(String tag, String object) {
        printLog(JLogLevel.DEBUG, FormatterType.JSON, tag, object);
    }

    public static void json(JLogLevel logLevel, String tag, String object) {
        printLog(logLevel, FormatterType.JSON, tag, object);
    }

    public static void xml(String object) {
        xml(null, object);
    }

    public static void xml(String tag, String object) {
        printLog(JLogLevel.DEBUG, FormatterType.XML, tag, object);
    }

    public static void xml(JLogLevel logLevel, String tag, String object) {
        printLog(logLevel, FormatterType.XML, tag, object);
    }

    private static void printLog(JLogLevel logLevel, FormatterType jLogType, String tag, Object object) {

        if (sLogConfiguration == null) {
            throw new IllegalStateException("日志工具还未初始化");
        }

        //打印的日志级别判断
        if (sLogConfiguration.logLevel >= logLevel.getLevel()) {
            return;
        }

        for (Class aClass : sLogConfiguration.mFormatterHashMap.keySet()) {
            //根据类型找对应的格式化处理类
            if (object.getClass() == aClass) {
                String message = sLogConfiguration.mFormatterHashMap.get(aClass).format(object, jLogType);

                //修饰打印出来的效果
                for (Decorate decorate : sLogConfiguration.mDecorateList.values()) {
                    message = decorate.handle(message);
                }

                //打印
                for (Printer printer : sLogConfiguration.mPrinterList.values()) {
                    printer.println(logLevel.getLevel(), tag == null ? sLogConfiguration.tag : tag, message);
                }

                return;
            }
        }

    }

}
