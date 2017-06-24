package com.fc.jisx.jlog.log;

import android.support.annotation.NonNull;

import com.fc.jisx.jlog.JLogLevel;
import com.fc.jisx.jlog.RuntimeType;
import com.fc.jisx.jlog.model.FileLogModel;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * Created by zhaokaiqiang on 15/11/18.
 */
public class FileLog extends BaseLog {

    @Override
    public int getStackTraceIndex(String tag) {
        return tag == null ? 9 : 8;
    }

    @Override
    public void print(RuntimeType runtimeType, JLogLevel logLevel, String tag, Object obj) {
        FileLogModel fileLogModel = null;

        if (obj instanceof FileLogModel) {
            fileLogModel = (FileLogModel) obj;
        }

        if (fileLogModel == null
                || fileLogModel.getFilePath() == null
                || "".equals(fileLogModel.getFilePath())) {
            fileLogModel.setText("check fileName！");
            showFileLog(runtimeType, logLevel, tag, fileLogModel);
            return;
        }

        if (fileLogModel.getParentFile() == null || !fileLogModel.getParentFile().exists()) {
            fileLogModel.setText("parentFile exist?！");
            showFileLog(runtimeType, logLevel, tag, fileLogModel);
            return;
        }

        if (save(runtimeType, fileLogModel.getParentFile(), fileLogModel.getFilePath(), tag, fileLogModel.getText())) {
            fileLogModel.setText("save to logfile success:" + fileLogModel.getText());
            showFileLog(runtimeType, logLevel, tag, fileLogModel);
        } else {
            fileLogModel.setText("save to logfile fail:" + fileLogModel.getText());
            showFileLog(runtimeType, logLevel, tag, fileLogModel);
        }
    }

    private void showFileLog(RuntimeType runtimeType, JLogLevel logLevel, String tag, FileLogModel fileLogModel) {
        if (fileLogModel.isShowLog())
            super.print(runtimeType, logLevel, tag, fileLogModel);
    }

    private boolean save(RuntimeType runtimeType, File parentFile, @NonNull String fileName, String tag, String msg) {
        try {
            File file = new File(parentFile, fileName);
            PrintStream p = new PrintStream(new BufferedOutputStream(new FileOutputStream(file, true)), true, "UTF-8");
            String[] strings = wrapperContent(getStackTraceIndex(tag));
            p.write(((tag == null ? strings[1] : tag) + msg + "\n").getBytes("UTF-8"));
            p.close();
        } catch (Throwable e) {
            printError(runtimeType, tag, e);
            return false;
        }
        return true;
    }

    @Override
    public String parseToString(Object obj) {
        return obj.toString();
    }

}
