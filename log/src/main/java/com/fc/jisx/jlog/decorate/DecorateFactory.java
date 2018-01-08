package com.fc.jisx.jlog.decorate;

/**
 * File description.
 *
 * @author jisx
 * @date Created in 2018/1/6
 * @modify By:
 */
public class DecorateFactory {
    private static Decorate sBorderDecorate;
    private static Decorate sTrackDecorate;

    static{
        sBorderDecorate = new BorderDecorate();
        sTrackDecorate = new TrackDecorate();
    }

    public static Decorate getBorderDecorate() {
        return sBorderDecorate;
    }

    public static Decorate getTrackDecorate() {
        return sTrackDecorate;
    }
}
