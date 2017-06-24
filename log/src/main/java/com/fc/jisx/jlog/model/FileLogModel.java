package com.fc.jisx.jlog.model;

import java.io.File;

/**
 * Created by jisx on 2017/6/21.
 */

public class FileLogModel extends BaseLogModel {

    private String mFileName;
    private File mParentFile;

    private boolean isShowLog;

    public FileLogModel(String mText) {
        super(mText);
    }


    public FileLogModel(String mFileName, File mParentFile, String mText) {
        super(mText);
        this.mFileName = mFileName;
        this.mParentFile = mParentFile;
    }

    public FileLogModel(String mText, File mParentFile, String mFileName, boolean isShowLog) {
        super(mText);
        this.mFileName = mFileName;
        this.mParentFile = mParentFile;
        this.isShowLog = isShowLog;
    }

    public String getFilePath() {
        return mFileName;
    }

    public void setFilePath(String filePath) {
        this.mFileName = filePath;
    }

    public File getParentFile() {
        return mParentFile;
    }

    public void setParentFile(File parentFile) {
        this.mParentFile = parentFile;
    }

    public boolean isShowLog() {
        return isShowLog;
    }

    public void setShowLog(boolean showLog) {
        isShowLog = showLog;
    }
}
