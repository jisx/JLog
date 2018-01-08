package com.fc.jisx.jlog.printer.config;

import com.fc.jisx.jlog.JLogLevel;
import com.fc.jisx.jlog.printer.PrinterConfig;

/**
 * File description.
 *
 * @author jisx
 */
public class FileConfig implements PrinterConfig{

    /**
     * 文件保存路径
     */
    private String path;
    /**
     * 最大保存天数
     */
    private int saveDays = 7;
    /**
     * 文件大小的上限
     */
    private long maxFileSize = 5 * 1024 * 1024;
    /**
     * 文件大小的上限
     */
    private JLogLevel mLogLevel = JLogLevel.DEBUG;


    public FileConfig(String path, int saveDays, long maxFileSize,JLogLevel mLogLevel) {
        this.path = path;
        this.saveDays = saveDays;
        this.maxFileSize = maxFileSize;
        this.mLogLevel = mLogLevel;
    }

    public String getPath() {
        return path;
    }

    public int getSaveDays() {
        return saveDays;
    }

    public long getMaxFileSize() {
        return maxFileSize;
    }

    public JLogLevel getLogLevel() {
        return mLogLevel;
    }
}
