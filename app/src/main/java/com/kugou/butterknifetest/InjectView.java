package com.kugou.butterknifetest;

import android.app.Activity;

import com.annotation.BdView;

import java.lang.reflect.Field;


/**
 * Created by david on 2017/3/6.
 */

public class InjectView {

    public static void init(Activity activity){
        Field[]  fields = activity.getClass().getDeclaredFields();
        if (fields!=null && fields.length>0){
            for (Field field : fields){
                if (field.isAnnotationPresent(BdView.class)){
                    BdView bindView = field.getAnnotation(BdView.class);
                    try {
                        field.setAccessible(true);
                        field.set(activity,activity.findViewById(bindView.value()));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
