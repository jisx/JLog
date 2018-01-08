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


import com.fc.jisx.jlog.decorate.BorderDecorate;
import com.fc.jisx.jlog.decorate.Decorate;
import com.fc.jisx.jlog.decorate.DecorateSort;
import com.fc.jisx.jlog.decorate.TrackDecorate;
import com.fc.jisx.jlog.formatter.DefaultFormatter;
import com.fc.jisx.jlog.formatter.Formatter;
import com.fc.jisx.jlog.formatter.StringFormatter;
import com.fc.jisx.jlog.formatter.ThrowFormatter;
import com.fc.jisx.jlog.printer.AndroidPrinter;
import com.fc.jisx.jlog.printer.ConsolePrinter;
import com.fc.jisx.jlog.printer.FilePrinter;
import com.fc.jisx.jlog.printer.Printer;
import com.fc.jisx.jlog.printer.RemotePrinter;
import com.fc.jisx.jlog.printer.config.FileConfig;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

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
     * 格式化类的集合
     */
    public final Map<Class, Formatter> mFormatterHashMap;
    /**
     * 打印对象集合
     */
    public final Map<Class, Printer> mPrinterList;
    /**
     * 修饰字符串对象集合
     */
    public final Map<Integer, Decorate> mDecorateList;

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
         * 格式化类的集合 并加入了排序
         */
        private Map<Class, Formatter> mFormatterHashMap = new HashMap<>();
        /**
         * 打印的对象集合
         */
        private Map<Class, Printer> mPrinterList = new HashMap<>();
        /**
         * 修饰字符串对象集合
         */
        private Map<Integer, Decorate> mDecorateList = new TreeMap(new DecorateSort());

        public Builder() {
            //预装的格式化类
            this.mFormatterHashMap.put(String.class, new StringFormatter());
            this.mFormatterHashMap.put(Throwable.class, new ThrowFormatter());
            this.mFormatterHashMap.put(Object.class, new DefaultFormatter());
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
         * @param logLevel 日志级别
         * @return 返回Builder对象
         */
        public Builder logLevel(int logLevel) {
            this.logLevel = logLevel;
            return this;
        }

        /**
         * 设置TAG
         *
         * @param tag 标记
         * @return 返回Builder对象
         */
        public Builder tag(String tag) {
            this.tag = tag;
            return this;
        }

        /**
         * 设置边框
         *
         * @return 返回Builder对象
         */
        public Builder border() {
            BorderDecorate borderDecorate = new BorderDecorate();
            this.mDecorateList.put(borderDecorate.getSort(), borderDecorate);
            return this;
        }

        /**
         * 是否显示打印日志所在的代码行数（可以通过点击行数直接定位到打印的代码）
         *
         * @return 返回Builder对象
         */
        public Builder track() {
            TrackDecorate trackDecorate = new TrackDecorate();
            this.mDecorateList.put(trackDecorate.getSort(), trackDecorate);
            return this;
        }

        /**
         * 修饰需要打印的message
         * @param decorates 修饰字符串的对象集合
         * @return 返回Builder对象
         */
        public Builder decorate(Decorate... decorates) {
            for (Decorate decorate : decorates) {
                this.mDecorateList.put(decorate.getSort(), decorate);
            }
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
            this.mPrinterList.put(AndroidPrinter.class, new AndroidPrinter());
            return this;
        }

        public Builder console() {
            this.mPrinterList.put(ConsolePrinter.class, new ConsolePrinter());
            return this;
        }


        public Builder remote(String url) {
            this.mPrinterList.put(RemotePrinter.class, new RemotePrinter());
            return this;
        }


        public Builder file(FileConfig config) {
            this.mPrinterList.put(FilePrinter.class, new FilePrinter(config));
            return this;
        }

        /**
         * 这里检查去重，防止打印多次
         *
         * @return 返回Builder对象
         * @see Printer#equals(Object)
         */
        public LogConfiguration build() {
            return new LogConfiguration(this);
        }

    }

}
