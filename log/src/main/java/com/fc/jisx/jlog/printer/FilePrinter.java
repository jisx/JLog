/*
 * Copyright 2016 Elvis Hew
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.fc.jisx.jlog.printer;

import com.fc.jisx.jlog.printer.config.FileConfig;
import com.fc.jisx.jlog.utils.DateType;
import com.fc.jisx.jlog.utils.DateUtils;
import com.fc.jisx.jlog.utils.FileWrite;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Log {@link Printer} which should print the log to remote server.
 * <p>
 * This is just a empty implementation telling you that you can do
 * such thing, you can override {@link #println(int, String, String)} )} and sending the log by your
 * implementation.
 */
public class FilePrinter extends Printer {

    private boolean canWrite;

    private String fileName;

    private int index = 1;

    ExecutorService eService = Executors.newSingleThreadExecutor();

    FileConfig mFileConfig;

    public FilePrinter(FileConfig fileConfig) {
        this.mFileConfig = fileConfig;

        canWrite = checkPath(mFileConfig.getPath());

        //检查文件夹中的文件是否过期
        if (canWrite) {

            checkOverdueFile(mFileConfig.getPath());

            setFileName();

            //再判断当前要使用的日志文件的大小是否已经到规定的大小了，如果到了就赶紧换个文件
            checkFileSize();

        }

    }

    @Override
    public void println(int logLevel, String tag, String msg) {
        if(!canWrite){
            System.out.println("文件路径 path=" + mFileConfig.getPath() + "不可用");
            return;
        }

        if (logLevel >= mFileConfig.getLogLevel().getLevel()) {
            checkFileSize();
            eService.execute(new FileWrite(msg, mFileConfig.getPath(), fileName));
        }
    }

    /**
     * 检查文件路径是否可用
     *
     * @param path
     * @return
     */
    private boolean checkPath(String path) {
        if (path == null) {
            return false;
        }

        File file = new File(path);
        if (file.exists()) {
            return true;
        } else {
            file.mkdirs();
            return file.exists();
        }
    }


    /**
     * 检查预期的日志文件，并删除
     * <p>
     * 还有趁筛选文件的过程中，检查今天的文件名编号到几号了
     *
     * @param path
     */
    private void checkOverdueFile(String path) {
        File file = new File(path);
        //过期的日志文件
        File[] files = file.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                //看是否满足2017-12-31  10位的长度
                if (name.length() > 10) {
                    String dateStr = name.substring(0, 10);
                    //如果是其他文件名也过来了，可能格式化成时间会报错，所以拦截一手异常。如果不能格式化成时间，那就放过这个文件
                    Date date = DateUtils.strToDate(dateStr, DateType.N_YMD);

                    if (date == null) {
                        return false;
                    }

                    //检查今天的文件编号
                    if (dateStr.equals(DateUtils.getCurrentDate(DateType.N_YMD))) {
                        try {
                            String fileIndex = name.substring(name.lastIndexOf("-") + 1, name.lastIndexOf("."));
                            if (Integer.parseInt(fileIndex) > index) {
                                index = Integer.parseInt(fileIndex);
                            }
                        } catch (Exception e) {
                            //防止出现 2017-12-32-1A3.txt  这类恶作剧的文件名，防一手
                            e.printStackTrace();
                            return false;
                        }
                    }

                    //比较两个时间的大小。如果当前日期往前推7天的这个时间还是大于这个文件的时间，说明这个文件是过期文件
                    return DateUtils.compareDate(DateUtils.getDayBefore(mFileConfig.getSaveDays()), date) > -1;
                }
                return false;
            }
        });

        //防止没有读写权限
        if(files == null){
            return;
        }

        for (File file1 : files) {
            file1.delete();
        }

    }

    /**
     * 文件的大小
     */
    private void checkFileSize() {
        File file = new File(mFileConfig.getPath(), fileName);
        if (file.exists()) {

            if (file.length() > mFileConfig.getMaxFileSize()) {
                index++;
                setFileName();
            }

        } else {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("无法创建文件夹");
            }
        }
    }

    private void setFileName() {
        fileName = DateUtils.getCurrentDate(DateType.N_YMD) + "-" + index + ".txt";
    }

}
