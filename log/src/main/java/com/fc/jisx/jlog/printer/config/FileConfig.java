package com.fc.jisx.jlog.printer.config;

import com.fc.jisx.jlog.printer.PrinterConfig;

/**
 * File description.
 *
 * @author jisx
 * @date Created in 2018/1/7
 * @modify By:
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


    public FileConfig(String path, int saveDays, long maxFileSize) {
        this.path = path;
        this.saveDays = saveDays;
        this.maxFileSize = maxFileSize;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getSaveDays() {
        return saveDays;
    }

    public void setSaveDays(int saveDays) {
        this.saveDays = saveDays;
    }

    public long getMaxFileSize() {
        return maxFileSize;
    }

    public void setMaxFileSize(long maxFileSize) {
        this.maxFileSize = maxFileSize;
    }
}
