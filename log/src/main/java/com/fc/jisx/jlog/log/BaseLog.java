package com.fc.jisx.jlog.log;

import android.support.annotation.NonNull;
import android.util.Log;


import com.fc.jisx.jlog.JLogLevel;
import com.fc.jisx.jlog.JLogUtil;
import com.fc.jisx.jlog.RuntimeType;
import com.fc.jisx.jlog.model.ParseToString;
import com.fc.jisx.jlog.model.Print;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by zhaokaiqiang on 15/11/18.
 */
public abstract class BaseLog implements Print, ParseToString {

    private static final String SUFFIX = ".java";

    /**
     * 获取打印所在的类、行数和方法
     *
     * @return 返回类名、方法和所在的行数
     */
    public String[] wrapperContent(int index) {
        String[] strings = new String[2];
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

        StackTraceElement targetElement = stackTrace[index];
        String className = targetElement.getClassName();
        String[] classNameInfo = className.split("\\.");
        if (classNameInfo.length > 0) {
            className = classNameInfo[classNameInfo.length - 1] + SUFFIX;
        }

        if (className.contains("$")) {
            className = className.split("\\$")[0] + SUFFIX;
        }

        String methodName = targetElement.getMethodName();

        int lineNumber = targetElement.getLineNumber();

        if (lineNumber < 0) {
            lineNumber = 0;
        }

        strings[0] = className;
        strings[1] = "[ (" + className + ":" + lineNumber + ")#" + methodName + " ] ";

        return strings;
    }

    public int getStackTraceIndex(String tag) {
        return tag == null ? 7 : 6;
    }

    /**
     * 打印
     *
     * @param runtimeType 运行的环境
     * @param logLevel    日志级别
     * @param tag         tag
     * @param obj         具体的内容
     */
    @Override
    public void print(RuntimeType runtimeType, JLogLevel logLevel, String tag, Object obj) {

        String[] wrapperContent = wrapperContent(getStackTraceIndex(tag));

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(wrapperContent[1]).append("\n");
        stringBuilder.append(parseToString(obj)).append("\n");
        printRuntimeType(runtimeType, logLevel, tag == null ? wrapperContent[0] : tag, stringBuilder.toString());
    }

    /**
     * 可以直接调用打印，不携带类名和方法名
     *
     * @param runtimeType 运行的环境
     * @param logLevel    日志级别
     * @param tag         tag
     * @param str         要打印的字符串
     */
    public void print(RuntimeType runtimeType, JLogLevel logLevel, @NonNull String tag, String str) {
        printRuntimeType(runtimeType, logLevel, tag, str);
    }

    /**
     * 打印错误信息
     *
     * @param runtimeType 运行的环境
     * @param tag         tag
     * @param t           要打印的错误
     */
    public void printError(RuntimeType runtimeType, String tag, Throwable t) {

        String[] wrapperContent = wrapperContent(getStackTraceIndex(tag));

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(wrapperContent[1]).append("\n");
        stringBuilder.append(JLogUtil.parseThrowableToString(t)).append("\n");
        printRuntimeType(runtimeType, JLogLevel.ERROR, tag == null ? wrapperContent[0] : tag, stringBuilder.toString());
    }

    private void printRuntimeType(RuntimeType runtimeType, JLogLevel logLevel, String tag, String sub) {

        switch (runtimeType) {
            case ANDROID:
                printAndroidText(logLevel, tag, sub);
                break;
            case JAVA:
                printJavaText(logLevel, tag, sub);
                break;
            default:
                printJavaText(logLevel, tag, sub);
                break;
        }
    }

    private void printAndroidText(JLogLevel logLevel, String tag, String sub) {
        switch (logLevel) {
            case ALL:
                Log.v(tag, sub);
                break;
            case VERBOSE:
                Log.v(tag, sub);
                break;
            case DEBUG:
                Log.d(tag, sub);
                break;
            case INFO:
                Log.i(tag, sub);
                break;
            case WARN:
                Log.w(tag, sub);
                break;
            case ERROR:
                Log.e(tag, sub);
                break;
        }
    }

    private static void printJavaText(JLogLevel type, String tag, String sub) {
        Logger logger = Logger.getLogger(tag);
        logger.setLevel(Level.ALL);
        if (logger.getHandlers().length == 0) {
            // 输出到控制台
            ConsoleHandler consoleHandler = new ConsoleHandler();
            // 设置输出级别
            consoleHandler.setLevel(Level.ALL);
            logger.addHandler(consoleHandler);
        }

        logger.log(Level.parse(type.toString()), sub);
    }
}
