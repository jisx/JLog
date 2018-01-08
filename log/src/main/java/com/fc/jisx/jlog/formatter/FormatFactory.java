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

package com.fc.jisx.jlog.formatter;

/**
 * Thrown to indicate that the format of the data is something wrong.
 */
public class FormatFactory {

    private static Formatter stringFormatter;
    private static Formatter defaultFormatter;
    private static Formatter throwFormatter;

    static {
        stringFormatter = new StringFormatter();
        defaultFormatter = new DefaultFormatter();
        throwFormatter = new ThrowFormatter();
    }

    public static Formatter getStringFormatter() {
        return stringFormatter;
    }

    public static Formatter getDefaultFormatter() {
        return defaultFormatter;
    }

    public static Formatter getThrowFormatter() {
        return throwFormatter;
    }

}
