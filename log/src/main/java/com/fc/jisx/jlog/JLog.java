package com.fc.jisx.jlog;


import com.fc.jisx.jlog.log.BaseLog;
import com.fc.jisx.jlog.log.LogFactory;
import com.fc.jisx.jlog.model.FileLogModel;

import java.io.File;

/**
 *  jsx
 */
public final class JLog {

    private static boolean isShowLog = true;

    private static RuntimeType runtimeType = RuntimeType.JAVA;

    private static File parentFile;

    private static String fileName;

    public static void v(Object object) {
        v(null, object);
    }

    public static void v(String tag, Object object) {
        printLog(JLogLevel.VERBOSE, JLogType.TEXT, tag, object);
    }

    public static void i(Object object) {
        i(null, object);
    }

    public static void i(String tag, Object object) {
        printLog(JLogLevel.INFO, JLogType.TEXT, tag, object);
    }

    public static void d(Object object) {
        d(null, object);
    }

    public static void d(String tag, Object object) {
        printLog(JLogLevel.DEBUG, JLogType.TEXT, tag, object);
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

    /**
     * 调用前需要调用 setFile()方法
     * @param text 打印内容
     */
    public static void file(String text) {
        file(null, text);
    }

    public static void file(String tag, String text) {
        printFile(JLogLevel.DEBUG, JLogType.FILE, tag, new FileLogModel(text, getParentFile(), getFileName(), isShowLog));
    }

    public static void json(Object json) {
        json(null, json);
    }

    public static void json(String tag, Object text) {
        printFile(JLogLevel.DEBUG, JLogType.JSON, tag, text);
    }

    public static void xml(Object obj) {
        xml(null, obj);
    }

    public static void xml(String tag, Object obj) {
        printFile(JLogLevel.DEBUG, JLogType.XML, tag, obj);
    }

    private static void printFile(JLogLevel logLevel, JLogType logType, String tag, Object object) {
        if(object == null){
            return;
        }
        BaseLog baseLog = LogFactory.create(logType);
        baseLog.print(runtimeType, logLevel, tag, object);
    }

    private static void printLog(JLogLevel logLevel, JLogType logType, String tag, Object object) {
        if(object == null){
            return;
        }
        if (isShowLog) {
            BaseLog baseLog = LogFactory.create(logType);
            baseLog.print(runtimeType, logLevel, tag, object);
        }
    }

    public static void setRuntimeType(RuntimeType runtimeType) {
        JLog.runtimeType = runtimeType;
    }

    public static File getParentFile() {
        return parentFile;
    }

    public static void setFile(File parentFile, String fileName) {
        JLog.parentFile = parentFile;
        JLog.fileName = fileName;
    }

    public static String getFileName() {
        return fileName;
    }

}
