package com.fc.jisx.jlog.decorate;

import android.annotation.SuppressLint;

/**
 * 日志文字的边框处理
 *
 * @author jisx
 *         <br>╔════════════════════════════════════════════════════════════════════════════
 *         <br>║String segment 1
 *         <br>╚════════════════════════════════════════════════════════════════════════════
 */
public class BorderDecorate extends Decorate {


    private static final char VERTICAL_BORDER_CHAR = '║';

    // Length: 100.
    private static final String TOP_HORIZONTAL_BORDER =
            "╔═════════════════════════════════════════════════" +
                    "══════════════════════════════════════════════════";

    // Length: 99.
    private static final String DIVIDER_HORIZONTAL_BORDER =
            "╟─────────────────────────────────────────────────" +
                    "──────────────────────────────────────────────────";

    // Length: 100.
    private static final String BOTTOM_HORIZONTAL_BORDER =
            "╚═════════════════════════════════════════════════" +
                    "══════════════════════════════════════════════════";

    @Override
    public String handle(String message) {
        if (message == null) {
            return "";
        }

        StringBuilder msgBuilder = new StringBuilder();
        msgBuilder.append(TOP_HORIZONTAL_BORDER).append(lineSeparator());
        msgBuilder.append(appendVerticalBorder(message));
        msgBuilder.append(lineSeparator()).append(BOTTOM_HORIZONTAL_BORDER);
        return msgBuilder.toString();
    }

    @Override
    public int getSort() {
        return 98;
    }

    /**
     * Add {@value #VERTICAL_BORDER_CHAR} to each line of msg.
     *
     * @param msg the message to add border
     * @return the message with {@value #VERTICAL_BORDER_CHAR} in the start of each line
     */
    private String appendVerticalBorder(String msg) {
        StringBuilder borderedMsgBuilder = new StringBuilder(msg.length() + 10);
        String[] lines = msg.split(lineSeparator());
        for (int i = 0, N = lines.length; i < N; i++) {
            if (i != 0) {
                borderedMsgBuilder.append(lineSeparator());
            }
            String line = lines[i];
            borderedMsgBuilder.append(VERTICAL_BORDER_CHAR).append(line);
        }
        return borderedMsgBuilder.toString();
    }

    @SuppressLint("NewApi")
    String lineSeparator() {
        return System.lineSeparator();
    }

}
