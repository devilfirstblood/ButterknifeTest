package com.kugou.mybind;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by david on 2017/2/28.
 */

public class MyBind {

    static final Map<Class<?>, Constructor<? extends Mybinder>> BINDINGS = new LinkedHashMap<>();

    public static Mybinder bind(Activity target){
        View sourceView = target.getWindow().getDecorView();
        return createBinding(target,sourceView);
    }

    private static Mybinder createBinding(@NonNull Object target, @NonNull View source) {
        Class<?> targetClass = target.getClass();
        Constructor<? extends Mybinder> constructor = findBindingConstructorForClass(targetClass);

        //noinspection TryWithIdenticalCatches Resolves to API 19+ only type.
        try {
            return constructor.newInstance(target, source);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Unable to invoke " + constructor, e);
        } catch (InstantiationException e) {
            throw new RuntimeException("Unable to invoke " + constructor, e);
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            if (cause instanceof RuntimeException) {
                throw (RuntimeException) cause;
            }
            if (cause instanceof Error) {
                throw (Error) cause;
            }
            throw new RuntimeException("Unable to create binding instance.", cause);
        }
    }

    private static Constructor<? extends Mybinder> findBindingConstructorForClass(Class<?> cls) {
        Constructor<? extends Mybinder> bindingCtor = BINDINGS.get(cls);
        if (bindingCtor != null) {
            return bindingCtor;
        }
        String clsName = cls.getName();
        try {
            Class<?> bindingClass = Class.forName(clsName + "_ViewBding");
            //noinspection unchecked
            bindingCtor = (Constructor<? extends Mybinder>) bindingClass.getConstructor(cls, View.class);
        } catch (ClassNotFoundException e) {
            bindingCtor = findBindingConstructorForClass(cls.getSuperclass());
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Unable to find binding constructor for " + clsName, e);
        }
        BINDINGS.put(cls, bindingCtor);
        return bindingCtor;
    }
}
