package com.monolithiot.inventory.commons.vo;

import lombok.Data;

/**
 * Create By Levent8421
 * Create Time: 2020/1/8 17:59
 * Class Name: Tuple2
 * Author: Levent8421
 * Description:
 * 二维元组
 *
 * @author Levent*421
 */
@Data
public class Tuple2<T1, T2> {
    public static <T1, T2> Tuple2<T1, T2> of(T1 o1, T2 o2) {
        return new Tuple2<>(o1, o2);
    }

    private T1 o1;
    private T2 o2;

    public Tuple2(T1 o1, T2 o2) {
        this.o1 = o1;
        this.o2 = o2;
    }
}
