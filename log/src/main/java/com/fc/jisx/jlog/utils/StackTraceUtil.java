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

package com.fc.jisx.jlog.utils;

import com.fc.jisx.jlog.JLog;

/**
 * Utility related with stack trace.
 */
public class StackTraceUtil {

    private static final String LOG_STACK_TRACE_ORIGIN;

    static {
        String jLogClassName = JLog.class.getName();
        LOG_STACK_TRACE_ORIGIN = jLogClassName.substring(0, jLogClassName.lastIndexOf('.') + 1);
    }

    /**
     * Get the real stack trace, all elements that come from XLog library would be dropped.
     *
     * @return the real stack trace, all elements come from system and library user
     */
    public static StackTraceElement getStackTrack() {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        String className;
        StackTraceElement targetElement = null;
        for (int i = stackTrace.length -1 ; i >= 0; i--) {
            className = stackTrace[i].getClassName();
            if (className.startsWith(LOG_STACK_TRACE_ORIGIN) && (i + 1) < stackTrace.length) {
                targetElement = stackTrace[i + 1];
                break;
            }

        }
        return targetElement;
    }

}
