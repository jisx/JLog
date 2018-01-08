package com.fc.jisx.jlog.decorate;

/**
 * 打印时，需要对打印的内容进行修饰，比如加入时间戳，加入边框等
 *
 * @author jisx
 * @see BorderDecorate 给打印内容周围加上边框，时日志更显眼一点
 * @see TrackDecorate 在控制台点击日志，可以直接跳转到打印日志语句所在的行。
 */
public abstract class Decorate {

    /**
     * 传入字符串，然后进行修饰或追加操作
     *
     * @param message 要处理的字符串
     * @return 修饰过后的字符串
     */
    public abstract String handle(String message);

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        return this.getClass().getName().equals(obj.getClass().getName());
    }

    /**
     * 加入排序是因为有些修饰有先后顺序的。
     * 98已经被{@link BorderDecorate#getSort()}使用
     * 99已经被{@link TrackDecorate#getSort()}使用
     *
     * @return 1-99
     * @see TrackDecorate 这个修饰必须在最后一个执行。不然没有点击效果。
     */
    public abstract int getSort();

}
