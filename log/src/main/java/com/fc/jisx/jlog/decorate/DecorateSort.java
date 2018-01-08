package com.fc.jisx.jlog.decorate;

import java.util.Comparator;

/**
 * File description.
 *
 * @author jisx
 */
public class DecorateSort implements Comparator<Integer> {

    @Override
    public int compare(Integer o1, Integer o2) {
        if (o1 < o2) {
            return -1;
        } else if (o1 == o2) {
            return 0;
        } else {
            return 1;
        }
    }

}
