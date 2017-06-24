package com.fc.jisx.jlog.model;

/**
 * Created by jisx on 2017/6/21.
 */

public abstract class BaseLogModel {

    private String mText;

    public BaseLogModel(String mText) {
        this.mText = mText;
    }

    public String getText() {
        return mText;
    }

    public void setText(String mText) {
        this.mText = mText;
    }

    @Override
    public String toString() {
        return mText;
    }
}
