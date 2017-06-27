package com.fc.jisx.jlog;

import java.io.File;

/**
 * File description.
 *
 * @author jisx
 */
public class JBuilder {

    private boolean isShowLog;

    private RuntimeType mRuntimeType;

    private boolean isWriteToFile;

    private JLogLevel mJLogLevelToFile;

    private File mParentFile;

    private String fileName;

    private int mMaxLength;

    private String mTag;

    public JBuilder() {
        this.isShowLog = true;
        this.isWriteToFile = false;
        this.mJLogLevelToFile = JLogLevel.ALL;
        this.mParentFile = null;
        this.fileName = "";
        this.mRuntimeType = RuntimeType.JAVA;
        this.mMaxLength = 1000;
        this.mTag = null;
    }

    public boolean isShowLog() {
        return isShowLog;
    }

    public JBuilder setShowLog(boolean showLog) {
        isShowLog = showLog;
        return this;
    }

    public boolean isWriteToFile() {
        return isWriteToFile;
    }

    public JBuilder setWriteToFile(boolean writeToFile) {
        isWriteToFile = writeToFile;
        return this;
    }

    public JLogLevel getJLogLevelToFile() {
        return mJLogLevelToFile;
    }

    public JBuilder setJLogLevelToFile(JLogLevel JLogLevelToFile) {
        mJLogLevelToFile = JLogLevelToFile;
        return this;
    }

    public File getParentFile() {
        return mParentFile;
    }

    public JBuilder setParentFile(File parentFile) {
        mParentFile = parentFile;
        return this;
    }

    public String getFileName() {
        return fileName;
    }

    public JBuilder setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public RuntimeType getRuntimeType() {
        return mRuntimeType;
    }

    public JBuilder setRuntimeType(RuntimeType runtimeType) {
        mRuntimeType = runtimeType;
        return this;
    }

    public int getMaxLength() {
        return mMaxLength;
    }

    public JBuilder setMaxLength(int maxLength) {
        mMaxLength = maxLength;
        return this;
    }

    public String getTag() {
        return mTag;
    }

    public JBuilder setTag(String tag) {
        mTag = tag;
        return this;
    }
}
