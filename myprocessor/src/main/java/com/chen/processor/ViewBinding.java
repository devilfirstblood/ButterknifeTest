package com.chen.processor;


import com.squareup.javapoet.TypeName;

final class ViewBinding {
    private final int id;
    private final String name;
    private final TypeName type;

    ViewBinding(int id, String name, TypeName type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public TypeName getType() {
        return type;
    }
}
