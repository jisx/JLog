/*
 * Copyright 2015 Elvis Hew
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

package com.fc.jisx.jlog;


import com.fc.jisx.jlog.decorate.Decorate;
import com.fc.jisx.jlog.decorate.DecorateFactory;
import com.fc.jisx.jlog.formatter.FormatFactory;
import com.fc.jisx.jlog.formatter.Formatter;
import com.fc.jisx.jlog.printer.AndroidPrinter;
import com.fc.jisx.jlog.printer.ConsolePrinter;
import com.fc.jisx.jlog.printer.FilePrinter;
import com.fc.jisx.jlog.printer.Printer;
import com.fc.jisx.jlog.printer.RemotePrinter;
import com.fc.jisx.jlog.printer.config.FileConfig;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Use the {@link Builder} to construct a {@link LogConfiguration} object.
 */
public class LogConfiguration {
    /**
     * 日志级别
     */
    public final int logLevel;
    /**
     * 默认TAG
     */
    public final String tag;
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
     * 格式化类的集合
     */
    public final Map<Class, Formatter> mFormatterHashMap;
    /**
     * 打印对象集合
     */
    public final List<Printer> mPrinterList;

    public final List<Decorate> mDecorateList;

    private LogConfiguration(Builder builder) {
        logLevel = builder.logLevel;

        tag = builder.tag;

        mFormatterHashMap = builder.mFormatterHashMap;

        mPrinterList = builder.mPrinterList;

        mDecorateList = builder.mDecorateList;

    }

    public static class Builder {

        private static final int DEFAULT_LOG_LEVEL = JLogLevel.VERBOSE.getLevel();

        private static final String DEFAULT_TAG = "JLog";

        /**
         * 默认日志级别
         */
        private int logLevel = DEFAULT_LOG_LEVEL;

        /**
         * 默认TAG
         */
        private String tag = DEFAULT_TAG;
        /**
         * 格式化类的集合
         */
        private Map<Class, Formatter> mFormatterHashMap = new HashMap<>();
        /**
         * 打印的对象集合
         */
        private List<Printer> mPrinterList = new ArrayList<>();
        /**
         * 修饰字符串对象集合
         */
        private List<Decorate> mDecorateList = new ArrayList<>();

        public Builder() {
            //预装的格式化类
            this.mFormatterHashMap.put(String.class, FormatFactory.getStringFormatter());
            this.mFormatterHashMap.put(Throwable.class, FormatFactory.getThrowFormatter());
            this.mFormatterHashMap.put(Object.class, FormatFactory.getDefaultFormatter());
        }

        public Builder(LogConfiguration logConfiguration) {
            this.logLevel = logConfiguration.logLevel;

            this.tag = logConfiguration.tag;

            this.mFormatterHashMap = logConfiguration.mFormatterHashMap;
            this.mPrinterList = logConfiguration.mPrinterList;
            this.mDecorateList = logConfiguration.mDecorateList;
        }

        /**
         * 设置打印的日志级别
         *
         * @param logLevel
         * @return
         */
        public Builder logLevel(int logLevel) {
            this.logLevel = logLevel;
            return this;
        }

        /**
         * 设置TAG
         *
         * @param tag
         * @return
         */
        public Builder tag(String tag) {
            this.tag = tag;
            return this;
        }

        /**
         * 设置边框
         *
         * @return
         */
        public Builder border() {
            if (!this.mDecorateList.contains(DecorateFactory.getBorderDecorate()))
                this.mDecorateList.add(DecorateFactory.getBorderDecorate());
            return this;
        }

        /**
         * 是否显示打印日志所在的代码行数（可以通过点击行数直接定位到打印的代码）
         *
         * @return
         */
        public Builder track() {
            if (!this.mDecorateList.contains(DecorateFactory.getTrackDecorate()))
                this.mDecorateList.add(DecorateFactory.getTrackDecorate());
            return this;
        }

        public Builder formatter(Formatter... formatterClass) {
            for (Formatter aClass : formatterClass) {
                Type type2 = aClass.getClass().getGenericSuperclass();
                ParameterizedType parameterizedType = (ParameterizedType) type2;
                this.mFormatterHashMap.put((Class) parameterizedType.getActualTypeArguments()[0], aClass);
            }
            return this;
        }

        public Builder android() {
            this.mPrinterList.add(new AndroidPrinter());
            return this;
        }

        public Builder console() {
            this.mPrinterList.add(new ConsolePrinter());
            return this;
        }


        public Builder remote(String url) {
            this.mPrinterList.add(new RemotePrinter());
            return this;
        }


        public Builder file(FileConfig config) {
            this.mPrinterList.add(new FilePrinter(config));
            return this;
        }

        /**
         * 这里检查去重，防止打印多次
         * @see Printer#equals(Object)
         * @return
         */
        public LogConfiguration build() {
            if (this.mPrinterList.size() == 0) {
                this.mPrinterList.add(new ConsolePrinter());
            }

            HashSet hashSet = new HashSet(mPrinterList);
            mPrinterList.clear();
            mPrinterList.addAll(hashSet);

            return new LogConfiguration(this);
        }

    }

}
