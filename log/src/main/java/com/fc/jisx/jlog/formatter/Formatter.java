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

package com.fc.jisx.jlog.formatter;

import com.fc.jisx.jlog.FormatterType;

/**
 * 把未知类型转换成字符串，用于打印的字符串
 * <p>
 * 一定要表明泛型的类型，因为默认是 {@link Object} 类型
 *
 * @param <T> 数据的类型
 */
public abstract class Formatter<T> {

    /**
     * Format the data to a readable and loggable string.
     *
     * @param data          the data to format
     * @param formatterType 格式枚举类型
     * @return the formatted string data
     */
    public String format(T data, FormatterType formatterType) {
        return data.toString();
    }

}
