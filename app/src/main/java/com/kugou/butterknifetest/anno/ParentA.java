package com.kugou.butterknifetest.anno;

/**
 * Created by david on 2017/3/8.
 */

@A(value = 11)
public class ParentA {

    @A(1)
    public void a(@A(2) int a){}
}
