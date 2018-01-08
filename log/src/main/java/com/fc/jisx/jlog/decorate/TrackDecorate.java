package com.fc.jisx.jlog.decorate;

import android.annotation.SuppressLint;

import com.fc.jisx.jlog.utils.StackTraceUtil;

/**
 * 日志文字的边框处理
 *
 * @author jisx
 * @date Created in 2018/1/6
 * @modify By:
 *
 * <br>[ (MainActivity.java:42)#onCreated ] 打印测试
 */
public class TrackDecorate extends Decorate {

    private static final String SUFFIX = ".java";

    @Override
    public String handle(String message) {
        StackTraceElement stackTrack = StackTraceUtil.getStackTrack();
        String className = stackTrack.getClassName();
        String[] classNameInfo = className.split("\\.");
        if (classNameInfo.length > 0) {
            className = classNameInfo[classNameInfo.length - 1] + SUFFIX;
        }

        if (className.contains("$")) {
            className = className.split("\\$")[0] + SUFFIX;
        }

        String methodName = stackTrack.getMethodName();

        int lineNumber = stackTrack.getLineNumber();

        if (lineNumber < 0) {
            lineNumber = 0;
        }

        message = "[ (" + className + ":" + lineNumber + ") #" + methodName + " ]\n" + message;
        return message;
    }

}
