package com.fc.jisx.jlog.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * File description.
 *
 * @author jisx
 */
public class FileWrite implements Runnable {

    String mMessage;
    String mFileName;
    String mPath;

    public FileWrite(String message,String path, String fileName) {
        mMessage = message;
        this.mPath = path;
        this.mFileName = fileName;
    }

    @Override
    public void run() {
        try {
            FileWriter writer = new FileWriter(new File(mPath,mFileName), true);
            writer.write(mMessage);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("日志写入文件失败，请检查文件写入权限");
        }
    }
}
