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

import com.fc.jisx.jlog.JLogLevel;

/**
 * Log {@link Printer} using {@code System.out.println(String)}.
 *
 * @since 1.3.0
 */
public class ConsolePrinter extends Printer {


    @Override
    public void println(int logLevel, String tag, String message) {
        if (logLevel >= JLogLevel.ERROR.getLevel()) {
            System.err.println(message);
        } else {
            System.out.println(message);
        }
    }

}
