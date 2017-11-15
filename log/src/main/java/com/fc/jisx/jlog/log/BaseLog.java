package com.fc.jisx.jlog.log;

import android.util.Log;

import com.fc.jisx.jlog.DateType;
import com.fc.jisx.jlog.DateUtils;
import com.fc.jisx.jlog.JBuilder;
import com.fc.jisx.jlog.JLog;
import com.fc.jisx.jlog.JLogLevel;
import com.fc.jisx.jlog.model.ParseToString;
import com.fc.jisx.jlog.model.Print;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by zhaokaiqiang on 15/11/18.
 */
public abstract class BaseLog implements Print, ParseToString {

    private static final String SUFFIX = ".java";

    private static JBuilder mBuilder = JLog.getBuilder();


    public int getStackTraceIndex(String tag) {
        return tag == null ? 7 : 6;
    }

    /**
     * 获取打印所在的类、行数和方法
     *
     * @param index 下标
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

    @Override
    public void print(JLogLevel jLogLevel, String tag, Object object) {

        mBuilder = JLog.getBuilder();

        int index = 0;

        String msg = parseToString(object);

        int length = msg.length();

        //获取打印最大长度
        int maxLength = mBuilder.getMaxLength();

        int countOfSub = length / maxLength;

        String[] strings = wrapperContent(getStackTraceIndex(tag));

        if (countOfSub > 0) {
            String sub;
            for (int i = 0; i < countOfSub; i++) {
                sub = msg.substring(index, index + maxLength);
                if (i == 0) {
                    printRuntimeType(jLogLevel, getTag(tag, strings[0]), strings[1] + "\n" + sub);
                } else {
                    printRuntimeType(jLogLevel, getTag(tag, strings[0]), sub);
                }
                index += maxLength;
            }
            printRuntimeType(jLogLevel, getTag(tag, strings[0]), msg.substring(index, length));
        } else {
            printRuntimeType(jLogLevel, getTag(tag, strings[0]), strings[1] + msg);
        }
    }

    public String getTag(String tag, String classTag) {
        if (tag == null) {
            if (mBuilder.getTag() != null) {
                return mBuilder.getTag();
            } else {
                return classTag;
            }
        }
        return tag;
    }

    private void printRuntimeType(JLogLevel logLevel, String tag, String sub) {

        switch (mBuilder.getRuntimeType()) {
            case ANDROID:
                printAndroidText(logLevel, tag, sub);
                break;
            case JAVA:
                printJavaText(logLevel, tag, sub);
                break;
        }

        if (mBuilder.isWriteToFile() && mBuilder.getJLogLevelToFile().getLevel() <= logLevel.getLevel()) {
            saveToFile(logLevel, tag, mBuilder.getParentFile(), mBuilder.getFileName(), sub);
        }


    }

    private void saveToFile(JLogLevel logLevel, String tag, File parentFile, String fileName, String sub) {
        if (parentFile.exists()) {
            try {
                File file = new File(parentFile, fileName);
                PrintStream p = new PrintStream(new BufferedOutputStream(new FileOutputStream(file, true)), true, "UTF-8");
                p.write((DateUtils.getCurrentDate(DateType.N_YMdHms) + sub + "\n").getBytes("UTF-8"));
                p.close();
            } catch (Throwable e) {
                print(logLevel, tag, "write to logfile error" + e.getMessage());
            }

        } else {
            print(logLevel, tag, "parentFile(logFile) not exist,create file first");
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
        if (logger.getHandlers().length < 1) {
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.ALL);
            logger.addHandler(consoleHandler);
        }
        logger.log(Level.parse(type.toString()), sub);
    }

}
