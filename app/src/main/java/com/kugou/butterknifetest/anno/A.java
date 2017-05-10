package com.kugou.butterknifetest.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by david on 2017/3/8.
 */
@Inherited
@Retention(RetentionPolicy.CLASS)
public @interface A {
    int value();
}
